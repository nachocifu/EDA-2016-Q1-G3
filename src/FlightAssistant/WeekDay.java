package FlightAssistant;


enum WeekDay {
    MONDAY(0), TUESDAY(1), WEDNESDAY(2), THURSDAY(3), FRIDAY(4), SATURDAY(5), SUNDAY(6);

	private Double HOURS = 24.0;
	private Double MINUTES = 60.0;
	
    private int numDay;

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

    WeekDay(int numDay) {
        this.numDay = numDay;
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

}
