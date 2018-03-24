public class Signal {
    private static Signal signal;
    private String data;

    private Signal(){}

    public static Signal getSignal(){
        Signal localSignal = signal;
        if (localSignal == null){
            signal = new Signal();
        }
        return localSignal;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
