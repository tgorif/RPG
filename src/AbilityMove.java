import java.lang.reflect.Method;
import java.util.Iterator;

public class AbilityMove extends Ability{
    public Action getAction(GameState gameState) throws NoSuchMethodException {
        Action result = new Action();
        Method m =GameState.class.getDeclaredMethod(
                "changeCharacterPosition",ConcreteCharacter.class, int.class,int.class,int.class);

        result.setMethod(m);
        result.setParameter(10);
        return result;
    }

}
