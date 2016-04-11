HIVE UDFS Function
------------------

**Name:** GetLocalTime
**Version:** v1.0
**Description:** Function that returns the local time from a timestamp given as param in the format "yyyyMMdd;HHmmss" and a ip address in order to find the local Timezone.

Example:
--------

`select get_local_ip('12.123.44.34','2016-03-30;00:20:08');`

**output:** `2016-03-30;00:21:28`

Output types
------------

 - Timestamp in the format yyyyMMdd;HHmmss

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
    ADD JAR ./target/GetLocalTime-1.0-SNAPSHOT.jar
    CREATE TEMPORARY FUNCTION decode_agent AS 'com.rramos.bigdata.utils.GetLocalTime';
    SELECT get_local_time(ip,date) from foo LIMIT1; 
```
