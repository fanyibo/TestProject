/*
 *    Copyright   2014
 *     Filename : TestComputableArray
 *      Project : TestProject
 *   Created by : fanyibo on 12/9/14 10:41 PM
 */
package com.fanyibo.test.array;

import com.fanyibo.array.computable.ComputableArray;

public class TestComputableArray {

    public static void main(String[] args) {

        try {
            ComputableArray array1 = new ComputableArray("-1.2");
            ComputableArray array2 = new ComputableArray("-10");

            System.out.println(array1.plus(array2).toString());
            System.out.println(array1.substract(array2).toString());
            System.out.println(array1.multiply(array2).toString());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
