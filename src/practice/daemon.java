package practice;

public class daemon {
    public static void main(String[] args) {
        Daemon daemon=new Daemon();
        daemon.setDaemon(true);
        daemon.start();
        try {
            daemon.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static class Daemon extends Thread{
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while(true) {
                System.out.println("I am alive");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
