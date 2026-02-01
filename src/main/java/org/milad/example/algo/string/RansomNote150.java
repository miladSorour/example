package org.milad.example.algo.string;

public class RansomNote150 {

    public static void main(String[] args) {
        RansomNote150 solution = new RansomNote150();
        System.err.println(solution.canConstruct("bga", "efjbdfbdgfjhhaiigfhbaejahgfbbgbjagbddfbgagdiaigdadhcfcj"));
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        char[] ransomArray = ransomNote.toCharArray();
        char[] magazineCharArray = magazine.toCharArray();

        for (int i = 0; i < ransomArray.length; i++) {
            boolean found = false;
            for (int j = 0; j < magazineCharArray.length; j++) {
                if (ransomArray[i] == magazineCharArray[j]) {
                    ransomArray[i] = '0';
                    magazineCharArray[j] = '0';
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
             
        }

        return true;
    }
}