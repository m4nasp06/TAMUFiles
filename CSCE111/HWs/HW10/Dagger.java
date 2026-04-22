public class Dagger extends LevelingSystem implements Weapon {

    public Dagger() {
        super();
    }

    public String getName() {
        return "Dagger (Lvl " + getLevel() + ")";
    }

    public String getIcon() {
        return "🗡️";
    }

    public int calculateDamage(int distance, Player player) {
        if (distance > 5) {
            return 0;
        } else {
            return finalizeDamage(30, 50, player);
        }
    }
}
