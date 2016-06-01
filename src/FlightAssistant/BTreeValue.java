/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlightAssistant;

import java.util.Comparator;
import java.util.LinkedList;

/**
 *
 * @author amoragues
 */
public class BTreeValue {

    private BTreeValueNode first;   //LeftValue
    private BTreeValueNode middle;
    private BTreeValueNode last;    //RightValue
    private int size;

    public static class BTreeValueNode {

        private BTreeValueNode next;
        private Flight value;

        public BTreeValueNode(Flight flight) {
            this.value = flight;
        }

        public BTreeValueNode(Flight flight, BTreeValueNode next) {
            this.value = flight;
            this.next = next;
        }

        public void setNext(BTreeValueNode next) {
            this.next = next;
        }

        public Flight getValue() {
            return this.value;
        }

        public BTreeValueNode getNext() {
            return this.next;
        }

    }

    public void addFlight(BTreeValueNode current, Flight flight) {
        
    }
}
