HIVE UDFS Function
------------------

**Name:** GetDeviceType
**Version:** v1.0
**Description:** Function that returns the string associated with the device type given as argument

Example:
--------

    select decode_agent('Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/48.0.2564.116 Chrome/48.0.2564.116 Safari/537.36');

**output:** `Computer`

Output types
------------

 - **COMPUTER**: Standard desktop or laptop computer 
 - **DMR**: Digital media receiver like the Google TV.
 - **GAME_CONSOLE**: Game console like the Wii or Playstation.
 - **MOBILE**: Mobile phone or similar small mobile device.
 - **UNKNOWN**: Other or unknow type of device.
 - **WEARABLE**: Miniature device like a smart watch or interactive glasses

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

    ADD JAR ./target/GetDeviceType-1.0-SNAPSHOT.jar
    CREATE TEMPORARY FUNCTION decode_agent AS 'com.rramos.bigdata.utils.GetDeviceType';
    SELECT decode_agent(foo) from bar LIMIT1; 

