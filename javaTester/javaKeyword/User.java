public class User {
   String fullName,email;
   int age;
   //Constructor
    public User(String fullName, int age , String email) {
        this.fullName = fullName;
        this.age = age;
        this.email = email;
    }
    // Method
    public void displayInfo()
    {
        System.out.println(fullName);
        System.out.println(age);
        System.out.println(email);
    }
    // run
    public static void main(String[] args) {
        User user1 = new User("Tam",31,"tam@dt");
        user1.displayInfo();
    }
}
