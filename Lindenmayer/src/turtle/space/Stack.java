package turtle.space;


import java.util.LinkedList;


/**
 * Stack
 * @author Wester
 */
public class Stack<E> extends LinkedList<E>{

    public void push(E value){
        add(value);
    }

    public E pop(){
        return removeLast();
    }

}

