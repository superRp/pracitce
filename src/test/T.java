package test;

public class T implements Runnable{
        static int i=2;
        @Override
        public void run() {
            // TODO Auto-generated method stub
            //++i;
            System.out.println(Thread.currentThread().getId()+" i="+(++i));
        }
    
    
    public static void main(String[] args) throws InterruptedException {
       
        Thread t1=new Thread(new T());
        Thread t2=new Thread(new T());
        t2.start();
        
        t1.start();
        t2.join();
        t1.join();
    }

}
