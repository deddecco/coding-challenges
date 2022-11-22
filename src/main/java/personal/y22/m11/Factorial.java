package personal.y22.m11;

public class Factorial {
     public double factorial(int n) {
          if (n == 1) {
               return 1;
          } else {
               return n * factorial(n - 1);
          }
     }

     public static void main(String[] args) {
          Factorial f = new Factorial();
          System.out.println(f.factorial(10));
     }
}

