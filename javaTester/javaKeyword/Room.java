public interface Room {
    // interface mac dinh la abstract method
    public  void showName();

    public abstract void showAgg();

    public default void showFirstName(){

    }
}
