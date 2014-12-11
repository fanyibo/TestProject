/*
 *    Copyright   2014
 *     Filename : Utils
 *      Project : TestProject
 *   Created by : fanyibo on 12/9/14 10:42 PM
 */
package com.fanyibo.util;

public class Utils {


    public static boolean isDigit(char c) {


        int asInt = (int) c;
        if (asInt < 48 || asInt > 57) {
            return false;
        }
        return true;
    }

    /**
     * implement atoi to convert a string to an integer.
     * Hint: Carefully consider all possible input cases.
     * If you want a challenge, please do not see below and ask yourself
     * what are the possible input cases.
     * Notes: It is intended for this problem to be specified vaguely
     * (ie, no given input specs). You are responsible to gather all
     * the input requirements up front.
     * spoilers alert... click to show requirements for atoi.
     * Requirements for atoi:
     * The function first discards as many whitespace characters as necessary until the first non-whitespace
     * character is found. Then, starting from this character, takes an optional initial plus or minus sign followed
     * by as many numerical digits as possible, and interprets them as a numerical value.
     * The string can contain additional characters after those that form the integral number, which are ignored and
     * have no effect on the behavior of this function.
     * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such
     * sequence exists because either str is empty or it contains only whitespace characters, no conversion is
     * performed.
     * If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range
     * of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
     * https://oj.leetcode.com/problems/string-to-integer-atoi/
     */
    public static int atoi(String str) {

        if (str == null || str.isEmpty()) {
            return 0;
        }
        int result = 0;
        boolean meetSpace = false;
        boolean meetValid = false;
        boolean isNegative = false;
        boolean symbol = false;
        for (char c : str.toCharArray()) {
            if (c == ' ') {
                if (meetValid) {
                    break;
                }
                if (!meetSpace) {
                    meetSpace = true;
                } else if (meetSpace && !meetValid) {

                } else {
                    break;
                }
                continue;
            }
            meetValid = true;
            int add = 0;
            if (c == '-') {
                if (symbol) {
                    break;
                }
                isNegative = true;
                symbol = true;
            } else if (c == '+') {
                if (symbol) {
                    break;
                }
                symbol = true;
            } else if (c == '0') {
                add = 0;
            } else if (c == '1') {
                add = 1;
            } else if (c == '2') {
                add = 2;
            } else if (c == '3') {
                add = 3;
            } else if (c == '4') {
                add = 4;
            } else if (c == '5') {
                add = 5;
            } else if (c == '6') {
                add = 6;
            } else if (c == '7') {
                add = 7;
            } else if (c == '8') {
                add = 8;
            } else if (c == '9') {
                add = 9;
            } else {
                return result;
            }

            if (result < (Integer.MIN_VALUE + add) / 10) {
                return Integer.MIN_VALUE;
            } else if (result > (Integer.MAX_VALUE - add) / 10) {
                return Integer.MAX_VALUE;
            }
            result = isNegative ? result * 10 - add : result * 10 + add;
        }
        return result;
    }

    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * Design an algorithm to find the maximum profit. You may complete at most two transactions.
     * Note:
     * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
     */
    public static int maxProfit(int[] prices) {

        return 0;
    }


    public static void main(String[] args) {
        System.out.println(atoi(" -0012a42"));
    }
}

