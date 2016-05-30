/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlightAssistant;

import static FlightAssistant.WeekDay.MONDAY;
import Stopovers.Stopover;

/**
 *
 * @author Alex
 */
public class testDijkstra {
    public static void main(String[] args) {
        Airport s = new Airport("s", Float.NaN, Float.NaN);
        Airport a = new Airport("a", Float.NaN, Float.NaN);
        Airport b = new Airport("b", Float.NaN, Float.NaN);
        Airport c = new Airport("c", Float.NaN, Float.NaN);
        Airport d = new Airport("d", Float.NaN, Float.NaN);
        s.addFlight(new Flight(Long.MIN_VALUE, Long.MAX_VALUE, MONDAY, a, s, "", Integer.MIN_VALUE, 1.0));
        s.addFlight(new Flight(Long.MIN_VALUE, Long.MAX_VALUE, MONDAY, b, s, "", Integer.MIN_VALUE, 4.0));
        s.addFlight(new Flight(Long.MIN_VALUE, Long.MAX_VALUE, MONDAY, c, s, "", Integer.MIN_VALUE, 2.0));
        s.addFlight(new Flight(Long.MIN_VALUE, Long.MAX_VALUE, MONDAY, d, s, "", Integer.MIN_VALUE, 7.0));
        a.addFlight(new Flight(Long.MIN_VALUE, Long.MAX_VALUE, MONDAY, b, a, "", Integer.MIN_VALUE, 3.0));
        a.addFlight(new Flight(Long.MIN_VALUE, Long.MAX_VALUE, MONDAY, c, a, "", Integer.MIN_VALUE, 9.0));
        a.addFlight(new Flight(Long.MIN_VALUE, Long.MAX_VALUE, MONDAY, d, a, "", Integer.MIN_VALUE, 4.0));
        b.addFlight(new Flight(Long.MIN_VALUE, Long.MAX_VALUE, MONDAY, a, b, "", Integer.MIN_VALUE, 3.0));
        b.addFlight(new Flight(Long.MIN_VALUE, Long.MAX_VALUE, MONDAY, c, b, "", Integer.MIN_VALUE, 2.0));
        b.addFlight(new Flight(Long.MIN_VALUE, Long.MAX_VALUE, MONDAY, d, b, "", Integer.MIN_VALUE, 6.0));
        c.addFlight(new Flight(Long.MIN_VALUE, Long.MAX_VALUE, MONDAY, a, c, "", Integer.MIN_VALUE, 9.0));
        c.addFlight(new Flight(Long.MIN_VALUE, Long.MAX_VALUE, MONDAY, b, c, "", Integer.MIN_VALUE, 2.0));
        c.addFlight(new Flight(Long.MIN_VALUE, Long.MAX_VALUE, MONDAY, d, c, "", Integer.MIN_VALUE, 4.0));
        d.addFlight(new Flight(Long.MIN_VALUE, Long.MAX_VALUE, MONDAY, a, d, "", Integer.MIN_VALUE, 4.0));
        d.addFlight(new Flight(Long.MIN_VALUE, Long.MAX_VALUE, MONDAY, b, d, "", Integer.MIN_VALUE, 6.0));
        d.addFlight(new Flight(Long.MIN_VALUE, Long.MAX_VALUE, MONDAY, c, d, "", Integer.MIN_VALUE, 4.0));
        Stopover sa = Dijkstra.algorithm(s, a, null);
        sa.printStopover();
        a.unTag();
        b.unTag();
        c.unTag();
        d.unTag();
        s.unTag();
        Stopover sb = Dijkstra.algorithm(s, b, null);
        sb.printStopover();
        a.unTag();
        b.unTag();
        c.unTag();
        d.unTag();
        s.unTag();
        Stopover sc = Dijkstra.algorithm(s, c, null);
        if(sc != null) {sc.printStopover();}
        a.unTag();
        b.unTag();
        c.unTag();
        d.unTag();
        s.unTag();
        Stopover sd = Dijkstra.algorithm(s, d, null);
        
        
        
        if(sd != null) {sd.printStopover();}
    }
}
