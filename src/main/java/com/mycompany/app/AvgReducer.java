package com.mycompany.app;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AvgReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	private IntWritable result = new IntWritable();

	public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		// initialize sum and count
		int sum = 0;

		for (IntWritable val : values) {
		    sum += val.get();
		}

		result.set(sum);
		context.write(key, result);
	}
}
