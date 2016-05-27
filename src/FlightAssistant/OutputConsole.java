package FlightAssistant;

import com.sun.glass.ui.SystemClipboard;
import com.sun.tools.doclets.internal.toolkit.util.DocFinder;


public class OutputConsole implements OutputWriter {

    private OutputFormat outputFormat;
    private StringBuilder builder;

    @Override
    public void start() {
        this.builder = new StringBuilder();
    }

    @Override
    public void finish() {
        System.out.println(this.builder);
        this.builder = null;
    }

    @Override
    public void writeFlight(Flight flight) {
        //Aca estoy escribiendo con formato text habria que armar algo
        // con el objeto format para diferenciar KML y text usando interfaces

        //Check if builder has been started
        if ( this.builder == null ) this.start();

        this.builder.append("origin" +
                " # " + flight.getAirline() +
                " # " + flight.getFlightNumber() +
                " # " + flight.getDestination() +
                System.lineSeparator());
    }

    @Override
    public OutputFormat getFormat() {
        return this.outputFormat;
    }

    @Override
    public void writeNotFound() {
        //Check if builder has been started
        if ( this.builder == null ) this.start();

        this.builder.append("NotFound");
    }

    @Override
    public void writeHeader(Float price, Long flightTime, Long totalTime) {
        //Check if builder has been started
        if ( this.builder == null ) this.start();

        this.builder.append("Precio#" + price + System.lineSeparator() +
                            "TiempoVuelo#" + flightTime + System.lineSeparator() +
                            "TiempoTotal#" + totalTime + System.lineSeparator() +
                            System.lineSeparator());
    }

    @Override
    public void setFormat(OutputFormat format) {
        this.outputFormat = format;
    }
}
