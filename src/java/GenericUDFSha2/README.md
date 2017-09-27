HIVE UDFS Function
------------------

**Name:** GenericUDFSha2
**Version:** v1.0
**Description:** This function is native in hive-1.3.0. 

**NOTICE:** This is just to serve as an example on how to create udf functions

Example:
--------

`select sha2('foo');`

**output:** 2C26B46B68FFC68FF99B453C1D30413413422D706483BFA0F98A5E886266E7AE

Output types
------------

 - String hash

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
    ADD JAR ./target/GenericUDFSha2-1.0-SNAPSHOT.jar
    CREATE TEMPORARY FUNCTION sha2 AS 'com.rramos.bigdata.utils.GenericUDFSha2';
    SELECT sha2(foo) from bar LIMIT1; 
```

