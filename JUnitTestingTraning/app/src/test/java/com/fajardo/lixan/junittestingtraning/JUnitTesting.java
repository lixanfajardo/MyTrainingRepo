package com.fajardo.lixan.junittestingtraning;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.lang.Math;
import java.util.Arrays;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Created by LeakSun on 06/11/2018.
 */

public class JUnitTesting {


    @BeforeClass
    public static void displayWelcomeClass(){
        System.out.println("Welcome to JUnit Testing Class");
    }

    @Before
    public void displayWelcome(){
        System.out.println("Welcome to JUnit Testing");
    }

    @Test
    public void myFirstJUnitMethod(){
        assertEquals(6,3 + 3);
        System.out.println("myFirstJUnitMethod - DONE");
    }

    @Test
    public void stringChecker(){
        String input = "lixan gwapo";
        String inputVal = input.toUpperCase();
        String expectedOutput = "LIXAN GWAPO";

        assertEquals(expectedOutput, inputVal);
        System.out.println("stringChecker - DONE");
    }

    @Test
    public void testMathceil(){
        double actual = 20.5;
        double expected = 21d;
        assertEquals(expected, Math.ceil(actual));
        System.out.println("testMathceil - DONE");
    }

    @Test
    public void testMathAbs(){
        assertEquals(21, Math.abs(-21));
        System.out.println("testMathAbs - DONE");
    }

    @Test
    public void testMath(){
        assertEquals(20d, Math.floor(20.5));
        System.out.println("testMath - DONE");
    }

    @Test
    public void testFalse(){
        String dummy = "I am a dummy";
        boolean actual = dummy.equalsIgnoreCase("i ams a dummy");
        assertFalse(actual);
        System.out.println("testFalse - DONE");

    }

    @Test
    public void testTrue(){
        String dummy = "I am a dummy";
        boolean actual = dummy.equalsIgnoreCase("i am a dummy");
        assertTrue(actual);
        System.out.println("testTrue - DONE");

    }

    @Test
    public void testArrays(){
        int[] numbers = {2,1,3,4,5};
        int[] expected = {1,2,3,4,5};

        Arrays.sort(numbers);
        Assert.assertArrayEquals(expected, numbers);
        System.out.println("testArrays - DONE");
    }

    @Test(expected = NullPointerException.class)
    public void testNullPointer(){
        int[] numbers = null;

        Arrays.sort(numbers);
        Assert.fail("Thrown NullPointerException");
    }

    @After
    public void displayBye(){
        System.out.println("Bye ka Oy :D");
    }

    @AfterClass
    public static void displayByeClass(){
        System.out.println("Don't come again HAHA :D");
    }

}
