import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {

        StudentService studentService = new StudentService();
        studentService.insertData();

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("\n\n!---Student Management System---!");
            System.out.println("0. Add Student");
            System.out.println("1. Find Student");
            System.out.println("2. Student Ranks");
            System.out.println("3. Student Result");
            System.out.println("4. Delete Student");
            System.out.println("5. List of Students");
            System.out.println("6. Pagination");
            System.out.println("7. File Services");
            System.out.println("8. Exit");
            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                    System.out.print("Enter Student Name :");
                    String name = sc.next();
                    System.out.print("Enter ClassID :");
                    int class_id =  sc.nextInt();
                    System.out.print("Enter Student Marks :");
                    int marks = sc.nextInt();
                    System.out.print("Enter Student Gender :");
                    String gender =  sc.next();
                    System.out.print("Enter Student Age :");
                    int age =  sc.nextInt();
                    studentService.addStudent(new Student(name, class_id, marks, gender, age));
                    break;
                case 1:
                    System.out.println("\n--- Find Student Sub-Menu ---");
                    System.out.println("1. Find by City");
                    System.out.println("2. Find by Pincode");
                    System.out.println("3. Find by Class");
                    System.out.println("0. Exit");
                    System.out.print(" Enter your choice: ");
                    int subChoice = sc.nextInt();

                    switch (subChoice) { // Inner Switch
                        case 0:
                            return;
                        case 1:
                            System.out.println("Finding by City...");
                            System.out.print("Enter City :");
                            studentService.findByCity(sc.next());
                            break;
                        case 2:
                            System.out.println("Finding by Pincode..");
                            System.out.print("Enter PinCode :");
                            studentService.findByPin(sc.nextInt());
                            break;
                        case 3:
                            System.out.println("Finding by Class...");
                            System.out.print("Enter Class Name :");
                            studentService.findByClass(sc.next());
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                    break;

                case 2:
                    studentService.rankStudents();
                    break;

                case 3:
                    System.out.print("Enter Student Name : ");
                    studentService.getResult(sc.next());
                    return;

                case 4:
                    System.out.print("Enter Student ID : ");
                    studentService.deleteStudent(sc.nextInt());
                    break;

                case 5:
                    studentService.allStudents();
                    break;

                case 6:
                    System.out.print("Enter Gender (M/F): ");
                    String gen = sc.next();
                    System.out.print("Select Page Range From: ");
                    int start = sc.nextInt();
                    System.out.print("To: ");
                    int end = sc.nextInt();

                    System.out.println("\n--- Select Required Pagination ---");
                    System.out.println("1. Normal Pagination");
                    System.out.println("2. Pagination by Names");
                    System.out.println("3. Pagination by Marks");
                    System.out.println("0. Exit");
                    System.out.print("Enter your choice: ");
                    int page = sc.nextInt();

                    switch (page) {
                        case 0:
                            break;
                        case 1:
                            studentService.paginate(start, end, gen);
                            break;
                        case 2:
                            studentService.paginateByName(start, end, gen);
                            break;
                        case 3:
                            studentService.paginateByMarks(start, end, gen);
                            break;
                        default:
                            System.out.println("Invalid pagination choice!");
                            break;
                    }
                    break;
                case 7:
                    System.out.println("      Select Option : ");
                    System.out.println("      1. Insert Data into File ");
                    System.out.println("      2. Read Data From File ");
                    System.out.println("      3. Store Ranks of Students");
                    System.out.print("      4. Fetch Ranks of Students :  \n        ");
                    int x = sc.nextInt();
                    studentService.fileService(x);
                   break;
                case 8:
                    System.out.println(" Exiting...");
                    return;

                default:
                    System.out.println("Invalid main choice!");
                    break;
            }
        }
    }
}