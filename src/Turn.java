public class Turn {
    ConcreteCharacter[] concreteCharacters;

    private void resolveTurn(){
        for (ConcreteCharacter c : concreteCharacters){
            c.takeTurn();
        }
    }
}
