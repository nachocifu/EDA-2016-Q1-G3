package DataStructure;

import FlightAssistant.Flight;

public class FlightBTree {

    private FlightBNode root;
    private int height;

    public FlightBTree() {
        this.root = new FlightBNode(null, null, null, null, null, null, null, null);
        this.height = 0;
    }

    public void insert(Flight flight) {
        FlightBNode current = this.root;
        while (!current.isLeaf()) {
            if (flight.getDepartureTime() < current.getLeft()) {
                current = current.getLeftChild();
            } else if (current.getRight() == null) {
                current = current.getRightChild();
            } else if (flight.getDepartureTime() < current.getRight()) {
                current = current.getMiddleChild();
            } else {
                current.getRightChild();
            }
        }
        current.add(flight.getDepartureTime());
        current.addToLeafList(flight);
        if(current.isFull()) {
            FlightBNode.split(current);
        }
    }

}

