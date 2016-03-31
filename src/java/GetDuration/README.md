HIVE UDFS Function
------------------

**Name:** GetDuration
**Version:** v1.0
**Description:** Function that returns duration seconds between two timestamps given as argument in the format yyyyMMdd'T'HHmmss.SSSZ

Example:
--------

`select duration('2016-03-30T00:20:08.000Z','2016-03-30T00:21:08.000Z');`

**output:** `60`

Output types
------------

 - integer seconds

**Requirements:**

 - Requires maven in order to compile the jar file

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
    ADD JAR ./target/GetDuration-1.0-SNAPSHOT.jar
    CREATE TEMPORARY FUNCTION decode_agent AS 'com.rramos.bigdata.utils.GetDeviceType';
    SELECT decode_agent(foo) from bar LIMIT1; 
```
