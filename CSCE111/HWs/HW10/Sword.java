public class Sword extends LevelingSystem implements Weapon {

    public Sword() {
        super();
    }

    public String getName() {
        return "Sword (Lvl " + getLevel() + ")";
    }

    public String getIcon() {
        return "⚔️";
    }

    public int calculateDamage(int distance, Player player) {
        if (distance > 8) {
            return 0;
        } else if (distance >= 5) {
            return finalizeDamage(25, 45, player);
        } else {
            return finalizeDamage(10, 20, player);
        }
    }
}
