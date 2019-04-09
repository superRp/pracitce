package test;

public class Outer {

    public void clear() {
        System.out.println("outer clear");
    };
    
    final class Inner{
        public void clear() {
            System.out.println("inner clear");
            Outer.this.clear();
        };
    }
    public static void main(String[] args) {
        Outer outer=new Outer();
        Inner inner=outer.new Inner();
        inner.clear();
        
    }
}
