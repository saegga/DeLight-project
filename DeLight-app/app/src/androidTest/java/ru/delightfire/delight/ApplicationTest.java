package ru.delightfire.delight;

import android.app.Application;
import android.test.ApplicationTestCase;

import ru.delightfire.delight.parser.ParserJson;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
    ParserJson parserJson = new ParserJson();


    public void test(){
       assert 5==5;
        System.out.printf("eg");
    }
}