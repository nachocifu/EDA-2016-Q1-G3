/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlightAssistant;

/**
 *
 * @author amoragues
 */
public class FlightBTrees {
    private FlightBNode root;
    
    public FlightBTrees() {
        this.root = new FlightBNode(null);
    }
    
    public void insertFlight(Flight flight) {
        if(root.isNodeEmpty()) {
            root.setLeftValue(flight);
            return;
        }
        else {
            
        }
    }
    
}
