package verify.futureMode;

public class RealData implements Data {

    public  String result;
    
    public  RealData(String result) {
        // TODO Auto-generated constructor stub
        
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<10;i++) {
            sb.append(result);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        this.result=sb.toString();
        
    }

    @Override
    public String getResult() {
        // TODO Auto-generated method stub
        return result;
    }
    
    



}
