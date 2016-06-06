package FlightAssistant;

import DataStructure.Storage;
import Outputs.OutputConsole;
import Outputs.OutputFormater;
import Outputs.OutputWriter;
import Outputs.TextFormat;
import Persistence.Persistence;
import Persistence.FilePersistence;
import Stopovers.Stopover;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import static FlightAssistant.WeekDay.*;

public class FlightAssistant {

    private AviationGraph aviationGraph;
    private OutputWriter outputWriter;
    private Persistence<Storage> persistence;

    public FlightAssistant(){
        //set defaults
        this.outputWriter = new OutputConsole();
        this.outputWriter.setFormat( new TextFormat() );
        this.aviationGraph = new AviationGraph();
        this.persistence = new FilePersistence<Storage>();

    }

    public void setOutputFormat(OutputFormater outputFormat) {
        this.outputWriter.setFormat(outputFormat);
    }

    public void changeOutput(OutputWriter writer) {
        writer.setFormat(this.outputWriter.getFormat());
        this.outputWriter = writer;
    }

    public void getBestPath(String originString, String destinationString, String priotityString, String weekdays) {
        if ( weekdays != null )
            weekdays.split("-"); //array of all posible departure days.

        //llamada al dijkstra

        //cuando termina manda
        System.err.println("getBestPath llama a output solo de flights pq no tengo un stopover");
        this.outputFlights(this.forTestingGetFlights());
    }

