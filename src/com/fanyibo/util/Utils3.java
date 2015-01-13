/*
 *    Copyright   2015
 *     Filename : Utils3
 *      Project : TestProject
 *   Created by : fanyibo on 1/10/15 9:22 PM
 */
package com.fanyibo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Utils3 {


    private int[] heights;

    /**
     * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
     * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
     * A partially filled sudoku which is valid.
     * Note:
     * A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
     */
    public static boolean isValidSudoku(char[][] board) {

        int size = board.length;
        if (size != 9) {
            return false;
        }
        if (board[0].length != 9) {
            return false;
        }
        int[][][] sub = new int[3][3][10];
        for (int i = 0; i < 9; i++) {
            int[] valRow = new int[10];
            int[] valCol = new int[10];
            for (int j = 0; j < 9; j++) {
                char cRow = board[i][j];
                if (cRow != '.') {
                    int c = (int) cRow - 48;
                    if (valRow[c] > 0) {
                        return false;
                    } else {
                        valRow[c] = 1;
                    }
                    int subI = i / 3;
                    int subJ = j / 3;
                    if (sub[subI][subJ] == null) {
                        sub[subI][subJ] = new int[10];
                    }
                    if (sub[subI][subJ][c] > 0) {
                        return false;
                    } else {
                        sub[subI][subJ][c] = 1;
                    }
                }
                char cCol = board[j][i];
                if (cCol != '.') {
                    int c = (int) cCol - 48;
                    if (valCol[c] > 0) {
                        return false;
                    } else {
                        valCol[c] = 1;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Maximum Subarray
     * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
     * For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
     * the contiguous subarray [4,−1,2,1] has the largest sum = 6.
     * click to show more practice.
     * More practice:
     * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach,
     * which is more subtle.
     */
    public static int maxSubArray(int[] A) {

        //        int size = A.length;
        //        if (size == 0) {
        //            return 0;
        //        }
        //        int[] d = new int[size];
        //        d[0] = A[0];
        //        int max = d[0];
        //        for (int i = 1; i < size; i++) {
        //            d[i] = Math.max(d[i - 1] + A[i], A[i]);
        //            max = Math.max(max, d[i]);
        //        }
        //        return max;

        int size = A.length;
        if (size == 0) {
            return 0;
        }
        return maxSubArray(A, 0, size - 1);
    }

    public static int maxSubArray(int[] A, int start, int end) {
        if (start > end) {
            return Integer.MIN_VALUE;
        }
        int size = end - start + 1;
        if (size == 1) {
            return A[start];
        } else if (size == 2) {
            int max = Math.max(A[start], A[end]);
            return Math.max(max, A[start] + A[end]);
        } else {
            int mid = (start + end) / 2;
            int maxL = maxSubArray(A, start, mid);
            int maxR = maxSubArray(A, mid + 1, end);
            int max = Math.max(maxL, maxR);

            int _maxL = 0;
            int _maxR = 0;
            int _sum = 0;
            for (int i = mid - 1; i >= start; i--) {
                _sum += A[i];
                if (_sum > _maxL) {
                    _maxL = _sum;
                }
            }
            _sum = 0;
            for (int i = mid + 1; i <= end; i++) {
                _sum += A[i];
                if (_sum > _maxR) {
                    _maxR = _sum;
                }
            }
            max = Math.max(max, _maxL + _maxR + A[mid]);
            return max;
        }
    }

    /**
     * Max Points on a Line
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

    public static boolean sameLine(Point p1, Point p2, Point p3) {
        return p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y) == 0;
    }

    public static int maxPoints(Point[] points) {
        int size = points.length;
        if (size == 0) {
            return 0;
        } else if (size <= 2) {
            return size;
        }
        int max = 0;
        HashMap<Float, Integer> map = new HashMap<Float, Integer>();
        for (int i = 0; i < size; i++) {
            map.clear();
            int dup = 1;
            Point p1 = points[i];
            for (int j = i + 1; j < size; j++) {
                Point p2 = points[j];
                if (p1.x == p2.x && p1.y == p2.y) {
                    dup++;
                    continue;
                }
                float a = (p1.x == p2.x) ? Float.MAX_VALUE : (p2.y == p1.y ? 0 : (float) (p2.y - p1.y) / (float) (p2
                        .x - p1.x));
                if (map.containsKey(a)) {
                    map.put(a, map.get(a) + 1);
                } else {
                    map.put(a, 1);
                }
            }
            if (map.isEmpty()) {
                max = Math.max(max, dup);
            } else {
                for (Float key : map.keySet()) {
                    int val = map.get(key);
                    if (val + dup > max) {
                        max = val + dup;
                    }
                }
            }
        }
        return max;
    }

    /**
     * Median of Two Sorted Arrays
     * There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays. The
     * overall run time complexity should be O(log (m+n)).
     */
    public static double findMedianSortedArrays(int A[], int B[]) {
        int m = A.length;
        int n = B.length;
        if ((m + n) % 2 == 0) {
            int m1 = getKthElement(A, 0, m - 1, B, 0, n - 1, (m + n) / 2);
            int m2 = getKthElement(A, 0, m - 1, B, 0, n - 1, (m + n) / 2 + 1);
            return (float) (m1 + m2) / 2.0;
        } else {
            return getKthElement(A, 0, m - 1, B, 0, n - 1, (m + n - 1) / 2 + 1);
        }
    }

    public static int getKthElement(int[] A, int a1, int a2, int[] B, int b1, int b2, int k) {

        if (a1 > a2) {
            return B[b1 + k - 1];
        }
        if (b1 > b2) {
            return A[a1 + k - 1];
        }
        if (k <= 1) {
            return Math.min(A[a1], B[b1]);
        } else if (k == 2) {
            if (A[a1] <= B[b1]) {
                if (a1 + 1 <= a2) {
                    return Math.min(A[a1 + 1], B[b1]);
                }
                return B[b1];
            } else {
                if (b1 + 1 <= b2) {
                    return Math.min(B[b1 + 1], A[a1]);
                }
                return A[a1];
            }
        }
        if (a1 == a2) {
            if (B[b1 + k - 1] <= A[a1]) {
                return B[b1 + k - 1];
            } else {
                return Math.max(B[b1 + k - 2], A[a1]);
            }
        }
        if (b1 == b2) {
            if (A[a1 + k - 1] <= B[b1]) {
                return A[a1 + k - 1];
            } else {
                return Math.max(A[a1 + k - 2], B[b1]);
            }
        }
        int midA = (a1 + a2) / 2;
        int midB = (b1 + b2) / 2;
        int mid = (midA - a1 + 1 + midB - b1);
        if (mid >= k - 1) {
            if (A[midA] >= B[midB]) {
                return getKthElement(A, a1, midA, B, b1, b2, k);
            } else {
                return getKthElement(A, a1, a2, B, b1, midB, k);
            }
        } else {
            if (A[midA] >= B[midB]) {
                return getKthElement(A, a1, a2, B, midB + 1, b2, k - (midB - b1 + 1));
            } else {
                return getKthElement(A, midA + 1, a2, B, b1, b2, k - (midA - a1 + 1));
            }
        }
    }

    /**
     * Maximal Rectangle
     * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its
     * area.
     */
    public static int maximalRectangle(char[][] matrix) {

        int rows = matrix.length;
        if (rows == 0) {
            return 0;
        }
        int cols = matrix[0].length;
        if (cols == 0) {
            return 0;
        }
        int[][] h = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] != '1') {
                    h[i][j] = 0;
                } else if (i == 0) {
                    h[i][j] = 1;
                } else {
                    h[i][j] = h[i - 1][j] + 1;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < rows; i++) {
            int[] heights = h[i];
            Stack<Integer> stack = new Stack<Integer>();
            for (int j = 0; j <= cols; j++) {
                int height = (j == cols) ? 0 : heights[j];
                if (stack.isEmpty() || height >= heights[stack.peek()]) {
                    stack.push(j);
                } else {
                    int index = stack.pop();
                    int count = j;
                    if (!stack.isEmpty()) {
                        count -= (1 + stack.peek());
                    }
                    int area = count * heights[index];
                    max = Math.max(max, area);
                    j--;
                }
            }
        }

        return max;
    }

    /**
     * Given a list of non negative integers, arrange them such that they form the largest number.
     * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
     * Note: The result may be very large, so you need to return a string instead of an integer.
     */
    public static String largestNumber(int[] num) {

        //        HashMap<Integer, List<String>> map = new HashMap<Integer, List<String>>();
        //        for (int i = 0; i < num.length; i++) {
        //            int number = num[i];
        //            while (number / 10 > 0) {
        //                number /= 10;
        //            }
        //            if (map.containsKey(number)) {
        //                map.get(number).add(String.valueOf(num[i]));
        //            } else {
        //                List<String> list = new ArrayList<String>();
        //                list.add(String.valueOf(num[i]));
        //                map.put(number, list);
        //            }
        //        }
        //        StringBuilder builder = new StringBuilder();
        //        for (int i = 9; i >= 0; i--) {
        //            if (map.containsKey(i)) {
        //                List<String> lists = map.get(i);
        //
        //            }
        //        }
        //        return builder.toString();


        String[] strArr = new String[num.length];
        for (int i = 0; i < num.length; i++) {
            strArr[i] = Integer.toString(num[i]);
        }
        strArr = mergeSort(strArr, 0, strArr.length - 1);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            if (builder.length() == 0 && strArr[i].charAt(0) == '0') {
                continue;
            }
            builder.append(strArr[i]);
        }
        return builder.length() == 0 ? "0" : builder.toString();
    }

    public static int compare(String str1, String str2) {

        String a = str1 + str2;
        String b = str2 + str1;
        int size = str1.length() + str2.length();
        for (int i = 0; i < size; i++) {
            int _a = a.charAt(i);
            int _b = b.charAt(i);
            if (_a > _b) {
                return 1;
            } else if (_a < _b) {
                return -1;
            }
        }
        return 0;
    }

    public static String[] mergeSort(String[] arr, int start, int end) {

        int size = end - start + 1;
        String[] result = new String[size];
        if (size == 0) {
            return result;
        } else if (size == 1) {
            result[0] = arr[start];
            return result;
        }
        int mid = (start + end) / 2;
        return merge(mergeSort(arr, start, mid), mergeSort(arr, mid + 1, end));
    }

    public static String[] merge(String[] arr1, String[] arr2) {

        int size1 = arr1.length;
        int size2 = arr2.length;
        String[] result = new String[size1 + size2];
        int i = 0;
        int index1 = 0;
        int index2 = 0;

        while (index1 < size1 && index2 < size2) {

            if (compare(arr1[index1], arr2[index2]) >= 0) {
                result[i++] = arr1[index1++];
            } else {
                result[i++] = arr2[index2++];
            }
        }
        if (index1 == size1 && index2 == size2) {
            return result;
        }
        int leftIndex = 0;
        String[] leftArr = null;
        if (index1 == size1 && index2 < size2) {
            leftIndex = index2;
            leftArr = arr2;
        } else {
            leftIndex = index1;
            leftArr = arr1;
        }
        for (int j = leftIndex; j < leftArr.length; j++, i++) {
            result[i] = leftArr[j];
        }
        return result;
    }

    /**
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

            if (tempMap.containsKey(_end)) {
                tempMap.put(_end, tempMap.get(_end) + 1);
            } else {
                tempMap.put(_end, 1);
            }
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
     * Valid Number
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
    public static enum InputType {
        INVALID(0),         // 0 Include: Alphas, '(', '&' ans so on
        SPACE(1),           // 1
        SIGN(2),            // 2 '+','-'
        DIGIT(3),           // 3 numbers
        DOT(4),             // 4 '.'
        EXPONENT(5);        // 5 'e' 'E'

        public int val;

        private InputType(int val) {
            this.val = val;
        }
    }

    public static boolean isNumber(String s) {

        int[][] transTable = {
                //0INVA,1SPA,2SIG,3DI,4DO,5E
                {-1, 0, 3, 1, 2, -1},// nothing input, or only space
                {-1, 8, -1, 1, 4, 5},// after digits
                {-1, -1, -1, 4, -1, -1},// only symbol before
                {-1, -1, -1, 1, 2, -1},// after symbol
                {-1, 8, -1, 4, -1, 5},// digit and symbol in front
                {-1, -1, 6, 7, -1, -1},// after exponent
                {-1, -1, -1, 7, -1, -1},// after exponent and symbol
                {-1, 8, -1, 7, -1, -1},// after exponent and digit
                {-1, 8, -1, -1, -1, -1} // space after digit
        };
        int state = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            InputType input = InputType.INVALID;
            if (c == ' ') {
                input = InputType.SPACE;
            } else if (c == '+' || c == '-') {
                input = InputType.SIGN;
            } else if (c >= '0' && c <= '9') {
                input = InputType.DIGIT;
            } else if (c == '.') {
                input = InputType.DOT;
            } else if (c == 'e' || c == 'E') {
                input = InputType.EXPONENT;
            }
            state = transTable[state][input.val];
            if (state == -1) {
                return false;
            }
        }
        return state == 1 || state == 4 || state == 7 || state == 8;

        /*
        int size = s.length();
        if (size == 0) {
            return false;
        }

        boolean hasDot = false;
        boolean hasSig = false;
        boolean started = false;
        boolean hasNum = false;
        boolean hasExp = false;
        for (int i = 0; i < size; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                if (started) {
                    while (i < size && s.charAt(i) == ' ') {
                        i++;
                    }
                    if (i != size) {
                        return false;
                    }
                }
            } else if (c == '+' || c == '-') {
                if (hasSig || hasNum || (!hasExp && hasDot)) {
                    return false;
                }
                hasSig = true;
                started = true;
            } else if (c == 'e' || c == 'E') {
                if (hasExp || !started || !hasNum || i == size - 1 ||
                        (s.charAt(i+1) != '+' &&  s.charAt(i+1) != '-' && s.charAt(i+1) != '.'
                        && !(s.charAt(i+1) >= '0' && s.charAt(i+1) <= '9'))) {
                    return false;
                }
                hasDot = true;
                hasExp = true;
                hasNum = false;
                hasSig = false;
            } else if (c == '.') {
                if (hasDot || hasExp) {
                    return false;
                }
                hasDot = true;
                started = true;
            } else if (c >= '0' && c <= '9') {
                hasNum = true;
                started = true;
            } else {
               return false;
            }
        }
        return hasNum;
        */
    }

    /**
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

        int size = s.length();
        if (size == 0) {
            return 0;
        }
        int[] d = new int[size];
        for (int i = 0; i < size; i++) {
            char c = s.charAt(i);
            if (i == 0) {
                if (c == '0') {
                    return 0;
                } else {
                    d[0] = 1;
                }
            } else {
                int current = Integer.parseInt(s.substring(i, i + 1));
                int previous = Integer.parseInt(s.substring(i - 1, i + 1));
                if (current == 0) {
                    if (s.charAt(i - 1) == '0' || s.charAt(i - 1) > '2') {
                        return 0;
                    } else {
                        d[i] = (i == 1) ? 1 : d[i - 2];
                    }
                } else if (previous > 0 && previous < 10) {
                    d[i] = (i == 1) ? 1 : d[i - 2];
                } else if (previous > 26) {
                    d[i] = d[i - 1];
                } else if (previous > 10 && previous < 27) {
                    d[i] = (i == 1) ? 2 : d[i - 2] + d[i - 1];
                }
            }
        }
        return d[size - 1];
    }

    /**
     * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following
     * operations: get and set.
     * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return
     * -1.
     * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its
     * capacity, it should invalidate the least recently used item before inserting a new item.
     */
    public static class LRUCache {

        public LRUCache(int capacity) {

        }

        public int get(int key) {
            return 0;
        }

        public void set(int key, int value) {

        }
    }

    /**
     * Regular Expression Matching
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

    public static boolean isMatch2(String s, String p) {
        return false;
    }


    /**
     * Implement int sqrt(int x).
     * Compute and return the square root of x.
     */
    public static int sqrt(int x) {
        return 0;
    }


    /**
     * Populating Next Right Pointers in Each Node II
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
    public static class TreeLinkNode {
        int          val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }

    public static void connect(TreeLinkNode root) {

    }

    public static void PRINT(Object obj) {
        System.out.println(obj.toString());
    }

    public static void main(String[] args) {

        PRINT("3 =>" + numDecodings("123"));
        PRINT("2 =>" + numDecodings("12"));
        PRINT("1 =>" + numDecodings("101010101010101010101"));


        //        Point p1 = new Point(84, 250);
        //        Point p2 = new Point(0, 0);
        //        Point p3 = new Point(1, 0);
        //        Point p4 = new Point(0, -70);
        //        Point p5 = new Point(0, -70);
        //        Point p6 = new Point(1, -1);
        //        Point p7 = new Point(21, 10);
        //        Point p8 = new Point(42, 90);
        //        Point p9 = new Point(-42, -230);
        //
        //        Point[] points = {p1, p2, p3, p4, p5, p6, p7, p8, p9};

        //        int[] D = {5, 2, 1, 2, 1, 5};
        //        System.out.println(trap(D));
        //
        //        int[] C = {5, 4, 1, 2};
        //        System.out.println(trap(C));
        //
        //        int[] A = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        //        System.out.println(trap(A));
        //
        //        int[] B = {1, 0, 2, 1, 4, 1, 3, 0, 2, 5, 3, 4};
        //        System.out.println(trap(B));
    }
}
