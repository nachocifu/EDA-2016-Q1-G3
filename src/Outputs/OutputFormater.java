package Outputs;


import FlightAssistant.Flight;

public interface OutputFormater {

    public String writeFlight(Flight flight);

    public String writeNotFound();

    public String writeHeader(Double price, Double flightTime, Double totalTime);
}
