package User;

public class User {
    // Biến toàn cục
    public String fullName;
    public int age;

    public User(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }
    public void print() {
        System.out.println("Name:" + fullName);
        System.out.println("Age:" + age);
    }
     static void main(String[] args) {
        User user = new User("Bob", 25);
        user.print();
    }
}
