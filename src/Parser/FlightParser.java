package Parser;

import FlightAssistant.Airport;
import FlightAssistant.Flight;
import FlightAssistant.WeekDay;

/**
 * Created by nacho on 6/7/16.
 */
public class FlightParser extends Parser<Flight> {

    private static int PARAMS_ON_FLIGHT = 8;
    private String DEFAULT_SAVE = "src/Storage/saveFlight.txt";

    public String getDEFAULT_SAVE() {
        return DEFAULT_SAVE;
    }

    @Override
    public Boolean parse(String[] params, GraphManager manager) {

        Boolean inconsistencies = true;

        if (params.length != PARAMS_ON_FLIGHT) return inconsistencies;

        String flightTimeString = params[6];
        String departureTimeString = params[5];
        String departureDayString = params[2];
        String destinationString = params[4];
        String originString = params[3];
        String airline = params[0];
        String flightNumberString = params[1];
        String priceString = params[7];

        Double flightTime, departureTime, price;
        WeekDay day;
        Airport destination, origin;
        Integer flightNumber;

        if (departureDayString == null) return inconsistencies;

        try {

            flightTime = stringDurationTimeToDouble(flightTimeString);
            price = new Double(priceString);

            flightNumber = new Integer(flightNumberString);

            destination = manager.findAirportByCode(destinationString);
            origin = manager.findAirportByCode(originString);

        } catch (NumberFormatException e) {
           return null;
        }
        //Validations
        if ( destination == null || origin == null || airline  == null ) return inconsistencies;

        //Parse Days
        for (String dayString : departureDayString.split("-")){
            day = WeekDay.getWeekDay(dayString);
            if (day != null) {
                departureTime = stringDepartureTimeToDouble(departureTimeString, day );
                if (departureTime != null)
                    manager.add( new Flight(flightTime, departureTime, day, destination, origin, airline, flightNumber, price) );
                else
                    inconsistencies = false;
            } else
                inconsistencies = false;
        }

        return !inconsistencies;
    }

    @Override
    public String parseAdditionalParams(String[] params, GraphManager manager){

        //Check params has file
        if (params.length == 1 ) return params[0];

        switch (params[1]){
            case "--replace-flights":
                manager.deleteAllFlights();
                break;
            case "--append-flights":
                //do nothing and continue
                break;
            default:
                //invalid param. abort!
                return null;
        }

        return params[0];
    }

    @Override
    public String saveFormat(Flight each) {
        StringBuilder sb = new StringBuilder();

        sb.append(each.getAirline())
                .append("#")
                .append(each.getFlightNumber())
                .append("#")
                .append(each.getDepartureDay().getCode())
                .append("#")
                .append(each.getOrigin().getCode())
                .append("#")
                .append(each.getDestination().getCode())
                .append("#")
                .append(Parser.time(each.getDepartureTime()))
                .append("#")
                .append(Parser.timeInMinutesToHoursAndMinutes(each.getFlightTime()))
                .append("#")
                .append(each.getPrice())
                .append(System.lineSeparator());

        return sb.toString();
    }

}
