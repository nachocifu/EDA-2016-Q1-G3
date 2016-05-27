package Outputs;


public interface OutputWriter {
    public void setFormat(OutputFormat format);
    public void start();
    public OutputFormat getFormat();
    public void finish();
}
