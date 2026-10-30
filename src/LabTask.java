import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

//SLLNode class
class SLLNode<E> {
    public E element;
    public SLLNode<E> next;

    public SLLNode(E element, SLLNode<E> next) {
        this.element = element;
        this.next = next;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

//class SLL
 class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        this.first = null;
    }

    // insert element en of the list
    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = new SLLNode<>(o, null);
        } else {
            first = new SLLNode<>(o, null);
        }
    }

    // delete the element at index
    public void deleteAtIndex(int index) {
        if (index == 0) {
            if (first != null) {
                first = first.next;
            }
            return;
        }
        SLLNode<E> current = first;
        for (int i = 0; i < index - 1 && current != null; i++) {
            current = current.next;
        }
        if (current != null && current.next != null) {
            current.next = current.next.next;
        }
    }

    // printList
    public void printList() {
        if (first == null) {
            System.out.println("Prazna lista!!!");
            return;
        }
        SLLNode<E> tmp = first;
        StringBuilder sb = new StringBuilder();
        while (tmp != null) {
            sb.append(tmp.element);
            if (tmp.next != null) {
                sb.append("->");
            }
            tmp = tmp.next;
        }
        System.out.println(sb.toString());
    }

    // return element of at index
    public SLLNode<E> getNode(int index) {
        SLLNode<E> current = first;
        for (int i = 0; i < index && current != null; i++) {
            current = current.next;
        }
        return current;
    }

    // change position element of at index
    public void setElement(int index, E element) {
        SLLNode<E> current = getNode(index);
        if (current != null) {
            current.element = element;
        }
    }
}

public class LabTask {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


       int n = Integer.parseInt(br.readLine());
        SLL<String> list1 = new SLL<>();
        for (int i = 0; i < n; i++) {
            list1.insertLast(br.readLine());
        }

        int m =Integer.parseInt (br.readLine());
        SLL<String> list2 = new SLL<>();
        for (int i = 0; i < m; i++) {
            list2.insertLast(br.readLine());
        }
        double x = Double.parseDouble(br.readLine());
        int minSize;
        if (n < m) {
            minSize = n;
        } else {
            minSize = m;
        }

        int deletedNodesCount = 0;

        for (int i = 0; i < minSize; i++) {

            long a = Long.parseLong(list1.getNode(i).element);
            long b = Long.parseLong(list2.getNode(i - deletedNodesCount).element);


            double diff = a - b;
            if (diff < 0) {
                diff = -diff;
            }

           if (diff > x) {
               list1.setElement(i,String.format("Outlier %d",a));
               list2.deleteAtIndex(i - deletedNodesCount);
                deletedNodesCount++;
            }
       }
        for (int i = minSize; i < n; i++) {
            String originalVal = list1.getNode(i).element;
            list1.setElement(i,String.format("Unchecked %s",originalVal));
        }
        list1.printList();
        list2.printList();
    }
}