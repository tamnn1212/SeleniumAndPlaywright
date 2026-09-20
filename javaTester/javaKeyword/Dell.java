public class Dell {
    public static void main(String[] args) {
        // bien =  variable

                 // bien non-static, static, final, toan cuc, cuc bo
        Computer computer = new Computer(); // neu k phai ke thua can khoi tao
        computer.lcdName = "";
        // ham - method/ function
            //non-static, static, abstract
        // lop  - class

            // final class,
        // truy cap staic co the qua lop hoac instance
        Computer.ram = "16GB"; // uu tien thong qua lop
        computer.cauhinh = 48; // Khong the gan lai gia tri cho bien final
    }
}
