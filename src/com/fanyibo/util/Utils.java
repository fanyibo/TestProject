/*
 *    Copyright   2014
 *     Filename : Utils
 *      Project : TestProject
 *   Created by : fanyibo on 12/9/14 10:42 PM
 */
package com.fanyibo.util;

public class Utils {


    public static boolean isDigit(char c) {


        int asInt = (int)c;
        if (asInt < 48 || asInt > 57) {
            return  false;
        }
        return true;
    }

}

