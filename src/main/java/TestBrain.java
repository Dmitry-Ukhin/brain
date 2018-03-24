import java.util.Random;
import java.util.Scanner;

public class TestBrain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String event;

        Brain brain = Brain.getBrain();
        brain.build();

        Random random = new Random();
        Integer indexToNewNeuron = random.nextInt(1);


        Integer iterator = 0;
        do {
            System.out.println("$$You can write /b for exit");
            System.out.println("$$write to brain (can command):");
            event = scanner.nextLine();

            if (event.equals("/sh N")){
                brain.showElements(Neuron.class);
                System.out.println("$$write to brain (NOT command):");
                event = scanner.nextLine();
            }else if (event.equals("/sh S")){
                brain.showElements(Synapse.class);
                System.out.println("$$write to brain (NOT command):");
                event = scanner.nextLine();
            }else if(event.equals("/sh")){
                System.out.println("$$Enter /sh N for show Neurons or /sh S for show Synapses");
                System.out.println("$$write to brain (NOT command):");
                event = scanner.nextLine();
            }else if(event.equals("/S")){
                showStatuses();
                System.out.println("$$write to brain (NOT command):");
                event = scanner.nextLine();
            }else if(event.equals("/h")){
                System.out.println("/sh show\n/S show Statuses\n/h this output");
                System.out.println("$$write to brain (NOT command):");
                event = scanner.nextLine();
            }

            System.out.println(brain.getMemories(event));
            if (indexToNewNeuron != 0){
                brain.newNeuron();
            }
            System.out.println(">>>>Iterator:" + iterator++);
        }while (!event.equals("/b"));
    }

    private static void showStatuses(){
        System.out.println("$$STATUSES: OK(1-9):\n\tstatus: 1 (Created synapse)\n\tstatus: 2 (Created neuron)\n" +
            "Not created (10-19):\n\tstatus: 10 (synapse: neuron == selectedNeuron)\n\tstatus: 11 (synapse: selected endNeuron)\n\t" +
            "status: 12 (neuron)\nWarning (20-29):\n\tstatus: 20 (neuron have small weight \"< 0.1\", " +
                "removed neuron)\n\tstatus: 21 (log file is updated)\n");
    }
}
