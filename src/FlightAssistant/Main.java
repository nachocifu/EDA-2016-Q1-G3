package FlightAssistant;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        FlightAssistant flightAssistant = new FlightAssistant();

        if ( args !=  null && args.length > 0) {
            System.out.println("Invocacion desde shell");
        } else {
            System.out.println("Invocacion pidiendo command-line");

            Boolean endExecution = false;
            while ( !endExecution ) {
                LinkedList<String> input = getInputFromCommandLine();
                endExecution = parseCommand(input, flightAssistant);
            }
        }
    }

    private static LinkedList<String> getInputFromCommandLine() {
        // create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);

        // get their input as a String
        String input = scanner.nextLine();

        return new LinkedList<String>(Arrays.asList(input.split(" ")));
    }

    /**
     * El message en este metodo es solo para el periodo de desarrollo.
     * Imprimir los mensajes corresponde al flight assistant.writer
     *
     * @param input
     * @param flightAssistant
     * @return
     */
    private static Boolean parseCommand(Queue<String> input, FlightAssistant flightAssistant) {
        String message = "Invalid Input";
        Boolean endExecution = false;
        String aux;
        switch ( input.poll() ) {
            case "insert":
                aux = input.poll();
                if ( aux != null )
                    switch ( aux ) {
                        case "airport":
                            message = "ok";
    //                        flightAssistant.insertAirport( new Airport( ...datos... ) );
                            break;
                        case "flight":
                            message = "ok";
    //                        flightAssistant.insertFlight( new Airport( ...datos... ) );
                            break;
                        case "all":
                            message = "ok";
                            break;
                    }
                break;
            case "delete":
                aux = input.poll();
                if ( aux != null )
                    switch ( aux ) {
                        case "airport":
                            message = "ok";
    //                        flightAssistant.deleteAirport(String code);
                            break;
                        case "flight":
                            message = "ok";
    //                        flightAssistant.deleteAFlight(String code);
                            break;
                        case "all":
                            message = "ok";
                            break;
                    }
                break;
            case "outputFormat":
                OutputFormat newFormat = OutputFormat.valueOf(input.poll());
                if ( newFormat != null ) {
                    message = "ok";
                    flightAssistant.setOutputFormat(newFormat);
                }
                break;
            case "output":
                aux = input.poll();
                if ( aux != null )
                    switch ( aux ) {
                        case "stdout":
                            message = "ok";
                            flightAssistant.changeOutput( new OutputConsole() );
                            break;
                        case "file":
                            message = "ok";
                            String file = input.poll();
                            if ( file != null ) flightAssistant.changeOutput( new OutputFile( file ) );
                            break;
                    }
                break;
            case "print-airports":
                message = "Printing all airports";

                break;
            case "print-flights":
                message = "Printing all flights";
                break;
            case "exit":
            case "bye":
            case "goodbye":
                endExecution = true;
                message = "Good Bye";
                break;
        }
        System.out.println(message);
        return endExecution;
    }

}
