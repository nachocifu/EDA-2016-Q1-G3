package FlightAssistant;

import DataStructure.Storage;
import Outputs.OutputConsole;
import Outputs.OutputFormater;
import Outputs.OutputWriter;
import Outputs.TextFormat;
import Parser.*;
import Persistence.Persistence;
import Persistence.FilePersistence;
import Stopovers.Stopover;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import Priorities.Priority;
import Priorities.PriorityFlightTime;
import Priorities.PriorityPrice;
import Priorities.PriorityTotalTime;

public class FlightAssistant implements GraphManager {

    private AviationGraph aviationGraph;
    private OutputWriter outputWriter;
    private Persistence persistence;
    private Parser<Flight> flightParser;
    private Parser<Airport> airportParser;

    public FlightAssistant(){
        //set defaults
        this.outputWriter = new OutputConsole();
        this.outputWriter.setFormat( new TextFormat() );
        this.aviationGraph = new AviationGraph();
        this.persistence = new FilePersistence<AviationGraph>();
        this.flightParser = new FlightParser();
        this.airportParser = new AirPortParser();
    }

    public void setOutputFormat(OutputFormater outputFormat) {
        this.outputWriter.setFormat(outputFormat);
    }

    public void changeOutput(OutputWriter writer) {
        writer.setFormat(this.outputWriter.getFormat());
        this.outputWriter = writer;
    }

    public void getBestPath(String originString, String destinationString, String priotityString, String weekdays) {
        WeekDay[] weekdayArray = null;
        if (!weekdays.isEmpty()) {
            System.out.println(weekdays);
            weekdayArray = Parser.parseWeekdays(weekdays);
        }
        Airport origin = findAirportByCode(originString);
        Airport destination = findAirportByCode(destinationString);
        if(origin == null || destination == null) {
            return;
        }
        Priority priority;
        switch (priotityString) {
            case "ft": priority = new PriorityFlightTime();
                     break;
            case "tt": priority = new PriorityTotalTime();
                     break;
            case "pr": priority = new PriorityPrice();
                     break;
            default:
                return;
        }
        Stopover result;
        //llamada al dijkstra
        if(weekdayArray == null)
            result = this.aviationGraph.getBestPath(origin, destination, priority);
        else {
            result = this.aviationGraph.getBestPath(origin, destination, priority, weekdayArray);
        }
//cuando termina manda
        this.outputBestPath(result);
    }

    /**
     * Send stopover to selected output with current output format.
     *
     * @param stopover Stopover
     */
    private void outputBestPath(Stopover stopover) {
        
        Boolean error = false;

        //Start writing
        if ( ! this.outputWriter.start() ) error = true;
        
        if(stopover == null) {
            if ( ! this.outputWriter.writeNotFound() ) error = true;
        }
        else {
            //Write Headers
            Double price = stopover.getTotalPrice();
            Double flightTime = stopover.getTotalFlightTime();
            Double totalTime = stopover.getTotalTime();
            if ( ! this.outputWriter.writeHeader(price, flightTime, totalTime) ) error = true;

            //Write Flights
            if ( ! this.writeToOutputFlights(stopover.getFlights()) ) error = true;
        }

        //Check for error and finish Writing
        checkErrorsAndFinishWritingToOutput(error);

    }

    /**
     * Send list of flights to selected output with current output format
     *
     * @param flights Iterable<Flight>
     */
    private void outputFlights(Iterable<Flight> flights ){
        Boolean error = false;

        //Start writing
        if ( ! this.outputWriter.start() ) error = true;

        //Write Flights
        if ( ! this.writeToOutputFlights(flights) ) error = true;


        //Check for error and finish Writing
        checkErrorsAndFinishWritingToOutput(error);
    }

    /**
     * Send list of airports to selected output with current output format
     *
     * @param airports Iterable<Airport>
     */
    private void outputAirports(Iterable<Airport> airports ){
        Boolean error = false;

        //Start writing
        if ( ! this.outputWriter.start() ) error = true;

        //Write Airports
        if ( ! this.writeToOutputAirports(airports) ) error = true;


        //Check for error and finish Writing
        checkErrorsAndFinishWritingToOutput(error);
    }

    private void checkErrorsAndFinishWritingToOutput(Boolean error){
        //(lazy evaluation)
        if ( error || ! this.outputWriter.finish() ) {
            System.err.println("Unable to write to output. Discarding all !!!"); //If writer cannot output, then output to system error
            this.outputWriter.discardAll();
        }
    }

