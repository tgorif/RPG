public class StatPerk extends Perk{
    private int HP;
    private int SPD;
    private int Movement;
    private StatPerk(int h,int s,int m,String name){
        super(name);
        HP=h;
        SPD=s;
        Movement=m;
        this.name=name;
    }
    int getHPBonus(){
        return HP;
    }
    int getSPDBonus(){
        return SPD;
    }
    int getMovementBonus(){
        return Movement;
    }
    public static class StatPerkBuilder{
        private int hp=0;
        private int SPD=0;
        private int movement=0;
        private String name="";
        public StatPerkBuilder(){

        }
        public StatPerkBuilder setHP(int h){
            hp=h;
            return this;
        }
        public StatPerkBuilder setSPD(int s){
            SPD=s;
            return this;
        }
        public StatPerkBuilder setName(String s){
            name=s;
            return this;
        }
        public StatPerkBuilder setMovement(int movement){
            this.movement=movement;
            return this;
        }
        public StatPerk build(){
            return new StatPerk(hp,SPD,movement,name);
        }
    }
}
