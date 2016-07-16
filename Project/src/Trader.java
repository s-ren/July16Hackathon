import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.management.Query;

import com.google.gson.Gson;

public class Trader
{
	Gson gson = new Gson();
	public PrintWriter to_exchange;
	public BufferedReader from_exchange;
	
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
		updateInfo();
   		to_exchange.println("ADD 0 BOND BUY 999 20");
			to_exchange.println("ADD 1 BOND SELL 1001 20");
			int i = 2;
			ArrayList<Security> secs = new ArrayList<>();
			secs.add(Security.VALBZ);
			secs.add(Security.GS);
			secs.add(Security.MS);
			secs.add(Security.WFC);
			for (Security sec : secs) {
				if (sec.buys.isEmpty() || sec.sells.isEmpty()) continue; 
				int toBuy = (int) sec.buys.get(0).get(0) + 1;
				int toSell = (int) sec.sells.get(0).get(0) - 1;
				if (toBuy >= toSell) continue;
				
				HashMap<String, Object> variables = new HashMap<String, Object>;
	      variables.put("type", "add");
	      variables.put("order_id", i);
	      variables.put("symbol", sec.name);
	      variables.put("dir", "BUY");
	      variables.put("price", toBuy);
	      variables.put("symbol", 5);
	      to_exchange.println(gson.toJson(variables));
				i++;
				variables = new HashMap<String, Object>;
	      variables.put("type", "add");
	      variables.put("order_id", i);
	      variables.put("symbol", sec.name);
	      variables.put("dir", "SELL");
	      variables.put("price", toSell);
	      variables.put("symbol", 5);
	      to_exchange.println(gson.toJson(variables));
	      i++;
				System.out.println("Sent buy for " + sec.name + " at price " + toBuy + ";\n" + 
						"Sent sell for " + sec.name + " at price " + toSell + ";\n");
			}
	}
}
