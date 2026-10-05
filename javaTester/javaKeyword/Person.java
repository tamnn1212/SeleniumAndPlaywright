public interface Person {
    // interface mac dinh là abstract method
    public void showName();
    public abstract void showCity();
    // Neu muon define body can khai bao la default
    public default void showCompany() {
    }
    // interface cho phep da ke thua
    // 1 class co the ke thua n interface
    // 1 class chi co the ke thua 1 class khac
}
