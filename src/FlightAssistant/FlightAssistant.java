package FlightAssistant;

import Outputs.OutputConsole;
import Outputs.OutputFormater;
import Outputs.OutputWriter;
import Outputs.TextFormat;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static FlightAssistant.WeekDay.MONDAY;
import static FlightAssistant.WeekDay.TUESDAY;
import static FlightAssistant.WeekDay.WEDNESDAY;

public class FlightAssistant {

    private AviationGraph aviationGraph;
    private OutputWriter outputWriter;

    public FlightAssistant(){
        //set defaults
        this.outputWriter = new OutputConsole();
        this.outputWriter.setFormat( new TextFormat() );
        this.aviationGraph = new AviationGraph();
    }

    public void setOutputFormat(OutputFormater outputFormat) {
        this.outputWriter.setFormat(outputFormat);
    }

    public void changeOutput(OutputWriter writer) {
        this.outputWriter.setFormat(writer.getFormat());
        this.outputWriter = writer;
    }

    public void getBestPath(String originString, String destinationString, String priotityString) {
        //llamada al dijkstra

        //cuando termina manda
        this.outputFlightPath(this.forTestingGetFlights());
    }

    /**
     * Send flights to selected output with current output format.
     *
     * @param flights Iterable<Flights>
     */
    private void outputFlightPath(Iterable<Flight> flights) {
        Double price = 0.0;
        Double flightTime = 0.0;
        Double totalTime = 0.0;

        //Start writer
        this.outputWriter.start();

        //Process Flights
        for (Flight flight : flights ) {
            this.outputWriter.writeFlight(flight);
            price += flight.getPrice();
            flightTime = flight.getFlightTime();
        }

        //Process Headers
        this.outputWriter.writeHeader(price, flightTime, totalTime);

        //Close Writer
        this.outputWriter.finish();
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
}
