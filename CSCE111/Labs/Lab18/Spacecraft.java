public class Spacecraft {
    // attributes
    public final String callsign;
    private double maxFuel;
    private double currentFuel;
    private double hullIntegrity;

    // constructor
    public Spacecraft(String callsign, double maxFuel) {
        this.callsign = callsign;
        this.maxFuel = maxFuel;
        currentFuel = maxFuel;
        hullIntegrity = 100.0;
    }

    // methods
    public double getCurrentFuel() {
        return currentFuel;
    }

    public double getHullIntegrity() {
        return hullIntegrity;
    }

    public void takeDamage(double amount) {
        hullIntegrity -= amount;

        if (hullIntegrity < 0.0) {
            hullIntegrity = 0.0;
        }

        System.out.println(callsign + " took " + amount + " damage. Hull integrity is now " + hullIntegrity + "%.");
    }

    public void refuel(double amount) {
        currentFuel += amount;
        
        if (currentFuel > maxFuel) {
            currentFuel = maxFuel;
        }

        System.out.println(callsign + " refueled. Current fuel is now " + currentFuel + ".");
    }

    public boolean burnEngine(double amount) {
        if (currentFuel >= amount) {
            currentFuel -= amount;
            System.out.println(callsign + " burned " + amount + " fuel.");
            return true;
        }

        else {
            System.out.println("ERROR: Insufficient fuel on " + callsign + "!");
            return false;
        }
    }
}
