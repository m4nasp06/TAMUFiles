import java.util.ArrayList;

public class EmployeeDirectory {
    private String companyName;
    private ArrayList<Employee> employees;

    public EmployeeDirectory(String companyName) {
        this.companyName = companyName;
        this.employees = new ArrayList<Employee>();
    }

    public void addEmployee(Employee e) {
        employees.add(e);
    }

    public String toString() {
        String result = "Company: " + companyName + "\nEmployees:";
        for (int i = 0; i < employees.size(); i++) {
            if (i == 0) {
                result += "\n" + employees.get(i).toString();
            } else {
                result += "\n\n" + employees.get(i).toString();
            }
        }
        return result;
    }
}