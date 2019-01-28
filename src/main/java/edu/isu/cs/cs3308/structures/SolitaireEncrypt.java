package edu.isu.cs.cs3308.structures;

import edu.isu.cs.cs3308.structures.impl.CircularlyLinkedList;
import edu.isu.cs.cs3308.structures.impl.Node;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class SolitaireEncrypt<E> {


    private String path;

    ///Constructor for SolitaireEncrypt
    public SolitaireEncrypt(String path){

        this.path = path;

    }

    ///Getter and Setter for file path just in case
    public String getPath() { return path;}
    ///Setter just in case
    public void setPath(String path) {this.path = path;}



    public String execute(String messages) throws IOException {
        String result = "";
        String finalResult ="";


        messages = messages.replaceAll("[^a-zA-Z]+", "").toUpperCase();     ///Get message remove whitespace and punct.

        System.out.println(messages.length() % 5);
                                                             ///If message.length not multiple of 5 pad with "X"

        while(messages.length() %5 != 0){
            messages += 'X';
        }



        char[] temp = messages.toCharArray();





        for(int i = 0; i < temp.length; i++){

            char letterChar = temp[i];
            int letterNum = (int) letterChar;
            int newLetterNum = letterNum - Keystream(path);

            if(newLetterNum < 65){ newLetterNum = newLetterNum + 26; result += (char) newLetterNum;}
            else{result += (char) newLetterNum; }
        }

        System.out.println(result.length());





        /*for(int i = 0; i < temp.length;i++){
           int a = temp[i];
           if( a - Keystream(path) + 1 < 65){
               int b = a + 27 - Keystream(path);
               char c = ((char) b);
               result += c;
           } else {
               int b = a - Keystream(path) + 1;
               char c = ((char)b);
               result += c;
           }
        }*/

        return result;
    }


    public int Keystream(String path) throws IOException {

       CircularlyLinkedList<Integer> key = new CircularlyLinkedList<>();

       List<String> ekey = Files.readAllLines(Paths.get(path));


       String[] numbs = ekey.get(0).split("\\s+");

       ///Below adds numbers to list
       for(int i = 0; i < numbs.length; i++){
           int numero = Integer.valueOf(numbs[i]);
           key.addLast(numero);
       }

        ///Take 27 and swap with card below it in the deck

       int index = key.indexOf(27);

       if(key.indexOf(27) + 1 > 26){

       key.addFirst(key.get(index));
       key.remove(index);
       } else {
           key.insert(key.get(index), index + 1);
           key.remove(index-1);
       }



        ///Below takes value of 28 and moves it down 2

        int index2 = key.indexOf(28);
        int numb = key.get(index2);

        if(key.indexOf(numb) + 1 > 27){

            key.insert(key.get(index2), (index2 - 29)+ 2);
            key.remove(index2);
        } else {
            key.insert(key.get(index2), index2 + 2);
            key.remove(index2-1);
        }


        ///Triple-Cut below


        CircularlyLinkedList<Integer> deck1 = new CircularlyLinkedList<>();
        CircularlyLinkedList<Integer> deck2 = new CircularlyLinkedList<>();



        ///Below removes the ends

        while(key.get(0) != 28 && key.get(0) != 27){
           deck1.addLast(key.first());
           key.removeFirst();
        }




        while(key.last() != 28 && key.last()!= 27){
            deck2.addFirst(key.last());
            key.removeLast();
        }

        ///Below adds the ends the fist end to the end of the list and the last end to the first.


           int count = key.size();
           for(int i = 0; i < count; i++){
            deck2.addLast(key.first());
            key.removeFirst();
        }

           count = deck1.size();
           for(int i = 0; i < count;i++){
               deck2.addLast(deck1.first());
               deck1.removeFirst();
           }

           key = deck2;

           ///Step 4 the wierd shuffle

            index = key.last();
            for(int i = 0; i < index;i++){
                key.insert(key.first(),key.size()-2);
                key.removeFirst();
            }



            ///Below Return Keystream Value;

        int daKeyss = key.first();


        return daKeyss;
    }
}