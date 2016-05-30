/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlightAssistant;

import static FlightAssistant.WeekDay.*;
import Stopovers.Stopover;

/**
 *
 * @author Alex
 */
public class DijkstraTest {
    public static void main(String[] args) {
        Airport s = new Airport("s", Float.NaN, Float.NaN);
        Airport a = new Airport("a", Float.NaN, Float.NaN);
        Airport b = new Airport("b", Float.NaN, Float.NaN);
        Airport c = new Airport("c", Float.NaN, Float.NaN);
        Airport d = new Airport("d", Float.NaN, Float.NaN);
        s.addFlight(new Flight(11.0, 5.0, MONDAY, a, s, "StoA", Integer.MIN_VALUE, 1.0));
        s.addFlight(new Flight(11.0, 5.0, MONDAY, b, s, "StoB", Integer.MIN_VALUE, 4.0));
        s.addFlight(new Flight(11.0, 5.0, MONDAY, c, s, "StoC", Integer.MIN_VALUE, 2.0));
        s.addFlight(new Flight(30.0, 5.0, MONDAY, d, s, "StoD", Integer.MIN_VALUE, 7.0));
        a.addFlight(new Flight(11.0, 5.0, MONDAY, b, a, "AtoB", Integer.MIN_VALUE, 3.0));
        a.addFlight(new Flight(11.0, 5.0, MONDAY, c, a, "AtoC", Integer.MIN_VALUE, 9.0));
        a.addFlight(new Flight(11.0, 16.0, MONDAY, d, a, "AtoD", Integer.MIN_VALUE, 3.0));
        b.addFlight(new Flight(11.0, 5.0, MONDAY, a, b, "BtoA", Integer.MIN_VALUE, 3.0));
        b.addFlight(new Flight(11.0, 5.0, MONDAY, c, b, "BtoC", Integer.MIN_VALUE, 2.0));
        b.addFlight(new Flight(11.0, 5.0, WEDNESDAY, d, b, "BtoD", Integer.MIN_VALUE, 6.0));
        c.addFlight(new Flight(11.0, 5.0, MONDAY, a, c, "CtoA", Integer.MIN_VALUE, 9.0));
        c.addFlight(new Flight(11.0, 5.0, MONDAY, b, c, "CtoB", Integer.MIN_VALUE, 2.0));
        c.addFlight(new Flight(11.0, 5.0, MONDAY, d, c, "CtoD", Integer.MIN_VALUE, 4.0));
        d.addFlight(new Flight(11.0, 5.0, MONDAY, a, d, "DtoA", Integer.MIN_VALUE, 4.0));
        d.addFlight(new Flight(11.0, 5.0, MONDAY, b, d, "DtoB", Integer.MIN_VALUE, 6.0));
        d.addFlight(new Flight(11.0, 5.0, MONDAY, c, d, "DtoC", Integer.MIN_VALUE, 4.0));
        Stopover sa = Dijkstra.getShortestPathFromAToBWithFixedWeights(s, a, null);
        sa.printStopover();
        a.unTag();
        b.unTag();
        c.unTag();
        d.unTag();
        s.unTag();
        Stopover sb = Dijkstra.getShortestPathFromAToBWithFixedWeights(s, b, null);
        sb.printStopover();
        a.unTag();
        b.unTag();
        c.unTag();
        d.unTag();
        s.unTag();
        Stopover sc = Dijkstra.getShortestPathFromAToBWithFixedWeights(s, c, null);
        if(sc != null) {sc.printStopover();}
        a.unTag();
        b.unTag();
        c.unTag();
        d.unTag();
        s.unTag();
        Stopover sd = Dijkstra.getShortestPathFromAToBWithTotalTime(s, d);
        
        
        
        if(sd != null) {sd.printStopover();}
    }
}