public interface Weapon {
    public String getName();
    public String getIcon();
    public int calculateDamage(int distance, Player player);
    public void upgrade();
    public int getLevel();
}
