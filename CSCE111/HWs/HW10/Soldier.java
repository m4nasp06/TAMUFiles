public class Soldier extends Player {

    private static int skill = 5;
    private static int luck = 1;

    public Soldier() {
        super();
    }

    public String getPlayerType() {
        return "Soldier";
    }

    public int getSkillBonus() {
        return skill;
    }

    public int getLuckBonus() {
        return luck;
    }
}
