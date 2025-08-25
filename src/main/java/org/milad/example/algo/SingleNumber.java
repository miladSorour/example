package org.milad.example.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class SingleNumber {
    public static void main(String[] args) {
        SingleNumber solution = new SingleNumber();
        solution.singleNumber(new int[]{2, 2, 2, 10, 3, 4, 51});
    }

    public int singleNumber(int[] nums) {
        List<Integer> singleArray = new ArrayList<>();
        Arrays.sort(nums);
        Integer[] integers = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            integers[i] = nums[i]; // Auto-boxing: int -> Integer
        }
        for (int i = 0; i < integers.length; i++) {
            if ((i + 1 <= integers.length - 1 && Objects.equals(integers[i], integers[i + 1])) || (i - 1 >= 0 && Objects.equals(integers[i], integers[i - 1]))) {

            } else {
                singleArray.add(integers[i]);
            }

        }
        String array = singleArray.isEmpty() ? "" : singleArray.toString();
        System.err.println(array);
        return singleArray.get(0);
    }
}