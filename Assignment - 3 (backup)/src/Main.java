import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

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
            System.out.println("7. Exit");
            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                    String name = getValidStringInput(sc, "Enter Student Name: ");
                    int class_id = getValidIntInput(sc, "Enter ClassID: ", 1, 4);
                    int marks = getValidIntInput(sc, "Enter Student Marks (0-100): ", 0, 100);
                    String gender = getValidStringInput(sc, "Enter Student Gender (M/F): ");
                    int age = getValidIntInput(sc, "Enter Student Age: ", 1, 120);
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
                            String city = getValidStringInput(sc, "Enter City : ");
                            studentService.findByCity(city);
                            break;
                        case 2:
                            System.out.println("Finding by Pincode..");
                            int pin = getValidIntInput(sc, "Enter PinCode : ", 000000, 999999);
                            studentService.findByPin(pin);
                            break;
                        case 3:
                            System.out.println("Finding by Class...");
                            studentService.findByClass(getValidIntInput(sc, "Enter Class ID : ", 1, 4));
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
                    studentService.getResult(getValidStringInput(sc, "Enter Student Name: "));
                    return;

                case 4:
                    studentService.deleteStudent(getValidIntInput(sc, "Enter Student ID: ", 1, Integer.MAX_VALUE));
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
                    System.out.println(" Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    private static int getValidIntInput(Scanner sc, String message, int min, int max) {
        while (true) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                int value = sc.nextInt();
                if (value >= min && value <= max) return value;
                else System.out.println("⚠️ Please enter a value between " + min + " and " + max + ".");
            } else {
                System.out.println("⚠️ Invalid input! Please enter a number.");
                sc.next(); // clear wrong input
            }
        }
    }

    // ✅ Validation for non-empty string input
    private static String getValidStringInput(Scanner sc, String message) {
        String input;
        do {
            System.out.print(message);
            input = sc.next();
            if (input.trim().isEmpty())
                System.out.println("⚠️ Input cannot be empty.");
        } while (input.trim().isEmpty());
        return input;
    }
}
