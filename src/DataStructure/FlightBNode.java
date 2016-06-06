package DataStructure;

import FlightAssistant.Flight;
import java.util.LinkedList;

public class FlightBNode {
    public static final int LEFT = 1;
    public static final int MIDDLE = 2;
    public static final int RIGHT = 3;
    private Double leftValue;
    private Double middleValue;
    private Double rightValue;
    private LinkedList<Flight> leafListLeft;
    private LinkedList<Flight> leafListRight;
    private FlightBNode parent;
    private FlightBNode leftChild;
    private FlightBNode leftValueRightChild;
    private FlightBNode middleChild;
    private FlightBNode rightValueLeftChild;
    private FlightBNode rightChild;
;
    public FlightBNode(Double left, Double right, FlightBNode left1stChild,FlightBNode left2ndChild, FlightBNode right1stChild, FlightBNode right2ndChild, FlightBNode parent, LinkedList<Flight> leafList) {
        this.leftValue = left;
        this.rightValue = right;
        this.leftChild = left1stChild;
        this.leftValueRightChild = left2ndChild;
        this.rightValueLeftChild = right1stChild;
        this.rightChild = right2ndChild;
        this.parent = parent;
    }

    
    
    public boolean isLeaf(){
    	if(leftChild == null && leftValueRightChild == null && rightValueLeftChild == null && rightChild == null)
    		return true;
    	return false;
    }
    
    public boolean isFull(){
    	if(this.leftValue != null && this.rightValue != null && this.middleValue != null) {
    		return true;
    	}
    	return false;
    }
    	
    public FlightBNode getParent() {
        return this.parent;
    }
    
    public Double getLeft(){
    	return this.leftValue;
    }
    
    public Double getMiddle(){
    	return this.middleValue;
    }
    
    public Double getRight(){
    	return this.rightValue;
    }

    public FlightBNode getMiddleChild() {
        return this.middleChild;
    }

    public FlightBNode getLeftValueRightChild() {
        return this.leftValueRightChild;
    }

    public FlightBNode getRightValueLeftChild() {
        return this.rightValueLeftChild;
    }

    public FlightBNode getLeftChild() {
        return this.leftChild;
    }

    public FlightBNode getRightChild() {
        return this.rightChild;
    }
    
    
    
    public int add(Double flightTime) {
        if(this.leftValue == null) {
            this.leftValue = flightTime;
            return LEFT;
        }
        else {
            if(this.rightValue == null) {
                if(this.leftValue < flightTime) {
                    this.rightValue = flightTime;
                    return RIGHT;
                }
                else {
                    this.rightValue = this.leftValue;
                    this.leftValue = flightTime;
                    return LEFT;
                }
            }
            else {
                if(this.leftValue < flightTime) {
                    if(flightTime < this.rightValue) {
                        this.middleValue = flightTime;
                        return MIDDLE;
                    }
                    else {
                        this.middleValue = this.rightValue;
                        this.rightValue = flightTime;
                        return RIGHT;
                    }
                }
                else {
                    this.middleValue = this.leftValue;
                    this.leftValue = flightTime;
                    return LEFT;
                }
            }
        }
    }
    
    public void addToLeafList(Flight flight) {
        if(flight.getDepartureTime() < this.rightValue) {
            this.leafListLeft.add(flight);
        }
        else {
            this.leafListRight.add(flight);
        }
    }
    
    public static void split(FlightBNode node){
    	FlightBNode current =  node;
    	Double leftAux;
    	Double middleAux;
    	Double rightAux;
        FlightBNode firstChild = null;
        FlightBNode secondChild = null;
        FlightBNode thirdChild = null;
        FlightBNode fourthChild = null;
    	while(current.isFull()){
    			leftAux = current.getLeft();
    			rightAux = current.getRight();
    			middleAux = current.getMiddle();
                        firstChild = current.getLeftChild();
                        secondChild = current.getLeftValueRightChild();
                        thirdChild = current.getRightValueLeftChild();
                        fourthChild = current.getRightChild();
    			if(current.isLeaf()) {
                            
                        }
                        current = current.parent;
                        if(current.add(middleAux) == 1){
                            current.leftChild = new FlightBNode(leftAux, null, firstChild, null, null, secondChild, current.getParent(), null);
                            current.leftValueRightChild = new FlightBNode(rightAux, null, thirdChild, null, null, fourthChild, current.getParent(),null);
                        }
                        if(current.add(middleAux) == 2){
                            current.leftValueRightChild = new FlightBNode(leftAux, null, firstChild, null, null, secondChild, current.getParent(), null);
                            current.rightValueLeftChild = new FlightBNode(rightAux, null, thirdChild, null, null, fourthChild, current.getParent(), null);
                        }
                        if(current.add(middleAux) == 3){
                            current.rightValueLeftChild = new FlightBNode(leftAux, null, firstChild, null, null, secondChild, current.getParent(), null);
                            current.rightChild = new FlightBNode(rightAux, null, thirdChild, null, null, fourthChild, current.getParent(), null);
                        }
    	}
        if(current.rightValueLeftChild == current.leftValueRightChild) {
            current.middleChild = current.rightValueLeftChild;
        }
        else if(current.rightValueLeftChild == null && current.leftValueRightChild != null) {
            current.middleChild = current.leftValueRightChild;
        }
        else if(current.rightValueLeftChild != null && current.leftValueRightChild == null) {
            current.middleChild = current.rightValueLeftChild;
        }
        else {
            throw new IllegalStateException();
        }
    }
    
}