import java.util.ArrayList;

public class Security{
	public static Security BOND = null;
	public static Security VALBZ = null;
	public static Security VALE = null;
	public static Security GS = null;
	public static Security MS = null;
	public static Security WFC = null;
	public static Security XLF = null;
	public static ArrayList<Security> secs = new ArrayList<Security>();
	
	public String name;
	public ArrayList<Object> sells;
	public ArrayList<Object> buys;
	
	public static void init() {
		secs.add(VALBZ);
		secs.add(GS);
		secs.add(MS);
		secs.add(WFC);
	}
	
	public Security(String n, ArrayList<Object> sell, ArrayList<Object> buy) {
		name = n;
		sells = sell;
		buys = buy;
	}
	
	@Override
	public String toString() {
		return "bond: " + name + "\n sells: " + sells + "\n buys " + buys + "\n";	
	}
}
