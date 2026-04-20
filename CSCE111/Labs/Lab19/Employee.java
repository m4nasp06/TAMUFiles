public class Employee {
    private String name;
    private int id;
    private String department;
    private String title;

    public Employee(String name, int id, String department, String title) {
        this.name = name;
        this.id = id;
        this.department = department;
        this.title = title;
    }

    public String toString() {
        return String.format("%-11s: %s%n%-11s: %d%n%-11s: %s%n%-11s: %s",
            "Name", name,
            "ID", id,
            "Department", department,
            "Job Title", title);
    }
}