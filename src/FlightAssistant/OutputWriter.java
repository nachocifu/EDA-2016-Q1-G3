package FlightAssistant;


public interface OutputWriter {

    public void start();

    public void finish();

    public void writeFlight(Flight flight);

    public void setFormat(OutputFormat format);

    public OutputFormat getFormat();

    public void writeNotFound();

    public void writeHeader(Float price, Long flightTime, Long totalTime);

}
