import java.util.Comparator;
import java.util.*;

class Employee implements Comparable<Employee>
 {
    int id;
    String name;
    String department;
    double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }


    @Override
    public int compareTo(Employee other) {
        int deptCompare = this.department.compareTo(other.department);
        if (deptCompare != 0) return deptCompare;

        int nameCompare = this.name.compareTo(other.name);
        if (nameCompare != 0) return nameCompare;

        return Double.compare(this.salary, other.salary); // ascending
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + department + " | " + salary;
    }
}

class MyComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        return Double.compare(e2.getSalary(), e1.getSalary()); // descending
    }
}


public class EmployeeTest {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        // Sample employees
        employees.add(new Employee(101, "Sourabh", "HR", 50000));
        employees.add(new Employee(102, "Ritik", "IT", 70000));
        employees.add(new Employee(103, "Hariom", "Finance", 60000));
        employees.add(new Employee(104, "Ritik", "IT", 60000));
        employees.add(new Employee(105, "Krishna", "HR", 75000));
        employees.add(new Employee(106, "Arpit", "Finance", 60000));
        employees.add(new Employee(107, "Rajat", "IT", 70000));

        // Comparable (Department → Name → Salary)
        Collections.sort(employees);
        System.out.println("Department →Name → Salary (ascending) ==>");
        Iterator<Employee> it = employees.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        // Comparator (Salary descending)
        Collections.sort(employees, new MyComparator());
        System.out.println("\n Salary (descending) ==>");
        Iterator<Employee> it2 = employees.iterator();
        while (it2.hasNext()) {
            System.out.println(it2.next());
        }
    }
}


