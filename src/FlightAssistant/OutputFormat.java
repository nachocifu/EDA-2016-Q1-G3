package FlightAssistant;

public interface OutputFormat {

    public String formatFlightString(Flight flight);

    public String alertNotFound();

    public String writeHeader(Float price, Long flightTime, Long totalTime);
}
