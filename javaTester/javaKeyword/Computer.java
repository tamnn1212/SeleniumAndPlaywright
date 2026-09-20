public class Computer {
        // bien =  variable
                 // bien non-static, static, final, toan cuc, cuc bo
            // Pham vi truy cap: public, protected, default , private
        private String ramName = "Kingston 8GB";

        String ssd = "VVO"; // default co the truy cap trong package
        protected  String mainName = ""; // co the truy cap qua ke thua

        public String lcdName = "samsung";

        static String ram = "8GB";

        final int cauhinh = 36;

        // bien static
                //truy cap truc tiep k qua ins
        // 2. Bien toan cuc va bien cuc bo
        // Cho xet trong class
        // Bien toan cuc: Khai bao o class
        // Bien cuc bo: Khai bao trong ham / tham so trong ham/ Khoi lenh. Va chi dung trong pham vi do

        // ham - method/ function
            //non-static, static, abstract

        // ham non-static
        public void printRam(String ramName) {

        }
        // ham static
        public static void printLCD() {

        }
        // ham final // ham final k the override
        //  Tinh abstraction  (truu tuong)
        final void printAddress() {

        }
        // ham abstract // bat buoc cac lop con phai viet lai
        //      Chi co ten ham, tham so, kieu du lieu tra ve; ko co logic code
        public abstract void  printPhoneNumber();
        //  Tinh abstraction  (truu tuong)

        // lop  - class
            // 1. final class khong cho phep ke thua
            // 2. abstract class
                // Chua ca abstract method va non-abstract method
           // 3, non-abstract class
                //Chi chua non-abstract method va non-abstract method

        // Interface
        // Chuc nang giong abstract class
        // Ko chua non-abstract method
        // Cho phep da  ke thua
}
