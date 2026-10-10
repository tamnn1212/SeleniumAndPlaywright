package javaKeyword;

public abstract class Computer {
    // Biến - variable / properties
    //1. - Bien non-static
    //2. - bien static
    //3. - Bien final
    //4. - Bien toan cuc
    //5. - Bien cuc bo

    // Access modify - Pham vi truy cap
    // private : chi co class/ ham hien tai moi dc su dung
    private String ramName = "Kingston";
    private void touchPC() {}
    // default: truy cap trong cung package
    String ssd = "evo";
    // protected: truy cap ngoai package = cach ke thua hoac truy cap cung package
    protected String mainName = "8G";
    // public : truy cap tat ca pham vi
    public String lcdName  = "";
    // Bien non-static: phai su dung qua instance
    // Bien static: truy cap truc tiep tu ten lop k can qua doi tuong
    // Chia se du lieu cho  tat ca thread
    static String cityName = " ";
    //3. - Bien final
    // Su dung khi khong muon gan lai du lieu
    final String address = " ";
    // 4. - Bien toan cuc
    // Chi xet trong pham vi class: duoc khai bao trong lop
    // 5. - Bien cuc bo
    // Khi bao trong func, khoi lenh
    public void printRamName (String ramName) {
        String hddName = "5";
        System.out.println(ramName);
        for (int i = 0 ;i<10;i++){
            System.out.println(i);
            System.out.println(ramName);
            System.out.println(hddName);
        }
    }
    // ham static
    public void printYourName(String name){
        System.out.println(name);
    }
    // ham non-static
    public static void printYourAgg() {

    }
    // ham final - khong cho phep override
    public final int printPi() {
        return (int) 3.14;
    }
    // ham abstract - Bat buoc phai viet lai
    protected abstract void printInfo(String name);
    public  void showLcdName() {
    }

}
