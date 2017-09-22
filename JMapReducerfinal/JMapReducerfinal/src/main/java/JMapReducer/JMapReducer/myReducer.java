package JMapReducer.JMapReducer;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class myReducer extends Reducer<LongWritable,WarcRecord, NullWritable, NullWritable> {

	public void reduce(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		
	}

}
