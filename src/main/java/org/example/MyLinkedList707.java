package org.example;

class MyLinkedList707 {
    int size;
    ListNode head;

    public MyLinkedList707() {
        this.size=0;
    }

    public int get(int index) {
        if(index>=size){
            return -1;
        }
        ListNode pre=head;
        while(index>0){
            pre=pre.next;
            index--;
        }
        return pre.val;
    }

    public void addAtHead(int val) {
        if(size==0){
            head=new ListNode(val);
        }else{
            ListNode listNode=new ListNode();
            listNode.next=head.next;
            listNode.val=head.val;
            head.val=val;
            head.next=listNode;
        }
        size++;
    }

    public void addAtTail(int val) {
        ListNode end=new ListNode(val,null);
        if(size==0){
            head=end;
        }else{
            ListNode pre=head;
            while(pre.next!=null){
                pre=pre.next;
            }
            pre.next=end;
        }
        size++;
    }

    public void addAtIndex(int index, int val) {
        if(index>size){
            return;
        }
        if(index<=0){
            addAtHead(val);
        }else if(index==size){
            addAtTail(val);
        }else {
            ListNode pre = head;
            ListNode listNode=new ListNode(val);
            while(index>1){
                pre=pre.next;
                index--;
            }
            listNode.next=pre.next;
            pre.next=listNode;
            size++;
        }
    }

    public void deleteAtIndex(int index) {
        if(index>=size||index<0){
            return;
        }
        if(index==0 && size>0){
            head=head.next;
        }else {
            ListNode pre = head;
            while (index > 1) {
                pre = pre.next;
                index--;
            }
            pre.next = pre.next.next;
        }
        size--;
    }
}
