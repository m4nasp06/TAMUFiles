public class Farmer extends Player {

    private static int skill = -2;
    private static int luck = 15;

    public Farmer() {
        super();
    }

    public String getPlayerType() {
        return "Farmer";
    }

    public int getSkillBonus() {
        return skill;
    }

    public int getLuckBonus() {
        return luck;
    }
}
