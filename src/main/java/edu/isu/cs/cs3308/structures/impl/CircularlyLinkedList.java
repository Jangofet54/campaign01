package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.List;


public class CircularlyLinkedList<E>implements List<E>
{

    protected Node<E> tail;
    protected Node<E> head;
    protected int size = 0;


    @Override
    public void addLast(E element) {

        Node<E> temp = new Node<>(element);

        if(isEmpty()==true){
           head = temp;
           temp.setNext(head);
           tail = head;


        } else {

           temp.setNext(head);
           tail.setNext(temp);
           tail = temp;


        }
        size+=1;
    }



    @Override
    public void addFirst(E element) {

        Node<E> temp = new Node<>(element);

        if(size==0){
            head = temp;
            temp.setNext(head);
            tail = head;


           } else {


            temp.setNext(head);
            tail.setNext(temp);
            head = temp;

        }

        size+=1;

    }


    @Override
    public E removeFirst() {

        if(isEmpty()==true){return null;}

        Node<E> temp = head.getNext();
        E answer = head.getData();

        while (temp.getNext() != head){
            temp = temp.getNext();
        }

        temp.setNext(head.getNext());
        head = head.getNext();

        size -= 1;


        return answer;

    }

    @Override
    public E removeLast() {

        Node<E> temp = head;
        E answer = tail.getData();

        while (temp.getNext() != tail){
            temp = temp.getNext();
        }

      temp.setNext(head);
      tail.setNext(null);
      tail = temp;

      size -= 1;

      return answer;

    }

    @Override
    public void insert(E element, int index) {

        if(index < 0 || index > size || element == null){return;}

        Node<E> temp = new Node<>(element);
        Node<E> newNode = new Node<>(element);

        if(isEmpty()==true){
           addFirst(element);
        }

        temp = head;

        for(int i = 0; i < index;i++){
            temp = temp.getNext();
        }

        newNode.setNext(temp.getNext());
        temp.setNext(newNode);
        size += 1;


    }

    @Override
    public E remove(int index) {
        if(index < 0 || index > size){return null;}

        Node<E> temp = head;
        for(int i = 0; i < index; i++){
            temp = temp.getNext();
        }

        Node<E> toRemove = temp.getNext();
        temp.setNext(toRemove.getNext());
        toRemove.setNext(null);
        size -= 1;

        return toRemove.getData();
    }

    @Override
    public E get(int index) {

        if(index < 0 || index > size){return null;}

        Node<E> temp = head;

        while(index != 0){
            temp = temp.getNext();
            index--;
        }
        return temp.getData();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0){return true;}
        return false;
    }

    @Override
    public void printList() {

        if(size == 0){return;}

        Node<E> temp = head;

        for(int i = 0; i < size; i++){

            System.out.print(get(i) + " ");


            temp = temp.getNext();

        }

        System.out.println("\r\n");

    }

    @Override
    public int indexOf(E item) {



        Node<E> temp = new Node<>(item);
        temp = head;

        for(int i = 0; i < size; i++){

            if(temp.getData().equals(item)){
                return i;
            } else {
            temp=temp.getNext();
            }
        }

        System.out.println("indexOf index error");
        return 0;

    }

    @Override
    public E first() {
        if(size == 0){return null;}

        return head.getData();

    }

    @Override
    public E last() {
        if(size == 0){return null;}

        return tail.getData();

    }
}
