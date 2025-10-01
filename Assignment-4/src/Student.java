public class Student {
    protected   String name;
    protected   int class_id;
    protected   int marks;
    protected   String gender;
    protected   int age;
    protected   int id;
    public static int trackID = 0;

    public Student() {}
    public Student( String name, int class_id, int marks, String gender, int age) {
            trackID++;
            this.name = name;
            this.class_id = class_id;
            this.marks = marks;
            this.gender = gender;
            this.age = age;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + class_id + "," + marks + "," + gender + "," + age;
    }
}
