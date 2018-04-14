package network2;

/**
 * Neuron is how adder
 * */
public class Neuron extends Cell{
    private String memory;
    private Double sumSigns = .0;

    public Neuron(String m){
        memory = m;
    }

    public void inputSign(Double sign){
        sumSigns = sumSigns + sign;
    }

    public void reset(){
        sumSigns = .0;
    }

    public String getMemory(){
        if (sumSigns == 1) {
            return memory + "["+sumSigns+"]";
        }
        return "null["+sumSigns+"]";
    }

    @Override
    public String toString() {
        return "<"+this.getId()+">"+"["+sumSigns+"] ";
    }
}
