package network2;

import java.util.ArrayList;
import java.util.List;

/**
* When Receptor will stimulus, he will return list of neurons ids
* */
public class Receptor extends Cell{

    /*
    * List ids of neurons
    * */
    private List<Neuron> neurons = new ArrayList<Neuron>();
    private List<Double> weight = new ArrayList<Double>();

    public void addNeuron(Neuron n, Double w){
        neurons.add(n);
        weight.add(w);
    }

    public void inputSign(int signal){
        System.out.println("("+this.getId()+")"+">"+signal+"<");
        if (signal == 1){
            for (int i = 0; i < neurons.size(); i++){
                neurons.get(i).inputSign(weight.get(i));
            }
        }
        System.out.println(neurons.toString());

    }
}
