package practice.calculator;

public class Test {
    public static void main(String[] args) throws InterruptedException {
       new Thread(new Plus()).start();
       new Thread(new Multiply()).start();
       for(int i=1;i<3;i++) {
           for(int j=1;j<3;j++) {
               Plus.bg.add(new Msg(i,j));
           }
       }
      

    }

}
