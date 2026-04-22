import java.util.ArrayList;

public abstract class Player {

    private String name;
    private String avatar;
    private int health;
    private int gold;
    private int position;
    private ArrayList<Weapon> inventory;
    private Weapon currentWeapon;

    public Player() {
        this.inventory = new ArrayList<>();
        this.health = 100;
        this.gold = 100;
        this.position = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void addWeapon(Weapon weapon) {
        inventory.add(weapon);
    }

    public void equipWeapon(int index) {
        currentWeapon = inventory.get(index);
    }

    public ArrayList<Weapon> getInventory() {
        return inventory;
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public int attack(int distance) {
        if (currentWeapon == null) return 0;
        return currentWeapon.calculateDamage(distance, this);
    }

    public void takeDamage(int amount) {
        this.health = this.health - amount;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public abstract String getPlayerType();

    public abstract int getSkillBonus();

    public abstract int getLuckBonus();
}
