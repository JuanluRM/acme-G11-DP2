
package acme.systemConfiguration;

import java.util.Arrays;
import java.util.List;

public class Currency {

	public static final String			SYSTEM_CURRENCY;

	public static final List<String>	ACCEPTED_CURRENCIES;

	// Static initializer block to initialize the variables
	static {
		SYSTEM_CURRENCY = "EUR";
		ACCEPTED_CURRENCIES = Arrays.asList("EUR", "USD", "GBP");
	}
}
