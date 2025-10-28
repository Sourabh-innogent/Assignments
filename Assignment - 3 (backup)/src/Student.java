public class Student {
    protected   int id;
    protected   String name;
    protected   int class_id;
    protected   int marks;
    protected   String gender;
    protected   int age;

    public Student() {}
    public Student(int id, String name, int class_id, int marks, String gender, int age) {

            this.id = id;
            this.name = name;
            this.class_id = class_id;
            this.marks = marks;
            this.gender = gender;
            this.age = age;
    }

    @Override
    public String toString(){
         return "Student=> "+"id : "+id+" || name : "+name+" || Class_id : "+ class_id+" || marks : "+marks+" || Gender : "+gender+" || age : "+age ;
    }
}
