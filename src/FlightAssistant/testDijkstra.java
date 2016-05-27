/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlightAssistant;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Alex
 */
public class testDijkstra {
    public static LinkedList<Stopover> Dijkstra(Airport origin, Airport destination) {
        Queue<Stopover> priorityQueue = new LinkedList<Stopover>();
        HashSet<Stopover> visited = new HashSet<Stopover> ();
        Stopover currentStop = null;
        priorityQueue.add(new Stopover(origin, null, 0));
        if(origin == destination) {
            return null;
        }
        while(!priorityQueue.isEmpty())
            currentStop = priorityQueue.poll();
            visited.add(currentStop);
            for(Flight flight: currentStop.getAirport().getOutboundFlights()){
                if(visited.contains(new Stopover(currentStop.getAirport(), flight, flight.getFlightTime()+currentStop.getCurrentFlightTime())))
                priorityQueue.offer(new Stopover(currentStop.getAirport(), flight, flight.getFlightTime()+currentStop.getCurrentFlightTime()));
            }
            
    }
}
