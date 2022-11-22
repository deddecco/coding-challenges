package personal.y22.m11;

public class Combinations {
     public static void main(String[] args) {
          Combinations combos = new Combinations();
          System.out.println(combos.nChooseK(20, 3));

     }

     private double nChooseK(int n, int k) {
          Factorial factorial = new Factorial();

          double nFact = factorial.factorial(n);
          System.out.println(nFact);
          double kFact = factorial.factorial(k);
          System.out.println(kFact);
          double nMinusKFact = factorial.factorial(n - k);
          System.out.println(nMinusKFact);

          return (nFact / (kFact * nMinusKFact));
     }
}
