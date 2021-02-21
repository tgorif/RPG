public class CombatPerk extends Perk {
    Ability ability;

    public CombatPerk(String name,Ability ability){
        super(name);
        this.name=name;
        this.ability=ability;
    }
    public Ability getAbility(){
        return ability;
    }
}
