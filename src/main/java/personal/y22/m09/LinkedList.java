package personal.y22.m09;

import java.util.Objects;

public class LinkedList {
     Element head;
     int size = 0;

     public void update(Element element) {
          getByKey(element.key).val = element.val;
     }

     static class Element {

          public String key;

          public int getVal() {
               return val;
          }

          public void setVal(int val) {
               this.val = val;
          }

          public int val;
          public Element next;

          Element(String key, int val) {
               this.key = key;
               this.val = val;
               this.next = null;
          }
     }

     public void put(String key, int val) {
          Element newElem = new Element(key, val);
          put(newElem);
     }

     public void put(Element element) {
          if (this.head == null) {
               this.head = element;
          } else {
               Element pointOfInsertion = this.head;
               while (pointOfInsertion.next != null) {
                    pointOfInsertion = pointOfInsertion.next;
               }
               pointOfInsertion.next = element;
          }
          this.size++;
     }

     public boolean isEmpty() {
          return this.size == 0;
     }

     public void printList() {
          Element current = this.head;
          while (current != null) {
               System.out.printf("%s\t%s\t\t", current.key, current.val);
               current = current.next;
          }
          System.out.println();
          System.out.println();
     }

     public void peek() {
          System.out.printf("%s\t%s", this.head.key, this.head.val);
          System.out.println();
     }

     public Element getHead() {
          return this.head;
     }

     public Element getByKey(String key) {
          Element search = this.head;
          while (search != null) {
               if (search.key.equals(key)) {
                    return search;
               } else {
                    search = search.next;
               }
          }

          return null;
     }

     public Element getByVal(int val) {
          Element search = head;
          while (search != null) {
               if (search.val == val) {
                    return search;
               } else {
                    search = search.next;
               }
          }
          return search;
     }

     public boolean contains(String key) {
          Element current = head;
          while (current != null) {
               if (current.key.equals(key)) {
                    return true;
               }
               current = current.next;
          }
          return false;
     }


     public boolean delete(String key) {
          // allow deletions if element exist
          if (contains(key)) {

               Element temp = this.head;
               Element prev = null;

               // if list has at least 1 element, deleting from beginning
               if (temp != null && Objects.equals(temp.key, key)) {
                    head = temp.next;
                    return true;
               }

               // if list has at least one element, deleting from elsewhere
               while (temp != null && !Objects.equals(temp.key, key)) {
                    prev = temp;
                    temp = temp.next;
               }

               // if list is empty
               if (temp == null) {
                    return false;
               }

               prev.next = temp.next;
               size--;
               return true;
          }
          System.out.println("Cannot delete non-existent element");
          return false;
     }

     public static String toString(Element elem) {
          return (elem.key + "\t" + elem.val);
     }

}