import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.*;
import java.net.Socket;
import java.util.regex.*;

@SuppressWarnings("unused")
public class Updater
{
	public static void writeToFile(String fileName, String Content){
	try{
		File file = new File(fileName);
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(Content);
		bw.close();
	}
   	catch (Exception e)
   	{
         e.printStackTrace(System.out);
    }
	
	}
	public static void writeTrade(String fileName, String Content){
		try{
			PrintWriter writer = new PrintWriter(new FileWriter("../data/" + fileName + "_trade", true));
			writer.println(Content);
			writer.close();	
			writeToFile("../data/" + fileName + "_current_trade", Content);
		}
   		catch (Exception e)
   		{
         	e.printStackTrace(System.out);
    	}
	
	}
	public static void writeRelevent(String Content){
		try{
			PrintWriter writer = new PrintWriter(new FileWriter("../data/relevent", true));
			writer.println(Content);
			writer.close();	
		}
   		catch (Exception e)
   		{
         	e.printStackTrace(System.out);
    	}
	
	}
	public static void handleFile(String target){
		String pattern = ".\"type\":\"book\".\"symbol\":\"(.*)\",.*.";
		String tradePattern = ".\"type\":\"trade\".\"symbol\":\"(.*)\",.*.";
		Pattern r = Pattern.compile(pattern);
		Pattern r_t = Pattern.compile(tradePattern);
		Matcher m = r.matcher(target);
		Matcher m_t = r_t.matcher(target);
		if (m.find()){
			writeToFile("../data/" + m.group(1), target);	
		}
		else if (m_t.find()){
			writeTrade(m_t.group(1), target);
		}
		else{
			writeRelevent(target);
		}
	}
	public static void main(String[] args){
		try
		{
			Socket skt = new Socket("production", 25000);
			BufferedReader from_exchange = new BufferedReader(new InputStreamReader(skt.getInputStream()));
			PrintWriter to_exchange = new PrintWriter(skt.getOutputStream(), true);
			to_exchange.println("{\"type\": \"hello\", \"team\": \"RDFZFIFTEEN\"}");
			String reply = from_exchange.readLine().trim();
			int count = 0;
			while(reply != null){
				count ++;
				//System.out.println(reply);
				handleFile(reply);
				reply = from_exchange.readLine().trim();
				if (count > 100) break;
			}
		}
      	catch (Exception e)
      	{
          e.printStackTrace(System.out);
        }
	}
}
