//interface to be implemented provided constants for exchange rates and the method of exchange to be implemented
public interface Exchangeable {

  //constants for exchange rates of non-earth planets
	public static final double ED = 1.00;
  public static final double MM = 1.30;
	public static final double SS = 0.87;
  public static final double NN = 2.00;

  //method that when implemented takes a currency object and a target amount and converts the amount from the object's currency and converts it to the other's currency
	public void exchange(Currency other, double amount);
}
