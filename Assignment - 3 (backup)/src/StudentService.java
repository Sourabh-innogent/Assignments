import java.util.*;
        
public class StudentService {
    List<Student> students = new ArrayList<>();
    List<ClassDetail> classes = new ArrayList<>();
    Set<Address> addresses = new HashSet<>();
    // insert data
    public void insertData() {

        Address indore = new Address(452002, "indore");
        Address delhi  = new Address( 422003, "delhi");
        Address dewas  = new Address(442004, "dewas");
        addresses.add(indore);
        addresses.add(delhi);
        addresses.add(dewas);

        classes.add(new ClassDetail(1, "A"));
        classes.add(new ClassDetail(2, "B"));
        classes.add(new ClassDetail(3, "C"));
        classes.add(new ClassDetail(4, "D"));

        addStudent("Srishti", 1, 88, "F", 10,indore);
        addStudent( "Kajal", 1, 70, "F", 11,dewas);
        addStudent( "Rajneesh", 2, 88, "M", 22,delhi);
        addStudent("Sourabh", 2, 55, "M", 13,indore);
        addStudent("kriti", 1, 30, "F", 18,dewas);
        addStudent("Ritik", 3, 30, "M", 15,delhi);
        addStudent("Naina", 1, 30, "F", 1,dewas);
        addStudent( "Aman", 4, 55, "M", 13,indore);
    }