    private Boolean writeToOutputFlights(Iterable<Flight> flights) {

        //Write Flights
        for (Flight flight : flights )
            if ( ! this.outputWriter.write(flight) ) {
                System.err.println("Unable to write to output. Discarding all !!!"); //If writer cannot output, then output to system error
                this.outputWriter.discardAll();
                return false;
            }
        return true;

    }

    private Boolean writeToOutputAirports(Iterable<Airport> airports) {

        //Write Airports
        for (Airport airport : airports )
            if ( ! this.outputWriter.write(airport) ) {
                System.err.println("Unable to write to output. Discarding all !!!"); //If writer cannot output, then output to system error
                this.outputWriter.discardAll();
                return false;
            }
        return true;

    }

    public void deleteFlight(String airline, String flightNum) {
        String code = "#" + airline + flightNum;
        Flight aux = this.aviationGraph.getFlights().get(code);
        System.out.println("Entro al deleteFlight");
        if(aux != null) {
            System.out.println("aux no dio null");
            this.aviationGraph.getAirports().get(aux.getOrigin().getCode()).removeFlight(aux);
            this.aviationGraph.getFlights().put(code, null);
        }
    }

    public void deleteAirport(String code) {
        Airport airportToDelete = this.aviationGraph.getAirports().get(code);
        for(Airport each: airportToDelete.getInboundFlightsOrigins()) {
            
        }
        this.aviationGraph.getAirports().put(code, null);
    }

    private void insert(Flight flight){
        Airport airportToPut = flight.getOrigin();
        this.aviationGraph.getAirports().get(airportToPut.getCode()).addFlight(flight);
        this.aviationGraph.getFlights().put(flight.getCode(), flight);
    }

    private void insert(Airport airport){
        this.aviationGraph.insertAirport(airport);
    }

    public void add(Flight flight){

        if (this.aviationGraph.isOk(flight)) this.insert(flight);
    }

    public void add(Airport airport){

        if (this.aviationGraph.isOk(airport)) this.insert(airport);

    }

    public void insertAirport(String[] params) {

        this.airportParser.parse(params, this);

    }

    public void insertFlight(String[] params) {

        this.flightParser.parse(params, this);

    }




    public void insertFromFile(String pathString,Parser parser) {


        BufferedReader reader;
        Boolean inconsistencies = false;
        String line;
        String[] vars;

        Path path = stringToPath(pathString);
        if (path == null){
            this.outputWriter.writeErrorFileHandling(pathString);
            return;
        }

        try {
            reader = Files.newBufferedReader(path);
            line = reader.readLine();

            while (line != null) {

                if ( !line.isEmpty() ) {
                    vars = line.split("#");
                    if (parser.parse(vars, this)) inconsistencies = true;
                }

                line = reader.readLine();
            }
            if (inconsistencies) {
                this.outputWriter.start();
                this.outputWriter.writeErrorsOnFile(pathString);
                this.outputWriter.finish();
            }

        } catch ( IOException e ) {
            this.outputWriter.start();
            this.outputWriter.writeErrorFileHandling(pathString);
            this.outputWriter.finish();
        }
    }

    public void findAllFlights() {
        this.outputFlights(this.aviationGraph.findAllFlights());

    }

    public void findAllAirports() {
        this.outputAirports(this.aviationGraph.findAllAirports());

    }

    public void deleteAllAirports() {
        for(String each: this.aviationGraph.getAirports().keySet()) {
            this.aviationGraph.getAirports().put(each, null);
        }
    }

    public void deleteAllFlights() {
        for(String each: this.aviationGraph.getAirports().keySet()) {
            this.aviationGraph.getAirports().get(each).deleteAllFlights();
        }
    }
    
    
    
    /**
     * Saves current data structure to where the persistence class has defined
     *
     */
    public void save() {
        if ( ! persistence.save( this.aviationGraph ) ) {
            this.outputWriter.start();
            this.outputWriter.writeErrorSaving();
            this.outputWriter.finish();
        }
    }

    /**
     * Loads data structure from where the persistence class has defined
     *
     */
    public void load() {

        AviationGraph graph = (AviationGraph) this.persistence.load();

        if(graph != null)
            this.aviationGraph = graph;
        else {
            this.outputWriter.start();
            this.outputWriter.writeUnableToLoadState();
            this.outputWriter.finish();
        }

    }

    /**
     * Change current persistence
     * @param persistence Persistence
     */
    public void changePersistence(Persistence<Storage> persistence) {
        this.persistence = persistence;
    }

    private static Path stringToPath(String string) {
        if (string == null) return null;
        try {
            return Paths.get(string);
        } catch ( InvalidPathException e ) {
            return null;
        }
    }

    @Override
    public Airport findAirportByCode(String code) {
        return this.aviationGraph.getAirports().get(code);
    }
}
