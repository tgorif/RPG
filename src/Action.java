import java.lang.reflect.Method;

public class Action {
    Method method;
    int parameter;
    ConcreteCharacter concreteCharacter;
    Position position;

    public void setMethod(Method m){
        method=m;
    }
    public void setParameter(int p){
        parameter=p;
    }
    public void setConcreteCharacter(ConcreteCharacter concreteCharacter){
        this.concreteCharacter=concreteCharacter;
    }
    public void setPosition(Position p){
        position=p;
    }
    public Method getMethod(){
        return method;
    }
    public Position getPosition(){
        return position;
    }
}
