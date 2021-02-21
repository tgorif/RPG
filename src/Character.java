import java.util.ArrayList;
import java.util.List;

public class Character {
    List<Class> classList;
    public Character(){
        classList=new ArrayList<>();
    }
    public void addClass(Class c){
        classList.add(c);
    }
}
