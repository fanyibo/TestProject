package com.fanyibo.util.round2;

import com.fanyibo.tree.TreeNode;
import com.fanyibo.util.ListNode;

import java.util.*;

public class Round2 {

    /**
     * 1. Two Sum
     * Given an array of integers, find two numbers such that they add up to a specific target number.
     * The function twoSum should return indices of the two numbers such that they add up to the target, where index1
     * must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
     * You may assume that each input would have exactly one solution.
     * Input: numbers={2, 7, 11, 15}, target=9
     * Output: index1=1, index2=2
     */
    public static int[] twoSum(int[] numbers, int target) {

        HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < numbers.length; i++) {
            int index = i + 1;
            if (map.containsKey(numbers[i])) {
                map.get(numbers[i]).add(index);
            } else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(index);
                map.put(numbers[i], list);
            }
        }
        int[] result = new int[2];
        for (int key : numbers) {
            int temp = target - key;
            if (map.containsKey(temp)) {
                if (key == temp && map.get(key).size() == 1) {
                    continue;
                }
                int index1 = map.get(key).get(0);
                int index2 = key == temp ? map.get(temp).get(1) : map.get(temp).get(0);
                result[0] = Math.min(index1, index2);
                result[1] = Math.max(index1, index2);
                break;
            } else {
                map.remove(key);
            }
        }
        return result;
    }


    /**
     * 2.Add Two Numbers
     * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and
     * each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = null;
        ListNode temp = null;
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        boolean mark = false;
        while (temp1 != null && temp2 != null) {
            int val1 = temp1.val;
            int val2 = temp2.val;

            int val = val1 + val2 + (mark ? 1 : 0);
            if (val >= 10) {
                mark = true;
                val -= 10;
            } else {
                mark = false;
            }
            if (head == null) {
                head = new ListNode(val);
                temp = head;
            } else {
                ListNode node = new ListNode(val);
                temp.next = node;
                temp = temp.next;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        if (temp1 != null && temp2 == null) {
            while (temp1 != null) {
                int val = temp1.val + (mark ? 1 : 0);
                if (val >= 10) {
                    mark = true;
                    val -= 10;
                } else {
                    mark = false;
                }
                temp.next = new ListNode(val);
                temp = temp.next;
                temp1 = temp1.next;
            }
        } else if (temp1 == null && temp2 != null) {
            while (temp2 != null) {
                int val = temp2.val + (mark ? 1 : 0);
                if (val >= 10) {
                    mark = true;
                    val -= 10;
                } else {
                    mark = false;
                }
                temp.next = new ListNode(val);
                temp = temp.next;
                temp2 = temp2.next;
            }
        }
        if (mark) {
            temp.next = new ListNode(1);
        }
        return head;
    }

    /**
     * 3. Longest Substring Without Repeating Characters
     * Given a string, find the length of the longest substring without repeating characters. For example, the longest
     * substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest
     * substring is "b", with the length of 1.
     */
    public static int lengthOfLongestSubstring(String s) {

        int size = s.length();
        if (size <= 1) {
            return size;
        }
        int[] chars = new int[256];
        int index1 = 0;
        int index2 = 0;
        int max = 0;
        while (index1 < size && index2 < size && index1 <= index2) {
            char c = s.charAt(index2);
            if (chars[c] != 0) {
                max = Math.max(max, index2 - index1);
                for (int i = index1; i < chars[c] - 1; i++) {
                    chars[s.charAt(i)] = 0;
                }
                index1 = chars[c];
                chars[c] = index2 + 1;
                index2++;
            } else {
                chars[c] = index2 + 1;
                index2++;
                if (index2 == size) {
                    max = Math.max(max, index2 - index1);
                    break;
                }
            }
        }
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
        int size = sizeA + sizeB;
        if (size % 2 == 0) {
            int mid1 = size / 2 - 1;
            int mid2 = mid1 + 1;
            double elem1 = findKth(A, 0, sizeA - 1, B, 0, sizeB - 1, mid1);
            double elem2 = findKth(A, 0, sizeA - 1, B, 0, sizeB - 1, mid2);
            return (elem1 + elem2) / 2.0;
        } else {
            int mid = (size - 1) / 2;
            return findKth(A, 0, sizeA - 1, B, 0, sizeB - 1, mid);
        }
    }

    public static double findKth(int[] A, int aLeft, int aRight, int[] B, int bLeft, int bRight, int k) {

        int sizeA = aRight - aLeft + 1;
        int sizeB = bRight - bLeft + 1;
        if (sizeA <= 0) {
            return B[bLeft + k];
        } else if (sizeB <= 0) {
            return A[aLeft + k];
        } else if (sizeA == 1 || sizeB == 1) {
            int[] temp = new int[sizeA + sizeB];
            int i = 0;
            int index1 = aLeft;
            int index2 = bLeft;
            while (index1 <= aRight && index2 <= bRight) {
                if (A[index1] <= B[index2]) {
                    temp[i++] = A[index1++];
                } else {
                    temp[i++] = B[index2++];
                }
            }
            if (index1 > aRight && index2 <= bRight) {
                while (index2 <= bRight) {
                    temp[i++] = B[index2++];
                }
            } else if (index1 <= aRight && index2 > bRight) {
                while (index1 <= aRight) {
                    temp[i++] = A[index1++];
                }
            }
            return temp[k];
        } else if (k <= 0) {
            return Math.min(A[aLeft], B[bLeft]);
        }

        int midA = (aLeft + aRight) / 2;
        int midB = (bLeft + bRight) / 2;

        if (A[midA] >= B[midB]) {
            if (k <= midA + midB - aLeft - bLeft + 1) {
                return findKth(A, aLeft, midA, B, bLeft, bRight, k);
            } else {
                return findKth(A, aLeft, aRight, B, midB + 1, bRight, k - (midB - bLeft + 1));
            }
        } else {
            if (k <= midA + midB - aLeft - bLeft + 1) {
                return findKth(A, aLeft, aRight, B, bLeft, midB, k);
            } else {
                return findKth(A, midA + 1, aRight, B, bLeft, bRight, k - (midA - aLeft + 1));
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
        int start = 0;
        int end = 0;
        boolean[][] d = new boolean[size][size];
        for (int i = size - 1; i >= 0; i--) {
            for (int j = i; j < size; j++) {
                if (i == j) {
                    d[i][j] = true;
                } else if (i + 1 == j) {
                    d[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    d[i][j] = d[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                }
                if (d[i][j]) {
                    if (end - start < j - i) {
                        end = j;
                        start = i;
                    }
                }
            }
        }
        return s.substring(start, end + 1);
    }

    /**
     * 6. ZigZag Conversion
     * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to
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

        int size = s.length();
        if (size == 0 || nRows < 2) {
            return s;
        }
        int block = size / (2 * nRows - 2);
        int left = size - block * (2 * nRows - 2);
        int cols = block * (nRows - 1) + 1 + (left > nRows ? left - nRows : 0);

        int rowIndex = 0;
        int colIndex = 0;
        char[][] chars = new char[nRows][cols];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (rowIndex == nRows) {
                rowIndex = nRows - 2;
                colIndex++;
            }
            if (colIndex % (nRows - 1) == 0) {
                chars[rowIndex++][colIndex] = c;
            } else {
                chars[rowIndex--][colIndex++] = c;
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < cols; j++) {
                if (chars[i][j] != '\0') {
                    builder.append(chars[i][j]);
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

        long target = x;
        boolean isNegative = target < 0;
        if (isNegative) {
            target = -target;
        }
        long result = 0;
        while (target != 0) {
            long digit = target % 10;
            target /= 10;
            result = 10 * result + digit;
        }
        if (isNegative) {
            result = -result;
        }
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) result;
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

        str = str.trim();
        int size = str.length();
        if (size == 0) {
            return 0;
        }
        boolean isNegative = str.charAt(0) == '-';
        int result = 0;
        for (int i = 0; i < size; i++) {
            char c = str.charAt(i);
            if (c != '+' && c != '-') {
                if (c >= '0' && c <= '9') {
                    int _c = c - 48;
                    if (isNegative) {
                        if (result < (Integer.MIN_VALUE + _c) / 10) {
                            return Integer.MIN_VALUE;
                        } else {
                            result = result * 10 - _c;
                        }
                    } else {
                        if (result > (Integer.MAX_VALUE - _c) / 10) {
                            return Integer.MAX_VALUE;
                        } else {
                            result = result * 10 + _c;
                        }
                    }
                } else {
                    return result;
                }
            } else {
                if (i > 0) {
                    return result;
                }
            }
        }
        return result;
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

        long number = x;
        if (number < 0) {
            return false;
        }
        if (number < 10) {
            return true;
        }
        int length = 0;
        long temp = number;
        while (temp != 0) {
            length++;
            temp /= 10;
        }
        long div = (long) Math.pow(10, length - 1);
        while (number != 0) {
            long lastDigit = number % 10;
            long firstDigit = number / div;
            if (firstDigit != lastDigit) {
                return false;
            }
            number = (number % div) / 10;
            div /= 100;
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

        int sizeP = p.length();
        int sizeS = s.length();
        if (sizeP == 0) {
            return sizeS == 0;
        }
        if (sizeP == 1 || p.charAt(1) != '*') {
            if (sizeS == 0 || (s.charAt(0) != p.charAt(0) && p.charAt(0) != '.')) {
                return false;
            }
            return isMatch(s.substring(1), p.substring(1));
        } else {
            int len = s.length();
            int i = -1;
            while (i < len && (i < 0 || p.charAt(0) == '.' || p.charAt(0) == s.charAt(i))) {
                if (isMatch(s.substring(i + 1), p.substring(2))) {
                    return true;
                }
                i++;
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
     * find max: (j-i)*min([i],[j])
     */
    public static int maxArea(int[] height) {

        if (height.length < 2) {
            return 0;
        }
        int max = 0;
        int index1 = 0;
        int index2 = height.length - 1;
        while (index1 < index2) {
            int area = (index2 - index1) * Math.min(height[index1], height[index2]);
            max = Math.max(max, area);
            if (height[index1] <= height[index2]) {
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

        int div = 1;
        int temp = num;
        while (temp != 0) {
            div *= 10;
            temp /= 10;
        }
        div /= 10;

        StringBuilder builder = new StringBuilder();
        while (div > 0) {
            int number = num - num % div;
            int count = number / div;
            if (count == 9) {
                builder.append(map.get(div)).append(map.get(div * 10));
                count = 0;
            } else if (count == 4) {
                builder.append(map.get(div)).append(map.get(div * 5));
                count = 0;
            } else if (count >= 5) {
                builder.append(map.get(div * 5));
                count -= 5;
            }
            String str = map.get(div);
            for (int i = 0; i < count; i++) {
                builder.append(str);
            }
            div /= 10;
            num -= number;
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

        int i = 0;
        int result = 0;
        for (; i < s.length() - 1; i++) {
            int i1 = map.get(s.charAt(i));
            int i2 = map.get(s.charAt(i + 1));
            if (i1 < i2) {
                result += (i2 - i1);
                i++;
            } else {
                result += i1;
            }
        }
        if (i == s.length() - 1) {
            result += map.get(s.charAt(i));
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
        } else if (strs.length == 1) {
            return strs[0];
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= Integer.MAX_VALUE; i++) {
            if (i >= strs[0].length()) {
                break;
            }
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                    return builder.toString();
                }
            }
            builder.append(c);
        }
        return builder.toString();
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

        Arrays.sort(num);
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        for (int i = 0; i < num.length; i++) {
            int head = num[i];
            int target = -head;
            int index1 = i + 1;
            int index2 = num.length - 1;
            while (index1 < index2) {
                int second = num[index1];
                int third = num[index2];
                if (second + third == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(head);
                    list.add(second);
                    list.add(third);
                    set.add(list);
                    index1++;
                    index2--;
                } else if (second + third < target) {
                    index1++;
                } else {
                    index2--;
                }
            }
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.addAll(set);
        return result;
    }

    /**
     * 16. 3Sum Closest
     * Given an array S of n integers, find three integers in S such that the sum is closest to a given number,
     * target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
     * For example, given array S = {-1 2 1 -4}, and target = 1.
     * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
     */
    public static int threeSumClosest(int[] num, int target) {

        if (num == null || num.length < 3) {
            return 0;
        }
        Arrays.sort(num);
        int closestSum = num[0] + num[1] + num[2];
        for (int i = 0; i < num.length; i++) {
            int head = num[i];
            int sum = target - head;
            int index1 = i + 1;
            int index2 = num.length - 1;
            while (index1 < index2) {
                int second = num[index1];
                int third = num[index2];

                int lastTwo = second + third;
                if (Math.abs(head + lastTwo - target) < Math.abs(closestSum - target)) {
                    closestSum = head + lastTwo;
                }
                if (lastTwo == sum) {
                    return target;
                } else if (lastTwo < sum) {
                    index1++;
                } else {
                    index2--;
                }
            }
        }
        return closestSum;
    }

    /**
     * 17. Letter Combinations of a Phone Number
     * Given a digit string, return all possible letter combinations that the number could represent.
     * A mapping of digit to letters (just like on the telephone buttons) is given below.
     * Input:Digit string "23"
     * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     */
    public static List<String> letterCombinations(String digits) {

        HashMap<Integer, String> map = new HashMap<Integer, String>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");

        List<String> result = new ArrayList<String>();
        for (int i = 0; i < digits.length(); i++) {
            String str = map.get(digits.charAt(i) - 48);
            if (str == null) {
                result.clear();
                break;
            } else if (result.isEmpty()) {
                for (int j = 0; j < str.length(); j++) {
                    result.add(Character.toString(str.charAt(j)));
                }
            } else {
                List<String> temp = new ArrayList<String>();
                for (int j = 0; j < str.length(); j++) {
                    for (String s : result) {
                        temp.add(s + Character.toString(str.charAt(j)));
                    }
                }
                result = temp;
            }
        }
        if (result.isEmpty()) {
            result.add("");
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
        if (num == null || num.length < 4) {
            return new ArrayList<List<Integer>>();
        }
        Arrays.sort(num);
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        for (int i = 0; i < num.length; i++) {
            int first = num[i];
            for (int j = i + 1; j < num.length; j++) {
                int second = num[j];
                int sum = target - first - second;
                int index1 = j + 1;
                int index2 = num.length - 1;
                while (index1 < index2) {
                    int third = num[index1];
                    int forth = num[index2];
                    int lastTwo = third + forth;
                    if (lastTwo == sum) {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(first);
                        list.add(second);
                        list.add(third);
                        list.add(forth);
                        set.add(list);
                        index1++;
                        index2--;
                    } else if (lastTwo < sum) {
                        index1++;
                    } else {
                        index2--;
                    }
                }
            }
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.addAll(set);
        return result;
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

        ListNode temp = head;
        ListNode nFather = null;
        int count = 0;
        while (temp != null) {
            temp = temp.next;
            if (nFather != null) {
                nFather = nFather.next;
            }
            if (count == n) {
                nFather = head;
            }
            count++;
        }
        if (nFather != null) {
            nFather.next = nFather.next.next;
        } else if (count == n) {
            return head.next;
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

        HashMap<Character, Character> map = new HashMap<Character, Character>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                if (stack.peek() == map.get(c)) {
                    stack.pop();
                } else {
                    stack.push(c);
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
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = null;
        ListNode temp = null;
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        while (temp1 != null && temp2 != null) {
            ListNode small = null;
            if (temp1.val <= temp2.val) {
                small = temp1;
                temp1 = temp1.next;
            } else {
                small = temp2;
                temp2 = temp2.next;
            }
            if (head == null) {
                head = small;
                temp = head;
            } else {
                temp.next = small;
                temp = temp.next;
            }
        }
        if (temp1 != null && temp2 == null) {
            temp.next = temp1;
        } else if (temp1 == null && temp2 != null) {
            temp.next = temp2;
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

        Set<String> set = new HashSet<String>();
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                set.add("()");
            } else {
                Set<String> temp = new HashSet<String>();
                for (String str : set) {
                    for (int j = 0; j < str.length(); j++) {
                        temp.add(str.substring(0, j) + "()" + str.substring(j));
                    }
                }
                set = temp;
            }
        }
        return new ArrayList<String>(set);
    }

    /**
     * 23. Merge k Sorted Lists
     * Total Accepted: 29062 Total Submissions: 137998
     * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
     */
    public static ListNode mergeKLists(List<ListNode> lists) {

        int size = lists.size();
        if (size == 0) {
            return null;
        } else if (size == 1) {
            return lists.get(0);
        } else {
            return _mergeKLists(lists, 0, size - 1);
        }
    }

    public static ListNode _mergeKLists(List<ListNode> lists, int start, int end) {

        int size = end - start + 1;
        if (size == 0) {
            return null;
        } else if (size == 1) {
            return lists.get(start);
        } else if (size == 2) {
            return mergeTwoLists(lists.get(start), lists.get(end));
        } else {
            int mid = (start + end) / 2;
            return mergeTwoLists(_mergeKLists(lists, start, mid), _mergeKLists(lists, mid + 1, end));
        }
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

        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = null;
        ListNode parent = null;
        ListNode left = head;
        ListNode right = left.next;
        while (right != null) {
            if (newHead == null) {
                newHead = right;
            }
            left.next = right.next;
            right.next = left;
            if (parent != null) {
                parent.next = right;
            }
            parent = left;
            left = parent.next;
            if (left != null) {
                right = left.next;
            } else {
                break;
            }
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
        int count = 0;
        while (temp != null) {
            if (count != 0 && count % k == 0) {
                if (parent == null) {
                    newHead = _reverseKGroup(head, k);
                    head.next = temp;
                    parent = head;
                } else {
                    ListNode tail = parent.next;
                    parent.next = _reverseKGroup(parent.next, k);
                    parent = tail;
                    parent.next = temp;
                }
            }
            count++;
            temp = temp.next;
        }
        if (count % k == 0) {
            if (parent == null) {
                newHead = _reverseKGroup(head, k);
                head.next = null;
            } else {
                ListNode tail = parent.next;
                parent.next = _reverseKGroup(parent.next, k);
                tail.next = null;
            }
        } else if (count < k) {
            return head;
        }
        return newHead;
    }

    public static ListNode _reverseKGroup(ListNode head, int k) {
        ListNode left = head;
        ListNode right = left.next;
        ListNode rightChild = right.next;
        int count = 0;
        while (count <= k - 2) {
            right.next = left;
            left = right;
            right = rightChild;
            if (right != null) {
                rightChild = right.next;
            } else {
                break;
            }
            count++;
        }
        return left;
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

        if (A.length < 2) {
            return A.length;
        }
        int index1 = 0;
        int index2 = 1;
        int size = A.length;
        while (index1 < size && index2 < A.length) {
            while (index2 < A.length && A[index1] == A[index2]) {
                size--;
                index2++;
            }
            if (index2 - index1 > 1 && index2 < A.length) {
                int temp = A[index1 + 1];
                A[index1 + 1] = A[index2];
                A[index2] = temp;
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

        if (A.length < 2) {
            return A.length == 0 ? 0 : (A[0] == elem ? 0 : 1);
        }
        int index1 = 0;
        int index2 = A.length - 1;
        while (index1 < index2) {
            if (A[index2] == elem) {
                index2--;
                continue;
            }
            if (A[index1] == elem) {
                A[index1] = A[index2];
                A[index2] = elem;
                index2--;
            }
            index1++;
        }
        if (index1 < A.length && A[index1] != elem) {
            index1++;
        }
        return index1;
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
        if (sizeA < sizeB) {
            return -1;
        } else if (sizeA == sizeB) {
            return haystack.equals(needle) ? 0 : -1;
        } else if (sizeB == 0) {
            return 0;
        }
        int index = 0;
        for (int i = 0; i <= sizeA - sizeB; i++) {
            if (haystack.charAt(i) == needle.charAt(index)) {
                int start = i;
                for (; index < sizeB; index++) {
                    if (haystack.charAt(i++) != needle.charAt(index)) {
                        index = sizeB + 1;
                        break;
                    }
                }
                if (index == sizeB) {
                    return start;
                } else {
                    i = start;
                    index = 0;
                }
            } else {
                index = 0;
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
        }
        long d1 = dividend;
        long d2 = divisor;
        boolean isNegative = false;
        if (d1 < 0) {
            d1 = -d1;
            isNegative = true;
        }
        if (d2 < 0) {
            d2 = -d2;
            isNegative = !isNegative;
        }
        if (d1 < d2) {
            return 0;
        } else if (d1 == d2) {
            return isNegative ? -1 : 1;
        }
        long result = 0;
        while (d1 >= d2) {
            long temp = d2;
            int i = 1;
            while (d1 >= temp) {
                d1 -= temp;
                result += i;
                temp <<= 1;
                i <<= 1;
            }
        }
        result = isNegative ? -result : result;
        if ((isNegative && result < Integer.MIN_VALUE) || (!isNegative && result > Integer.MAX_VALUE)) {
            return Integer.MAX_VALUE;
        }
        return (int) result;
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

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (String aL : L) {
            if (map.containsKey(aL)) {
                map.put(aL, map.get(aL) + 1);
            } else {
                map.put(aL, 1);
            }
        }
        int len = L[0].length();
        List<Integer> result = new ArrayList<Integer>();
        HashMap<String, Integer> temp = new HashMap<String, Integer>();
        int done = map.size();
        int left = 0;
        int right = len;
        for (; left < S.length() && right <= S.length(); ) {
            String str = S.substring(right - len, right);
            if (map.containsKey(str)) {
                if (temp.containsKey(str)) {
                    temp.put(str, temp.get(str) + 1);
                } else {
                    temp.put(str, 1);
                }
                if (temp.get(str).equals(map.get(str))) {
                    done--;
                    if (done == 0) {
                        result.add(left);
                        left++;
                        right = left + len;
                        temp.clear();
                        done = map.size();
                    } else {
                        right += len;
                    }
                } else if (temp.get(str) > map.get(str)) {
                    left++;
                    right = left + len;
                    temp.clear();
                    done = map.size();
                } else {
                    right += len;
                }
            } else {
                left++;
                right = left + len;
                temp.clear();
                done = map.size();
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
     * 1,2,3,4 -> 1,2,4,3
     * 1,2,1,2,4
     * 4,2,2,1,1
     */
    public static void nextPermutation(int[] num) {

        if (num.length < 2) {
            return;
        }
        int first = 0;
        int i = num.length - 1;
        for (; i > 0; i--) {
            if (num[i] > num[i - 1]) {
                first = i - 1;
                break;
            }
        }
        if (i == 0 && num[1] <= num[0]) {
            Arrays.sort(num);
            return;
        }
        i = first + 1;
        int last = i;
        for (; i < num.length; i++) {
            if (num[i] <= num[last] && num[i] > num[first]) {
                last = i;
            }
        }
        int temp = num[first];
        num[first] = num[last];
        num[last] = temp;
        Arrays.sort(num, first + 1, num.length);
    }

    /**
     * 32. Longest Valid Parentheses
     * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed)
     * parentheses substring.
     * For "(()", the longest valid parentheses substring is "()", which has length = 2.
     * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
     */
    public static int longestValidParentheses(String s) {

        int max = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.isEmpty() || s.charAt(stack.peek()) == ')' || c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    max = Math.max(max, i + 1);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
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
        } else if (A.length == 1) {
            return A[0] == target ? 0 : -1;
        } else {
            return searchInRotatedArray(A, 0, A.length - 1, target);
        }
    }

    public static int searchInRotatedArray(int[] A, int start, int end, int target) {

        int size = end - start + 1;
        if (size == 0) {
            return -1;
        } else if (size <= 2) {
            for (int i = start; i <= end; i++) {
                if (A[i] == target) {
                    return i;
                }
            }
            return -1;
        } else {
            int mid = (start + end) / 2;
            if (A[mid] == target) {
                return mid;
            }
            if (A[start] < A[mid] && A[mid] > A[end]) {
                if (target > A[mid] || target <= A[end]) {
                    return searchInRotatedArray(A, mid + 1, end, target);
                } else {
                    return searchInRotatedArray(A, start, mid, target);
                }
            } else if (A[start] > A[mid] && A[mid] < A[end]) {
                if (target < A[mid] || target >= A[start]) {
                    return searchInRotatedArray(A, start, mid, target);
                } else {
                    return searchInRotatedArray(A, mid + 1, end, target);
                }
            } else {
                if (target < A[mid]) {
                    return searchInRotatedArray(A, start, mid, target);
                } else {
                    return searchInRotatedArray(A, mid + 1, end, target);
                }
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
        } else if (A.length == 1) {
            return (A[0] == target) ? new int[]{0, 0} : new int[]{-1, -1};
        } else {
            return _searchRange(A, 0, A.length - 1, target);
        }
    }

    public static int[] _searchRange(int[] A, int start, int end, int target) {

        int size = end - start + 1;
        if (size == 0) {
            return new int[]{-1, -1};
        } else if (size == 1) {
            return (A[start] == target) ? new int[]{start, start} : new int[]{-1, -1};
        } else {
            int mid = (start + end) / 2;
            int midLeft = mid;
            while (midLeft >= start && A[midLeft] == A[mid]) {
                midLeft--;
            }
            int midRight = mid;
            while (midRight <= end && A[midRight] == A[mid]) {
                midRight++;
            }
            midLeft++;
            midRight--;
            int[] result = new int[2];
            if (A[mid] == target) {
                result[0] = midLeft;
                result[1] = midRight;
                return result;
            } else if (target < A[midLeft]) {
                return _searchRange(A, start, midLeft - 1, target);
            } else {
                return _searchRange(A, midRight + 1, end, target);
            }
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
        } else if (A.length == 1) {
            return (A[0] == target) ? 0 : ((A[0] > target) ? 0 : 1);
        } else {
            return _searchInsert(A, 0, A.length - 1, target);
        }
    }

    public static int _searchInsert(int[] A, int start, int end, int target) {

        int size = end - start + 1;
        if (size == 0) {
            return start;
        } else if (size <= 2) {
            for (int i = start; i <= end; i++) {
                if (A[i] >= target) {
                    return i;
                }
            }
            return end + 1;
        } else {
            int mid = (start + end) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (target < A[mid]) {
                return _searchInsert(A, start, mid, target);
            } else {
                return _searchInsert(A, mid + 1, end, target);
            }
        }
    }

    /**
     * 36. Valid Sudoku
     * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
     * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
     */
    public static boolean isValidSudoku(char[][] board) {
        int size = board.length;
        if (size != 9) {
            return false;
        }
        if (board[0].length != 9) {
            return false;
        }
        Set<Character> setRow = new HashSet<Character>();
        Set<Character> setCol = new HashSet<Character>();
        Set<Character> setCub1 = new HashSet<Character>();
        Set<Character> setCub2 = new HashSet<Character>();
        Set<Character> setCub3 = new HashSet<Character>();
        for (int i = 0; i < size; i++) {
            if (i > 0 && i / 3 != (i - 1) / 3) {
                setCub1.clear();
                setCub2.clear();
                setCub3.clear();
            }
            for (int j = 0; j < size; j++) {
                if (board[i][j] != '.') {
                    int block = j / 3;
                    Set<Character> cube;
                    if (block == 0) {
                        cube = setCub1;
                    } else if (block == 1) {
                        cube = setCub2;
                    } else if (block == 2) {
                        cube = setCub3;
                    } else {
                        return false;
                    }
                    if (setRow.contains(board[i][j]) || cube.contains(board[i][j])) {
                        return false;
                    }
                    setRow.add(board[i][j]);
                    cube.add(board[i][j]);
                }
                if (board[j][i] != '.') {
                    if (setCol.contains(board[j][i])) {
                        return false;
                    }
                    setCol.add(board[j][i]);
                }
            }
            setRow.clear();
            setCol.clear();
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
        int size = board.length;
        if (size < 9 || size > 9 || board[0].length != 9) {
            return;
        }
        _solveSudoku(board, 0, 0);
    }

    public static boolean _solveSudoku(char[][] board, int row, int col) {
        if (row == 9) {
            return true;
        }
        int nextRow = col == 8 ? row + 1 : row;
        int nextCol = col == 8 ? 0 : col + 1;
        if (board[row][col] != '.') {
            if (!_isValidSudokuElem(board, row, col)) {
                return false;
            }
            return _solveSudoku(board, nextRow, nextCol);
        } else {
            for (int i = 1; i <= 9; i++) {
                board[row][col] = Character.forDigit(i, 10);
                if (_isValidSudokuElem(board, row, col) && _solveSudoku(board, nextRow, nextCol)) {
                    return true;
                }
                board[row][col] = '.';
            }
            return false;
        }
    }

    public static boolean _isValidSudokuElem(char[][] board, int row, int col) {

        for (int i = 0; i < 9; i++) {
            if ((board[row][i] == board[row][col] && i != col) || (board[i][col] == board[row][col] && i != row)) {
                return false;
            }
        }
        int ROW = row / 3;
        int COL = col / 3;
        for (int i = 3 * ROW; i < 3 * ROW + 3; i++) {
            for (int j = 3 * COL; j < 3 * COL + 3; j++) {
                if (board[i][j] == board[row][col] && i != row && j != col) {
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
                StringBuilder temp = new StringBuilder();
                int count = 1;
                char last = builder.charAt(0);
                for (int k = 1; k < builder.length(); k++) {
                    if (builder.charAt(k) == last) {
                        count++;
                    } else {
                        temp.append(count).append(last);
                        last = builder.charAt(k);
                        count = 1;
                    }
                }
                temp.append(count).append(last);
                builder = temp;
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

        HashSet<List<Integer>> result = new HashSet<List<Integer>>();
        if (start > end) {
            return result;
        } else if (start == end) {
            if (candidates[start] > target) {
                return result;
            } else {
                if (target % candidates[start] == 0) {
                    List<Integer> list = new ArrayList<Integer>();
                    int times = target / candidates[start];
                    for (int i = 0; i < times; i++) {
                        list.add(candidates[start]);
                    }
                    result.add(list);
                }
                return result;
            }
        }
        for (int i = start; i <= end && candidates[i] <= target; i++) {
            int totalTimes = target / candidates[i];
            for (int j = 1; j <= totalTimes; j++) {
                int newTarget = target - candidates[i] * j;
                List<Integer> element = new ArrayList<Integer>();
                for (int k = 0; k < j; k++) {
                    element.add(candidates[i]);
                }
                if (newTarget == 0) {
                    result.add(element);
                } else {
                    Set<List<Integer>> list = _combinationSum(candidates, i + 1, end, newTarget);
                    if (!list.isEmpty()) {
                        for (List<Integer> _list : list) {
                            List<Integer> _element = new ArrayList<Integer>(element);
                            _element.addAll(_list);
                            result.add(_element);
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
        return new ArrayList<List<Integer>>(_combinationSum2(num, 0, num.length - 1, target));
    }

    public static Set<List<Integer>> _combinationSum2(int[] candidates, int start, int end, int target) {

        HashSet<List<Integer>> result = new HashSet<List<Integer>>();
        if (start > end) {
            return result;
        } else if (start == end) {
            if (candidates[start] > target) {
                return result;
            } else {
                if (target == candidates[start]) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(candidates[start]);
                    result.add(list);
                }
                return result;
            }
        }
        for (int i = start; i <= end && candidates[i] <= target; i++) {
            int newTarget = target - candidates[i];
            List<Integer> element = new ArrayList<Integer>();
            element.add(candidates[i]);
            if (newTarget == 0) {
                result.add(element);
            } else {
                Set<List<Integer>> list = _combinationSum2(candidates, i + 1, end, newTarget);
                if (!list.isEmpty()) {
                    for (List<Integer> _list : list) {
                        List<Integer> _element = new ArrayList<Integer>(element);
                        _element.addAll(_list);
                        result.add(_element);
                    }
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
        for (int i = 0; i < A.length; i++) {
            while (A[i] != i + 1) {
                if (A[i] > 0 && A[i] <= A.length && A[i] != A[A[i] - 1]) {
                    int temp = A[A[i] - 1];
                    A[A[i] - 1] = A[i];
                    A[i] = temp;
                } else {
                    break;
                }
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

        int total = 0;
        if (A.length < 3) {
            return total;
        }
        int[] maxLeft = new int[A.length];
        int temp = A[0];
        for (int i = 0; i < A.length; i++) {
            maxLeft[i] = temp;
            if (temp < A[i]) {
                temp = A[i];
            }
        }
        temp = A[A.length - 1];
        for (int i = A.length - 1; i >= 0; i--) {
            int minMax = Math.min(maxLeft[i], temp);
            if (minMax > A[i]) {
                total += (minMax - A[i]);
            }
            if (temp < A[i]) {
                temp = A[i];
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

        int size1 = num1.length();
        int size2 = num2.length();

        String result = "0";
        for (int i = size2 - 1; i >= 0; i--) {
            int zero = size2 - 1 - i;
            int b = num2.charAt(i) - 48;
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < zero; j++) {
                builder.append("0");
            }
            int carry = 0;
            for (int j = size1 - 1; j >= 0; j--) {
                int a = num1.charAt(j) - 48;
                int c = a * b + carry;
                carry = c / 10;
                builder.append(c % 10);
            }
            if (carry > 0) {
                builder.append(carry);
            }
            result = add(builder.reverse().toString(), result);
        }
        int index = 0;
        for (; index < result.length(); index++) {
            if (result.charAt(index) != '0') {
                break;
            }
        }
        return index == result.length() ? "0" : result.substring(index);
    }

    public static String add(String num1, String num2) {

        int size1 = num1.length();
        int size2 = num2.length();

        int index1 = size1 - 1;
        int index2 = size2 - 1;

        boolean markOne = false;

        StringBuilder builder = new StringBuilder();
        while (index1 >= 0 && index2 >= 0) {
            int a = num1.charAt(index1) - 48;
            int b = num2.charAt(index2) - 48;
            int c = a + b + (markOne ? 1 : 0);
            if (c >= 10) {
                markOne = true;
                builder.append(c - 10);
            } else {
                markOne = false;
                builder.append(c);
            }
            index1--;
            index2--;
        }
        if (index1 < 0 && index2 >= 0) {
            while (index2 >= 0) {
                int b = num2.charAt(index2) - 48;
                int c = b + (markOne ? 1 : 0);
                if (c >= 10) {
                    markOne = true;
                    builder.append(c - 10);
                } else {
                    markOne = false;
                    builder.append(c);
                }
                index2--;
            }
        } else if (index1 >= 0 && index2 < 0) {
            while (index1 >= 0) {
                int a = num1.charAt(index1) - 48;
                int c = a + (markOne ? 1 : 0);
                if (c >= 10) {
                    markOne = true;
                    builder.append(c - 10);
                } else {
                    markOne = false;
                    builder.append(c);
                }
                index1--;
            }
        }
        if (markOne) {
            builder.append(1);
        }
        return builder.reverse().toString();
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
            if (p.charAt(i) == '*') {
                if (builder.length() == 0 || builder.charAt(builder.length() - 1) != '*') {
                    builder.append(p.charAt(i));
                }
            } else {
                builder.append(p.charAt(i));
            }
        }

        p = builder.toString();

        return _isMatch2(s, p);
    }

    public static boolean _isMatch2(String s, String p) {

        int sizeA = s.length();
        int sizeB = p.length();

        if (sizeA == 0) {
            return sizeB == 0 || (sizeB == 1 && p.charAt(0) == '*');
        }
        if (sizeB == 0) {
            return false;
        }

        int index1 = 0;
        int index2 = 0;

        int lastS = 0;
        int lastP = 0;

        boolean meetStar = false;

        for (; index1 < sizeA; ) {
            char c1 = s.charAt(index1);
            char c2 = index2 >= sizeB ? '\0' : p.charAt(index2);
            if (c2 == '?') {
                index1++;
                index2++;
            } else if (c2 == '*') {
                if (index2 == sizeB - 1) {
                    return true;
                }
                lastS = index1;
                lastP = ++index2;
                meetStar = true;
            } else {
                if (c1 != c2) {
                    if (!meetStar) {
                        return false;
                    }
                    lastS++;
                    index1 = lastS;
                    index2 = lastP;
                } else {
                    index1++;
                    index2++;
                }
            }
        }
        return index2 >= sizeB || (index2 == sizeB - 1 && p.charAt(index2) == '*');

        //        if (p.charAt(0) == '*') {
        //            for (int i = 0; i <= sizeA; i++) {
        //                if (_isMatch2(s.substring(i), p.substring(1))) {
        //                    return true;
        //                }
        //            }
        //            return false;
        //        } else {
        //            if (p.charAt(0) != s.charAt(0) && p.charAt(0) != '?') {
        //                return false;
        //            }
        //            return _isMatch2(s.substring(1), p.substring(1));
        //        }
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

        int length = A.length;
        if (length <= 1) {
            return 0;
        }
        int start = 0;
        int end = 0;
        int count = 0;
        while (end < length) {
            count++;
            int max = 0;
            for (int i = start; i <= end; i++) {
                int edge = A[i] + i;
                if (edge >= length - 1) {
                    return count;
                }
                if (edge > max) {
                    max = edge;
                }
            }
            start = end + 1;
            end = max;
            if (start > end) {
                break;
            }
        }
        return -1;
    }

    /**
     * 46. Permutations
     * Given a collection of numbers, return all possible permutations.
     * For example,
     * [1,2,3] have the following permutations:
     * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
     */
    public static List<List<Integer>> permute(int[] num) {

        Set<List<Integer>> set = new HashSet<List<Integer>>();
        for (int i = 0; i < num.length; i++) {
            int insert = num[i];
            if (set.isEmpty()) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(insert);
                set.add(list);
            } else {
                Set<List<Integer>> temp = new HashSet<List<Integer>>();
                for (List<Integer> list : set) {
                    for (int j = 0; j <= list.size(); j++) {
                        List<Integer> tempList = new ArrayList<Integer>(list);
                        if (j == list.size()) {
                            tempList.add(insert);
                        } else {
                            tempList.add(j, insert);
                        }
                        temp.add(tempList);
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

        Set<List<Integer>> set = new HashSet<List<Integer>>();
        for (int i = 0; i < num.length; i++) {
            int insert = num[i];
            if (set.isEmpty()) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(insert);
                set.add(list);
            } else {
                Set<List<Integer>> temp = new HashSet<List<Integer>>();
                for (List<Integer> list : set) {
                    for (int j = 0; j <= list.size(); j++) {
                        List<Integer> tempList = new ArrayList<Integer>(list);
                        if (j == list.size()) {
                            tempList.add(insert);
                        } else {
                            tempList.add(j, insert);
                        }
                        temp.add(tempList);
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
     */
    public static void rotate(int[][] matrix) {

        int size = matrix.length;
        if (size <= 1) {
            return;
        }
        for (int i = 0; i < size / 2; i++) {
            for (int j = 0; j < (size + 1) / 2; j++) {
                int temp1 = matrix[j][size - 1 - i]; //b
                int temp2 = matrix[size - 1 - i][size - 1 - j];//c
                int temp3 = matrix[size - 1 - j][i];//d

                matrix[j][size - 1 - i] = matrix[i][j];
                matrix[size - 1 - i][size - 1 - j] = temp1;
                matrix[size - 1 - j][i] = temp2;
                matrix[i][j] = temp3;
            }
        }
    }

    /**
     * 49. Anagrams
     * Given an array of strings, return all groups of strings that are anagrams.
     * Note: All inputs will be in lower-case.
     */
    public static List<String> anagrams(String[] strs) {

        if (strs != null && strs.length > 0) {
            HashMap<String, List<String>> map = new HashMap<String, List<String>>();
            for (String str : strs) {
                char[] c = str.toCharArray();
                Arrays.sort(c);
                String key = new StringBuilder().append(c).toString();
                if (!map.containsKey(key)) {
                    List<String> list = new ArrayList<String>();
                    list.add(str);
                    map.put(key, list);
                } else {
                    List<String> list = map.get(key);
                    list.add(str);
                }
            }
            List<String> result = new ArrayList<String>();
            for (String key : map.keySet()) {
                List<String> list = map.get(key);
                if (list.size() > 1) {
                    result.addAll(list);
                }
            }
            return result;
        }
        return new ArrayList<String>();
    }

    /**
     * 50. Pow(x, n)
     * Implement pow(x, n).
     */
    public static double pow(double x, int n) {

        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 1 / power(x, -n);
        }
        return power(x, n);
    }

    public static double power(double x, int n) {

        if (n == 0) {
            return 1;
        }
        double result = power(x, n / 2);
        if (n % 2 == 0) {
            return result * result;
        } else {
            return result * result * x;
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

        char[][] board = new char[n][n];
        List<String[]> result = new ArrayList<String[]>();
        _solveNQueens(0, n, board, result);
        return result;

    }

    public static void _solveNQueens(int startRow, int n, char[][] board, List<String[]> result) {

        int i = startRow;
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                board[i][k] = '.';
            }
            if (isValidNQueue(board, n, i, j)) {
                board[i][j] = 'Q';
                if (i == n - 1) {
                    String[] element = new String[n];
                    for (int k = 0; k < n; k++) {
                        if (k == n - 1) {
                            StringBuilder builder = new StringBuilder();
                            for (int l = 0; l < n; l++) {
                                if (l == j) {
                                    builder.append("Q");
                                } else {
                                    builder.append(".");
                                }
                            }
                            element[k] = builder.toString();
                        } else {
                            element[k] = joinCharArray(board[k]);
                        }
                    }
                    result.add(element);
                } else {
                    _solveNQueens(i + 1, n, board, result);
                }
            }
            board[i][j] = '.';
        }
    }

    public static String joinCharArray(char[] array) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            builder.append(array[i]);
        }
        return builder.toString();
    }

    public static boolean isValidNQueue(char[][] board, int size, int i, int j) {
        for (int k = 0; k < size; k++) {
            if ((board[i][k] == 'Q' && k != j) || (board[k][j] == 'Q' && k != i)) {
                return false;
            }
        }
        int upLeftI = i - 1;
        int upLeftJ = j - 1;
        while (upLeftI >= 0 && upLeftJ >= 0) {
            if (board[upLeftI--][upLeftJ--] == 'Q') {
                return false;
            }
        }
        int upRightI = i - 1;
        int upRightJ = j + 1;
        while (upRightI >= 0 && upRightJ < size) {
            if (board[upRightI--][upRightJ++] == 'Q') {
                return false;
            }
        }
        int downLeftI = i + 1;
        int downLeftJ = j - 1;
        while (downLeftI < size && downLeftJ >= 0) {
            if (board[downLeftI++][downLeftJ--] == 'Q') {
                return false;
            }
        }
        int downRightI = i + 1;
        int downRightJ = j + 1;
        while (downRightI < size && downRightJ < size) {
            if (board[downRightI++][downRightJ++] == 'Q') {
                return false;
            }
        }
        return true;
    }

    /**
     * 52. N-Queens II Total Accepted: 19780 Total Submissions: 57141 My Submissions Question Solution
     * Follow up for N-Queens problem.
     * Now, instead outputting board configurations, return the total number of distinct solutions.
     */
    public static int totalNQueens(int n) {
        char[][] board = new char[n][n];
        List<String[]> result = new ArrayList<String[]>();
        _solveNQueens(0, n, board, result);
        return result.size();
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

        //        if (A.length == 0) {
        //            return 0;
        //        }
        //        int[] d = new int[A.length];
        //        d[0] = A[0];
        //        int max = d[0];
        //        for (int i = 1; i < A.length; i++) {
        //            d[i] = Math.max(d[i-1] + A[i], A[i]);
        //            max = Math.max(max, d[i]);
        //        }
        //        return max;

        if (A.length == 0) {
            return 0;
        } else if (A.length == 1) {
            return A[0];
        } else {
            return _maxSubArray(A, 0, A.length - 1);
        }
    }

    public static int _maxSubArray(int[] A, int start, int end) {

        int size = end - start + 1;
        if (size == 0) {
            return 0;
        } else if (size == 1) {
            return A[start];
        } else if (size == 2) {
            return Math.max(Math.max(A[start], A[end]), A[start] + A[end]);
        }
        int mid = (start + end) / 2;
        int maxLeft = _maxSubArray(A, start, mid);
        int maxRight = _maxSubArray(A, mid + 1, end);

        int maxL = A[mid];
        int maxR = A[mid];
        int sum = A[mid];
        for (int i = mid - 1; i >= start; i--) {
            sum += A[i];
            if (sum > maxL) {
                maxL = sum;
            }
        }
        sum = A[mid];
        for (int i = mid + 1; i <= end; i++) {
            sum += A[i];
            if (sum > maxR) {
                maxR = sum;
            }
        }
        return Math.max(Math.max(maxLeft, maxRight), maxL + maxR - A[mid]);
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

        List<Integer> result = new ArrayList<Integer>();

        int m = matrix.length;
        if (m == 0) {
            return result;
        }
        int n = matrix[0].length;
        if (n == 0) {
            return result;
        }

        boolean[][] used = new boolean[m][n];
        int i = 0;
        int j = 0;
        while (!used[i][j]) {
            result.add(matrix[i][j]);
            used[i][j] = true;

            boolean up = i != 0 && !used[i - 1][j];
            boolean down = i != m - 1 && !used[i + 1][j];
            boolean left = j != 0 && !used[i][j - 1];
            boolean right = j != n - 1 && !used[i][j + 1];

            if (!up && down && !left && right) {
                j++;
            } else if (!up && down && left && !right) {
                i++;
            } else if (up && !down && left && !right) {
                j--;
            } else if (up && !down && !left && right) {
                i--;
            } else if (!up && !down && !left && right) {
                j++;
            } else if (!up && down && !left && !right) {
                i++;
            } else if (!up && !down && left && !right) {
                j--;
            } else if (up && !down && !left && !right) {
                i--;
            } else {
                break;
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

        int length = A.length;
        if (length <= 1) {
            return true;
        }
        int start = 0;
        int end = 0;
        while (end < length) {
            int furthest = end;
            for (int i = start; i <= end; i++) {
                if (i + A[i] > furthest) {
                    furthest = i + A[i];
                }
            }
            if (furthest == end) {
                return false;
            } else if (furthest >= length - 1) {
                return true;
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

        List<Interval> collect = new ArrayList<Interval>();
        for (Interval interval : intervals) {
            if (collect.isEmpty()) {
                collect.add(interval);
            } else {
                insertInterval(collect, interval);
            }
        }
        return collect;
    }

    public static void insertInterval(List<Interval> intervals, Interval newInterval) {
        int i = 0;
        while (i < intervals.size()) {

            int start = intervals.get(i).start;
            int end = intervals.get(i).end;

            if (start == newInterval.start && end == newInterval.end) {
                return;
            }

            if (newInterval.end < start) {
                intervals.add(i, newInterval);
                return;
            } else if (newInterval.start > end) {
                i++;
            } else {
                newInterval.start = Math.min(newInterval.start, start);
                newInterval.end = Math.max(newInterval.end, end);
                intervals.remove(i);
            }
        }
        intervals.add(newInterval);
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

        int i = 0;
        while (i < intervals.size()) {
            int newStart = newInterval.start;
            int newEnd = newInterval.end;

            int start = intervals.get(i).start;
            int end = intervals.get(i).end;
            if (newEnd < start) {
                intervals.add(i, newInterval);
                return intervals;
            } else if (newStart > end) {
                i++;
            } else {
                intervals.remove(i);
                newInterval.start = Math.min(start, newStart);
                newInterval.end = Math.max(end, newEnd);
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

        int length = 0;
        boolean meetChar = false;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (meetChar) {
                    return length;
                }
            } else {
                meetChar = true;
                length++;
            }
        }
        return length;
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

        int[][] result = new int[n][n];
        if (n == 0) {
            return result;
        }
        boolean[][] used = new boolean[n][n];
        int i = 0;
        int j = 0;
        int val = 1;
        while (!used[i][j]) {
            result[i][j] = val++;
            used[i][j] = true;
            boolean up = i != 0 && !used[i - 1][j];
            boolean down = i != n - 1 && !used[i + 1][j];
            boolean left = j != 0 && !used[i][j - 1];
            boolean right = j != n - 1 && !used[i][j + 1];
            if (!up && down && !left && right) {
                j++;
            } else if (!up && down && left && !right) {
                i++;
            } else if (up && !down && left && !right) {
                j--;
            } else if (up && !down && !left && right) {
                i--;
            } else if (!up && !down && !left && right) {
                j++;
            } else if (!up && down && !left && !right) {
                i++;
            } else if (!up && !down && left && !right) {
                j--;
            } else if (up && !down && !left && !right) {
                i--;
            } else {
                break;
            }
        }
        return result;
    }


    /**
     * 60. Permutation Sequence
     * The set [1,2,3,…,n] contains a total of n! unique permutations.
     * By listing and labeling all of the permutations in order,
     * We get the following sequence (ie, for n = 3):
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     * Given n and k, return the kth permutation sequence.
     * Note: Given n will be between 1 and 9 inclusive.
     */
    public static String getPermutation(int n, int k) {

        if (n == 1) {
            return "1";
        }
        boolean[] used = new boolean[n];
        return _getPermutation(used, n, k);
    }

    public static String _getPermutation(boolean[] used, int size, int k) {

        if (size == 1) {
            for (int i = 0; i < used.length; i++) {
                if (!used[i]) {
                    return Integer.toString(i + 1);
                }
            }
        }

        int total = 1;
        for (int i = 1; i <= size - 1; i++) {
            total *= i;
        }
        int index = ((k - 1) / total) % size;
        int count = 0;
        int t = 0;
        for (; t < used.length; t++) {
            if (!used[t]) {
                if (count++ == index) {
                    break;
                }
            }
        }
        used[t] = true;
        return new StringBuilder().append(t + 1).append(_getPermutation(used, size - 1, k)).toString();
    }

    /**
     * 61. Rotate List
     * Given a list, rotate the list to the right by k places, where k is non-negative.
     * For example:
     * Given 1->2->3->4->5->NULL and k = 2,
     * return 4->5->1->2->3->NULL.
     */
    public static ListNode rotateRight(ListNode head, int n) {

        if (n <= 0) {
            return head;
        }
        int size = 0;
        ListNode temp = head;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        if (size == 0) {
            return head;
        }
        n = n % size == 0 ? size : n % size;
        int count = 0;
        temp = head;
        ListNode tail = null;
        while (temp != null) {
            if (tail != null) {
                tail = tail.next;
            }
            if (count == n) {
                tail = head;
            }
            count++;
            if (temp.next == null && tail != null) {
                ListNode newHead = tail.next;
                tail.next = null;
                temp.next = head;
                return newHead;
            }
            temp = temp.next;
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
        if (m == 1 || n == 1) {
            return 1;
        }
        int[][] path = new int[m][n];
        path[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    path[i][j] = 1;
                } else {
                    path[i][j] = path[i - 1][j] + path[i][j - 1];
                }
            }
        }
        return path[m - 1][n - 1];
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
        int[][] path = new int[m][n];
        path[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (obstacleGrid[i][j] == 1) {
                    path[i][j] = 0;
                } else {
                    if (i == 0) {
                        path[i][j] = path[i][j - 1];
                    } else if (j == 0) {
                        path[i][j] = path[i - 1][j];
                    } else {
                        path[i][j] = path[i - 1][j] + path[i][j - 1];
                    }
                }
            }
        }
        return path[m - 1][n - 1];
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
        int[][] sum = new int[m][n];
        sum[0][0] = grid[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (i == 0) {
                    sum[i][j] = sum[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    sum[i][j] = sum[i - 1][j] + grid[i][j];
                } else {
                    sum[i][j] = Math.min(sum[i - 1][j], sum[i][j - 1]) + grid[i][j];
                }
            }
        }
        return sum[m - 1][n - 1];
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

        int len = s.length();
        if (len == 0) {
            return false;
        }
        boolean started = false;
        boolean hasSig = false;
        boolean hasDot = false;
        boolean hasExp = false;
        boolean hasNum = false;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                while (i < len && s.charAt(i) == ' ') {
                    i++;
                }
                if ((i == len && !started) || (i < len && started)) {
                    return false;
                }
                i--;
            } else if (c == '.') {
                if (hasDot || hasExp) {
                    return false;
                }
                hasDot = true;
                started = true;
            } else if (c == 'E' || c == 'e') {
                if (!started || hasExp || !hasNum || s.charAt(i - 1) == '+' || s.charAt(i - 1) == '-') {
                    return false;
                }
                hasExp = true;
                hasSig = false;
                hasNum = false;
            } else if (c == '+' || c == '-') {
                if (hasSig || hasNum || s.charAt(i - 1) == '.') {
                    return false;
                }
                hasSig = true;
                started = true;
            } else if (c >= '0' && c <= '9') {
                hasNum = true;
                started = true;
            } else {
                return false;
            }
        }
        if (!started || !hasNum) {
            return false;
        }
        return true;
    }

    /**
     * 66. Plus One
     * Given a non-negative number represented as an array of digits, plus one to the number.
     * The digits are stored such that the most significant digit is at the head of the list.
     */
    public static int[] plusOne(int[] digits) {

        int size = digits.length;
        int[] result = new int[size == 0 ? 1 : size];
        if (size == 0) {
            result[0] = 1;
        } else {
            boolean markOne = false;
            for (int i = size - 1; i >= 0; i--) {
                int c = digits[i] + (markOne ? 1 : 0) + (i == size - 1 ? 1 : 0);
                if (c >= 10) {
                    markOne = true;
                    c -= 10;
                } else {
                    markOne = false;
                }
                result[i] = c;
            }
            if (markOne) {
                int[] temp = new int[size + 1];
                temp[0] = 1;
                for (int i = 0; i < size; i++) {
                    temp[i + 1] = result[i];
                }
                return temp;
            }
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

        int size1 = a.length();
        int size2 = b.length();
        if (size1 == 0) {
            return b;
        }
        if (size2 == 0) {
            return a;
        }

        StringBuilder builder = new StringBuilder();
        boolean markOne = false;
        int index1 = size1 - 1;
        int index2 = size2 - 1;
        while (index1 >= 0 && index2 >= 0) {
            int c1 = a.charAt(index1) - 48;
            int c2 = b.charAt(index2) - 48;
            int c = c1 + c2 + (markOne ? 1 : 0);
            if (c >= 2) {
                markOne = true;
                c = c % 2;
            } else {
                markOne = false;
            }
            builder.append(c);
            index1--;
            index2--;
        }
        if (index1 < 0 && index2 >= 0) {
            while (index2 >= 0) {
                int c2 = b.charAt(index2) - 48;
                int c = c2 + (markOne ? 1 : 0);
                if (c >= 2) {
                    markOne = true;
                    c = c % 2;
                } else {
                    markOne = false;
                }
                builder.append(c);
                index2--;
            }
        } else if (index1 >= 0 && index2 < 0) {
            while (index1 >= 0) {
                int c1 = a.charAt(index1) - 48;
                int c = c1 + (markOne ? 1 : 0);
                if (c >= 2) {
                    markOne = true;
                    c = c % 2;
                } else {
                    markOne = false;
                }
                builder.append(c);
                index1--;
            }
        }
        if (markOne) {
            builder.append(1);
        }
        return builder.reverse().toString();
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

        List<Integer> lineCount = new ArrayList<Integer>();
        List<List<StringBuilder>> lines = new ArrayList<List<StringBuilder>>();
        List<StringBuilder> currentLine = new ArrayList<StringBuilder>();
        int currentLineCharCount = 0;
        for (String word : words) {
            int len = word.length();
            if (currentLineCharCount + (currentLineCharCount == 0 ? 0 : 1) + len > L) {
                lines.add(currentLine);
                lineCount.add(currentLineCharCount);
                currentLineCharCount = len;
                currentLine = new ArrayList<StringBuilder>();
                currentLine.add(new StringBuilder().append(word));
            } else {
                if (currentLineCharCount == 0) {
                    currentLineCharCount = len;
                } else {
                    currentLineCharCount += (1 + len);
                }
                currentLine.add(new StringBuilder().append(word));
            }
        }
        if (!currentLine.isEmpty()) {
            lineCount.add(currentLineCharCount);
            lines.add(currentLine);
        }

        List<String> result = new ArrayList<String>();
        for (int i = 0; i < lines.size(); ++i) {
            List<StringBuilder> line = lines.get(i);
            int left = L - lineCount.get(i);
            if (line.size() == 1) {
                for (int k = 0; k < left; k++) {
                    line.get(0).append(" ");
                }
            } else if (i != lines.size() - 1) {
                for (int j = 0; j < line.size() - 1; j++) {
                    line.get(j).append(" ");
                }
                int index = 0;
                while (left > 0) {
                    line.get(index).append(" ");
                    if (index + 1 == line.size() - 1) {
                        index = 0;
                    } else {
                        index++;
                    }
                    left--;
                }
            } else {
                for (int j = 0; j < line.size(); j++) {
                    if (j == line.size() - 1) {
                        for (int k = 0; k < left; k++) {
                            line.get(j).append(" ");
                        }
                    } else {
                        line.get(j).append(" ");
                    }
                }
            }
            StringBuilder resultLine = new StringBuilder();
            for (StringBuilder tempBuilder : line) {
                resultLine.append(tempBuilder.toString());
            }
            result.add(resultLine.toString());
        }
        return result;
    }

    /**
     * 69. Sqrt(x)
     * Implement int sqrt(int x).
     * Compute and return the square root of x.
     */
    public static int sqrt(int x) {

        long target = x;
        long start = 0;
        long end = target / 2 + 1;

        while (start <= end) {
            long mid = (start + end) / 2;
            long sqr = mid * mid;
            if (sqr == target) {
                return (int) mid;
            } else if (sqr > target) {
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
        if (n <= 1) {
            return n;
        }
        int[] d = new int[n];
        d[0] = 1;
        for (int i = 1; i < n; i++) {
            d[i] = d[i - 1] + (i >= 2 ? d[i - 2] : 1);
        }
        return d[n - 1];
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
        stack.addLast("/");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            if (c == '/') {
                if (builder.toString().equals(".")) {
                    builder.delete(0, builder.length());
                } else if (builder.toString().equals("..")) {
                    builder.delete(0, builder.length());
                    if (!stack.isEmpty() && !stack.getLast().equals("/")) {
                        stack.removeLast();
                    }
                } else if (builder.length() > 0) {
                    stack.addLast(builder.toString());
                    builder.delete(0, builder.length());
                }
            } else {
                builder.append(c);
            }
        }
        StringBuilder result = new StringBuilder();
        result.append("/");
        stack.removeFirst();
        while (!stack.isEmpty()) {
            String str = stack.removeFirst();
            result.append(str);
            if (!stack.isEmpty()) {
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

        int len1 = word1.length();
        int len2 = word2.length();
        int[][] d = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len2; i++) {
            d[0][i] = i;
        }
        for (int i = 0; i <= len1; i++) {
            d[i][0] = i;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    d[i][j] = d[i - 1][j - 1];
                } else {
                    d[i][j] = Math.min(Math.min(d[i - 1][j - 1], d[i][j - 1]), d[i - 1][j]) + 1;
                }
            }
        }
        return d[len1][len2];

    }

    /**
     * 73. Set Matrix Zeroes
     * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
     */
    public static void setZeroes(int[][] matrix) {

        Set<Integer> rows = new HashSet<Integer>();
        Set<Integer> cols = new HashSet<Integer>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (rows.contains(i) || cols.contains(j)) {
                    matrix[i][j] = 0;
                }
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

        int m = matrix.length;
        if (m == 0) {
            return false;
        }
        int n = matrix[0].length;
        if (n == 0) {
            return false;
        }
        return _searchMatrix(matrix, 0, m - 1, 0, n - 1, target);
    }

    public static boolean _searchMatrix(int[][] matrix, int iRow1, int iRow2, int iCol1, int iCol2, int target) {

        if (iRow1 > iRow2 || iCol1 > iCol2) {
            return false;
        }
        if (iRow1 == iRow2) {
            if (iCol1 == iCol2) {
                return matrix[iRow1][iCol1] == target;
            } else {
                int mid = (iCol1 + iCol2) / 2;
                if (matrix[iRow1][mid] == target) {
                    return true;
                } else if (matrix[iRow1][mid] > target) {
                    return _searchMatrix(matrix, iRow1, iRow1, iCol1, mid - 1, target);
                } else {
                    return _searchMatrix(matrix, iRow1, iRow1, mid + 1, iCol2, target);
                }
            }
        } else {
            int mid = (iRow1 + iRow2) / 2;
            if (matrix[mid][iCol1] > target) {
                return _searchMatrix(matrix, iRow1, mid - 1, iCol1, iCol2, target);
            } else if (matrix[mid][iCol1] == target) {
                return true;
            } else if (matrix[mid][iCol1] < target && matrix[mid][iCol2] >= target) {
                return _searchMatrix(matrix, mid, mid, iCol1, iCol2, target);
            } else {
                return _searchMatrix(matrix, mid + 1, iRow2, iCol1, iCol2, target);
            }
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
        if (A.length < 2) {
            return;
        }
        int index1 = 0;
        int index2 = A.length - 1;
        for (int i = 0; index1 <= index2 && i <= index2; ) {
            if (A[i] == 0) {
                int temp = A[index1];
                A[index1] = A[i];
                A[i] = temp;
                index1++;
                i++;
            } else if (A[i] == 1) {
                i++;
            } else if (A[i] == 2) {
                int temp = A[index2];
                A[index2] = A[i];
                A[i] = temp;
                index2--;
            } else {
                System.out.println("Invalid Number!");
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

        int sizeS = S.length();
        int sizeT = T.length();
        String result = "";
        if (sizeS == 0 || sizeT == 0 || sizeS < sizeT) {
            return result;
        }
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < sizeT; i++) {
            char c = T.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        List<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0; i < sizeS; i++) {
            char c = S.charAt(i);
            if (map.containsKey(c)) {
                indexes.add(i);
            }
        }

        int size = indexes.size();
        HashMap<Character, Integer> tempMap = new HashMap<Character, Integer>();
        int done = 0;
        result = null;
        int start = 0;
        int end = 0;
        for (; end < size; end++) {
            int iStart = indexes.get(start);
            int iEnd = indexes.get(end);
            char _start = S.charAt(iStart);
            char _end = S.charAt(iEnd);

            tempMap.put(_end, (tempMap.containsKey(_end) ? tempMap.get(_end) : 0) + 1);
            if (tempMap.get(_end).equals(map.get(_end))) {
                done++;
                if (done == map.size()) {
                    if (result == null) {
                        result = S.substring(iStart, iEnd + 1);
                    } else {
                        result = iEnd - iStart + 1 < result.length() ? S.substring(iStart, iEnd + 1) : result;
                    }
                    if (end - start + 1 == T.length()) {
                        tempMap.put(_start, tempMap.get(_start) - 1);
                        if (tempMap.get(_start) < map.get(_start)) {
                            done--;
                        }
                        start++;
                    } else {
                        tempMap.put(_start, tempMap.get(_start) - 1);
                        if (tempMap.get(_start) < map.get(_start)) {
                            done--;
                        }
                        tempMap.put(_end, tempMap.get(_end) - 1);
                        if (tempMap.get(_end) < map.get(_end) && _start != _end) {
                            done--;
                        }
                        start++;
                        end--;
                    }
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

        Set<List<Integer>> result = new HashSet<List<Integer>>();
        if (k > n || n == 0) {
            return new ArrayList<List<Integer>>(result);
        }
        for (int i = 1; i <= k; i++) {
            if (i == 1) {
                for (int j = 1; j <= n; j++) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(j);
                    result.add(list);
                }
                continue;
            }
            Set<List<Integer>> temp = new HashSet<List<Integer>>();
            for (int j = 1; j <= n; j++) {
                for (List<Integer> preList : result) {
                    if (preList.contains(j)) {
                        continue;
                    }
                    int l = 0;
                    List<Integer> list = new ArrayList<Integer>();
                    for (; l < preList.size(); l++) {
                        if (j < preList.get(l)) {
                            break;
                        } else {
                            list.add(preList.get(l));
                        }
                    }
                    list.add(j);
                    while (l < preList.size()) {
                        list.add(preList.get(l++));
                    }
                    temp.add(list);
                }
            }
            result = temp;
        }
        return new ArrayList<List<Integer>>(result);
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

        Arrays.sort(S);

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = 0; i < S.length; i++) {
            int a = S[i];

            if (result.isEmpty()) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(a);
                result.add(list);
            } else {
                int size = result.size();
                for (int j = 0; j < size; j++) {
                    List<Integer> list = new ArrayList<Integer>(result.get(j));
                    list.add(a);
                    result.add(list);
                }
                List<Integer> list = new ArrayList<Integer>();
                list.add(a);
                result.add(list);
            }
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

        int m = board.length;
        if (m <= 0) {
            return false;
        }
        int n = board[0].length;
        if (n <= 0) {
            return false;
        }
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0) && !visited[i][j]) {
                    visited[i][j] = true;
                    if (_wordSearch(board, m, n, word, visited, i, j, 1)) {
                        return true;
                    }
                    visited[i][j] = false;
                }
            }
        }
        return false;
    }

    public static boolean _wordSearch(char[][] board, int m, int n, String word, boolean[][] visited, int row, int col,
                                      int index) {

        if (index >= word.length()) {
            return true;
        }
        char target = word.charAt(index);
        boolean up = row > 0 && !visited[row - 1][col] && board[row - 1][col] == target;
        boolean down = row < m - 1 && !visited[row + 1][col] && board[row + 1][col] == target;
        boolean left = col > 0 && !visited[row][col - 1] && board[row][col - 1] == target;
        boolean right = col < n - 1 && !visited[row][col + 1] && board[row][col + 1] == target;
        if (up) {
            visited[row - 1][col] = true;
            if (_wordSearch(board, m, n, word, visited, row - 1, col, index + 1)) {
                return true;
            }
            visited[row - 1][col] = false;
        }
        if (down) {
            visited[row + 1][col] = true;
            if (_wordSearch(board, m, n, word, visited, row + 1, col, index + 1)) {
                return true;
            }
            visited[row + 1][col] = false;
        }
        if (left) {
            visited[row][col - 1] = true;
            if (_wordSearch(board, m, n, word, visited, row, col - 1, index + 1)) {
                return true;
            }
            visited[row][col - 1] = false;
        }
        if (right) {
            visited[row][col + 1] = true;
            if (_wordSearch(board, m, n, word, visited, row, col + 1, index + 1)) {
                return true;
            }
            visited[row][col + 1] = false;
        }
        return false;
    }

    /**
     * 80. Remove Duplicates from Sorted Array II
     * Question
     * Solution
     * Follow up for "Remove Duplicates":
     * What if duplicates are allowed at most twice?
     * For example,
     * Given sorted array A = [1,1,1,2,2,3],
     * Your function should return length = 5, and A is now [1,1,2,2,3].
     */
    public static int removeDuplicates2(int[] A) {

        if (A.length <= 2) {
            return A.length;
        }
        int size = A.length;
        int index1 = 0;
        int index2 = 1;
        while (index1 < size && index2 < A.length) {

            while (index2 < A.length && A[index1] == A[index2]) {
                if (index2 - index1 >= 2) {
                    size--;
                }
                index2++;
            }
            if (index2 - index1 > 2 && index2 < A.length) {
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

        int size = A.length;
        if (size == 0) {
            return false;
        }
        if (size == 1) {
            return A[0] == target;
        }
        return _search2(A, 0, size - 1, target);
    }

    public static boolean _search2(int[] A, int start, int end, int target) {

        if (start > end) {
            return false;
        }
        int size = end - start + 1;
        if (size == 1) {
            return A[start] == target;
        }
        int mid = (start + end) / 2;
        if (A[mid] == target) {
            return true;
        }
        int left = mid;
        int right = mid;
        while (left >= start && A[left] == A[mid]) {
            left--;
        }
        while (right <= end && A[right] == A[mid]) {
            right++;
        }
        if (left <= start) {
            return A[start] == target || _search2(A, right, end, target);
        } else if (right >= end) {
            return A[end] == target || _search2(A, start, left, target);
        }

        if (A[left] >= A[start]) {
            if (A[left] == target) {
                return true;
            } else if (A[left] > target && A[start] <= target) {
                return _search2(A, start, left - 1, target);
            } else if (A[right] <= target || A[end] >= target) {
                return _search2(A, right, end, target);
            }
        } else if (A[right] <= A[end]) {
            if (A[left] == target) {
                return true;
            } else if (A[left] > target || A[end] < target) {
                return _search2(A, start, left - 1, target);
            } else if (A[right] == target) {
                return true;
            } else if (A[right] < target && A[end] >= target) {
                return _search2(A, right + 1, end, target);
            }
        }
        return false;
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

        ListNode newHead = head;
        ListNode father = null;
        ListNode temp1 = head;
        ListNode temp2 = null;
        int count = 0;
        while (temp1 != null) {
            temp2 = temp1;
            count = 0;
            while (temp2 != null && temp2.val == temp1.val) {
                temp2 = temp2.next;
                count++;
            }
            if (count > 1) {
                if (father == null) {
                    newHead = temp2;
                    temp1 = temp2;
                } else {
                    father.next = temp2;
                    temp1 = temp2;
                }
                continue;
            }
            father = temp1;
            temp1 = temp1.next;
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

        ListNode temp1 = head;
        ListNode temp2 = null;
        int count = 0;
        while (temp1 != null) {
            temp2 = temp1;
            count = 0;
            while (temp2 != null && temp2.val == temp1.val) {
                temp2 = temp2.next;
                count++;
            }
            if (count > 1) {
                temp1.next = temp2;
            }
            temp1 = temp1.next;
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

        int size = height.length;
        if (size == 0) {
            return 0;
        }
        int max = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i <= size; i++) {
            int h = 0;
            if (i < size) {
                h = height[i];
            }
            if (stack.isEmpty() || height[stack.peek()] <= h) {
                stack.push(i);
            } else {
                int index = stack.pop();
                int area = 0;
                if (!stack.isEmpty()) {
                    area = height[index] * (i - stack.peek() - 1);
                } else {
                    area = i * height[index];
                }
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
        int[][] level = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    level[i][j] = i == 0 ? 1 : level[i - 1][j] + 1;
                } else {
                    level[i][j] = 0;
                }
            }
            max = Math.max(max, _maximalRectangle(level[i]));
        }
        return max;
    }

    public static int _maximalRectangle(int[] A) {
        int max = 0;
        int size = A.length;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i <= size; i++) {
            int current = 0;
            if (i < size) {
                current = A[i];
            }
            if (stack.isEmpty() || A[stack.peek()] <= current) {
                stack.push(i);
            } else {
                int index = stack.pop();
                int area;
                if (stack.isEmpty()) {
                    area = i * A[index];
                } else {
                    area = (i - stack.peek() - 1) * A[index];
                }
                max = Math.max(max, area);
                i--;
            }
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

        ListNode head1 = new ListNode(Integer.MAX_VALUE);
        ListNode head2 = new ListNode(Integer.MAX_VALUE);
        ListNode temp1 = head1;
        ListNode temp2 = head2;
        ListNode temp = head;
        while (temp != null) {
            if (temp.val < x) {
                temp1.next = temp;
                temp1 = temp1.next;
                temp = temp.next;
                temp1.next = null;
            } else {
                temp2.next = temp;
                temp2 = temp2.next;
                temp = temp.next;
                temp2.next = null;
            }
        }
        if (head1.next == null) {
            return head2.next;
        } else if (head2.next == null) {
            return head1.next;
        } else {
            temp1.next = head2.next;
            return head1.next;
        }
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
     * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
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
        }
        int[] chars = new int[256];
        for (int i = 0; i < size1; i++) {
            chars[s1.charAt(i)]++;
        }
        for (int i = 0; i < size1; i++) {
            chars[s2.charAt(i)]--;
        }
        for (int i = 0; i < 256; i++) {
            if (chars[i] != 0) {
                return false;
            }
        }
        if (size1 == 1) {
            return true;
        }
        for (int i = 1; i < size1; i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                return true;
            }
            if (isScramble(s1.substring(0, i), s2.substring(size1 - i)) && isScramble(s1.substring(i),
                                                                                      s2.substring(0, size1 - i))) {
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

        int i = m + n - 1;
        int a = m - 1;
        int b = n - 1;

        while (a >= 0 && b >= 0) {
            int target;
            if (A[a] > B[b]) {
                target = A[a];
                a--;
            } else {
                target = B[b];
                b--;
            }
            A[i--] = target;
        }
        if (a < 0 && b >= 0) {
            while (b >= 0) {
                A[i--] = B[b--];
            }
        } else if (a >= 0 && b < 0) {
            while (a >= 0) {
                A[i--] = A[a--];
            }
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
        result.add(0);
        for (int i = 1; i <= n; i++) {
            int extra = (int) Math.pow(2, i - 1);
            int size = result.size();
            for (int j = size - 1; j >= 0; j--) {
                result.add(result.get(j) + extra);
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

        Set<List<Integer>> result = new HashSet<List<Integer>>();
        List<List<Integer>> indexList = new ArrayList<List<Integer>>();
        for (int k = 1; k <= num.length; k++) {
            List<List<Integer>> tempIndexList = new ArrayList<List<Integer>>();
            for (int i = 0; i < num.length; i++) {
                if (k == 1) {
                    List<Integer> index = new ArrayList<Integer>();
                    index.add(i);
                    tempIndexList.add(index);
                } else {
                    for (int j = 0; j < indexList.size(); j++) {
                        if (!indexList.get(j).isEmpty() && indexList.get(j).get(0) > i) {
                            List<Integer> tempIndexes = new ArrayList<Integer>();
                            tempIndexes.add(i);
                            tempIndexes.addAll(indexList.get(j));
                            tempIndexList.add(tempIndexes);
                        }
                    }
                }
            }
            indexList = tempIndexList;
            for (List<Integer> indexes : indexList) {
                List<Integer> values = new ArrayList<Integer>();
                for (Integer index : indexes) {
                    values.add(num[index]);
                }
                result.add(values);
            }
        }
        result.add(new ArrayList<Integer>());
        return new ArrayList<List<Integer>>(result);
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
        }
        if (s.charAt(0) == '0') {
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
            } else if (c > '6') {
                d[i] = d[i - 1] + ((s.charAt(i - 1) == '1') ? (i < 2 ? 1 : d[i - 2]) : 0);
            } else if (c > '0' && c <= '6') {
                d[i] = d[i - 1] + ((s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2') ? (i < 2 ? 1 : d[i - 2]) : 0);
            } else {
                d[i] = 0;
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

        if (m >= n || head == null) {
            return head;
        }
        ListNode startFather = null;
        ListNode start = null;
        ListNode end = null;
        ListNode endChild = null;
        int count = 1;
        ListNode temp = head;
        ListNode tempFather = null;
        while (temp != null) {
            if (count == m) {
                start = temp;
                startFather = tempFather;
            } else if (count == n) {
                end = temp;
                endChild = end.next;
                end.next = null;
                break;
            }
            count++;
            tempFather = temp;
            temp = temp.next;
        }
        ListNode temp1 = start;
        ListNode temp2 = temp1.next;
        temp1.next = null;
        while (temp2 != null) {
            temp = temp2.next;
            temp2.next = temp1;
            temp1 = temp2;
            temp2 = temp;
        }
        ListNode newHead = head;
        if (startFather == null) {
            newHead = end;
        } else {
            startFather.next = end;
        }
        start.next = endChild;
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

        List<String> result = new ArrayList<String>();
        if (s.length() < 4 || s.length() > 12) {
            return result;
        }
        List<List<String>> lists = _restoreIpAddresses(s, 4);
        for (List<String> list : lists) {
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

    public static List<List<String>> _restoreIpAddresses(String s, int section) {

        List<List<String>> result = new ArrayList<List<String>>();
        if (section == 0 || s.length() < section) {
            return result;
        }
        if (section == 1) {
            if (s.equals("0") || (s.charAt(0) != '0' && Integer.parseInt(s) <= 255)) {
                List<String> list = new ArrayList<String>();
                list.add(s);
                result.add(list);
            }
            return result;
        }

        if (s.charAt(0) == '0') {
            List<List<String>> rest = _restoreIpAddresses(s.substring(1), section - 1);
            for (List<String> aRest : rest) {
                if (aRest.size() != section - 1) {
                    continue;
                }
                aRest.add(0, "0");
                result.add(aRest);
            }
        } else {
            for (int i = 0; i < Math.min(s.length(), 3); i++) {
                String head = s.substring(0, i + 1);
                if (head.equals("0") || (head.charAt(0) != '0' && Integer.parseInt(head) <= 255)) {
                    List<List<String>> rest = _restoreIpAddresses(s.substring(i + 1), section - 1);
                    for (List<String> aRest : rest) {
                        if (aRest.size() != section - 1) {
                            continue;
                        }
                        aRest.add(0, head);
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

        List<Integer> inorder = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode temp = root;
        while (!stack.isEmpty() || temp != null) {
            if (temp != null) {
                stack.push(temp);
                temp = temp.left;
            } else {
                temp = stack.pop();
                inorder.add(temp.val);
                temp = temp.right;
            }
        }
        return inorder;
    }

    public static void inorderTraversalDFS(TreeNode node, List<Integer> inorder) {

        if (node == null) {
            return;
        }
        inorderTraversalDFS(node.left, inorder);
        inorder.add(node.val);
        inorderTraversalDFS(node.right, inorder);
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

        if (n < 1) {
            List<TreeNode> result = new ArrayList<TreeNode>();
            result.add(null);
            return result;
        }
        return _generateTrees(1, n);
    }

    public static List<TreeNode> _generateTrees(int start, int end) {

        List<TreeNode> result = new ArrayList<TreeNode>();
        if (start > end) {
            return result;
        } else if (start == end) {
            result.add(new TreeNode(start));
            return result;
        } else if (start + 1 == end) {
            TreeNode head1 = new TreeNode(start);
            head1.right = new TreeNode(end);
            TreeNode head2 = new TreeNode(end);
            head2.left = new TreeNode(start);
            result.add(head1);
            result.add(head2);
            return result;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftHeads = _generateTrees(start, i - 1);
            List<TreeNode> rightHeads = _generateTrees(i + 1, end);
            if (leftHeads.size() == 0) {
                for (TreeNode right : rightHeads) {
                    TreeNode head = new TreeNode(i);
                    head.right = right;
                    result.add(head);
                }
            } else if (rightHeads.size() == 0) {
                for (TreeNode left : leftHeads) {
                    TreeNode head = new TreeNode(i);
                    head.left = left;
                    result.add(head);
                }
            } else {
                for (TreeNode left : leftHeads) {
                    for (TreeNode right : rightHeads) {
                        TreeNode head = new TreeNode(i);
                        head.left = left;
                        head.right = right;
                        result.add(head);
                    }
                }
            }
        }
        return result;
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
        if (size1 + size2 != s3.length()) {
            return false;
        }
        boolean[][] d = new boolean[size1 + 1][size2 + 1];
        for (int i = 0; i <= size2; i++) {
            d[0][i] = i == 0 || (d[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1));
        }
        for (int i = 0; i <= size1; i++) {
            d[i][0] = i == 0 || (d[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1));
        }
        for (int i = 1; i <= size1; i++) {
            for (int j = 1; j <= size2; j++) {
                d[i][j] = (d[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (d[i][j - 1] && s2
                        .charAt(j - 1) == s3.charAt(i + j - 1));
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

        List<Integer> inorder = new ArrayList<Integer>();
        _isValidBST(root, inorder);
        for (int i = 1; i < inorder.size(); i++) {
            if (inorder.get(i) <= inorder.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void _isValidBST(TreeNode root, List<Integer> inorder) {

        if (root == null) {
            return;
        }
        _isValidBST(root.left, inorder);
        inorder.add(root.val);
        _isValidBST(root.right, inorder);
    }


    /**
     * 99. Recover Binary Search Tree
     * Two elements of a binary search tree (BST) are swapped by mistake.
     * Recover the tree without changing its structure.
     * Note:
     * A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
     */


    public static void recoverTree(TreeNode root) {

        if (root == null) {
            return;
        }

        TreeNode first = null;
        TreeNode second = null;
        TreeNode node = root;
        TreeNode pre = null;
        TreeNode parent = null;
        while (node != null) {
            if (node.left == null) {
                if (parent != null && parent.val > node.val) {
                    if (first == null) {
                        first = parent;
                    }
                    second = node;
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
                    if (parent != null && parent.val > node.val) {
                        if (first == null) {
                            first = parent;
                        }
                        second = node;
                    }
                    parent = node;
                    pre.right = null;
                    node = node.right;
                }
            }
        }
        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }

    public static List<Integer> ConstantMemoryInorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<Integer>();
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
                    result.add(pre.val);
                    node = node.right;
                }
            }
        }
        return result;
    }

    //    public static void _recoverTree(TreeNode root, List<Integer> inorder, HashMap<Integer, TreeNode> map) {
    //
    //        if (root == null) {
    //            return;
    //        }
    //        _recoverTree(root.left, inorder, map);
    //        inorder.add(root.val);
    //        map.put(root.val, root);
    //        _recoverTree(root.right, inorder, map);
    //    }


    /**
     * 100. Same Tree
     * Given two binary trees, write a function to check if they are equal or not.
     * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if ((p == null) || (q == null) || p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
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
        if (root == null) {
            return true;
        }
        return _isSymmetric(root.left, root.right);
    }

    public static boolean _isSymmetric(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if ((node1 == null) || (node2 == null) || node1.val != node2.val) {
            return false;
        }
        return _isSymmetric(node1.left, node2.right) && _isSymmetric(node1.right, node2.left);
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
        List<Integer> tempList = new ArrayList<Integer>();
        List<TreeNode> list = new ArrayList<TreeNode>();
        list.add(root);
        TreeNode newMark = root;
        boolean markNew = false;
        while (!list.isEmpty()) {
            TreeNode current = list.remove(0);
            if (current.left != null) {
                list.add(current.left);
            }
            if (current.right != null) {
                list.add(current.right);
            }

            if (current == newMark) {
                if (current == root) {
                    tempList.add(current.val);
                } else {
                    result.add(tempList);
                    tempList = new ArrayList<Integer>();
                }
                markNew = true;
            }

            if (current != root) {
                tempList.add(current.val);
            }

            if (markNew) {
                if (current.left != null) {
                    newMark = current.left;
                    markNew = false;
                } else if (current.right != null) {
                    newMark = current.right;
                    markNew = false;
                }
            }
        }
        if (!tempList.isEmpty()) {
            result.add(tempList);
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
        List<Integer> tempList = new ArrayList<Integer>();
        List<TreeNode> list = new ArrayList<TreeNode>();
        list.add(root);
        TreeNode newMark = root;
        boolean markNew = false;
        int row = 0;
        while (!list.isEmpty()) {
            TreeNode current = list.remove(0);
            if (current.left != null) {
                list.add(current.left);
            }
            if (current.right != null) {
                list.add(current.right);
            }

            if (current == newMark) {
                row++;
                if (current == root) {
                    tempList.add(current.val);
                } else {
                    result.add(tempList);
                    tempList = new ArrayList<Integer>();
                }
                markNew = true;
            }

            if (current != root) {
                if (row % 2 == 0) {
                    tempList.add(0, current.val);
                } else {
                    tempList.add(current.val);
                }
            }

            if (markNew) {
                if (current.left != null) {
                    newMark = current.left;
                    markNew = false;
                } else if (current.right != null) {
                    newMark = current.right;
                    markNew = false;
                }
            }
        }
        if (!tempList.isEmpty()) {
            result.add(tempList);
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

        return _maxDepth(root, 0);
    }

    public static int _maxDepth(TreeNode root, Integer level) {

        if (root == null) {
            return level;
        }
        return Math.max(_maxDepth(root.left, level + 1), _maxDepth(root.right, level + 1));
    }

    /**
     * 105. Construct Binary Tree from Preorder and Inorder Traversal
     * Given preorder and inorder traversal of a tree, construct the binary tree.
     * Note:
     * You may assume that duplicates do not exist in the tree.
     */
    public static TreeNode buildTree1(int[] preorder, int[] inorder) {

        if (preorder.length != inorder.length || preorder.length == 0) {
            return null;
        }
        return _buildTree1(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public static TreeNode _buildTree1(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart,
                                       int inEnd) {

        if (preStart > preEnd) {
            return null;
        }
        int size = preEnd - preStart + 1;
        if (size == 1) {
            return new TreeNode(preorder[preStart]);
        }
        TreeNode head = new TreeNode(preorder[preStart]);
        int headIndex = inStart;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == preorder[preStart]) {
                headIndex = i;
                break;
            }
        }
        head.left = _buildTree1(preorder, preStart + 1, preStart + headIndex - inStart, inorder, inStart,
                                headIndex - 1);
        head.right = _buildTree1(preorder, preStart + headIndex - inStart + 1, preEnd, inorder, headIndex + 1, inEnd);
        return head;
    }

    /**
     * 106. Construct Binary Tree from Inorder and Postorder
     * Given inorder and postorder traversal of a tree, construct the binary tree.
     * Note:
     * You may assume that duplicates do not exist in the tree.
     */
    public static TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (inorder.length != postorder.length || postorder.length == 0) {
            return null;
        }
        return _buildTree2(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public static TreeNode _buildTree2(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart,
                                       int postEnd) {
        if (postStart > postEnd) {
            return null;
        }
        int size = postEnd - postStart + 1;
        if (size == 1) {
            return new TreeNode(postorder[postEnd]);
        }
        TreeNode head = new TreeNode(postorder[postEnd]);
        int headIndex = inStart;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == postorder[postEnd]) {
                headIndex = i;
                break;
            }
        }
        head.left = _buildTree2(inorder, inStart, headIndex - 1, postorder, postStart,
                                postStart + headIndex - inStart - 1);
        head.right = _buildTree2(inorder, headIndex + 1, inEnd, postorder, postStart + headIndex - inStart,
                                 postEnd - 1);
        return head;
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
        List<Integer> tempList = new ArrayList<Integer>();
        List<TreeNode> list = new ArrayList<TreeNode>();
        list.add(root);
        TreeNode newMark = root;
        boolean markNew = false;
        while (!list.isEmpty()) {
            TreeNode current = list.remove(0);
            if (current.left != null) {
                list.add(current.left);
            }
            if (current.right != null) {
                list.add(current.right);
            }

            if (current == newMark) {
                if (current == root) {
                    tempList.add(current.val);
                } else {
                    result.add(0, tempList);
                    tempList = new ArrayList<Integer>();
                }
                markNew = true;
            }

            if (current != root) {
                tempList.add(current.val);
            }

            if (markNew) {
                if (current.left != null) {
                    newMark = current.left;
                    markNew = false;
                } else if (current.right != null) {
                    newMark = current.right;
                    markNew = false;
                }
            }
        }
        if (!tempList.isEmpty()) {
            result.add(0, tempList);
        }
        return result;
    }

    /**
     * 108. Convert Sorted Array to Binary Search Tree
     * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
     */
    public static TreeNode sortedArrayToBST(int[] num) {
        int size = num.length;
        if (size == 0) {
            return null;
        } else if (size == 1) {
            return new TreeNode(num[0]);
        }
        return _sortedArrayToBST(num, 0, size - 1);
    }

    public static TreeNode _sortedArrayToBST(int[] num, int start, int end) {

        if (start > end) {
            return null;
        } else if (start == end) {
            return new TreeNode(num[start]);
        } else if (start + 1 == end) {
            TreeNode head = new TreeNode(num[end]);
            head.left = new TreeNode(num[start]);
            return head;
        }
        int mid = (start + end) / 2;
        TreeNode head = new TreeNode(num[mid]);
        head.left = _sortedArrayToBST(num, start, mid - 1);
        head.right = _sortedArrayToBST(num, mid + 1, end);
        return head;
    }

    /**
     * 109. Convert Sorted List to Binary Search Tree
     * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
     */
    public static TreeNode sortedListToBST(ListNode head) {

        if (head == null) {
            return null;
        }
        int size = 0;
        ListNode temp = head;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        if (size == 1) {
            return new TreeNode(head.val);
        }
        return _sortedListToBST(head, size);
    }

    public static TreeNode _sortedListToBST(ListNode start, int size) {

        if (start == null || size == 0) {
            return null;
        }
        if (size == 1) {
            return new TreeNode(start.val);
        } else if (size == 2) {
            TreeNode head = new TreeNode(start.next.val);
            head.left = new TreeNode(start.val);
            return head;
        }
        int mid = (size + 1) / 2;
        ListNode root = start;
        int count = 0;
        while (root != null) {
            count++;
            if (count == mid) {
                break;
            }
            root = root.next;
        }
        TreeNode head = new TreeNode(root.val);
        head.left = _sortedListToBST(start, mid - 1);
        head.right = _sortedListToBST(root.next, size - mid);
        return head;
    }

    /**
     * 110. Balanced Binary Tree
     * Given a binary tree, determine if it is height-balanced.
     * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two
     * subtrees of every node never differ by more than 1.
     */
    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int left = maxDepth1(root.left);
        int right = maxDepth1(root.right);
        if (Math.abs(left - right) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    public static int maxDepth1(TreeNode root) {

        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return 1;
        } else if (root.left == null && root.right != null) {
            return 1 + maxDepth1(root.right);
        } else if (root.left != null && root.right == null) {
            return 1 + maxDepth1(root.left);
        } else {
            return 1 + Math.max(maxDepth1(root.left), maxDepth1(root.right));
        }
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
        } else if (root.left == null && root.right != null) {
            return 1 + minDepth(root.right);
        } else if (root.left != null && root.right == null) {
            return 1 + minDepth(root.left);
        } else {
            return 1 + Math.min(minDepth(root.left), minDepth(root.right));
        }
    }

    /**
     * 112. Path Sum
     * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values
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
        } else if (root.left == null && root.right == null) {
            return root.val == sum;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
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

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) {
            return result;
        }
        List<Integer> list = new ArrayList<Integer>();
        _pathSum(root, sum, result, list);
        return result;
    }

    public static void _pathSum(TreeNode root, int sum, List<List<Integer>> lists, List<Integer> list) {

        if (root == null) {
            return;
        } else if (root.left == null && root.right == null) {
            if (root.val == sum) {
                List<Integer> temp = new ArrayList<Integer>(list);
                temp.add(root.val);
                lists.add(temp);
                return;
            }
        }
        list.add(root.val);
        _pathSum(root.left, sum - root.val, lists, list);
        _pathSum(root.right, sum - root.val, lists, list);
        if (!list.isEmpty()) {
            list.remove(list.size() - 1);
        }
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

        if (root == null) {
            return;
        }
        _flatten(root);
    }

    public static TreeNode _flatten(TreeNode node) {

        if (node == null) {
            return null;
        } else if (node.left == null && node.right == null) {
            return node;
        }
        TreeNode left = node.left;
        TreeNode right = node.right;
        node.left = null;
        node.right = null;
        TreeNode lRight = _flatten(left);
        TreeNode rRight = _flatten(right);
        if (lRight != null) {
            lRight.left = null;
            lRight.right = right;
        }
        node.right = (left == null) ? right : left;
        return rRight == null ? lRight : rRight;
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

        int len1 = T.length();
        int len2 = S.length();
        int[][] d = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len2; i++) {
            d[0][i] = 1;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = i; j <= len2; j++) {
                if (i == j) {
                    d[i][j] = T.charAt(i - 1) == S.charAt(j - 1) ? d[i - 1][j - 1] : 0;
                } else {
                    d[i][j] = d[i][j - 1] + (T.charAt(i - 1) == S.charAt(j - 1) ? d[i - 1][j - 1] : 0);
                }
            }
        }
        return d[len1][len2];
    }

    /**
     * 116. Populating Next Right Pointers in Each Node
     * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer
     * should be set to NULL.
     * Initially, all next pointers are set to NULL.
     * Note:
     * You may only use constant extra space.
     * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two
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

        if (root == null) {
            return;
        }
        _connect1(root, null);
    }

    public static void _connect1(TreeLinkNode node, TreeLinkNode left) {

        if (node == null || node.left == null || node.right == null) {
            return;
        }
        node.left.next = node.right;
        if (left != null) {
            left.right.next = node.left;
        }
        _connect1(node.left, left == null ? null : left.right);
        _connect1(node.right, node.left);
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

        if (root == null) {
            return;
        }
        TreeLinkNode p = root.next;
        while (p != null) {
            if (p.left != null) {
                p = p.left;
                break;
            }
            if (p.right != null) {
                p = p.right;
                break;
            }
            p = p.next;
        }
        if (root.right != null) {
            root.right.next = p;
        }
        if (root.left != null) {
            root.left.next = root.right == null ? p : root.right;
        }
        connect(root.right);
        connect(root.left);

        //        if (root == null) {
        //            return;
        //        }
        //        List<TreeLinkNode> list = new ArrayList<TreeLinkNode>();
        //        list.add(root);
        //        TreeLinkNode newMark = root;
        //        TreeLinkNode prev = null;
        //        TreeLinkNode current = null;
        //        boolean markNew = false;
        //        while (!list.isEmpty()) {
        //            current = list.remove(0);
        //            if (current.left != null) {
        //                list.add(current.left);
        //            }
        //            if (current.right != null) {
        //                list.add(current.right);
        //            }
        //            if (current == newMark) {
        //                prev = current;
        //                markNew = true;
        //            } else {
        //                if (prev != null) {
        //                    prev.next = current;
        //                }
        //                prev = prev.next;
        //            }
        //            if (markNew) {
        //                if (current.left != null) {
        //                    newMark = current.left;
        //                    markNew = false;
        //                } else if (current.right != null) {
        //                    newMark = current.right;
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
            List<Integer> list = new ArrayList<Integer>();
            if (result.isEmpty()) {
                list.add(1);
            } else {
                List<Integer> last = result.get(result.size() - 1);
                for (int j = 0; j <= last.size(); j++) {
                    if (j == 0) {
                        list.add(1);
                    } else if (j == last.size()) {
                        list.add(last.get(last.size() - 1));
                    } else {
                        list.add(last.get(j - 1) + last.get(j));
                    }
                }
            }
            result.add(list);
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

        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i <= rowIndex; i++) {
            if (result.isEmpty()) {
                result.add(1);
            } else {
                List<Integer> last = result;
                result = new ArrayList<Integer>();
                for (int j = 0; j <= last.size(); j++) {
                    if (j == 0) {
                        result.add(1);
                    } else if (j == last.size()) {
                        result.add(last.get(last.size() - 1));
                    } else {
                        result.add(last.get(j - 1) + last.get(j));
                    }
                }
            }
        }
        return result;
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

        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        List<Integer> distance = new ArrayList<Integer>();
        for (int i = 0; i < triangle.size(); i++) {
            List<Integer> row = triangle.get(i);
            if (distance.isEmpty()) {
                distance.addAll(row);
            } else {
                int preSize = distance.size();
                for (int j = 0; j < row.size(); j++) {
                    int parentDistance = j == 0 ? distance.get(j) : (j == row.size() - 1 ? distance.get(j - 1) : Math
                            .min(distance.get(j - 1), distance.get(j)));
                    distance.add(row.get(j) + parentDistance);
                }
                for (int j = 0; j < preSize && !distance.isEmpty(); j++) {
                    distance.remove(0);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < distance.size(); i++) {
            min = Math.min(min, distance.get(i));
        }
        return min;
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
        int max = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }

    /**
     * 122. Best Time to Buy and Sell Stock II
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one
     * and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the
     * same
     * time (ie, you must sell the stock before you buy again).
     */
    public static int maxProfit2(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int max = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < prices.length; i++) {
            if (stack.isEmpty()) {
                stack.push(prices[i]);
            } else if (stack.peek() <= prices[i]) {
                max += (prices[i] - stack.pop());
                stack.push(prices[i]);
            } else {
                stack.pop();
                stack.push(prices[i]);
            }
        }
        return max;
    }

    /**
     * 123. Best Time to Buy and Sell Stock III
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * Design an algorithm to find the maximum profit. You may complete at most two transactions.
     * Note:
     * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
     */
    public static int maxProfit3(int[] prices) {

        int size = prices.length;
        if (size <= 1) {
            return 0;
        }
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < prices.length; i++) {
            if (list.isEmpty()) {
                list.add(prices[i]);
            } else if (list.get(list.size() - 1) <= prices[i]) {
                if (list.size() % 2 == 0) {
                    list.remove(list.size() - 1);
                    list.add(prices[i]);
                } else {
                    list.add(prices[i]);
                }
            } else {
                if (list.size() % 2 == 0) {
                    list.add(prices[i]);
                } else {
                    list.remove(list.size() - 1);
                    list.add(prices[i]);
                }
            }
        }
        if (list.size() <= 1) {
            return 0;
        } else if (list.size() == 2) {
            return list.get(1) - list.get(0);
        }
        int maxOneTrade = 0;
        int maxTwoTrade = 0;

        int minLeft = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            int maxLeftProfit = list.get(i) - minLeft;
            int maxRightProfit = 0;
            maxOneTrade = Math.max(maxOneTrade, maxLeftProfit);

            if (i < list.size() - 1) {
                int minRight = list.get(i + 1);
                for (int j = i + 1; j < list.size(); j++) {
                    maxRightProfit = Math.max(maxRightProfit, list.get(j) - minRight);
                    minRight = Math.min(minRight, list.get(j));
                }
            }
            maxTwoTrade = Math.max(maxTwoTrade, maxLeftProfit + maxRightProfit);
            minLeft = Math.min(minLeft, list.get(i));
        }
        return Math.max(maxOneTrade, maxTwoTrade);
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
    private static int iMaxPathSum = 0;

    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        _maxPathSum(root);
        return iMaxPathSum;
    }

    public static int _maxPathSum(TreeNode node) {

        if (node == null) {
            return 0;
        }
        int left = Math.max(0, _maxPathSum(node.left));
        int right = Math.max(0, _maxPathSum(node.right));
        iMaxPathSum = Math.max(iMaxPathSum, left + right + node.val);
        return left + right + node.val;
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

        int len = s.length();
        if (len <= 1) {
            return true;
        }
        int index1 = 0;
        int index2 = len - 1;
        while (index1 < index2) {
            char c1 = s.charAt(index1);
            char c2 = s.charAt(index2);
            if (!((c1 >= 'a' && c1 <= 'z') || (c1 >= 'A' && c1 <= 'Z') || (c1 >= '0' && c1 <= '9'))) {
                index1++;
            } else if (!((c2 >= 'a' && c2 <= 'z') || (c2 >= 'A' && c2 <= 'Z') || (c2 >= '0' && c2 <= '9'))) {
                index2--;
            } else if (!isSameCharIgnoreCase(c1, c2)) {
                return false;
            } else {
                index1++;
                index2--;
            }
        }
        return true;
    }

    public static boolean isSameCharIgnoreCase(char c1, char c2) {
        if (c1 == c2) {
            return true;
        }
        int a1 = (c1 >= 'a' && c1 <= 'z') ? ((int) c1) : ((int) c1 - (int) 'A' + (int) 'a');
        int a2 = (c2 >= 'a' && c2 <= 'z') ? ((int) c2) : ((int) c2 - (int) 'A' + (int) 'a');
        return a1 == a2;
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

    public static class Node {
        String val;
        Set<Node> next = new HashSet<Node>();

        public Node(String val) {
            this.val = val;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Node node = (Node) o;

            if (val != null ? !val.equals(node.val) : node.val != null) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            return val != null ? val.hashCode() : 0;
        }
    }

    public static List<List<String>> findLadders(String start, String end, Set<String> dict) {

        List<List<String>> result = new ArrayList<List<String>>();
        List<String> d = new ArrayList<String>(dict);
        d.remove(start);
        if (!d.contains(end)) {
            d.add(end);
        }
        HashSet<String> lastRow = new HashSet<String>();
        lastRow.add(start);
        result.add(new ArrayList<String>(lastRow));
        while (!lastRow.contains(end)) {
            HashSet<String> newRow = new HashSet<String>();
            for (String elem : lastRow) {
                for (int i = 0; i < d.size(); i++) {
                    if (oneChange(d.get(i), elem)) {
                        newRow.add(d.get(i));
                        int size = result.size();
                        for (int k = 0; k < size; k++) {
                            if (result.get(k).get(result.get(k).size() - 1).equals(elem)) {
                                List<String> list = new ArrayList<String>(result.get(k));
                                list.add(d.get(i));
                                result.add(list);
                            }
                        }
                    }
                }
            }
            if (newRow.isEmpty()) {
                return new ArrayList<List<String>>();
            }
            for (String newRowElem : newRow) {
                d.remove(newRowElem);
            }
            lastRow = newRow;
        }
        List<List<String>> _result = new ArrayList<List<String>>();
        for (int i = 0; i < result.size(); i++) {
            if (!result.get(i).get(result.get(i).size() - 1).equals(end)) {
                _result.add(result.get(i));
            }
        }
        return _result;

        //        Map<String, Node> d = new HashMap<String, Node>();
        //        for (String _dict : dict) {
        //            d.put(_dict, new Node(_dict));
        //        }
        //        if (d.containsKey(start)) {
        //            d.remove(start);
        //        }
        //        if (!d.containsKey(end)) {
        //            d.put(end, new Node(end));
        //        }
        //        Node head = new Node(start);
        //        Node last = d.get(end);
        //        HashSet<Node> level = new HashSet<Node>();
        //        level.add(head);
        //
        //        while (!level.contains(last)) {
        //            HashSet<Node> newRow = new HashSet<Node>();
        //            for (String key : d.keySet()) {
        //                for (Node elem : level) {
        //                    if (oneChange(key, elem.val)) {
        //                        elem.next.add(d.get(key));
        //                        newRow.add(d.get(key));
        //                    }
        //                }
        //            }
        //            if (newRow.isEmpty()) {
        //                return new ArrayList<List<String>>();
        //            }
        //            for (Node elem : newRow) {
        //                d.remove(elem.val);
        //            }
        //            level = newRow;
        //        }
        //        List<List<String>> result = new ArrayList<List<String>>();
        //        DFS(head, end, result, new ArrayList<String>());
        //        return result;
    }

    public static void DFS(Node node, String target, List<List<String>> result, List<String> current) {

        if (node == null) {
            return;
        }
        if (node.val.equals(target)) {
            List<String> list = new ArrayList<String>(current);
            list.add(target);
            result.add(list);
        } else {
            current.add(node.val);
            for (Node n : node.next) {
                DFS(n, target, result, current);
            }
            current.remove(node.val);
        }
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

        List<String> d = new ArrayList<String>(dict);
        List<List<String>> levels = new ArrayList<List<String>>();
        List<String> root = new ArrayList<String>();
        root.add(start);
        levels.add(root);
        d.remove(start);
        if (!d.contains(end)) {
            d.add(end);
        }
        while (!levels.get(levels.size() - 1).contains(end)) {
            List<String> lastRow = levels.get(levels.size() - 1);
            List<String> newRow = new ArrayList<String>();
            for (String elem : lastRow) {
                for (int i = 0; i < d.size(); i++) {
                    if (oneChange(d.get(i), elem)) {
                        newRow.add(d.get(i));
                        d.remove(i);
                        i--;
                    }
                }
            }
            if (newRow.isEmpty()) {
                return 0;
            }
            levels.add(newRow);
        }
        return levels.size();
    }

    public static boolean oneChange(String w1, String w2) {
        if (w1.length() != w2.length()) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < w1.length(); i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
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
    public static int longestConsecutive(int[] num) {

        Map<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
        Map<Integer, Integer> distance = new HashMap<Integer, Integer>();
        for (int n : num) {
            visited.put(n, false);
            distance.put(n, 1);
        }
        for (int n : num) {
            if (!visited.get(n)) {
                _longestConsecutive(visited, distance, n);
            }
        }
        int max = 0;
        for (Integer key : distance.keySet()) {
            max = Math.max(max, distance.get(key));
        }
        return max;
    }

    public static int _longestConsecutive(Map<Integer, Boolean> visited, Map<Integer, Integer> distance, int num) {

        if (visited.containsKey(num)) {
            if (!visited.get(num)) {
                visited.put(num, true);
                int len = _longestConsecutive(visited, distance, num - 1);
                distance.put(num, len + 1);
            }
            return distance.get(num);
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
        int[] pathSum = new int[1];
        pathSum[0] = 0;
        int[] totalSum = new int[1];
        totalSum[0] = 0;
        _sumNumbers(root, pathSum, totalSum);
        return totalSum[0];
    }

    public static void _sumNumbers(TreeNode node, int[] pathSum, int[] totalSum) {

        if (node == null) {
            return;
        }
        pathSum[0] = pathSum[0] * 10 + node.val;
        if (node.left == null && node.right == null) {
            totalSum[0] += pathSum[0];
        }
        _sumNumbers(node.left, pathSum, totalSum);
        _sumNumbers(node.right, pathSum, totalSum);
        pathSum[0] = (pathSum[0] - node.val) / 10;
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
        boolean[][] d = new boolean[size][size];
        for (int i = size - 1; i >= 0; i--) {
            for (int j = i; j < size; j++) {
                if (i == j) {
                    d[i][j] = true;
                } else if (i + 1 == j) {
                    d[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    d[i][j] = d[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                }
            }
        }
        return _partition(s, 0, d);
    }

    public static List<List<String>> _partition(String s, int start, boolean[][] d) {

        List<List<String>> result = new ArrayList<List<String>>();
        if (start >= s.length()) {
            return result;
        }
        if (d[start][s.length() - 1]) {
            List<String> list = new ArrayList<String>();
            list.add(s.substring(start));
            result.add(list);
        }
        for (int i = start; i < s.length(); i++) {
            if (!d[start][i]) {
                continue;
            }
            String head = s.substring(start, i + 1);
            List<List<String>> rest = _partition(s, i + 1, d);
            for (List<String> _rest : rest) {
                _rest.add(0, head);
            }
            result.addAll(rest);
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
        if (size <= 1) {
            return 0;
        }
        int[] cuts = new int[size];
        boolean[][] d = new boolean[size][size];
        for (int i = size - 1; i >= 0; i--) {
            cuts[i] = i;
            for (int j = i; j < size; j++) {
                if (i == j) {
                    d[i][j] = true;
                } else if (i + 1 == j) {
                    d[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    d[i][j] = d[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                }
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= i; j++) {
                if (d[j][i]) {
                    if (j == 0) {
                        cuts[i] = 0;
                    } else {
                        cuts[i] = Math.min(cuts[i], cuts[j - 1] + 1);
                    }
                }
            }
        }
        return cuts[size - 1];
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

        Map<Integer, UndirectedGraphNode> visited = new HashMap<Integer, UndirectedGraphNode>();
        if (node == null) {
            return null;
        }
        return _cloneGraph(node, visited);
    }

    public static UndirectedGraphNode _cloneGraph(UndirectedGraphNode node, Map<Integer, UndirectedGraphNode> visited) {
        if (node == null) {
            return null;
        }
        int hashCode = node.hashCode();
        if (visited.containsKey(hashCode)) {
            return visited.get(hashCode);
        } else {
            UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
            visited.put(hashCode, newNode);
            for (UndirectedGraphNode next : node.neighbors) {
                newNode.neighbors.add(_cloneGraph(next, visited));
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

        if (gas.length != cost.length || gas.length == 0) {
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
        int currentStart = maxIndex;
        for (int i = 0; i < size; i++) {
            int tank = gas[currentStart];
            int last = currentStart;
            int index = last == size - 1 ? 0 : last + 1;
            for (int j = 1; j <= size; j++) {
                tank -= cost[last];
                if (tank < 0) {
                    break;
                }
                tank += gas[index];
                last = index;
                index = last == size - 1 ? 0 : last + 1;
            }
            if (tank >= 0) {
                return currentStart;
            }
            currentStart = currentStart == size - 1 ? 0 : currentStart + 1;
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

        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int length = ratings.length;
        int[] candies = new int[length];
        candies[0] = 1;
        for (int i = 1; i < length; i++) {
            candies[i] = 1;
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            } else if (ratings[i] < ratings[i - 1]) {
                candies[i] = candies[i - 1] - 1;
            }
            if (i < length - 1 && ratings[i] < ratings[i - 1] && ratings[i] <= ratings[i + 1]) {
                ReAdjustCandy(ratings, candies, i);
            } else if (i == length - 1 && ratings[i] < ratings[i - 1]) {
                ReAdjustCandy(ratings, candies, i);
            }
        }
        int total = 0;
        for (int i = 0; i < length; i++) {
            total += candies[i];
        }
        return total;
    }

    public static void ReAdjustCandy(int[] ratings, int[] candy, int startIndex) {
        int k = startIndex;
        int diff = 1 - candy[k];
        while (k > 0 && ratings[k - 1] > ratings[k]) {
            candy[k] = candy[k] + diff;
            k--;
        }
        if (diff > 0) {
            candy[k] += diff;
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
        for (int aA : A) {
            result = (~(result & aA)) & (result | aA);
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
        int n = A.length;
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (((A[j] >> i) & 1) != 0) {
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

        if (head == null) {
            return null;
        }
        HashMap<Integer, RandomListNode> copied = new HashMap<Integer, RandomListNode>();
        RandomListNode oriTemp = head;
        RandomListNode newHead = null;
        RandomListNode newTemp = null;
        while (oriTemp != null) {
            if (!copied.containsKey(oriTemp.hashCode())) {
                RandomListNode newNode = new RandomListNode(oriTemp.label);
                copied.put(oriTemp.hashCode(), newNode);
            }
            if (oriTemp.random != null) {
                if (copied.containsKey(oriTemp.random.hashCode())) {
                    copied.get(oriTemp.hashCode()).random = copied.get(oriTemp.random.hashCode());
                } else {
                    copied.get(oriTemp.hashCode()).random = new RandomListNode(oriTemp.random.label);
                    copied.put(oriTemp.random.hashCode(), copied.get(oriTemp.hashCode()).random);
                }
            }
            if (newHead == null) {
                newHead = copied.get(oriTemp.hashCode());
                newTemp = newHead;
            } else {
                newTemp.next = copied.get(oriTemp.hashCode());
                newTemp = newTemp.next;
            }
            oriTemp = oriTemp.next;
        }
        return newHead;
    }

    public static RandomListNode _copyRandomList(RandomListNode node, HashMap<Integer, RandomListNode> copied) {

        if (node == null) {
            return null;
        }
        int hash = node.hashCode();
        if (copied.containsKey(hash)) {
            return copied.get(hash);
        }
        RandomListNode newNode = new RandomListNode(node.label);
        copied.put(hash, newNode);
        newNode.next = _copyRandomList(node.next, copied);
        newNode.random = _copyRandomList(node.random, copied);
        return newNode;
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

        if (s.length() == 0) {
            return true;
        }
        int size = s.length();
        boolean[][] d = new boolean[size][size];
        for (int i = size; i >= 0; i--) {
            for (int j = i; j < size; j++) {
                d[i][j] = dict.contains(s.substring(i, j + 1));
                if (!d[i][j]) {
                    for (int k = i; k < j; k++) {
                        if (d[i][k] && d[k + 1][j]) {
                            d[i][j] = true;
                        }
                    }
                }
            }
        }
        return d[0][size - 1];
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

        if (s.length() == 0) {
            return new ArrayList<String>();
        }
        int size = s.length();
        boolean[][] d = new boolean[size][size];
        for (int i = size; i >= 0; i--) {
            for (int j = i; j < size; j++) {
                d[i][j] = dict.contains(s.substring(i, j + 1));
                if (!d[i][j]) {
                    for (int k = i; k < j; k++) {
                        if (d[i][k] && d[k + 1][j]) {
                            d[i][j] = true;
                        }
                    }
                }
            }
        }

        List<List<String>> lists = _wordBreak2(s, 0, size - 1, dict, d);
        List<String> result = new ArrayList<String>();
        for (List<String> list : lists) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                builder.append(list.get(i));
                if (i < list.size() - 1) {
                    builder.append(" ");
                }
            }
            result.add(builder.toString());
        }
        return result;
    }

    public static List<List<String>> _wordBreak2(String s, int start, int end, Set<String> dict, boolean[][] d) {

        if (end < start) {
            return new ArrayList<List<String>>();
        }
        List<List<String>> result = new ArrayList<List<String>>();
        for (int i = start + 1; i <= end + 1; i++) {
            String head = s.substring(start, i);
            if (dict.contains(head) && (i == end + 1 || d[i][end])) {
                if (i == s.length()) {
                    List<String> list = new ArrayList<String>();
                    list.add(head);
                    result.add(list);
                } else {
                    List<List<String>> rest = _wordBreak2(s, i, end, dict, d);
                    for (List<String> _rest : rest) {
                        _rest.add(0, head);
                        result.add(_rest);
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
        ListNode index1 = head;
        ListNode index2 = head.next;
        if (index2 == null) {
            return false;
        }
        index2 = index2.next;
        while (index1 != null && index2 != null && index1 != index2) {
            index1 = index1.next;
            index2 = index2.next;
            if (index2 == null) {
                return false;
            }
            index2 = index2.next;
        }
        if (index1 == index2 && index1 != null) {
            return true;
        }
        return false;
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
        ListNode index1 = head;
        ListNode index2 = head;
        boolean first = true;
        while (index1 != null && index2 != null && (index1 != index2 || first)) {
            index1 = index1.next;
            index2 = index2.next;
            if (index2 == null) {
                return null;
            }
            index2 = index2.next;
            first = false;
        }
        if (index1 == index2 && index1 != null) {

            index1 = head;
            while (index1 != index2) {
                index1 = index1.next;
                index2 = index2.next;
            }
            return index1;
        }
        return null;
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

        if (head == null) {
            return head;
        }
        ListNode temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        if (count <= 2) {
            return head;
        }
        int mid = count / 2;
        ListNode rightStart = head;
        while (rightStart != null) {
            mid--;
            if (mid == 0) {
                break;
            }
            rightStart = rightStart.next;
        }
        if (count % 2 == 0) {
            temp = rightStart.next;
            rightStart.next = null;
            rightStart = temp;
            temp = null; // temp is the mid one which is left;
        } else {
            temp = rightStart.next;
            rightStart.next = null;
            rightStart = temp.next;
            temp.next = null;
        }

        ListNode left = head;
        ListNode right = _reverseLinkList(rightStart);
        ListNode middle = temp;

        while (left != null && right != null) {
            ListNode temp1 = left.next;
            ListNode temp2 = right.next;

            left.next = right;
            right.next = temp1;

            if (temp1 == null) {
                right.next = middle;
                break;
            }

            left = temp1;
            right = temp2;
        }
        return head;
    }

    public static ListNode _reverseLinkList(ListNode start) {

        if (start == null) {
            return start;
        }
        ListNode newHead = null;
        ListNode temp1 = start;
        ListNode temp2 = start.next;
        while (temp2 != null) {
            ListNode temp = temp2.next;
            temp2.next = temp1;
            temp1 = temp2;
            temp2 = temp;
            if (temp == null) {
                newHead = temp1;
            }
        }
        return newHead == null ? start : newHead;
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
                result.add(node.val);
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop().right;
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
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        TreeNode lastnodevisited = null;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                TreeNode peeknode = stack.peek();
                if (peeknode.right != null && lastnodevisited != peeknode.right) {
                    node = peeknode.right;
                } else {
                    result.add(peeknode.val);
                    lastnodevisited = stack.pop();
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

        private class DoubleLinkedNode {
            int              key;
            int              val;
            DoubleLinkedNode prev;
            DoubleLinkedNode next;

            public DoubleLinkedNode(int key, int val) {
                this.key = key;
                this.val = val;
                this.prev = null;
                this.next = null;
            }
        }

        private HashMap<Integer, DoubleLinkedNode> values = new HashMap<Integer, DoubleLinkedNode>();
        private DoubleLinkedNode                   head   = null;
        private DoubleLinkedNode                   last   = null;
        private int                                cap    = 0;

        public LRUCache(int capacity) {
            this.cap = capacity;
        }

        public int get(int key) {
            if (!values.containsKey(key)) {
                return -1;
            }
            DoubleLinkedNode target = values.get(key);
            if (target != head) {
                if (target == last) {
                    last = last.prev;
                }
                if (target.prev != null) {
                    target.prev.next = target.next;
                }
                if (target.next != null) {
                    target.next.prev = target.prev;
                }
                target.prev = null;
                target.next = head;
                head.prev = target;
                head = target;
            }
            return target.val;
        }

        public void set(int key, int value) {

            DoubleLinkedNode target = null;
            if (values.containsKey(key)) {
                target = values.get(key);
                target.val = value;
                if (target != head) {
                    if (target == last) {
                        last = last.prev;
                    }
                    if (target.prev != null) {
                        target.prev.next = target.next;
                    }
                    if (target.next != null) {
                        target.next.prev = target.prev;
                    }
                    target.prev = null;
                    target.next = head;
                    head.prev = target;
                    head = target;
                }
            } else {
                if (values.size() == cap) {
                    if (last != null) {
                        values.remove(last.key);
                        DoubleLinkedNode temp = last.prev;
                        last.prev = null;
                        last.next = null;
                        if (temp != null) {
                            temp.next = null;
                        }
                        last = temp;
                        if (values.size() == 0) {
                            head = null;
                        }
                    }
                }
                target = new DoubleLinkedNode(key, value);
                values.put(key, target);
                if (head == null) {
                    head = target;
                    last = target;
                } else {
                    head.prev = target;
                    target.next = head;
                    head = target;
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
            return head;
        }
        ListNode newHead = head;
        ListNode i = head.next;
        ListNode leftEnd = head;

        while (i != null) {

            ListNode rightStart = i.next;
            i.next = null;
            leftEnd.next = null;

            ListNode temp = newHead;
            ListNode parent = null;
            while (temp != null && temp.val < i.val) {
                parent = temp;
                temp = temp.next;
            }
            if (temp == null) {
                parent.next = i;
                leftEnd = i;
            } else {
                if (parent == null) {
                    i.next = newHead;
                    newHead = i;
                } else {
                    i.next = temp;
                    parent.next = i;
                }
            }
            leftEnd.next = rightStart;
            i = rightStart;
        }
        return newHead;
    }

    /**
     * 148. Sort List
     * Sort a linked list in O(n log n) time using constant space complexity.
     */
    public static ListNode sortList(ListNode head) {

        if (head == null) {
            return head;
        }
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        if (count == 1) {
            return head;
        }
        int midIndex = count / 2;
        temp = head;
        while (temp != null) {
            midIndex--;
            if (midIndex == 0) {
                break;
            }
            temp = temp.next;
        }
        ListNode mid = temp;

        ListNode rightStart = mid.next;
        mid.next = null;

        ListNode newHead = null;
        temp = null;
        ListNode temp1 = sortList(head);
        ListNode temp2 = sortList(rightStart);

        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                if (newHead == null) {
                    newHead = temp1;
                    temp = newHead;
                } else {
                    temp.next = temp1;
                    temp = temp.next;
                }
                temp1 = temp1.next;
            } else {
                if (newHead == null) {
                    newHead = temp2;
                    temp = newHead;
                } else {
                    temp.next = temp2;
                    temp = temp.next;
                }
                temp2 = temp2.next;
            }
        }
        if (temp1 != null && temp2 == null) {
            while (temp1 != null) {
                if (newHead == null) {
                    newHead = temp1;
                    temp = newHead;
                } else {
                    temp.next = temp1;
                    temp = temp.next;
                }
                temp1 = temp1.next;
            }
        } else if (temp1 == null && temp2 != null) {
            while (temp2 != null) {
                if (newHead == null) {
                    newHead = temp2;
                    temp = newHead;
                } else {
                    temp.next = temp2;
                    temp = temp.next;
                }
                temp2 = temp2.next;
            }
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
        int max = 1;
        for (int i = 0; i < points.length; i++) {
            int x1 = points[i].x;
            int y1 = points[i].y;
            int dup = 1;
            HashMap<Float, Integer> map = new HashMap<Float, Integer>();
            for (int j = i + 1; j < points.length; j++) {
                int x2 = points[j].x;
                int y2 = points[j].y;
                if (x2 == x1 && y2 == y1) {
                    dup++;
                    continue;
                }
                float key = (x1 == x2) ? Float.MAX_VALUE : (y1 == y2 ? 0 : (float) (y2 - y1) / (float) (x2 - x1));
                if (map.containsKey(key)) {
                    map.put(key, map.get(key) + 1);
                } else {
                    map.put(key, 1);
                }
            }
            if (map.isEmpty()) {
                max = Math.max(max, dup);
            } else {
                for (Float key : map.keySet()) {
                    int num = map.get(key) + dup;
                    max = Math.max(max, num);
                }
            }
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

        if (tokens.length == 0) {
            return 0;
        } else if (tokens.length == 1) {
            return Integer.parseInt(tokens[0]);
        } else if (tokens.length < 3) {
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();
        for (String token : tokens) {
            if (isSymbol(token)) {
                int b = stack.pop();
                int a = stack.pop();

                int result = 0;
                if (token.equals("+")) {
                    result = a + b;
                } else if (token.equals("-")) {
                    result = a - b;
                } else if (token.equals("*")) {
                    result = a * b;
                } else if (token.equals("/")) {
                    result = a / b;
                }
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    public static boolean isSymbol(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
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

        Stack<String> stack = new Stack<String>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= s.length(); i++) {
            char c = i == s.length() ? ' ' : s.charAt(i);
            if (c == ' ') {
                if (builder.length() > 0) {
                    stack.push(builder.toString());
                    builder.delete(0, builder.length());
                }
            } else {
                builder.append(c);
            }
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
            if (!stack.isEmpty()) {
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

        int len = A.length;
        if (len == 0) {
            return 0;
        }
        int result = A[0];
        int postive = Math.max(0, A[0]);
        int negative = Math.min(0, A[0]);
        for (int i = 1; i < len; i++) {
            if (A[i] > 0) {
                postive = Math.max(1, postive) * A[i];
                negative = negative * A[i];
            } else if (A[i] < 0) {
                int prev_max = postive;
                postive = negative * A[i];
                negative = Math.min(A[i], prev_max * A[i]);
            } else {
                postive = 0;
                negative = 0;
            }
            result = Math.max(result, postive);
        }
        return result;
    }

    /**
     * 153. Find Minimum in Rotated Sorted Array
     * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
     * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2). 7 0 1 2 4 5 6
     * Find the minimum element.
     * You may assume no duplicate exists in the array.
     */
    public static int findMin1(int[] num) {

        if (num.length == 0) {
            return Integer.MAX_VALUE;
        }
        return _findMin1(num, 0, num.length - 1);
    }

    public static int _findMin1(int[] num, int start, int end) {

        if (start > end) {
            return Integer.MAX_VALUE;
        } else if (start == end) {
            return num[start];
        } else if (start + 1 == end) {
            return Math.min(num[start], num[end]);
        } else {
            int mid = (start + end) / 2;
            if (num[mid] > num[start]) {
                if (num[start] > num[end]) {
                    return _findMin1(num, mid + 1, end);
                } else {
                    return num[start];
                }
            } else {
                return _findMin1(num, start, mid);
            }
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
        if (num.length == 0) {
            return Integer.MAX_VALUE;
        }
        return _findMin2(num, 0, num.length - 1);
    }

    public static int _findMin2(int[] num, int start, int end) {

        if (start > end) {
            return Integer.MAX_VALUE;
        } else if (start == end) {
            return num[start];
        } else if (start + 1 == end) {
            return Math.min(num[start], num[end]);
        } else if (start + 2 == end) {
            return Math.min(num[start], Math.min(num[start + 1], num[end]));
        }
        int mid = (start + end) / 2;
        int left = mid - 1;
        int right = mid + 1;
        while (left > start && num[left] == num[mid]) {
            left--;
        }
        while (right < end && num[right] == num[mid]) {
            right++;
        }
        if (num[left] >= num[start]) {
            if (num[start] >= num[end]) {
                return Math.min(_findMin2(num, right, end), num[mid]);
            } else {
                return num[start];
            }
        } else {
            if (num[start] >= num[end]) {
                return Math.min(_findMin2(num, start, left), num[mid]);
            } else {
                return num[start];
            }
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

        private class DoubleLinkedListNode {
            int val;
            DoubleLinkedListNode prev = null;
            DoubleLinkedListNode next = null;

            public DoubleLinkedListNode(int val) {
                this.val = val;
            }
        }

        DoubleLinkedListNode head = null;
        DoubleLinkedListNode min  = null;

        public void push(int x) {
            DoubleLinkedListNode node = new DoubleLinkedListNode(x);
            if (head == null) {
                head = node;
                min = new DoubleLinkedListNode(x);
            } else {
                node.next = head;
                head.prev = node;
                head = node;
                if (head.val <= min.val) {
                    DoubleLinkedListNode minNode = new DoubleLinkedListNode(x);
                    minNode.next = min;
                    min.prev = minNode;
                    min = minNode;
                }
            }
        }

        public void pop() {

            if (head == null) {
                return;
            }
            DoubleLinkedListNode popOut = head;
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            popOut.next = null;
            if (min.val == popOut.val) {
                DoubleLinkedListNode popOutMin = min;
                min = min.next;
                if (min != null) {
                    min.prev = null;
                }
                popOutMin.next = null;
            }
        }

        public int top() {
            if (head != null) {
                return head.val;
            }
            return 0;
        }

        public int getMin() {
            if (min != null) {
                return min.val;
            }
            return 0;
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


        ListNode tailA = headA;
        while (tailA != null) {
            if (tailA == headB) {
                return headB;
            }
            if (tailA.next == null) {
                break;
            }
            tailA = tailA.next;
        }
        tailA.next = headA;

        ListNode temp1 = headB;
        ListNode temp2 = headB;
        boolean first = true;
        while (temp1 != null && temp2 != null && (temp1 != temp2 || first)) {
            temp1 = temp1.next;
            if (temp2.next == null) {
                tailA.next = null;
                return null;
            }
            temp2 = temp2.next.next;
            first = false;
        }
        if (temp1 == null || temp2 == null) {
            tailA.next = null;
            return null;
        }
        first = true;
        temp1 = headB;
        while (temp1 != temp2 || first) {
            temp1 = temp1.next;
            temp2 = temp2.next;
            first = false;
        }
        tailA.next = null;
        return temp1;
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

        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < num.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else if (num[stack.peek()] < num[i]) {
                stack.pop();
                stack.push(i);
            } else if (num[stack.peek()] > num[i]) {
                return stack.peek();
            }
        }
        return stack.isEmpty() ? -1 : stack.peek();
    }

    /**
     * 164. Maximum Gap
     * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
     * Try to solve it in linear time/space.
     * Return 0 if the array contains less than 2 elements.
     * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
     */
    public static int maximumGap(int[] num) {
        int size = num.length;
        if (size < 2) {
            return 0;
        }
        int max = num[0];
        int min = num[0];
        for (int i = 1; i < size; i++) {
            max = Math.max(max, num[i]);
            min = Math.min(min, num[i]);
        }
        int bucketLen = (max - min) / size + 1;
        int numBuckets = ((max - min) / bucketLen) + 1;
        int[][] buckets = new int[numBuckets][2];
        for (int i = 0; i < numBuckets; i++) {
            buckets[i][0] = Integer.MAX_VALUE;
            buckets[i][1] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < size; i++) {
            int value = num[i];
            int iBucket = (value - min) / bucketLen;
            buckets[iBucket][0] = Math.min(value, buckets[iBucket][0]);
            buckets[iBucket][1] = Math.max(value, buckets[iBucket][1]);
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

        List<String> v1 = _compareVersion(version1);
        List<String> v2 = _compareVersion(version2);
        int size1 = v1.size();
        int size2 = v2.size();
        if (size1 < size2) {
            for (int i = 0; i < size2 - size1; i++) {
                v1.add("0");
            }
        } else if (size1 > size2) {
            for (int i = 0; i < size1 - size2; i++) {
                v2.add("0");
            }
        }
        int len = v1.size();
        for (int i = 0; i < len; i++) {
            int _v1 = Integer.parseInt(v1.get(i));
            int _v2 = Integer.parseInt(v2.get(i));
            if (_v1 > _v2) {
                return 1;
            } else if (_v1 < _v2) {
                return -1;
            }
        }
        return 0;
    }

    public static List<String> _compareVersion(String version) {
        List<String> result = new ArrayList<String>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= version.length(); i++) {
            char c = i == version.length() ? '.' : version.charAt(i);
            if (c == '.') {
                if (builder.length() > 0) {
                    result.add(builder.toString());
                    builder.delete(0, builder.length());
                }
            } else {
                builder.append(c);
            }
        }
        return result;
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
     * -----------
     * 16 6
     * 2  2
     * _16_____
     * 3|50
     * ----------
     * 20
     * 18
     * -----------
     * 2
     */
    public static String fractionToDecimal(int numerator, int denominator) {

        if (numerator == 0 || denominator == 0) {
            return "0";
        }
        boolean isNegative = false;
        long a = numerator;
        long b = denominator;
        if (a < 0) {
            a = -a;
            isNegative = true;
        }
        if (b < 0) {
            b = -b;
            isNegative = !isNegative;
        }

        List<Long> ans = new ArrayList<Long>();
        List<Long> res = new ArrayList<Long>();
        int dotIndex = -1;
        int leftBrace = -1;
        while (a > 0) {
            if (a < b) {
                ans.add(0L);
                res.add(a);
                if (dotIndex == -1) {
                    dotIndex = ans.size();
                }
                a *= 10;
                continue;
            }
            long _ans = a / b;
            a = a % b;
            ans.add(_ans);
            if (res.contains(a) && dotIndex != -1) {
                for (int i = res.size() - 1; i >= 0; i--) {
                    if (res.get(i) == a) {
                        leftBrace = i;
                        break;
                    }
                }
                a = 0;
            } else {
                res.add(a);
            }
            if (a < b && dotIndex == -1) {
                dotIndex = ans.size();
            }
            a *= 10;
        }

        StringBuilder builder = new StringBuilder();
        if (isNegative) {
            builder.append("-");
        }
        for (int i = 0; i < ans.size(); i++) {
            builder.append(ans.get(i));
            if (i + 1 == dotIndex && dotIndex != ans.size()) {
                builder.append(".");
            }
            if (i == leftBrace) {
                builder.append("(");
            }
        }
        if (leftBrace != -1) {
            builder.append(")");
        }
        return builder.toString();
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

        int size = num.length;
        if (size == 0) {
            return 0;
        } else if (size <= 2) {
            return num[0];
        }
        int half = size / 2;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
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
     * ABC = 26*26*A + 26*B + C
     * 26*0+     1 2 3 4 5 6 ... 26
     * 26*1+
     * 26*2+
     */
    public static int titleToNumber(String s) {

        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            int val = ((int) s.charAt(i)) - 64;
            total = 26 * total + val;
        }
        return total;
    }


    /**
     * 172. Factorial Trailing Zeroes
     * Given an integer n, return the number of trailing zeroes in n!.
     * Note: Your solution should be in logarithmic time complexity.
     */
    public static int trailingZeroes(int n) {

        double num = 0;
        long pow = 5;
        do {
            num += Math.floor(n / pow);
            pow *= 5;
        } while (pow <= n);
        return (int) num;
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

        private Deque<Integer> inorder = new ArrayDeque<Integer>();

        public BSTIterator(TreeNode root) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            TreeNode temp = root;
            while (!stack.isEmpty() || temp != null) {
                if (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                } else {
                    temp = stack.pop();
                    inorder.addLast(temp.val);
                    temp = temp.right;
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
     * -2 (7)	 -3 (7)   3(4)
     * -5 (19)	 -10(15)  1(5)
     * 10 (14)	  30(24) -5(6)
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
        int[][] blood = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (dungeon[i][j] >= 0) {
                    if (i == m - 1) {
                        blood[i][j] = (j == n - 1) ? 1 : (blood[i][j + 1] <= dungeon[i][j] ? 1 : Math
                                .abs(blood[i][j + 1] - dungeon[i][j]));
                    } else if (j == n - 1) {
                        blood[i][j] = (blood[i + 1][j] <= dungeon[i][j] ? 1 : Math
                                .abs(blood[i + 1][j] - dungeon[i][j]));
                    } else {
                        int minReq = Math.min(blood[i + 1][j], blood[i][j + 1]);
                        if (minReq <= dungeon[i][j]) {
                            blood[i][j] = 1;
                        } else {
                            blood[i][j] = minReq - dungeon[i][j];
                        }
                    }
                } else {
                    if (i == m - 1) {
                        blood[i][j] = ((j == n - 1) ? 1 : blood[i][j + 1]) - dungeon[i][j];
                    } else if (j == n - 1) {
                        blood[i][j] = blood[i + 1][j] - dungeon[i][j];
                    } else {
                        blood[i][j] = Math.min(blood[i + 1][j], blood[i][j + 1]) - dungeon[i][j];
                    }
                }
            }
        }
        return blood[0][0];
    }

    /**
     * 179. Largest Number
     * Given a list of non negative integers, arrange them such that they form the largest number.
     * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
     * Note: The result may be very large, so you need to return a string instead of an integer.
     */
    public static String largestNumber(int[] num) {

        List<String> array = new ArrayList<String>();
        for (int n : num) {
            array.add(Integer.toString(n));
        }
        List<String> sorted = _largestNumberMergeSort(array, 0, array.size() - 1);
        StringBuilder builder = new StringBuilder();
        for (String elem : sorted) {
            if (builder.length() == 0 && elem.equals("0")) {
                continue;
            }
            builder.append(elem);
        }
        return builder.length() == 0 ? "0" : builder.toString();
    }

    public static List<String> _largestNumberMergeSort(List<String> num, int start, int end) {

        int size = end - start + 1;
        if (size <= 0) {
            return new ArrayList<String>();
        } else if (size == 1) {
            List<String> result = new ArrayList<String>();
            result.add(num.get(start));
            return result;
        }
        int mid = (start + end) / 2;
        return _largestNumberMerge(_largestNumberMergeSort(num, start, mid),
                                   _largestNumberMergeSort(num, mid + 1, end));
    }

    public static List<String> _largestNumberMerge(List<String> arr1, List<String> arr2) {

        int size1 = arr1.size();
        int size2 = arr2.size();
        if (size1 == 0) {
            return arr2;
        }
        if (size2 == 0) {
            return arr1;
        }

        List<String> result = new ArrayList<String>();
        int index1 = 0;
        int index2 = 0;
        while (index1 < size1 && index2 < size2) {
            if (_largestNumberCmp(arr1.get(index1), arr2.get(index2)) >= 0) {
                result.add(arr1.get(index1));
                index1++;
            } else {
                result.add(arr2.get(index2));
                index2++;
            }
        }
        if (index1 == size1 && index2 != size2) {
            for (int i = index2; i < size2; i++) {
                result.add(arr2.get(i));
            }
        } else if (index1 != size1 && index2 == size2) {
            for (int i = index1; i < size1; i++) {
                result.add(arr1.get(i));
            }
        }
        return result;
    }

    public static int _largestNumberCmp(String num1, String num2) {

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


    public static void main(String[] args) {

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
}
