package org.milad.example.algo;

public class LengthOfLastWord {

    public static void main(String[] args) {
        LengthOfLastWord solution = new LengthOfLastWord();
        System.err.println(solution.lengthOfLastWord("   fly me   to   the moon  "));
    }



    public int lengthOfLastWord(String s) {
        s = s.trim();
        String[] splitWord = s.split(" ");
        int length = splitWord.length;
        String lastWord = splitWord[length - 1];
        return lastWord.length();
    }
}
