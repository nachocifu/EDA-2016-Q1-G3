/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package FlightAssistant;
//
///**
// *
// */
//public class FlightBNode {
//    private FlightBNode parent;
//    private BTreeValue values;
//    private FlightBNode leftChild;
//    private FlightBNode middleChild;
//    private FlightBNode rightChild;
//    private int spacesFilled;
//
//    public FlightBNode(FlightBNode parent) {
//        this.parent = parent;
//        this.spacesFilled = 0;
//    }
//
//    public boolean isNodeEmpty() {
//        return leftChild == null && rightChild == null;
//    }
//
//    public void addFlightToNode(Flight flight) {
//       if(values.size > 2) {
//           aux = values.getMiddleValue();
//           if(this.parent == null) {
//
//           }
//           this.parent.addFlightToNode(aux);
//       }
//    }
//
//    public FlightBNode getLeftChild() {
//        return this.leftChild;
//
//    public FlightBNode getMiddleChild() {
//        return this.middleChild;
//    }
//
//    public FlightBNode getRightChild() {
//        return this.rightChild;
//    }
//
//    public void setLeftChild(FlightBNode leftChild) {
//        this.leftChild = leftChild;
//    }
//
//    public void setMiddleChild(FlightBNode middleChild) {
//        this.middleChild = middleChild;
//    }
//
//    public void setRightChild(FlightBNode rightChild) {
//        this.rightChild = rightChild;
//    }
//
//}
