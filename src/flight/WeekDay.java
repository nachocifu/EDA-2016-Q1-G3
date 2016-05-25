package flight;


public enum WeekDay {
    MONDAY(0), TUESDAY(1), WENDSDAY(2), THURSDAY(3), FRIDAY(4), SATURDAY(5), SUNDAY(6);

    private int numDay;

    public static WeekDay getWeekDay(String string){
        switch (string){
            case "Lu":
                return MONDAY;
            case "Ma":
                return TUESDAY;
            case "Mi":
                return WENDSDAY;
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

}
