import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@SuppressWarnings("unused")
public class Bot
{
	private Socket skt;
	private BufferedReader from_exchange;
	private PrintWriter to_exchange;
	public void init(){
        skt = new Socket("test-exch-rdfzfifteen", 20000);
        from_exchange = new BufferedReader(new InputStreamReader(skt.getInputStream()));
        to_exchange = new PrintWriter(skt.getOutputStream(), true);
	}
    public static void main(String[] args)
    {
        try
        {
            Socket skt = new Socket("test-exch-rdfzfifteen", 20000);
            BufferedReader from_exchange = new BufferedReader(new InputStreamReader(skt.getInputStream()));
            PrintWriter to_exchange = new PrintWriter(skt.getOutputStream(), true);
            Trader trader = new Trader();
            trader.to_exchange = to_exchange;	
            to_exchange.println("HELLO RDFZFIFTEAN"); 
            String reply = from_exchange.readLine().trim();
            System.err.printf("The exchange replied: %s\n", reply);
        }
        catch (Exception e)
        {
            e.printStackTrace(System.out);
        }
    }
    
}
