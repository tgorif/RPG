package RPG.SkillSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
todo write tests
 */

public class SkillData {
    private static Map<String,SkillData> map = new HashMap<>();
    private static Logger LOGGER =Logger.getLogger(SkillData.class.getName());
    final String name;
    final String template;
    final int cost;
    final int range;
    final int damage;
    final int coolDown;
    public static SkillData getInstance(String s){
        if(map.containsKey(s)) return map.get(s);
        return null;
    }
    private SkillData(String name,String template,int cost,int range,int damage,int coolDown){
        this.name=name;
        this.cost=cost;
        this.range=range;
        this.damage=damage;
        this.coolDown=coolDown;
        this.template=template;
        map.put(this.name,this);
        LOGGER.log(Level.FINE,"added SkillData for "
                + this.name + " with template "
                + this.template + " Number of entries is "
                + map.size());
    }
    public static class SkillBuilder{
        private String name="";
        private String template="";
        private int cost;
        private int range;
        private int damage;
        private int coolDown;

        public SkillBuilder() {

        }
        public SkillBuilder name(String s){
            this.name=s;
            return this;
        }
        public SkillBuilder template(String s){
            this.template=s;
            return this;
        }
        public SkillBuilder cost(int s){
            this.cost=s;
            return this;
        }
        public SkillBuilder range(int s){
            this.range=s;
            return this;
        }
        public SkillBuilder damage(int s){
            this.damage=s;
            return this;
        }
        public SkillBuilder coolDown(int s){
            this.coolDown=s;
            return this;
        }
        public void build(){
            if(name.length()==0 || template.length()==0){
                LOGGER.log(Level.SEVERE,"added SkillData with empty name or empty template Values :"
                        +" cost " + cost
                        +" range " + range
                        +" damage " + damage
                        +" coolDown " + coolDown);
            }
            new SkillData(name,template,cost,range,damage,coolDown);
        }
    }
}
