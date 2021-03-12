package RPG.Projectiles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectileData {
    final String name;
    final int damage;
    final List<String> statusList;
    final String template;
    private static final Map<String,ProjectileData> map = new HashMap<>();

    public static ProjectileData get(String s){
        if(map.containsKey(s)) return map.get(s);
        return null;
    }
    private ProjectileData(String name,String template, int damage, List<String> statusList){
        this.name = name;
        this.damage = damage;
        this.statusList = statusList;
        this.template=template;
        map.put(this.name,this);
    }
    public static class ProjectileBuilder{
        private String name="";
        private int damage;
        private List<String> statusList=new ArrayList<>();
        private String template="";

        public ProjectileBuilder(){

        }
        public ProjectileBuilder name(String s){
            this.name=s;
            return this;
        }
        public ProjectileBuilder template(String s){
            this.template=s;
            return this;
        }
        public ProjectileBuilder damage(int s){
            this.damage=s;
            return this;
        }
        public ProjectileBuilder status(String s){
            statusList.add(s);
            return this;
        }
        public ProjectileData build(){
            return new ProjectileData(name,template,damage,statusList);
        }
    }
}
