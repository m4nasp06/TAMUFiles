public class Bow extends LevelingSystem implements Weapon {

    public Bow() {
        super();
    }

    public String getName() {
        return "Bow & Arrow (Lvl " + getLevel() + ")";
    }

    public String getIcon() {
        return "🏹";
    }

    public int calculateDamage(int distance, Player player) {
        if (distance > 70) {
            return finalizeDamage(2, 8, player);
        } else if (distance >= 40) {
            return finalizeDamage(5, 12, player);
        } else if (distance >= 15) {
            return finalizeDamage(8, 18, player);
        } else if (distance >= 8) {
            return finalizeDamage(11, 24, player);
        } else {
            return finalizeDamage(1, 4, player);
        }
    }
}
