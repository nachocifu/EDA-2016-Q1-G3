package FlightAssistant;

enum OutputFormat {
    TEXT("text"), KML("KML");

    private String code;

    OutputFormat(String code) {
        this.code = code;
    }

    public static OutputFormat getFormatFromString(String string){
        switch (string){
            case "text":
                return TEXT;
            case "KML":
                return KML;
            default:
                return null;
        }
    }
}
