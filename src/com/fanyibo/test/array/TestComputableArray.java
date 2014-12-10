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
            ComputableArray array1 = new ComputableArray("3471027489237893252309519075013513502357.023752084535730");
            ComputableArray array2 = new ComputableArray("1920482308572380510928429587285103410471.084731582475689");

            System.out.println(array1.plus(array2).toString());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
