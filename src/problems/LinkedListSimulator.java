package problems;

//707
public class LinkedListSimulator {
    class Node{
        int val;
        Node next;
        public Node(int val){
            this.val = val;
            next = null;
        }
    }
    private Node head;
    private int idx;
    private Node tail;
    public LinkedListSimulator() {
        idx = -1;
        head = new Node(0);
        tail = head;
    }

    public int get(int index) {
        if(index > idx) return -1;
        int i = 0;
        Node n = head.next;
        while(i != index){
            ++i;
            n = n.next;
        }
        return n.val;
    }

    public void addAtHead(int val) {
        Node n = new Node(val);
        n.next = head.next;
        head.next = n;
        idx++;
        if(idx == 0) tail = n;
    }

    public void addAtTail(int val) {
        if(tail == null) head.next = new Node(val);
        else tail.next = new Node(val);
        tail = tail.next;
        idx++;
    }

    public void addAtIndex(int index, int val) {
        if(index == idx + 1) addAtTail(val);
        else if(index > idx + 1) return;
        else if(index <= 0) addAtHead(val);
        else {
            int i = 0;
            Node n = head.next;
            while(i != index - 1){
                i++;
                n = n.next;
            }
            Node node = n.next;
            n.next = new Node(val);
            n.next.next = node;
            idx++;
        }
    }

    public void deleteAtIndex(int index) {
        if(index > idx || index < 0) return;
        if(index == 0) head.next = head.next.next;
        else{
            int i = 0;
            Node n = head.next;
            while(i != index - 1) {
                i++;
                n = n.next;
            }
            n.next = n.next.next;
            if(index == idx) tail = n;
        }
        idx--;
    }
}
