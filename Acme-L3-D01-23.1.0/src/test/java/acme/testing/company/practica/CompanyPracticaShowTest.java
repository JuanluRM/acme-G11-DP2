
package acme.testing.company.practica;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.Practica;
import acme.testing.TestHarness;

public class CompanyPracticaShowTest extends TestHarness {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected CompanyPracticaTestRepository repository;

	// Test data --------------------------------------------------------------


	@ParameterizedTest
	@CsvFileSource(resources = "/company/practica/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int recordIndex, final String code, final String title, final String summary, final String goals, final String estimatedTotalTime) throws InterruptedException {

		super.signIn("company1", "company1");
		super.clickOnMenu("Company", "List Practica");
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();

		Thread.sleep(1000);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("summary", summary);
		super.checkInputBoxHasValue("goals", goals);
		super.checkInputBoxHasValue("estimatedTotalTime", estimatedTotalTime);
		Thread.sleep(1000);
		super.signOut();
	}

	@Test
	public void test200Negative() {
		// HINT: there aren't any negative tests for this feature because it's a listing
		// HINT+ that doesn't involve entering any data in any forms.
	}

	@Test
	public void test300Hacking() {

		final Collection<Practica> practicas;
		String param;

		practicas = this.repository.findManyPracticasByCompanyUsername("company1");
		for (final Practica practica : practicas)
			if (!practica.getDraftMode() || practica.getDraftMode()) {
				param = String.format("id=%d", practica.getId());

				super.checkLinkExists("Sign in");
				super.request("/assistant/tutorial/show", param);
				super.checkPanicExists();

				super.signIn("administrator1", "administrator1");
				super.request("/assistant/tutorial/show", param);
				super.checkPanicExists();
				super.signOut();

				super.signIn("lecturer2", "lecturer2");
				super.request("/assistant/tutorial/show", param);
				super.checkPanicExists();
				super.signOut();

				super.signIn("student1", "student1");
				super.request("/assistant/tutorial/show", param);
				super.checkPanicExists();
				super.signOut();
			}
	}

}
