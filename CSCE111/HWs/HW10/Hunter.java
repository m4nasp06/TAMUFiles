public class Hunter extends Player {

    private static int skill = 3;
    private static int luck = 4;

    public Hunter() {
        super();
    }

    public String getPlayerType() {
        return "Hunter";
    }

    public int getSkillBonus() {
        return skill;
    }

    public int getLuckBonus() {
        return luck;
    }
}
