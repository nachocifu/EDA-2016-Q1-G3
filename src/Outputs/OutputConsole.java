package Outputs;

import FlightAssistant.Airport;
import FlightAssistant.Flight;

public class OutputConsole implements OutputWriter {

    private OutputFormater outputFormat;
    private StringBuilder builder;
    private Boolean noResults = false;

    @Override
    public Boolean start() {
        this.builder = new StringBuilder();
        return true;
    }

    @Override
    public Boolean finish() {

        if ( this.builder != null ) {

            System.out.println( this.builder );

            this.builder = null;

        }

        return true;
    }

    public Boolean write(Flight flight) {

        if ( !okForWriting() ) return false;

        this.builder.append(this.outputFormat.write(flight));

        return true;
    }

    @Override
    public Boolean write(Airport airport) {

        if ( !okForWriting() ) return false;

        this.builder.append(this.outputFormat.write(airport));

        return true;

    }

    @Override
    public OutputFormater getFormat() {
        return this.outputFormat;
    }

    public Boolean writeNotFound() {

        if ( !okForWriting() ) return false;

        this.builder.append(outputFormat.writeNotFound());

        return true;

    }

    public Boolean writeHeader(Double price, Double flightTime, Double totalTime) {

        if ( !okForWriting() ) return false;
        
        this.builder.append(outputFormat.writeHeader(price, flightTime, totalTime));

        return true;

    }

    @Override
    public Boolean writeHeader() {

        if ( !okForWriting() ) return false;

        this.builder.append(outputFormat.writeHeader());

        return true;

    }

    @Override
    public Boolean writeFooter() {
        if ( !okForWriting() ) return false;

        this.builder.append(outputFormat.writeFooter());

        return true;
    }

    @Override
    public void setFormat(OutputFormater format) {
        if ( format != null )
            this.outputFormat = format;
    }

    @Override
    public Boolean okForWriting(){

        if ( this.builder == null )
            return this.start();

        return true;

    }

    @Override
    public void discardAll(){
        this.builder = null;
    }

    @Override
    public Boolean writeErrorFileHandling(String pathString) {
        if ( !okForWriting() ) return false;

        this.builder.append(outputFormat.writeErrorFileHandling(pathString));

        return true;

    }

    @Override
    public Boolean writeErrorsOnFile(String pathString) {

        if ( !okForWriting() ) return false;

        this.builder.append(outputFormat.writeErrorsOnFile(pathString));

        return true;

    }

    @Override
    public Boolean writeErrorSaving() {

        if ( !okForWriting() ) return false;

        this.builder.append(outputFormat.writeErrorSaving());

        return true;

    }

    @Override
    public Boolean writeUnableToLoadState() {

        if ( !okForWriting() ) return false;

        this.builder.append(outputFormat.writeUnableToLoadState());

        return true;

    }
}
