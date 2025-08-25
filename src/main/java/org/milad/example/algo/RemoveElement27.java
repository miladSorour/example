package org.milad.example.algo;

public class RemoveElement27 {

    public static void main(String[] args) {
        int[] ints = {3, 2, 2, 3};
        RemoveElement27 removeElement27 = new RemoveElement27();
        System.err.println(removeElement27.removeElement(ints, 3));
    }


    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
        }
        return i;
    }
}
