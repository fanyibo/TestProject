/*
 *    Copyright   2014
 *     Filename : ComputableArray
 *      Project : TestProject
 *   Created by : fanyibo on 12/9/14 10:40 PM
 */
package com.fanyibo.array.computable;

import com.fanyibo.util.Utils;

import java.util.Arrays;

/**
 * Computable Array
 *
 * @author fanyibo
 */
public class ComputableArray {


    private static final String MALFORMED_STRING = "Malformed String";

    private static final char   DOT               = '.';
    private static final char   POSITIVE          = '+';
    private static final char   NEGATIVE          = '-';
    private static final char   ZERO              = '0';
    private static final char   ONE               = '1';
    private static final int    DEFAULT_DOT_INDEX = -1;
    private static final char[] DEFAULT_VALUES    = {};


    /**
     * internal array to store number values
     */
    private char[] VALUE = DEFAULT_VALUES;


    /**
     * index of the dot.
     * dot is located after at element after this value.
     * Default: -1
     */
    private int _DOT_INDEX = DEFAULT_DOT_INDEX;


    public ComputableArray(String number) throws Exception {

        if (number != null && !number.isEmpty()) {

            char first = number.charAt(0);
            if ((first == POSITIVE || first == NEGATIVE) && number.length() == 1) {
                throw new Exception(MALFORMED_STRING);
            }

            String numStr = (first == NEGATIVE)
                    ?
                    number
                    :
                    (first == POSITIVE ? number : POSITIVE + number);

            boolean hasDot = false;
            int size = numStr.length();
            int i = 1;
            while (i < size) {
                if (numStr.charAt(i) == DOT) {
                    if (hasDot) {
                        throw new Exception(MALFORMED_STRING);
                    }
                    hasDot = true;
                    _DOT_INDEX = --i;
                    numStr = numStr.replaceAll("\\.", "");
                    size--;
                } else if (!Utils.isDigit(numStr.charAt(i))) {
                    throw new Exception(MALFORMED_STRING);
                }
                i++;
            }

            VALUE = numStr.toCharArray();

        } else {
            throw new Exception(MALFORMED_STRING);
        }
    }


    /**
     * Get the index of the dot.
     *
     * @return int index
     */
    public int dotIndex() {
        return _DOT_INDEX == DEFAULT_DOT_INDEX ? VALUE.length - 1 : _DOT_INDEX;
    }


    public boolean isNegative() {
        return VALUE[0] == NEGATIVE;
    }


    public int length() {
        return VALUE.length;
    }


    private char[] adjustLength(int newTotalLength, int newDotIndex) {

        char[] result = new char[newTotalLength];

        int dot = this.dotIndex();
        int j = dot;
        for (int i = newDotIndex; i > 0; --i) {
            if (j > 0) {
                result[i] = VALUE[j--];
            } else {
                result[i] = ZERO;
            }
        }
        result[0] = VALUE[0];
        j = dot;
        for (int i = newDotIndex + 1; i < newTotalLength; ++i) {
            if (++j < this.length()) {
                result[i] = VALUE[j];
            } else {
                result[i] = ZERO;
            }
        }
        return result;
    }


    @Override
    public String toString() {

        int dot = this.dotIndex();

        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < VALUE.length; ++i) {

            strBuilder.append(VALUE[i]);
            if (i == dot && dot != VALUE.length - 1) {
                strBuilder.append(DOT);
            }
        }
        return strBuilder.toString();
    }


    private char[] absPlus(char[] left, char[] right) {

        int length = left.length;
        char result[] = new char[length];

        boolean markOne = false;
        String s;
        int a, b, c;
        for (int i = length - 1; i > 0; --i) {

            a = Integer.parseInt(String.valueOf(left[i]));
            b = Integer.parseInt(String.valueOf(right[i]));
            c = a + b;
            if (markOne) {
                c++;
            }
            s = String.valueOf(c);
            if (s.length() == 1) {
                markOne = false;
                result[i] = s.charAt(0);
            } else {
                markOne = true;
                result[i] = s.charAt(1);
            }
        }

        if (markOne) {
            result[0] = ONE;
        } else {
            result[0] = ZERO;
        }

        return result;
    }


    private char[] absSubstract(char[] left, char[] right) {

        int length = left.length;

        // first, compare these two numbers
        boolean needSwitch = false;
        for (int i = 1; i < length; i++) {
            if (left[i] > right[i]) {
                needSwitch = false;
                break;
            } else if (left[i] < right[i]) {
                needSwitch = true;
                break;
            }
        }

        char[] large = needSwitch ? right : left;
        char[] small = needSwitch ? left : right;


        char result[] = new char[length];

        boolean markOne = false;
        int a, b, c;
        for (int i = length - 1; i > 0; --i) {

            a = Integer.parseInt(String.valueOf(large[i]));
            b = Integer.parseInt(String.valueOf(small[i]));
            if (markOne) {
                a--;
            }
            if (a < b) {
                markOne = true;
                c = 10 + a - b;
            } else {
                markOne = false;
                c = a - b;
            }
            result[i] = Character.forDigit(c, 10);
        }

        if (needSwitch) {
            result[0] = NEGATIVE;
        } else {
            result[0] = POSITIVE;
        }

        return result;
    }


    /**
     * All _methods don't care about positive or negative
     *
     * @param target
     * @return
     */
    public ComputableArray plus(ComputableArray target) throws Exception {

        int newDotIndex = Math.max(this.dotIndex(), target.dotIndex());
        int newLength = newDotIndex + Math.max(this.length() - this.dotIndex(),
                target.length() - target.dotIndex());
        char[] left = this.adjustLength(newLength, newDotIndex);
        char[] right = target.adjustLength(newLength, newDotIndex);

        char[] result;
        boolean appendNegative = false;

        StringBuilder strBuilder = new StringBuilder();

        if (this.isNegative()) {

            if (target.isNegative()) {
                result = this.absPlus(left, right);
                appendNegative = true;
            } else {
                result = this.absSubstract(right, left);
            }

        } else {
            if (target.isNegative()) {
                result = this.absSubstract(left, right);
            } else {
                result = this.absPlus(left, right);
            }
        }

        if (appendNegative) {
            strBuilder.append(NEGATIVE);
        }
        strBuilder.append(Arrays.copyOfRange(result, 0, newDotIndex + 1));
        strBuilder.append(".");
        strBuilder.append(Arrays.copyOfRange(result, newDotIndex + 1, newLength));

        return new ComputableArray(strBuilder.toString());
    }


    private ComputableArray multiply(ComputableArray target) {

        return null;
    }

    private ComputableArray divide(ComputableArray target) {

        return null;
    }

}

