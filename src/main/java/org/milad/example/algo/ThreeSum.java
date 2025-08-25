package org.milad.example.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public static List<List<Integer>> findTaskTriplets(int[] taskDurations, int targetDuration) {
        Arrays.sort(taskDurations);
        List<List<Integer>> result = new ArrayList<>();

        System.err.println(Arrays.toString(taskDurations));
        for (int i = 0; i < taskDurations.length - 2; i++) {
            if (i > 0 && taskDurations[i] == taskDurations[i - 1]) continue; // Skip duplicates
            int left = i + 1;
            int right = taskDurations.length - 1;

            while (left < right) {
                int sum = taskDurations[i] + taskDurations[left] + taskDurations[right];
                if (sum == targetDuration) {
                    result.add(Arrays.asList(taskDurations[i], taskDurations[left], taskDurations[right]));
                    while (left < right && taskDurations[left] == taskDurations[left + 1]) left++; // Skip duplicates
                    while (left < right && taskDurations[right] == taskDurations[right - 1]) right--; // Skip duplicates
                    left++;
                    right--;
                } else if (sum < targetDuration) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] taskDurations = {2, 3, 1, 0, 5, 4, 0 , 1, 6, 7, 8};
        int targetDuration = 6;
        List<List<Integer>> triplets = findTaskTriplets(taskDurations, targetDuration);
        for (List<Integer> triplet : triplets) {
            System.out.println(triplet);
        }
    }
}