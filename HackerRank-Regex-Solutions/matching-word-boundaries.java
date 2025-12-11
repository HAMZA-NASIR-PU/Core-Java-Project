// https://www.hackerrank.com/challenges/matching-word-boundaries/problem

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner sc = new Scanner(System.in);
        // Pattern p = Pattern.compile("\\b[aeiouAEIOU]\\w+\\b"); // test case 2 failed
        // Pattern p = Pattern.compile("\\b[aeiouAEIOU]([a-zA-Z]+)\\b"); // test case 1 failed
        // Pattern p = Pattern.compile("\\b[aeiouAEIOU][a-zA-Z]+\\b"); // test case 1 failed
        Pattern p = Pattern.compile("\\b[aeiouAEIOU][a-zA-Z]*\\b");
        Matcher m = p.matcher(sc.nextLine());
        System.out.println(m.find());
    }
}
