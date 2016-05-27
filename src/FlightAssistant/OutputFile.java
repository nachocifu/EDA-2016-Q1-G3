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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFormat(OutputFormat format) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    

}
