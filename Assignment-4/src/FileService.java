import java.nio.file.*;
import java.io.IOException;
import java.util.*;
public class FileService {

    public static <T> void saveData(List<T> list, String fileName, String header) {
        List<String> lines = new ArrayList<>();
        lines.add(header); // first line is header

        for (T item : list) {
            lines.add(item.toString());  // use toString() of object
        }

        try {
            Files.write(Paths.get(fileName), lines,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println(" Data saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving " + fileName + ": " + e.getMessage());
        }
    }

    public static void readData() {
        try {
            System.out.println("---- Students Data ----");
            List<String> studentlines = Files.readAllLines(Paths.get("students.csv"));
            for (String line : studentlines) {
                System.out.println(line);
            }
            System.out.println("\n\n");

            System.out.println("---- Classes Data ----");
            List<String> classlines = Files.readAllLines(Paths.get("classes.csv"));
            for (String line : classlines) {
                System.out.println(line);
            }

            System.out.println("\n\n");

            System.out.println("---- Addresses Data ----");
            List<String> addresslines = Files.readAllLines(Paths.get("addresses.csv"));
            for (String line : addresslines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading students.csv: " + e.getMessage());
        }
        System.out.println();
    }


    public static void saveStudentsRank(List<Student> students) {
        students.sort((s1, s2) -> s2.marks - s1.marks);

        List<String> lines = new ArrayList<>();
        lines.add("rank,id,name,marks,gender,age,class_id"); // header

        int rank = 1;
        for (Student s : students) {
            lines.add(rank + "," + s.id + "," + s.name + "," + s.marks + "," +
                    s.gender + "," + s.age + "," + s.class_id);
            rank++;
        }

        try {
            Files.write(Paths.get("students_rank.csv"), lines,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Students rank saved to students_rank.csv");
        } catch (IOException e) {
            System.out.println("Error saving ranks: " + e.getMessage());
        }
    }

    public static void fetchStudentsRank() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("students_rank.csv"));
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }


}

