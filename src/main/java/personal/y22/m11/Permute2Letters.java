package personal.y22.m11;

public class Permute2Letters {
     int[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
               'x', 'y', 'z'};

     public void printDoublesOneApart() {
          for (int i = 0; i < alphabet.length; i++) {
               for (int j = i + 1; j < alphabet.length; j++) {
                    System.out.println(alphabet[i] + "\t" + alphabet[j]);
               }
          }
     }

     public static void main(String[] args) {
          Permute2Letters permute2Letters = new Permute2Letters();

          permute2Letters.printDoublesOneApart();
     }
}
