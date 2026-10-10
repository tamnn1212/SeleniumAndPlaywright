package javaBasic;

import javaKeyword.Computer;

public class Topic_03_KeyWords extends Computer {
    Computer com1;
    public void showMainName() {
        // Doi voi lop ke thua co the su dung luon ma k can new lop
        System.out.println(mainName);
        showLcdName();
    }
    public static void main(String[] args) {

    }
    // Khong the overide ham final
//    public int printPi() {
//    }
    // abstract can phai ghi de
    public void printInfo(String name){
        System.out.println(name);
    }
}
