HIVE UDFS Function
------------------

**Name:** GetTimeOffset
**Version:** v1.0
**Description:** Function that returns the time offset of 
                 local time from date given as parameter 
                 and IP address.

Example:
--------

`select get_offset('12.123.44.34','2016-03-30;00:20:08');`

**output:** `-05:00`

Output types
------------

 - String with the offset

**Requirements:**

 - Requires maven in order to compile the jar file
 - Requires MaxMind LookupTable GeoLite2-City.mmdb

How to compile ?
----------------

    mvn clean package

How to compile with dependencies ?
----------------------------------

    mvn clean compile assembly:single

How to use it ?
---------------

 1.  Upload the created jar package to the cluster.
 2.  Define a function that uses the class.
 3.  Use the function

```
    ADD JAR ./target/GetTimeOffset-1.0-SNAPSHOT.jar
    CREATE TEMPORARY FUNCTION get_offset AS 'com.rramos.bigdata.utils.GetTimeOffset';
    SELECT get_offset(ip,date) from foo LIMIT1; 
```
