package verify.design;


//不能控制单例模式什么时候创建
//public class Singleton {
//    public static int status=1;
//
//    private  Singleton() {
//        // TODO Auto-generated constructor stub
//        System.out.println("create instance");
//    }
//
//    private static Singleton single=new Singleton();
//
//    public static Singleton getInstance() {
//        return single;
//    }
//    
//}

//public class Singleton {
//    public static int status=1;
//    private  Singleton() {
//        // TODO Auto-generated constructor stub
//        System.out.println("lazy create instance");
//    }
//
//    private static Singleton single=null;
//
//    public synchronized static Singleton getInstance() {
//        if(single==null) {
//            single=new Singleton();
//        } 
//        return single;
//    }
//    
//}



//更优秀的单例模式
public class Singleton {
    private Singleton() {
        // TODO Auto-generated constructor stub
    }
    private static class SingletonHolder{
        private static Singleton singleton=new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.singleton;
    }
}
