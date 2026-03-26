import java.util.ArrayList;

public class LW15 {

    public static Airline airline_creator(String name) {
        ArrayList<Airplane> planes = new ArrayList<Airplane>(3);
        planes.add(new Airplane("Plane 1", "PLANE1", 0, 0, 0, 0, 0, 0, false));
        planes.add(new Airplane("Plane 2", "PLANE2", 0, 0, 0, 0, 0, 0, false));
        planes.add(new Airplane("Plane 3", "PLANE3", 0, 0, 0, 0, 0, 0, false));
        return new Airline(name, planes, 0);
    }

    public static void printAirline(Airline a) {
        System.out.println(a.name);
        System.out.println();
        System.out.println("Number of Employees: " + a.num_employees);
        System.out.println();
        System.out.println("Planes:");
        for (int i = 0; i < a.planes.size(); i++) {
            System.out.println(
                "Plane " + (i + 1) + ": " + a.planes.get(i).tail_number
            );
        }
    }
}
