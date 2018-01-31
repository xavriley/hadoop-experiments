package com.mycompany.app;

import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class SimpleXML {

	// This helper function parses the stackoverflow into a Map for us.
	public static Map<String, String> transformXmlToMap(String xml) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			// exploit the fact that splitting on double quote
			//  tokenizes the data nicely for us
			String[] tokens = xml.trim().substring(5, xml.trim().length() - 3).split("\"");
			for(int i=0; i < tokens.length-1; i += 2) { 
				String key = tokens[i].trim();

				String val = tokens[i + 1];
				map.put(key.substring(0, key.length() - 1), val);
			}

		} catch (StringIndexOutOfBoundsException e) {
			return null;
		}
		return map;
	}



	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("/Users/uxac007/Work/BigData/CS5234/lab2/stackoverflow/small/Users10.xml"));
		while(s.hasNextLine()) {

			String line = s.nextLine();
			Map<String, String> map = SimpleXML.transformXmlToMap(line);
			if (map != null) {
				String repString = map.get("Reputation");
				int rep = Integer.parseInt(repString);
				System.out.println(rep);
			}
		}
		s.close();
//		Map<String, String> map = SimpleXML.transformXmlToMap(line);
//		for (String key : map.keySet())
//			System.out.println("key=" + key + " value=" + map.get(key));

	}

}
