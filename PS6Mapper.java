
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public  class PS6Mapper extends Mapper<LongWritable, Text, Text, IntWritable>
{
	private Text state = new Text();
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
			
			if (flag == 0 && token[0].equals("Onida"))
			{
				state.set(token[3]);
				context.write(state,new IntWritable(1));
				System.out.println("writing..company "+ token[0]+" state "+token[3] );
			}
		 } 
		}
	    
	
