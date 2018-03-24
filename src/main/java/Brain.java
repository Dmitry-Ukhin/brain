import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * OK (1-9):
 *      status: 1 (Created synapse)
 *      status: 2 (Created neuron)
 *
 * Not created (10-19):
 *      status: 10 (synapse: neuron == selectedNeuron)
 *      status: 11 (synapse: selected endNeuron)
 *      status: 12 (neuron)
 * Warning (20-29):
 *      status: 20 (neuron have small weight "< 0.1", removed neuron)
 *      status: 21 (log file is updated)
 */
public class Brain {
    private static Brain brain;

    private Neuron pointerOnNeuron;

    private Integer counterCalls = 0;

    private List<Long> idsSynapses = new ArrayList<Long>();
    private String memories = "<";
    private String inputEvent;

    private List<Neuron> neurons = new ArrayList<Neuron>();
    private List<Synapse> synapses = new ArrayList<Synapse>();

    private Brain(){}

    public static Brain getBrain(){
        if(brain == null){
            brain = new Brain();
        }
        return brain;
    }

    public void createNeuron(){
        Neuron neuron = new Neuron();
        neurons.add(neuron);
    }

    public void createSynapse(Neuron neuronLeft, Neuron neuronRight){
        Synapse synapse = new Synapse(neuronLeft, neuronRight);
        neuronLeft.addSynapseRight(synapse);
        neuronRight.addSynapseLeft(synapse);
        synapses.add(synapse);
    }

    public void build(){
        brain.createNeuron();
        brain.createNeuron();
        brain.createNeuron();
        brain.createNeuron();
        brain.createNeuron();

        brain.createSynapse(brain.getNeuronById(0L), brain.getNeuronById(1L));
        brain.createSynapse(brain.getNeuronById(1L), brain.getNeuronById(2L));
        brain.createSynapse(brain.getNeuronById(2L), brain.getNeuronById(3L));
        brain.createSynapse(brain.getNeuronById(3L), brain.getNeuronById(4L));
    }

    public void newNeuron(){
        Random random = new Random();
        Integer status = random.nextInt(2);
        if (status != 0){
            createNeuron();
            System.out.println("STATUS: 2");
        }else{
            System.out.println("STATUS: 12");
        }
    }

    public String getMemories(String event){
        counterCalls++;
        inputEvent = event;
        memories = "<" + neurons.get(0).toString();
        pointerOnNeuron = null;
        Integer status;
        do {
            update(0);
            status = transferSignal();
            newNeuron();
        }while (status != 0);
        return memories;
    }

    public void showElements(Class objectOfBrain){
        if (objectOfBrain == Neuron.class){
            System.out.println();
            System.out.print("[");
            for(Neuron n : neurons){
                System.out.print("[" + n.toString() + "<" + n.getWeight() + ">]");
            }
        }else if (objectOfBrain == Synapse.class){
            System.out.println(synapses.toString());
        }
        System.out.print("]");
        System.out.println();
    }

