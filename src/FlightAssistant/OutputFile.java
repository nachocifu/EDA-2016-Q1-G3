package FlightAssistant;

public class OutputFile implements OutputWriter {

    public OutputFile( String file){
        //guardo el file
    }

    @Override
    public void start() {
        //crear buffer para escribir a archivo
    }

    @Override
    public void finish() {
        //dumpear el resto de la buffer q no se haya pasado con el automatico por haberse llenado MAX buffer
    }

    @Override
    public OutputFormat getFormat() {
        return null;
    }

    @Override
    public void writeNotFound() {

    }

    @Override
    public void writeHeader(Float price, Long flightTime, Long totalTime) {

    }

    @Override
    public void setFormat(OutputFormat format) {

    }

    @Override
    public void writeFlight(Flight flight) {
        //escribir vuelo al buffer en el formato correspondiente
    }
}
