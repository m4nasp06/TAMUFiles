public class Freighter extends Spacecraft {
    // attributes
    private double maxCargoCapacity;
    private double currentCargoWeight;

    // constructor
    Freighter(String callsign, double maxFuel, double maxCargoCapacity) {
        super(callsign, maxFuel);
        this.maxCargoCapacity = maxCargoCapacity;
        currentCargoWeight = 0.0;
    }

    // methods
    public void loadCargo(double weight) {
        currentCargoWeight += weight;

        if (currentCargoWeight > maxCargoCapacity) {
            currentCargoWeight = maxCargoCapacity;
            System.out.println("WARNING: Cargo limit reached.");
        }
    }

        @Override
        public void takeDamage(double amount) {
            double actualDamage = amount - 10.0;
            if (actualDamage < 0) {
                actualDamage = 0;
            }
            System.out.println("ARMOR: " + callsign + " plating absorbed 10.0 damage!");
            super.takeDamage(actualDamage);
        }

        @Override
        public boolean burnEngine(double amount) {
            double penalty = currentCargoWeight * 0.05;
            double totalCost = amount + penalty;
            System.out.println("HEAVY BURN: Cargo mass penalty of " + penalty + " applied.");
            return super.burnEngine(totalCost);
        }
}
