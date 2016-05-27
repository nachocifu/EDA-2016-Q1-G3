package FlightAssistant;


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

    public void writeFlight(Flight flight) {
        //Aca estoy escribiendo con formato text habria que armar algo
        // con el objeto format para diferenciar KML y text usando interfaces

        //Check if builder has been started
        if ( this.builder == null ) this.start();

        this.builder.append(this.outputFormat.formatFlightString(flight));
    }

    @Override
    public OutputFormat getFormat() {
        return this.outputFormat;
    }

    public void writeNotFound() {
        //Check if builder has been started
        if ( this.builder == null ) this.start();

        this.builder.append(outputFormat.alertNotFound());
    }

    public void writeHeader(Float price, Long flightTime, Long totalTime) {
        //Check if builder has been started
        if ( this.builder == null ) this.start();

        this.builder.append(outputFormat.writeHeader(price, flightTime, totalTime));
    }

    @Override
    public void setFormat(OutputFormat format) {
        this.outputFormat = format;
    }
}
