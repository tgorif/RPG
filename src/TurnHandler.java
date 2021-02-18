import java.util.List;

public class TurnHandler {
    int turn;
    Map map;
    ResultHandler resultHandler;
    Party[] parties;
    List<Turn> turns;
    private void nextTurn(){
        new Turn();
    }
}
