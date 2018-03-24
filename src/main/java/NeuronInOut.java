import java.util.List;

public class NeuronInOut extends Neuron {
    private String memories;

    public NeuronInOut(String memories){
        super();
        this.memories = memories;
    }

    public String getMemories() {
        return memories;
    }
}