    /**
     * Send stopover to selected output with current output format.
     *
     * @param stopover Stopover
     */
    private void outputBestPath(Stopover stopover) {
        Double price = 0.0;
        Double flightTime = 0.0;
        Double totalTime = 0.0;
        Boolean error = false;

        //Start writing
        if ( ! this.outputWriter.start() ) error = true;

        //Write Headers
        if ( ! this.outputWriter.writeHeader(price, flightTime, totalTime) ) error = true;

        //Write Flights
        if ( ! this.writeToOutputFlights(stopover.getFlights()) ) error = true;


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
            System.err.println("Unable to write to output. Discarding all !!!");
            this.outputWriter.discardAll();
        }
    }

    private Boolean writeToOutputFlights(Iterable<Flight> flights) {

        //Write Flights
        for (Flight flight : flights )
            if ( ! this.outputWriter.write(flight) ) {
                System.err.println("Unable to write a flight to output. Discarding all !!!");
                this.outputWriter.discardAll();
                return false;
            }
        return true;

    }

    private Boolean writeToOutputAirports(Iterable<Airport> airports) {

        //Write Airports
        for (Airport airport : airports )
            if ( ! this.outputWriter.write(airport) ) {
                System.err.println("Unable to write an airport to output. Discarding all !!!");
                this.outputWriter.discardAll();
                return false;
            }
        return true;

    }

    private Iterable<Flight> forTestingGetFlights(){
        List<Flight> list = new LinkedList<Flight>();

        Airport s = new Airport("s", Float.NaN, Float.NaN);
        Airport a = new Airport("a", Float.NaN, Float.NaN);
        Airport b = new Airport("b", Float.NaN, Float.NaN);
        Airport c = new Airport("c", Float.NaN, Float.NaN);
        Airport d = new Airport("d", Float.NaN, Float.NaN);

        list.add(new Flight(11.0, 5.0, MONDAY, a, s, "StoA", Integer.MIN_VALUE, 1.0));
        list.add(new Flight(11.0, 5.0, MONDAY, b, s, "StoB", Integer.MIN_VALUE, 4.0));
        list.add(new Flight(11.0, 5.0, MONDAY, c, s, "StoC", Integer.MIN_VALUE, 2.0));
        list.add(new Flight(1800.0, 5.0, MONDAY, d, s, "StoD", Integer.MIN_VALUE, 7.0));
        list.add(new Flight(11.0, 5.0, MONDAY, b, a, "AtoB", Integer.MIN_VALUE, 3.0));
        list.add(new Flight(11.0, 5.0, MONDAY, c, a, "AtoC", Integer.MIN_VALUE, 9.0));
        list.add(new Flight(11.0, 16.0, TUESDAY, d, a, "AtoD", Integer.MIN_VALUE, 3.0));
        list.add(new Flight(11.0, 5.0, MONDAY, a, b, "BtoA", Integer.MIN_VALUE, 3.0));
        list.add(new Flight(11.0, 5.0, MONDAY, c, b, "BtoC", Integer.MIN_VALUE, 2.0));
        list.add(new Flight(11.0, 5.0, WEDNESDAY, d, b, "BtoD", Integer.MIN_VALUE, 6.0));
        list.add(new Flight(11.0, 5.0, MONDAY, a, c, "CtoA", Integer.MIN_VALUE, 9.0));
        list.add(new Flight(11.0, 5.0, MONDAY, b, c, "CtoB", Integer.MIN_VALUE, 2.0));
        list.add(new Flight(11.0, 5.0, MONDAY, d, c, "CtoD", Integer.MIN_VALUE, 4.0));
        list.add(new Flight(11.0, 5.0, MONDAY, a, d, "DtoA", Integer.MIN_VALUE, 4.0));
        list.add(new Flight(11.0, 5.0, MONDAY, b, d, "DtoB", Integer.MIN_VALUE, 6.0));
        list.add(new Flight(11.0, 5.0, MONDAY, c, d, "DtoC", Integer.MIN_VALUE, 4.0));

        return list;
    }

    public void deleteFlight(String code) {
        throw new NotImplementedException();
    }

    public void deleteAirport(String code) {
        this.aviationGraph.getAirports().put(code, null);
    }

    private void insertFlight(Flight flight){
        Airport airportToPut = flight.getOrigin();
        this.aviationGraph.getAirports().get(airportToPut.getCode()).addFlight(flight);
    }

    private void insertAirport(Airport airport){
        this.aviationGraph.insertAirport(airport);
    }

    private Airport findAirportByCode(String code) {
        return this.aviationGraph.getAirports().get(code);
    }

    public void insertAirport( String code, String lat, String lon ) {

        Float latitud, longitud;

        try {
            latitud = new Float(lat);
            longitud = new Float(lon);

            //Validations
            if ( code == null ) return;

            insertAirport( new Airport(code, latitud, longitud) );

        } catch ( Exception e ) {
            return;
        }
    }

    public void insertFlight(String flightTimeString, String departureTimeString, String departureDayString,
                             String destinationString, String originString, String airline,
                             String flightNumberString, String priceString) {

        Double flightTime, departureTime, price;
        WeekDay departureDay;
        Airport destination, origin;
        Integer flightNumber;;

        try {
            flightTime = new Double(flightTimeString);
            departureTime = new Double(departureTimeString);
            price = new Double(priceString);
            departureDay = WeekDay.valueOf(departureDayString);
            flightNumber = new Integer(flightNumberString);

            destination = findAirportByCode(destinationString);
            origin = findAirportByCode(originString);

            //Validations
            if ( destination == null || origin == null || airline  == null ) return;

            insertFlight( new Flight(flightTime,departureTime,departureDay,destination,origin,airline,flightNumber,price) );

        } catch ( Exception e ) {
            return;
        }
    }

    public void insertAirport(Path path) {
        BufferedReader reader;
        String line;
        Integer lineCount = 1;
        String[] flightArray;

        try {
            reader = Files.newBufferedReader(path);
            line = reader.readLine();

            while (line != null) {
                System.err.println(line);
                flightArray = line.split("#");

                try {
                    if ( flightArray.length != 3 )
                        throw new IllegalArgumentException();

                    insertAirport( new Airport(
                            flightArray[0].trim(),
                            new Float(flightArray[1].trim()),
                            new Float(flightArray[2].trim()))
                    );
                } catch ( IllegalArgumentException e ) {
                    System.err.println("Error parsing airport on line: " + lineCount);
                } finally {
                    lineCount++;
                    line = reader.readLine();
                }
            }

        } catch ( IOException e ) {
            System.err.println("Error manipulating file.");
        }

    }

    public void insertFlight(Path path) {
        throw new NotImplementedException();
    }

    public void findAllFlights() {
        throw new NotImplementedException();

//        //Retrieve all flights
//        Iterable<Flight> allFlights =  .....
//
//        this.outputFlights(allFlights);

    }

    public void findAllAirports() {
        throw new NotImplementedException();

//        //Retrieve all flights
//        Iterable<Airport> allAirports =  ....
//
//        this.outputAirports(allAirports);

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
//        persistence.save( __STRUCT_WITH_ALL_DATA__ );
    }

    /**
     * Loads data structure from where the persistence class has defined
     *
     */
    public void load() {
//        __STRUCT_WITH_ALL_DATA__
        this.persistence.load();
    }

    /**
     * Change current persistence
     * @param persistence Persistence
     */
    public void changePersistence(Persistence<Storage> persistence) {
        this.persistence = persistence;
    }

}
