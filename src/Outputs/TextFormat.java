package Outputs;

import FlightAssistant.Flight;

public class TextFormat implements OutputFormater {

    @Override
    public String writeFlight(Flight flight) {

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
}
