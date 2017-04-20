import org.apache.hadoop.io.WritableComparable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Other implements WritableComparable<Other> {

	private String company;
	private String product;
	private int size;
	
	public String getCompany()
	{
		return company;
	}
	public String getProduct()
	{
		return product;
	}
	public int getSize()
	{
		return size;
	}
	public void set(String c,String p, int s)
	{
		company = c;
		product = p;
		size = s;
	}
	public void readFields(DataInput in) throws IOException
	{
		company = in.readLine();
		product = in.readLine();
		size = in.readInt();
	}
	public void write(DataOutput out) throws IOException
	{
    company = in.readUTF();
    product = in.readUTF();
    size = in.readInt();
  }
  public int compare(Other a, Other b )
	{
		return (-1* (a.getSize()-b.getSize()));
	}
  
	@Override
	public int compareTo(Other o) 
  {
		return (-1* (this.getSize()-o.getSize()));
	}
}
