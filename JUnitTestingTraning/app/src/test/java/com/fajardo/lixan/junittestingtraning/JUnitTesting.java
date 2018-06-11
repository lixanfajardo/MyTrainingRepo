package com.fajardo.lixan.junittestingtraning;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

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
    }

    @Test
    public void stringChecker(){
        String input = "lixan gwapo";
        String inputVal = input.toUpperCase();
        String expectedOutput = "LIXAN GWAPO";

        assertEquals(expectedOutput, inputVal);
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
