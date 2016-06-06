package Outputs;

import FlightAssistant.Airport;
import FlightAssistant.Flight;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by nacho on 6/4/16.
 */
public class KmlFormat implements OutputFormater {
    @Override
    public String write(Flight flight) {
        throw new NotImplementedException();
    }

    @Override
    public String writeNotFound() {
        throw new NotImplementedException();
    }

    @Override
    public String writeHeader(Double price, Double flightTime, Double totalTime) {
        StringBuilder sb = new StringBuilder();

        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append( System.lineSeparator() )
                .append( "<kml xmlns=\"http://www.opengis.net/kml/2.2\">").append( System.lineSeparator() )
                .append( "<Document>" ).append( System.lineSeparator() );

        return sb.toString();
    }

    @Override
    public String write(Airport airport) {
        StringBuilder sb = new StringBuilder();

        sb.append("<Placemark>").append( System.lineSeparator() )
                .append( "<name>" ).append( airport.getCode() ).append( "</name>" ).append( System.lineSeparator() )
                .append( "<Point>" ).append( System.lineSeparator() )
                .append( "<coordinates>" ).append( airport.getLatitude() ).append(",").append( airport.getLongitude() ).append( "</coordinates>" ).append( System.lineSeparator() )
                .append( "<Point>" ).append( System.lineSeparator() )
                .append("<Placemark>").append( System.lineSeparator() );

        return sb.toString();
    }

    @Override
    public String writeErrorFileHandling(String pathString) {
        return null;
    }

    @Override
    public String writeErrorsOnFile(String pathString) {
        return null;
    }
}
