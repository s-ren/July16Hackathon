public class Security{
	public static Security BOND = null;
	public static Security VALBZ = null;
	public static Security VALE = null;
	public static Security GS = null;
	public static Security MS = null;
	public static Security WFC = null;
	public static Security XLF = null;
	
	public String name;
	public double[][] sells;
	public double[][] buys;
	
	public Security(String n, double[][] sell, double[][] buy) {
		name = n;
		sells = sell;
		buys = buy;
	}
	
	@Override
	public String toString() {
		return "bond: " + name;
	}
}