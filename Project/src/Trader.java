import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Trader
{
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
				to_exchange.println("ADD " + i + " " + sec.name + " BUY " + toBuy + " 20");
				i++;
				to_exchange.println("ADD " + i + " " + sec.name + " SELL " + toSell + " 20");
				i++;
				System.out.println("Sent buy for " + sec.name + " at price " + toBuy + ";\n" + 
						"Sent sell for " + sec.name + " at price " + toSell + ";\n");
			}
	}
}
