package com.rramos.bigdata.utils;

import org.junit.*;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF.DeferredJavaObject;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF.DeferredObject;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.JavaStringObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import org.apache.hadoop.io.Text;

/**
 * Unit test for simple App.
 */
public class GetLocalTimeTest {

  @Test
  public void VerifyUDFReturnValue() throws HiveException {
    
    // set up the models we need
    GetLocalTime test = new GetLocalTime();

    ObjectInspector inspectorA = PrimitiveObjectInspectorFactory.javaStringObjectInspector;
    ObjectInspector inspectorB = PrimitiveObjectInspectorFactory.javaStringObjectInspector;
    JavaStringObjectInspector resultInspector = (JavaStringObjectInspector) test.initialize(new ObjectInspector[]{inspectorA,inspectorB});
    
    // Check the value
    Object result = test.evaluate(new DeferredObject[]{new DeferredJavaObject("128.101.101.101"), new DeferredJavaObject("2015-08-11;20:10:16")});
    Assert.assertEquals("2015-08-11;14:10:16", resultInspector.getPrimitiveJavaObject(result));
    
    // tCheck the value
    Object result2 = test.evaluate(new DeferredObject[]{new DeferredJavaObject("128.101.101.101"), new DeferredJavaObject("2016-02-01;00:00:08")});
    Assert.assertEquals("2016-01-31;18:00:08", resultInspector.getPrimitiveJavaObject(result2));
          
  }


/*
    @Test 
    public void VerifyUDFNull_arg1_Check() {
      GetLocalTime test = new GetLocalTime();
      Assert.assertEquals(0,test.evaluate(null,new Text("2016-03-01T10:30:00")));
    }

    @Test 
    public void VerifyUDFNull_arg0_Check() {
      GetLocalTime test = new GetLocalTime();
      Assert.assertEquals(0,test.evaluate(new Text("2016-03-01T10:30:00"),null));
  */
}