    private Integer transferSignal() {
        Integer status;
        Neuron selectedNeuron;
        if (pointerOnNeuron != null) {
            selectedNeuron = getNeuronById(pointerOnNeuron.getId());
        } else {
            selectedNeuron = getNeuronById(0L);
        }
        Synapse synapse;
        if (selectedNeuron != null) {
            selectedNeuron.updateWeight(1);
            synapse = selectedNeuron.chooseSynapse();
            if (synapse != null) {
                idsSynapses.add(synapse.getId());


                if (synapse.getData() != null) {
                    if (memories.lastIndexOf(")") > 0) {
                        memories += "--" + synapse.getData() + "-->";
                    } else {
                        memories += "--" + synapse.getData() + "-->";
                    }
                } else {
                    synapse.setData(inputEvent);
                    inputEvent = null;
                    memories += "--" + synapse.getData() + "-->";
                }

                pointerOnNeuron = synapse.nextNeuron();
                if (pointerOnNeuron == null) {
                    memories += "-->NULL";
                    status = 0;
                } else {
                    memories += pointerOnNeuron.toString();
                    status = 1;
                }



                Double weight = (double) selectedNeuron.getCounterSignal() / counterCalls;
                if (weight > 0) {
                    Random random = new Random();
                    Integer randomNumber = random.nextInt(selectedNeuron.getNumberOfConnects());
                    if (randomNumber == 0) {
                        Integer index = random.nextInt(neurons.size());

                        Neuron neuron = neurons.get(index);
                        if (neuron != selectedNeuron) {
                            createSynapse(selectedNeuron, neuron);
                            System.out.println("STATUS: 1");
                        } else {
                            System.out.println("STATUS: 10");
                        }
                    }
                } else {
                    System.out.println("STATUS: 20 {" + selectedNeuron.toString() + "[" + selectedNeuron.getWeight() +
                            "]}");
                    Synapse synapse1 = selectedNeuron.chooseSynapse();

                    if (synapse1 != null) {
                        if (synapse1.getData() != null) {
                            memories += " " + synapse1.getData() + " ||";
                        } else {
                            synapse1.setData(inputEvent);
                            inputEvent = null;
                        }
                        pointerOnNeuron = synapse1.nextNeuron();
                        if (pointerOnNeuron == null) {
                            status = 0;
                        } else {
                            status = 1;
                        }
                    } else {
                        status = 0;
                    }
                    removeNeuron(selectedNeuron);
                }
                return status;
            } else {
                FileWriter fileOut = null;
                String out = ">>>>>>>pointerOnNeuron: " + pointerOnNeuron;
                String neuron;
                try {
                    fileOut = new FileWriter("logListNeurons.txt");

                    for (int i = 0; i < neurons.size(); i++) {
                        neuron = "\ni=" + i + " " + neurons.get(i).toString();
                        out = out.concat(neuron);
                    }
                    Date date = new Date();
                    out = out.concat("\n" + date.toString() + "\n____________");

                    fileOut.write(out);
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    System.out.println("STATUS: 21");
                    if (fileOut != null) {
                        try {
                            fileOut.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return 0;
            }
        }
        return 0;
    }

    public void success(String result){
        if (result.equals("%OK")){
            for (Long id : idsSynapses){
                Synapse synapse = getSynapseById(id);
                Neuron neuron = null;
                if (synapse != null) {
                    neuron = synapse.previousNeuron();
                }
                if (neuron != null) {
                    neuron.addSynapseRight(synapse);
                }
            }
            System.out.println("< +1");
        }else if(result.equals("%NO")){
            Integer counter = 0;
            for (Long id : idsSynapses){
                Synapse synapse = getSynapseById(id);
                Neuron neuron = null;
                if (synapse != null) {
                    neuron = synapse.previousNeuron();
                }
                if (neuron != null) {
                    List<Synapse> list = neuron.getAllSynapses();
                    for (Synapse s : list){
                        if (s == synapse){
                            counter++;
                        }
                    }
                    if (counter > 1){
                        neuron.removeSynapseRight(synapse);
                    }
                }
            }
            System.out.println("< -1");
        }
    }

    private void update(Integer isUp){
        for (int i = 0; i < neurons.size(); i++){
            Neuron n = neurons.get(i);
            n.updateWeight(isUp);

            if (n.getWeight() <= 0){
                removeNeuron(n);
            }
        }
    }

    private void removeNeuron(Neuron n){
        for (Synapse syn : n.getAllSynapses()) {
            Neuron neuron = syn.previousNeuron();
            neuron.removeSynapseRight(syn);
            neuron = syn.nextNeuron();
            neuron.removeSynapseLeft(syn);
            synapses.remove(syn);
        }
        neurons.remove(n);
        System.out.println("STATUS: 20 {" + n.toString() + "[" + n.getWeight() +"]}");
    }

    private Neuron getNeuronById(Long id){
        for (Neuron neuron : neurons){
            if (neuron.getId() == id){
                return neuron;
            }
        }
        return null;
    }

    private Synapse getSynapseById(Long id){
        for (Synapse synapse : synapses){
            if (synapse.getId() == id){
                return synapse;
            }
        }
        return null;
    }
}
