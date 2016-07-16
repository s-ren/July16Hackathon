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
	public static void handleFile(String target){
		String pattern = ".\"type\":\"book\".\"symbol\":\"(.*)\",.*.";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(target);
		if (m.find()){
			writeToFile(m.group(1), target);	
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
			while(reply != null){
				System.out.println(reply);
				handleFile(reply);
				reply = from_exchange.readLine().trim();
			}
			//handleFile("{\"type\":\"book\",\"symbol\":\"VALBZ\",\"buy\":[[4198,14],[4197,1]],\"sell\":[[4199,7],[4200,23]]}");
		}
      	catch (Exception e)
      	{
          e.printStackTrace(System.out);
        }
	}
}
