/*
 *    Copyright   2015
 *     Filename : Round3
 *      Project : TestProject
 *   Created by : fanyibo on 2/2/15 6:02 PM
 */
package com.fanyibo.util.round3;

import com.fanyibo.tree.TreeNode;
import com.fanyibo.util.ListNode;

import java.util.*;

public class Round3 {


    /**
     * 1. Two Sum
     * Given an array of integers, find two numbers such that they add up to a specific target number.
     * The function twoSum should return indices of the two numbers such that they add up to the target, where
     * index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not
     * zero-based. You may assume that each input would have exactly one solution.
     * Input: numbers={2, 7, 11, 15}, target=9
     * Output: index1=1, index2=2
     */
    public static int[] twoSum(int[] numbers, int target) {

        int[] result = new int[2];
        HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(numbers[i])) {
                map.get(numbers[i]).add(i);
            } else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                map.put(numbers[i], list);
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            int a = numbers[i];
            int b = target - a;
            if (map.containsKey(b) && ((a == b && map.get(b).size() > 1) || (a != b))) {
                List<Integer> indexes = map.get(b);
                if (a == b) {
                    result[0] = i + 1;
                    result[1] = indexes.get(1) + 1;
                } else {
                    result[0] = i + 1;
                    result[1] = indexes.get(0) + 1;
                }
                return result;
            }
        }
        return result;
    }


    /**
     * 2.Add Two Numbers
     * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse
     * order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        ListNode result = null;
        ListNode temp = null;
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        boolean markOne = false;
        while (temp1 != null || temp2 != null) {
            int a = 0;
            int b = 0;
            if (temp1 != null) {
                a = temp1.val;
                temp1 = temp1.next;
            }
            if (temp2 != null) {
                b = temp2.val;
                temp2 = temp2.next;
            }
            int c = a + b + (markOne ? 1 : 0);
            if (c >= 10) {
                markOne = true;
                c = c % 10;
            } else {
                markOne = false;
            }
            if (temp == null) {
                result = new ListNode(c);
                temp = result;
            } else {
                temp.next = new ListNode(c);
                temp = temp.next;
            }
        }
        if (markOne) {
            temp.next = new ListNode(1);
        }
        return result;
    }

    /**
     * 3. Longest Substring Without Repeating Characters
     * Given a string, find the length of the longest substring without repeating characters. For example, the
     * longest
     * substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest
     * substring is "b", with the length of 1.
     */
    public static int lengthOfLongestSubstring(String s) {

        if (s.length() < 2) {
            return s.length();
        }
        int index1 = 0;
        int index2 = 0;
        int max = 0;
        Set<Character> set = new HashSet<Character>();
        while (index2 < s.length()) {
            char c = s.charAt(index2);
            if (!set.contains(c)) {
                set.add(c);
                index2++;
            } else {
                max = Math.max(max, index2 - index1);
                for (; index1 < index2; index1++) {
                    if (s.charAt(index1) == c) {
                        index1++;
                        break;
                    } else {
                        set.remove(s.charAt(index1));
                    }
                }
                index2++;
            }
        }
        max = Math.max(max, index2 - index1);
        return max;
    }

    /**
     * 4. Median of Two Sorted Arrays
     * There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays. The
     * overall run time complexity should be O(log (m+n)).
     */
    public static double findMedianSortedArrays(int A[], int B[]) {

        int sizeA = A.length;
        int sizeB = B.length;
        if (sizeA == 0 && sizeB == 0) {
            return 0;
        } else if (sizeA == 0) {
            return sizeB % 2 == 0 ? ((double) B[(sizeB - 1) / 2] + (double) B[sizeB / 2]) / 2.0 : (double) B[(sizeB -
                    1) / 2];
        } else if (sizeB == 0) {
            return sizeA % 2 == 0 ? ((double) A[(sizeA - 1) / 2] + (double) A[sizeA / 2]) / 2.0 : (double) A[(sizeA -
                    1) / 2];
        }
        if ((sizeA + sizeB) % 2 == 0) {
            int first = findKth(A, 0, sizeA - 1, B, 0, sizeB - 1, (sizeA + sizeB) / 2);
            int second = findKth(A, 0, sizeA - 1, B, 0, sizeB - 1, (sizeA + sizeB) / 2 + 1);
            return (double) (first + second) / 2.0;
        } else {
            return (double) findKth(A, 0, sizeA - 1, B, 0, sizeB - 1, (sizeA + sizeB) / 2 + 1);
        }
    }

    public static int findKth(int A[], int startA, int endA, int B[], int startB, int endB, int k) {

        int sizeA = endA - startA + 1;
        int sizeB = endB - startB + 1;
        if (sizeA <= 0) {
            return B[startB + k - 1];
        } else if (sizeB <= 0) {
            return A[startA + k - 1];
        } else if (k == 1) {
            return Math.min(A[startA], B[startB]);
        } else if (k == sizeA + sizeB) {
            return Math.max(A[endA], B[endB]);
        } else if (sizeA == 1) {
            if (B[startB + k - 1] <= A[startA]) {
                return B[startB + k - 1];
            } else {
                return findKth(A, startA, endA, B, startB, startB + k - 2, k);
            }
        } else if (sizeB == 1) {
            if (A[startA + k - 1] <= B[startB]) {
                return A[startA + k - 1];
            } else {
                return findKth(A, startA, startA + k - 2, B, startB, endB, k);
            }
        }
        int midAIndex = (startA + endA) / 2;
        int midBIndex = (startB + endB) / 2;
        if (A[midAIndex] >= B[midBIndex]) {
            if (k <= midAIndex + midBIndex - startA - startB + 2) {
                return findKth(A, startA, midAIndex, B, startB, endB, k);
            } else {
                return findKth(A, startA, endA, B, midBIndex + 1, endB, k - midBIndex - 1 + startB);
            }
        } else {
            if (k <= midAIndex + midBIndex - startA - startB + 2) {
                return findKth(A, startA, endA, B, startB, midBIndex, k);
            } else {
                return findKth(A, midAIndex + 1, endA, B, startB, endB, k - midAIndex - 1 + startA);
            }
        }
    }

    /**
     * 5. Longest Palindromic Substring
     * Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is
     * 1000, and there exists one unique longest palindromic substring.
     */
    public static String longestPalindrome(String s) {

        int size = s.length();
        if (size <= 1) {
            return s;
        }
        boolean[][] d = new boolean[size][size];
        int maxI = 0;
        int maxJ = 0;
        for (int i = size - 1; i >= 0; i--) {
            for (int j = i; j < size; j++) {
                d[i][j] = (i == j) || (i + 1 == j && s.charAt(i) == s.charAt(j)) || (s.charAt(i) == s
                        .charAt(j) && d[i + 1][j - 1]);
                if (d[i][j] && (j - i) > (maxJ - maxI)) {
                    maxI = i;
                    maxJ = j;
                }
            }
        }
        return s.substring(maxI, maxJ + 1);
    }

    /**
     * 6. ZigZag Conversion
     * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may
     * want to
     * display this pattern in a fixed font for better legibility)
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * And then read line by line: "PAHNAPLSIIGYIR"
     * Write the code that will take a string and make this conversion given a number of rows:
     * string convert(string text, int nRows);
     * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
     */
    public static String convert(String s, int nRows) {

        if (nRows <= 1) {
            return s;
        }
        int m = nRows;
        int numPerBlock = 2 * m - 2;
        int numBlock = s.length() / numPerBlock;
        int numLeft = s.length() % numPerBlock;
        int colLeft = 0;
        if (numLeft > m) {
            colLeft = 1 + numLeft % m;
        } else if (numLeft > 0) {
            colLeft = 1;
        }
        int n = numBlock * (m - 1) + colLeft;
        char[][] matrix = new char[m][n];

        int indexI = 0;
        int indexJ = 0;
        for (int i = 0; i < s.length(); i++) {
            matrix[indexI][indexJ] = s.charAt(i);

            int nextI = (indexJ % (m - 1) == 0 && indexI != m - 1) ? indexI + 1 : indexI - 1;
            int nextJ = (indexJ % (m - 1) == 0) ? ((indexI == m - 1 ? indexJ + 1 : indexJ)) : (indexJ + 1);

            indexI = nextI;
            indexJ = nextJ;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != '\0') {
                    builder.append(matrix[i][j]);
                }
            }
        }
        return builder.toString();
    }

    /**
     * 7. Reverse Integer
     * Reverse digits of an integer.
     * Example1: x = 123, return 321
     * Example2: x = -123, return -321
     */
    public static int reverse(int x) {

        if (x == 0) {
            return x;
        }
        long result = 0;
        long target = x;
        boolean isNegative = false;
        if (target < 0) {
            isNegative = true;
            target = -target;
        }
        while (target != 0) {
            result = result * 10 + target % 10;
            target /= 10;
        }
        result = isNegative ? -result : result;
        return (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) ? 0 : (int) result;
    }

    /**
     * 8. String to Integer (atoi)
     * Implement atoi to convert a string to an integer.
     * Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask
     * yourself what are the possible input cases.
     * Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible
     * to gather all the input requirements up front.
     */
    public static int atoi(String str) {

        int len = str.length();
        if (len == 0) {
            return 0;
        }
        long result = 0;
        boolean isNegative = false;
        boolean started = false;
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (!started && c == ' ') {
                continue;
            } else if (started && c == ' ') {
                break;
            }

            if (c == '+' || c == '-') {
                if (started) {
                    return 0;
                } else {
                    isNegative = c == '-';
                    started = true;
                }
            } else if (c >= '0' && c <= '9') {
                started = true;
                result = result * 10 + ((int) c - 48);
                if (!isNegative && result > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                } else if (isNegative && result - 1 > Integer.MAX_VALUE) {
                    return Integer.MIN_VALUE;
                }
            } else {
                break;
            }
        }
        return isNegative ? (int) -result : (int) result;
    }

    /**
     * 9. Palindrome Number
     * Determine whether an integer is a palindrome. Do this without extra space.
     * click to show spoilers.
     * Some hints:
     * Could negative integers be palindromes? (ie, -1)
     * If you are thinking of converting the integer to string, note the restriction of using extra space.
     * You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know
     * that the reversed integer might overflow. How would you handle such case?
     * There is a more generic way of solving this problem.
     */
    public static boolean isPalindrome(int x) {

        if (x < 0) {
            return false;
        } else if (x == 0) {
            return true;
        }
        int len = 0;
        int temp = x;
        while (temp != 0) {
            len++;
            temp /= 10;
        }
        int dev = (int) Math.pow(10, len - 1);
        while (x != 0) {
            int firstDigit = x / dev;
            int lastDigit = x % 10;
            if (firstDigit != lastDigit) {
                return false;
            }
            x = (x % dev) / 10;
            dev /= 100;
        }
        return true;
    }

    /**
     * 10. Regular Expression Matching
     * Implement regular expression matching with support for '.' and '*'.
     * '.' Matches any single character.
     * '*' Matches zero or more of the preceding element.
     * The matching should cover the entire input string (not partial).
     * The function prototype should be:
     * bool isMatch(const char *s, const char *p)
     * Some examples:
     * isMatch("aa","a") → false
     * isMatch("aa","aa") → true
     * isMatch("aaa","aa") → false
     * isMatch("aa", "a*") → true
     * isMatch("aa", ".*") → true
     * isMatch("ab", ".*") → true
     * isMatch("aab", "c*a*b") → true
     */
    public static boolean isMatch(String s, String p) {

        int size1 = s.length();
        int size2 = p.length();
        if (size2 == 0) {
            return size1 == 0;
        }
        if (size2 == 1 || p.charAt(1) != '*') {
            if (size1 == 0 || (p.charAt(0) != '.' && p.charAt(0) != s.charAt(0))) {
                return false;
            }
            return isMatch(s.substring(1), p.substring(1));
        } else {
            int index = -1;
            while (index < size1 && (index < 0 || p.charAt(0) == '.' || p.charAt(0) == s.charAt(index))) {
                if (isMatch(s.substring(index + 1), p.substring(2))) {
                    return true;
                }
                index++;
            }
            return false;
        }
    }

    /**
     * 11. Container With Most Water
     * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical
     * lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together
     * with x-axis forms a container, such that the container contains the most water.
     * Note: You may not slant the container.
     */
    public static int maxArea(int[] height) {

        int size = height.length;
        if (size < 2) {
            return 0;
        }
        int index1 = 0;
        int index2 = size - 1;
        int max = 0;
        while (index1 < index2) {
            max = Math.max(max, Math.min(height[index1], height[index2]) * (index2 - index1));
            if (height[index1] < height[index2]) {
                index1++;
            } else {
                index2--;
            }
        }
        return max;
    }

    /**
     * 12. Integer to Roman
     * Given an integer, convert it to a roman numeral.
     * Input is guaranteed to be within the range from 1 to 3999.
     */
    public static String intToRoman(int num) {

        HashMap<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "I");
        map.put(5, "V");
        map.put(10, "X");
        map.put(50, "L");
        map.put(100, "C");
        map.put(500, "D");
        map.put(1000, "M");

        StringBuilder builder = new StringBuilder();
        int dev = 1000;
        while (num != 0) {
            int t = num / dev;
            String c = map.get(dev);
            if (t >= 5 && dev != 1000) {
                if (t == 9) {
                    builder.append(c);
                    builder.append(map.get(10 * dev));
                    t = 0;
                } else {
                    builder.append(map.get(5 * dev));
                    t -= 5;
                }
            } else if (t == 4 && dev != 1000) {
                builder.append(c);
                builder.append(map.get(5 * dev));
                t = 0;
            }
            for (int i = 0; i < t; i++) {
                builder.append(c);
            }
            num = num % dev;
            dev /= 10;
        }
        return builder.toString();
    }

    /**
     * 13. Roman to Integer
     * Given a roman numeral, convert it to an integer.
     * Input is guaranteed to be within the range from 1 to 3999.
     */
    public static int romanToInt(String s) {

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            int current = map.get(s.charAt(i));
            int next = (i == s.length() - 1) ? 0 : map.get(s.charAt(i + 1));
            if (current < next) {
                result += (next - current);
                i++;
            } else {
                result += current;
            }
        }
        return result;
    }

    /**
     * 14. Longest Common Prefix
     * Write a function to find the longest common prefix string amongst an array of strings.
     */
    public static String longestCommonPrefix(String[] strs) {

        if (strs.length == 0) {
            return "";
        }
        String result = _longestCommonPrefix(strs, 0, strs.length - 1);
        return result == null ? "" : result;
    }

    public static String _longestCommonPrefix(String[] strs, int start, int end) {

        int size = end - start + 1;
        if (size <= 0) {
            return null;
        } else if (size == 1) {
            return strs[start];
        }
        int mid = (start + end) / 2;
        String left = _longestCommonPrefix(strs, start, mid);
        String right = _longestCommonPrefix(strs, mid + 1, end);
        if (left == null && right == null) {
            return null;
        } else if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            StringBuilder builder = new StringBuilder();
            int len = Math.min(left.length(), right.length());
            for (int i = 0; i < len; i++) {
                if (left.charAt(i) != right.charAt(i)) {
                    break;
                } else {
                    builder.append(left.charAt(i));
                }
            }
            return builder.toString();
        }
    }

    /**
     * 15. 3Sum
     * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique
     * triplets in the array which gives the sum of zero.
     * Note:
     * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
     * The solution set must not contain duplicate triplets.
     * For example, given array S = {-1 0 1 2 -1 -4},
     * A solution set is:
     * (-1, 0, 1)
     * (-1, -1, 2)
     */
    public static List<List<Integer>> threeSum(int[] num) {

        Set<List<Integer>> result = new HashSet<List<Integer>>();
        if (num.length < 3) {
            return new ArrayList<List<Integer>>();
        }
        Arrays.sort(num);
        for (int i = 0; i < num.length; i++) {
            int a = num[i];
            int target = -a;
            int index1 = i + 1;
            int index2 = num.length - 1;
            while (index1 < index2) {
                int b = num[index1];
                int c = num[index2];
                if (b + c == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(a);
                    list.add(b);
                    list.add(c);
                    result.add(list);
                    index1++;
                    index2--;
                } else if (b + c > target) {
                    index2--;
                } else {
                    index1++;
                }
            }
        }
        return new ArrayList<List<Integer>>(result);
    }

    /**
     * 16. 3Sum Closest
     * Given an array S of n integers, find three integers in S such that the sum is closest to a given number,
     * target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
     * For example, given array S = {-1 2 1 -4}, and target = 1.
     * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
     */
    public static int threeSumClosest(int[] num, int target) {

        if (num.length < 3) {
            return 0;
        }
        Arrays.sort(num);
        int sum = 0;
        boolean set = false;
        for (int i = 0; i < num.length; i++) {
            int a = num[i];
            int t = target - a;
            int index1 = i + 1;
            int index2 = num.length - 1;
            while (index1 < index2) {
                int b = num[index1];
                int c = num[index2];
                if (b + c == t) {
                    return target;
                } else if (b + c > t) {
                    index2--;
                } else {
                    index1++;
                }
                int currSum = a + b + c;
                if (!set || Math.abs(currSum - target) < Math.abs(sum - target)) {
                    set = true;
                    sum = currSum;
                }
            }
        }
        return sum;
    }

    /**
     * 17. Letter Combinations of a Phone Number
     * Given a digit string, return all possible letter combinations that the number could represent.
     * A mapping of digit to letters (just like on the telephone buttons) is given below.
     * Input:Digit string "23"
     * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     */
    public static List<String> letterCombinations(String digits) {

        HashMap<Character, String> map = new HashMap<Character, String>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        List<String> result = new ArrayList<String>();
        if (digits.length() == 0) {
            result.add("");
            return result;
        }
        for (int i = 0; i < digits.length(); i++) {
            char c = digits.charAt(i);
            String str = map.get(c);
            if (result.isEmpty()) {
                for (int j = 0; j < str.length(); j++) {
                    result.add(Character.toString(str.charAt(j)));
                }
            } else {
                List<String> temp = new ArrayList<String>();
                for (String elem : result) {
                    for (int j = 0; j < str.length(); j++) {
                        temp.add(elem + Character.toString(str.charAt(j)));
                    }
                }
                result = temp;
            }
        }
        return result;
    }

    /**
     * 18. 4Sum
     * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find
     * all unique quadruplets in the array which gives the sum of target.
     * Note:
     * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
     * The solution set must not contain duplicate quadruplets.
     * For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
     * A solution set is:
     * (-1,  0, 0, 1)
     * (-2, -1, 1, 2)
     * (-2,  0, 0, 2)
     */
    public static List<List<Integer>> fourSum(int[] num, int target) {

        int size = num.length;
        if (size < 4) {
            return new ArrayList<List<Integer>>();
        }
        Arrays.sort(num);
        Set<List<Integer>> result = new HashSet<List<Integer>>();
        for (int i = 0; i <= size - 4; i++) {
            int a = num[i];
            for (int j = i + 1; j <= size - 3; j++) {
                int b = num[j];
                int t = target - a - b;
                int index1 = j + 1;
                int index2 = size - 1;
                while (index1 < index2) {
                    int c = num[index1];
                    int d = num[index2];
                    int sum = c + d;
                    if (sum == t) {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(a);
                        list.add(b);
                        list.add(c);
                        list.add(d);
                        result.add(list);
                        index1++;
                        index2--;
                    } else if (sum > t) {
                        index2--;
                    } else {
                        index1++;
                    }
                }
            }
        }
        return new ArrayList<List<Integer>>(result);
    }

    /**
     * 19. Remove Nth Node From End of List
     * Given a linked list, remove the nth node from the end of list and return its head.
     * For example,
     * Given linked list: 1->2->3->4->5, and n = 2.
     * After removing the second node from the end, the linked list becomes 1->2->3->5.
     * Note:
     * Given n will always be valid.
     * Try to do this in one pass.
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {

        if (head == null || n <= 0) {
            return head;
        }
        ListNode father = null;
        ListNode temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            if (count == n + 1) {
                father = head;
            } else if (father != null) {
                father = father.next;
            }
            temp = temp.next;
        }
        if (father == null) {
            if (count == n) {
                ListNode newHead = head.next;
                head.next = null;
                return newHead;
            }
        } else {
            father.next = father.next.next;
        }
        return head;
    }

    /**
     * 20. Valid Parentheses
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string
     * is valid.
     * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
     */
    public static boolean isValid(String s) {

        int size = s.length();
        if (size == 0 || size % 2 != 0) {
            return false;
        }
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.isEmpty() || c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if ((c == ')' && stack.peek() == '(') || (c == ']' && stack.peek() == '[') || (c == '}' && stack
                        .peek() == '{')) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 21. Merge Two Sorted Lists
     * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together
     * the nodes of the first two lists.
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        ListNode head = null;
        ListNode temp = null;
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        while (temp1 != null && temp2 != null) {
            ListNode use;
            if (temp1.val <= temp2.val) {
                use = temp1;
                temp1 = temp1.next;
                use.next = null;
            } else {
                use = temp2;
                temp2 = temp2.next;
                use.next = null;
            }
            if (head == null) {
                head = use;
                temp = head;
            } else {
                temp.next = use;
                temp = temp.next;
            }
        }
        if (temp2 != null) {
            temp.next = temp2;
        } else if (temp1 != null) {
            temp.next = temp1;
        }
        return head;
    }

    /**
     * 22. Generate Parentheses
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     * For example, given n = 3, a solution set is:
     * "((()))", "(()())", "(())()", "()(())", "()()()"
     */
    public static List<String> generateParenthesis(int n) {

        if (n <= 0) {
            return new ArrayList<String>();
        }
        Set<String> set = new HashSet<String>();
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                set.add("()");
            } else {
                Set<String> temp = new HashSet<String>();
                for (String elem : set) {
                    for (int j = -1; j < elem.length(); j++) {
                        temp.add("(" + elem.substring(0, j + 1) + ")" + (j == elem.length() - 1 ? "" : elem
                                .substring(j + 1, elem.length())));
                    }
                }
                set = temp;
            }
        }
        return new ArrayList<String>(set);
    }

    /**
     * 23. Merge k Sorted Lists
     * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
     */
    public static ListNode mergeKLists(List<ListNode> lists) {

        if (lists == null || lists.size() == 0) {
            return null;
        }
        return _mergeKLists(lists, 0, lists.size() - 1);
    }

    public static ListNode _mergeKLists(List<ListNode> lists, int start, int end) {

        int size = end - start + 1;
        if (size == 0) {
            return null;
        } else if (size == 1) {
            return lists.get(start);
        } else if (size == 2) {
            return _mergeKLists(lists.get(start), lists.get(end));
        }
        int mid = (start + end) / 2;
        return _mergeKLists(_mergeKLists(lists, start, mid), _mergeKLists(lists, mid + 1, end));
    }

    public static ListNode _mergeKLists(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        ListNode head = null;
        ListNode temp = null;
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        while (temp1 != null && temp2 != null) {
            ListNode node = null;
            if (temp1.val <= temp2.val) {
                node = new ListNode(temp1.val);
                temp1 = temp1.next;
            } else {
                node = new ListNode(temp2.val);
                temp2 = temp2.next;
            }
            if (head == null) {
                head = node;
                temp = head;
            } else {
                temp.next = node;
                temp = temp.next;
            }
        }
        if (temp1 == null && temp2 != null) {
            while (temp2 != null) {
                temp.next = new ListNode(temp2.val);
                temp = temp.next;
                temp2 = temp2.next;
            }
        } else if (temp1 != null && temp2 == null) {
            while (temp1 != null) {
                temp.next = new ListNode(temp1.val);
                temp = temp.next;
                temp1 = temp1.next;
            }
        }
        return head;
    }

    /**
     * 24. Swap Nodes in Pairs
     * Given a linked list, swap every two adjacent nodes and return its head.
     * For example,
     * Given 1->2->3->4, you should return the list as 2->1->4->3.
     * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself
     * can be changed.
     */
    public static ListNode swapPairs(ListNode head) {

        if (head == null) {
            return null;
        }
        ListNode newHead = null;
        ListNode parent = null;
        ListNode temp = head;
        while (temp != null) {
            ListNode next = temp.next;
            if (next == null) {
                return newHead == null ? head : newHead;
            }
            ListNode nextNext = next.next;
            next.next = temp;
            temp.next = nextNext;
            if (newHead == null) {
                newHead = next;
            } else {
                parent.next = next;
            }
            parent = temp;
            temp = nextNext;
        }
        return newHead;
    }

    /**
     * 25. Reverse Nodes in k-Group
     * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
     * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
     * You may not alter the values in the nodes, only nodes itself may be changed.
     * Only constant memory is allowed.
     * For example,
     * Given this linked list: 1->2->3->4->5
     * For k = 2, you should return: 2->1->4->3->5
     * For k = 3, you should return: 3->2->1->4->5
     */
    public static ListNode reverseKGroup(ListNode head, int k) {

        if (head == null || k <= 1) {
            return head;
        }
        ListNode newHead = null;
        ListNode parent = null;
        ListNode temp = head;
        while (temp != null) {
            ListNode subHead = _reverseKGroup(temp, k);
            if (parent == null) {
                newHead = subHead;
            } else {
                parent.next = subHead;
            }
            parent = temp;
            temp = temp.next;
        }
        return newHead;
    }

    public static ListNode _reverseKGroup(ListNode head, int k) {

        if (head == null || head.next == null || k <= 1) {
            return head;
        }
        int size = 0;
        ListNode tempCount = head;
        while (tempCount != null) {
            size++;
            tempCount = tempCount.next;
        }
        if (size < k) {
            return head;
        }
        ListNode temp1 = head;
        ListNode temp2 = temp1.next;
        head.next = null;
        int count = 2;
        while (temp2 != null && count++ <= k) {
            ListNode temp = temp2.next;
            temp2.next = temp1;
            temp1 = temp2;
            temp2 = temp;
        }
        head.next = temp2;
        return temp1;
    }

    /**
     * 26. Remove Duplicates from Sorted Array
     * Given a sorted array, remove the duplicates in place such that each element appear only once and return the
     * new length.
     * Do not allocate extra space for another array, you must do this in place with constant memory.
     * For example,
     * Given input array A = [1,1,2],
     * Your function should return length = 2, and A is now [1,2].
     */
    public static int removeDuplicates(int[] A) {

        int size = A.length;
        if (size <= 1) {
            return size;
        }
        int index1 = 0;
        int index2 = 1;
        while (index1 < size) {
            while (index2 < A.length && A[index1] == A[index2]) {
                index2++;
                size--;
            }
            if (index2 - index1 > 1 && index2 < A.length) {
                A[index1 + 1] = A[index2];
            }
            index1++;
            index2++;
        }
        return size;
    }

    /**
     * 27. Remove Element
     * Given an array and a value, remove all instances of that value in place and return the new length.
     * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
     */
    public static int removeElement(int[] A, int elem) {

        int size = A.length;
        if (size < 1) {
            return size;
        }
        int index1 = 0;
        int index2 = A.length - 1;
        while (index1 < size) {
            if (A[index1] == elem) {
                swap(A, index1, index2);
                size--;
                index2--;
            } else {
                index1++;
            }
        }
        return size;
    }

    /**
     * 28. Implement strStr()
     * Implement strStr().
     * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     * Update (2014-11-02):
     * The signature of the function had been updated to return the index instead of the pointer. If you still see
     * your function signature returns a char * or String, please click the reload button to reset your code
     * definition.
     */
    public static int strStr(String haystack, String needle) {

        int sizeA = haystack.length();
        int sizeB = needle.length();
        if (sizeA == 0) {
            return sizeB == 0 ? 0 : -1;
        } else if (sizeB == 0) {
            return 0;
        } else if (sizeA < sizeB) {
            return -1;
        }
        for (int i = 0; i <= sizeA - sizeB; i++) {
            if (haystack.charAt(i) != needle.charAt(0)) {
                continue;
            }
            int j = i;
            for (; j < i + sizeB; j++) {
                if (haystack.charAt(j) != needle.charAt(j - i)) {
                    break;
                }
            }
            if (j == i + sizeB) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 29. Divide Two Integers
     * Divide two integers without using multiplication, division and mod operator.
     * If it is overflow, return MAX_INT.
     */
    public static int divide(int dividend, int divisor) {

        if (dividend == 0) {
            return 0;
        } else if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        long a = dividend;
        long b = divisor;
        boolean isNegative = false;
        if (a < 0) {
            a = -a;
            isNegative = true;
        }
        if (b < 0) {
            b = -b;
            isNegative = !isNegative;
        }
        long result = 0;
        while (a >= b) {
            int i = 1;
            long temp = b;
            while (a >= temp) {
                a -= temp;
                temp <<= 1;
                result += i;
                i <<= 1;
            }
        }
        if ((!isNegative && result > Integer.MAX_VALUE) || (isNegative && result - 1 > Integer.MAX_VALUE)) {
            return Integer.MAX_VALUE;
        }
        return isNegative ? (int) -result : (int) result;
    }


    /**
     * 30. Substring with Concatenation of All Words
     * You are given a string, S, and a list of words, L, that are all of the same length. Find all starting indices
     * of substring(s) in S that is a concatenation of each word in L exactly once and without any intervening
     * characters.
     * For example, given:
     * S: "barfoothefoobarman"
     * L: ["foo", "bar"]
     * You should return the indices: [0,9].
     * (order does not matter).
     */
    public static List<Integer> findSubstring(String S, String[] L) {

        int sizeS = S.length();
        int sizeL = L.length;
        if (sizeS == 0 || sizeL == 0) {
            return new ArrayList<Integer>();
        }
        List<Integer> result = new ArrayList<Integer>();
        int sizePerWord = L[0].length();
        HashMap<String, Integer> words = new HashMap<String, Integer>();
        for (String aL : L) {
            if (words.containsKey(aL)) {
                words.put(aL, words.get(aL) + 1);
            } else {
                words.put(aL, 1);
            }
        }
        HashMap<String, Integer> tempWords = new HashMap<String, Integer>();
        int endIndex = sizeS - sizePerWord * sizeL;
        int index1 = 0;
        int index2 = 0;
        int done = 0;
        while (index1 <= endIndex) {
            String w = S.substring(index2, index2 + sizePerWord);
            if (!words.containsKey(w)) {
                tempWords.clear();
                index1++;
                index2 = index1;
                done = 0;
                continue;
            }
            if (tempWords.containsKey(w)) {
                tempWords.put(w, tempWords.get(w) + 1);
            } else {
                tempWords.put(w, 1);
            }
            if (tempWords.get(w).equals(words.get(w))) {
                done++;
                if (done == words.size()) {
                    result.add(index1);
                    tempWords.clear();
                    index1++;
                    index2 = index1;
                    done = 0;
                } else {
                    index2 += sizePerWord;
                }
            } else if (tempWords.get(w) > words.get(w)) {
                tempWords.clear();
                index1++;
                index2 = index1;
                done = 0;
            } else {
                index2 += sizePerWord;
            }
        }
        return result;
    }

    /**
     * 31. Next Permutation
     * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of
     * numbers.
     * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in
     * ascending order).
     * The replacement must be in-place, do not allocate extra memory.
     * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand
     * column.
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     * 1,2,3,8,6 -> 1,2,6,3,8
     * 1,2,1,2,4
     * 4,2,2,1,1
     * 123213421
     */
    public static void nextPermutation(int[] num) {

        int size = num.length;
        if (size <= 1) {
            return;
        }
        int btmIndex = size - 1;
        for (; btmIndex > 0; btmIndex--) {
            if (num[btmIndex] > num[btmIndex - 1]) {
                int minIndex = btmIndex;
                while (minIndex < size && num[minIndex] > num[btmIndex - 1]) {
                    minIndex++;
                }
                minIndex--;
                int temp = num[minIndex];
                num[minIndex] = num[btmIndex - 1];
                num[btmIndex - 1] = temp;
                break;
            }
        }
        Arrays.sort(num, btmIndex, size);
    }

    /**
     * 32. Longest Valid Parentheses
     * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed)
     * parentheses substring.
     * For "(()", the longest valid parentheses substring is "()", which has length = 2.
     * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
     * )())
     */
    public static int longestValidParentheses(String s) {

        int size = s.length();
        if (size <= 1) {
            return 0;
        }
        int max = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < size; i++) {
            char c = s.charAt(i);
            if (stack.isEmpty() || c == '(' || s.charAt(stack.peek()) == ')') {
                stack.push(i);
            } else {
                stack.pop();
                max = Math.max(max, stack.isEmpty() ? (i + 1) : (i - stack.peek()));
            }
        }
        return max;
    }

    /**
     * 33. Search in Rotated Sorted Array
     * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
     * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
     * You are given a target value to search. If found in the array return its index, otherwise return -1.
     * You may assume no duplicate exists in the array.
     */
    public static int search(int[] A, int target) {

        if (A.length == 0) {
            return -1;
        }
        return _search(A, 0, A.length - 1, target);
    }

    public static int _search(int[] A, int start, int end, int target) {

        int size = end - start + 1;
        if (size <= 0) {
            return -1;
        } else if (size == 1) {
            return target == A[start] ? start : -1;
        } else if (size == 2) {
            if (A[start] == target) {
                return start;
            } else if (A[end] == target) {
                return end;
            } else {
                return -1;
            }
        }
        int mid = (start + end) / 2;
        if (A[mid] == target) {
            return mid;
        } else if (A[start] < A[mid]) {
            if (target >= A[start] && target <= A[mid]) {
                return _search(A, start, mid, target);
            } else {
                return _search(A, mid + 1, end, target);
            }
        } else {
            if (target >= A[start] || target <= A[mid]) {
                return _search(A, start, mid, target);
            } else {
                return _search(A, mid + 1, end, target);
            }
        }
    }

    /**
     * 34. Search for a Range
     * Given a sorted array of integers, find the starting and ending position of a given target value.
     * Your algorithm's runtime complexity must be in the order of O(log n).
     * If the target is not found in the array, return [-1, -1].
     * For example,
     * Given [5, 7, 7, 8, 8, 10] and target value 8,
     * return [3, 4].
     */
    public static int[] searchRange(int[] A, int target) {

        if (A.length == 0) {
            return new int[]{-1, -1};
        }
        return _searchRange(A, 0, A.length - 1, target);
    }

    public static int[] _searchRange(int[] A, int start, int end, int target) {

        int size = end - start + 1;
        if (size <= 0) {
            return new int[]{-1, -1};
        } else if (size == 1) {
            return A[start] == target ? new int[]{start, start} : new int[]{-1, -1};
        }
        int mid = (start + end) / 2;
        int left = mid - 1;
        int right = mid + 1;
        while (left >= start && A[left] == A[mid]) {
            left--;
        }
        while (right <= end && A[right] == A[mid]) {
            right++;
        }
        if (A[mid] == target) {
            return new int[]{left + 1, right - 1};
        } else if (A[mid] > target) {
            return _searchRange(A, start, left, target);
        } else {
            return _searchRange(A, right, end, target);
        }
    }

    /**
     * 35. Search Insert Position
     * Given a sorted array and a target value, return the index if the target is found. If not, return the index
     * where it would be if it were inserted in order.
     * You may assume no duplicates in the array.
     * Here are few examples.
     * [1,3,5,6], 5 → 2
     * [1,3,5,6], 2 → 1
     * [1,3,5,6], 7 → 4
     * [1,3,5,6], 0 → 0
     */
    public static int searchInsert(int[] A, int target) {

        if (A.length == 0) {
            return 0;
        }
        return _searchInsert(A, 0, A.length - 1, target);
    }

    public static int _searchInsert(int[] A, int start, int end, int target) {

        int size = end - start + 1;
        if (size <= 0) {
            return start;
        } else if (size == 1) {
            return A[start] >= target ? start : start + 1;
        }
        int mid = (start + end) / 2;
        if (A[mid] == target) {
            return mid;
        } else if (A[mid] > target) {
            return _searchInsert(A, start, mid - 1, target);
        } else {
            return _searchInsert(A, mid + 1, end, target);
        }
    }

    /**
     * 36. Valid Sudoku
     * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
     * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
     */
    public static boolean isValidSudoku(char[][] board) {

        if (board.length != 9 || board[0].length != 9) {
            return false;
        }
        Set<Character> setRow = new HashSet<Character>();
        Set<Character> setCol = new HashSet<Character>();
        Set<Character> setCub1 = new HashSet<Character>();
        Set<Character> setCub2 = new HashSet<Character>();
        Set<Character> setCub3 = new HashSet<Character>();
        for (int i = 0; i < 9; i++) {
            setRow.clear();
            setCol.clear();
            if (i % 3 == 0) {
                setCub1.clear();
                setCub2.clear();
                setCub3.clear();
            }
            for (int j = 0; j < 9; j++) {
                char c1 = board[i][j];
                char c2 = board[j][i];

                if (c1 != '.') {
                    int iCol = j / 3;
                    Set<Character> set = null;
                    if (iCol == 0) {
                        set = setCub1;
                    } else if (iCol == 1) {
                        set = setCub2;
                    } else {
                        set = setCub3;
                    }

                    if (setRow.contains(c1) || set.contains(c1)) {
                        return false;
                    }
                    setRow.add(c1);
                    set.add(c1);
                }

                if (c2 != '.') {
                    if (setCol.contains(c2)) {
                        return false;
                    }
                    setCol.add(c2);
                }
            }
        }
        return true;
    }

    /**
     * 37. Sudoku Solver
     * Write a program to solve a Sudoku puzzle by filling the empty cells.
     * Empty cells are indicated by the character '.'.
     * You may assume that there will be only one unique solution.
     */
    public static void solveSudoku(char[][] board) {

        if (board.length != 9 || board[0].length != 9) {
            return;
        }
        _solveSudoku(board, 0, 0);
    }

    public static boolean _solveSudoku(char[][] board, int i, int j) {

        if (i < 0 || i > 8 || j < 0 || j > 8) {
            return true;
        }
        int nextI = (j == 8) ? (i + 1) : i;
        int nextJ = (j == 8) ? 0 : (j + 1);
        if (board[i][j] == '.') {
            for (int k = 1; k <= 9; k++) {
                board[i][j] = Character.forDigit(k, 10);
                if (_isValidSudoku(board, i, j) && _solveSudoku(board, nextI, nextJ)) {
                    return true;
                }
                board[i][j] = '.';
            }
            return false;
        } else {
            if (!_isValidSudoku(board, i, j)) {
                return false;
            }
            return _solveSudoku(board, nextI, nextJ);
        }
    }

    public static boolean _isValidSudoku(char[][] board, int i, int j) {

        for (int k = 0; k < 9; k++) {
            if ((board[i][k] == board[i][j] && k != j) || (board[k][j] == board[i][j] && k != i)) {
                return false;
            }
        }
        int iRowStart = 3 * (i / 3);
        int iRowEnd = iRowStart + 2;
        int iColStart = 3 * (j / 3);
        int iColEnd = iColStart + 2;
        for (int k = iRowStart; k <= iRowEnd; k++) {
            for (int l = iColStart; l <= iColEnd; l++) {
                if (board[k][l] == board[i][j] && (k != i || l != j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 38. Count and Say
     * The count-and-say sequence is the sequence of integers beginning as follows:
     * 1, 11, 21, 1211, 111221, ...
     * 1 is read off as "one 1" or 11.
     * 11 is read off as "two 1s" or 21.
     * 21 is read off as "one 2, then one 1" or 1211.
     * Given an integer n, generate the nth sequence.
     * Note: The sequence of integers will be represented as a string.
     */
    public static String countAndSay(int n) {

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                builder.append("1");
            } else {
                int size = builder.length();
                int index = 0;
                while (index < size) {
                    char c = builder.charAt(index);
                    int count = 0;
                    while (index < size && builder.charAt(index) == c) {
                        count++;
                        index++;
                    }
                    builder.append(count).append(c);
                }
                builder.delete(0, size);
            }
        }
        return builder.toString();
    }

    /**
     * 39. Combination Sum
     * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the
     * candidate numbers sums to T.
     * The same repeated number may be chosen from C unlimited number of times.
     * Note:
     * All numbers (including target) will be positive integers.
     * Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
     * The solution set must not contain duplicate combinations.
     * For example, given candidate set 2,3,6,7 and target 7,
     * A solution set is:
     * [7]
     * [2, 2, 3]
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        if (candidates.length == 0) {
            return new ArrayList<List<Integer>>();
        }
        Arrays.sort(candidates);
        return new ArrayList<List<Integer>>(_combinationSum(candidates, 0, candidates.length - 1, target));
    }

    public static Set<List<Integer>> _combinationSum(int[] candidates, int start, int end, int target) {

        Set<List<Integer>> result = new HashSet<List<Integer>>();
        if (start > end) {
            return result;
        } else if (start == end) {
            if (candidates[start] > target || target % candidates[start] != 0) {
                return result;
            } else {
                int times = target / candidates[start];
                List<Integer> list = new ArrayList<Integer>();
                for (int i = 0; i < times; i++) {
                    list.add(candidates[start]);
                }
                result.add(list);
                return result;
            }
        }
        for (int i = start; i <= end && candidates[i] <= target; i++) {
            int times = target / candidates[i];
            for (int j = 1; j <= times; j++) {
                int newTarget = target - candidates[i] * j;
                List<Integer> head = new ArrayList<Integer>();
                for (int k = 0; k < j; k++) {
                    head.add(candidates[i]);
                }
                if (newTarget == 0) {
                    result.add(head);
                } else {
                    Set<List<Integer>> rest = _combinationSum(candidates, i + 1, end, newTarget);
                    if (!rest.isEmpty()) {
                        for (List<Integer> _rest : rest) {
                            List<Integer> list = new ArrayList<Integer>(head);
                            list.addAll(_rest);
                            result.add(list);
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 40. Combination Sum II
     * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where
     * the candidate numbers sums to T.
     * Each number in C may only be used once in the combination.
     * Note:
     * All numbers (including target) will be positive integers.
     * Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
     * The solution set must not contain duplicate combinations.
     * For example, given candidate set 10,1,2,7,6,1,5 and target 8,
     * A solution set is:
     * [1, 7]
     * [1, 2, 5]
     * [2, 6]
     * [1, 1, 6]
     */
    public static List<List<Integer>> combinationSum2(int[] num, int target) {

        if (num.length == 0) {
            return new ArrayList<List<Integer>>();
        }
        Arrays.sort(num);
        return new ArrayList<List<Integer>>(_combinationSum2(num, 0, target));
    }

    public static Set<List<Integer>> _combinationSum2(int[] candidates, int start, int target) {

        Set<List<Integer>> result = new HashSet<List<Integer>>();
        for (int i = start; i < candidates.length && candidates[i] <= target; i++) {
            List<Integer> head = new ArrayList<Integer>();
            head.add(candidates[i]);
            if (target - candidates[i] == 0) {
                result.add(head);
            } else {
                Set<List<Integer>> rest = _combinationSum2(candidates, i + 1, target - candidates[i]);
                for (List<Integer> _rest : rest) {
                    List<Integer> list = new ArrayList<Integer>(head);
                    list.addAll(_rest);
                    result.add(list);
                }
            }
        }
        return result;
    }

    /**
     * 41. First Missing Positive
     * Given an unsorted integer array, find the first missing positive integer.
     * For example,
     * Given [1,2,0] return 3,
     * and [3,4,-1,1] return 2.
     * Your algorithm should run in O(n) time and uses constant space.
     */
    public static int firstMissingPositive(int[] A) {

        if (A.length == 0) {
            return 1;
        }
        for (int i = 0; i < A.length; i++) {
            while (A[i] != i + 1 && A[i] - 1 >= 0 && A[i] - 1 < A.length && A[A[i] - 1] != A[i]) {
                int temp = A[A[i] - 1];
                A[A[i] - 1] = A[i];
                A[i] = temp;
            }
        }
        for (int i = 0; i < A.length; i++) {
            if (A[i] != i + 1) {
                return i + 1;
            }
        }
        return A.length + 1;
    }

    /**
     * 42. Trapping Rain Water
     * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much
     * water it is able to trap after raining.
     * For example,
     * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
     */
    public static int trap(int[] A) {

        if (A.length <= 2) {
            return 0;
        }
        int[] maxLeft = new int[A.length];
        int[] maxRight = new int[A.length];
        int maxL = A[0];
        int maxR = A[A.length - 1];
        for (int i = 0; i < A.length; i++) {
            maxLeft[i] = maxL;
            maxL = Math.max(maxL, A[i]);
        }
        int total = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            maxRight[i] = maxR;
            maxR = Math.max(maxR, A[i]);

            int left = maxLeft[i];
            int right = maxRight[i];
            if (A[i] < left && A[i] < right) {
                total += Math.min(left, right) - A[i];
            }
        }
        return total;
    }

    /**
     * 43. Multiply Strings
     * Given two numbers represented as strings, return multiplication of the numbers as a string.
     * Note: The numbers can be arbitrarily large and are non-negative.
     */
    public static String multiply(String num1, String num2) {

        if (num1.length() == 0) {
            return num2;
        } else if (num2.length() == 0) {
            return num1;
        }
        String result = "";
        for (int i = num2.length() - 1; i >= 0; i--) {
            int b = num2.charAt(i) - 48;
            int tailingZero = num2.length() - i - 1;

            StringBuilder strA = new StringBuilder();
            for (int j = 0; j < tailingZero; j++) {
                strA.append(0);
            }
            int carryOver = 0;
            for (int j = num1.length() - 1; j >= 0; j--) {
                int a = num1.charAt(j) - 48;
                int c = a * b + carryOver;
                carryOver = c / 10;
                c %= 10;
                strA.append(c);
            }
            if (carryOver > 0) {
                strA.append(carryOver);
            }
            result = addString(result, strA.reverse().toString());
        }
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) != '0') {
                return result.substring(i);
            }
        }
        return "0";
    }

    public static String addString(String num1, String num2) {

        int size1 = num1.length();
        int size2 = num2.length();
        if (size1 == 0) {
            return num2;
        } else if (size2 == 0) {
            return num1;
        }
        int index1 = size1 - 1;
        int index2 = size2 - 1;
        boolean markOne = false;
        StringBuilder result = new StringBuilder();
        while (index1 >= 0 || index2 >= 0) {
            int a = (index1 >= 0) ? (num1.charAt(index1) - 48) : 0;
            int b = (index2 >= 0) ? (num2.charAt(index2) - 48) : 0;
            int c = a + b + (markOne ? 1 : 0);
            markOne = c >= 10;
            c %= 10;
            result.append(c);
            index1--;
            index2--;
        }
        if (markOne) {
            result.append(1);
        }
        return result.reverse().toString();
    }


    /**
     * 44. Wildcard Matching
     * Implement wildcard pattern matching with support for '?' and '*'.
     * '?' Matches any single character.
     * '*' Matches any sequence of characters (including the empty sequence).
     * The matching should cover the entire input string (not partial).
     * The function prototype should be:
     * bool isMatch(const char *s, const char *p)
     * Some examples:
     * isMatch("aa","a") → false
     * isMatch("aa","aa") → true
     * isMatch("aaa","aa") → false
     * isMatch("aa", "*") → true
     * isMatch("aa", "a*") → true
     * isMatch("ab", "?*") → true
     * isMatch("aab", "c*a*b") → false
     */
    public static boolean isMatch2(String s, String p) {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) != '*' || builder.length() == 0 || builder.charAt(builder.length() - 1) != '*') {
                builder.append(p.charAt(i));
            }
        }
        p = builder.toString();
        int size1 = s.length();
        int size2 = p.length();
        if (size2 == 0) {
            return size1 == 0;
        } else if (size1 == 0) {
            return p.equals("*");
        }
        int index1 = 0;
        int index2 = 0;
        int lastIndex1 = -1;
        int lastIndex2 = -1;
        boolean meetStar = false;
        while (index1 < size1) {
            char c1 = s.charAt(index1);
            char c2 = index2 >= size2 ? '\0' : p.charAt(index2);
            if (c2 == '?') {
                index1++;
                index2++;
            } else if (c2 == '*') {
                if (index2 == size2 - 1) {
                    return true;
                }
                meetStar = true;
                lastIndex1 = index1;
                lastIndex2 = ++index2;
            } else {
                if (c1 != c2) {
                    if (meetStar) {
                        index1 = lastIndex1 + 1;
                        index2 = lastIndex2;
                        lastIndex1 = index1;
                    } else {
                        return false;
                    }
                } else {
                    index1++;
                    index2++;
                }
            }
        }
        return index2 >= size2 || (index2 == size2 - 1 && p.charAt(index2) == '*');
    }

    /**
     * 45. Jump Game II
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Your goal is to reach the last index in the minimum number of jumps.
     * For example:
     * Given array A = [2,3,1,1,4]
     * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the
     * last index.)
     */
    public static int jump(int[] A) {

        if (A.length < 2) {
            return 0;
        }
        int step = 0;
        int start = 0;
        int end = 0;

        while (end < A.length - 1) {
            int furthest = 0;
            for (int i = start; i <= end; ++i) {
                furthest = Math.max(furthest, i + A[i]);
            }
            if (furthest <= end && furthest < A.length - 1) {
                return 0;
            }
            start = end + 1;
            end = furthest;
            step++;
        }
        return step;
    }

    public static List<List<Integer>> getJumpPath(int[] A) {

        if (A.length < 2) {
            return new ArrayList<List<Integer>>();
        }
        int start = 0;
        int end = 0;

        List<Set<Integer>> prevs = new ArrayList<Set<Integer>>();
        for (int i = 0; i < A.length; i++) {
            prevs.add(new HashSet<Integer>());
        }
        while (end < A.length - 1) {
            int furthest = 0;
            for (int i = start; i <= end; ++i) {
                for (int j = i + 1; j <= Math.min(i + A[i], A.length - 1); j++) {
                    prevs.get(j).add(i);
                }
                furthest = Math.max(furthest, i + A[i]);
            }
            if (furthest <= end && furthest < A.length - 1) {
                return new ArrayList<List<Integer>>();
            }
            start = end + 1;
            end = furthest;
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        _DFS(prevs, A.length - 1, new ArrayList<Integer>(), result);
        return result;
    }

    public static void _DFS(List<Set<Integer>> map, int node, List<Integer> path, List<List<Integer>> result) {

        if (node < 0 || node >= map.size()) {
            return;
        }
        path.add(0, node);
        if (map.get(node).isEmpty()) {
            result.add(new ArrayList<Integer>(path));
        } else {
            Set<Integer> children = map.get(node);
            for (Integer child : children) {
                _DFS(map, child, path, result);
            }
        }
        path.remove(0);
    }

    /**
     * 46. Permutations
     * Given a collection of numbers, return all possible permutations.
     * For example,
     * [1,2,3] have the following permutations:
     * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
     */
    public static List<List<Integer>> permute(int[] num) {

        if (num.length == 0) {
            return new ArrayList<List<Integer>>();
        }
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        for (int i = 0; i < num.length; i++) {
            int target = num[i];
            if (set.isEmpty()) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(target);
                set.add(list);
            } else {
                Set<List<Integer>> temp = new HashSet<List<Integer>>();
                for (List<Integer> aSet : set) {
                    for (int j = 0; j <= aSet.size(); j++) {
                        List<Integer> list = new ArrayList<Integer>(aSet);
                        if (j == aSet.size()) {
                            list.add(target);
                        } else {
                            list.add(j, target);
                        }
                        temp.add(list);
                    }
                }
                set = temp;
            }
        }
        return new ArrayList<List<Integer>>(set);
    }


    /**
     * 47. Permutations II
     * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
     * For example,
     * [1,1,2] have the following unique permutations:
     * [1,1,2], [1,2,1], and [2,1,1].
     */
    public static List<List<Integer>> permuteUnique(int[] num) {

        if (num.length == 0) {
            return new ArrayList<List<Integer>>();
        }
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        for (int i = 0; i < num.length; i++) {
            int target = num[i];
            if (set.isEmpty()) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(target);
                set.add(list);
            } else {
                Set<List<Integer>> temp = new HashSet<List<Integer>>();
                for (List<Integer> aSet : set) {
                    for (int j = 0; j <= aSet.size(); j++) {
                        List<Integer> list = new ArrayList<Integer>(aSet);
                        if (j == aSet.size()) {
                            list.add(target);
                        } else {
                            list.add(j, target);
                        }
                        temp.add(list);
                    }
                }
                set = temp;
            }
        }
        return new ArrayList<List<Integer>>(set);
    }

    /**
     * 48. Rotate Image
     * You are given an n x n 2D matrix representing an image.
     * Rotate the image by 90 degrees (clockwise).
     * Follow up:
     * Could you do this in-place?
     * (1,0) -> (0,3)
     * (0,1) -> (1,4)
     * #,#,#,#,# 5, 2   4,2
     * #,#,#,#,#
     * 1,2,3,4,5
     * #,#,3,#,#
     * #,#,3,#,#
     * (i,j) -> (j, size-1-i)
     */
    public static void rotate(int[][] matrix) {

        int m = matrix.length;
        if (m <= 1) {
            return;
        } else if (matrix[0].length != m) {
            return;
        }
        int rowEnd = m % 2 == 0 ? (m - 1) / 2 : (m - 1) / 2 - 1;
        int colEnd = (m - 1) / 2;
        for (int i = 0; i <= rowEnd; i++) {
            for (int j = 0; j <= colEnd; j++) {
                int a = matrix[i][j];
                int b = matrix[j][m - i - 1];
                int c = matrix[m - i - 1][m - j - 1];
                int d = matrix[m - j - 1][i];

                matrix[i][j] = d;
                matrix[j][m - i - 1] = a;
                matrix[m - i - 1][m - j - 1] = b;
                matrix[m - j - 1][i] = c;
            }
        }
    }

    /**
     * 49. Anagrams
     * Given an array of strings, return all groups of strings that are anagrams.
     * Note: All inputs will be in lower-case.
     */
    public static List<String> anagrams(String[] strs) {

        Map<Map<Character, Integer>, List<String>> map = new HashMap<Map<Character, Integer>, List<String>>();
        for (String str : strs) {
            Map<Character, Integer> key = new HashMap<Character, Integer>();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (key.containsKey(c)) {
                    key.put(c, key.get(c) + 1);
                } else {
                    key.put(c, 1);
                }
            }
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<String>());
            }
            map.get(key).add(str);
        }
        List<String> result = new ArrayList<String>();
        for (Map<Character, Integer> key : map.keySet()) {
            if (map.get(key).size() > 1) {
                result.addAll(map.get(key));
            }
        }
        return result;
    }

    /**
     * 50. Pow(x, n)
     * Implement pow(x, n).
     */
    public static double pow(double x, int n) {

        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return x;
        } else if (n == -1) {
            return 1 / x;
        }
        boolean isNegative = n < 0;
        n = n < 0 ? -n : n;
        double result = _pow(x, n);
        return isNegative ? 1 / result : result;
    }

    public static double _pow(double x, int n) {

        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return x;
        }
        double half = _pow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    /**
     * 51. N-Queens
     * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each
     * other.
     * Given an integer n, return all distinct solutions to the n-queens puzzle.
     * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both
     * indicate
     * a queen and an empty space respectively.
     * For example,
     * There exist two distinct solutions to the 4-queens puzzle:
     * [
     * [".Q..",  // Solution 1
     * "...Q",
     * "Q...",
     * "..Q."],
     * ["..Q.",  // Solution 2
     * "Q...",
     * "...Q",
     * ".Q.."]
     * ]
     */
    public static List<String[]> solveNQueens(int n) {

        if (n <= 0) {
            return new ArrayList<String[]>();
        }
        char[][] board = new char[n][n];
        List<String[]> result = new ArrayList<String[]>();
        _solveNQueens(board, 0, result);
        return result;
    }

    public static void _solveNQueens(char[][] board, int i, List<String[]> boards) {

        for (int j = 0; j < board.length; j++) {
            if (isValidNQueens(board, i, j)) {
                board[i][j] = 'Q';
                if (i == board.length - 1) {
                    String[] strs = new String[board.length];
                    StringBuilder builder = new StringBuilder();
                    for (int k = 0; k < board.length; k++) {
                        char[] row = board[k];
                        for (char c : row) {
                            if (c != 'Q') {
                                builder.append('.');
                            } else {
                                builder.append(c);
                            }
                        }
                        strs[k] = builder.toString();
                        builder.delete(0, builder.length());
                    }
                    boards.add(strs);
                } else {
                    _solveNQueens(board, i + 1, boards);
                }
            }
            board[i][j] = '.';
        }
    }

    public static boolean isValidNQueens(char[][] board, int i, int j) {

        for (int k = 0; k < board.length; k++) {
            if ((board[k][j] == 'Q' && k != i) || (board[i][k] == 'Q' && k != j)) {
                return false;
            }
        }
        for (int m = i - 1, n = j - 1; m >= 0 && n >= 0; m--, n--) {
            if (board[m][n] == 'Q') {
                return false;
            }
        }
        for (int m = i + 1, n = j + 1; m < board.length && n < board.length; m++, n++) {
            if (board[m][n] == 'Q') {
                return false;
            }
        }
        for (int m = i - 1, n = j + 1; m >= 0 && n < board.length; m--, n++) {
            if (board[m][n] == 'Q') {
                return false;
            }
        }
        for (int m = i + 1, n = j - 1; m < board.length && n >= 0; m++, n--) {
            if (board[m][n] == 'Q') {
                return false;
            }
        }
        return true;
    }

    /**
     * 52. N-Queens II
     * Follow up for N-Queens problem.
     * Now, instead outputting board configurations, return the total number of distinct solutions.
     */
    public static int totalNQueens(int n) {

        if (n <= 0) {
            return 0;
        }
        char[][] board = new char[n][n];
        int[] num = new int[1];
        _solveNQueens(board, 0, num);
        return num[0];
    }

    public static void _solveNQueens(char[][] board, int i, int[] num) {

        for (int j = 0; j < board.length; j++) {
            if (board[i][j] != 'Q') {
                if (isValidNQueens(board, i, j)) {
                    board[i][j] = 'Q';
                    if (i == board.length - 1) {
                        num[0]++;
                    } else {
                        _solveNQueens(board, i + 1, num);
                    }
                }
                board[i][j] = '.';
            }
        }
    }


    /**
     * 53. Maximum Subarray
     * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
     * For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
     * the contiguous subarray [4,−1,2,1] has the largest sum = 6.
     * click to show more practice.
     * More practice:
     * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach,
     * which is more subtle.
     */
    public static int maxSubArray(int[] A) {

        if (A.length == 0) {
            return 0;
        }
        //                int max = A[0];
        //                int tempMax = A[0];
        //                for (int i = 1; i < A.length; i++) {
        //                    tempMax = Math.max(A[i], A[i] + tempMax);
        //                    max = Math.max(max, tempMax);
        //                }
        //                return max;
        return _maxSubArray(A, 0, A.length - 1);
    }

    public static int _maxSubArray(int[] A, int start, int end) {

        if (start > end) {
            return Integer.MIN_VALUE;
        } else if (start == end) {
            return A[start];
        }
        int mid = (start + end) / 2;
        int maxL = _maxSubArray(A, start, mid - 1);
        int maxR = _maxSubArray(A, mid + 1, end);
        int midML = A[mid];
        int midMR = A[mid];
        int tempM = A[mid];
        int index = mid - 1;
        while (index >= start) {
            tempM += A[index--];
            midML = Math.max(midML, tempM);
        }
        tempM = A[mid];
        index = mid + 1;
        while (index <= end) {
            tempM += A[index++];
            midMR = Math.max(midMR, tempM);
        }
        return Math.max(Math.max(maxL, maxR), midML + midMR - A[mid]);
    }

    /**
     * 54. Spiral Matrix
     * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
     * For example,
     * Given the following matrix:
     * [
     * [ 1, 2, 3 ],
     * [ 4, 5, 6 ],
     * [ 7, 8, 9 ]
     * ]
     * You should return [1,2,3,6,9,8,7,4,5].
     */
    public static List<Integer> spiralOrder(int[][] matrix) {

        int m = matrix.length;
        if (m == 0) {
            return new ArrayList<Integer>();
        }
        int n = matrix[0].length;
        if (n == 0) {
            return new ArrayList<Integer>();
        }
        int size = m * n;
        boolean[][] used = new boolean[m][n];
        int row = 0;
        int col = 0;
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            result.add(matrix[row][col]);
            used[row][col] = true;

            boolean up = row > 0 && !used[row - 1][col];
            boolean down = row < m - 1 && !used[row + 1][col];
            boolean left = col > 0 && !used[row][col - 1];
            boolean right = col < n - 1 && !used[row][col + 1];
            if (!up && !left && right) {
                col++;
            } else if (!up && down && !right) {
                row++;
            } else if (left && !down && !right) {
                col--;
            } else if (up && !left && !down) {
                row--;
            }
        }
        return result;
    }


    /**
     * 55. Jump Game
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Determine if you are able to reach the last index.
     * For example:
     * A = [2,3,1,1,4], return true.
     * A = [3,2,1,0,4], return false.
     */
    public static boolean canJump(int[] A) {

        if (A.length <= 1) {
            return true;
        }
        int start = 0;
        int end = 0;
        while (end < A.length - 1) {
            int furthest = 0;
            for (int i = start; i <= end; i++) {
                furthest = Math.max(furthest, i + A[i]);
            }
            if (furthest <= end) {
                return false;
            }
            start = end + 1;
            end = furthest;
        }
        return true;
    }

    /**
     * 56. Merge Intervals
     * Given a collection of intervals, merge all overlapping intervals.
     * For example,
     * Given [1,3],[2,6],[8,10],[15,18],
     * return [1,6],[8,10],[15,18].
     */
    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }

    public static List<Interval> merge(List<Interval> intervals) {

        List<Interval> result = new ArrayList<Interval>();
        for (int i = 0; i < intervals.size(); i++) {
            insert(result, intervals.get(i));
        }
        return result;
    }

    /**
     * 57. Insert Interval
     * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
     * You may assume that the intervals were initially sorted according to their start times.
     * Example 1:
     * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
     * Example 2:
     * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
     * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
     */
    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {

        for (int i = 0; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            if (interval.start > newInterval.end) {
                intervals.add(i, newInterval);
                return intervals;
            } else if (interval.end < newInterval.start) {
                continue;
            } else {
                newInterval.start = Math.min(newInterval.start, interval.start);
                newInterval.end = Math.max(newInterval.end, interval.end);
                intervals.remove(i--);
            }
        }
        intervals.add(newInterval);
        return intervals;
    }

    /**
     * 58. Length of Last Word
     * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of
     * last
     * word in the string.
     * If the last word does not exist, return 0.
     * Note: A word is defined as a character sequence consists of non-space characters only.
     * For example,
     * Given s = "Hello World",
     * return 5.
     */
    public static int lengthOfLastWord(String s) {

        int len = 0;
        boolean started = false;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (started) {
                    break;
                }
            } else {
                started = true;
                len++;
            }
        }
        return len;
    }

    /**
     * 59. Spiral Matrix II
     * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
     * For example,
     * Given n = 3,
     * You should return the following matrix:
     * [
     * [ 1, 2, 3 ],
     * [ 8, 9, 4 ],
     * [ 7, 6, 5 ]
     * ]
     */
    public static int[][] generateMatrix(int n) {

        if (n <= 0) {
            return new int[0][0];
        }
        int[][] result = new int[n][n];
        boolean[][] used = new boolean[n][n];
        int row = 0;
        int col = 0;
        int i = 1;
        while (!used[row][col]) {
            result[row][col] = i++;
            used[row][col] = true;

            boolean up = row > 0 && !used[row - 1][col];
            boolean down = row < n - 1 && !used[row + 1][col];
            boolean left = col > 0 && !used[row][col - 1];
            boolean right = col < n - 1 && !used[row][col + 1];
            if (!up && !left && right) {
                col++;
            } else if (!up && down && !right) {
                row++;
            } else if (left && !down && !right) {
                col--;
            } else if (up && !left && !down) {
                row--;
            }
        }
        return result;
    }


    /**
     * 60. Permutation Sequence
     * The set [1,2,3,…,n] contains a total of n! unique permutations.
     * By listing and labeling all of the permutations in order,
     * We get the following sequence (ie, for n = 3):
     * "123"1
     * "132"2
     * "213"3
     * "231"4
     * "312"5
     * "321"6
     * Given n and k, return the kth permutation sequence.
     * Note: Given n will be between 1 and 9 inclusive.
     */
    public static String getPermutation(int n, int k) {

        if (n <= 0) {
            return "";
        }
        int totalSize = 1;
        boolean[] used = new boolean[n];
        for (int i = 1; i <= n; i++) {
            totalSize *= i;
        }
        int temp = n;
        StringBuilder builder = new StringBuilder();
        while (totalSize >= 1 && temp > 0) {
            k = k % totalSize == 0 ? totalSize : k % totalSize;
            totalSize /= (temp--);
            int index = (k - 1) / totalSize + 1;
            int target = -1;
            for (int i = 0; i < used.length; i++) {
                target = i + 1;
                if (!used[i] && --index == 0) {
                    used[i] = true;
                    break;
                }
            }
            builder.append(target);
        }
        return builder.toString();
    }

    /**
     * 61. Rotate List
     * Given a list, rotate the list to the right by k places, where k is non-negative.
     * For example:
     * Given 1->2->3->4->5->NULL and k = 2,
     * return 4->5->1->2->3->NULL.
     */
    public static ListNode rotateRight(ListNode head, int n) {

        if (head == null || n <= 0) {
            return head;
        }
        int size = 0;
        ListNode temp = head;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        int step = n % size == 0 ? size : n % size;
        if (step == size || size == 1) {
            return head;
        }
        ListNode newHead = null;
        ListNode tailleft = null;
        temp = head;
        while (temp.next != null) {
            temp = temp.next;
            if (--step == 0) {
                tailleft = head;
            } else if (tailleft != null) {
                tailleft = tailleft.next;
            }
        }
        if (tailleft != null && temp != null) {
            newHead = tailleft.next;
            tailleft.next = null;
            temp.next = head;
            return newHead;
        }
        return head;
    }

    /**
     * 62. Unique Paths
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right
     * corner of the grid (marked 'Finish' in the diagram below).
     * How many possible unique paths are there?
     */
    public static int uniquePaths(int m, int n) {

        if (m <= 0 || n <= 0) {
            return 0;
        }
        int[][] d = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    d[i][j] = 1;
                } else {
                    d[i][j] = (i == 0 ? 0 : d[i - 1][j]) + (j == 0 ? 0 : d[i][j - 1]);
                }
            }
        }
        return d[m - 1][n - 1];
    }

    /**
     * 63. Unique Paths II
     * Follow up for "Unique Paths":
     * Now consider if some obstacles are added to the grids. How many unique paths would there be?
     * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
     * For example,
     * There is one obstacle in the middle of a 3x3 grid as illustrated below.
     * [
     * [0,0,0],
     * [0,1,0],
     * [0,0,0]
     * ]
     * The total number of unique paths is 2.
     * Note: m and n will be at most 100.
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        if (m <= 0) {
            return 0;
        }
        int n = obstacleGrid[0].length;
        if (n <= 0) {
            return 0;
        }
        int[][] d = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    d[i][j] = obstacleGrid[i][j] == 1 ? 0 : 1;
                } else {
                    d[i][j] = obstacleGrid[i][j] == 1 ? 0 : (i == 0 ? 0 : d[i - 1][j]) + (j == 0 ? 0 : d[i][j - 1]);
                }
            }
        }
        return d[m - 1][n - 1];
    }

    /**
     * 64. Minimum Path Sum
     * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes
     * the sum of all numbers along its path.
     * Note: You can only move either down or right at any point in time.
     */
    public static int minPathSum(int[][] grid) {

        int m = grid.length;
        if (m <= 0) {
            return 0;
        }
        int n = grid[0].length;
        if (n <= 0) {
            return 0;
        }
        int[][] d = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    d[i][j] = grid[i][j];
                } else if (i == 0) {
                    d[i][j] = d[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    d[i][j] = d[i - 1][j] + grid[i][j];
                } else {
                    d[i][j] = Math.min(d[i - 1][j], d[i][j - 1]) + grid[i][j];
                }
            }
        }
        return d[m - 1][n - 1];
    }

    /**
     * 65. Valid Number
     * Validate if a given string is numeric.
     * Some examples:
     * "0" => true
     * " 0.1 " => true
     * "abc" => false
     * "1 a" => false
     * "2e10" => true
     * Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front
     * before implementing one.
     */
    public static boolean isNumber(String s) {

        boolean hasDigit = false;
        boolean hasDot = false;
        boolean hasSymbol = false;
        boolean hasExp = false;
        boolean hasStarted = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char prev = i == 0 ? '\0' : s.charAt(i - 1);
            if (c == ' ') {
                if (hasStarted) {
                    while (i < s.length()) {
                        if (s.charAt(i++) != ' ') {
                            return false;
                        }
                    }
                }
            } else if (c == '+' || c == '-') {
                if (hasSymbol || hasDigit || (!hasExp && hasDot)) {
                    return false;
                }
                hasStarted = true;
                hasSymbol = true;
            } else if (c == '.') {
                if (hasDot || hasExp) {
                    return false;
                }
                hasStarted = true;
                hasDot = true;
            } else if (c == 'E' || c == 'e') {
                if (!hasStarted || hasExp || !hasDigit) {
                    return false;
                }
                hasStarted = true;
                hasExp = true;
                hasSymbol = false;
                hasDigit = false;
            } else if (c >= '0' && c <= '9') {
                hasStarted = true;
                hasDigit = true;
            } else {
                return false;
            }
        }
        return hasDigit;
    }

    /**
     * 66. Plus One
     * Given a non-negative number represented as an array of digits, plus one to the number.
     * The digits are stored such that the most significant digit is at the head of the list.
     */
    public static int[] plusOne(int[] digits) {

        int size = digits.length;
        if (size == 0) {
            return new int[]{1};
        }
        boolean markOne = false;
        List<Integer> list = new ArrayList<Integer>();
        for (int i = size - 1; i >= 0; i--) {
            int c = 0;
            if (i == size - 1) {
                c = digits[i] + 1;
            } else {
                c = digits[i] + (markOne ? 1 : 0);
            }
            markOne = c >= 10;
            c %= 10;
            list.add(0, c);
        }
        if (markOne) {
            list.add(0, 1);
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }


    /**
     * 67. Add Binary
     * Given two binary strings, return their sum (also a binary string).
     * For example,
     * a = "11"
     * b = "1"
     * Return "100".
     */
    public static String addBinary(String a, String b) {

        boolean markOne = false;
        int index1 = a.length() - 1;
        int index2 = b.length() - 1;
        StringBuilder builder = new StringBuilder();
        while (index1 >= 0 || index2 >= 0) {
            int c1 = index1 < 0 ? 0 : a.charAt(index1) - 48;
            int c2 = index2 < 0 ? 0 : b.charAt(index2) - 48;
            int c = c1 + c2 + (markOne ? 1 : 0);
            markOne = c >= 2;
            c %= 2;
            builder.append(c);
            index1--;
            index2--;
        }
        if (markOne) {
            builder.append(1);
        }
        return builder.length() == 0 ? "0" : builder.reverse().toString();
    }

    /**
     * 68. Text Justification
     * Given an array of words and a length L, format the text such that each line has exactly L characters and is
     * fully
     * (left and right) justified.
     * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra
     * spaces ' ' when necessary so that each line has exactly L characters.
     * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not
     * divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the
     * right.
     * For the last line of text, it should be left justified and no extra space is inserted between words.
     * For example,
     * words: ["This", "is", "an", "example", "of", "text", "justification."]
     * L: 16.
     * Return the formatted lines as:
     * [
     * "This    is    an",
     * "example  of text",
     * "justification.  "
     * ]
     * Note: Each word is guaranteed not to exceed L in length.
     */
    public static List<String> fullJustify(String[] words, int L) {

        int currentLineLen = 0;
        List<Integer> lineLengths = new ArrayList<Integer>();
        List<String> line = new ArrayList<String>();
        List<List<String>> lines = new ArrayList<List<String>>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int lenAfterAddWord = (currentLineLen == 0) ? word.length() : (currentLineLen + 1 + word.length());
            if (lenAfterAddWord > L) {
                // do not add word
                // finish this line
                lineLengths.add(currentLineLen - line.size() + 1);
                lines.add(line);
                line = new ArrayList<String>();
                line.add(word);
                currentLineLen = word.length();
            } else {
                // add word
                line.add(word);
                currentLineLen = lenAfterAddWord;
            }
            if (i == words.length - 1) {
                lines.add(line);
                lineLengths.add(currentLineLen - line.size() + 1);
            }
        }
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < lines.size(); i++) {
            line = lines.get(i);
            currentLineLen = lineLengths.get(i);
            int numSpaces = L - currentLineLen;
            int numWords = line.size();

            StringBuilder builder = new StringBuilder();
            if (numWords == 1) {
                builder.append(line.get(0));
                for (int j = 0; j < numSpaces; j++) {
                    builder.append(" ");
                }
            } else if (i != lines.size() - 1) {
                int baseNumSpacePerGap = numSpaces / (numWords - 1);
                int spaceLeft = numSpaces % (numWords - 1);
                for (int j = 0; j < numWords; j++) {
                    builder.append(line.get(j));
                    int spaces = (j == numWords - 1) ? 0 : (baseNumSpacePerGap + (spaceLeft-- > 0 ? 1 : 0));
                    for (int k = 0; k < spaces; k++) {
                        builder.append(" ");
                    }
                }
            } else {
                for (int j = 0; j < numWords; j++) {
                    builder.append(line.get(j));
                    if (j < numWords - 1) {
                        builder.append(" ");
                        numSpaces--;
                    }
                }
                for (int k = 0; k < numSpaces; k++) {
                    builder.append(" ");
                }
            }
            result.add(builder.toString());
        }
        return result;
    }

    /**
     * 69. Sqrt(x)
     * Implement int sqrt(int x).
     * Compute and return the square root of x.
     */
    public static int sqrt(int x) {

        if (x <= 0) {
            return 0;
        } else if (x == 1) {
            return 1;
        }
        long start = 0;
        long end = x / 2 + 1;
        while (start <= end) {
            long mid = (start + end) / 2;
            long product = mid * mid;
            if (product == x) {
                return (int) mid;
            } else if (product > x) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return (int) (start + end) / 2;
    }

    /**
     * 70. Climbing Stairs
     * You are climbing a stair case. It takes n steps to reach to the top.
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     */
    public static int climbStairs(int n) {

        if (n <= 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[0] = 1;
        for (int i = 1; i <= n; i++) {
            d[i] = d[i - 1] + (i >= 2 ? d[i - 2] : 0);
        }
        return d[n];
    }

    /**
     * 71. Simplify Path
     * Given an absolute path for a file (Unix-style), simplify it.
     * For example,
     * path = "/home/", => "/home"
     * path = "/a/./b/../../c/", => "/c"
     */
    public static String simplifyPath(String path) {

        path += "/";
        Deque<String> stack = new ArrayDeque<String>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            if (c == '/') {
                if (i == 0) {
                    stack.addLast("/");
                } else {
                    String str = builder.toString();
                    if (str.equals("..")) {
                        if (stack.isEmpty()) {
                            stack.addLast("/");
                        } else if (!stack.getLast().equals("/")) {
                            stack.removeLast();
                        }
                    } else if (!str.equals(".") && !str.equals("")) {
                        stack.addLast(str);
                    }
                }
                builder.delete(0, builder.length());
            } else {
                builder.append(c);
            }
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            String str = stack.removeFirst();
            result.append(str);
            if (!str.equals("/") && !stack.isEmpty()) {
                result.append("/");
            }
        }
        return result.toString();
    }

    /**
     * 72. Edit Distance
     * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each
     * operation is counted as 1 step.)
     * You have the following 3 operations permitted on a word:
     * a) Insert a character
     * b) Delete a character
     * c) Replace a character
     * fame rome
     */
    public static int minDistance(String word1, String word2) {

        int size1 = word1.length();
        int size2 = word2.length();
        if (size1 == 0) {
            return size2;
        } else if (size2 == 0) {
            return size1;
        }
        int[][] d = new int[size1 + 1][size2 + 1];
        d[0][0] = 0;
        for (int i = 0; i <= size1; i++) {
            d[i][0] = i;
        }
        for (int i = 0; i <= size2; i++) {
            d[0][i] = i;
        }
        for (int i = 1; i <= size1; i++) {
            for (int j = 1; j <= size2; j++) {
                int choiceA = d[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1);
                int choiceB = d[i - 1][j] + 1;
                int choiceC = d[i][j - 1] + 1;
                d[i][j] = Math.min(Math.min(choiceA, choiceB), choiceC);
            }
        }
        return d[size1][size2];
    }

    /**
     * 73. Set Matrix Zeroes
     * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
     */
    public static void setZeroes(int[][] matrix) {

        int m = matrix.length;
        if (m == 0) {
            return;
        }
        int n = matrix[0].length;
        if (n == 0) {
            return;
        }
        Set<Integer> rows = new HashSet<Integer>();
        Set<Integer> cols = new HashSet<Integer>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        for (Integer col : cols) {
            for (int i = 0; i < m; i++) {
                matrix[i][col] = 0;
            }
        }
        for (Integer row : rows) {
            for (int i = 0; i < n; i++) {
                matrix[row][i] = 0;
            }
        }
    }

    /**
     * 74. Search a 2D Matrix
     * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following
     * properties:
     * Integers in each row are sorted from left to right.
     * The first integer of each row is greater than the last integer of the previous row.
     * For example,
     * Consider the following matrix:
     * [
     * [1,   3,  5,  7],
     * [10, 11, 16, 20],
     * [23, 30, 34, 50]
     * ]
     * Given target = 3, return true.
     */
    public static boolean searchMatrix(int[][] matrix, int target) {

        return _searchMatrix(matrix, 0, matrix.length - 1, target);
    }

    public static boolean _searchMatrix(int[][] matrix, int start, int end, int target) {

        if (start > end) {
            return false;
        } else if (start == end) {
            return _searchArray(matrix[start], 0, matrix[start].length - 1, target);
        }
        int mid = (start + end) / 2;
        if (matrix[mid][0] <= target && matrix[mid][matrix[mid].length - 1] >= target) {
            return _searchArray(matrix[mid], 0, matrix[mid].length - 1, target);
        } else if (target < matrix[mid][0]) {
            return _searchMatrix(matrix, start, mid - 1, target);
        } else {
            return _searchMatrix(matrix, mid + 1, end, target);
        }
    }

    public static boolean _searchArray(int[] array, int start, int end, int target) {

        if (start > end) {
            return false;
        } else if (start == end) {
            return array[start] == target;
        }
        int mid = (start + end) / 2;
        if (array[mid] == target) {
            return true;
        } else if (array[mid] > target) {
            return _searchArray(array, start, mid - 1, target);
        } else {
            return _searchArray(array, mid + 1, end, target);
        }
    }

    /**
     * 75. Sort Colors
     * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are
     * adjacent, with the colors in the order red, white and blue.
     * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
     * Note:
     * You are not suppose to use the library's sort function for this problem.
     * click to show follow up.
     * Follow up:
     * A rather straight forward solution is a two-pass algorithm using counting sort.
     * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's,
     * then 1's and followed by 2's.
     * Could you come up with an one-pass algorithm using only constant space?
     */
    public static void sortColors(int[] A) {

        if (A.length <= 1) {
            return;
        }
        int index1 = 0;
        int index2 = A.length - 1;
        for (int i = 0; i <= index2 && index1 < index2; ) {
            if (A[i] == 0) {
                int temp = A[index1];
                A[index1] = A[i];
                A[i] = temp;
                i++;
                index1++;
            } else if (A[i] == 1) {
                i++;
            } else if (A[i] == 2) {
                int temp = A[index2];
                A[index2] = A[i];
                A[i] = temp;
                index2--;
            } else {
                return;
            }
        }
    }

    /**
     * 76. Minimum Window Substring
     * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in
     * complexity O(n).
     * For example,
     * S = "ADOBECODEBANC"
     * T = "ABC"
     * Minimum window is "BANC".
     * Note:
     * If there is no such window in S that covers all characters in T, return the emtpy string "".
     * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window
     * in S.
     */
    public static String minWindow(String S, String T) {

        int size1 = S.length();
        int size2 = T.length();
        if (size1 < size2) {
            return "";
        } else if (S.equals(T)) {
            return S;
        }
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < size2; i++) {
            char t = T.charAt(i);
            if (map.containsKey(t)) {
                map.put(t, map.get(t) + 1);
            } else {
                map.put(t, 1);
            }
        }
        List<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0; i < size1; i++) {
            if (map.containsKey(S.charAt(i))) {
                indexes.add(i);
            }
        }
        if (indexes.size() == 0) {
            return "";
        }
        int iIndex = 0;
        int index1 = indexes.get(iIndex++);
        int index2 = index1;
        int charNum = map.size();
        String result = null;
        Map<Character, Integer> tempMap = new HashMap<Character, Integer>();
        for (; index2 < size1; index2++) {
            char current = S.charAt(index2);
            if (map.containsKey(current)) {
                if (tempMap.containsKey(current)) {
                    tempMap.put(current, tempMap.get(current) + 1);
                } else {
                    tempMap.put(current, 1);
                }
                if (tempMap.get(current).equals(map.get(current))) {
                    charNum--;
                }
                if (charNum == 0) {
                    String temp = S.substring(index1, index2 + 1);
                    result = (result == null || result.length() > temp.length()) ? temp : result;
                    if (result.length() == map.size()) {
                        return result;
                    }
                    char c1 = S.charAt(index1);
                    tempMap.put(c1, tempMap.get(c1) - 1);
                    if (tempMap.get(c1) < map.get(c1)) {
                        charNum++;
                    }
                    if (index1 != index2) {
                        tempMap.put(current, tempMap.get(current) - 1);
                        if (tempMap.get(current) < map.get(current) && current != c1) {
                            charNum++;
                        }
                    }
                    index1 = indexes.get(iIndex++);
                    index2--;
                }
            }
        }
        return result == null ? "" : result;
    }

    /**
     * 77. Combinations
     * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
     * For example,
     * If n = 4 and k = 2, a solution is:
     * [
     * [2,4],
     * [3,4],
     * [2,3],
     * [1,2],
     * [1,3],
     * [1,4],
     * ]
     */
    public static List<List<Integer>> combine(int n, int k) {

        if (k <= 0 || n < k) {
            return new ArrayList<List<Integer>>();
        }
        Set<List<Integer>> set = null;
        for (int i = 1; i <= k; i++) {
            Set<List<Integer>> temp = new HashSet<List<Integer>>();
            for (int j = 1; j <= n; j++) {
                if (i == 1) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(j);
                    temp.add(list);
                } else {
                    for (List<Integer> tempList : set) {
                        List<Integer> list = new ArrayList<Integer>(tempList);
                        for (int l = 0; l < list.size(); l++) {
                            if (list.get(l) > j) {
                                list.add(l, j);
                                temp.add(list);
                                break;
                            } else if (list.get(l) == j) {
                                break;
                            }
                        }
                    }
                }
            }
            set = temp;
        }
        return new ArrayList<List<Integer>>(set);
    }

    /**
     * 78. Subsets
     * Given a set of distinct integers, S, return all possible subsets.
     * Note:
     * Elements in a subset must be in non-descending order.
     * The solution set must not contain duplicate subsets.
     * For example,
     * If S = [1,2,3], a solution is:
     * [
     * [3],
     * [1],
     * [2],
     * [1,2,3],
     * [1,3],
     * [2,3],
     * [1,2],
     * []
     * ]
     */
    public static List<List<Integer>> subsets(int[] S) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (S.length == 0) {
            result.add(new ArrayList<Integer>());
            return result;
        }
        Set<List<Integer>> set = null;
        for (int i = 1; i <= S.length; i++) {
            Set<List<Integer>> temp = new HashSet<List<Integer>>();
            for (int j = 0; j < S.length; j++) {
                if (i == 1) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(S[j]);
                    temp.add(list);
                } else {
                    for (List<Integer> tempList : set) {
                        List<Integer> list = new ArrayList<Integer>(tempList);
                        for (int l = 0; l < list.size(); l++) {
                            if (list.get(l) > S[j]) {
                                list.add(l, S[j]);
                                temp.add(list);
                                break;
                            } else if (list.get(l) == S[j]) {
                                break;
                            }
                        }
                    }
                }
            }
            set = temp;
            result.addAll(set);
        }
        result.add(new ArrayList<Integer>());
        return result;
    }

    /**
     * 79. Word Search
     * Given a 2D board and a word, find if the word exists in the grid.
     * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
     * horizontally or vertically neighboring. The same letter cell may not be used more than once.
     * For example,
     * Given board =
     * [
     * ["ABCE"],
     * ["SFCS"],
     * ["ADEE"]
     * ]
     * word = "ABCCED", -> returns true,
     * word = "SEE", -> returns true,
     * word = "ABCB", -> returns false.
     */
    public static boolean exist(char[][] board, String word) {

        if (word.length() == 0) {
            return false;
        }
        int m = board.length;
        if (m == 0) {
            return false;
        }
        int n = board[0].length;
        if (n == 0) {
            return false;
        }
        boolean[][] used = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!used[i][j] && board[i][j] == word.charAt(0)) {
                    used[i][j] = true;
                    if (_exist(board, m, n, i, j, used, word.substring(1))) {
                        return true;
                    }
                    used[i][j] = false;
                }
            }
        }
        return false;
    }

    public static boolean _exist(char[][] board, int m, int n, int i, int j, boolean[][] used, String word) {

        if (word.equals("")) {
            return true;
        }
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return false;
        }
        char c = word.charAt(0);
        boolean up = i > 0 && !used[i - 1][j] && c == board[i - 1][j];
        boolean down = i < m - 1 && !used[i + 1][j] && c == board[i + 1][j];
        boolean left = j > 0 && !used[i][j - 1] && c == board[i][j - 1];
        boolean right = j < n - 1 && !used[i][j + 1] && c == board[i][j + 1];
        if (up) {
            used[i - 1][j] = true;
            if (_exist(board, m, n, i - 1, j, used, word.substring(1))) {
                return true;
            }
            used[i - 1][j] = false;
        }
        if (down) {
            used[i + 1][j] = true;
            if (_exist(board, m, n, i + 1, j, used, word.substring(1))) {
                return true;
            }
            used[i + 1][j] = false;
        }
        if (left) {
            used[i][j - 1] = true;
            if (_exist(board, m, n, i, j - 1, used, word.substring(1))) {
                return true;
            }
            used[i][j - 1] = false;
        }
        if (right) {
            used[i][j + 1] = true;
            if (_exist(board, m, n, i, j + 1, used, word.substring(1))) {
                return true;
            }
            used[i][j + 1] = false;
        }
        return false;
    }


    /**
     * 80. Remove Duplicates from Sorted Array II
     * Follow up for "Remove Duplicates":
     * What if duplicates are allowed at most twice?
     * For example,
     * Given sorted array A = [1,1,1,2,2,3],
     * Your function should return length = 5, and A is now [1,1,2,2,3].
     */
    public static int removeDuplicates2(int[] A) {

        int size = A.length;
        if (size <= 2) {
            return size;
        }
        int index1 = 0;
        int index2 = 1;
        while (index1 < size) {
            while (index2 < A.length && A[index1] == A[index2]) {
                if (index2 - index1 >= 2) {
                    size--;
                }
                index2++;
            }
            if (index2 < A.length && index2 - index1 >= 2) {
                int temp = A[index1 + 2];
                A[index1 + 2] = A[index2];
                A[index2] = temp;
            }
            index1++;
            index2++;
        }
        return size;
    }

    /**
     * 81. Search in Rotated Sorted Array II
     * Follow up for "Search in Rotated Sorted Array":
     * What if duplicates are allowed?
     * Would this affect the run-time complexity? How and why?
     * Write a function to determine if a given target is in the array.
     * 1,1,2,2,3,3,4,5 => 2,2,2,4,5,1,1,1,1,1,1,1,1,1,1,1,1
     * 3,3,4,5,6,1,2
     */
    public static boolean search2(int[] A, int target) {

        if (A.length == 0) {
            return false;
        }
        return _search2(A, 0, A.length - 1, target);
    }

    public static boolean _search2(int[] A, int start, int end, int target) {

        if (start > end) {
            return false;
        } else if (start == end) {
            return A[start] == target;
        } else if (start + 1 == end) {
            return A[start] == target || A[end] == target;
        }
        int mid = (start + end) / 2;
        if (A[mid] == target) {
            return true;
        }
        int left = mid - 1;
        int right = mid + 1;
        while (left > start && A[left] == A[mid]) {
            left--;
        }
        while (right < end && A[right] == A[mid]) {
            right++;
        }
        if (A[start] <= A[left]) {
            if (target >= A[start] && target <= A[left]) {
                return _search2(A, start, left, target);
            } else {
                return _search2(A, right, end, target);
            }
        } else {
            if (target >= A[right] && target <= A[end]) {
                return _search2(A, right, end, target);
            } else {
                return _search2(A, start, left, target);
            }
        }
    }

    /**
     * 82. Remove Duplicates from Sorted List II
     * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from
     * the original list.
     * For example,
     * Given 1->2->3->3->4->4->5, return 1->2->5.
     * Given 1->1->1->2->3, return 2->3.
     */
    public static ListNode deleteDuplicates2(ListNode head) {

        if (head == null) {
            return null;
        }
        ListNode newHead = null;
        ListNode parent = null;
        ListNode child = head;
        while (child != null) {
            ListNode temp = child;
            int count = 0;
            while (temp != null && temp.val == child.val) {
                count++;
                temp = temp.next;
            }
            if (count > 1) {
                if (parent == null) {
                    newHead = temp;
                    child = temp;
                } else {
                    parent.next = temp;
                    child = temp;
                }
            } else {
                if (parent == null) {
                    newHead = child;
                }
                parent = child;
                child = child.next;
            }
        }
        return newHead;
    }

    /**
     * 83. Remove Duplicates from Sorted List
     * Given a sorted linked list, delete all duplicates such that each element appear only once.
     * For example,
     * Given 1->1->2, return 1->2.
     * Given 1->1->2->3->3, return 1->2->3.
     */
    public static ListNode deleteDuplicates(ListNode head) {

        if (head == null) {
            return null;
        }
        ListNode temp = head;
        while (temp.next != null) {
            if (temp.val == temp.next.val) {
                ListNode next = temp.next.next;
                temp.next.next = null;
                temp.next = next;
            } else {
                temp = temp.next;
            }
        }
        return head;
    }

    /**
     * 84. Largest Rectangle in Histogram
     * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find
     * the area of largest rectangle in the histogram.
     * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
     * The largest rectangle is shown in the shaded area, which has area = 10 unit.
     * For example,
     * Given height = [2,1,5,6,2,3],
     * return 10.
     */
    public static int largestRectangleArea(int[] height) {

        if (height.length == 0) {
            return 0;
        }
        int max = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i <= height.length; i++) {
            int h = i == height.length ? 0 : height[i];
            if (stack.isEmpty() || height[stack.peek()] <= h) {
                stack.push(i);
            } else {
                int preIndex = stack.pop();
                int area = height[preIndex] * (stack.isEmpty() ? i : (i - stack.peek() - 1));
                max = Math.max(max, area);
                i--;
            }
        }
        return max;
    }

    /**
     * 85. Maximal Rectangle
     * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return
     * its area.
     */
    public static int maximalRectangle(char[][] matrix) {

        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        if (n == 0) {
            return 0;
        }
        int max = 0;
        int[][] heights = new int[m][n];
        for (int i = 0; i < m; i++) {
            int rowMax = 0;
            Stack<Integer> stack = new Stack<Integer>();
            for (int j = 0; j <= n; j++) {
                int h = 0;
                if (j < n) {
                    heights[i][j] = (matrix[i][j] == '1') ? ((i > 0 ? heights[i - 1][j] : 0) + 1) : 0;
                    h = heights[i][j];
                }
                if (stack.isEmpty() || heights[i][stack.peek()] <= h) {
                    stack.push(j);
                } else {
                    int preIndex = stack.pop();
                    int area = heights[i][preIndex] * (stack.isEmpty() ? j : j - stack.peek() - 1);
                    rowMax = Math.max(rowMax, area);
                    j--;
                }
            }
            max = Math.max(max, rowMax);
        }
        return max;
    }

    /**
     * 86. Partition List
     * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than
     * or equal to x.
     * You should preserve the original relative order of the nodes in each of the two partitions.
     * For example,
     * Given 1->4->3->2->5->2 and x = 3,
     * return 1->2->2->4->3->5.
     */
    public static ListNode partition(ListNode head, int x) {

        if (head == null) {
            return null;
        }
        ListNode leftHead = null;
        ListNode rightHead = null;
        ListNode leftTemp = null;
        ListNode rightTemp = null;

        ListNode temp = head;
        while (temp != null) {
            ListNode next = temp.next;
            temp.next = null;
            if (temp.val < x) {
                if (leftHead == null) {
                    leftHead = temp;
                    leftTemp = temp;
                } else {
                    leftTemp.next = temp;
                    leftTemp = leftTemp.next;
                }
            } else {
                if (rightHead == null) {
                    rightHead = temp;
                    rightTemp = temp;
                } else {
                    rightTemp.next = temp;
                    rightTemp = rightTemp.next;
                }
            }
            temp = next;
        }
        if (leftTemp != null) {
            leftTemp.next = rightHead;
        }
        return leftHead == null ? rightHead : leftHead;
    }

    /**
     * 87. Scramble String
     * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings
     * recursively.
     * Below is one possible representation of s1 = "great":
     * great
     * /    \
     * gr    eat
     * / \    /  \
     * g   r  e   at
     * / \
     * a   t
     * To scramble the string, we may choose any non-leaf node and swap its two children.
     * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
     * rgeat
     * /    \
     * rg    eat
     * / \    /  \
     * r   g  e   at
     * / \
     * a   t
     * We say that "rgeat" is a scrambled string of "great".
     * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string
     * "rgtae".
     * rgtae
     * /    \
     * rg    tae
     * / \    /  \
     * r   g  ta  e
     * / \
     * t   a
     * We say that "rgtae" is a scrambled string of "great".
     * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
     */
    public static boolean isScramble(String s1, String s2) {

        int size1 = s1.length();
        int size2 = s2.length();
        if (size1 != size2) {
            return false;
        } else if (size1 == 0) {
            return true;
        } else if (size1 == 1) {
            return s1.charAt(0) == s2.charAt(0);
        }
        int[] chars = new int[256];
        for (int i = 0; i < size1; i++) {
            chars[s1.charAt(i)]++;
            chars[s2.charAt(i)]--;
        }
        for (int i = 0; i < 256; i++) {
            if (chars[i] != 0) {
                return false;
            }
        }
        for (int i = 1; i < size1; i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                return true;
            }
            if (isScramble(s1.substring(0, i), s2.substring(size2 - i)) && isScramble(s1.substring(i),
                                                                                      s2.substring(0, size2 - i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 88. Merge Sorted Array
     * Given two sorted integer arrays A and B, merge B into A as one sorted array.
     * Note:
     * You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements
     * from B. The number of elements initialized in A and B are m and n respectively.
     */
    public static void merge(int A[], int m, int B[], int n) {

        if (m < 0 || n < 0) {
            return;
        }
        int index1 = m - 1;
        int index2 = n - 1;
        for (int i = m + n - 1; i >= 0; i--) {
            int c;
            if (index1 >= 0 && index2 >= 0) {
                if (A[index1] >= B[index2]) {
                    c = A[index1];
                    index1--;
                } else {
                    c = B[index2];
                    index2--;
                }
            } else if (index1 >= 0) {
                c = A[index1];
                index1--;
            } else if (index2 >= 0) {
                c = B[index2];
                index2--;
            } else {
                break;
            }
            A[i] = c;
        }
    }

    /**
     * 89. Gray Code
     * The gray code is a binary numeral system where two successive values differ in only one bit.
     * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray
     * code. A gray code sequence must begin with 0.
     * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
     * 00 - 0
     * 01 - 1
     * 11 - 3
     * 10 - 2
     * Note:
     * For a given n, a gray code sequence is not uniquely defined.
     * For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
     * For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
     */
    public static List<Integer> grayCode(int n) {

        List<Integer> result = new ArrayList<Integer>();
        if (n < 0) {
            return result;
        }
        int elem = 1;
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                result.add(0);
            } else {
                int size = result.size();
                for (int j = size - 1; j >= 0; j--) {
                    result.add(result.get(j) + elem);
                }
                elem *= 2;
            }
        }
        return result;
    }

    /**
     * 90. Subsets II
     * Given a collection of integers that might contain duplicates, S, return all possible subsets.
     * Note:
     * Elements in a subset must be in non-descending order.
     * The solution set must not contain duplicate subsets.
     * For example,
     * If S = [1,2,2], a solution is:
     * [
     * [2],
     * [1],
     * [1,2,2],
     * [2,2],
     * [1,2],
     * []
     * ]
     */
    public static List<List<Integer>> subsetsWithDup(int[] num) {

        Arrays.sort(num);
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        if (num.length > 0) {
            for (int i = 0; i < num.length; i++) {
                List<Integer> singleList = new ArrayList<Integer>();
                singleList.add(num[i]);
                if (!set.isEmpty()) {
                    Set<List<Integer>> temp = new HashSet<List<Integer>>();
                    for (List<Integer> aSet : set) {
                        temp.add(new ArrayList<Integer>(aSet));
                        List<Integer> list = new ArrayList<Integer>(aSet);
                        list.add(num[i]);
                        temp.add(list);
                    }
                    set = temp;
                }
                set.add(singleList);
            }
        }
        set.add(new ArrayList<Integer>());
        return new ArrayList<List<Integer>>(set);
    }

    /**
     * 91. Decode Ways
     * A message containing letters from A-Z is being encoded to numbers using the following mapping:
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * Given an encoded message containing digits, determine the total number of ways to decode it.
     * For example,
     * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
     * The number of ways decoding "12" is 2.
     */
    public static int numDecodings(String s) {

        if (s.length() == 0) {
            return 0;
        } else if (s.charAt(0) == '0') {
            return 0;
        }
        int[] d = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i == 0) {
                d[i] = 1;
            } else if (c == '0') {
                if (s.charAt(i - 1) == '0' || s.charAt(i - 1) > '2') {
                    return 0;
                }
                d[i] = i < 2 ? 1 : d[i - 2];
            } else if (c >= '7' && c <= '9') {
                d[i] = d[i - 1] + ((s.charAt(i - 1) == '1') ? (i < 2 ? 1 : d[i - 2]) : 0);
            } else if (c > '0' && c <= '6') {
                d[i] = d[i - 1] + ((s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2') ? (i < 2 ? 1 : d[i - 2]) : 0);
            } else {
                return 0;
            }
        }
        return d[s.length() - 1];
    }

    /**
     * 92. Reverse Linked List II
     * Reverse a linked list from position m to n. Do it in-place and in one-pass.
     * For example:
     * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
     * return 1->4->3->2->5->NULL.
     * Note:
     * Given m, n satisfy the following condition:
     * 1 ≤ m ≤ n ≤ length of list.
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {

        if (m >= n) {
            return head;
        }
        ListNode newHead = null;
        ListNode tailA = null;
        ListNode start = head;
        int count = 0;
        while (start != null) {
            count++;
            if (count == m) {
                break;
            }
            tailA = start;
            start = start.next;
        }
        if (tailA != null) {
            tailA.next = null;
        }
        int size = n - m + 1;
        count = 1;
        ListNode temp1 = start;
        ListNode temp2 = temp1.next;
        while (temp1 != null && temp2 != null && ++count <= size) {
            ListNode next = temp2.next;
            temp2.next = temp1;
            temp1 = temp2;
            temp2 = next;
        }
        if (tailA != null) {
            tailA.next = temp1;
            start.next = temp2;
            newHead = head;
        } else {
            newHead = temp1;
            start.next = temp2;
        }
        return newHead;
    }

    /**
     * 93. Restore IP Addresses
     * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
     * For example:
     * Given "25525511135",
     * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
     */
    public static List<String> restoreIpAddresses(String s) {

        if (s.length() <= 3) {
            return new ArrayList<String>();
        }
        List<List<String>> lists = _restoreIpAddresses(s, 4);
        List<String> result = new ArrayList<String>();
        for (List<String> list : lists) {
            if (list.size() != 4) {
                continue;
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                builder.append(list.get(i));
                if (i < list.size() - 1) {
                    builder.append(".");
                }
            }
            result.add(builder.toString());
        }
        return result;
    }

    public static List<List<String>> _restoreIpAddresses(String s, int level) {

        List<List<String>> result = new ArrayList<List<String>>();
        if (level <= 0 || (level == 1 && s.length() > 3) || s.length() == 0) {
            return result;
        } else if (level == 1) {
            int num = Integer.parseInt(s);
            if (num > 255 || (s.charAt(0) == '0' && (num != 0 || (s.length() > 1)))) {
                return result;
            } else {
                List<String> list = new ArrayList<String>();
                list.add(s);
                result.add(list);
            }
        } else {
            int endIndex = Math.min(3, s.length());
            if (s.length() > 0 && s.charAt(0) == '0') {
                endIndex = 1;
            }
            for (int i = 0; i < endIndex; i++) {
                String strNum = s.substring(0, i + 1);
                int num = Integer.parseInt(strNum);
                if ((num == 0 && i == 0) || (num > 0 && num <= 255)) {
                    List<List<String>> rest = _restoreIpAddresses(s.substring(i + 1), level - 1);
                    for (List<String> aRest : rest) {
                        aRest.add(0, strNum);
                        result.add(aRest);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 94. Binary Tree Inorder Traversal
     * Given a binary tree, return the inorder traversal of its nodes' values.
     * For example:
     * Given binary tree {1,#,2,3},
     * 1
     * \
     * 2
     * /
     * 3
     * return [1,3,2].
     * Note: Recursive solution is trivial, could you do it iteratively?
     */
    public static List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                result.add(node.val);
                node = node.right;
            }
        }
        return result;
    }

    public static List<Integer> constSpaceInorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }
        TreeNode node = root;
        TreeNode pre = null;
        while (node != null) {
            if (node.left == null) {
                result.add(node.val);
                node = node.right;
            } else {
                pre = node.left;
                while (pre.right != null && pre.right != node) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = node;
                    node = node.left;
                } else {
                    pre.right = null;
                    result.add(node.val);
                    node = node.right;
                }
            }
        }
        return result;
    }


    /**
     * 95. Unique Binary Search Trees II
     * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
     * For example,
     * Given n = 3, your program should return all 5 unique BST's shown below.
     * 1         3     3      2      1
     * \       /     /      / \      \
     * 3     2     1      1   3      2
     * /     /       \                 \
     * 2     1         2                 3
     */


    public static List<TreeNode> generateTrees(int n) {

        return generateTrees(1, n);
    }

    public static List<TreeNode> generateTrees(int start, int end) {

        List<TreeNode> roots = new ArrayList<TreeNode>();
        if (start > end) {
            roots.add(null);
            return roots;
        } else if (start == end) {
            roots.add(new TreeNode(start));
            return roots;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> lefts = generateTrees(start, i - 1);
            List<TreeNode> rights = generateTrees(i + 1, end);
            if (lefts.isEmpty()) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(i);
                    root.left = null;
                    root.right = right;
                    roots.add(root);
                }
            } else if (rights.isEmpty()) {
                for (TreeNode left : lefts) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = null;
                    roots.add(root);
                }
            } else {
                for (TreeNode left : lefts) {
                    for (TreeNode right : rights) {
                        TreeNode root = new TreeNode(i);
                        root.left = left;
                        root.right = right;
                        roots.add(root);
                    }
                }
            }
        }
        return roots;
    }


    /**
     * 96. Unique Binary Search Trees
     * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
     * For example,
     * Given n = 3, there are a total of 5 unique BST's.
     * 1         3     3      2      1
     * \       /     /      / \      \
     * 3     2     1      1   3      2
     * /     /       \                 \
     * 2     1         2                 3
     */
    public static int numTrees(int n) {

        if (n <= 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                d[i] += (d[j - 1] * d[i - j]);
            }
        }
        return d[n];
    }


    /**
     * 97. Interleaving String
     * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
     * For example,
     * Given:
     * s1 = "aabcc",
     * s2 = "dbbca",
     * When s3 = "aadbbcbcac", return true.
     * When s3 = "aadbbbaccc", return false.
     */
    public static boolean isInterleave(String s1, String s2, String s3) {

        int size1 = s1.length();
        int size2 = s2.length();
        int size3 = s3.length();
        if (size1 + size2 != size3) {
            return false;
        }
        boolean[][] d = new boolean[size1 + 1][size2 + 1];
        for (int i = 0; i <= size1; i++) {
            for (int j = 0; j <= size2; j++) {
                if (i == 0 && j == 0) {
                    d[i][j] = true;
                } else if (i == 0) {
                    d[i][j] = s2.charAt(j - 1) == s3.charAt(j - 1) && d[i][j - 1];
                } else if (j == 0) {
                    d[i][j] = s1.charAt(i - 1) == s3.charAt(i - 1) && d[i - 1][j];
                } else {
                    d[i][j] = (s1.charAt(i - 1) == s3.charAt(i + j - 1) && d[i - 1][j])
                            || (s2.charAt(j - 1) == s3.charAt(i + j - 1) && d[i][j - 1]);
                }
            }
        }
        return d[size1][size2];
    }

    /**
     * 98. Validate Binary Search Tree
     * Given a binary tree, determine if it is a valid binary search tree (BST).
     * Assume a BST is defined as follows:
     * The left subtree of a node contains only nodes with keys less than the node's key.
     * The right subtree of a node contains only nodes with keys greater than the node's key.
     * Both the left and right subtrees must also be binary search trees.
     */
    public static boolean isValidBST(TreeNode root) {

        int lastVal = 0;
        boolean first = true;
        TreeNode pre = null;
        TreeNode node = root;
        while (node != null) {
            if (node.left == null) {
                if (lastVal >= node.val && !first) {
                    return false;
                }
                first = false;
                lastVal = node.val;
                node = node.right;
            } else {
                pre = node.left;
                while (pre.right != null && pre.right != node) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = node;
                    node = node.left;
                } else {
                    pre.right = null;
                    if (lastVal >= node.val && !first) {
                        return false;
                    }
                    first = false;
                    lastVal = node.val;
                    node = node.right;
                }
            }
        }
        return true;
    }


    /**
     * 99. Recover Binary Search Tree
     * Two elements of a binary search tree (BST) are swapped by mistake.
     * Recover the tree without changing its structure.
     * Note:
     * A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
     */
    public static void recoverTree(TreeNode root) {

        TreeNode parent = null;
        TreeNode pre = null;
        TreeNode node = root;
        TreeNode nodeA = null;
        TreeNode nodeB = null;
        while (node != null) {
            if (node.left == null) {
                if (parent != null && parent.val >= node.val) {
                    if (nodeA == null) {
                        nodeA = parent;
                    }
                    nodeB = node;
                }
                parent = node;
                node = node.right;
            } else {
                pre = node.left;
                while (pre.right != null && pre.right != node) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = node;
                    node = node.left;
                } else {
                    pre.right = null;
                    if (parent != null && parent.val >= node.val) {
                        if (nodeA == null) {
                            nodeA = parent;
                        }
                        nodeB = node;
                    }
                    parent = node;
                    node = node.right;
                }
            }
        }
        if (nodeA != null && nodeB != null) {
            int temp = nodeA.val;
            nodeA.val = nodeB.val;
            nodeB.val = temp;
        }
    }


    /**
     * 100. Same Tree
     * Given two binary trees, write a function to check if they are equal or not.
     * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null) {
            return q == null;
        } else if (q == null) {
            return false;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }


    /**
     * 101. Symmetric Tree
     * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
     * For example, this binary tree is symmetric:
     * 1
     * / \
     * 2   2
     * / \ / \
     * 3  4 4  3
     * But the following is not:
     * 1
     * / \
     * 2   2
     * \   \
     * 3    3
     * Note:
     * Bonus points if you could solve it both recursively and iteratively.
     */
    public static boolean isSymmetric(TreeNode root) {

        return root == null || isSymmetric(root.left, root.right);
    }

    public static boolean isSymmetric(TreeNode p, TreeNode q) {

        if (p == null) {
            return q == null;
        } else if (q == null) {
            return false;
        }
        return p.val == q.val && isSymmetric(p.left, q.right) && isSymmetric(p.right, q.left);
    }


    /**
     * 102. Binary Tree Level Order Traversal
     * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by
     * level).
     * For example:
     * Given binary tree {3,9,20,#,#,15,7},
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * return its level order traversal as:
     * [
     * [3],
     * [9,20],
     * [15,7]
     * ]
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) {
            return result;
        }
        boolean markNew = true;
        TreeNode mark = root;
        Deque<TreeNode> dequeue = new ArrayDeque<TreeNode>();
        dequeue.addLast(root);
        List<Integer> row = new ArrayList<Integer>();
        while (!dequeue.isEmpty()) {
            TreeNode node = dequeue.removeFirst();
            TreeNode left = node.left;
            TreeNode right = node.right;
            if (left != null) {
                dequeue.addLast(left);
            }
            if (right != null) {
                dequeue.addLast(right);
            }
            if (node == mark) {
                markNew = true;
                if (node == root) {
                    row.add(node.val);
                } else {
                    result.add(row);
                    row = new ArrayList<Integer>();
                    row.add(node.val);
                }
            } else {
                row.add(node.val);
            }
            if (markNew) {
                if (left != null) {
                    mark = left;
                    markNew = false;
                } else if (right != null) {
                    mark = right;
                    markNew = false;
                }
            }
        }
        if (!row.isEmpty()) {
            result.add(row);
        }
        return result;
    }


    /**
     * 103. Binary Tree Zigzag Level Order Traversal
     * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then
     * right to left for the next level and alternate between).
     * For example:
     * Given binary tree {3,9,20,#,#,15,7},
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * return its zigzag level order traversal as:
     * [
     * [3],
     * [20,9],
     * [15,7]
     * ]
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) {
            return result;
        }
        boolean markNew = true;
        TreeNode mark = root;
        Deque<TreeNode> dequeue = new ArrayDeque<TreeNode>();
        dequeue.addLast(root);
        List<Integer> row = new ArrayList<Integer>();
        int level = 1;
        while (!dequeue.isEmpty()) {
            TreeNode node = dequeue.removeFirst();
            TreeNode left = node.left;
            TreeNode right = node.right;
            if (left != null) {
                dequeue.addLast(left);
            }
            if (right != null) {
                dequeue.addLast(right);
            }
            if (node == mark) {
                markNew = true;
                if (node == root) {
                    row.add(node.val);
                } else {
                    result.add(row);
                    row = new ArrayList<Integer>();
                    if (level % 2 != 0) {
                        row.add(0, node.val);
                    } else {
                        row.add(node.val);
                    }
                }
                level++;
            } else {
                if (level % 2 != 0) {
                    row.add(0, node.val);
                } else {
                    row.add(node.val);
                }
            }
            if (markNew) {
                if (left != null) {
                    mark = left;
                    markNew = false;
                } else if (right != null) {
                    mark = right;
                    markNew = false;
                }
            }
        }
        if (!row.isEmpty()) {
            result.add(row);
        }
        return result;
    }

    /**
     * 104. Maximum Depth of Binary Tree
     * Given a binary tree, find its maximum depth.
     * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf
     * node.
     */
    public static int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return 1;
        } else {
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }
    }

    /**
     * 105. Construct Binary Tree from Preorder and Inorder Traversal
     * Given preorder and inorder traversal of a tree, construct the binary tree.
     * Note:
     * You may assume that duplicates do not exist in the tree.
     */
    public static TreeNode buildTree1(int[] preorder, int[] inorder) {

        if (preorder.length != inorder.length) {
            return null;
        }
        return _buildTree1(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public static TreeNode _buildTree1(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {

        if (pStart > pEnd || iStart > iEnd || pEnd - pStart != iEnd - iStart) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[pStart]);
        int rootIndex = iStart;
        for (; rootIndex <= iEnd; rootIndex++) {
            if (inorder[rootIndex] == preorder[pStart]) {
                break;
            }
        }
        root.left = _buildTree1(preorder, pStart + 1, pStart + rootIndex - iStart, inorder, iStart, rootIndex - 1);
        root.right = _buildTree1(preorder, pStart + rootIndex - iStart + 1, pEnd, inorder, rootIndex + 1, iEnd);
        return root;
    }

    /**
     * 106. Construct Binary Tree from Inorder and Postorder
     * Given inorder and postorder traversal of a tree, construct the binary tree.
     * Note:
     * You may assume that duplicates do not exist in the tree.
     */
    public static TreeNode buildTree2(int[] inorder, int[] postorder) {

        if (inorder.length != postorder.length) {
            return null;
        }
        return _buildTree2(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public static TreeNode _buildTree2(int[] inorder, int iStart, int iEnd, int[] postorder, int pStart, int pEnd) {

        if (pStart > pEnd || iStart > iEnd || pEnd - pStart != iEnd - iStart) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[pEnd]);
        int rootIndex = iStart;
        for (; rootIndex <= iEnd; rootIndex++) {
            if (inorder[rootIndex] == postorder[pEnd]) {
                break;
            }
        }
        root.left = _buildTree2(inorder, iStart, rootIndex - 1, postorder, pStart, rootIndex - iStart - 1 + pStart);
        root.right = _buildTree2(inorder, rootIndex + 1, iEnd, postorder, rootIndex - iStart + pStart,
                                 pStart - iStart + iEnd - 1);
        return root;
    }

    /**
     * 107. Binary Tree Level Order Traversal II
     * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right,
     * level by level from leaf to root).
     * For example:
     * Given binary tree {3,9,20,#,#,15,7},
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * return its bottom-up level order traversal as:
     * [
     * [15,7],
     * [9,20],
     * [3]
     * ]
     */
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) {
            return result;
        }
        boolean markNew = true;
        TreeNode mark = root;
        Deque<TreeNode> dequeue = new ArrayDeque<TreeNode>();
        dequeue.addLast(root);
        List<Integer> row = new ArrayList<Integer>();
        while (!dequeue.isEmpty()) {
            TreeNode node = dequeue.removeFirst();
            TreeNode left = node.left;
            TreeNode right = node.right;
            if (left != null) {
                dequeue.addLast(left);
            }
            if (right != null) {
                dequeue.addLast(right);
            }
            if (node == mark) {
                markNew = true;
                if (node == root) {
                    row.add(node.val);
                } else {
                    result.add(0, row);
                    row = new ArrayList<Integer>();
                    row.add(node.val);
                }
            } else {
                row.add(node.val);
            }
            if (markNew) {
                if (left != null) {
                    mark = left;
                    markNew = false;
                } else if (right != null) {
                    mark = right;
                    markNew = false;
                }
            }
        }
        if (!row.isEmpty()) {
            result.add(0, row);
        }
        return result;
    }

    /**
     * 108. Convert Sorted Array to Binary Search Tree
     * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
     */
    public static TreeNode sortedArrayToBST(int[] num) {

        return _sortedArrayToBST(num, 0, num.length - 1);
    }

    public static TreeNode _sortedArrayToBST(int[] num, int start, int end) {

        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(num[mid]);
        root.left = _sortedArrayToBST(num, start, mid - 1);
        root.right = _sortedArrayToBST(num, mid + 1, end);
        return root;
    }

    /**
     * 109. Convert Sorted List to Binary Search Tree
     * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
     */
    public static TreeNode sortedListToBST(ListNode head) {

        if (head == null) {
            return null;
        } else if (head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode temp1 = head;
        ListNode temp2 = head;
        ListNode parent = null;
        while (temp2 != null && temp2.next != null) {
            parent = temp1;
            temp1 = temp1.next;
            temp2 = temp2.next.next;
        }
        ListNode rootNode = parent.next;
        parent.next = null;
        TreeNode root = new TreeNode(rootNode.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(rootNode.next);
        parent.next = rootNode;
        return root;
    }

    /**
     * 110. Balanced Binary Tree
     * Given a binary tree, determine if it is height-balanced.
     * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two
     * subtrees of every node never differ by more than 1.
     */
    public static boolean isBalanced(TreeNode root) {

        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        int leftH = maxDepth(root.left);
        int rightH = maxDepth(root.right);
        if (Math.abs(rightH - leftH) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 111. Minimum Depth of Binary Tree
     * Given a binary tree, find its minimum depth.
     * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf
     * node.
     */
    public static int minDepth(TreeNode root) {

        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return 1;
        } else if (root.left == null) {
            return minDepth(root.right) + 1;
        } else if (root.right == null) {
            return minDepth(root.left) + 1;
        } else {
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        }
    }

    /**
     * 112. Path Sum
     * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the
     * values
     * along the path equals the given sum.
     * For example:
     * Given the below binary tree and sum = 22,
     * 5
     * / \
     * 4   8
     * /   / \
     * 11  13  4
     * /  \      \
     * 7    2      1
     * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
     */
    public static boolean hasPathSum(TreeNode root, int sum) {

        if (root == null) {
            return false;
        }
        int newSum = sum - root.val;
        if (root.left == null && root.right == null) {
            return newSum == 0;
        }
        return hasPathSum(root.left, newSum) || hasPathSum(root.right, newSum);
    }


    /**
     * 113. Path Sum II
     * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
     * For example:
     * Given the below binary tree and sum = 22,
     * 5
     * / \
     * 4   8
     * /   / \
     * 11  13  4
     * /  \    / \
     * 7    2  5   1
     * return
     * [
     * [5,4,11,2],
     * [5,8,4,5]
     * ]
     */
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {

        return _pathSum(root, sum);
    }

    public static List<List<Integer>> _pathSum(TreeNode root, int sum) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) {
            return result;
        } else if (root.left == null && root.right == null) {
            if (sum == root.val) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(root.val);
                result.add(list);
            }
            return result;
        }
        List<List<Integer>> left = _pathSum(root.left, sum - root.val);
        List<List<Integer>> right = _pathSum(root.right, sum - root.val);
        for (List<Integer> list : left) {
            List<Integer> temp = new ArrayList<Integer>();
            temp.add(root.val);
            temp.addAll(list);
            result.add(temp);
        }
        for (List<Integer> list : right) {
            List<Integer> temp = new ArrayList<Integer>();
            temp.add(root.val);
            temp.addAll(list);
            result.add(temp);
        }
        return result;
    }

    /**
     * 114. Flatten Binary Tree to Linked List
     * Given a binary tree, flatten it to a linked list in-place.
     * For example,
     * Given
     * 1
     * / \
     * 2   5
     * / \   \
     * 3   4   6
     * The flattened tree should look like:
     * 1
     * \
     * 2
     * \
     * 3
     * \
     * 4
     * \
     * 5
     * \
     * 6
     */
    public static void flatten(TreeNode root) {

        _flatten(root);
    }

    public static TreeNode _flatten(TreeNode root) {

        if (root == null) {
            return null;
        } else if (root.left == null && root.right == null) {
            return root;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        root.right = null;

        TreeNode leftTail = _flatten(left);
        TreeNode rightTail = _flatten(right);

        if (leftTail == null) {
            root.right = right;
            return rightTail;
        } else {
            root.right = left;
            leftTail.right = right;
            return rightTail == null ? leftTail : rightTail;
        }
    }


    /**
     * 115. Distinct Subsequences
     * Given a string S and a string T, count the number of distinct subsequences of T in S.
     * A subsequence of a string is a new string which is formed from the original string by deleting some (can be
     * none)
     * of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a
     * subsequence of "ABCDE" while "AEC" is not).
     * Here is an example:
     * S = "rabbbit", T = "rabbit"
     * Return 3.
     */
    public static int numDistinct(String S, String T) {

        int sizeS = S.length();
        int sizeT = T.length();
        if (sizeS < sizeT || sizeS == 0) {
            return 0;
        }
        int[][] d = new int[sizeT + 1][sizeS + 1];
        for (int i = 0; i <= sizeT; i++) {
            for (int j = i; j <= sizeS; j++) {
                if (i == 0) {
                    d[i][j] = 1;
                } else if (i == j) {
                    d[i][j] = T.charAt(i - 1) == S.charAt(j - 1) ? d[i - 1][j - 1] : 0;
                } else {
                    d[i][j] = (T.charAt(i - 1) == S.charAt(j - 1) ? d[i - 1][j - 1] : 0) + d[i][j - 1];
                }
            }
        }
        return d[sizeT][sizeS];
    }

    /**
     * 116. Populating Next Right Pointers in Each Node
     * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer
     * should be set to NULL.
     * Initially, all next pointers are set to NULL.
     * Note:
     * You may only use constant extra space.
     * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent
     * has two
     * children).
     * For example,
     * Given the following perfect binary tree,
     * 1
     * /  \
     * 2    3
     * / \  / \
     * 4  5  6  7
     * After calling your function, the tree should look like:
     * 1 -> NULL
     * /  \
     * 2 -> 3 -> NULL
     * / \  / \
     * 4->5->6->7 -> NULL
     */
    public static class TreeLinkNode {
        int          val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }

    public static void connect(TreeLinkNode root) {

        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        TreeLinkNode next = root.next;
        TreeLinkNode levelNext = null;
        while (next != null && next.left == null && next.right == null) {
            next = next.next;
        }
        if (next != null) {
            levelNext = next.left == null ? next.right : next.left;
        }
        if (root.left == null) {
            root.right.next = levelNext;
        } else if (root.right == null) {
            root.left.next = levelNext;
        } else {
            root.left.next = root.right;
            root.right.next = levelNext;
        }
        connect(root.right);
        connect(root.left);


        //        if (root == null) {
        //            return;
        //        }
        //        boolean markNew = true;
        //        TreeLinkNode mark = root;
        //        TreeLinkNode lastOne = null;
        //        Deque<TreeLinkNode> dequeue = new ArrayDeque<TreeLinkNode>();
        //        dequeue.addLast(root);
        //        while (!dequeue.isEmpty()) {
        //            TreeLinkNode node = dequeue.removeFirst();
        //            TreeLinkNode left = node.left;
        //            TreeLinkNode right = node.right;
        //            if (left != null) {
        //                dequeue.addLast(left);
        //            }
        //            if (right != null) {
        //                dequeue.addLast(right);
        //            }
        //            if (node == mark) {
        //                markNew = true;
        //                if (node != root) {
        //                    lastOne = node;
        //                }
        //            } else if (lastOne != null) {
        //                lastOne.next = node;
        //                lastOne = node;
        //            }
        //            if (markNew) {
        //                if (left != null) {
        //                    mark = left;
        //                    markNew = false;
        //                } else if (right != null) {
        //                    mark = right;
        //                    markNew = false;
        //                }
        //            }
        //        }
    }


    /**
     * 117. Populating Next Right Pointers in Each Node II
     * Follow up for problem "Populating Next Right Pointers in Each Node".
     * What if the given tree could be any binary tree? Would your previous solution still work?
     * Note:
     * You may only use constant extra space.
     * For example,
     * Given the following binary tree,
     * 1
     * /  \
     * 2    3
     * / \    \
     * 4   5    7
     * After calling your function, the tree should look like:
     * 1 -> NULL
     * /  \
     * 2 -> 3 -> NULL
     * / \    \
     * 4-> 5 -> 7 -> NULL
     */
    public void connect2(TreeLinkNode root) {

        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        TreeLinkNode next = root.next;
        TreeLinkNode levelNext = null;
        while (next != null && next.left == null && next.right == null) {
            next = next.next;
        }
        if (next != null) {
            levelNext = next.left == null ? next.right : next.left;
        }
        if (root.left == null) {
            root.right.next = levelNext;
        } else if (root.right == null) {
            root.left.next = levelNext;
        } else {
            root.left.next = root.right;
            root.right.next = levelNext;
        }
        connect(root.right);
        connect(root.left);

        //        if (root == null) {
        //            return;
        //        }
        //        boolean markNew = true;
        //        TreeLinkNode mark = root;
        //        TreeLinkNode lastOne = null;
        //        Deque<TreeLinkNode> dequeue = new ArrayDeque<TreeLinkNode>();
        //        dequeue.addLast(root);
        //        while (!dequeue.isEmpty()) {
        //            TreeLinkNode node = dequeue.removeFirst();
        //            TreeLinkNode left = node.left;
        //            TreeLinkNode right = node.right;
        //            if (left != null) {
        //                dequeue.addLast(left);
        //            }
        //            if (right != null) {
        //                dequeue.addLast(right);
        //            }
        //            if (node == mark) {
        //                markNew = true;
        //                if (node != root) {
        //                    lastOne = node;
        //                }
        //            } else if (lastOne != null) {
        //                lastOne.next = node;
        //                lastOne = node;
        //            }
        //            if (markNew) {
        //                if (left != null) {
        //                    mark = left;
        //                    markNew = false;
        //                } else if (right != null) {
        //                    mark = right;
        //                    markNew = false;
        //                }
        //            }
        //        }
    }


    /**
     * 118. Pascal's Triangle
     * Given numRows, generate the first numRows of Pascal's triangle.
     * For example, given numRows = 5,
     * Return
     * [
     * [1],
     * [1,1],
     * [1,2,1],
     * [1,3,3,1],
     * [1,4,6,4,1]
     * ]
     */
    public static List<List<Integer>> generate(int numRows) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = 1; i <= numRows; i++) {
            if (i == 1) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                result.add(list);
            } else {
                List<Integer> last = result.get(result.size() - 1);
                List<Integer> list = new ArrayList<Integer>();
                for (int j = 0; j <= last.size(); j++) {
                    list.add((j == last.size() ? 0 : last.get(j)) + (j == 0 ? 0 : last.get(j - 1)));
                }
                result.add(list);
            }
        }
        return result;
    }

    /**
     * 119. Pascal's Triangle II
     * Given an index k, return the kth row of the Pascal's triangle.
     * For example, given k = 3,
     * Return [1,3,3,1].
     * Note:
     * Could you optimize your algorithm to use only O(k) extra space?
     */
    public static List<Integer> getRow(int rowIndex) {

        List<Integer> last = new ArrayList<Integer>();
        for (int i = 0; i <= rowIndex; i++) {
            if (i == 0) {
                last.add(1);
            } else {
                List<Integer> list = new ArrayList<Integer>();
                for (int j = 0; j <= last.size(); j++) {
                    list.add((j == last.size() ? 0 : last.get(j)) + (j == 0 ? 0 : last.get(j - 1)));
                }
                last = list;
            }
        }
        return last;
    }

    /**
     * 120. Triangle
     * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on
     * the
     * row below.
     * For example, given the following triangle
     * [
     * [2],
     * [3,4],
     * [6,5,7],
     * [4,1,8,3]
     * ]
     * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
     * Note:
     * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the
     * triangle.
     */
    public static int minimumTotal(List<List<Integer>> triangle) {

        List<Integer> rowPath = new ArrayList<Integer>();
        for (List<Integer> row : triangle) {
            List<Integer> temp = new ArrayList<Integer>();
            for (int i = 0; i <= rowPath.size(); i++) {
                if (i == 0) {
                    temp.add((rowPath.isEmpty() ? 0 : rowPath.get(0)) + row.get(0));
                } else if (i == rowPath.size()) {
                    temp.add(rowPath.get(i - 1) + row.get(i));
                } else {
                    temp.add(Math.min(rowPath.get(i - 1), rowPath.get(i)) + row.get(i));
                }
            }
            rowPath = temp;
        }
        if (rowPath.isEmpty()) {
            return 0;
        }
        int min = rowPath.get(0);
        for (int i = 1; i < rowPath.size(); i++) {
            min = Math.min(min, rowPath.get(i));
        }
        return min;
    }


    /**
     * Not very efficient
     */
    public static int minimumTotal2(List<List<Integer>> triangle) {

        if (triangle.size() == 0 || triangle.get(0).size() == 0) {
            return 0;
        }
        int[] min = new int[1];
        min[0] = Integer.MAX_VALUE;
        _minimumTotal2(triangle, 0, 0, new int[1], min);
        return min[0];
    }

    public static void _minimumTotal2(List<List<Integer>> triangle, int i, int j, int[] current, int[] min) {

        if (i < 0 || i >= triangle.size() || j < 0 || j >= triangle.get(i).size()) {
            return;
        }
        current[0] += triangle.get(i).get(j);
        if (!_minimumTotal2HasChild(triangle, i, j)) {
            min[0] = Math.min(min[0], current[0]);
        } else {
            _minimumTotal2(triangle, i + 1, j, current, min);
            _minimumTotal2(triangle, i + 1, j + 1, current, min);
        }
        current[0] -= triangle.get(i).get(j);
    }

    public static boolean _minimumTotal2HasChild(List<List<Integer>> triangle, int i, int j) {

        if (i < 0 || i >= triangle.size() - 1 || j < 0 || j >= triangle.get(i).size()) {
            return false;
        }
        return true;
    }


    /**
     * 121. Best Time to Buy and Sell Stock
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock),
     * design an algorithm to find the maximum profit.
     */
    public static int maxProfit1(int[] prices) {

        if (prices.length < 2) {
            return 0;
        }
        int maxProfit = 0;
        int minLeft = prices[0];
        for (int i = 0; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - minLeft);
            minLeft = Math.min(minLeft, prices[i]);
        }
        return maxProfit;
    }

    /**
     * 122. Best Time to Buy and Sell Stock II
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy
     * one
     * and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the
     * same
     * time (ie, you must sell the stock before you buy again).
     */
    public static int maxProfit2(int[] prices) {

        if (prices.length < 2) {
            return 0;
        }
        int maxProfit = 0;
        int prev = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] >= prev) {
                maxProfit += (prices[i] - prev);
            }
            prev = prices[i];
        }
        return maxProfit;
    }

    /**
     * 123. Best Time to Buy and Sell Stock III
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * Design an algorithm to find the maximum profit. You may complete at most two transactions.
     * Note:
     * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy
     * again).
     */
    public static int maxProfit3(int[] prices) {

        if (prices.length < 2) {
            return 0;
        }
        int minLeft = prices[0];
        int maxProfitOne = 0;
        int maxProfitTwo = 0;
        List<Integer> queue = new ArrayList<Integer>();
        for (int i = 0; i < prices.length; i++) {
            int currentPrice = prices[i];
            maxProfitOne = Math.max(maxProfitOne, currentPrice - minLeft);
            minLeft = Math.min(minLeft, currentPrice);
            if (queue.isEmpty()) {
                queue.add(currentPrice);
            } else {
                int size = queue.size();
                if (currentPrice > queue.get(queue.size() - 1)) {
                    if (size % 2 == 0) {
                        queue.remove(queue.size() - 1);
                    }
                    queue.add(currentPrice);
                } else if (currentPrice < queue.get(queue.size() - 1)) {
                    if (size % 2 != 0) {
                        queue.remove(queue.size() - 1);
                    }
                    queue.add(currentPrice);
                }
            }
        }
        minLeft = queue.get(0);
        int profitMaxL = 0;
        for (int i = 0; i < queue.size(); i++) {
            profitMaxL = Math.max(profitMaxL, queue.get(i) - minLeft);
            minLeft = Math.min(minLeft, queue.get(i));

            int profitMaxR = 0;
            int minRight = queue.get(i);
            for (int j = i; j < queue.size(); j++) {
                profitMaxR = Math.max(profitMaxR, queue.get(j) - minRight);
                minRight = Math.min(minRight, queue.get(j));
            }
            maxProfitTwo = Math.max(maxProfitTwo, profitMaxL + profitMaxR);
        }
        return Math.max(maxProfitOne, maxProfitTwo);
    }

    /**
     * 124. Binary Tree Maximum Path Sum
     * Given a binary tree, find the maximum path sum.
     * The path may start and end at any node in the tree.
     * For example:
     * Given the below binary tree,
     * 1
     * / \
     * 2   3
     * Return 6.
     */

    public static int maxPathSum(TreeNode root) {

        if (root == null) {
            return 0;
        }
        int[] max = new int[1];
        max[0] = root.val;
        _maxPathSum(root, max);
        return max[0];
    }

    public static int _maxPathSum(TreeNode root, int[] max) {

        if (root == null) {
            return 0;
        } else {
            int left = Math.max(0, _maxPathSum(root.left, max));
            int right = Math.max(0, _maxPathSum(root.right, max));
            max[0] = Math.max(max[0], left + right + root.val);
            return root.val + Math.max(left, right);
        }
    }

    /**
     * 125. Valid Palindrome
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     * For example,
     * "A man, a plan, a canal: Panama" is a palindrome.
     * "race a car" is not a palindrome.
     * Note:
     * Have you consider that the string might be empty? This is a good question to ask during an interview.
     * For the purpose of this problem, we define empty string as valid palindrome.
     */
    public static boolean isPalindrome(String s) {

        if (s.length() == 0) {
            return true;
        }
        int index1 = 0;
        int index2 = s.length() - 1;
        while (index1 < index2) {
            char c1 = s.charAt(index1);
            char c2 = s.charAt(index2);

            if (!((c1 >= 'A' && c1 <= 'Z') || (c1 >= 'a' && c1 <= 'z') || (c1 >= '0' && c1 <= '9'))) {
                index1++;
            } else if (!((c2 >= 'A' && c2 <= 'Z') || (c2 >= 'a' && c2 <= 'z') || (c2 >= '0' && c2 <= '9'))) {
                index2--;
            } else {
                if (!isSameChar(c1, c2)) {
                    return false;
                } else {
                    index1++;
                    index2--;
                }
            }
        }
        return true;
    }

    public static boolean isSameChar(char a, char b) {

        if (a == b) {
            return true;
        }
        int iA = (int) a;
        int iB = (int) b;
        if (iA >= 65 && iA <= 90) {
            iA += 32;
        }
        if (iB >= 65 && iB <= 90) {
            iB += 32;
        }
        return iA == iB;
    }


    /**
     * 126. Word Ladder II
     * Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to
     * end, such that:
     * Only one letter can be changed at a time
     * Each intermediate word must exist in the dictionary
     * For example,
     * Given:
     * start = "hit"
     * end = "cog"
     * dict = ["hot","dot","dog","lot","log"]
     * Return
     * [
     * ["hit","hot","dot","dog","cog"],
     * ["hit","hot","lot","log","cog"]
     * ]
     * Note:
     * All words have the same length.
     * All words contain only lowercase alphabetic characters.
     */
    public static List<List<String>> findLadders(String start, String end, Set<String> dict) {

        if (dict.contains(start)) {
            dict.remove(start);
        }
        if (!dict.contains(end)) {
            dict.add(end);
        }
        List<List<String>> result = new ArrayList<List<String>>();
        Set<String> lastRow = new HashSet<String>();
        lastRow.add(start);
        result.add(new ArrayList<String>(lastRow));
        while (!lastRow.contains(end)) {
            if (lastRow.isEmpty() || dict.isEmpty()) {
                return new ArrayList<List<String>>();
            }
            Set<String> toBeRemoved = new HashSet<String>();
            List<List<String>> temp = new ArrayList<List<String>>();
            int size = result.size();
            for (String wordLastRow : lastRow) {
                for (String word : dict) {
                    if (oneChange(wordLastRow, word)) {
                        toBeRemoved.add(word);
                        for (int i = 0; i < size; i++) {
                            List<String> path = result.get(i);
                            if (path.get(path.size() - 1).equals(wordLastRow)) {
                                List<String> list = new ArrayList<String>(path);
                                list.add(word);
                                temp.add(list);
                            }
                        }
                    }
                }
            }
            result = temp;
            dict.removeAll(toBeRemoved);
            lastRow = toBeRemoved;
        }
        return result;
    }


    /**
     * 127. Word Ladder
     * Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from
     * start
     * to end, such that:
     * Only one letter can be changed at a time
     * Each intermediate word must exist in the dictionary
     * For example,
     * Given:
     * start = "hit"
     * end = "cog"
     * dict = ["hot","dot","dog","lot","log"]
     * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     * return its length 5.
     * Note:
     * Return 0 if there is no such transformation sequence.
     * All words have the same length.
     * All words contain only lowercase alphabetic characters.
     */
    public static int ladderLength(String start, String end, Set<String> dict) {

        if (dict.contains(start)) {
            dict.remove(start);
        }
        if (!dict.contains(end)) {
            dict.add(end);
        }
        int level = 1;
        Set<String> lastRow = new HashSet<String>();
        lastRow.add(start);
        while (!lastRow.contains(end)) {
            if (lastRow.isEmpty() || dict.isEmpty()) {
                return 0;
            }
            Set<String> toBeRemoved = new HashSet<String>();
            for (String wordLastRow : lastRow) {
                for (String word : dict) {
                    if (oneChange(wordLastRow, word)) {
                        toBeRemoved.add(word);
                    }
                }
            }
            dict.removeAll(toBeRemoved);
            lastRow = toBeRemoved;
            level++;
        }
        return level;
    }

    public static boolean oneChange(String str1, String str2) {

        if (str1.length() != str2.length()) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                count++;
            }
        }
        return count == 1;
    }


    /**
     * 128. Longest Consecutive Sequence
     * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
     * For example,
     * Given [100, 4, 200, 1, 3, 2],
     * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
     * Your algorithm should run in O(n) complexity.
     */
    public static class INTBOOL {
        int value;
        boolean bool = false;

        public INTBOOL(int v, boolean b) {
            value = v;
            bool = b;
        }
    }

    public static int longestConsecutive(int[] num) {

        if (num.length <= 1) {
            return num.length;
        }
        Map<Integer, INTBOOL> values = new HashMap<Integer, INTBOOL>();
        for (int n : num) {
            values.put(n, new INTBOOL(1, false));
        }
        for (int n : values.keySet()) {
            if (!values.get(n).bool) {
                _longestConsecutive(n, values);
            }
        }
        int max = 0;
        for (int n : num) {
            max = Math.max(max, values.get(n).value);
        }
        return max;
    }

    public static int _longestConsecutive(int num, Map<Integer, INTBOOL> values) {

        if (values.containsKey(num)) {
            INTBOOL attrib = values.get(num);
            if (!attrib.bool) {
                attrib.bool = true;
                if (values.containsKey(num - 1)) {
                    int len = _longestConsecutive(num - 1, values);
                    attrib.value = len + 1;
                }
            }
            return attrib.value;
        }
        return 0;
    }


    /**
     * 129. Sum Root to Leaf Numbers
     * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
     * An example is the root-to-leaf path 1->2->3 which represents the number 123.
     * Find the total sum of all root-to-leaf numbers.
     * For example,
     * 1
     * / \
     * 2   3
     * The root-to-leaf path 1->2 represents the number 12.
     * The root-to-leaf path 1->3 represents the number 13.
     * Return the sum = 12 + 13 = 25.
     */
    public static int sumNumbers(TreeNode root) {

        if (root == null) {
            return 0;
        }
        int[] sum = new int[1];
        _sumNumbers(root, 0, sum);
        return sum[0];
    }

    public static void _sumNumbers(TreeNode root, int value, int[] sum) {

        if (root == null) {
            return;
        }
        value = 10 * value + root.val;
        if (root.left == null && root.right == null) {
            sum[0] += value;
        } else {
            _sumNumbers(root.left, value, sum);
            _sumNumbers(root.right, value, sum);
        }
    }


    /**
     * 130. Surrounded Regions
     * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
     * A region is captured by flipping all 'O's into 'X's in that surrounded region.
     * For example,
     * X X X X
     * X O O X
     * X X O X
     * X O X X
     * After running your function, the board should be:
     * X X X X
     * X X X X
     * X X X X
     * X O X X
     */
    public static void solve(char[][] board) {

        if (board == null || board.length == 0) {
            return;
        }
        int rows = board.length;
        int cols = board[0].length;
        if (rows < 3 || cols < 3) {
            return;
        }

        List<Integer> indexX = new ArrayList<Integer>();
        List<Integer> indexY = new ArrayList<Integer>();

        int[][] marks = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            if (board[i][0] != 'X' && marks[i][0] != 1) {
                indexX.add(i);
                indexY.add(0);
            }
            if (board[i][cols - 1] != 'X' && marks[i][cols - 1] != 1) {
                indexX.add(i);
                indexY.add(cols - 1);
            }
        }
        for (int j = 1; j < cols - 1; j++) {
            if (board[0][j] != 'X' && marks[0][j] != 1) {
                indexX.add(0);
                indexY.add(j);
            }
            if (board[rows - 1][j] != 'X' && marks[rows - 1][j] != 1) {
                indexX.add(rows - 1);
                indexY.add(j);
            }
        }


        while (!indexX.isEmpty()) {

            int x = indexX.remove(0);
            int y = indexY.remove(0);

            marks[x][y] = 1;

            int leftX = x;
            int leftY = y - 1;
            int upX = x - 1;
            int upY = y;
            int rightX = x;
            int rightY = y + 1;
            int downX = x + 1;
            int downY = y;

            if (rightY < cols && marks[rightX][rightY] != 1 && board[rightX][rightY] != 'X') {
                indexX.add(rightX);
                indexY.add(rightY);
            }
            if (downX < rows && marks[downX][downY] != 1 && board[downX][downY] != 'X') {
                indexX.add(downX);
                indexY.add(downY);
            }
            if (upX >= 0 && marks[upX][upY] != 1 && board[upX][upY] != 'X') {
                indexX.add(upX);
                indexY.add(upY);
            }
            if (leftY >= 0 && marks[leftX][leftY] != 1 && board[leftX][leftY] != 'X') {
                indexX.add(leftX);
                indexY.add(leftY);
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (marks[i][j] != 1) {
                    board[i][j] = 'X';
                }
            }
        }
    }


    /**
     * 131. Palindrome Partitioning
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     * Return all possible palindrome partitioning of s.
     * For example, given s = "aab",
     * Return
     * [
     * ["aa","b"],
     * ["a","a","b"]
     * ]
     */
    public static List<List<String>> partition(String s) {

        int size = s.length();
        if (size == 0) {
            return new ArrayList<List<String>>();
        }
        boolean[][] d = new boolean[size][size];
        for (int i = size - 1; i >= 0; i--) {
            for (int j = i; j < size; j++) {
                if (i == j) {
                    d[i][j] = true;
                } else if (i + 1 == j) {
                    d[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    d[i][j] = s.charAt(i) == s.charAt(j) && d[i + 1][j - 1];
                }
            }
        }
        return new ArrayList<List<String>>(_partition(s, 0, d));
    }

    public static Set<List<String>> _partition(String s, int start, boolean[][] d) {

        Set<List<String>> result = new HashSet<List<String>>();
        if (start >= s.length()) {
            return result;
        }
        for (int i = start; i < s.length(); i++) {
            if (d[start][i]) {
                if (i < s.length() - 1) {
                    Set<List<String>> rest = _partition(s, i + 1, d);
                    for (List<String> _rest : rest) {
                        List<String> list = new ArrayList<String>();
                        list.add(s.substring(start, i + 1));
                        list.addAll(_rest);
                        result.add(list);
                    }
                } else {
                    List<String> list = new ArrayList<String>();
                    list.add(s.substring(start));
                    result.add(list);
                }
            }
        }
        return result;
    }


    /**
     * 132. Palindrome Partitioning II
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     * Return the minimum cuts needed for a palindrome partitioning of s.
     * For example, given s = "aab",
     * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
     */
    public static int minCut(String s) {

        int size = s.length();
        if (size == 0) {
            return 0;
        }
        boolean[][] d = new boolean[size][size];
        for (int i = size - 1; i >= 0; i--) {
            for (int j = i; j < size; j++) {
                if (i == j) {
                    d[i][j] = true;
                } else if (i + 1 == j) {
                    d[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    d[i][j] = s.charAt(i) == s.charAt(j) && d[i + 1][j - 1];
                }
            }
        }
        int[] cut = new int[size];
        for (int i = 0; i < size; i++) {
            cut[i] = i;
            if (d[0][i]) {
                cut[i] = 0;
            } else {
                for (int j = 0; j < i; j++) {
                    if (d[j + 1][i]) {
                        cut[i] = Math.min(cut[i], cut[j] + 1);
                    }
                }
            }
        }
        return cut[size - 1];
    }

    /**
     * 133. Clone Graph
     * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
     * OJ's undirected graph serialization:
     * Nodes are labeled uniquely.
     * We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
     * As an example, consider the serialized graph {0,1,2#1,2#2,2}.
     * The graph has a total of three nodes, and therefore contains three parts as separated by #.
     * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
     * Second node is labeled as 1. Connect node 1 to node 2.
     * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
     * Visually, the graph looks like the following:
     * 1
     * / \
     * /   \
     * 0 --- 2
     * / \
     * \_/
     */
    public static class UndirectedGraphNode {
        int                       label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }

    public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {

        if (node == null) {
            return null;
        }
        Map<Integer, UndirectedGraphNode> map = new HashMap<Integer, UndirectedGraphNode>();
        return _cloneGraph(node, map);
    }

    public static UndirectedGraphNode _cloneGraph(UndirectedGraphNode node, Map<Integer, UndirectedGraphNode> map) {

        if (node == null) {
            return null;
        }
        int hash = node.hashCode();
        if (map.containsKey(hash)) {
            return map.get(hash);
        } else {
            UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
            map.put(hash, newNode);
            for (UndirectedGraphNode neighbor : node.neighbors) {
                newNode.neighbors.add(_cloneGraph(neighbor, map));
            }
            return newNode;
        }
    }


    /**
     * 134. Gas Station
     * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
     * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next
     * station (i+1). You begin the journey with an empty tank at one of the gas stations.
     * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
     * Note:
     * The solution is guaranteed to be unique.
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {

        if (gas.length == 0 || cost.length == 0 || gas.length != cost.length) {
            return -1;
        }
        int size = gas.length;
        int max = gas[0];
        int maxIndex = 0;
        for (int i = 0; i < size; i++) {
            if (max < gas[i]) {
                max = gas[i];
                maxIndex = i;
            }
        }
        int startIndex = maxIndex;
        for (int i = 0; i < size; i++) {
            int tank = 0;
            int count = 0;
            int current = startIndex;
            while (count < size) {
                tank += gas[current];
                if (tank < cost[current]) {
                    break;
                }
                tank -= cost[current];
                current = current == size - 1 ? 0 : current + 1;
                count++;
            }
            if (count >= size) {
                return startIndex;
            }
            startIndex = startIndex == size - 1 ? 0 : startIndex + 1;
        }
        return -1;
    }


    /**
     * 135. Candy
     * There are N children standing in a line. Each child is assigned a rating value.
     * You are giving candies to these children subjected to the following requirements:
     * Each child must have at least one candy.
     * Children with a higher rating get more candies than their neighbors.
     * What is the minimum candies you must give?
     */
    public static int candy(int[] ratings) {

        int size = ratings.length;
        if (size < 2) {
            return size;
        }
        int[] candies = new int[size];
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                candies[i] = 1;
            } else if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            } else if (ratings[i] < ratings[i - 1]) {
                candies[i] = candies[i - 1] - 1;
            } else {
                candies[i] = candies[i - 1];
            }
            if ((i > 0 && i < size - 1 && ratings[i - 1] > ratings[i] && ratings[i] <= ratings[i + 1]) || (i > 0 && i
                    == size - 1 && ratings[i - 1] > ratings[i])) {
                reAdjustCandy(ratings, candies, i);
            }
        }
        int total = 0;
        for (int candy : candies) {
            total += candy;
        }
        return total;
    }

    public static void reAdjustCandy(int[] ratings, int[] candies, int startIndex) {

        int k = startIndex;
        int diff = 1 - candies[k];
        while (k > 0 && ratings[k - 1] > ratings[k]) {
            candies[k] = candies[k] + diff;
            k--;
        }
        if (diff > 0) {
            candies[k] += diff;
        }
    }


    /**
     * 136. Single Number
     * Given an array of integers, every element appears twice except for one. Find that single one.
     * Note:
     * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
     */
    public static int singleNumber1(int[] A) {

        int result = 0;
        for (int a : A) {
            result = (~a & result) | (a & ~result);
        }
        return result;
    }


    /**
     * 137. Single Number II
     * Given an array of integers, every element appears three times except for one. Find that single one.
     * Note:
     * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
     */
    public static int singleNumber2(int[] A) {

        int result = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int a : A) {
                if (((a >> i) & 1) != 0) {
                    count++;
                }
            }
            result |= ((count % 3) << i);
        }
        return result;
    }


    /**
     * 138. Copy List with Random Pointer
     * A linked list is given such that each node contains an additional random pointer which could point to any node
     * in
     * the list or null.
     * Return a deep copy of the list.
     */
    public static class RandomListNode {
        int            label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }

    public static RandomListNode copyRandomList(RandomListNode head) {

        return _copyRandomList(head, new HashMap<Integer, RandomListNode>());
    }

    public static RandomListNode _copyRandomList(RandomListNode head, Map<Integer, RandomListNode> map) {

        if (head == null) {
            return null;
        }
        int hash = head.hashCode();
        if (map.containsKey(hash)) {
            return map.get(hash);
        } else {
            RandomListNode newNode = new RandomListNode(head.label);
            map.put(hash, newNode);
            newNode.random = _copyRandomList(head.random, map);
            newNode.next = _copyRandomList(head.next, map);
            return newNode;
        }
    }


    /**
     * 139. Word Break
     * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence
     * of one or more dictionary words.
     * For example, given
     * s = "leetcode",
     * dict = ["leet", "code"].
     * Return true because "leetcode" can be segmented as "leet code".
     */
    public static boolean wordBreak1(String s, Set<String> dict) {

        if (dict.size() == 0 || s.length() == 0) {
            return false;
        }
        boolean[] d = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            String head = s.substring(0, i + 1);
            if (dict.contains(head)) {
                d[i] = true;
            } else {
                for (int j = 0; j <= i; j++) {
                    if (d[j] && dict.contains(s.substring(j + 1, i + 1))) {
                        d[i] = true;
                        break;
                    }
                }
            }
        }
        return d[s.length() - 1];
    }


    /**
     * 140. Word Break II
     * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a
     * valid dictionary word.
     * Return all such possible sentences.
     * For example, given
     * s = "catsanddog",
     * dict = ["cat", "cats", "and", "sand", "dog"].
     * A solution is ["cats and dog", "cat sand dog"].
     */
    public static List<String> wordBreak2(String s, Set<String> dict) {

        if (dict.size() == 0 || s.length() == 0) {
            return new ArrayList<String>();
        }
        boolean[][] d = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (dict.contains(s.substring(i, j + 1))) {
                    d[i][j] = true;
                    continue;
                }
                for (int k = i; k <= j; k++) {
                    if (d[i][k] && dict.contains(s.substring(k + 1, j + 1))) {
                        d[i][j] = true;
                        break;
                    }
                }
            }
        }
        if (!d[0][s.length() - 1]) {
            return new ArrayList<String>();
        }
        List<List<String>> lists = _wordBreak2(s, 0, dict, d);
        List<String> result = new ArrayList<String>();
        for (List<String> list : lists) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                builder.append(list.get(i));
                if (i != list.size() - 1) {
                    builder.append(" ");
                }
            }
            result.add(builder.toString());
        }
        return result;
    }

    public static List<List<String>> _wordBreak2(String s, int startIndex, Set<String> dict, boolean[][] d) {

        List<List<String>> result = new ArrayList<List<String>>();
        for (int i = startIndex; i < s.length(); i++) {
            String head = s.substring(startIndex, i + 1);
            if (dict.contains(head)) {
                if (i == s.length() - 1) {
                    List<String> list = new ArrayList<String>();
                    list.add(head);
                    result.add(list);
                } else if (d[i + 1][s.length() - 1]) {
                    List<List<String>> rest = _wordBreak2(s, i + 1, dict, d);
                    for (List<String> _rest : rest) {
                        List<String> list = new ArrayList<String>();
                        list.add(head);
                        list.addAll(_rest);
                        result.add(list);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 141. Linked List Cycle
     * Given a linked list, determine if it has a cycle in it.
     * Follow up:
     * Can you solve it without using extra space?
     */
    public static boolean hasCycle(ListNode head) {

        if (head == null) {
            return false;
        }
        boolean first = true;
        ListNode temp1 = head;
        ListNode temp2 = head;
        while ((first || temp1 != temp2) && temp1 != null && temp2 != null) {
            temp1 = temp1.next;
            temp2 = temp2.next;
            if (temp2 == null) {
                return false;
            } else {
                temp2 = temp2.next;
            }
            first = false;
        }
        if (temp1 == null || temp2 == null) {
            return false;
        }
        return true;
    }

    /**
     * 142. Linked List Cycle II
     * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
     * Follow up:
     * Can you solve it without using extra space?
     */
    public static ListNode detectCycle(ListNode head) {

        if (head == null) {
            return null;
        }
        boolean first = true;
        ListNode temp1 = head;
        ListNode temp2 = head;
        while ((first || temp1 != temp2) && temp1 != null && temp2 != null) {
            temp1 = temp1.next;
            temp2 = temp2.next;
            if (temp2 == null) {
                return null;
            } else {
                temp2 = temp2.next;
            }
            first = false;
        }
        if (temp1 == null || temp2 == null) {
            return null;
        }
        temp1 = head;
        while (temp1 != temp2) {
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        return temp1;
    }

    /**
     * 143. Reorder List
     * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
     * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
     * You must do this in-place without altering the nodes' values.
     * For example,
     * Given {1,2,3,4}, reorder it to {1,4,2,3}.
     */
    public static ListNode reorderList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        boolean even = false;
        ListNode temp1 = head;
        ListNode temp2 = head;
        while (temp1 != null && temp2 != null) {
            temp2 = temp2.next.next;
            if (temp2 == null) {
                even = true;
                break;
            } else if (temp2.next == null) {
                even = false;
                break;
            }
            temp1 = temp1.next;
        }
        ListNode head2 = null;
        ListNode last = null;
        if (even) {
            head2 = temp1.next;
            temp1.next = null;
        } else {
            last = temp1.next;
            temp1.next = null;
            head2 = last.next;
            last.next = null;
        }
        return _joinLists(head, _reverseList(head2), last);
    }

    public static ListNode _reverseList(ListNode head) {

        if (head == null) {
            return null;
        }
        ListNode temp1 = head;
        ListNode temp2 = head.next;
        while (temp2 != null) {
            ListNode next = temp2.next;
            temp2.next = temp1;
            temp1 = temp2;
            temp2 = next;
        }
        return temp1;
    }

    public static ListNode _joinLists(ListNode head1, ListNode head2, ListNode last) {

        ListNode temp1 = head1;
        ListNode temp2 = head2;
        ListNode tail = null;
        while (temp1 != null && temp2 != null) {
            ListNode next1 = temp1.next;
            ListNode next2 = temp2.next;
            temp1.next = temp2;
            temp2.next = next1;
            temp1 = next1;
            tail = temp2;
            temp2 = next2;
        }
        if (tail != null) {
            tail.next = last;
        }
        return head1;
    }

    /**
     * 144. Binary Tree Preorder Traversal
     * Solution
     * Given a binary tree, return the preorder traversal of its nodes' values.
     * For example:
     * Given binary tree {1,#,2,3},
     * 1
     * \
     * 2
     * /
     * 3
     * return [1,2,3].
     * Note: Recursive solution is trivial, could you do it iteratively?
     */
    public static List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                result.add(node.val);
                node = node.left;
            } else {
                node = stack.pop();
                node = node.right;
            }
        }
        return result;
    }


    /**
     * 145. Binary Tree Postorder Traversal
     * Solution
     * Given a binary tree, return the postorder traversal of its nodes' values.
     * For example:
     * Given binary tree {1,#,2,3},
     * 1
     * \
     * 2
     * /
     * 3
     * return [3,2,1].
     * Note: Recursive solution is trivial, could you do it iteratively?
     */
    public static List<Integer> postorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }
        TreeNode node = root;
        TreeNode lastVisited = null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (!stack.isEmpty() || node != null) {
            if (node.left != null) {
                stack.push(node);
                node = node.left;
            } else {
                TreeNode peekNode = stack.peek();
                if (peekNode.right != null && peekNode.right != lastVisited) {
                    node = peekNode.right;
                } else {
                    result.add(peekNode.val);
                    lastVisited = stack.pop();
                }
            }
        }
        return result;
    }

    /**
     * 146. LRU Cache
     * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following
     * operations: get and set.
     * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return
     * -1.
     * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its
     * capacity,
     * it should invalidate the least recently used item before inserting a new item.
     */
    public static class LRUCache {

        public static class DoubleLinkedListNode {
            int key;
            int val;
            DoubleLinkedListNode prev = null;
            DoubleLinkedListNode next = null;

            public DoubleLinkedListNode(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        int cap;
        DoubleLinkedListNode               head = null;
        DoubleLinkedListNode               tail = null;
        Map<Integer, DoubleLinkedListNode> map  = new HashMap<Integer, DoubleLinkedListNode>();

        public LRUCache(int capacity) {
            this.cap = capacity;
        }

        public int get(int key) {

            if (!map.containsKey(key) || cap == 0) {
                return -1;
            }
            DoubleLinkedListNode node = map.get(key);
            if (head != node) {
                node.prev.next = node.next;
                if (node.next != null) {
                    node.next.prev = node.prev;
                } else {
                    tail = node.prev;
                }
                node.prev = null;
                node.next = head;
                head.prev = node;
                head = node;
            }
            return node.val;
        }

        public void set(int key, int value) {

            if (cap == 0) {
                return;
            }
            if (map.containsKey(key)) {
                DoubleLinkedListNode node = map.get(key);
                node.val = value;
                if (head != node) {
                    node.prev.next = node.next;
                    if (node.next != null) {
                        node.next.prev = node.prev;
                    } else {
                        tail = node.prev;
                    }
                    node.prev = null;
                    node.next = head;
                    head.prev = node;
                    head = node;
                }
            } else {
                if (map.size() == cap) {
                    map.remove(tail.key);
                    if (tail.prev == null) {
                        head = null;
                        tail = null;
                    } else {
                        DoubleLinkedListNode newTail = tail.prev;
                        newTail.next = null;
                        tail.prev = null;
                        tail = newTail;
                    }
                }
                DoubleLinkedListNode newNode = new DoubleLinkedListNode(key, value);
                map.put(key, newNode);
                if (head == null) {
                    head = newNode;
                    tail = newNode;
                } else {
                    newNode.next = head;
                    head.prev = newNode;
                    head = newNode;
                }
            }
        }
    }

    /**
     * 147. Insertion Sort List
     * Sort a linked list using insertion sort.
     */
    public static ListNode insertionSortList(ListNode head) {

        if (head == null) {
            return null;
        }
        ListNode newHead = head;
        ListNode temp = head;
        ListNode parent = null;
        while (temp != null) {
            ListNode next = temp.next;
            temp.next = null;
            if (parent != null) {
                parent.next = null;
                newHead = _insertionSortList(newHead, temp);
            }
            ListNode temp1 = newHead;
            while (temp1 != null) {
                parent = temp1;
                temp1 = temp1.next;
            }
            if (parent != null) {
                parent.next = next;
            }
            temp = next;
        }
        return null;
    }

    public static ListNode _insertionSortList(ListNode head, ListNode target) {

        ListNode newHead = head;
        ListNode temp = head;
        ListNode parent = null;
        while (temp != null && temp.val <= target.val) {
            parent = temp;
            temp = temp.next;
        }
        if (parent == null) {
            newHead = target;
            newHead.next = head;
        } else {
            parent.next = target;
            target.next = temp;
        }
        return newHead;
    }

    /**
     * 148. Sort List
     * Sort a linked list in O(n log n) time using constant space complexity.
     */
    public static ListNode sortList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode temp = head.next.next;
        ListNode parent = head;
        while (temp != null) {
            parent = parent.next;
            temp = temp.next;
            if (temp != null) {
                temp = temp.next;
            }
        }
        ListNode head2 = parent.next;
        parent.next = null;
        return _mergeSortedList(sortList(head), sortList(head2));
    }

    public static ListNode _mergeSortedList(ListNode head1, ListNode head2) {

        if (head1 == null) {
            return head2;
        } else if (head2 == null) {
            return head1;
        }
        ListNode temp1 = head1;
        ListNode temp2 = head2;
        ListNode newHead = null;
        ListNode temp = null;
        while (temp1 != null && temp2 != null) {
            if (newHead == null) {
                if (temp1.val <= temp2.val) {
                    newHead = temp1;
                    temp1 = temp1.next;
                } else {
                    newHead = temp2;
                    temp2 = temp2.next;
                }
                temp = newHead;
                temp.next = null;
            } else {
                if (temp1.val <= temp2.val) {
                    temp.next = temp1;
                    temp1 = temp1.next;
                } else {
                    temp.next = temp2;
                    temp2 = temp2.next;
                }
                temp = temp.next;
                temp.next = null;
            }
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return newHead;
    }

    /**
     * 149. Max Points on a Line
     * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
     */
    public static class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    public static int maxPoints(Point[] points) {

        if (points.length <= 2) {
            return points.length;
        }
        int max = 0;
        for (int i = 0; i < points.length; i++) {
            int aX = points[i].x;
            int aY = points[i].y;
            int dup = 1;
            Map<Float, Integer> map = new HashMap<Float, Integer>();
            for (int j = i + 1; j < points.length; j++) {
                int bX = points[j].x;
                int bY = points[j].y;
                if (aX == bX && aY == bY) {
                    dup++;
                } else {
                    float key = (aX == bX) ? Float.MAX_VALUE : (aY == bY ? 0 : (float) (bY - aY) / (float) (bX - aX));
                    if (map.containsKey(key)) {
                        map.put(key, map.get(key) + 1);
                    } else {
                        map.put(key, 1);
                    }
                }
            }
            for (Float key : map.keySet()) {
                max = Math.max(max, map.get(key) + dup);
            }
            max = Math.max(max, dup);
        }
        return max;
    }

    /**
     * 150. Evaluate Reverse Polish Notation
     * Solution
     * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
     * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
     * Some examples:
     * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
     * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
     */
    public static int evalRPN(String[] tokens) {

        Stack<String> stack = new Stack<String>();
        for (String s : tokens) {
            if (stack.isEmpty() || (!s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("/"))) {
                stack.push(s);
            } else {
                int b = Integer.parseInt(stack.pop());
                if (stack.isEmpty()) {
                    return 0;
                }
                int a = Integer.parseInt(stack.pop());
                int c;
                if (s.equals("+")) {
                    c = a + b;
                } else if (s.equals("-")) {
                    c = a - b;
                } else if (s.equals("*")) {
                    c = a * b;
                } else if (s.equals("/")) {
                    c = a / b;
                } else {
                    return 0;
                }
                stack.push(Integer.toString(c));
            }
        }
        return stack.isEmpty() ? 0 : Integer.parseInt(stack.pop());
    }

    /**
     * 151. Reverse Words in a String
     * Given an input string, reverse the string word by word.
     * For example,
     * Given s = "the sky is blue",
     * return "blue is sky the".
     * click to show clarification.
     * Clarification:
     * What constitutes a word?
     * A sequence of non-space characters constitutes a word.
     * Could the input string contain leading or trailing spaces?
     * Yes. However, your reversed string should not contain leading or trailing spaces.
     * How about multiple spaces between two words?
     * Reduce them to a single space in the reversed string.
     */
    public static String reverseWords(String s) {

        if (s.length() == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        List<String> words = new ArrayList<String>();
        String original = s + " ";
        for (int i = 0; i < original.length(); i++) {
            char c = original.charAt(i);
            if (c == ' ') {
                if (builder.length() > 0) {
                    words.add(builder.toString());
                    builder.delete(0, builder.length());
                }
            } else {
                builder.append(c);
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = words.size() - 1; i >= 0; i--) {
            result.append(words.get(i));
            if (i > 0) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    /**
     * 152. Maximum Product Subarray
     * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
     * For example, given the array [2,3,-2,4],
     * the contiguous subarray [2,3] has the largest product = 6.
     */
    public static int maxProduct(int[] A) {

        if (A.length == 0) {
            return 0;
        }
        int max = A[0];
        int pos = Math.max(0, A[0]);
        int neg = Math.min(0, A[0]);
        for (int i = 1; i < A.length; i++) {
            if (A[i] > 0) {
                pos = Math.max(pos * A[i], A[i]);
                neg *= A[i];
            } else if (A[i] < 0) {
                int oriPos = pos;
                pos = neg * A[i];
                neg = Math.min(A[i], oriPos * A[i]);
            } else {
                pos = 0;
                neg = 0;
            }
            max = Math.max(max, pos);
        }
        return max;
    }

    /**
     * 153. Find Minimum in Rotated Sorted Array
     * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
     * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2). 7 0 1 2 4 5 6
     * Find the minimum element.
     * You may assume no duplicate exists in the array.
     */
    public static int findMin1(int[] num) {

        return _findMin1(num, 0, num.length - 1);
    }

    public static int _findMin1(int[] num, int start, int end) {

        if (start > end) {
            return Integer.MAX_VALUE;
        } else if (start == end) {
            return num[start];
        } else if (start + 1 == end) {
            return Math.min(num[start], num[end]);
        }
        int mid = (start + end) / 2;
        int left = mid - 1;
        int right = mid + 1;
        while (left > start && num[mid] == num[left]) {
            left--;
        }
        while (right < end && num[mid] == num[right]) {
            right++;
        }
        if (num[start] <= num[left]) {
            if (num[start] >= num[end]) {
                return Math.min(num[mid], _findMin1(num, right, end));
            } else {
                return num[start];
            }
        } else {
            return Math.min(num[mid], _findMin1(num, start, left));
        }
    }

    /**
     * 154. Find Minimum in Rotated Sorted Array II
     * Follow up for "Find Minimum in Rotated Sorted Array":
     * What if duplicates are allowed?
     * Would this affect the run-time complexity? How and why?
     * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
     * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
     * Find the minimum element.
     * The array may contain duplicates.
     */
    public static int findMin2(int[] num) {

        return _findMin2(num, 0, num.length - 1);
    }

    public static int _findMin2(int[] num, int start, int end) {

        if (start > end) {
            return Integer.MAX_VALUE;
        } else if (start == end) {
            return num[start];
        } else if (start + 1 == end) {
            return Math.min(num[start], num[end]);
        }
        int mid = (start + end) / 2;
        int left = mid - 1;
        int right = mid + 1;
        while (left > start && num[mid] == num[left]) {
            left--;
        }
        while (right < end && num[mid] == num[right]) {
            right++;
        }
        if (num[start] <= num[left]) {
            if (num[start] >= num[end]) {
                return Math.min(num[mid], _findMin2(num, right, end));
            } else {
                return num[start];
            }
        } else {
            return Math.min(num[mid], _findMin2(num, start, left));
        }
    }

    /**
     * 155. Min Stack
     * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
     * push(x) -- Push element x onto stack.
     * pop() -- Removes the element on top of the stack.
     * top() -- Get the top element.
     * getMin() -- Retrieve the minimum element in the stack.
     */
    public static class MinStack {

        Stack<Integer> mainStack = new Stack<Integer>();
        Stack<Integer> minStack  = new Stack<Integer>();

        public void push(int x) {
            mainStack.push(x);
            if (minStack.isEmpty() || minStack.peek() >= x) {
                minStack.push(x);
            }
        }

        public void pop() {
            if (!mainStack.isEmpty()) {
                int val = mainStack.pop();
                if (!minStack.isEmpty() && minStack.peek() == val) {
                    minStack.pop();
                }
            }
        }

        public int top() {

            return mainStack.isEmpty() ? 0 : mainStack.peek();
        }

        public int getMin() {

            return minStack.isEmpty() ? 0 : minStack.peek();
        }
    }

    /**
     * 160. Intersection of Two Linked Lists
     * Write a program to find the node at which the intersection of two singly linked lists begins.
     * For example, the following two linked lists:
     * A:          a1 → a2
     * ↘
     * c1 → c2 → c3
     * ↗
     * B:     b1 → b2 → b3
     * begin to intersect at node c1.
     * Notes:
     * If the two linked lists have no intersection at all, return null.
     * The linked lists must retain their original structure after the function returns.
     * You may assume there are no cycles anywhere in the entire linked structure.
     * Your code should preferably run in O(n) time and use only O(1) memory.
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if (headA == null || headB == null) {
            return null;
        }
        int sizeA = 0;
        int sizeB = 0;
        ListNode tempA = headA;
        ListNode tempB = headB;
        while (tempA != null) {
            sizeA++;
            tempA = tempA.next;
        }
        while (tempB != null) {
            sizeB++;
            tempB = tempB.next;
        }
        tempA = headA;
        tempB = headB;
        if (sizeA >= sizeB) {
            int count = 0;
            while (count < sizeA - sizeB) {
                count++;
                tempA = tempA.next;
            }
        } else {
            int count = 0;
            while (count < sizeB - sizeA) {
                count++;
                tempB = tempB.next;
            }
        }
        while (tempA != tempB && tempA != null && tempB != null) {
            tempA = tempA.next;
            tempB = tempB.next;
        }
        return tempA;
    }

    /**
     * 162. Find Peak Element
     * A peak element is an element that is greater than its neighbors.
     * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
     * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
     * You may imagine that num[-1] = num[n] = -∞.
     * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
     * click to show spoilers.
     * Note:
     * Your solution should be in logarithmic complexity.
     */
    public static int findPeakElement(int[] num) {

        for (int i = 0; i < num.length; i++) {
            int current = num[i];
            if (i == 0) {
                if (num.length == 1) {
                    return i;
                } else {
                    int next = num[i + 1];
                    if (current > next) {
                        return i;
                    }
                }
            } else if (i == num.length - 1) {
                int prev = num[i - 1];
                if (current > prev) {
                    return i;
                }
            } else {
                int prev = num[i - 1];
                int next = num[i + 1];
                if (prev < current && current > next) {
                    return i;
                }
            }
        }
        return 0;
    }

    /**
     * 164. Maximum Gap
     * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
     * Try to solve it in linear time/space.
     * Return 0 if the array contains less than 2 elements.
     * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
     */
    public static int maximumGap(int[] num) {

        if (num.length < 2) {
            return 0;
        }
        int size = num.length;
        int min = num[0];
        int max = num[0];
        for (int i = 1; i < size; i++) {
            min = Math.min(min, num[i]);
            max = Math.max(max, num[i]);
        }
        if (max == min) {
            return 0;
        }
        int sizeBucket = (max - min) / size + 1;
        int numBuckets = (max - min) / sizeBucket + 1;
        int[][] buckets = new int[numBuckets][2];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i][0] = Integer.MAX_VALUE;
            buckets[i][1] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < size; i++) {
            int iBucket = (num[i] - min) / sizeBucket;
            buckets[iBucket][0] = Math.min(buckets[iBucket][0], num[i]);
            buckets[iBucket][1] = Math.max(buckets[iBucket][1], num[i]);
        }
        int maxGap = 0;
        int prev = 0;
        for (int i = 1; i < numBuckets; i++) {
            int[] curBucket = buckets[i];
            int[] preBucket = buckets[prev];
            if (curBucket[0] == Integer.MAX_VALUE || preBucket[0] == Integer.MAX_VALUE) {
                continue;
            }
            maxGap = Math.max(maxGap, curBucket[0] - preBucket[1]);
            prev = i;
        }
        return maxGap;
    }

    /**
     * 165. Compare Version Numbers
     * Compare two version numbers version1 and version2.
     * If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
     * You may assume that the version strings are non-empty and contain only digits and the . character.
     * The . character does not represent a decimal point and is used to separate number sequences.
     * For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision
     * of the second first-level revision.
     * Here is an example of version numbers ordering:
     * 0.1 < 1.1 < 1.2 < 13.37
     */
    public static int compareVersion(String version1, String version2) {

        List<Integer> v1 = decodeVersionNumber(version1);
        List<Integer> v2 = decodeVersionNumber(version2);
        int size = Math.max(v1.size(), v2.size());
        for (int i = 0; i < size; i++) {
            int num1 = i < v1.size() ? v1.get(i) : 0;
            int num2 = i < v2.size() ? v2.get(i) : 0;
            if (num1 > num2) {
                return 1;
            } else if (num1 < num2) {
                return -1;
            }
        }
        return 0;
    }

    public static List<Integer> decodeVersionNumber(String version) {

        List<Integer> list = new ArrayList<Integer>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= version.length(); i++) {
            char c = i == version.length() ? '.' : version.charAt(i);
            if (c == '.') {
                if (builder.length() > 0) {
                    list.add(Integer.parseInt(builder.toString()));
                    builder.delete(0, builder.length());
                } else {
                    // malformed
                }
            } else {
                builder.append(c);
            }
        }
        if (list.isEmpty()) {
            list.add(0);
        }
        return list;
    }


    /**
     * 166. Fraction to Recurring Decimal
     * Given two integers representing the numerator and denominator of a fraction, return the fraction in string
     * format.
     * If the fractional part is repeating, enclose the repeating part in parentheses.
     * For example,
     * Given numerator = 1, denominator = 2, return "0.5".
     * Given numerator = 2, denominator = 1, return "2".
     * Given numerator = 2, denominator = 3, return "0.(6)".
     */
    public static String fractionToDecimal(int numerator, int denominator) {

        if (numerator == 0) {
            return "0";
        } else if (denominator == 0) {
            return "";
        } else if (numerator == denominator) {
            return "1";
        }
        int dotIndex = -1;
        int leftIndex = -1;
        long a = numerator;
        long b = denominator;
        if (b == 1 || b == -1) {
            return Long.toString(a / b);
        }
        boolean isNegative = (a < 0 && b > 0) || (a > 0 && b < 0);
        a = a < 0 ? -a : a;
        b = b < 0 ? -b : b;
        StringBuilder result = new StringBuilder();
        Map<Long, Integer> prevs = new HashMap<Long, Integer>();
        while (a != 0) {
            if (a < b) {
                if (dotIndex == -1) {
                    dotIndex = result.length();
                    result.append(".");
                } else {
                    prevs.put(a, result.length());
                    result.append(0);
                }
            } else {
                long c = a / b;
                prevs.put(a, result.length());
                result.append(c);
                a %= b;
                long temp = a * 10;
                if (prevs.containsKey(temp) && prevs.get(temp) > dotIndex && dotIndex != -1) {
                    leftIndex = prevs.get(temp);
                    if (leftIndex == result.length()) {
                        result.deleteCharAt(result.length() - 1);
                    }
                    break;
                }
                if (dotIndex == -1) {
                    dotIndex = result.length();
                    result.append(".");
                }
            }
            a *= 10;
        }
        if (dotIndex != -1 && leftIndex != -1) {
            result.insert(leftIndex, "(");
            result.append(")");
        }
        if (dotIndex == 0) {
            result.insert(0, "0");
        }
        if (result.charAt(result.length() - 1) == '.') {
            result.deleteCharAt(result.length() - 1);
        }
        return isNegative ? "-" + result.toString() : result.toString();
    }

    /**
     * 168. Excel Sheet Column Title
     * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
     * For example:
     * 1 -> A
     * 2 -> B
     * 3 -> C
     * ...
     * 26 -> Z
     * 27 -> AA
     * 28 -> AB
     */
    private static String[] CHARS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
                                     "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public static String convertToTitle(int n) {

        if (n < 1) {
            return "";
        }
        if (n >= 1 && n <= 26) {
            return CHARS[n - 1];
        }

        Stack<String> stack = new Stack<String>();
        while (n > 0) {
            int temp = n % 26;
            stack.add(CHARS[(temp == 0 ? CHARS.length : temp) - 1]);
            n = (n - (temp == 0 ? CHARS.length : temp)) / 26;
        }
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }
        return builder.toString();
    }

    /**
     * 169. Majority Element
     * Given an array of size n, find the majority element. The majority element is the element that appears more than
     * ⌊n/2 ⌋ times.
     * You may assume that the array is non-empty and the majority element always exist in the array.
     */
    public static int majorityElement(int[] num) {

        int half = num.length / 2;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int n : num) {
            if (map.containsKey(n)) {
                map.put(n, map.get(n) + 1);
            } else {
                map.put(n, 1);
            }
            if (map.get(n) > half) {
                return n;
            }
        }
        return 0;
    }


    /**
     * 171. Excel Sheet Column Number
     * Related to question Excel Sheet Column Title
     * Given a column title as appear in an Excel sheet, return its corresponding column number.
     * For example:
     * A -> 1
     * B -> 2
     * C -> 3
     * ...
     * Z -> 26
     * AA -> 27
     * AB -> 28
     */
    public static int titleToNumber(String s) {

        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 64;
            total = 26 * total + c;
        }
        return total;
    }


    /**
     * 172. Factorial Trailing Zeroes
     * Given an integer n, return the number of trailing zeroes in n!.
     * Note: Your solution should be in logarithmic time complexity.
     */
    public static int trailingZeroes(int n) {

        int count = 0;
        for (long i = 5; n >= i; i *= 5) {
            count += (n / i);
        }
        return count;
    }

    /**
     * 173. Binary Search Tree Iterator
     * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a
     * BST.
     * Calling next() will return the next smallest number in the BST.
     * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the
     * tree.
     */
    public static class BSTIterator {

        Deque<Integer> inorder = new ArrayDeque<Integer>();

        public BSTIterator(TreeNode root) {

            TreeNode node = root;
            TreeNode prev = null;
            while (node != null) {
                if (node.left == null) {
                    inorder.addLast(node.val);
                    node = node.right;
                } else {
                    prev = node.left;
                    while (prev.right != null && prev.right != node) {
                        prev = prev.right;
                    }
                    if (prev.right == null) {
                        prev.right = node;
                        node = node.left;
                    } else {
                        prev.right = null;
                        inorder.addLast(node.val);
                        node = node.right;
                    }
                }
            }
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {

            return !inorder.isEmpty();
        }

        /**
         * @return the next smallest number
         */
        public int next() {

            return inorder.removeFirst();
        }
    }

    /**
     * Your BSTIterator will be called like this:
     * BSTIterator i = new BSTIterator(root);
     * while (i.hasNext()) v[f()] = i.next();
     */

    /**
     * 174. Dungeon Game
     * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon
     * consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left
     * room and must fight his way through the dungeon to rescue the princess.
     * The knight has an initial health point represented by a positive integer. If at any point his health point drops
     * to 0 or below, he dies immediately.
     * Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these
     * rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive
     * integers).
     * In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in
     * each step.
     * Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
     * For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the
     * optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
     * -2 (7)	 -3 (5)   3(2)
     * -5 (6)	 -10(11)  1(5)
     * 10 (1)	  30(1)  -5(6)
     * Notes:
     * The knight's health has no upper bound.
     * Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where
     * the princess is imprisoned.
     */
    public static int calculateMinimumHP(int[][] dungeon) {

        int m = dungeon.length;
        if (m == 0) {
            return 0;
        }
        int n = dungeon[0].length;
        if (n == 0) {
            return 0;
        }
        int[][] d = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (dungeon[i][j] >= 0) {
                    if (i == m - 1 && j == n - 1) {
                        d[i][j] = 1;
                    } else if (i == m - 1) {
                        d[i][j] = dungeon[i][j] >= d[i][j + 1] ? 1 : (d[i][j + 1] - dungeon[i][j]);
                    } else if (j == n - 1) {
                        d[i][j] = dungeon[i][j] >= d[i + 1][j] ? 1 : (d[i + 1][j] - dungeon[i][j]);
                    } else {
                        int min = Math.min(d[i + 1][j], d[i][j + 1]);
                        d[i][j] = dungeon[i][j] >= min ? 1 : (min - dungeon[i][j]);
                    }
                } else {
                    if (i == m - 1 && j == n - 1) {
                        d[i][j] = 1 - dungeon[i][j];
                    } else if (i == m - 1) {
                        d[i][j] = d[i][j + 1] - dungeon[i][j];
                    } else if (j == n - 1) {
                        d[i][j] = d[i + 1][j] - dungeon[i][j];
                    } else {
                        int min = Math.min(d[i + 1][j], d[i][j + 1]);
                        d[i][j] = min - dungeon[i][j];
                    }
                }
            }
        }
        return d[0][0];
    }

    /**
     * 179. Largest Number
     * Given a list of non negative integers, arrange them such that they form the largest number.
     * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
     * Note: The result may be very large, so you need to return a string instead of an integer.
     */
    public static String largestNumber(int[] num) {

        List<String> list = new ArrayList<String>();
        for (int n : num) {
            list.add(Integer.toString(n));
        }
        List<String> orders = mergeSortStringList(list, 0, list.size() - 1);
        StringBuilder builder = new StringBuilder();
        for (String s : orders) {
            if (s.equals("0") && builder.length() == 0) {
                continue;
            }
            builder.append(s);
        }
        return builder.length() == 0 ? "0" : builder.toString();
    }

    public static List<String> mergeSortStringList(List<String> list, int start, int end) {

        if (start > end) {
            return new ArrayList<String>();
        } else if (start == end) {
            return list.subList(start, start + 1);
        } else {
            int mid = (start + end) / 2;
            return mergeStringList(mergeSortStringList(list, start, mid), mergeSortStringList(list, mid + 1, end));
        }
    }

    public static List<String> mergeStringList(List<String> list1, List<String> list2) {

        if (list1.isEmpty()) {
            return list2;
        } else if (list2.isEmpty()) {
            return list1;
        }
        List<String> result = new ArrayList<String>();
        int index1 = 0;
        int index2 = 0;
        while (index1 < list1.size() && index2 < list2.size()) {
            String str1 = list1.get(index1);
            String str2 = list2.get(index2);
            if (compareStrNumber(str1, str2) >= 0) {
                result.add(str1);
                index1++;
            } else {
                result.add(str2);
                index2++;
            }
        }
        if (index1 < list1.size()) {
            result.addAll(list1.subList(index1, list1.size()));
        } else if (index2 < list2.size()) {
            result.addAll(list2.subList(index2, list2.size()));
        }
        return result;
    }

    public static int compareStrNumber(String num1, String num2) {

        String str1 = num1 + num2;
        String str2 = num2 + num1;
        for (int i = 0; i < str1.length(); i++) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            if (c1 > c2) {
                return 1;
            } else if (c1 < c2) {
                return -1;
            }
        }
        return 0;
    }

    /**
     * 187. Repeated DNA Sequences
     * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When
     * studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
     * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA
     * molecule.
     * For example,
     * Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
     * Return:
     * ["AAAAACCCCC", "CCCCCAAAAA"].
     */
    public static List<String> findRepeatedDnaSequences(String s) {

        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('A', 0);
        map.put('C', 1);
        map.put('G', 2);
        map.put('T', 3);

        int hash = 0;
        Set<String> result = new HashSet<String>();
        Set<Integer> visited = new HashSet<Integer>();
        for (int i = 0; i < s.length(); i++) {
            if (i < 9) {
                hash = (hash << 2) + map.get(s.charAt(i));
            } else {
                hash = (hash << 2) + map.get(s.charAt(i));
                hash &= ((1 << 20) - 1);
                if (visited.contains(hash)) {
                    result.add(s.substring(i - 9, i + 1));
                } else {
                    visited.add(hash);
                }
            }
        }
        return new ArrayList<String>(result);
    }


    /**
     * Additional Questions
     */

    /**
     * 156. Binary Tree Upside Down
     * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares
     * the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes
     * turned into left leaf nodes. Return the new root.
     * For example:
     * Given a binary tree {1,2,3,4,5},
     * 1
     * / \
     * 2   3
     * / \
     * 4   5
     * return the root of the binary tree [4,5,2,#,#,3,1].
     * 4
     * / \
     * 5   2
     * / \
     * 3   1
     */
    public static TreeNode upsideDownBinaryTree(TreeNode root) {

        TreeNode node = root;
        TreeNode parent = null;
        TreeNode parentRight = null;
        while (node != null) {
            TreeNode parentLeft = node.left;
            node.left = parentRight;
            parentRight = node.right;
            node.right = parent;
            parent = node;
            node = parentLeft;
        }
        return parent;

        //        if (root == null || root.left == null) {
        //            return root;
        //        }
        //        Stack<TreeNode> stack = new Stack<TreeNode>();
        //        TreeNode temp = root;
        //        while (temp != null) {
        //            stack.push(temp);
        //            temp = temp.left;
        //        }
        //        TreeNode newRoot = null;
        //        while (!stack.isEmpty()) {
        //            temp = stack.pop();
        //            if (newRoot == null) {
        //                newRoot = temp;
        //            }
        //            TreeNode oriLeft = temp.left;
        //            TreeNode oriRight = temp.right;
        //            if (oriLeft == null) {
        //                continue;
        //            }
        //            temp.left = null;
        //            temp.right = null;
        //            oriLeft.left = oriRight;
        //            oriLeft.right = temp;
        //        }
        //        return newRoot;
    }

    /**
     * 157. Read N Characters Given Read4
     * The API: int read4(char *buf) reads 4 characters at a time from a file.
     * The return value is the actual number of characters read. For example, it returns 3 if there is only 3
     * characters left in the file.
     * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
     * Note:
     * The read function will only be called once for each test case.
     */
    public static int read4(char[] buf) {
        return 0;
    }

    public static int read(char[] buf, int n) {

        char[] buffer = new char[4];
        int total = 0;
        int numLeft = n;
        while (numLeft > 0) {
            int numRead = read4(buffer);
            int copy = Math.min(numRead, numLeft);
            System.arraycopy(buffer, 0, buf, total, copy);
            total += numRead;
            numLeft -= copy;
            if (numRead < 4) {
                break;
            }
        }
        return total;
    }

    /**
     * 158. Read N Characters Given Read4 II
     * The API: int read4(char *buf) reads 4 characters at a time from a file.
     * The return value is the actual number of characters read. For example, it returns 3 if there is only 3
     * characters left in the file.
     * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
     * Note:
     * The read function may be called multiple times.
     */
    public static char[] unused;
    public static int unusedLen = 0;

    public static int read2(char[] buf, int n) {
        char[] buffer = new char[4];
        int total = 0;
        int numLeft = n;
        while (numLeft > 0) {
            if (unusedLen > 0) {
                if (numLeft >= unusedLen) {
                    System.arraycopy(unused, 0, buf, total, unusedLen);
                    total += unusedLen;
                    numLeft -= unusedLen;
                    unusedLen = 0;
                    // clear unused.
                } else {
                    System.arraycopy(unused, 0, buf, total, numLeft);
                    total += numLeft;
                    unusedLen -= numLeft;
                    numLeft = 0;
                    // clear unused (first numLeft size)
                }
            } else {
                int numRead = read4(buffer);
                int copy = numRead;
                if (numRead > numLeft) {
                    copy = numLeft;
                    System.arraycopy(buffer, numRead, unused, unusedLen, numRead - numLeft);
                    unusedLen += (numRead - numLeft);
                }
                System.arraycopy(buffer, 0, buf, total, copy);
                total += numRead;
                numLeft -= copy;
                if (numRead < 4) {
                    break;
                }
            }
        }
        return total;
    }

    /**
     * 159. Longest Substring with At Most Two Distinct Characters
     */
    public static String subString(String s) {

        if (s.length() <= 2) {
            return s;
        }
        int maxI = 0;
        int maxJ = 0;
        int index1 = 0;
        int index2 = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        while (index2 < s.length()) {
            char c = s.charAt(index2);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
                index2++;
            } else {
                if (map.size() == 2) {
                    if (maxJ - maxI < index2 - index1 - 1) {
                        maxI = index1;
                        maxJ = index2 - 1;
                    }
                    while (index1 < index2) {
                        char a = s.charAt(index1);
                        map.put(a, map.get(a) - 1);
                        if (map.get(a) == 0) {
                            map.remove(a);
                            index1++;
                            break;
                        } else {
                            index1++;
                        }
                    }
                    map.put(c, 1);
                    index2++;
                } else {
                    map.put(c, 1);
                    index2++;
                }
            }
        }
        return s.substring(maxI, maxJ + 1);
    }

    /**
     * 161. One Edit Distance
     * Given two strings S and T, determine if they are both one edit distance apart.
     * Hint:
     * 1. If | n – m | is greater than 1, we know immediately both are not one-edit distance apart.
     * 2. It might help if you consider these cases separately, m == n and m ≠ n.
     * 3. Assume that m is always ≤ n, which greatly simplifies the conditional statements. If m > n, we could just
     * simply swap S and T.
     * 4. If m == n, it becomes finding if there is exactly one modified operation. If m ≠ n, you do not have to
     * consider the delete operation. Just consider the insert operation in T.
     */
    public static boolean isOneEditDistance(String s, String t) {

        int sizeS = s.length();
        int sizeT = t.length();
        if (Math.abs(sizeS - sizeT) > 1) {
            return false;
        }
        if (sizeS == sizeT) {
            int diff = 0;
            for (int i = 0; i < sizeS; i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    diff++;
                }
            }
            return diff == 1;
        } else {
            String minStr;
            String maxStr;
            if (sizeS < sizeT) {
                minStr = s;
                maxStr = t;
            } else {
                minStr = t;
                maxStr = s;
            }
            int i = 0;
            for (; i < minStr.length(); i++) {
                char c1 = minStr.charAt(i);
                char c2 = maxStr.charAt(i);
                if (c1 != c2) {
                    break;
                }
            }
            if (i == minStr.length() && i == maxStr.length() - 1) {
                return true;
            }
            int index1 = i;
            int index2 = i + 1;
            for (; index1 < minStr.length() && index2 < maxStr.length(); index1++, index2++) {
                if (minStr.charAt(index1) != maxStr.charAt(index2)) {
                    return false;
                }
            }
            return true;
        }
    }


    /**
     * 162. Missing Ranges
     * Given a sorted integer array where the range of elements are [0, 99] inclusive, return its missing ranges.
     * For example, given [0, 1, 3, 50, 75], return ["2", "4->49", "51->74", "76->99"]
     */
    public static List<String> findMissingRanges(int[] vals, int start, int end) {

        List<String> result = new ArrayList<String>();
        if (vals.length == 0) {
            result.add(gerRangeString(start, end));
            return result;
        }
        boolean started = false;
        int prev = -1;
        for (int i = 0; i < vals.length; i++) {
            int current = vals[i];
            if (current >= start && current <= end) {
                if (!started) {
                    started = true;
                    if (current != start) {
                        result.add(gerRangeString(start, current - 1));
                    }
                } else if (current != prev + 1) {
                    result.add(gerRangeString(prev + 1, current - 1));
                }
            }
            prev = current;
        }
        if (prev < end) {
            result.add(gerRangeString(prev + 1, end));
        }
        return result;
    }

    public static String gerRangeString(int start, int end) {
        if (start == end) {
            return Integer.toString(start);
        }
        return Integer.toString(start) + "->" + Integer.toString(end);
    }

    /**
     * 167. Two Sum II - Input array is sorted
     * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to
     * a specific target number.
     * The function twoSum should return indices of the two numbers such that they add up to the target, where index1
     * must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
     * You may assume that each input would have exactly one solution.
     * Input: numbers={2, 7, 11, 15}, target=9
     * Output: index1=1, index2=2
     */
    public static int[] twoSum2(int[] numbers, int target) {

        int[] result = {-1, -1};
        if (numbers.length == 0) {
            return result;
        }
        int index1 = 0;
        int index2 = numbers.length - 1;
        while (index1 < index2) {
            int sum = numbers[index1] + numbers[index2];
            if (sum > target) {
                index2--;
            } else if (sum < target) {
                index1++;
            } else {
                result[0] = index1 + 1;
                result[1] = index2 + 1;
                break;
            }
        }
        return result;
    }

    /**
     * 170. Two Sum III - Data structure design
     * Design and implement a TwoSum class. It should support the following operations: add and find.
     * add - Add the number to an internal data structure.
     * find - Find if there exists any pair of numbers which sum is equal to the value.
     * For example,
     * add(1); add(3); add(5);
     * find(4) -> true
     * find(7) -> false
     */
    public static class TwoSum {

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        public void add(int number) {

            if (map.containsKey(number)) {
                map.put(number, map.get(number) + 1);
            } else {
                map.put(number, 1);
            }
        }

        public boolean find(int value) {

            for (Integer key : map.keySet()) {
                int target = value - key;
                if (map.containsKey(target)) {
                    if (key == target && map.get(key) > 1) {
                        return true;
                    } else if (key != target) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /**
     * 186. Reverse Words in a String II
     * Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.
     * The input string does not contain leading or trailing spaces and the words are always separated by a single
     * space.
     * For example,
     * Given s = "the sky is blue",
     * return "blue is sky the".
     * Could you do it in-place without allocating extra space?
     */
    public static void reverseWords(char[] s) {

        int index1 = 0;
        int index2 = s.length - 1;
        while (index1 < index2) {
            char c = s[index1];
            s[index1] = s[index2];
            s[index2] = c;
            index1++;
            index2--;
        }
        int prevSpace = -1;
        for (int i = 0; i <= s.length; i++) {
            int start = -1;
            int end = -1;
            if (i == s.length) {
                start = prevSpace + 1;
                end = i - 1;
            } else if (s[i] == ' ') {
                start = prevSpace + 1;
                end = i - 1;
                prevSpace = i;
            }
            if (start != -1 && end != -1) {
                index1 = start;
                index2 = end;
                while (index1 < index2) {
                    char c = s[index1];
                    s[index1] = s[index2];
                    s[index2] = c;
                    index1++;
                    index2--;
                }
            }
        }
    }


    /**
     * Binary Tree Vertical Order
     */
    public static List<List<Integer>> binaryTreeVerticalOrder(TreeNode root) {

        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        int[] min = new int[1];
        int[] max = new int[1];
        findMinMaxVerticalLevel(root, 0, min, max);

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = min[0]; i <= max[0]; i++) {
            result.add(binaryTreeVerticalOrder(root, 0, i));
        }
        return result;
    }

    public static void findMinMaxVerticalLevel(TreeNode node, int currentLevel, int[] min, int[] max) {

        if (node == null) {
            return;
        }
        min[0] = Math.min(min[0], currentLevel);
        max[0] = Math.max(max[0], currentLevel);
        findMinMaxVerticalLevel(node.left, currentLevel - 1, min, max);
        findMinMaxVerticalLevel(node.right, currentLevel + 1, min, max);
    }

    public static List<Integer> binaryTreeVerticalOrder(TreeNode node, int currentLevel, int level) {

        if (node == null) {
            return new ArrayList<Integer>();
        }
        List<Integer> result = new ArrayList<Integer>();
        if (currentLevel == level) {
            result.add(node.val);
        }
        result.addAll(binaryTreeVerticalOrder(node.left, currentLevel - 1, level));
        result.addAll(binaryTreeVerticalOrder(node.right, currentLevel + 1, level));
        return result;
    }

    /**
     * Lowest Common Ancestor of two tree nodes
     */
    public static TreeNode LCATreeNode(TreeNode root, int node1, int node2) {

        if (root == null) {
            return null;
        }
        if (root.val == node1 || root.val == node2) {
            return root;
        }
        TreeNode left = LCATreeNode(root.left, node1, node2);
        TreeNode right = LCATreeNode(root.right, node1, node2);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

    /**
     * Move zero to end of the array
     */
    public static void MoveZeroToEnd(int[] num) {

        if (num.length <= 1) {
            return;
        }
        int j = num.length - 1;
        for (int i = 0; i < j; i++) {
            if (num[i] == 0) {
                int temp = num[i];
                num[i] = num[j];
                num[j] = temp;
                j--;
                i--;
            }
        }
    }

    /**
     * Find intersection of two sorted array
     */
    public static int IntersectionSortedArray(int[] A, int[] B) {

        if (A.length == 0 || B.length == 0) {
            return 0;
        }
        return _IntersectionSortedArray(A, 0, A.length - 1, B, 0, B.length - 1);
    }

    public static int _IntersectionSortedArray(int[] A, int startA, int endA, int[] B, int startB, int endB) {

        if (startA > endA || startB > endB) {
            return 0;
        } else if (startA == endA) {
            return A[startA];
        } else if (startB == endB) {
            return B[startB];
        }
        int midA = (startA + endA) / 2;
        int midB = (startB + endB) / 2;
        if (A[midA] == B[startB]) {
            return A[midA];
        } else if (A[midA] < B[startB]) {
            return _IntersectionSortedArray(A, midA + 1, endA, B, startB, endB);
        } else if (A[midA] >= B[endB]) {
            return _IntersectionSortedArray(A, startA, midA, B, startB, endB);
        } else if (B[midB] == A[startA]) {
            return B[midB];
        } else if (B[midB] < A[startA]) {
            return _IntersectionSortedArray(A, startA, endA, B, midB + 1, endB);
        } else if (B[midB] >= A[endA]) {
            return _IntersectionSortedArray(A, startA, endA, B, startB, midB);
        } else {
            Set<Integer> setA = new HashSet<Integer>();
            for (int i = startA; i <= endA; i++) {
                setA.add(A[i]);
            }
            for (int i = startB; i <= endB; i++) {
                if (setA.contains(B[i])) {
                    return B[i];
                }
            }
            return 0;
        }
    }

    public static List<List<Integer>> PrintAllPath(TreeNode root) {

        List<List<Integer>> paths = new ArrayList<List<Integer>>();
        _PrintAllPath(root, new ArrayList<Integer>(), paths);
        return paths;
    }

    public static void _PrintAllPath(TreeNode root, List<Integer> path, List<List<Integer>> paths) {

        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.left == null && root.right == null) {
            paths.add(new ArrayList<Integer>(path));
        }
        if (root.left != null) {
            _PrintAllPath(root.left, path, paths);
        }
        if (root.right != null) {
            _PrintAllPath(root.right, path, paths);
        }
        path.remove(path.size() - 1);
    }

    public static int findKthSmallestElement(int[] A, int k) {

        if (k <= 0 || k > A.length) {
            return -1;
        }
        return _findKthSmallestElement(A, 0, A.length - 1, k);
    }

    public static int _findKthSmallestElement(int[] A, int start, int end, int k) {

        if (start > end) {
            return -1;
        } else if (start == end) {
            return A[start];
        }
        int mid = (start + end) / 2;
        int pivot = A[mid];
        swap(A, mid, end);
        int i = start;
        for (int j = start; j < end; j++) {
            if (A[j] <= pivot) {
                swap(A, i, j);
                i++;
            }
        }
        swap(A, i, end);
        int frontSize = i - start + 1;
        if (frontSize == k) {
            return pivot;
        } else if (frontSize > k) {
            return _findKthSmallestElement(A, start, i - 1, k);
        } else {
            return _findKthSmallestElement(A, i + 1, end, k - frontSize);
        }
    }

    public static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    /**
     * get permutation, 61
     * pow
     * sqrt
     * sortColor
     * largestRectangleArea
     * Populate next right node in each node 116.
     * Word Break
     * HashTable
     */


    /**
     * Implement concurrent read/write buffer
     * Function to find the square root of a number
     * Pretty print JSON object: JSON.stringify(obj, undefined, 2); // indentation level = 2
     * Square root
     * Simple regex matcher
     * Jump II
     * Partition
     * MapReduce
     * Decode ways
     * Find minimum in rotated sorted array
     * Reverse list
     * Print level order tree
     * Print out all char combinations of phone number
     * Have multiple points, find the k-th closest point to (0,0)
     * Trie
     * Sort colors
     * How would you design home feed?
     * System, design, disjoint set problem
     * conflict resolution (conflict with colleagues)
     * a project on resume
     * How will you design the social graph with class, interfaces, etc
     */


    public static int conscutiveInt(int[][] board, int row, int col) {

        int m = board.length;
        if (m == 0) {
            return 0;
        }
        int n = board[0].length;
        if (n == 0 || row < 0 || row >= m || col < 0 || col >= n) {
            return 0;
        }
        return _conscutiveInt(board, m, n, new boolean[m][n], new int[m][n], row, col);
    }

    public static int _conscutiveInt(int[][] board, int m, int n, boolean[][] visited, int[][] d, int row, int col) {

        if (row < 0 || row >= m || col < 0 || col >= n) {
            return 0;
        }
        if (visited[row][col]) {
            return d[row][col];
        }
        d[row][col] = 1;
        int current = board[row][col];
        boolean up = row > 0 && current == board[row - 1][col] - 1;
        boolean down = row < m - 1 && current == board[row + 1][col] - 1;
        boolean left = col > 0 && current == board[row][col - 1] - 1;
        boolean right = col < n - 1 && current == board[row][col + 1] - 1;
        if (up && !visited[row - 1][col]) {
            d[row - 1][col] = _conscutiveInt(board, m, n, visited, d, row - 1, col);
            d[row][col] = Math.max(d[row][col], d[row - 1][col] + 1);
        }
        if (down && !visited[row + 1][col]) {
            d[row + 1][col] = _conscutiveInt(board, m, n, visited, d, row + 1, col);
            d[row][col] = Math.max(d[row][col], d[row + 1][col] + 1);
        }
        if (left && !visited[row][col - 1]) {
            d[row][col - 1] = _conscutiveInt(board, m, n, visited, d, row, col - 1);
            d[row][col] = Math.max(d[row][col], d[row][col - 1] + 1);
        }
        if (right && !visited[row][col + 1]) {
            d[row][col + 1] = _conscutiveInt(board, m, n, visited, d, row, col + 1);
            d[row][col] = Math.max(d[row][col], d[row][col + 1] + 1);
        }
        visited[row][col] = true;
        return d[row][col];
    }


    public static class InOrderTreeNode {

        int val;
        InOrderTreeNode left;
        InOrderTreeNode right;
        InOrderTreeNode prev;
        InOrderTreeNode next;
        public InOrderTreeNode(int v) {
            this.val = v;
        }
    }

    public static class InOrderTreeNodeStruct {

        InOrderTreeNode self;
        InOrderTreeNode first;
        InOrderTreeNode last;

        public InOrderTreeNodeStruct(InOrderTreeNode self, InOrderTreeNode first, InOrderTreeNode last) {
            this.self = self;
            this.first = first;
            this.last = last;
        }
    }

    public static InOrderTreeNode cloneTreeInOrder(TreeNode root) {

        if (root == null) {
            return null;
        }
        return _cloneTreeInOrder(root).self;
    }

    public static InOrderTreeNodeStruct _cloneTreeInOrder(TreeNode node) {

        if (node == null) {
            return new InOrderTreeNodeStruct(null, null, null);
        }
        InOrderTreeNode newNode = new InOrderTreeNode(node.val);
        if (node.left == null && node.right == null) {
            return new InOrderTreeNodeStruct(newNode, newNode, newNode);
        }
        InOrderTreeNodeStruct left = _cloneTreeInOrder(node.left);
        InOrderTreeNodeStruct right = _cloneTreeInOrder(node.right);
        newNode.left = left.self;
        newNode.right = right.self;
        newNode.prev = left.last;
        newNode.next = right.first;
        if (left.last != null) {
            left.last.next = newNode;
        }
        if (right.first != null) {
            right.first.prev = newNode;
        }
        return new InOrderTreeNodeStruct(newNode, left.first, right.last);
    }

    public static void main(String[] args) {

        //PRINT(getJumpPath(new int[]{5, 3, 2, 1, 4}));


        /**
         *     1
         *    / \
         *   2   3
         *  / \
         * 4   5
         * return the root of the binary tree [4,5,2,#,#,3,1].
         * 4
         * / \
         * 5   2
         *    / \
         *   3   1
         */

        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        InOrderTreeNode node = cloneTreeInOrder(n1);
        PRINT(node.val);

        //        PRINT(PrintAllPath(n1));
        //
        //        TreeNode newRoot = upsideDownBinaryTree(n1);
        //        PRINT(constSpaceInorderTraversal(newRoot));

        //        Set<String> dict = new HashSet<String>();
        //        dict.add("hot");
        //        dict.add("dot");
        //        dict.add("dog");
        //        dict.add("lot");
        //        dict.add("log");
        //        PRINT(findLadders("hit", "cog", dict));
    }

    public static ListNode createListNode(int[] num) {
        ListNode head = null;
        ListNode temp = null;
        for (int val : num) {
            if (head == null) {
                head = new ListNode(val);
                temp = head;
            } else {
                temp.next = new ListNode(val);
                temp = temp.next;
            }
        }
        return head;
    }

    public static void TITLE(String title) {
        int size = title.length();
        System.out.println("\n" + title);
        for (int i = 0; i < size; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    public static void PRINT(Object obj) {
        if (obj == null) {
            System.out.println("NULL");
        } else {
            System.out.println(obj.toString());
        }
    }

    public static void PRINT(TreeNode node) {
        if (node == null) {
            System.out.println("NULL");
        } else {
            List<String> list = new ArrayList<String>();
            TreeNode temp = node;
            List<TreeNode> queue = new ArrayList<TreeNode>();
            queue.add(temp);
            while (!queue.isEmpty()) {
                temp = queue.remove(0);
                if (temp != null) {
                    list.add(Integer.toString(temp.val));
                    queue.add(temp.left);
                    queue.add(temp.right);
                } else {
                    list.add("#");
                }
            }
            System.out.println(list.toString());
        }
    }

    public static void PRINT(ListNode node) {
        if (node == null) {
            System.out.println("NULL");
        } else {
            ListNode temp = node;
            while (temp != null) {
                System.out.print(temp.val + " -> ");
                temp = temp.next;
            }
            System.out.print("NULL\n");
        }
    }

    public static String arrayToString(char[] array) {

        StringBuilder builder = new StringBuilder();
        for (char c : array) {
            builder.append(c);
        }
        return builder.toString();
    }
}
