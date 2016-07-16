import java.util.ArrayList;

public class Security{
	public static Security BOND = null;
	public static Security VALBZ = null;
	public static Security VALE = null;
	public static Security GS = null;
	public static Security MS = null;
	public static Security WFC = null;
	public static Security XLF = null;
	
	public String name;
	public ArrayList<ArrayList> sells;
	public ArrayList<ArrayList> buys;
	
	
	public Security(String n, ArrayList<ArrayList> sell, ArrayList<ArrayList> buy) {
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
