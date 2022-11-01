package personal.y22.m09;

import java.util.ArrayList;
import java.util.HashSet;

public class RemoveDuplicates {
    public static void main(String[] args) {
    }

    public static ArrayList<String> removeDuplicates(String[] source) {
        ArrayList<String> noDuplicates = new ArrayList<>();
        HashSet<String> setFromSource = new HashSet<>();
        for (int i = 0; i < source.length; i++) {
            if (!setFromSource.contains(source[i])) {
                setFromSource.add(source[i]);
                noDuplicates.add(source[i]);
            }
        }

        return noDuplicates;
    }
}