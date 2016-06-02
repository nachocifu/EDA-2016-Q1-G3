package Outputs;


import FlightAssistant.Flight;

public interface OutputFormater {

    public String writeFlight(Flight flight);

    public String alertNotFound();

    public String writeHeader(Double price, Double flightTime, Double totalTime);
}
