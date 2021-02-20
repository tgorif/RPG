import java.util.HashMap;
import java.util.Map;
public class Perk {
    String name;
    static Map<String,Perk> PerkMap=new HashMap<>();
    public Perk(String name){
        this.name=name;
        PerkMap.put(this.name,this);
    }
}
