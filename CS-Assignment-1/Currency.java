//abstract class that when a child class extends it, provides data types and methods that all objects that take the form of a currency must have
public abstract class Currency implements Exchangeable {

  //protected datatypes that have a string for the name of the currencyname in object and a double for the total funds for the object
  protected String currencyName;
	protected double totalFunds;

  //abstract method that when implemented takes an amount as a double and converts it to earth dollars
	public abstract double toEarthDollars(double amount);

  //abstract method that when implemented takes an amount as a double and converts it from earth dollars to the target currency
	public abstract double fromEarthDollars(double EarthDollars);

  //abstract method from the interface that when implemented takes a currency object and a target amount and converts the amount from the object's currency and converts it to the other's currency
  public abstract void exchange(Currency other, double amount);

}