//import statement to use the NumberFormat class to have format the doubles into monetary values
import java.text.NumberFormat;

//class which implements the currency abstract class as a concrete mars class to keep track of what mars' currency is and what their total funds are
public class Mars extends Currency {

  //contructor which sets the currencyname string from the extended currency class and totalfunds double to their respective amounts when a new instance of mars is made
	public Mars(double amount) {
   currencyName = "MarsMoney";
   totalFunds = amount;
	}

  //implementation of the double to earth dollars which takes the amount and divides it by the exchange rate of marsmoney
  @Override
	public double toEarthDollars(double amount) {
		return amount/MM;
	}

  //implementation of the double from earth dollars which takes the amount in earth dollars and multiplies it by the exchange rate of marsmoney
  @Override
	public double fromEarthDollars(double EarthDollars) {
		return EarthDollars*MM;
	}

  //implementation of the exchange method from the exchangable interface which takes the currency and removes it from this planet's bank in its currency and adds it into the other planet's bank in their own currency
  @Override
	public void exchange(Currency other, double amount) {
    //creates a numberformat instance which will be used to format the doubles into currency values
    NumberFormat doubleMoney = NumberFormat.getCurrencyInstance();

    //if statement checking to see if the bank of mars has enough money to make the exchange
    if (totalFunds >= amount) {
      //print statement which prints the currencyname the method is converting from and the currencyname the method is converting to
      System.out.println("Converting from " + currencyName + " to " +      other.currencyName + " initiating transfer...");

      //prints out the formatted doubles of what the amount in the inital currency is 
      System.out.println(doubleMoney.format(amount) + " " + currencyName + " = " + doubleMoney.format(toEarthDollars(amount)) + " EarthDollars  = " + doubleMoney.format(other.fromEarthDollars(toEarthDollars(amount))) + " " + other.currencyName);

      //sets the total funds to the new amount after the exchange and sets the total funds of the other planet to the new amount
      totalFunds = totalFunds - amount;
      other.totalFunds = other.totalFunds + other.fromEarthDollars(toEarthDollars(amount));

      //prints out the remaining total of both planets' banks 
      System.out.println(getClass().getSimpleName() + " has a total of " + doubleMoney.format(totalFunds) + " " + currencyName);
System.out.println(other.getClass().getSimpleName() + " has a total of " + doubleMoney.format(other.totalFunds) + " " + other.currencyName); 
    }

    //else statement which prints an error message in the case that the transaction is unable to occur due to insufficent funds
    else {
      System.out.println("Uh oh - " + getClass().getSimpleName() + " only has an available balance of " + doubleMoney.format(totalFunds) + ", which is less than " + doubleMoney.format(amount) + "!");
    }

  //print statement to create space in the console after this method runs
  System.out.println("");
	}
}
