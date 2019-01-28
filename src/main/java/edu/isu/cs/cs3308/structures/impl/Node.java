package edu.isu.cs.cs3308.structures.impl;

public class Node<E> {

    private E data;
    private Node<E> next;

    public Node(E data)
    {
      this.data = data;
    }

    public E getData() {return data;}//Returns the data in the node;

    public void setData(E d){this.data = data;}//Sets the data of the node;

    public Node<E> getNext(){return next;}// Returns the next node;


    public void setNext(Node<E> n){this.next = n;}//sets the next node;

}
