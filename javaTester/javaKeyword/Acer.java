public class Acer extends Computer implements Person,Room{
    // Ko the ghi de ham final
    public void printAddress() {
    }

    public void printPhoneNumber(){

    }

    @Override
    public void showName() {

    }

    @Override
    public void showAgg() {

    }

    @Override
    public void showFirstName() {
        Person.super.showFirstName();
    }
}
