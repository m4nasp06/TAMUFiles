import java.util.ArrayList;

public class Airline {

    String name;
    ArrayList<Airplane> planes = new ArrayList<Airplane>();
    int num_employees;

    // default constructor + constructor with parameters
    public Airline(String name, ArrayList<Airplane> planes, int num_employees) {
        this.name = name;
        this.planes = planes;
        this.num_employees = num_employees;
    }

    public Airline() {}




}
