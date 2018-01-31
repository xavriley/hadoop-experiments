package com.mycompany.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Map;

import org.apache.commons.text.StringEscapeUtils;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AvgMapperTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AvgMapperTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AvgMapperTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testAvgMapper()
    {
	    File file = new File("/home/local/mdac073/Users10.xml");
	    try {
		    Scanner scan = new Scanner(file);
		    while (scan.hasNextLine()) {
			    String line = scan.nextLine();
			    // System.out.println(line);
			    Map<String, String> map = SimpleXML.transformXmlToMap(line);
			    if (map != null) {
				    String txt = map.get("AboutMe");
				    if (txt != null) {
					    txt = StringEscapeUtils.unescapeHtml4(txt);
					    txt = txt.replaceAll("'", ""); // remove single quotes (e.g., can't)
					    txt = txt.replaceAll("[^a-zA-Z]", " "); // replace the rest with a space 
					    // System.out.println(map);
					    assertTrue( true );
                                    }
			    }
		    }
	    } catch (FileNotFoundException e) {
		    e.printStackTrace();
	    }
    }
}
