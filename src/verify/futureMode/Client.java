package verify.futureMode;

public class Client {

    public Data request(String data) {
        FutureData future=new FutureData();
        new Thread() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                RealData realData=new RealData(data);
                future.setResult(realData);
            }
        }.start();

        return future;
    }
    public static void main(String[] args) {
        Client client=new Client();
        Data data = client.request("hello");
        System.out.println("请求完毕");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("真实数据为"+data.getResult());
    }
}
