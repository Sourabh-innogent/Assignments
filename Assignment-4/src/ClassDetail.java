public class ClassDetail {
    int id;
    String class_name;

    public ClassDetail(int id, String class_name) {
        if(id > 4)
        {
            throw new InvalidClassIDException("Class ID can't be Greater than 4");
        }
        this.id = id;
        this.class_name = class_name;
    }

    @Override
    public String toString() {
        return id + "," + class_name;
    }

}
