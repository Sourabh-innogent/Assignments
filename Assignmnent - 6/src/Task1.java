import java.util.*;
import java.util.Scanner;

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

        Scanner sc= new Scanner(System.in);
        ArrayList<Employee> employees =new ArrayList<Employee>();
        employees.add(new Employee("Aman", 10000,"HR"));
        employees.add(new Employee("Sourabh", 7000,"IT"));
        employees.add(new Employee("Priya", 30000,"Finance"));
        employees.add(new Employee("Ritik", 40000,"HR"));

        //Filter Employees by Department
        System.out.println("Enter Department :");
        String dep = sc.next();
        List<Employee> li = employees.stream().filter(x -> x.department.equalsIgnoreCase(dep)).toList();
        if(!li.isEmpty()){
            li.stream().forEach(System.out::println);
        }
        else{
            System.out.println("No Employee found with department : "+dep);
        }

        System.out.println("\nTotal salary of employees:");
        Double reduce = employees.stream()
                .map(e -> e.salary)
                .reduce(0.0, Double::sum);
        System.out.println(reduce);

        //Convert Employee name into UpperCase
        System.out.println("\nConvert Employee name into UpperCase:");
        employees.stream().map(e-> e.name).map(String::toUpperCase).forEach(System.out::println);


    }
}
