import java.util.*;
        
public class StudentService {
    List<Student> students = new ArrayList<>();
    List<Address> addresses = new ArrayList<>();
    List<ClassDetail> classes = new ArrayList<>();

    // insert data
    public void insertData() {
        classes.add(new ClassDetail(1, "A"));
        classes.add(new ClassDetail(2, "B"));
        classes.add(new ClassDetail(3, "C"));
        classes.add(new ClassDetail(4, "D"));

        addStudent(new Student(1, "Srishti", 1, 88, "F", 10));
        addStudent(new Student(2, "Kajal", 1, 70, "F", 11));
        addStudent(new Student(3, "Rajneesh", 2, 88, "M", 22));
        addStudent(new Student(4, "Sourabh", 2, 55, "M", 13));
        addStudent(new Student(5, "kriti", 1, 30, "F", 18));
        addStudent(new Student(6, "Ritik", 3, 30, "M", 15));
        addStudent(new Student(7, "Naina", 1, 30, "F", 18));
        addStudent(new Student(8, "Aman", 4, 55, "M", 13));

        addresses.add(new Address(1, 452002, "indore", 1));
        addresses.add(new Address(2, 422002, "delhi", 1));
        addresses.add(new Address(3, 442002, "indore", 2));
        addresses.add(new Address(4, 462002, "delhi", 3));
        addresses.add(new Address(5, 472002, "indore", 4));
        addresses.add(new Address(6, 452002, "indore", 5));
        addresses.add(new Address(7, 482002, "mumbai", 6));
    }

    public void addStudent(Student s) {
        for (Student st : students) {
            if (st.id == s.id) {
                System.out.println("Student ID already exists!");
                return;
            }
        }
        if (s.age > 20 ) {
            System.out.println("Student " + s.name + " not added (Age > 20 or invalid class ID)");
            s = null;
        }
        else if (s.class_id >=4  ) {
            System.out.println("Student " + s.name + " not added (Age > 20 or invalid class ID)");
            s = null;
        }
        else {
            students.add(s);
            System.out.println(" Student details added successfully!");
        }
    }

    public void getResult(String name) {
        for (Student s : students) {
            if(s.name.equalsIgnoreCase(name)) {
                if (s.marks > 50) {
                    System.out.println(name + " is PASS with marks :" + s.marks );
                } else {
                    System.out.println(name + " is Fail");
                }
            }
            else {
                System.out.println(name + "'s data is not available ");
            }
        }
    }

    public void rankStudents() {
        students.sort((s1, s2) -> s2.marks - s1.marks); // Descending order
        int rank = 1;
        for (Student s : students) {
            System.out.println("Name: " + s.name + " Marks: " + s.marks + " → Rank: " + rank++);
        }
    }

    public void findByCity(String city) {
        for (Address a : addresses) {
            if (a.city.equalsIgnoreCase(city)) {
                for (Student s : students) {
                    if (s.id == a.student_id) {
                        System.out.print(s + " || ");
                        System.out.println(a);
                    }
                    }
            }
        }
    }

    public void findByPin(int pin) {
        for (Address a : addresses) {
            if (a.pincode == pin) {
                for (Student s : students) {
                    if (s.id == a.student_id) {
                        System.out.print(s + " || ");
                        System.out.println(a);
                    }
                }
            }
        }
    }

    public void findByClass(String found_class) {
        for (ClassDetail c : classes) {
            if (c.class_name.equals(found_class)) {
                for (Student s : students) {
                    if (s.class_id == c.id) {
                        System.out.print(s + " || ");
                        System.out.println(c);
                    }
                }
            }
        }
    }

    public void getPassed() {
        for (Student s : students) {
            if (s.marks >= 50) {
                System.out.println(s.name + " → Passed");
            }
        }
    }

    public void getFailed() {
        for (Student s : students) {
            if (s.marks < 50) {
                System.out.println(s.name + " → Failed");
            }
        }
    }

    public void deleteStudent(int studentId) {
        boolean removed = students.removeIf(s -> s.id == studentId);

        if (removed) {
            addresses.removeIf(a -> a.student_id == studentId);

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
        for (int i = start - 1; i < end && i < students.size(); i++) {
            Student s = students.get(i);
            if (gender.equalsIgnoreCase(s.gender)) {
                System.out.println(s);
            }
        }
    }

    // Pagination by name
    public void paginateByName(int start, int end, String gender) {
        List<Student> data = new ArrayList<>();
        for (Student s : students) {
            if (gender.equalsIgnoreCase(s.gender)) {
                data.add(s);
            }
        }
        data.sort(Comparator.comparing(s -> s.name, String.CASE_INSENSITIVE_ORDER));
        for (int i = start - 1; i < end && i < data.size(); i++) {
            System.out.println(data.get(i));
        }
    }

    // Pagination by marks
    public void paginateByMarks(int start, int end, String gender) {
        List<Student> data = new ArrayList<>();
        for (Student s : students) {
            if (gender.equalsIgnoreCase(s.gender)) {
                data.add(s);
            }
        }
        data.sort((s1, s2) -> s1.marks - s2.marks);
        for (int i = start - 1; i < end && i < data.size(); i++) {
            System.out.println(data.get(i));
        }
    }


    public void allStudents()
        {
            for (Student s : students) {
                System.out.println(s);
            }
        }
}