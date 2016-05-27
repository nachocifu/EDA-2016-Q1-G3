/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlightAssistant;

/**
 *
 * @author Alex
 */
public class Stopover {
    private Airport airport;
    private Flight inboundFlight;
    private long currentFlightTime;
    
    public Stopover(Airport airport, Flight flight, long weightInFlightTime){
        this.airport = airport;
        this.inboundFlight = flight;
        this.currentFlightTime = weightInFlightTime;
    }
    public Stopover(Stopover stopover){
        this.airport = stopover.airport;
        this.inboundFlight = stopover.inboundFlight;
        this.currentFlightTime = stopover.currentFlightTime;
    }

    public Airport getAirport(){
        return this.airport;
    }
    
    public long getCurrentFlightTime(){
        return currentFlightTime;
    }
    public boolean equals(Object o){
        Stopover o1 = (Stopover)o;
        return this.airport.equals(o1.getAirport());
    }
}
