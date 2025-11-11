public class Student {
    private static int nextId = 1;
    protected   int id;
    protected   String name;
    protected   int class_id;
    protected   int marks;
    protected   String gender;
    protected   int age;
    protected Address address;

    public Student() {}
    public Student(String name, int class_id, int marks, String gender, int age, Address address) {

            this.id = nextId++;
            this.name = name;
            this.class_id = class_id;
            this.marks = marks;
            this.gender = gender;
            this.age = age;
            this.address = address;
    }

    @Override
    public String toString(){
         return "Student=> "+"id : "+id+" || name : "+name+" || Class_id : "+ class_id+" || marks : "+marks+" || Gender : "+gender+" || age : "+age ;
    }
}
