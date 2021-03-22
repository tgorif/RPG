package RPG.Character;

public class CharacterActionManager {
    CombatCharacter owner;
    public CharacterActionManager(CombatCharacter owner){
        this.owner=owner;
    }
    public int resolveHit(int damage){
        int currentHP=owner.attributes.getHP();
        owner.attributes.changeHP(-Math.max(1,damage-owner.attributes.getArmor()));
        return currentHP-owner.attributes.getHP();
    }

}
