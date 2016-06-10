package Parser;


import FlightAssistant.Airport;


public class AirPortParser extends Parser<Airport> {

    private static int PARAMS_ON_AIRPORT = 3;

    @Override
    public Boolean parse(String[] params, GraphManager manager) {

        Boolean inconsistencies = true;

        if (params.length != PARAMS_ON_AIRPORT) return inconsistencies;

        String code = params[0];
        String lat = params[1];
        String lon = params[2];
        Float latitud, longitud;

        try {

            latitud = new Float(lat);
            longitud = new Float(lon);

        } catch (NumberFormatException e) {
            return inconsistencies;
        }

        //Validations
        if ( code == null || latitud == null || longitud == null ) return inconsistencies;


        manager.add( new Airport(code, latitud, longitud) );
        return !inconsistencies;
    }

    @Override
    public String parseAdditionalParams(String[] params, GraphManager manager){

        //Check params has file
        if (params.length == 1 ) return params[0];

        switch (params[1]){
            case "--replace-airports":
                manager.deleteAllAirports();
                break;
            case "--append-airports":
                //do nothing and continue
                break;
            default:
                //invalid param. abort!
                return null;
        }

        return params[0];
    }

}
