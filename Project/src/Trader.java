import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Trader
{		
	public void updateInfo() throws IOException {
		Parser.readSecurity("BOND");
		Parser.readSecurity("VALBZ");
		Parser.readSecurity("VALE");
		Parser.readSecurity("GS");
		Parser.readSecurity("MS");
		Parser.readSecurity("WFC");
		Parser.readSecurity("XLF");
	}
	
	public void simpleBuyBond() throws IOException{
		int now = parseLong(new Date().getTime());
		File myFile = new File("./data/ID");
		BufferedReader reader = new BufferedReader(new FileReader(myFile));
		File tempFile = new File("myTempFile.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
		String currentLine;
		System.out.println(now);
		while((currentLine = reader.readLine()) != null) {
		    String trimmedLine = currentLine.trim();
				int time = Integer.parseInt(trimmedLine);
				if (now - time >= 5000) continue;
		    writer.write(currentLine + System.getProperty("line.separator"));
		    System.out.println(time);
	     	    HashMap<String, Object> variables = new HashMap<String, Object>();
		    variables.put("type", "cancel");
		    variables.put("order_id", time);
		    Bot.getInputer().println(Bot.gson.toJson(variables));
		}
		writer.close(); 
		reader.close(); 
		tempFile.renameTo(tempFile);
		
		HashMap<String, Object> variables = new HashMap<String, Object>();
    variables.put("type", "hello");
    variables.put("team", "RDFZFIFTEEN");
    Bot.getInputer().println(Bot.gson.toJson(variables));
    //Bot.readFromServer();
    
		updateInfo();
			ArrayList<Security> secs = new ArrayList<>();
			secs.add(Security.GS);
			secs.add(Security.MS);
			secs.add(Security.WFC);
			secs.add(Security.VALBZ);
			secs.add(Security.XLF);
			for (Security sec : secs) {
				if (sec.buys.isEmpty() || sec.sells.isEmpty()) continue;
				int toBuy = new Double((double) sec.buys.get(0).get(0)).intValue();
				int toSell = new Double((double) sec.sells.get(0).get(0)).intValue();
				if (toBuy >= toSell) continue;
				
				now = parseLong(new Date().getTime());
				variables = new HashMap<String, Object>();
	      variables.put("type", "add");

	      variables.put("order_id", now);
	      variables.put("symbol", sec.name);
	      variables.put("dir", "BUY");
	      variables.put("price", toBuy);
	      variables.put("size", 20);
	      System.out.println(Bot.gson.toJson(variables));
	      Bot.getInputer().println(Bot.gson.toJson(variables));
	      Parser.write(Integer.toString(now), "ID");
	      now = parseLong(new Date().getTime());
				variables = new HashMap<String, Object>();
	      variables.put("type", "add");
	      variables.put("order_id", now);
	      variables.put("symbol", sec.name);
	      variables.put("dir", "SELL");
	      variables.put("price", toSell);
	      variables.put("size", 20);
	      System.out.println(Bot.gson.toJson(variables));
	      Bot.getInputer().println(Bot.gson.toJson(variables));
	      Parser.write(Integer.toString(now), "ID");
	      System.out.println(Bot.readFromServer());
			}
			int VALDown = new Double((double) Security.VALBZ.buys.get(0).get(0)).intValue();
			int VALUp = new Double((double) Security.VALBZ.sells.get(0).get(0)).intValue();
			int VALBZValue = (VALDown + VALUp) / 2;
			
			int VALEBuy = new Double((double) Security.VALE.buys.get(0).get(0)).intValue();
			int VALESell = new Double((double) Security.VALE.sells.get(0).get(0)).intValue();
			if (VALEBuy < VALDown) {
				now = parseLong(new Date().getTime());
				variables = new HashMap<String, Object>();
	      variables.put("type", "add");
	      variables.put("order_id", parseLong(new Date().getTime()));
	      variables.put("symbol", "VALE");
	      variables.put("dir", "BUY");
	      variables.put("price", VALEBuy);
	      variables.put("size", 10);
	      System.out.println(Bot.gson.toJson(variables));
				Bot.getInputer().println(Bot.gson.toJson(variables));
				Parser.write(Integer.toString(now), "ID");
			} else if (VALESell < VALUp) {
				now = parseLong(new Date().getTime());
				variables = new HashMap<String, Object>();
	      variables.put("type", "add");
	      variables.put("order_id", now);
	      variables.put("symbol", "VALE");
	      variables.put("dir", "SELL");
	      variables.put("price", VALESell);
	      variables.put("size", 10);
	      System.out.println(Bot.gson.toJson(variables));
				Bot.getInputer().println(Bot.gson.toJson(variables));
				Parser.write(Integer.toString(now), "ID");
			} else {
				if (!Security.VALE.buys.isEmpty() && !Security.VALE.sells.isEmpty()) {
					int toBuy = new Double((double) Security.VALE.buys.get(0).get(0)).intValue() + 1;
					int toSell = new Double((double) Security.VALE.sells.get(0).get(0)).intValue() - 1;
					if (toBuy < toSell) {
						now = parseLong(new Date().getTime());
						variables = new HashMap<String, Object>();
			      variables.put("type", "add");
			      variables.put("order_id", now);
			      variables.put("symbol", Security.VALE.name);
			      variables.put("dir", "BUY");
			      variables.put("price", toBuy);
			      variables.put("size", 10);
			      System.out.println(Bot.gson.toJson(variables));
			      Bot.getInputer().println(Bot.gson.toJson(variables));
			      Parser.write(Integer.toString(now), "ID");
			      now = parseLong(new Date().getTime());
						variables = new HashMap<String, Object>();
			      variables.put("type", "add");
			      variables.put("order_id", now);
			      variables.put("symbol", Security.VALE.name);
			      variables.put("dir", "SELL");
			      variables.put("price", toSell);
			      variables.put("size", 10);
			      System.out.println(Bot.gson.toJson(variables));
			      Bot.getInputer().println(Bot.gson.toJson(variables));
			      Parser.write(Integer.toString(now), "ID");
			      System.out.println(Bot.readFromServer());
					}
				}
			}
			
			variables = new HashMap<String, Object>();
      variables.put("type", "hello");
      variables.put("team", "RDFZFIFTEEN");
      System.out.println(Bot.gson.toJson(variables));
      Bot.getInputer().println(Bot.gson.toJson(variables));
			Type typeOfObjectsList = new TypeToken<Map<String, Object>>() {}.getType();
			Map<String, Object> out = new Gson().fromJson(Bot.readFromServer(), typeOfObjectsList);
			if (out.get("type").equals("hello")) {
				ArrayList<Map> symbols = (ArrayList) out.get("symbols");
				for (Map sec : symbols) {
					if (sec.get("symbol").equals("VALE")) {
						System.out.println(sec);
						variables = new HashMap<String, Object>();
			      variables.put("type", "convert");
			      variables.put("order_id", now);
			      variables.put("symbol", Security.VALE.name);
			      variables.put("dir", "SELL");
			      variables.put("size", 5);
			      System.out.println(Bot.gson.toJson(variables));
			      Bot.getInputer().println(Bot.gson.toJson(variables));
			      Parser.write(Integer.toString(now), "ID");
				System.out.println("___________________________");
					}
				}
			}
	}
	private int parseLong(long l) {
		return (int) (l % 10000000);
	}
}
