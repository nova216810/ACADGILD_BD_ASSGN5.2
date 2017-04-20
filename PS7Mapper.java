
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public  class maply extends Mapper<LongWritable, Text, Other, IntWritable>
{
	Other outKey = new Other();
	
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
	{
	      
		System.out.println("mapper Input key = " + key.toString() + " Input value = " + value.toString());
	     
		String[] token = value.toString().split("\\|");

			int flag=0;
			
			if (token[0].equals("NA") || token[1].equals("NA"))
			{
				flag = 1;
				System.out.println("flag set company "+ token[0]+" product "+token[1] );
			}
			
			if (flag == 0)
			{
				
				outKey.set(token[0],token[1], Integer.parseInt(token[2]));
				context.write(outKey, new IntWritable(1));
				System.out.println("writing..company "+ token[0]+" product "+token[1]+ " size " +token[2] );
			}
	} 
}
