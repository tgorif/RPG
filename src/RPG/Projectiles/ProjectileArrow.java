package RPG.Projectiles;

public class ProjectileArrow implements StrategyProjectile {
    public int damage;
    public ProjectileArrow(int damage){
        this.damage=damage;
    }
    @Override
    public void setDamage() {
     damage=10;
    }

    @Override
    public int getDamage() {
        return damage;
    }
}
