package verify.design;



public class TestSingle {
    public static void main(String[] args) {
       Singleton instance = Singleton.getInstance();
       System.out.println(instance.toString());
       Singleton instance2=Singleton.getInstance();
       System.out.println(instance2.toString());
        
    }
}
