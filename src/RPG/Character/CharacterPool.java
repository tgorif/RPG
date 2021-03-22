package RPG.Character;

import java.util.*;

public class CharacterPool {
    private static Map<String,CharacterData> characterDataMap=new HashMap<>();
    private static List<CharacterData> list = new ArrayList<>();
    private static int count=0;
    public static CharacterData getCharacterData(String s){
        return characterDataMap.get(s);
    }
    public static CharacterData getNext(){
        if(count>=list.size())count=0;
        return list.get(count++);
    }
    public static class CharacterData {
        private final String name;
        private final List<String> PerkTrees=new ArrayList<>();
        private final List<String> perks=new ArrayList<>();
        public CharacterData(String name){
            this.name=name;
            characterDataMap.put(name,this);
            list.add(this);
            Collections.shuffle(list);
        }
        public void addPerkTree(String s){
            PerkTrees.add(s);
        }
        public void addPerk(String s){
            perks.add(s);
        }
        public String getName() {
            return name;
        }
        public List<String> getPerkTrees() {
            return PerkTrees;
        }
        public List<String> getPerks() {
            return perks;
        }
    }
}
