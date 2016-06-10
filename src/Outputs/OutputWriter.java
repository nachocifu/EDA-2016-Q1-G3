package Outputs;


import FlightAssistant.Airport;
import FlightAssistant.Flight;

public interface OutputWriter {

    void setFormat(OutputFormater format);

    OutputFormater getFormat();

    Boolean start();
    Boolean finish();

    Boolean write(Flight flight);
    Boolean write(Airport airport);

    Boolean writeNotFound();

    Boolean writeHeader(Double price, Double flightTime, Double totalTime);
    Boolean writeHeader();

    Boolean writeFooter();

    Boolean okForWriting();

    void discardAll();

    Boolean writeErrorFileHandling(String pathString);

    Boolean writeErrorsOnFile(String pathString);

    Boolean writeErrorSaving();

    Boolean writeUnableToLoadState();

}
