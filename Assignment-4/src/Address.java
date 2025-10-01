public class Address {
    int id;
    int pincode;
    String city;
    int student_id;
    public Address(int id, int pincode, String city, int student_id) {
        this.id = id;
        this.pincode = pincode;
        this.city = city;
        this.student_id = student_id;
    }

    @Override
    public String toString() {
        return id + "," + pincode + "," + city + "," + student_id;
    }
}
