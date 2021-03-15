package RPG.StatusEffects;

import RPG.SkillSystem.SkillData;

import java.util.HashMap;
import java.util.Map;

public class StatusData {
    private static final Map<String,StatusData> map = new HashMap<>();
    final String name;
    final String template;
    final int duration;
    final int HPChange;

    public static StatusData get(String s){
        if(map.containsKey(s)) return map.get(s);
        return null;
    }
    private StatusData(String name,String template,int duration,int HPChange){
        this.name = name;
        this.template = template;
        this.duration = duration;
        this.HPChange = HPChange;
    }
    public static class StatusBuilder{
        private String name="";
        private String template ="";
        private int duration;
        private int HPChange;
        public StatusBuilder name(String s){
            name=s;
            return this;
        }
        public StatusBuilder template(String s){
            template=s;
            return this;
        }
        public StatusBuilder duration(int s){
            duration=s;
            return this;
        }
        public StatusBuilder HPChange(int s){
            HPChange=s;
            return this;
        }
        public StatusData build(){
            return new StatusData(name,template,duration,HPChange);
        }

    }
}
