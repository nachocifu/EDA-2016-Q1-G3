package FlightAssistant;


import java.io.Serializable;

public enum WeekDay implements Serializable{
    MONDAY(0,"Lu"), TUESDAY(1,"Ma"), WEDNESDAY(2,"Mi"), THURSDAY(3, "Ju"), FRIDAY(4, "Vi"), SATURDAY(5, "Sa"), SUNDAY(6, "Do");

	private static Double HOURS = 24.0;
	private static Double MINUTES = 60.0;
	
    private Integer numDay;
    private String code;

    public String getCode() {
        return code;
    }

    public static WeekDay getWeekDay(String string){
        switch (string){
            case "Lu":
                return MONDAY;
            case "Ma":
                return TUESDAY;
            case "Mi":
                return WEDNESDAY;
            case "Ju":
                return THURSDAY;
            case "Vi":
                return FRIDAY;
            case "Sa":
                return SATURDAY;
            case "Do":
                return SUNDAY;
            default:
                return null;
        }
    }

    WeekDay(int numDay, String code) {
        this.numDay = numDay;
        this.code = code;
    }

    int distanceInDays(WeekDay other) {
        return ( other.numDay - this.numDay );
    }
    
    public Double distanceInMinutes(WeekDay other){
    	return distanceInDays(other) * HOURS * MINUTES;
    }

    public Double getDaysInMinutes() {
        return this.numDay*HOURS*MINUTES;
    }

    public static Double getMinutesInAWeek() {
         return (24.0 * 60.0 * 7.0);
    }
    
    public Integer getNumDay() {
        return this.numDay;
    }

    public static Double minutesInDay() {
        return HOURS*MINUTES;
    }
}
