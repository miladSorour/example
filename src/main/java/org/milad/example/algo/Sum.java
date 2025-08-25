package org.milad.example.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sum {

    public static List<List<Integer>> findAllCombinations(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // Sorting to handle duplicates easily
        System.err.println(Arrays.toString(nums));
        backtrack(nums, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] nums, int remaining, int start, List<Integer> tempList, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(tempList));
            System.out.println("Found combination: " + tempList);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue; // Skip duplicates
            if (nums[i] > remaining) break; // Early termination
            tempList.add(nums[i]);
            System.out.printf("i= %d , start= %d , Considering element %d, current combination: %s, remaining: %d%n", i, start, nums[i], tempList, remaining - nums[i]);
            backtrack(nums, remaining - nums[i], i + 1, tempList, result); // Move to the next element
            tempList.remove(tempList.size() - 1);
            System.out.printf("Backtracking, current combination: %s, remaining: %d%n", tempList, remaining);
        }
        System.out.println("-------------------------------------------------------- \n");
    }

    public static void main(String[] args) {
        int[] taskDurations = {2, 3, 2, 1, 1, 0, 0, 0, 0, 1, 5, 4, 6, 7, 8};
        int targetDuration = 3;
        List<List<Integer>> combinations = findAllCombinations(taskDurations, targetDuration);
        for (List<Integer> combination : combinations) {
            System.out.println(combination);
        }
    }
}
