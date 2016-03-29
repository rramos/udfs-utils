package com.rramos.bigdata.utils;

import org.junit.*;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import org.apache.hadoop.io.Text;

/**
 * Unit test for simple App.
 */
public class GetDeviceTypeTest {

  	@Test
  	public void VerifyUDF() {
    	GetDeviceType test = new GetDeviceType();
    	Assert.assertEquals("Computer", test.evaluate(new Text("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36")).toString());
  	}

  	@Test 
  	public void VerifyUDFNullCheck() {
    	GetDeviceType test = new GetDeviceType();
    	Assert.assertNull(test.evaluate(null));
  	}


}
