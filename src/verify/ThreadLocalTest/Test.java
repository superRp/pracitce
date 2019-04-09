package verify.ThreadLocalTest;

public class Test {
    //    ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    //    ThreadLocal<String> stringLocal = new ThreadLocal<String>();

    T<Long> longT=new T<Long>();
    T<String> stringT=new T<String>();


    public void set() {
        longT.setE(Thread.currentThread().getId());
        stringT.setE(Thread.currentThread().getName());
    }

    public long getLong() {
        return longT.getE();
    }

    public String getString() {
        return stringT.getE();
    }

    public static void main(String[] args) throws InterruptedException {
        final Test test = new Test();


        test.set();
        System.out.println(test.getLong());
        System.out.println(test.getString());


        Thread thread1 = new Thread(){
            public void run() {
                test.set();
                System.out.println(test.getLong());
                System.out.println(test.getString());
            };
        };
        thread1.start();
        thread1.join();

        System.out.println(test.getLong());
        System.out.println(test.getString());
    }
}

class T<E>{

    E e;

    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }


}