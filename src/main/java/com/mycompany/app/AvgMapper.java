package com.mycompany.app;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Map;

import org.apache.commons.text.StringEscapeUtils;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AvgMapper extends Mapper<Object, Text, Text, IntWritable> {

	private final IntWritable one = new IntWritable(1);
	private Text word = new Text();

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String xml = value.toString();
		Map<String, String> map = SimpleXML.transformXmlToMap(xml);
		if (map != null) {
			// Extract the value for the reputation key
			String txt = map.get("AboutMe");
			if (txt != null) {
				txt = StringEscapeUtils.unescapeHtml4(txt.toLowerCase());
				txt = txt.replaceAll("'", ""); // remove single quotes (e.g., can't)
				txt = txt.replaceAll("[^a-zA-Z]", " "); // replace the rest with a space 

				StringTokenizer itr = new StringTokenizer(txt.toString());
				while (itr.hasMoreTokens()) {
					word.set(itr.nextToken());
					context.write(word, one);
				}
			}
		}
	}
}
