public class Sorcerer extends Player {

    private static int skill = 4;
    private static int luck = 5;

    public Sorcerer() {
        super();
    }

    public String getPlayerType() {
        return "Sorcerer";
    }

    public int getSkillBonus() {
        return skill;
    }

    public int getLuckBonus() {
        return luck;
    }
}
