package Outputs;


import FlightAssistant.Flight;

public interface OutputWriter {

    public void setFormat(OutputFormater format);
    public OutputFormater getFormat();

    public void start();
    public void finish();

    public void writeFlight(Flight flight);

    public void writeNotFound();

    public void writeHeader(Double price, Double flightTime, Double totalTime);

}
