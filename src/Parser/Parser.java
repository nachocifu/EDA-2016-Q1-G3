package Parser;

import FlightAssistant.WeekDay;

import static FlightAssistant.WeekDay.MONDAY;

public abstract class Parser<E> {

    public abstract Boolean parse(String[] params, GraphManager consultor);

    public Double stringDurationTimeToDouble(String timeString) {
        Double timeInMinutes;
        if(timeString.indexOf("h") != -1) {
            String hours = timeString.substring(0, timeString.indexOf("h") - 1);
            String minutes = timeString.substring(timeString.indexOf("h") + 1, timeString.indexOf("m") - 1);
            timeInMinutes = Double.parseDouble(hours + ".0")*60 + Double.parseDouble(minutes + ".0");
        }
        else {
            String minutes = timeString.substring(0, timeString.indexOf("m") - 1);
            timeInMinutes = Double.parseDouble(minutes);
        }
        return timeInMinutes;
    }

    public Double stringDepartureTimeToDouble(String timeString, WeekDay weekday) {
        Double timeInMinutes;
        String[] ary = timeString.split(":");
        String hours = ary[0] + ".0";    //Se agrega el .0 para que pueda parsearse como Double
        String minutes = ary[1] + ".0";  //Se agrega el .0 para que pueda parsearse como Double
        timeInMinutes = Double.parseDouble(hours)*60 + Double.parseDouble(minutes) + weekday.distanceInMinutes(MONDAY);
        return timeInMinutes;
    }

    public static String[] toStringArray (Object[] objectArray) {
        String [] stringArray = new String[objectArray.length];
        int i = 0;
        for(Object each: objectArray) {
            stringArray[i] = (String)each;
            i++;
        }
        return stringArray;
    }
}
