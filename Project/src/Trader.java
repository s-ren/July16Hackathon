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
		for (int i = 0; i < 10; i++) {
			Parser.readSecurity("BOND");
			Parser.readSecurity("VALBZ");
			Parser.readSecurity("VALE");
			Parser.readSecurity("GS");
			Parser.readSecurity("MS");
			Parser.readSecurity("WFC");
			Parser.readSecurity("XLF");
		}
		System.out.println(Security.BOND);
		System.out.println(Security.VALBZ);
		System.out.println(Security.VALE);
		System.out.println(Security.GS);
		System.out.println(Security.MS);
		System.out.println(Security.WFC);
		System.out.println(Security.XLF);
		
   	//to_exchange.println("HELLO RDFZFIFTEEN");
		//to_exchange.println("ADD 1 BOND BUY 999 10");
	}
}
