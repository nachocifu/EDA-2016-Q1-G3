package Outputs;


import FlightAssistant.Airport;
import FlightAssistant.Flight;

public interface OutputFormater {

    String write(Flight flight);

    String writeNotFound();

    String writeHeader(Double price, Double flightTime, Double totalTime);

    String writeHeader();

    String write(Airport airport);

    String writeErrorFileHandling(String pathString);

    String writeErrorsOnFile(String pathString);

    String writeErrorSaving();

    String writeUnableToLoadState();

    String writeFooter();
}
