HIVE UDFS Function
------------------

**Name:** GetBrowserType
**Version:** v1.0
**Description:** Function that returns the string associated with the Browser type given as argument

Example:
--------

    select decode_browser('Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/48.0.2564.116 Chrome/48.0.2564.116 Safari/537.36');

**output:** `WEB_BROWSER`

Output types
------------
 - **APP**: Application
 - **EMAIL_CLIENT**: Email client like Thunderbird
 - **MOBILE_BROWSER**: Special web-browser for mobile devices
 - **ROBOT**: Search robot, spider, crawler,...
 - **TEXT_BROWSER**: Text only browser like the good old Lynx
 - **TOOL**: Downloading tools
 - **UNKNOWN**: Unknown browser type 
 - **WEB_BROWSER**: Standard web-browser

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

    ADD JAR ./target/GetBrowserType-1.0-SNAPSHOT.jar
    CREATE TEMPORARY FUNCTION decode_browser AS 'com.rramos.bigdata.utils.GetBrowserType';
    SELECT decode_browser(foo) from bar LIMIT1; 

