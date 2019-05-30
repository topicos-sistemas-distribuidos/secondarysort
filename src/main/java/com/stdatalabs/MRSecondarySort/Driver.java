package com.stdatalabs.MRSecondarySort;

import org.apache.hadoop.conf.Configuration;

/*#############################################################################################
# Description: SecondarySort using Map Reduce
#
# Input: 
#   1. /user/cloudera/MarkTwain.txt
#
# To Run this code use the command:    
# yarn jar MRSecondarySort-0.0.1-SNAPSHOT.jar \
#		   com.stdatalabs.MRSecondarySort.Driver \
#		   fName_lName.csv \
#		   MRSecondarySort_op
#############################################################################################*/
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class Driver{
	public static void main(String args[]) throws Exception {
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		
		if (otherArgs.length < 2) {
			System.err.println("Usage: secondarysort <in> [<in>...] <out>");
			System.exit(2);
		}
		
		Job job = Job.getInstance(conf, "person sort");
		job.setJarByClass(Driver.class);
		job.getConfiguration().set("key.value.separator.in.input.line", ",");
		job.setMapperClass(PersonMapper.class);
		job.setReducerClass(PersonReducer.class);

		job.setInputFormatClass(KeyValueTextInputFormat.class);
		job.setMapOutputKeyClass(Person.class);
		job.setMapOutputValueClass(Text.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		job.setPartitionerClass(PersonPartitioner.class);
		job.setSortComparatorClass(PersonSortingComparator.class);
		job.setGroupingComparatorClass(PersonGroupingComparator.class);

		job.setNumReduceTasks(2);

		for (int i = 0; i < otherArgs.length - 1; ++i) {
			FileInputFormat.addInputPath(job, new Path(otherArgs[i]));
		}
		
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[otherArgs.length - 1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
