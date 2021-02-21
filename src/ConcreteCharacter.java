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
    public List<Action> getActions(GameState gameState) throws NoSuchMethodException {
        List<Action> actions = new ArrayList<>();
        for (Ability a: abilityList){
            Action action=a.getAction(gameState);
            if(action==null) continue;;
            action.setConcreteCharacter(this);
            actions.add(action);
        }
        return actions;
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
