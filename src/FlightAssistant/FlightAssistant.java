package FlightAssistant;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FlightAssistant {

    private AviationGraph aviationGraph;


    public static void main(String[] args) {

        if ( args !=  null && args.length > 0)
            System.out.println("Invocacion desde shell");
        else
            System.out.println("Invocacion pidiendo command-line");

        while( true ) {
            LinkedList<String> input = getInputFromCommandLine();
            parseCommand(input);
        }
    }

    private static LinkedList<String> getInputFromCommandLine() {
        // create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);

        // get their input as a String
        String input = scanner.nextLine();

        return new LinkedList<String>(Arrays.asList(input.split(" ")));
    }

    private static void parseCommand(LinkedList<String> input) {
        switch ( input.poll() ) {
            case "insert":
                break;
            case "delete":
                break;
            case "outputFormat":

                break;
            case "output":
                break;
            case "findRoute":
                break;
            default:
                System.out.println("Invalid input");
                return;
        }
    }


}
