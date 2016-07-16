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
	public ArrayList<ArrayList> sells;
	public ArrayList<ArrayList> buys;
	
	public static void init() {
		secs.add(VALBZ);
		secs.add(GS);
		secs.add(MS);
		secs.add(WFC);
	}
	
	public Security(String n, ArrayList<> sell, ArrayList<Object> buy) {
		name = n;
		sells = sell;
		buys = buy;
		System.out.println(this);
	}
	
	@Override
	public String toString() {
		return "bond: " + name + "\n sells: " + sells + "\n buys " + buys + "\n";	
	}
}
