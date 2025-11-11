public class Address {
    int id;
    int pincode;
    String city;
    static int countId = 1;

    public Address(int pincode, String city) {
        this.id = countId++;
        this.pincode = pincode;
        this.city = city;
    }

    public String toString(){
        return "pincode : "+ pincode+" || City : "+ city;
    }
}
