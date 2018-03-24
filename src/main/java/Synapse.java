public class Synapse {
    private Neuron neuronLeft;
    private Neuron neuronRight;

    private String data;

    public Synapse(){}

    public Synapse(Neuron neuron, String data){
        this.data = data;
        this.neuronRight = neuron;
    }

    public Synapse(Neuron neuronLeft){
        this.neuronLeft = neuronLeft;
    }

    public Synapse(Neuron neuronLeft, Neuron neuronRight){
        this.neuronLeft = neuronLeft;
        this.neuronRight = neuronRight;
    }

    public Neuron nextNeuron(){
//        System.out.println("NEXT_NEURON" + neuronRight);
        return neuronRight;
    }

    public Neuron previousNeuron(){
        return neuronLeft;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData(){
        return data;
    }

    @Override
    public String toString() {
        return "{" + neuronLeft.toString() + "-|" + data + "|>" + neuronRight.toString() + "}";
    }
}
