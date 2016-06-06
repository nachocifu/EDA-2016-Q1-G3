package FlightAssistant;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import Outputs.KmlFormat;
import Outputs.OutputConsole;
import Outputs.OutputFile;
import Outputs.TextFormat;
import com.sun.corba.se.impl.presentation.rmi.DynamicMethodMarshallerImpl;

public class Main {

    public static void main(String[] args) {

        FlightAssistant flightAssistant = new FlightAssistant();

        if (args !=  null && args.length > 0) {
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
                            flightAssistant.insertAirport(
                                    input.poll(),
                                    input.poll(),
                                    input.poll()
                            );
                            break;
                        case "flight":
                            message = "ok";
                            flightAssistant.insertFlight(
                                    input.poll(),
                                    input.poll(),
                                    input.poll(),
                                    input.poll(),
                                    input.poll(),
                                    input.poll(),
                                    input.poll(),
                                    input.poll()
                            );
                            break;
                        case "all":
                            aux = input.poll();
                            if ( aux != null )
                                switch (aux) {
                                    case "flight":
                                    case "airport":
                                        flightAssistant.insertFromFile(input.poll(), input.poll());
                                        break;
                                }
                            break;
                    }
                break;
            case "delete":
                aux = input.poll();
                if ( aux != null )
                    switch ( aux ) {
                        case "airport":
                            flightAssistant.deleteAirport(input.poll());
                            break;
                        case "flight":
                            flightAssistant.deleteFlight(input.poll());
                            break;
                        case "all":
                            aux = input.poll();
                            if ( aux != null )
                                switch ( aux ) {
                                    case "airport":
                                        flightAssistant.deleteAllAirports();
                                        break;
                                    case "flight":
                                        flightAssistant.deleteAllFlights();
                                        break;
                                }
                    }
                break;
            case "outputFormat":
                aux = input.poll();
                if ( aux != null )
                    switch ( aux ) {
                        case "KML":
                            flightAssistant.setOutputFormat( new KmlFormat() );
                            break;
                        case "text":
                            flightAssistant.setOutputFormat( new TextFormat() );
                            break;
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
                            flightAssistant.changeOutput( new OutputFile( input.poll() ) );
                            break;
                    }
                break;
            case "findBestPath":
                    flightAssistant.getBestPath(
                            getParameterFromString(input.poll()),
                            getParameterFromString(input.poll()),
                            getParameterFromString(input.poll()),
                            getParameterFromString(input.poll()));
                break;
            case "findAllAirports":
                flightAssistant.findAllAirports();
                message = "ok";
                break;
            case "findAllFlights":
                flightAssistant.findAllFlights();
                message = "ok";
                break;
            case "exitAndSave":
                flightAssistant.save();
                message = "ok";
                break;
            case "exit":
            case "bye":
            case "goodbye":
                endExecution = true;
                message = "Good Bye";
                break;
        }
        System.err.println(message);
        return endExecution;
    }

    /**
     * Returns parameter in string or empty string if null.
     * Parameter is anything after first '=' symbol or entire string if no '='.
     *
     * @param string String
     * @return String
     */
    private static String getParameterFromString(String string) {
        if ( string == null ) return "";

        Integer auxNum;
        auxNum = string.indexOf('=');
        if ( auxNum < 0 )
            return string;

        return string.substring(++auxNum).trim();
    }

}
