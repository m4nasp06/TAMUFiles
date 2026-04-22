public class LevelingSystem {

    private int level;

    public LevelingSystem() {
        this.level = 1;
    }

    public void upgrade() {
        level++;
    }

    public int getLevel() {
        return level;
    }

    public int finalizeDamage(int minBase, int maxBase, Player player) {
        int levelBonus = (level - 1) * 5;
        int min = minBase + levelBonus + player.getSkillBonus();
        int max = maxBase + levelBonus + player.getLuckBonus();
        if (max <= min) max = min + 1;
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
