package com.selenium.eduviya_utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
	
	public class WriteToCsvFile {
	
	private  BufferedWriter writer;
	
    public WriteToCsvFile(String filePath) throws IOException 
    {
     writer = new BufferedWriter(new FileWriter(filePath+timestamp()+" Eduvidya_Schools.csv"));
	 writer.write("School Name\n");
	}
	public  void writeSchoolName(String name) throws IOException 
	{
	if (name != null && !name.trim().isEmpty()) 
	{
	writer.write("\"" + name.trim() + "\"\n");
	}
	}

	public void closeWriter() throws IOException
	{
		if(writer!=null)
		{
			writer.flush();
			writer.close();
		}
	}
	public static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }

		
}


