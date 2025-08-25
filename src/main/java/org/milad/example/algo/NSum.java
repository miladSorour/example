package org.milad.example.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NSum {

    public static List<List<Integer>> findNSum(int[] nums, int target, int n) {
        Arrays.sort(nums);
        return nSumHelper(nums, target, n, 0);
    }

    private static List<List<Integer>> nSumHelper(int[] nums, int target, int n, int start) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;

        if (n == 2) { // Base case: 2Sum problem
            int left = start, right = len - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    result.add(Arrays.asList(nums[left], nums[right]));
                    System.out.printf("Found pair: (%d, %d)%n", nums[left], nums[right]);
                    while (left < right && nums[left] == nums[left + 1]) left++; // Skip duplicates
                    while (left < right && nums[right] == nums[right - 1]) right--; // Skip duplicates
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        } else { // Recursively reduce to (n-1)Sum problem
            for (int i = start; i < len - (n - 1); i++) {
                if (i > start && nums[i] == nums[i - 1]) continue; // Skip duplicates
                System.out.printf("Considering element %d for %dSum%n", nums[i], n);
                List<List<Integer>> subResults = nSumHelper(nums, target - nums[i], n - 1, i + 1);
                for (List<Integer> subResult : subResults) {
                    List<Integer> newResult = new ArrayList<>(subResult);
                    newResult.add(0, nums[i]);
                    result.add(newResult);
                    System.out.printf("Formed %dSum combination: %s%n", n, newResult);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] taskDurations = {2, 3, 1, 5, 4, 6, 7, 8};
        int targetDuration = 15;
        int n = 4; // Example for 4Sum

        List<List<Integer>> nSumResults = findNSum(taskDurations, targetDuration, n);
        for (List<Integer> result : nSumResults) {
            System.out.println(result);
        }
    }
}
