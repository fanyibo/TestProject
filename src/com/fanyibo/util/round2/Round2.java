package com.fanyibo.util.round2;

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

        int size1 = S.length();
        int size2 = T.length();
        if (size1 < size2 || size2 == 0) {
            return "";
        }

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < size2; i++) {
            char c = T.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        String result = "";
        int index1 = -1;
        int index2 = 0;
        int cLeft = map.size();
        Deque<Integer> queue = new ArrayDeque<Integer>();
        HashMap<Character, Integer> temp = new HashMap<Character, Integer>();
        for (; index1 < index2 && index2 < size1; ) {
            char c = S.charAt(index2);
            if (map.containsKey(c)) {
                if (queue.isEmpty() || queue.getLast() != index2) {
                    queue.addLast(index2);
                }
                if (index1 == -1) {
                    index1 = index2;
                    queue.removeFirst();
                }
                temp.put(c, (temp.containsKey(c) ? temp.get(c) : 0) + 1);
                if (map.get(c).equals(temp.get(c))) {
                    cLeft--;
                    if (cLeft == 0) {
                        String sub = S.substring(index1, index2 + 1);
                        if (result.equals("")) {
                            result = sub;
                        } else {
                            result = sub.length() < result.length() ? sub : result;
                        }
                        char first = S.charAt(index1);
                        if (queue.isEmpty()) {
                            return result;
                        }
                        index1 = queue.removeFirst();
                        temp.put(first, temp.get(first) == 0 ? 0 : temp.get(first) - 1);
                        if (temp.get(first) == 0) {
                            temp.remove(first);
                            cLeft++;
                        } else if (temp.get(first) >= map.get(first)) {
                            sub = S.substring(index1, index2 + 1);
                            result = sub.length() < result.length() ? sub : result;
                        } else {
                            cLeft++;
                        }
                        if (temp.containsKey(c)) {
                            temp.put(c, temp.get(c) == 0 ? 0 : temp.get(c) - 1);
                            cLeft++;
                        }
                    } else {
                        index2++;
                    }
                } else {
                    index2++;
                }
            } else {
                index2++;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        PRINT(minWindow("acbbaca", "aba"));
        PRINT(minWindow("aab", "aab"));
        PRINT(minWindow("adobecodebancbbcaa", "abc"));
        PRINT(minWindow("ADOBECOACBANC", "ABC"));
        PRINT(minWindow("a", "a"));
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
