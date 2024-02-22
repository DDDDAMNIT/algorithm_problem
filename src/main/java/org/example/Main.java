package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Solution solution=new Solution();
        MyLinkedList707 myLinkedList707 = new MyLinkedList707();
        myLinkedList707.addAtHead(2);
        myLinkedList707.deleteAtIndex(1);
        myLinkedList707.addAtHead(2);
        myLinkedList707.addAtHead(7);
        myLinkedList707.addAtHead(3);
        myLinkedList707.addAtHead(2);
        myLinkedList707.addAtHead(5);
        myLinkedList707.addAtTail(5);
        myLinkedList707.get(5);
        myLinkedList707.deleteAtIndex(6);
        myLinkedList707.deleteAtIndex(4);
    }
}