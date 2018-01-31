package com.mycompany.app;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class AvgDriver {
	  public static void main(String[] args) throws Exception {
		    Configuration conf = new Configuration();
		    Job job = Job.getInstance(conf, "AboutMe word count");
		    job.setJar("target/about-me-wordcount-1.0-SNAPSHOT.jar");

		    job.setMapperClass(AvgMapper.class);
		    // job.setCombinerClass(AvgReducer.class);
		    job.setReducerClass(AvgReducer.class);

		    job.setOutputKeyClass(Text.class);
		    job.setOutputValueClass(IntWritable.class);

		    FileInputFormat.addInputPath(job, new Path(args[1]));
		    FileOutputFormat.setOutputPath(job, new Path(args[2]));

		    System.exit(job.waitForCompletion(true) ? 0 : 1);
		  }
}
