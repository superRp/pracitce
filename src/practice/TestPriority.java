package practice;

public class TestPriority {
    
    public static class highPriority extends Thread{
        static int count=0;
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while(true) {
                synchronized(TestPriority.class) {
                    count++;
                    if(count>10000) {
                        System.out.println("highPriority执行完成");
                        break;
                    }   
                }
            } 
        }   
    }
    
    public static class LowerPriority extends Thread{
       static int count=0;
       public void run() {
        // TODO Auto-generated method stub
           while(true) {
               synchronized(TestPriority.class) {
                   count++;
                   if(count>10000) {
                       System.out.println("LowerPriority执行完成");
                       break;
                   }
               }
           }
    }
    }
    public static void main(String[] args) {
        LowerPriority low=new LowerPriority();
        highPriority high=new highPriority();
        low.setPriority(Thread.MIN_PRIORITY);
        high.setPriority(Thread.MAX_PRIORITY);
        low.start();
        high.start();
    }

}
