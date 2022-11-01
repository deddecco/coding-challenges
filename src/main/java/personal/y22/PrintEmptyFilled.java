package personal.y22.m10;

public class PrintEmptyFilled {
     public static int[] nums;

     public static void main(String[] args) {
          nums = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          printArray();
     }

     public static void printArray() {

          int firstEmpty = -1;
          int lastEmpty = -1;

          for (int i = 0; i < nums.length; i++) {
               if (nums[i] == 0) {
                    if (firstEmpty == -1) {
                         firstEmpty = i;
                         lastEmpty = i;
                    } else {
                         lastEmpty = i;
                    }
               } else {
                    if (firstEmpty != -1) {
                         System.out.printf("%d to %d: empty%n", firstEmpty, lastEmpty);
                         firstEmpty = -1;
                         lastEmpty = -1;
                    }
                    System.out.println(nums[i]);
               }
          }
          if (firstEmpty != -1) {
               System.out.printf("%d to %d: empty%n", firstEmpty, lastEmpty);
          }
     }
}