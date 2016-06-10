package Parser;

import FlightAssistant.WeekDay;

import static FlightAssistant.WeekDay.MONDAY;

public abstract class Parser<E> {

    public abstract Boolean parse(String[] params, GraphManager consultor);

    public Double stringDurationTimeToDouble(String timeString) {
        Double timeInMinutes;

        if (timeString.indexOf("m") != (timeString.length() -1)) return null;

        try {

            if (timeString.indexOf('h') > 0) {
                String hours = timeString.substring(0, timeString.indexOf('h'));
                String minutes = timeString.substring(timeString.indexOf('h') + 1, timeString.indexOf('m'));
                timeInMinutes = Double.parseDouble(hours + ".0") * 60 + Double.parseDouble(minutes + ".0");
            } else {
                String minutes = timeString.substring(0, timeString.indexOf('m'));
                timeInMinutes = Double.parseDouble(minutes + ".0");
            }
            return timeInMinutes;
        } catch (NumberFormatException e) {
            return null;
        }

    }

    public Double stringDepartureTimeToDouble(String timeString, WeekDay weekday) {
        Double timeInMinutes;

        if (timeString.indexOf(";") < 0) return null;

        try {
            String[] ary = timeString.split(":");

            if (ary[0].length() > 2 || ary[1].length() > 2) return null;

            String hours = ary[0] + ".0";    //Se agrega el .0 para que pueda parsearse como Double
            String minutes = ary[1] + ".0";  //Se agrega el .0 para que pueda parsearse como Double

            timeInMinutes = Double.parseDouble(hours) * 60 + Double.parseDouble(minutes);

            if (timeInMinutes > WeekDay.minutesInDay()) return null;

            timeInMinutes += weekday.distanceInMinutes(MONDAY);

        } catch (NumberFormatException e) {
            return null;
        }
        return timeInMinutes;
    }

    public static String timeInMinutesToHoursAndMinutes(Double timeInMinutes) {
        String hours;
        String minutes;
        Double hoursDouble = timeInMinutes/60.0;
        hours = Double.toString(hoursDouble);
        hours = hours.substring(0, hours.indexOf('.'));
        hoursDouble = new Double(hours);
        Double minutesDouble = timeInMinutes - hoursDouble*60.0;
        minutes = Double.toString(minutesDouble);
        minutes = minutes.substring(0, minutes.indexOf('.'));
        return hours+"h"+minutes+"m"; 
    }
    
    public static WeekDay[] parseWeekdays(String weekdays) {
        if(weekdays == null) {
            return null;
        }
        String [] ary = weekdays.split("-"); //array of all posible departure days.
        WeekDay [] weekdaysArray = new WeekDay[ary.length];
        int i = 0;
        for(String each: ary)
            weekdaysArray[i] = (WeekDay.getWeekDay(each));

        return weekdaysArray;
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

    public abstract String parseAdditionalParams(String[] params, GraphManager manager);
}
