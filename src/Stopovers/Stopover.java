package Stopovers;
import FlightAssistant.*;
import java.util.LinkedList;

public class Stopover implements Comparable<Stopover>{
	private Double criteriaWeight;
        private Airport currentStop;
	private LinkedList<Flight> flights;

	
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
            int dias = (int) (criteriaWeight/(24*60));
            int horas = (int) ((criteriaWeight-(dias*24*60))/60);
            int minutos = (int) ((criteriaWeight-(dias*24*60))-(horas*60));
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
    @Override
    public int compareTo(Stopover o) {
        return (int)(this.criteriaWeight - o.getWeight()) ;//To change body of generated methods, choose Tools | Templates.
    }
}