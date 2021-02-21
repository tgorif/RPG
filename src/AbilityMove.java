public class AbilityMove extends Ability{
    public void use(GameState gameState,ConcreteCharacter origin){
        if(gameState.level.isValid(origin.position,10,10,0)){
            origin.position.x+=10;
            origin.position.y+=10;
        }

    }

}
