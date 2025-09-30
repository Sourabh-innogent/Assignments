public class ClassDetail {
    int id;
    String class_name;

    public ClassDetail(int id, String class_name) {
        this.id = id;
        this.class_name = class_name;
    }

    public String toString() {
        return "Class : "+class_name;
    }

}
