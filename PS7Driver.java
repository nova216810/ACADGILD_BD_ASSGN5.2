import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class driver {
	public static void main(String[] args) throws Exception 
	{
    	Configuration conf = new Configuration();
		conf.addResource(new Path("/usr/lib/hadoop/etc/hadoop/core-site.xml"));
		conf.addResource(new Path("/usr/lib/hadoop/etc/hadoop/hdfs-site.xml"));
		//create an instance of FileSystem that holds Filesystem namespace
		FileSystem fs = FileSystem.get(conf);
		//variables to hold path of input file and output directory
		String inPath;
		String outPath;
		
		if (args.length != 2) 
		{
		    System.err.println("Usage: Seq File Example <input file> <output dir>");
		    System.out.println("Using default file: seqfile");
		    inPath = "/user/acadgild/television";
			  outPath = "/user/acadgild/validtv";
		}
		else
		 {
		    inPath = args[0];
		    outPath = args[1];
		}
		//create an instance of job
		Job job = new Job(conf, "television");
		job.setJarByClass(PS7Driver.class);
		job.setMapperClass(PS7Mapper.class);
		job.setReducerClass(PS7Reducer.class); 
		job.setNumReduceTasks(1);
		
		job.setMapOutputKeyClass(Other.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(Other.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.addInputPath(job, new Path(inPath));
		if (fs.exists(new Path(outPath))) 
		{
			fs.delete(new Path(outPath), true);
		}
		FileOutputFormat.setOutputPath(job, new Path(outPath));
		 System.exit(job.waitForCompletion(true) ? 0 : 1);
		  
} 
}
