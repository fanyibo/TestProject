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

public class Utils3 {


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
                float a = (p1.x == p2.x) ? Float.MAX_VALUE : (p2.y == p1.y ? 0 : (float) (p2.y - p1.y) / (float) (p2.x
                        - p1.x));
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
        return 0;
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
    public static boolean isMatch2(String s,
                                   String p) {
        return false;
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
    public static boolean isNumber(String s) {
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
    public static void connect(Utils.TreeLinkNode root) {

    }

    public static void PRINT(Object obj) {
        System.out.println(obj.toString());
    }

    public static void main(String[] args) {

        int[] D1 = {1};
        int[] D2 = {1};
        PRINT("1 => " + findMedianSortedArrays(D1, D2));

        int[] A1 = {-1, 0, 1, 1, 3, 5, 5, 8, 9};
        int[] A2 = {-2, -1, 0};
        PRINT("1 => " + findMedianSortedArrays(A1, A2));

        int[] B1 = {-1, 0, 1, 1, 3, 5, 5, 8, 9};
        int[] B2 = {9, 10, 11};
        PRINT("5 => " + findMedianSortedArrays(B1, B2));

        int[] C1 = {-1, 0, 1, 1, 3, 5, 5, 8, 9};
        int[] C2 = {2, 3, 4, 5};
        PRINT("3 => " + findMedianSortedArrays(C1, C2));


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
