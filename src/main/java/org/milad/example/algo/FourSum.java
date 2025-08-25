package org.milad.example.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

    public static List<List<Integer>> findTaskQuadruplets(int[] taskDurations, int targetDuration) {
        Arrays.sort(taskDurations); // Sorting the array
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < taskDurations.length - 3; i++) {
            if (i > 0 && taskDurations[i] == taskDurations[i - 1]) continue; // Skip duplicates
            for (int j = i + 1; j < taskDurations.length - 2; j++) {
                if (j > i + 1 && taskDurations[j] == taskDurations[j - 1]) continue; // Skip duplicates
                int left = j + 1;
                int right = taskDurations.length - 1;

                while (left < right) {
                    int sum = taskDurations[i] + taskDurations[j] + taskDurations[left] + taskDurations[right];
                    System.out.printf("Checking quadruplet: (%d, %d, %d, %d) -> Sum: %d%n", taskDurations[i], taskDurations[j], taskDurations[left], taskDurations[right], sum);

                    if (sum == targetDuration) {
                        result.add(Arrays.asList(taskDurations[i], taskDurations[j], taskDurations[left], taskDurations[right]));
                        System.out.printf("Found valid quadruplet: (%d, %d, %d, %d)%n", taskDurations[i], taskDurations[j], taskDurations[left], taskDurations[right]);
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
        }
        return result;
    }

    public static void main(String[] args) {
        int[] taskDurations = {2, 3, 1, 0, 5, 4, 0, 0 , 1, 6, 7, 8};
        int targetDuration = 6;
        List<List<Integer>> quadruplets = findTaskQuadruplets(taskDurations, targetDuration);
        for (List<Integer> quadruplet : quadruplets) {
            System.out.println(quadruplet);
        }
    }
}