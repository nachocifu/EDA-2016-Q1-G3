package Outputs;

import FlightAssistant.Flight;

public class OutputConsole implements OutputWriter {

    private OutputFormater outputFormat;
    private StringBuilder builderHeaders;
    private StringBuilder builderFlights;
    private Boolean noResults = false;

    @Override
    public void start() {
        this.builderHeaders = new StringBuilder();
        this.builderFlights = new StringBuilder();
    }

    @Override
    public void finish() {
        //Check if builder has been started and flag is down
        if ( this.builderFlights == null || this.builderHeaders ==null ) {
            this.start();
            this.noResults = false;
        }

        System.out.println( this.builderHeaders.toString() + this.builderFlights.toString() );
        this.builderFlights = null;
        this.builderHeaders = null;
    }

    public void writeFlight(Flight flight) {
        //Check if builder has been started and flag is down
        if ( this.builderFlights == null || this.builderHeaders ==null || this.noResults ) {
            this.start();
            this.noResults = false;
        }

        this.builderFlights.append(this.outputFormat.writeFlight(flight));
    }

    @Override
    public OutputFormater getFormat() {
        return this.outputFormat;
    }

    public void writeNotFound() {
        //Clear builders set message and raise flag
        this.start();
        this.noResults = true;

        this.builderHeaders.append(outputFormat.alertNotFound());
    }

    public void writeHeader(Double price, Double flightTime, Double totalTime) {
        //Check if builder has been started and flag is down
        if ( this.builderFlights == null || this.builderHeaders ==null || this.noResults ) {
            this.start();
            this.noResults = false;
        }

        this.builderHeaders.append(outputFormat.writeHeader(price, flightTime, totalTime));
    }

    @Override
    public void setFormat(OutputFormater format) {
        this.outputFormat = format;
    }
}
