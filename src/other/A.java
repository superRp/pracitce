package other;

public class A {
    D d;
    static {
        System.out.println("A 1 中静态代码块");
        System.out.println("aa");
    }
    {
        System.out.println("A 2 中代码块");
        d=new D();
    }

    public A() {
        System.out.println("A 3 的构造函数");
    }
}
