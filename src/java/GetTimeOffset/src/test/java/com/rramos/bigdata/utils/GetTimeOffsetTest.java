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
public class GetTimeOffsetTest {

  @Test
  public void VerifyUDFReturnValue() throws HiveException {
    
    // set up the models we need
    GetTimeOffset test = new GetTimeOffset();

    ObjectInspector inspectorA = PrimitiveObjectInspectorFactory.javaStringObjectInspector;
    ObjectInspector inspectorB = PrimitiveObjectInspectorFactory.javaStringObjectInspector;
    JavaStringObjectInspector resultInspector = (JavaStringObjectInspector) test.initialize(new ObjectInspector[]{inspectorA,inspectorB});
    
    // Check the value
    Object result = test.evaluate(new DeferredObject[]{new DeferredJavaObject("128.101.101.101"), new DeferredJavaObject("2015-08-11;20:10:16")});
    Assert.assertEquals("-05:00", resultInspector.getPrimitiveJavaObject(result));
    
    // tCheck the value
    Object result2 = test.evaluate(new DeferredObject[]{new DeferredJavaObject("78.96.10.76"), new DeferredJavaObject("2016-02-01;00:00:08")});
    Assert.assertEquals("+03:00", resultInspector.getPrimitiveJavaObject(result2));

  }
}
