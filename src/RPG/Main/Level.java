package RPG.Main;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Level {
    int[] x=new int[2];
    int[] y=new int[2];
    int[] z=new int[2];
    static Level currentLevel;
    public Level(){
        x[0]=0;
        x[1]=1000;
        y[0]=0;
        y[1]=1000;
        z[0]=0;
        z[1]=1000;
        currentLevel=this;
    }
    public static Level getCurrentLevel(){
        return currentLevel;
    }
    public boolean isValid(int x,int y,int z) {
        return this.x[0] <= x && this.x[1] >= x && this.y[0] <= y && this.y[1] >= y && this.z[0] <= z && this.z[1] >= z;
    }
    public boolean isValid(Position position, int x, int y, int z){
        return isValid(position.x+x, position.y+y, position.z+z);
    }
    public boolean isValid(Position position){
        return isValid(position.x, position.y, position.z);
    }
    public List<Position> getPositionsInRange(Position position,int distance){
        if(distance==0) System.out.println("distance ==0");
        List<Position> result = new ArrayList<>();
        if(isValid(position,distance,0,0)){
            result.add(new Position(position.x+distance,position.y, position.z));
        }
        if(isValid(position,-distance,0,0)){
            result.add(new Position(position.x-distance,position.y, position.z));
        }
        if(isValid(position,0,distance,0)){
            result.add(new Position(position.x,position.y+distance, position.z));
        }
        if(isValid(position,0,-distance,0)){
            result.add(new Position(position.x,position.y-distance, position.z));
        }
        if(isValid(position,0,0,distance)){
            result.add(new Position(position.x,position.y, position.z+distance));
        }
        if(isValid(position,0,0,-distance)){
            result.add(new Position(position.x,position.y, position.z-distance));
        }
        return result;
    }
    public int getDistance(Position a,Position b){
        int result=0;
        result+=Math.abs(a.x-b.x);
        result+=Math.abs(a.y-b.y);
        result+=Math.abs(a.z-b.z);
        return result;

    }
    public boolean isEqualPosition(Position a,Position b){
        if(a.x!=b.x || a.y!=b.y || a.z!=b.z) return false;
        return true;
    }
}
