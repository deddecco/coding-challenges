package personal.y22.m11;

import java.util.Arrays;

public class RandomlyPopulateZeroes {


     public static void main(String[] args) {
          int[] nums = {1, 2, 3, 4, 0, 0};

          int[] ref = {1, 2, 3, 4, 5, 6};
          for (int i = 0; i < nums.length; i++) {
               if (nums[i] != ref[i]) {
                    nums[i] = ref[i];
               }
          }
          System.out.println(Arrays.toString(nums));
     }
}
