public class Airplane {

    // If you choose to add a constructor with parameters, then add a constructor with no parameters as well otherwise the tests will fail.

    String model;
    String tail_number;
    double cruising_speed;
    int engine_count;
    double range;
    int passenger_capacity;
    double fuel_capacity;
    double current_fuel;
    boolean isFlying;

    // constructor
    public Airplane(
        String model,
        String tail_number,
        double cruising_speed,
        int engine_count,
        double range,
        int passenger_capacity,
        double fuel_capacity,
        double current_fuel,
        boolean isFlying
    ) {
        this.model = model;
        this.tail_number = tail_number;
        this.cruising_speed = cruising_speed;
        this.engine_count = engine_count;
        this.range = range;
        this.passenger_capacity = passenger_capacity;
        this.fuel_capacity = fuel_capacity;
        this.current_fuel = current_fuel;
        this.isFlying = isFlying;
    }

    // default constructor
    public Airplane() {}
}
