import java.util.ArrayList;
import java.util.Scanner;

// Helper Class
class PathWeapon {

    int position;
    Weapon weapon;

    PathWeapon(int position, Weapon weapon) {
        this.position = position;
        this.weapon = weapon;
    }
}

// =========================================
// GAME ENGINE
// =========================================

public class DuelGame {

    private Player p1;
    private Player p2;
    private int round = 0;
    private int distance = 100;
    private boolean gameOver = false;
    private Scanner scanner;

    private ArrayList<PathWeapon> p1PathWeapons;
    private ArrayList<PathWeapon> p2PathWeapons;

    private final double[] moveSteps = { 0, 20, 15, 10, 2, 1.5 };
    private final int UPGRADE_COST = 50;

    public DuelGame() {
        this.scanner = new Scanner(System.in);
        this.p1PathWeapons = new ArrayList<>();
        this.p2PathWeapons = new ArrayList<>();
    }

    public void start() {
        System.out.println("==========================================");
        System.out.println("         DUEL");
        System.out.println("==========================================");

        boolean keepPlaying = true;
        while (keepPlaying) {
            setupGame();
            gameLoop();

            System.out.print("\nPlay again? (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            keepPlaying = input.equals("y") || input.equals("yes");
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }

    private void setupGame() {
        System.out.println("\n--- Select Class for Player 1 ---");
        Player[] types1 = {
            new Soldier(),
            new Hunter(),
            new Farmer(),
            new Sorcerer(),
        };
        for (int i = 0; i < types1.length; i++) {
            System.out.printf(
                "%d. %-8s [Skill: %+2d, Luck: %+2d]\n",
                (i + 1),
                types1[i].getPlayerType(),
                types1[i].getSkillBonus(),
                types1[i].getLuckBonus()
            );
        }

        int choice = getIntInput(1, 4);
        p1 = types1[choice - 1];
        p1.setName("Player 1");
        p1.setAvatar("🧙");

        Player[] types2 = {
            new Soldier(),
            new Hunter(),
            new Farmer(),
            new Sorcerer(),
        };
        p2 = types2[(int) (Math.random() * 4)];
        p2.setName("Player 2");
        p2.setAvatar("🧛");
        resetGameState();
    }

    private void resetGameState() {
        p1.setPosition(0);
        p2.setPosition(100);
        p1.setHealth(100);
        p2.setHealth(100);
        p1.setGold(100);
        p2.setGold(100);
        distance = 100;
        round = 0;
        gameOver = false;
        p1PathWeapons.clear();
        p2PathWeapons.clear();
        p1.getInventory().clear();
        p2.getInventory().clear();

        p1.addWeapon(new Bow());
        p1.equipWeapon(0);
        p2.addWeapon(new Bow());
        p2.equipWeapon(0);

        placeRandomWeapons(1, 49, p1PathWeapons);
        placeRandomWeapons(51, 99, p2PathWeapons);
    }

    private int getIntInput(int min, int max) {
        int choice = -1;
        while (choice < min || choice > max) {
            System.out.print("Enter number (" + min + "-" + max + "): ");
            try {
                String input = scanner.nextLine();
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                choice = -1;
            }
        }
        return choice;
    }

    private void placeRandomWeapons(
        int min,
        int max,
        ArrayList<PathWeapon> weaponList
    ) {
        ArrayList<Integer> positions = new ArrayList<>();
        while (positions.size() < 4) {
            int pos = (int) (Math.random() * (max - min + 1)) + min;
            boolean exists = false;
            for (int i = 0; i < positions.size(); i++) {
                if (positions.get(i) == pos) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                positions.add(pos);
            }
        }

        ArrayList<Weapon> weaponsToPlace = new ArrayList<>();
        weaponsToPlace.add(new Gun());
        weaponsToPlace.add(new Sword());
        weaponsToPlace.add(new Dagger());
        weaponsToPlace.add(new Red());

        for (int i = 0; i < 4; i++) {
            int posIndex = (int) (Math.random() * positions.size());
            int pos = positions.remove(posIndex);
            weaponList.add(new PathWeapon(pos, weaponsToPlace.get(i)));
        }
    }

    private void gameLoop() {
        System.out.println("\nThe duel begins!");
        System.out.println(p1.getName() + " is a " + p1.getPlayerType());
        System.out.println(p2.getName() + " is a " + p2.getPlayerType());
        printStatus();

        while (!gameOver) {
            System.out.println("\nPress ENTER for next round...");
            scanner.nextLine();

            nextRound();

            if (round >= moveSteps.length && !gameOver) {
                endGameDraw();
            }
        }
    }

    private void nextRound() {
        if (round >= moveSteps.length) return;

        int oldP1Pos = p1.getPosition();
        int oldP2Pos = p2.getPosition();

        if (moveSteps[round] > 0) {
            int moveAmt = (int) moveSteps[round];
            p1.setPosition(p1.getPosition() + moveAmt);
            p2.setPosition(p2.getPosition() - moveAmt);
            distance = p2.getPosition() - p1.getPosition();
            System.out.println(
                ">>> Players move " +
                    moveAmt +
                    "ft closer. Current distance: " +
                    distance +
                    "ft"
            );
        }

        checkPathForWeapons(p1, oldP1Pos, p1.getPosition(), p1PathWeapons);
        checkPathForWeapons(p2, p2.getPosition(), oldP2Pos, p2PathWeapons);

        chooseStrategies();

        System.out.println(
            "\n--- Round " + (round + 1) + " Combat (" + distance + "ft) ---"
        );
        resolveCombat();

        // Award survival gold if the round didn't end the game
        if (p1.isAlive()) {
            p1.setGold(p1.getGold() + 25);
            System.out.println(
                ">>> " +
                    p1.getName() +
                    " survived the round and earned 25 Gold!"
            );
        }
        if (p2.isAlive()) {
            p2.setGold(p2.getGold() + 25);
            System.out.println(
                ">>> " +
                    p2.getName() +
                    " survived the round and earned 25 Gold!"
            );
        }

        round++;

        if (!p1.isAlive() || !p2.isAlive()) {
            endGame();
        } else {
            printStatus();
        }
    }

    private void checkPathForWeapons(
        Player p,
        int startPos,
        int endPos,
        ArrayList<PathWeapon> pathWeapons
    ) {
        int min = Math.min(startPos, endPos);
        int max = Math.max(startPos, endPos);

        ArrayList<PathWeapon> found = new ArrayList<>();
        for (int i = 0; i < pathWeapons.size(); i++) {
            PathWeapon pw = pathWeapons.get(i);
            if (pw.position >= min && pw.position <= max) {
                System.out.println(
                    ">>> " +
                        p.getAvatar() +
                        " " +
                        p.getName() +
                        " found a " +
                        pw.weapon.getIcon() +
                        " " +
                        pw.weapon.getName() +
                        " at position " +
                        pw.position +
                        "!"
                );
                p.addWeapon(pw.weapon);
                found.add(pw);
            }
        }
        for (int i = 0; i < found.size(); i++) {
            pathWeapons.remove(found.get(i));
        }
    }

    private void chooseStrategies() {
        // --- Player 1 Turn ---
        System.out.println(
            "\n[P1] Choose weapon (Dist: " +
                distance +
                "ft, Gold: " +
                p1.getGold() +
                "):"
        );
        ArrayList<Weapon> inv = p1.getInventory();
        for (int i = 0; i < inv.size(); i++) {
            System.out.printf(
                "%d. %s %s\n",
                (i + 1),
                inv.get(i).getIcon(),
                inv.get(i).getName()
            );
        }
        int choice = getIntInput(1, inv.size());
        p1.equipWeapon(choice - 1);

        // Offer Upgrade
        if (p1.getGold() >= UPGRADE_COST) {
            System.out.print(
                "Upgrade " +
                    p1.getCurrentWeapon().getName() +
                    " for " +
                    UPGRADE_COST +
                    " gold? (y/n): "
            );
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                p1.setGold(p1.getGold() - UPGRADE_COST);
                p1.getCurrentWeapon().upgrade();
                System.out.println(
                    "Weapon upgraded to Level " +
                        p1.getCurrentWeapon().getLevel() +
                        "!"
                );
            }
        }
        System.out.println("You equipped: " + p1.getCurrentWeapon().getName());

        // --- Player 2 Turn (AI) ---
        // AI picks best weapon based on average damage at current distance
        Weapon bestWeapon = p2.getInventory().get(0);
        int maxAvgDamage = -1;
        for (int i = 0; i < p2.getInventory().size(); i++) {
            Weapon w = p2.getInventory().get(i);
            int totalDmg = 0;
            // Simple simulation of 10 attacks to find average
            for (int j = 0; j < 10; j++) totalDmg += w.calculateDamage(
                distance,
                p2
            );
            int avg = totalDmg / 10;
            if (avg > maxAvgDamage) {
                maxAvgDamage = avg;
                bestWeapon = w;
            }
        }
        p2.equipWeapon(p2.getInventory().indexOf(bestWeapon));

        // AI Upgrade Logic: Always upgrade if it has enough gold and it's the best weapon
        if (p2.getGold() >= UPGRADE_COST) {
            p2.setGold(p2.getGold() - UPGRADE_COST);
            p2.getCurrentWeapon().upgrade();
            System.out.println(
                ">>> " +
                    p2.getName() +
                    " upgraded their " +
                    p2.getCurrentWeapon().getIcon() +
                    "!"
            );
        }
    }

    private void resolveCombat() {
        int dmg1 = p1.attack(distance);
        p2.takeDamage(dmg1);
        System.out.printf(
            "%s %s attacks with %s for [%d] damage!\n",
            p1.getAvatar(),
            p1.getName(),
            p1.getCurrentWeapon().getName(),
            dmg1
        );

        int dmg2 = p2.attack(distance);
        p1.takeDamage(dmg2);
        System.out.printf(
            "%s %s attacks with %s for [%d] damage!\n",
            p2.getAvatar(),
            p2.getName(),
            p2.getCurrentWeapon().getName(),
            dmg2
        );
    }

    private void endGame() {
        gameOver = true;
        System.out.println("\n==========================================");
        System.out.println("             DUEL FINISHED");
        System.out.println("==========================================");
        printStatus();

        if (!p1.isAlive() && !p2.isAlive()) {
            System.out.println("\nDOUBLE KO! It's a DRAW.");
        } else if (!p1.isAlive()) {
            System.out.println(
                "\n" +
                    p1.getName() +
                    " died! " +
                    p2.getAvatar() +
                    " " +
                    p2.getName() +
                    " WINS!"
            );
        } else {
            System.out.println(
                "\n" +
                    p2.getName() +
                    " died! " +
                    p1.getAvatar() +
                    " " +
                    p1.getName() +
                    " WINS!"
            );
        }
    }

    private void endGameDraw() {
        gameOver = true;
        System.out.println("\n==========================================");
        System.out.println("             TIME LIMIT REACHED");
        System.out.println("==========================================");
        printStatus();

        if (p1.getHealth() > p2.getHealth()) {
            System.out.println(
                "\n" + p1.getAvatar() + " " + p1.getName() + " WINS by health!"
            );
        } else if (p2.getHealth() > p1.getHealth()) {
            System.out.println(
                "\n" + p2.getAvatar() + " " + p2.getName() + " WINS by health!"
            );
        } else {
            System.out.println("\nIt's a perfect DRAW!");
        }
    }

    private void printStatus() {
        System.out.println("\nSTATUS:");

        String p1Wpn = "None";
        if (p1.getCurrentWeapon() != null) {
            p1Wpn =
                p1.getCurrentWeapon().getIcon() +
                " " +
                p1.getCurrentWeapon().getName();
        }

        String p2Wpn = "None";
        if (p2.getCurrentWeapon() != null) {
            p2Wpn =
                p2.getCurrentWeapon().getIcon() +
                " " +
                p2.getCurrentWeapon().getName();
        }

        System.out.printf(
            "%s %s [%s]: %3d HP | %3d G | Wpn: %s\n",
            p1.getAvatar(),
            p1.getName(),
            p1.getPlayerType(),
            p1.getHealth(),
            p1.getGold(),
            p1Wpn
        );
        System.out.printf(
            "%s %s [%s]: %3d HP | %3d G | Wpn: %s\n",
            p2.getAvatar(),
            p2.getName(),
            p2.getPlayerType(),
            p2.getHealth(),
            p2.getGold(),
            p2Wpn
        );
    }

    public static void main(String[] args) {
        new DuelGame().start();
    }
}
