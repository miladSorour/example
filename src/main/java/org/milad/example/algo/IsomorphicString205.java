package org.milad.example.algo;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicString205 {

    public static void main(String[] args) {
        IsomorphicString205 solver = new IsomorphicString205();
        System.err.println(solver.isIsomorphic("badc", "baba"));
    }

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Character> mapST = new HashMap<>();
        Map<Character, Character> mapTS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if (mapST.containsKey(c1) && mapST.get(c1) != c2) return false;
            if (mapTS.containsKey(c2) && mapTS.get(c2) != c1) return false;

            mapST.put(c1, c2);
            mapTS.put(c2, c1);
        }

        return true;
    }
}
