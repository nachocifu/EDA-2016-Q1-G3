package Outputs;


import FlightAssistant.Airport;
import FlightAssistant.Flight;

public interface OutputFormater {

    public String write(Flight flight);

    public String writeNotFound();

    public String writeHeader(Double price, Double flightTime, Double totalTime);

    public String write(Airport airport);

    public String writeErrorFileHandling(String pathString);

    public String writeErrorsOnFile(String pathString);

    public String writeErrorSaving();

    public String writeUnableToLoadState();
}
