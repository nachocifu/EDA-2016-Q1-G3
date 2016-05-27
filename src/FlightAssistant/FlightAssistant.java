package FlightAssistant;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import Outputs.OutputConsole;
import Outputs.OutputFormat;
import Outputs.OutputWriter;

public class FlightAssistant {

    private AviationGraph aviationGraph;
    private OutputWriter outputWriter;



    public FlightAssistant(){
        //set defaults
        this.outputWriter = new OutputConsole();
        this.aviationGraph = new AviationGraph();
    }


    public void setOutputFormat(OutputFormat outputFormat) {
        this.outputWriter.setFormat(outputFormat);
    }

    public void changeOutput(OutputWriter writer) {
        this.outputWriter.setFormat(writer.getFormat());
        this.outputWriter = writer;
    }


}