    public void addStudent(String name,int class_id,int marks,String gender,int age,Address address) {
        try {
            if (name == null || name.trim().isEmpty()) {
                System.out.println("Student not added: Name cannot be empty");
                return;
            }
            if (gender == null || (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F"))) {
                System.out.println("Student " + name + " not added: Invalid gender (use M/F)");
                return;
            }
            if (address == null) {
                System.out.println("Student " + name + " not added: Address cannot be null");
                return;
            }
            if (age > 20 || age < 1) {
                System.out.println("Student " + name + " not added: Age must be between 1-20");
                return;
            }
            if (class_id > 4 || class_id < 1) {
                System.out.println("Student " + name + " not added: Invalid class ID (1-4)");
                return;
            }
            if (marks < 0 || marks > 100) {
                System.out.println("Student " + name + " not added: Marks must be between 0-100");
                return;
            }
            Student student = new Student(name, class_id, marks, gender, age, address);
            students.add(student);
            addresses.add(address);
            System.out.println("Student details added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    public void getResult(String name) {
        try {
            if (name == null || name.trim().isEmpty()) {
                System.out.println("Name cannot be empty");
                return;
            }
            if (students.isEmpty()) {
                System.out.println("No students available");
                return;
            }
            for (Student s : students) {
                if (s != null && s.name != null && s.name.equalsIgnoreCase(name)) {
                    if (s.marks >= 50) {
                        System.out.println(name + " is PASS with marks: " + s.marks);
                    } else {
                        System.out.println(name + " is FAIL with marks: " + s.marks);
                    }
                    return;
                }
            }
            System.out.println(name + "'s data is not available");
        } catch (Exception e) {
            System.out.println("Error retrieving result: " + e.getMessage());
        }
    }

    public void rankStudents() {
        try {
            if (students.isEmpty()) {
                System.out.println("No students available for ranking");
                return;
            }
            students.sort((s1, s2) -> s2.marks - s1.marks);
            int rank = 1;
            for (Student s : students) {
                if (s != null && s.name != null) {
                    System.out.println("Name: " + s.name + " Marks: " + s.marks + " → Rank: " + rank++);
                }
            }
        } catch (Exception e) {
            System.out.println("Error ranking students: " + e.getMessage());
        }
    }

    public void findByCity(String city) {
        try {
            if (city == null || city.trim().isEmpty()) {
                System.out.println("City name cannot be empty");
                return;
            }
            if (students.isEmpty()) {
                System.out.println("No students available");
                return;
            }
            boolean found = false;
            for (Student s : students) {
                if (s != null && s.address != null && s.address.city != null && s.address.city.equalsIgnoreCase(city)) {
                    System.out.print(s + " || ");
                    System.out.println(s.address);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No students found living in the city: " + city);
            }
        } catch (Exception e) {
            System.out.println("Error searching by city: " + e.getMessage());
        }
    }

    public void findByPin(int pin) {
        try {
            if (pin < 100000 || pin > 999999) {
                System.out.println("Invalid pincode. Must be 6 digits");
                return;
            }
            if (students.isEmpty()) {
                System.out.println("No students available");
                return;
            }
            boolean found = false;
            for (Student s : students) {
                if (s != null && s.address != null && s.address.pincode == pin) {
                    System.out.print(s + " || ");
                    System.out.println(s.address);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No students found with PinCode: " + pin);
            }
        } catch (Exception e) {
            System.out.println("Error searching by pincode: " + e.getMessage());
        }
    }

    public void findByClass(int classId) {
        try {
            if (classId < 1 || classId > 4) {
                System.out.println("Invalid class ID. Must be between 1-4");
                return;
            }
            if (students.isEmpty()) {
                System.out.println("No students available");
                return;
            }
            boolean found = false;
            for (ClassDetail c : classes) {
                if (c != null && c.id == classId) {
                    for (Student s : students) {
                        if (s != null && s.class_id == classId) {
                            System.out.print(s + " || ");
                            System.out.println(c);
                            found = true;
                        }
                    }
                }
            }
            if (!found) {
                System.out.println("No students found for Class ID: " + classId);
            }
        } catch (Exception e) {
            System.out.println("Error searching by class: " + e.getMessage());
        }
    }


    public void getPassed() {
        try {
            if (students.isEmpty()) {
                System.out.println("No students available");
                return;
            }
            boolean found = false;
            for (Student s : students) {
                if (s != null && s.marks >= 50) {
                    System.out.println(s.name + " → Passed");
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No passed students found");
            }
        } catch (Exception e) {
            System.out.println("Error getting passed students: " + e.getMessage());
        }
    }

    public void getFailed() {
        try {
            if (students.isEmpty()) {
                System.out.println("No students available");
                return;
            }
            boolean found = false;
            for (Student s : students) {
                if (s != null && s.marks < 50) {
                    System.out.println(s.name + " → Failed");
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No failed students found");
            }
        } catch (Exception e) {
            System.out.println("Error getting failed students: " + e.getMessage());
        }
    }

    public void deleteStudent(int studentId) {
        boolean removed = students.removeIf(s -> s.id == studentId);

        if (removed) {
            Set<Integer> remaining = new HashSet<>();
            for (Student s : students) {
                remaining.add(s.class_id);
            }
            classes.removeIf(c -> !remaining.contains(c.id));

            System.out.println(" Student with ID " + studentId + " and their details deleted successfully!");
        }
        else {
            System.out.println("Student with ID " + studentId + " not found!");
        }
    }

    // Normal pagination with gender filter
    public void paginate(int start, int end, String gender) {
        try {
            if (students.isEmpty()) {
                System.out.println("No students available");
                return;
            }
            if (start < 1 || end < start) {
                System.out.println("Invalid range. Start must be >= 1 and end >= start");
                return;
            }
            if (gender == null || (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F"))) {
                System.out.println("Invalid gender. Use M or F");
                return;
            }
            boolean found = false;
            for (int i = start - 1; i < end && i < students.size(); i++) {
                Student s = students.get(i);
                if (s != null && s.gender != null && gender.equalsIgnoreCase(s.gender)) {
                    System.out.println(s);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No students found with gender: " + gender);
            }
        } catch (Exception e) {
            System.out.println("Error in pagination: " + e.getMessage());
        }
    }

    // Pagination by name
    public void paginateByName(int start, int end, String gender) {
        try {
            if (students.isEmpty()) {
                System.out.println("No students available");
                return;
            }
            if (start < 1 || end < start) {
                System.out.println("Invalid range. Start must be >= 1 and end >= start");
                return;
            }
            if (gender == null || (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F"))) {
                System.out.println("Invalid gender. Use M or F");
                return;
            }
            List<Student> data = new ArrayList<>();
            for (Student s : students) {
                if (s != null && s.gender != null && gender.equalsIgnoreCase(s.gender)) {
                    data.add(s);
                }
            }
            if (data.isEmpty()) {
                System.out.println("No students found with gender: " + gender);
                return;
            }
            data.sort(Comparator.comparing(s -> s.name, String.CASE_INSENSITIVE_ORDER));
            for (int i = start - 1; i < end && i < data.size(); i++) {
                System.out.println(data.get(i));
            }
        } catch (Exception e) {
            System.out.println("Error in name pagination: " + e.getMessage());
        }
    }

    // Pagination by marks
    public void paginateByMarks(int start, int end, String gender) {
        try {
            if (students.isEmpty()) {
                System.out.println("No students available");
                return;
            }
            if (start < 1 || end < start) {
                System.out.println("Invalid range. Start must be >= 1 and end >= start");
                return;
            }
            if (gender == null || (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F"))) {
                System.out.println("Invalid gender. Use M or F");
                return;
            }
            List<Student> data = new ArrayList<>();
            for (Student s : students) {
                if (s != null && s.gender != null && gender.equalsIgnoreCase(s.gender)) {
                    data.add(s);
                }
            }
            if (data.isEmpty()) {
                System.out.println("No students found with gender: " + gender);
                return;
            }
            data.sort((s1, s2) -> s1.marks - s2.marks);
            for (int i = start - 1; i < end && i < data.size(); i++) {
                System.out.println(data.get(i));
            }
        } catch (Exception e) {
            System.out.println("Error in marks pagination: " + e.getMessage());
        }
    }


    public void allStudents() {
        try {
            if (students.isEmpty()) {
                System.out.println("No students available");
                return;
            }
            for (Student s : students) {
                if (s != null) {
                    System.out.println(s);
                }
            }
        } catch (Exception e) {
            System.out.println("Error displaying students: " + e.getMessage());
        }
    }
}