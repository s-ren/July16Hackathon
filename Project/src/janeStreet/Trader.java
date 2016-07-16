import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Trader
{
	public PrintWriter to_exchange;
	public void simpleBuyBond(){
		to_exchange.println("ADD 1 BOND BUY 999 10");
	}
}
