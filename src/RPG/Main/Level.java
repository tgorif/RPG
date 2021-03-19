package RPG.Main;

import org.junit.Test;

import java.util.*;
import java.util.logging.Logger;

public class Level {
    int[] x=new int[2];
    int[] y=new int[2];
    int[] z=new int[2];
    public Map<List<Integer>,Tile> tiles=new HashMap<>();
    private final Logger LOGGER = Logger.getLogger(Level.class.getName());
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
    public Level(int diameter,int height){
        if(diameter%2==0)LOGGER.log(java.util.logging.Level.SEVERE,"created map with even diameter");
        for(int h=0;h<height;h++) {
            for (int i = diameter / (-2); i < diameter / 2; i++) {
                for (int j = diameter / (-2); j < diameter / 2; j++) {
                    for (int k = diameter / (-2); k < diameter / 2; k++) {
                        if (i + j + k == h){
                            Tile t = new Tile(i,j,k,h);
                            tiles.put(t.toList(),t);
                        }
                    }
                }
            }
        }
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
    public List<Tile> getTilesInRange(Tile t,int distance){
        List<Tile> result =new ArrayList<>();
        for(int i=t.x-(distance/2);i<t.x+(distance/2);i++){
            for(int j=t.y-(distance/2);j<t.y+(distance/2);j++){
                for(int k=t.z-(distance/2);k<t.z+(distance/2);k++){
                    if(i+j+k==t.level){
                        List<Integer> tmp = List.of(i,j,k,t.level);
                        if(tiles.containsKey(tmp)){
                            result.add(tiles.get(tmp));
                        }
                    }
                }
            }
        }
        return result;
    }
    class Tile{
        final int x;
        final int y;
        final int z;
        final int level;
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tile tile = (Tile) o;
            return x == tile.x && y == tile.y && z == tile.z && level == tile.level;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x*7, y*31, z*71, level*47);
        }
        public List<Integer> toList(){
            return List.of(x,y,z,level);
        }
        public int getDistance(Tile other){
            return  (Math.abs(this.x-other.x)
                    +Math.abs(this.y-other.y)
                    +Math.abs(this.z-other.z))
                    /2+(this.level-other.level);
        }

        private Tile(int x,int y,int z,int level){
            this.x=x;
            this.y=y;
            this.z=z;
            this.level=level;
            if(x+y+z!=level){
                LOGGER.log(java.util.logging.Level.SEVERE,"created unreachable Tile " + x + " " + y + " " + level  +" " + z);
            }
        }
    }
}
