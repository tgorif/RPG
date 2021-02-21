import java.util.ArrayList;
import java.util.List;

public class ConcreteCharacter {
    int HP;
    int SPD;
    int movement;
    List<Ability> abilityList=new ArrayList<>();
    List<Perk> preCombatPerks=new ArrayList<>();
    Position position;

    public ConcreteCharacter(Character character,Position position){
        for(Class c : character.classList){
            for(Perk p : c.perks){
                if(p instanceof StatPerk){
                    preCombatPerks.add(p);
                }
                else{
                    assert p instanceof CombatPerk;
                    CombatPerk cp=(CombatPerk)p;
                    abilityList.add(cp.getAbility());
                }
            }
        }
        this.position=position;
        resolvePreCombatPerks();
    }
    private void resolvePreCombatPerks(){
        for(Perk p : preCombatPerks){
            StatPerk statPerk=(StatPerk) p;
            this.HP+=statPerk.getHPBonus();
            this.SPD=statPerk.getSPDBonus();
            this.movement=statPerk.getMovementBonus();
        }
    }
}
