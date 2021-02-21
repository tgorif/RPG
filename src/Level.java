public class Level {
    int[] x=new int[2];
    int[] y=new int[2];
    int[] z=new int[2];
    public Level(){
        x[0]=0;
        x[1]=1000;
        y[0]=0;
        y[1]=1000;
        z[0]=0;
        z[1]=1000;
    }
    public boolean isValid(int x,int y,int z) {
        return this.x[0] <= x && this.x[1] >= x && this.y[0] <= y && this.y[1] >= y && this.z[0] <= z && this.z[1] >= z;
    }
    public boolean isValid(Position position,int x,int y,int z){
        return isValid(position.x+x, position.y+y, position.z+z);
    }
}
