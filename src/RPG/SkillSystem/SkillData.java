package RPG.SkillSystem;

import java.util.HashMap;
import java.util.Map;

public class SkillData {
    private static Map<String,SkillData> map = new HashMap<>();
    final String name;
    final int cost;
    final int range;
    final int damage;
    final int coolDown;
    public static SkillData getInstance(String s){
        if(map.containsKey(s)) return map.get(s);
        return null;
    }
    private SkillData(String name,int cost,int range,int damage,int coolDown){
        this.name=name;
        this.cost=cost;
        this.range=range;
        this.damage=damage;
        this.coolDown=coolDown;
        map.put(this.name,this);
    }
    public static class SkillBuilder{
        private String name="";
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
            new SkillData(name,cost,range,damage,coolDown);
        }
    }
}
