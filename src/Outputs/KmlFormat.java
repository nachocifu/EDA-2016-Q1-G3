package Outputs;

import FlightAssistant.Airport;
import FlightAssistant.Flight;


public class KmlFormat implements OutputFormater {
    @Override
    public String write(Flight flight) {
        StringBuilder sb = new StringBuilder();

        sb.append("<Placemark>").append( System.lineSeparator() )
                .append("<name>" ).append( flight.getCode() ).append( "</name>" ).append( System.lineSeparator() )
                .append("<description>").append(System.lineSeparator())
                .append("Origin Airport: ").append(flight.getOrigin().getCode()).append(System.lineSeparator())
                .append("Destination Airport: ").append(flight.getDestination().getCode()).append(System.lineSeparator())
                .append("</description>").append(System.lineSeparator())
                .append("<styleUrl>#lineStyle</styleUrl>").append(System.lineSeparator())
                .append("<LineString>").append(System.lineSeparator())
                .append("<tessellate>1</tessellate>").append(System.lineSeparator())
                .append("<altitudeMode>relativeToGround</altitudeMode>").append(System.lineSeparator())
                .append("<gx:altitudeOffset>10</gx:altitudeOffset>").append(System.lineSeparator())
                .append("<coordinates>").append(System.lineSeparator())
                .append(flight.getOrigin().getLatitude()).append(",").append(flight.getOrigin().getLongitude()).append(System.lineSeparator())
                .append(flight.getDestination().getLatitude()).append(",").append(flight.getDestination().getLongitude()).append(System.lineSeparator())
                .append("</coordinates>").append(System.lineSeparator())
                .append("</LineString>").append(System.lineSeparator())
                .append("</Placemark>").append( System.lineSeparator() );

        return sb.toString();
    }

    @Override
    public String writeNotFound() {
        return "";
    }

    @Override
    public String writeHeader(Double price, Double flightTime, Double totalTime) {
        StringBuilder sb = new StringBuilder();

        sb.append(this.writeHeader());

        sb.append("<description>").append(System.lineSeparator())
                .append("Precio: ").append(price).append(System.lineSeparator())
                .append("Tiempo de Vuelo: ").append(flightTime).append(System.lineSeparator())
                .append("Tiempo total: ").append(totalTime).append(System.lineSeparator())
                .append("</description>").append(System.lineSeparator());

        return sb.toString();
    }

    @Override
    public String writeHeader() {
        StringBuilder sb = new StringBuilder();

        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append( System.lineSeparator() )
                .append( "<kml xmlns=\"http://www.opengis.net/kml/2.2\" xmlns:gx=\"http://www.google.com/kml/ext/2.2\">").append( System.lineSeparator() )
                .append( "<Document>" ).append( System.lineSeparator() )
                .append("<name>Flight Assistant Results</name>").append(System.lineSeparator())
                .append("<Style id=\"lineStyle\">").append(System.lineSeparator())
                .append("<LineStyle>").append(System.lineSeparator())
                .append("<color>7fff00ff</color>").append(System.lineSeparator())
                .append("<width>4</width>").append(System.lineSeparator())
                .append("</LineStyle>").append(System.lineSeparator())
                .append("</Style>").append(System.lineSeparator());

        return sb.toString();
    }

    @Override
    public String write(Airport airport) {
        StringBuilder sb = new StringBuilder();

        sb.append("<Placemark>").append( System.lineSeparator() )
                .append( "<name>" ).append( airport.getCode() ).append( "</name>" ).append( System.lineSeparator() )
                .append( "<Point>" ).append( System.lineSeparator() )
                .append( "<coordinates>" ).append( airport.getLatitude() ).append(",").append( airport.getLongitude() ).append( "</coordinates>" ).append( System.lineSeparator() )
                .append( "</Point>" ).append( System.lineSeparator() )
                .append("</Placemark>").append( System.lineSeparator() );

        return sb.toString();
    }

    @Override
    public String writeErrorFileHandling(String pathString) {
        return "";
    }

    @Override
    public String writeErrorsOnFile(String pathString) {
        return "";
    }

    @Override
    public String writeErrorSaving() {
        return "";
    }

    @Override
    public String writeUnableToLoadState() {
        return "";
    }

    @Override
    public String writeFooter() {
        StringBuilder sb = new StringBuilder();

        sb.append("</Document>").append(System.lineSeparator())
                .append("</kml>").append(System.lineSeparator());

        return sb.toString();
    }
}
