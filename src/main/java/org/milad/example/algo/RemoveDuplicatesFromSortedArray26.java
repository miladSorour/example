package org.milad.example.algo;

import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray26 {

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArray26 removeDuplicatesFromSortedArray26 = new RemoveDuplicatesFromSortedArray26();
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.err.println(removeDuplicatesFromSortedArray26.removeDuplicates(nums));
    }

    private int removeDuplicates(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                nums[i] = -101;
            }
        }
        int[] array = Arrays.stream(nums).boxed().filter((integer -> integer != -101)).sorted(Integer::compare).mapToInt(Integer::intValue).toArray();
        for (int i = 0; i < array.length; i++) {
            nums[i] = array[i];
        }
        return array.length - 1;
    }


}
