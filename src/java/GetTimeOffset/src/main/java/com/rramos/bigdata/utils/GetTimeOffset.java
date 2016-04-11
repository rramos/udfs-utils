package com.rramos.bigdata.utils;

// Generic java packages
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

// Time packages
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.DateTimeZone;

import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.Date;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.text.ParseException;

// Maxmind GeoIP packages
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Location;

// Hive Packages
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;

// GenericUDF Inspectors 
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

class GetTimeOffset extends GenericUDF {

  StringObjectInspector inspectorA;
  StringObjectInspector inspectorB;
  

  @Override
  public String getDisplayString(String[] arg0) {
    return "GetTimeOffset()"; // Function description
  }

  @Override
  public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {

    if (arguments.length != 2) {
      throw new UDFArgumentLengthException("GetTimeOffset only takes 2 arguments: String, String");
    }

    // 1. Check we received the right object types.
    ObjectInspector a = arguments[0];
    ObjectInspector b = arguments[1];

    if (!(a instanceof StringObjectInspector) || !(b instanceof StringObjectInspector)) {
      throw new UDFArgumentException("Both arguments must be Strings");
    }

    this.inspectorA = (StringObjectInspector) a;
    this.inspectorB = (StringObjectInspector) b;
    
    // return the result inspector
    return PrimitiveObjectInspectorFactory.javaStringObjectInspector;
  }
  
  @Override
  public Object evaluate(DeferredObject[] arguments) throws HiveException {
    
    // get the String from the inspectors
    String ipstr   = inspectorA.getPrimitiveJavaObject(arguments[0].get());
    String indate = inspectorB.getPrimitiveJavaObject(arguments[1].get());

    // check for nulls
    if (ipstr == null || indate == null) {
      return null;
    }
    
    // database file  
    String DATABASE_CITY_PATH = "GeoLite2-City.mmdb";

    try{
        // The GeoLite2 database file
        File dbFile = new File(DATABASE_CITY_PATH);

        // Database reader object
        DatabaseReader reader = new DatabaseReader.Builder(dbFile).build();

        // Convert IP address string to object
        InetAddress ipAddress = InetAddress.getByName(ipstr);
        
        // Lookup ip Address 
        CityResponse response = reader.city(ipAddress);
                
        // Geo Location info.
        Location location = response.getLocation();

        // Get timezone object for identified location timezone found
        TimeZone tz = TimeZone.getTimeZone(location.getTimeZone());

        // Format indate for identified timezone
        DateTimeFormatter dtf1 = DateTimeFormat.forPattern("yyyy-MM-dd;HH:mm:ss");
        DateTimeZone zone = DateTimeZone.forTimeZone(tz);  
        
        // get offset 
        int offsetInMillis = zone.getOffset(new DateTime().getMillis());
        String offset = String.format("%02d:%02d", Math.abs(offsetInMillis / 3600000),Math.abs((offsetInMillis / 60000) % 60));
        offset = (offsetInMillis >= 0 ? "+" : "-") + offset;

        return offset;
           
    }catch(Exception e){
      // In case of some exception caught return null
      //return ("Exception: "  + e );
      return null;
    }

  }

}
