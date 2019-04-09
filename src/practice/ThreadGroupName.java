package practice;

public class ThreadGroupName implements Runnable{
    public static void main(String[] args) {
        ThreadGroup tg=new ThreadGroup("PrintGroup");
        Thread t1=new Thread(tg, new ThreadGroupName(), "T1");
        Thread t2=new Thread(tg, new ThreadGroupName(), "T2");
        t1.start();
        t2.start();
        System.out.println("活跃数量"+tg.activeCount());
        System.out.println("线程信息");
        tg.list();
        
    }
    

    @Override
    public void run() {
        // TODO Auto-generated method stub
        String GroupAndName=Thread.currentThread().getThreadGroup().getName()+"-"+Thread.currentThread().getName();
        while(true) {
            System.out.println("I am"+GroupAndName);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
