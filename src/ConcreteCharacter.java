import java.util.ArrayList;
import java.util.List;

public class ConcreteCharacter {
    int HP;
    int SPD;
    int movement;
    List<Ability> abilityList=new ArrayList<>();
    List<IStrategyAction> actionList=new ArrayList<>();
    List<Perk> preCombatPerks=new ArrayList<>();
    Position position;
/*
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
        for(Ability ability : abilityList){
            actionList.add((IStrategyAction)ability.action.clone());
        }
        this.position=position;
        resolvePreCombatPerks();
    }
    public List<IStrategyAction> getActions(GameState gameState){
        List<IStrategyAction> actions = new ArrayList<>();
        for (Ability a: abilityList){
            IStrategyAction action=a.getAction(gameState);
            if(action==null) continue;;
            action.setConcreteCharacter(this);
            actions.add(action);
        }
        return actions;
    }
    public void updateStatus(){

    }
    private void resolvePreCombatPerks(){
        for(Perk p : preCombatPerks){
            StatPerk statPerk=(StatPerk) p;
            this.HP+=statPerk.getHPBonus();
            this.SPD=statPerk.getSPDBonus();
            this.movement=statPerk.getMovementBonus();
        }
    }
    */
}
