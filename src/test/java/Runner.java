import network2.Brain;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Brain brain = new Brain();
        Scanner scan = new Scanner(System.in);
        do {
            System.out.print("Enter>>>");
            String text = scan.nextLine();
            int[] matrix = charToArray(text);
            brain.enterSignals(matrix);
            System.out.println(brain.getResult());
            System.out.print("Enter \\q for exit");
        }while (!scan.nextLine().equals("\\q"));
    }

    private static int[] charToArray(String a){
        a = a.toUpperCase();
        char[] chars = a.toCharArray();
        int[] result = new int[5];
        switch (chars[0]){
            case 'A':
                result[0] = 0;
                result[1] = 0;
                result[2] = 0;
                result[3] = 0;
                result[4] = 1;
                break;
            case 'B':
                result[0] = 0;
                result[1] = 0;
                result[2] = 0;
                result[3] = 1;
                result[4] = 0;
                break;
            case 'C':
                result[0] = 0;
                result[1] = 0;
                result[2] = 0;
                result[3] = 1;
                result[4] = 1;
                break;
            case 'D':
                result[0] = 0;
                result[1] = 0;
                result[2] = 1;
                result[3] = 0;
                result[4] = 0;
                break;
            case 'E':
                result[0] = 0;
                result[1] = 0;
                result[2] = 1;
                result[3] = 0;
                result[4] = 1;
                break;
            case 'F':
                result[0] = 0;
                result[1] = 0;
                result[2] = 1;
                result[3] = 1;
                result[4] = 0;
                break;
            case 'G':
                result[0] = 0;
                result[1] = 0;
                result[2] = 1;
                result[3] = 1;
                result[4] = 1;
                break;
            case 'H':
                result[0] = 0;
                result[1] = 1;
                result[2] = 0;
                result[3] = 0;
                result[4] = 0;
                break;
            case 'I':
                result[0] = 0;
                result[1] = 1;
                result[2] = 0;
                result[3] = 0;
                result[4] = 1;
                break;
            case 'J':
                result[0] = 0;
                result[1] = 1;
                result[2] = 0;
                result[3] = 1;
                result[4] = 0;
                break;
            case 'K':
                result[0] = 0;
                result[1] = 1;
                result[2] = 0;
                result[3] = 1;
                result[4] = 1;
                break;
            case 'L':
                result[0] = 0;
                result[1] = 1;
                result[2] = 1;
                result[3] = 0;
                result[4] = 0;
                break;
            case 'M':
                result[0] = 0;
                result[1] = 1;
                result[2] = 1;
                result[3] = 0;
                result[4] = 1;
                break;
            case 'N':
                result[0] = 0;
                result[1] = 1;
                result[2] = 1;
                result[3] = 1;
                result[4] = 0;
                break;
            case 'O':
                result[0] = 0;
                result[1] = 1;
                result[2] = 1;
                result[3] = 1;
                result[4] = 1;
                break;
            case 'P':
                result[0] = 1;
                result[1] = 0;
                result[2] = 0;
                result[3] = 0;
                result[4] = 0;
                break;
            case 'Q':
                result[0] = 1;
                result[1] = 0;
                result[2] = 0;
                result[3] = 0;
                result[4] = 1;
                break;
            case 'R':
                result[0] = 1;
                result[1] = 0;
                result[2] = 0;
                result[3] = 1;
                result[4] = 0;
                break;
            case 'S':
                result[0] = 1;
                result[1] = 0;
                result[2] = 0;
                result[3] = 1;
                result[4] = 1;
                break;
            case 'T':
                result[0] = 1;
                result[1] = 0;
                result[2] = 1;
                result[3] = 0;
                result[4] = 0;
                break;
            case 'U':
                result[0] = 1;
                result[1] = 0;
                result[2] = 1;
                result[3] = 0;
                result[4] = 1;
                break;
            case 'V':
                result[0] = 1;
                result[1] = 0;
                result[2] = 1;
                result[3] = 1;
                result[4] = 0;
                break;
            case 'W':
                result[0] = 1;
                result[1] = 0;
                result[2] = 1;
                result[3] = 1;
                result[4] = 1;
                break;
            case 'X':
                result[0] = 1;
                result[1] = 1;
                result[2] = 0;
                result[3] = 0;
                result[4] = 0;
                break;
            case 'Y':
                result[0] = 1;
                result[1] = 1;
                result[2] = 0;
                result[3] = 0;
                result[4] = 1;
                break;
            case 'Z':
                result[0] = 1;
                result[1] = 1;
                result[2] = 0;
                result[3] = 1;
                result[4] = 0;
                break;
        }
        return result;
    }
}
