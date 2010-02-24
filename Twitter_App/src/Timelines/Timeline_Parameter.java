package Timelines;

public class Timeline_Parameter {
	
	//public static final Source IPhone = new Source("IPhone");
	public static final Timeline_Parameter user = new Timeline_Parameter();

	private String 	type;
	private String 	value;
	private int 	int_value;
	
	private Timeline_Parameter()
	{
		type = "Unknown";
		value = "Unknown";
		int_value = 0;
	}
	
	public boolean is_a(Timeline_Parameter parameter)
	{
		boolean value = false;
		if(parameter.type == this.type)
			value = true;
		return value;
	}
}
