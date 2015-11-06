package ru.delightfire.delight;

import android.test.InstrumentationTestCase;

import ru.delightfire.delight.parser.ParserJson;

/**
 * Created by sergei on 05.11.2015.
 */
public class Test extends InstrumentationTestCase{
    ParserJson json = new ParserJson();
    @Override
    public void setUp() throws Exception {
        super.setUp();
        testMakeHttp();
    }
    public void testMakeHttp(){
   // json.makeRequestHttp();
    }
}
