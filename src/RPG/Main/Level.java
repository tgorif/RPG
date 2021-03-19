package RPG.Main;

import java.util.*;
import java.util.logging.Logger;

public class Level {
    private static final Logger LOGGER = Logger.getLogger(Level.class.getName());
    private static Level currentLevel;
    public Map<List<Integer>,Tile> tiles;
    private final int[][] dir =new int[][]{{-1,0,1},{-1,1,0},{0,-1,1},{0,1,-1},{1,-1,0},{1,0,-1}};

    public static Level getCurrentLevel(){
        return currentLevel;
    }
    public Level(int diameter,int height){
        if(diameter%2==0)LOGGER.log(java.util.logging.Level.SEVERE,"created map with even diameter");
        tiles=new HashMap<>();
        currentLevel=this;
        int floor=-(diameter-1)/2;
        int ceiling=(diameter-1)/2;
        for(int i=floor;i<=ceiling;i++){
            for(int j=floor-Math.min(i,0);j<=ceiling-Math.max(0,i);j++){
                if(i+j<floor || i+j>ceiling) continue;
                for(int h=0;h<height;h++){
                    Tile t = new Tile(i,j,-(i+j),h);
                    tiles.put(t.toList(),t);
                }
            }
        }
    }
    public List<Tile> getTilesInRange(Tile t,int distance){
        List<Tile> result =new ArrayList<>();
        for(int i=t.x-distance;i<=t.x+distance;i++){
            for(int j=t.y-distance;j<=t.y+distance;j++){
                for(int k=t.z-distance;k<=t.z+distance;k++){
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
    public int getDistance(Tile a, Tile other){
        return  (Math.abs(a.x-other.x)
                +Math.abs(a.y-other.y)
                +Math.abs(a.z-other.z))
                /2+(a.level-other.level);
    }
    public boolean canEnter(Tile tile){
        return tile.contains==null;
    }
    public List<Tile> getPath(Tile start,Tile target){
        Queue<Path> queue= new PriorityQueue<>((path, t1) -> Integer.compare(path.length,t1.length));
        queue.add(new Path(0,start));
        while (!queue.isEmpty()){
            Path current=queue.poll();
            if(current.current==target) return current.toList();
            for(int[] d : dir){
                Tile tmp = tiles.get(List.of(current.current.x+d[0],current.current.y+d[1],current.current.z+d[2],
                        current.current.level));
                if(tmp!=null && canEnter(tmp)){
                    queue.add(new Path(tmp,current));
                }
            }
        }
        return new ArrayList<>();
    }

    public class Tile{
        final int x;
        final int y;
        final int z;
        final int level;
        Object contains;
        private Tile(int x,int y,int z,int level){
            this.x=x;
            this.y=y;
            this.z=z;
            this.level=level;
            if(x+y+z!=level){
                LOGGER.log(java.util.logging.Level.SEVERE,"created unreachable Tile " + x + " " + y + " " + z  +" " + level);
            }
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tile tile = (Tile) o;
            return x == tile.x && y == tile.y && z == tile.z && level == tile.level;
        }
        public List<Integer> toList(){
            return List.of(x,y,z,level);
        }
    }
    private class Path{
        int length;
        Tile current;
        Path previous;
        private Path(int length,Tile current){
            this.current=current;
            this.length=length;
        }
        private Path(Tile current,Path last){
            this.current=current;
            this.length=last.length+1;
            this.previous=last;
        }

        private List<Tile> toList() {
            List<Tile> result = new ArrayList<>();
            if(previous!=null){
                previous.toList(result);
            }
            result.add(this.current);
            return result;
        }
        private void toList(List<Tile> result) {
            if(previous!=null){
                previous.toList(result);
            }
            result.add(this.current);
        }
    }
}
