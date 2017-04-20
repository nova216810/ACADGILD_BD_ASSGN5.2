import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public  class reducely extends Reducer<Other,IntWritable,Other,IntWritable> 
{
	    
	private IntWritable units_sold = new IntWritable();
	
	public void reduce(Other key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException 
	{
	      int sum = 0;
	      System.out.println("reducer Input key = " + key.toString() );
		    
	      for(IntWritable val : values) 
		  {
	        sum =sum+val.get();
	        System.out.println("sum = " + sum );
	      }
	      units_sold.set(sum);
		  context.write(key, units_sold);
	    
	}
	 
 }
