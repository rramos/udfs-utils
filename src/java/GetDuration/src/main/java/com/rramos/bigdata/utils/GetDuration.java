package com.rramos.bigdata.utils;

import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

// Hive imports
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

@Description(
  name="GetDuration",
  value="returns the duration seconds between two timestamps in the format (yyyyMMdd'T'HHmmss.SSSZ) given as arguments",
  extended="SELECT GetDuration('2016-03-30T00:20:08.000Z','2016-03-30T00:21:08.000Z') from foo limit 1;"
  )

public class GetDuration extends UDF {

    /**
     *   Hive UDF Function that returns seconds between time1 and time2 timestamps
     *   This code uses joda-Time
     */
    public int evaluate(Text time1,Text time2) {

        if(time1 == null || time2 == null) return 0;

            DateTimeFormatter fmt = ISODateTimeFormat.dateTime();

            DateTime dt1 = fmt.parseDateTime(time1.toString());
            DateTime dt2 = fmt.parseDateTime(time2.toString());

            Seconds seconds = Seconds.secondsBetween(dt1,dt2);

            return (seconds.getSeconds());
        }

}
