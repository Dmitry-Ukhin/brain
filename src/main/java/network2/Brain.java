package network2;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.ArrayList;
import java.util.List;

public class Brain {
    private List<Cell> cells = new ArrayList<Cell>();

    public Brain(){
//        Cell neuron = new Neuron();
//        cells.add(neuron);
//        for (int i = 0; i < 5; i++){
//            Cell receptor = new Receptor();
//            ((Receptor) receptor).addNeuron((Neuron) neuron);
//            cells.add(receptor);
//        }

        double weight;

        Cell rec1 = new Receptor();
        Cell rec2 = new Receptor();
        Cell rec3 = new Receptor();
        Cell rec4 = new Receptor();
        Cell rec5 = new Receptor();

        weight = 1.0;
        Cell neu1 = new Neuron("P");
        ((Receptor) rec1).addNeuron((Neuron) neu1, weight);
        Cell neu2 = new Neuron("H");//H
        ((Receptor) rec2).addNeuron((Neuron) neu2, weight);
        Cell neu3 = new Neuron("D");
        ((Receptor) rec3).addNeuron((Neuron) neu3, weight);
        Cell neu4 = new Neuron("B");//B
        ((Receptor) rec4).addNeuron((Neuron) neu4, weight);
        Cell neu5 = new Neuron("A");//A
        ((Receptor) rec5).addNeuron((Neuron) neu5, weight);

        weight = weight / 2;
        Cell neu6 = new Neuron("C");
        ((Receptor) rec4).addNeuron((Neuron) neu6, weight);
        ((Receptor) rec5).addNeuron((Neuron) neu6, weight);
        Cell neu7 = new Neuron("E");
        ((Receptor) rec3).addNeuron((Neuron) neu7, weight);
        ((Receptor) rec5).addNeuron((Neuron) neu7, weight);
        Cell neu8 = new Neuron("F");
        ((Receptor) rec3).addNeuron((Neuron) neu8, weight);
        ((Receptor) rec4).addNeuron((Neuron) neu8, weight);

        weight = 1.0;
        weight = weight / 3;
        Cell neu9 = new Neuron("G");
        ((Receptor) rec3).addNeuron((Neuron) neu9, weight);
        ((Receptor) rec4).addNeuron((Neuron) neu9, weight);
        ((Receptor) rec5).addNeuron((Neuron) neu9, weight);

        cells.add(rec1);
        cells.add(rec2);
        cells.add(rec3);
        cells.add(rec4);
        cells.add(rec5);

        cells.add(neu1);
        cells.add(neu2);
        cells.add(neu3);
        cells.add(neu4);
        cells.add(neu5);
        cells.add(neu6);
        cells.add(neu7);
        cells.add(neu8);
        cells.add(neu9);
    }

    public void enterSignals(int[] signs){
        int i = 0;
        for(Cell c : cells){
            if (c instanceof Receptor) {
                Receptor rec = (Receptor) c;
                rec.inputSign(signs[i++]);
            }
        }

    }

    public String getResult(){
        String result = "";
        for (Cell c : cells){
            if (c instanceof Neuron) {
                Neuron neu = (Neuron) c;
                result = result.concat(neu.getMemory() + " ");
                neu.reset();
            }
        }
        return result;
    }
}
/*
 * A[0 0001]
 * B[0 0010]
 * C[0 0011]
 * D[0 0100]
 * E[0 0101]
 * F[0 0110]
 * G[0 0111]
 * H[0 1000]
 * I[0 1001]
 * J[0 1010]
 * K[0 1011]
 * L[0 1100]
 * M[0 1101]
 * N[0 1110]
 * O[0 1111]
 * P[1 0000]
 * Q[1 0001]
 * R[1 0010]
 * S[1 0011]
 * T[1 0100]
 * U[1 0101]
 * V[1 0110]
 * W[1 0111]
 * X[1 1000]
 * Y[1 1001]
 * Z[1 1010]
 *
 * Hello
 * [01000][00101][01100][01100][01111]
 * */

