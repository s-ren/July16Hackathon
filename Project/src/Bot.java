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
		try{
        	skt = new Socket("test-exch-rdfzfifteen", 20000);
        	from_exchange = new BufferedReader(new InputStreamReader(skt.getInputStream()));
        	to_exchange = new PrintWriter(skt.getOutputStream(), true);
		}
		catch (Exception e){
            		e.printStackTrace(System.out);
		}
	}

    public void print_loop(BufferedReader read_from){
		try{
			String reply = read_from.readLine().trim();
			while(reply != null){
			System.out.println(reply);
			reply = read_from.readLine().trim();
			}
		}
      		catch (Exception e)
      	{
          e.printStackTrace(System.out);
      }
	}
  public static void main(String[] args)
    {
      try
      {
		Bot rdfz = new Bot();
		rdfz.init();
		Security.init();
		Trader trader = new Trader();
		trader.to_exchange = rdfz.to_exchange;	
		trader.from_exchange = rdfz.from_exchange;	
		trader.simpleBuyBond();
		//rdfz.print_loop(rdfz.from_exchange);
			
      }
      catch (Exception e)
      {
          e.printStackTrace(System.out);
      }
    }
    
}
