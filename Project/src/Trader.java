import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

public class Trader
{
	Gson gson = new Gson();
	
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
		HashMap<String, Object> variables = new HashMap<String, Object>();
    variables.put("type", "hello");
    variables.put("team", "RDFZFIFTEEN");
    Bot.to_exchange.println(gson.toJson(variables));
    
		updateInfo();
			Bot.to_exchange.println("ADD 0 BOND BUY 999 20");
			Bot.to_exchange.println("ADD 1 BOND SELL 1001 20");
			int i = 2;
			ArrayList<Security> secs = new ArrayList<>();
			secs.add(Security.VALBZ);
			secs.add(Security.GS);
			secs.add(Security.MS);
			secs.add(Security.WFC);
			for (Security sec : secs) {
				if (sec.buys.isEmpty() || sec.sells.isEmpty()) continue;
				int toBuy = new Double((double) sec.buys.get(0).get(0)).intValue() + 1;
				int toSell = new Double((double) sec.sells.get(0).get(0)).intValue() - 1;
				if (toBuy >= toSell) continue;
				
				variables = new HashMap<String, Object>();
	      variables.put("type", "add");
	      variables.put("order_id", i);
	      variables.put("symbol", sec.name);
	      variables.put("dir", "BUY");
	      variables.put("price", toBuy);
	      variables.put("symbol", 5);
	      Bot.to_exchange.println(gson.toJson(variables));
				i++;
				variables = new HashMap<String, Object>();
	      variables.put("type", "add");
	      variables.put("order_id", i);
	      variables.put("symbol", sec.name);
	      variables.put("dir", "SELL");
	      variables.put("price", toSell);
	      variables.put("symbol", 5);
	      Bot.to_exchange.println(gson.toJson(variables));
	      i++;
				System.out.println("Sent buy for " + sec.name + " at price " + toBuy + ";\n" + 
						"Sent sell for " + sec.name + " at price " + toSell + ";\n");
			}
	}
}
