package org.example;

import java.util.LinkedList;
import java.util.Queue;

class MyStack {
    private LinkedList<Integer> queue1;
    boolean rolled;

    public MyStack() {
        queue1=new LinkedList<>();
        rolled=false;
    }

    public void push(int x) {
        queue1.add(x);
        rolled=false;
    }

    public int pop() {
        roll();
        rolled=false;
        return queue1.poll();
    }

    public int top() {
        roll();
        rolled=true;
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty();
    }

    private void roll(){
        if(!rolled) {
            int l = queue1.size();
            while (l > 1) {
                int x = queue1.poll();
                queue1.add(x);
                l--;
            }
        }
    }
}
