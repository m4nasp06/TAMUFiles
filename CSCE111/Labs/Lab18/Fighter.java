public class Fighter extends Spacecraft {
    // attributes
    private boolean afterburnersActive;
    private int engineHeat;

    // constructor
    Fighter(String callsign, double maxFuel) {
        super(callsign, maxFuel);
        this.afterburnersActive = false;
        this.engineHeat = 0;
    }

    public void toggleAfterburners() {
        afterburnersActive = !afterburnersActive;
        if (afterburnersActive) System.out.println("Afterburners on " + callsign + " are now ON.");
        else System.out.println("Afterburners on " + callsign + " are now OFF.");
    }

    @Override
    public void takeDamage(double amount) {
        double actualDamage;
        if (afterburnersActive) {
            actualDamage = amount * 0.5;
            System.out.println("EVASION: " + callsign + " dodged 50% of the damage!");
        }
        else {
            actualDamage = amount * 1.5;
            System.out.println("VULNERABLE: " + callsign + " shields were down!");
        }
        
        super.takeDamage(actualDamage);
    }

    @Override
    public void refuel(double amount) {
        engineHeat = 0;
        System.out.println("COOLING: " + callsign + " engine heat completely flushed.");
        super.refuel(amount);        
    }
    
}