package Outputs;

import FlightAssistant.Airport;
import FlightAssistant.Flight;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OutputFile implements OutputWriter {

    private String path;
    private BufferedWriter writer;
    private OutputFormater outputFormat;

    public OutputFile(String path){
        this.path = path;
    }

    @Override
    public Boolean start() {
        //Create buffered writer
        try {
            writer = Files.newBufferedWriter(Paths.get(this.path));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public Boolean finish() {
        //If theres buffer flush and close
        if ( writer == null ) return true;

        try {
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public Boolean write(Flight flight) {
        //Check if writing is posible
        if ( ! okForWriting() ) return false;

        try {
            this.writer.write(this.outputFormat.write(flight));
            return true;
        } catch ( IOException e ) {
            return false;
        }

    }

    @Override
    public Boolean write(Airport airport) {
        //Check if writing is posible
        if ( ! okForWriting() ) return false;

        try {
            this.writer.write(this.outputFormat.write(airport));
            return true;
        } catch ( IOException e ) {
            return false;
        }
    }

    @Override
    public Boolean writeNotFound() {
        //Check if writing is posible
        if ( ! okForWriting() ) return false;

        try {
            this.writer.write(this.outputFormat.writeNotFound());
            return true;
        } catch ( IOException e ) {
            return false;
        }

    }

    @Override
    public Boolean writeHeader(Double price, Double flightTime, Double totalTime) {
        //Check if writing is posible
        if ( ! okForWriting() ) return false;

        try {
            this.writer.write(this.outputFormat.writeHeader(price,flightTime,totalTime));
            return true;
        } catch ( IOException e ) {
            return false;
        }

    }

    @Override
    public OutputFormater getFormat() {
        return this.outputFormat;
    }

    @Override
    public void setFormat(OutputFormater format) {
        if ( format != null )
            this.outputFormat = format;
    }
    
    @Override
    public Boolean okForWriting(){

        if ( this.writer == null )
            return this.start();
        else
            return true;
    }

    @Override
    public void discardAll(){
        this.writer = null;
    }

    @Override
    public Boolean writeErrorFileHandling(String pathString) {
        //Dont write errors to files
        return true;
    }

    @Override
    public Boolean writeErrorsOnFile(String pathString) {
        //Dont write errors to files
        return true;
    }
}
