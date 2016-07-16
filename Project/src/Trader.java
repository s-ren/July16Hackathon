import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Trader
{
	public PrintWriter to_exchange;
	public BufferedReader from_exchange;
	public void simpleBuyBond() throws IOException{
			Parser.readSecurity("BOND");
			Parser.readSecurity("VALBZ");
			Parser.readSecurity("VALE");
			Parser.readSecurity("GS");
			Parser.readSecurity("MS");
			Parser.readSecurity("WFC");
			Parser.readSecurity("XLF");
   			to_exchange.println("ADD 0 BOND BUY 999 50");
			to_exchange.print;n("ADD 1 BOND SELL 1001 50)";
		//to_exchange.println("ADD 1 BOND BUY 999 10");
	}
}
