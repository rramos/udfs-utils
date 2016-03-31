package com.rramos.bigdata.utils;

import org.junit.*;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import org.apache.hadoop.io.Text;

/**
 * Unit test for simple App.
 */
public class GetDurationTest {

  	@Test
  	public void VerifyUDF() {
    	GetDuration test = new GetDuration();
    	Assert.assertEquals(60, test.evaluate(new Text("2016-03-30T00:20:08.000Z"),new Text("2016-03-30T00:21:08.000Z")));
  	}

  	@Test 
  	public void VerifyUDFNullCheck() {
    	GetDuration test = new GetDuration();
    	Assert.assertEquals(0,test.evaluate(null,null));
  	}

    @Test 
    public void VerifyUDFNull_arg1_Check() {
      GetDuration test = new GetDuration();
      Assert.assertEquals(0,test.evaluate(null,new Text("2016-03-01T10:30:00")));
    }

        @Test 
    public void VerifyUDFNull_arg0_Check() {
      GetDuration test = new GetDuration();
      Assert.assertEquals(0,test.evaluate(new Text("2016-03-01T10:30:00"),null));
    }

}
