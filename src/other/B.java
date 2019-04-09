package other;

public class B extends A {
    static C c;
    static int number=1;
    static {
        System.out.println("B 1 的静态代码块");
    }
    {
        System.out.println("B 2 的代码块");
    }
    public B() {
        // TODO Auto-generated constructor stub
        System.out.println("B 3 的构造函数");
    }
}
