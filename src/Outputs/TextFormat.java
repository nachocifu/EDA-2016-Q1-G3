package Outputs;

import FlightAssistant.Airport;
import FlightAssistant.Flight;

public class TextFormat implements OutputFormater {

    @Override
    public String write(Flight flight) {

        StringBuilder sb = new StringBuilder();

        sb.append(flight.getOrigin())
                .append("#")
                .append(flight.getAirline())
                .append("#")
                .append(flight.getFlightNumber())
                .append("#")
                .append(flight.getDestination())
                .append(System.lineSeparator());

        return sb.toString();
    }

    @Override
    public String writeNotFound() {
        return "NotFound" + System.lineSeparator();
    }

    @Override
    public String writeHeader(Double price, Double flightTime, Double totalTime) {
        StringBuilder sb = new StringBuilder();

        sb.append("Precio#")
                .append(price)
                .append(System.lineSeparator())
                .append("TiempoVuelo#")
                .append(flightTime)
                .append(System.lineSeparator())
                .append("TiempoTotal#")
                .append(totalTime)
                .append(System.lineSeparator())
                .append(System.lineSeparator());

        return sb.toString();
    }

    @Override
    public String write(Airport airport) {
        StringBuilder sb = new StringBuilder();

        sb.append(airport.getCode())
                .append("#Lat:")
                .append(airport.getLatitude())
                .append("#Longitud:")
                .append(airport.getLongitude())
                .append(System.lineSeparator());

        return sb.toString();
    }

    @Override
    public String writeErrorFileHandling(String pathString) {
        StringBuilder sb = new StringBuilder();

        sb.append("***********").append(System.lineSeparator())
                .append("ERROR: Unable to handle file.").append(System.lineSeparator())
                .append("File: ").append(pathString).append(System.lineSeparator())
                .append("***********").append(System.lineSeparator());

        return sb.toString();
    }

    @Override
    public String writeErrorsOnFile(String pathString) {
        StringBuilder sb = new StringBuilder();

        sb.append("*** WARNING: Found inconsistencies on file ***");

        return sb.toString();
    }

    @Override
    public String writeErrorSaving() {
        StringBuilder sb = new StringBuilder();

        sb.append("***********").append(System.lineSeparator())
                .append("ERROR: Unable to save current state").append(System.lineSeparator())
                .append("***********").append(System.lineSeparator());

        return sb.toString();
    }

    @Override
    public String writeUnableToLoadState() {

        StringBuilder sb = new StringBuilder();

        sb.append("*** WARNING: Unable to recover saved state ***");

        return sb.toString();

    }
}
