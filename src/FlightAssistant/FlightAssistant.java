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
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import static FlightAssistant.WeekDay.*;
import Priorities.Priority;
import Priorities.PriorityFlightTime;
import Priorities.PriorityPrice;
import Priorities.PriorityTotalTime;

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
        //llamada al dijkstra
        Stopover result = this.aviationGraph.getBestPath(origin, destination, priority);
        //cuando termina manda
        System.err.println("getBestPath llama a output solo de flights pq no tengo un stopover");
        this.outputBestPath(result);
    }

    /**
     * Send stopover to selected output with current output format.
     *
     * @param stopover Stopover
     */
    private void outputBestPath(Stopover stopover) {
        Double price = stopover.getTotalPrice();
        Double flightTime = stopover.getTotalFlightTime();
        Double totalTime = stopover.getTotalTime();
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
        WeekDay day;
        Airport destination, origin;
        Integer flightNumber;

        if (departureDayString == null)
            return;

        try {
            flightTime = stringDurationTimeToDouble(flightTimeString);
            price = new Double(priceString);

            flightNumber = new Integer(flightNumberString);

            destination = findAirportByCode(destinationString);
            origin = findAirportByCode(originString);

            //Validations
            if ( destination == null || origin == null || airline  == null ) return;

            //Parse Days
            for (String dayString : departureDayString.split("-")){
                day = WeekDay.getWeekDay(dayString);
                if (day != null) {
                    departureTime = stringDepartureTimeToDouble(departureTimeString, day );
                    if (departureTime != null)
                        insertFlight(new Flight(flightTime, departureTime, day, destination, origin, airline, flightNumber, price));
                }
            }


        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }



    public void insertFromFile(String pathString, String append) {

        BufferedReader reader;
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

                vars = line.split("#");
                try {
                    switch (vars.length){
                        case 3:
                            insertAirport(vars[0], vars[1], vars[2]) ;
                            break;
                        case 8:
                            insertFlight(vars[6], vars[5], vars[2], vars[4], vars[3], vars[0], vars[1], vars[7]);
                            break;
                        default:
                            throw new IllegalArgumentException();
                    }


                } catch ( IllegalArgumentException e ) {
                    this.outputWriter.writeErrorsOnFile(pathString);
                } finally {
                    line = reader.readLine();
                }
            }

        } catch ( IOException e ) {
            this.outputWriter.writeErrorFileHandling(pathString);
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

    public Double stringDurationTimeToDouble(String timeString) {
        Double timeInMinutes;
        if(timeString.indexOf("h") != -1) {
            String hours = timeString.substring(0, timeString.indexOf("h") - 1);
            String minutes = timeString.substring(timeString.indexOf("h") + 1, timeString.indexOf("m") - 1);
            timeInMinutes = Double.parseDouble(hours + ".0")*60 + Double.parseDouble(minutes + ".0");
        }
        else {
            String minutes = timeString.substring(0, timeString.indexOf("m") - 1);
            timeInMinutes = Double.parseDouble(minutes);
        }
        return timeInMinutes;
    }
    
    public Double stringDepartureTimeToDouble(String timeString, WeekDay weekday) {
        Double timeInMinutes;
        String[] ary = timeString.split(":");
        String hours = ary[0];
        String minutes = ary[1];
        timeInMinutes = Double.parseDouble(hours)*60 + Double.parseDouble(minutes) + weekday.distanceInMinutes(MONDAY);
        return timeInMinutes;
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

    private static Path stringToPath(String string) {
        if (string == null) return null;
        try {
            return Paths.get(string);
        } catch ( InvalidPathException e ) {
            return null;
        }
    }

}
