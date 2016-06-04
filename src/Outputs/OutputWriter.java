package Outputs;


import FlightAssistant.Flight;

public interface OutputWriter {

    public void setFormat(OutputFormater format);
    public OutputFormater getFormat();

    public Boolean start();
    public Boolean finish();

    public Boolean writeFlight(Flight flight);

    public Boolean writeNotFound();

    public Boolean writeHeader(Double price, Double flightTime, Double totalTime);

    public Boolean okForWriting();

    public void discardAll();

}
