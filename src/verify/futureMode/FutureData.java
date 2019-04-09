package verify.futureMode;

public class FutureData implements Data{
    private RealData realData=null;  //futureDate对realData的封装

    protected boolean isReady=false;

    public synchronized void setResult(RealData data) {
        if(isReady) {
            return;
        }
        this.realData=data;
        isReady=true;
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        // TODO Auto-generated method stub
        while(!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return this.realData.getResult();
    }

}
