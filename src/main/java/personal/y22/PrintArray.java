package personal.y22.m10;

public class PrintArray {
     public static int[] nums;

     public static void setNums() {
          nums = new int[9];
          nums[1] = 2;
          nums[2] = 3;
          nums[3] = 4;
          nums[4] = 6;
          nums[6] = 7;
     }

     public static void print() {

          // run pointers start outside
          int firstEmpty = -1;
          int lastEmpty = -1;

          // loop through every number
          for (int i = 0; i < nums.length; i++) {
               // if cell is empty
               if (nums[i] == 0) {
                    // if first empty cell
                    if (firstEmpty == -1) {
                         // set both pointers to current position
                         firstEmpty = i;
                         lastEmpty = i;
                    } else {
                         // move only second pointer
                         lastEmpty = i;
                    }
               } else {
                    if (firstEmpty != -1) {
                         // print out the most recent empty run
                         System.out.printf("%d to %d: empty%n", firstEmpty, lastEmpty);
                         // reset both pointers
                         firstEmpty = -1;
                         lastEmpty = -1;
                    }
                    // print out the non-empty number
                    System.out.println(nums[i]);
               }
          }
          if (firstEmpty != -1) {
               System.out.printf("%d to %d: empty%n", firstEmpty, lastEmpty);
          }
     }

     public static void main(String[] args) {
          setNums();
          print();
     }
}
