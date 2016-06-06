package Stopovers;
import FlightAssistant.*;
import java.util.LinkedList;

public class Stopover implements Comparable<Stopover>{
	private Double criteriaWeight;
        private Airport currentStop;
	private LinkedList<Flight> flights;
	private int HOURS_PER_DAY = 24;
	private int MINUTES_PER_HOUR = 60;

	
	public Stopover(Airport airport, Double weight){
		if(weight < 0 || airport == null)
			throw new IllegalArgumentException();
		this.criteriaWeight = weight;
		this.currentStop = airport;
		this.flights = new LinkedList<>();
                
	}
	
        public Stopover(LinkedList<Flight> previousStops, Airport finalDestination,Flight newFlight, Double weight){
		criteriaWeight = weight;
		this.currentStop = finalDestination;
                this.flights = new LinkedList<>();
                this.flights.addAll(previousStops);
                this.flights.add(newFlight);
                this.criteriaWeight = weight;
                
	}
	public Double getWeight(){
		return criteriaWeight;
	}
	
	public void setWeight(Double weight){
		if(weight < 0)
			throw new IllegalArgumentException();
		criteriaWeight = weight;
	}
        
        public Airport getCurrentStop() {
            return this.currentStop;
        }
        
        public LinkedList<Flight> getFlights() {
            return flights;
        }
	
        public void printStopoverWithPrice() {
            System.out.println("Se llego a " + currentStop.toString() + " con precio: " + criteriaWeight.toString());
            System.out.println("Atraves de:" );
            for(Flight each: this.flights) {
                System.out.println(each.toString());
            }
        }
        
        public void printStopoverWithTime() {
            int dias = (int) (criteriaWeight/(HOURS_PER_DAY*MINUTES_PER_HOUR));
            int horas = (int) ((criteriaWeight-(dias*HOURS_PER_DAY*MINUTES_PER_HOUR))/MINUTES_PER_HOUR);
            int minutos = (int) ((criteriaWeight-(dias*HOURS_PER_DAY*MINUTES_PER_HOUR))-(horas*MINUTES_PER_HOUR));
            System.out.println("Se llego a " + currentStop.toString() + " en: " + dias + " dias " + horas + " horas " + minutos + " minutos");
            System.out.println("Atraves de:" );
            for(Flight each: this.flights) {
                System.out.println(each.toString());
            }
        }
        
        public String toString(){
            if(flights.isEmpty()) {
                return "Arrancamos desde: " + currentStop.toString();
            }
            String str = "Desde: " + flights.getFirst().getOrigin().toString() + " Hasta " + currentStop + " por: " + criteriaWeight.toString();
            return str;
        }
        
        public Double getTotalFlightTime() {
            Double result = 0.0;
            for(Flight each: this.flights) {
                result += each.getFlightTime();
            }
            return result;
        }
        
        public Double getTotalTime() {
            Double result = 0.0;
            Double auxAirportTime = this.flights.getFirst().getDepartureTime();
            for(Flight each: this.flights) {
                auxAirportTime = each.getDepartureTime() - auxAirportTime;
                if(auxAirportTime < 0) {
                auxAirportTime = WeekDay.getMinutesInAWeek() - auxAirportTime;
            }
                result += each.getFlightTime() + auxAirportTime;
                auxAirportTime = each.getArrivalTime();
            }
            return result;
        }
        
        public Double getTotalPrice() {
            Double result = 0.0;
            for(Flight each: this.flights) {
                result += each.getPrice();
            }
            return result;
        }
        
    @Override
    public int compareTo(Stopover o) {
        return (int)(this.criteriaWeight - o.getWeight()) ;
    }
}