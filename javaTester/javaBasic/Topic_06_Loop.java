import java.util.ArrayList;
import java.util.List;

public class Topic_06_Loop {
    public static void main(String[] args) {
    // lap
    // for
    int employeeSize = 100;
        for (int i = 1; i <= employeeSize; i++) {
            System.out.printf("Nhan vien thu" + i);
        }
    // for each
        List<String> productName = new ArrayList<>();
        productName.add("Iphone 12");
        productName.add("Iphone 13");
        productName.add("Iphone 14");
        productName.add("Iphone 15");
        productName.add("Iphone 16");
        productName.add("Iphone 17");
        for (int i = 0; i < productName.size(); i++) {
            System.out.printf(productName.get(i));
        }
        for (String name:productName) {
            System.out.printf(name);
        }
        //while
        int x = 1;
        while (x<=employeeSize) {
            System.out.printf("Nhan vien thu" + x);
            x++;
        }
        // do while
        int y = 1;
        do {
            System.out.printf("Nhan vien thu" + y); y++;
        } while (y<=employeeSize);
    }
}
