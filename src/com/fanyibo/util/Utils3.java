/*
 *    Copyright   2015
 *     Filename : Utils3
 *      Project : TestProject
 *   Created by : fanyibo on 1/10/15 9:22 PM
 */
package com.fanyibo.util;

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

    public static int maxPoints(Point[] points) {
        return 0;
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
     * Median of Two Sorted Arrays
     * There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays. The
     * overall run time complexity should be O(log (m+n)).
     */
    public static double findMedianSortedArrays(int A[], int B[]) {
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


    /**
     * Median of Two Sorted Arrays
     * There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays.
     * The overall run time complexity should be O(log (m+n)).
     */
    public static double findMedianSortedArrays(int A[],
                                                int B[]) {
        return 0;
    }

    public static void PRINT(Object obj) {
        System.out.println(obj.toString());
    }

    public static void main(String[] args) {

        int[] A = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        PRINT(maxSubArray(A));

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
