import java.util.ArrayList;
public class Fleet {
    // attributes
    private String fleetName;
    private ArrayList<Freighter> freighters;
    private ArrayList<Fighter> fighters;

    // constructor
    Fleet(String name) {
        fleetName = name;
        freighters = new ArrayList<Freighter>();
        fighters = new ArrayList<Fighter>();
    }

    // methods
    public String getName() {
        return fleetName;
    }
    
    public void addFreighter(String callsign, double maxFuel, double maxCargo) {
        Freighter newFreighter = new Freighter(callsign, maxFuel, maxCargo);
        freighters.add(newFreighter);
        System.out.println("Freighter Added");
    }
    
    public void addFighter(String callsign, double maxFuel) {
        Fighter newFighter = new Fighter(callsign, maxFuel);
        fighters.add(newFighter);
        System.out.println("Fighter Added");
    }

    public void checkShipStatus(String callsign) {
        for (Freighter f : freighters) {
            if (f.callsign.equals(callsign)) {
                System.out.println("Callsign: " + f.callsign + " | " + "Fuel: " + f.getCurrentFuel() + " | " + "Hull: " + f.getHullIntegrity() );
            }
        }
        for (Fighter g : fighters) {
            if (g.callsign.equals(callsign)) {
                System.out.println("Callsign: " + g.callsign + " | " + "Fuel: " + g.getCurrentFuel() + " | " + "Hull: " + g.getHullIntegrity() );
            }
        }
    }
    
    public void loadCargo(String callsign, double weight) {
        for (Freighter x : freighters) {
            if (x.callsign.equals(callsign)) {
                x.loadCargo(weight);
            }
        }
    }

    public void toggleAfterburners(String callsign) {
        for (Fighter y : fighters) {
            if (y.callsign.equals(callsign)) {
                y.toggleAfterburners();
            }
        }
    }

    public void burnEngine(String callsign, double amount) {
        for (Freighter a : freighters) {
            if (a.callsign.equals(callsign)) {
                a.burnEngine(amount);
            }
        }
        for (Fighter b : fighters) {
            if (b.callsign.equals(callsign)) {
                b.burnEngine(amount);
            }
        }
    }

    public void refuelShip(String callsign, double amount) {
        for (Freighter z : freighters) {
            if (z.callsign.equals(callsign)) {
                z.refuel(amount);
            }
        }        
        for (Fighter w : fighters) {
            if (w.callsign.equals(callsign)) {
                w.refuel(amount);
            }
        }
    }

    public void attackShip(String callsign, double damage) {
        for (Freighter c : freighters) {
            if (c.callsign.equals(callsign)) {
                c.takeDamage(damage);
            }
        }
        for (Fighter d : fighters) {
            if (d.callsign.equals(callsign)) {
                d.takeDamage(damage);
            }
        }
    }
}
