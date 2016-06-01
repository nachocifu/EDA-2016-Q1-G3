/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Priorities;

import FlightAssistant.Flight;
import Stopovers.Stopover;
import java.util.LinkedList;

/**
 *
 */
public class PriorityPrice implements Priority{
    
    public PriorityPrice() {
    }

    @Override
    public Double getWeightByPriority(Stopover currentStop, Flight flightToEvaluate, LinkedList<Flight> prevFlights) {
       return flightToEvaluate.getPrice() + currentStop.getWeight();
    }
    
}

