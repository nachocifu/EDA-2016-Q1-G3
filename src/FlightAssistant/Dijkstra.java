package FlightAssistant;

import Priorities.Priority;
import java.util.PriorityQueue;

import Stopovers.Stopover;
import java.awt.BorderLayout;
import java.util.LinkedList;

public class Dijkstra {

    private static Double MIN = 0.0;

    public static Stopover getShortestPathFromAToBWithFixedWeights(Airport origin, Airport target, Priority priority) {
        long time = System.currentTimeMillis();
        if (origin == null || target == null) {
            return null;
        }
        //clearMarks();
        LinkedList<Flight> prevFlights = new LinkedList<Flight>();
        PriorityQueue<Stopover> queue = new PriorityQueue<>();
        Stopover dbgassist = null;
        queue.offer(new Stopover(origin, 0.0));
        while (!queue.isEmpty()) {
            Stopover currentStop = queue.poll();
            while (currentStop.getCurrentStop().isTagged() && !queue.isEmpty()) {
                currentStop = queue.poll();
            }
            if (currentStop.getCurrentStop().equals(target)) {
                return currentStop;
            }
            currentStop.getCurrentStop().tag();
            prevFlights = currentStop.getFlights();
            for (Flight each : currentStop.getCurrentStop().getOutboundFlights()) {
                if (!each.getDestination().isTagged()) {
                    dbgassist = new Stopover(prevFlights, each.getDestination(), each, priority.getWeightByPriority(currentStop, each, prevFlights));
                    queue.offer(dbgassist);
                }
            }
        }
        return null;
    }

    public static Stopover getShortestPathFromAToBWithFixedWeightsDepartingSpecificDays(Airport origin, Airport target, Priority priority, WeekDay[] weekdays) {
        long time = System.currentTimeMillis();
        if (origin == null || target == null) {
            return null;
        }
        //clearMarks();
        LinkedList<Flight> prevFlights = new LinkedList<Flight>();
        PriorityQueue<Stopover> queue = new PriorityQueue<>();
        Stopover dbgassist = null;
        queue.offer(new Stopover(origin, 0.0));
        Stopover currentStop = queue.poll();
        if(weekdays != null){
        if (currentStop.getCurrentStop().equals(target)) {
                    return currentStop;
                }
            currentStop.getCurrentStop().tag();
                prevFlights = currentStop.getFlights();
                for (Flight each : currentStop.getCurrentStop().getOutboundFlightsByDayOfDeparture(weekdays)) {
                    if (!each.getDestination().isTagged()) {
                        dbgassist = new Stopover(prevFlights, each.getDestination(), each, priority.getWeightByPriority(currentStop, each, prevFlights));
                        queue.offer(dbgassist);
                    }
                }
        }
        while (!queue.isEmpty()) {
            currentStop = queue.poll();
            while (currentStop.getCurrentStop().isTagged() && !queue.isEmpty()) {
                currentStop = queue.poll();
            }
            if (currentStop.getCurrentStop().equals(target)) {
                return currentStop;
            }
            currentStop.getCurrentStop().tag();
            prevFlights = currentStop.getFlights();
            for (Flight each : currentStop.getCurrentStop().getOutboundFlights()) {
                if (!each.getDestination().isTagged()) {
                    dbgassist = new Stopover(prevFlights, each.getDestination(), each, priority.getWeightByPriority(currentStop, each, prevFlights));
                    queue.offer(dbgassist);
                }
            }
        }
        return null;
    }

    public static Double getTimeAtTheAirport(Flight departingFlight, LinkedList<Flight> prevFlights) {
        if (prevFlights.isEmpty()) {
            return MIN;
        }
        Double timeStopped = (departingFlight.getDepartureTime() - prevFlights.getLast().getArrivalTime());
        if (timeStopped < 0) {
            timeStopped = WeekDay.getMinutesInAWeek() - timeStopped;
        }
        return timeStopped;
    }

    /**
     * @param
     *
     *
     */
    private static void clearMarks(AviationGraph avGraph) {
        avGraph.clearMarks();
    }
}
