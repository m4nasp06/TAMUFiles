public class Red extends LevelingSystem implements Weapon {

    public Red() {
        super();
    }

    public String getName() {
        return "Red (Lvl " + getLevel() + ")";
    }

    public String getIcon() {
        return "🔴";
    }

    public int calculateDamage(int distance, Player player) {
        if (distance > 70) {
            return finalizeDamage(10, 22, player);
        } else if (distance >= 40) {
            return finalizeDamage(20, 38, player);
        } else if (distance >= 15) {
            return finalizeDamage(14, 28, player);
        } else if (distance >= 8) {
            return finalizeDamage(8, 18, player);
        } else {
            return finalizeDamage(3, 10, player);
        }
    }
}
