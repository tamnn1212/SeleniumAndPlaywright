public class Topic_05_Conditions {
    public static void main(String[] args) {
    // Bieu thuc dieu kien
        String fullName = "nguyen van linh";
        // if - chi co 1 dk
        if (fullName.equals("nguyen van linh")) {
            System.out.printf("Success!");
        }
        // if -- else
        String browerName = "Chrome";
        if (browerName.equals("Chrome")){
            System.out.printf("Init Chorome");
        } else {
            System.out.printf("Edge");
        }
        // if else .. if else
        float point = 8.5f;
        if (point >= 8) {
            System.out.printf("A");
        } else if (point < 8) {
            System.out.printf("B");
        } else if (point < 6) {
            System.out.printf("C");
        } else if (point<4){
            System.out.printf("F");
        }
        // switch case
        String enviromentName = "QA";
        String url = null;
        switch (enviromentName) {
            case "Local":
                url = "https//localhost"; break;
            case "DEV":
                url = "https//dev"; break;
            case "QA":
                url = "https//test  "; break;
            default:
                url = "https//dev  "; break;
        }
        System.out.printf(url);
    }
}
