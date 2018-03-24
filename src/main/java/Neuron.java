import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Neuron {
    private static Long idCounter = 0L;
    private Long id;

    private Integer counterSignal = 0;
//    private Integer numberOfConnects;

    private Integer weight = 10;

    private List<Synapse> synapsesLeft = new ArrayList<Synapse>();
    private List<Synapse> synapsesRight = new ArrayList<Synapse>();

    public Neuron(){
        this.id = idCounter++;
    }

    public Synapse chooseSynapse(){
        if (synapsesRight != null) {
            if (synapsesRight.size() > 0) {
                Random random = new Random();
                Integer index = random.nextInt(synapsesRight.size());
                counterSignal++;
                return synapsesRight.get(index);
            }else{
                counterSignal++;
                return null;
            }
        }else{
            counterSignal++;
            return null;
        }
    }

    public void addSynapseLeft(Synapse synapse){
//        System.out.println(this.toString() + " synapsesLeft " + synapsesLeft.toString());
        synapsesLeft.add(synapse);
    }

    public void addSynapseRight(Synapse synapse){
//        System.out.println(this.toString() + " synapsesRight " + synapsesRight.toString());
        synapsesRight.add(synapse);
    }

    public void removeSynapseLeft(Synapse synapse){
        synapsesLeft.remove(synapse);
    }

    public void removeSynapseRight(Synapse synapse){
        synapsesRight.remove(synapse);
    }

    public Integer getNumberOfConnects() {
        return synapsesLeft.size() + synapsesRight.size();
    }

    public Integer getCounterSignal() {
        return counterSignal;
    }

    public List<Synapse> getAllSynapses(){
        List<Synapse> synapses = new ArrayList<Synapse>();
        synapses.addAll(synapsesRight);
        synapses.addAll(synapsesLeft);
        return synapses;
    }

    public Long getId(){
        return this.id;
    }

    public Integer getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "(" + getId() + ")";
    }

    public void updateWeight(int i) {
        if (i == 0){
            weight -= 1;
        }else{
            weight = 10;
        }
    }
}
