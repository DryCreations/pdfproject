package com.groupseven.pdfproject;

import java.util.EmptyStackException;

/// \brief stack for storing move data
///
/// \ref t10_2 "task 10.2"
public class MoveStack<T> {

    /// \brief nodes for storing individual moves 
    ///
    /// \ref t10_2 "task 10.2"
    private class Node {
        T move;
        Node next;

        /// \brief create a new node given data
        ///
        /// \ref t10_2 "task 10.2"
        public Node(T move, Node next) {
            this.move = move;
            this.next = next;
        }

        /// \brief set move of node
        ///
        /// \ref t10_2 "task 10.2"
        public void setMove(T move) {
            this.move = move;
        }

        /// \brief return move of node
        /// \return Generic object containing move
        ///
        /// \ref t10_2 "task 10.2"
        public T getMove() {
            return move;
        }

        /// \brief get next node in stack
        /// \return Node next in stack
        ///
        /// \ref t10_2 "task 10.2"
        public Node getNext() {
            return next;
        }

        /// \brief set next node in stack
        ///
        /// \ref t10_2 "task 10.2"
        public void setNext(Node next) {
            this.next = next;
        }

    }

    Node top;
    int size;

    /// \brief create a new stack for storing moves
    ///
    /// \ref t10_2 "task 10.2"
    public MoveStack() {
        top = null;
        size = 0;
    }

    /// \brief determine if stack is empty
    /// \return Boolean indicating emptiness of stack
    ///
    /// \ref t10_2 "task 10.2"
    public boolean isEmpty() {
        return top == null;
    }

    /// \brief get size of the stack
    /// \return Integer indicating size of stack
    ///
    /// \ref t10_2 "task 10.2"
    public int getSize() {
        return size;
    }

    /// \brief push a new move onto the stack
    ///
    /// \ref t10_2 "task 10.2"
    public void push(T val) {
        Node newnode = new Node(val, top);
        top = newnode;
        size++;
    }

    /// \brief remove top node of the stack
    /// \return Move of the node removed
    ///
    /// \ref t10_2 "task 10.2"
    public T pop() {
        T data = null;
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            data = top.getMove();
            Node temp = top;
            top = top.getNext();
            temp.setNext(null);
            size--;
        }
        return data;
    }

    /// \brief get move of top node
    /// \return Move of top node
    ///
    /// \ref t10_2 "task 10.2"
    public T peek() {
        T data = null;
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            data = top.getMove();
        }
        return data;
    }
}
