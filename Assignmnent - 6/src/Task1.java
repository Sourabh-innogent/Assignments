import java.util.ArrayList;

class Employee
{
    String name;
    double salary;
    String department;
    Employee(String name,double salary,String department)
    {
        this.name=name;
        this.salary=salary;
        this.department=department;
    }
    @Override
    public String toString()
    {
        return "Name: "+name+" || Salary: "+salary+" || Department: "+department;
    }
}
public class Task1 {
    public static void main(String[] args) {
        ArrayList<Employee> employees =new ArrayList<Employee>();
        employees.add(new Employee("Aman", 10000,"HR"));
        employees.add(new Employee("Sourabh", 7000,"IT"));
        employees.add(new Employee("Priya", 30000,"Finance"));
        employees.add(new Employee("Ritik", 40000,"HR"));

        //Filter Employees by Department
        employees.stream().filter(x -> x.department.equalsIgnoreCase("IT")).forEach(System.out::println);

        //Total salary of employees
        Double reduce = employees.stream()
                .map(e -> e.salary)
                .reduce(0.0, Double::sum);
        System.out.println(reduce);

        //Convert Employee name into UpperCase
        employees.stream().map(e-> e.name).map(String::toUpperCase).forEach(System.out::println);


    }
}
