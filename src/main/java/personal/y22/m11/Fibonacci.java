package personal.y22.m11;

public class Fibonacci {
     public static void main(String[] args) {
          System.out.println(fib(30));
     }

     private static int fib(int i) {
          if (i == 1) {
               return 1;
          } else if (i == 2) {
               return 1;
          } else {
               return fib(i - 1) + fib(i - 2);
          }
     }
}
