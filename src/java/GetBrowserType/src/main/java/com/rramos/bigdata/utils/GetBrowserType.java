package com.rramos.bigdata.utils;

import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.BrowserType;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;

// Hive imports
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

@Description(
  name="GetBrowserType",
  value="returns the decoded BrowserType given a user agent string as argument",
  extended="SELECT GetBrowserType('Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36') from foo limit 1;"
  )

public class GetBrowserType extends UDF {

    /**
     *   Hive UDF Function that returns BrowserType given user-agent string as argument
     *   the following types are available:
     *   http://bitwalker.eu/user-agent-utils/javadoc/eu/bitwalker/useragentutils/BrowserType.html
     */
    public Text evaluate(Text userAgentString) {

        if(userAgentString == null) return null;

                UserAgent agent = UserAgent.parseUserAgentString(userAgentString.toString());
                Browser browser = agent.getBrowser();
                BrowserType bt = browser.getBrowserType();
                
                return new Text(bt.getName());
        }

}
