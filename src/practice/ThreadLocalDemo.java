package practice;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo {
    static ThreadLocal<SimpleDateFormat> tl=new ThreadLocal<>();
    public static class ParseDate implements Runnable{
        int i=0;
        public ParseDate(int i) {
            // TODO Auto-generated constructor stub
            this.i=i;
        }
        @Override
        public void run() {
            if(tl.get()==null) {
                tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            }
            try {
                Date t=tl.get().parse("2019-01-17 15:50:"+i % 60);
                System.out.println(i+" : "+t);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(5);
        for(int i=1;i<=10;i++) {
            es.execute(new ParseDate(i));
        }
        es.shutdown();
    }
}
