package User;

public class User_Login {
    String User_Name;
    String User_Password;

    public User_Login(String User_Name, String User_Password) {
        this.User_Name = User_Name;
        this.User_Password = User_Password;
    }

    public void login() {
        if (User_Name.equals("tam") && User_Password.equals("123456")) {
            System.out.println("Login Successful");
        } else  {
            System.out.println("Login Failed");
        }
    }
    static void main(String[] args) {
        User_Login obj = new User_Login("admin", "admin");
    }
}
