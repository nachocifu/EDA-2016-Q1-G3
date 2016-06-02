package Outputs;

import FlightAssistant.Flight;

public class OutputFile implements OutputWriter {

    public OutputFile( String file){
        //guardo el file
    }

    @Override
    public void start() {
        //crear buffer para escribir a archivo
    }

    @Override
    public void finish() {
        //dumpear el resto de la buffer q no se haya pasado con el automatico por haberse llenado MAX buffer
    }

    @Override
    public void writeFlight(Flight flight) {

    }

    @Override
    public void writeNotFound() {

    }

    @Override
    public void writeHeader(Double price, Double flightTime, Double totalTime) {

    }

    @Override
    public OutputFormater getFormat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFormat(OutputFormater format) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    

}
