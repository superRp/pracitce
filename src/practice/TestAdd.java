package practice;

public class TestAdd implements Runnable{
    static volatile int count=0;
    static TestAdd add=new TestAdd();

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for(int i=0;i<100000;i++) {
            synchronized (add) {
                count++;
            }
           
        }
    }
    public static void main(String[] args) {
        Thread t1=new Thread(add);
        Thread t2=new Thread(add);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
            System.out.println(TestAdd.count);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
    }

}
