
package acme.testing.company.practica;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.Practica;
import acme.testing.TestHarness;

public class CompanyPracticaPublishTest extends TestHarness {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected CompanyPracticaTestRepository repository;

	// Test data --------------------------------------------------------------


	@ParameterizedTest
	@CsvFileSource(resources = "/company/practica/publish-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int recordIndex, final String code) {

		super.signIn("company2", "company2");

		super.clickOnMenu("Company", "List Practica");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, code);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnSubmit("Publish");
		super.checkNotErrorsExist();

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/company/practica/publish-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test200Negative(final int recordIndex, final String code, final String title, final String summary, final String goals, final String estimatedTotalTime, final String draftMode) {
		// HINT: this test attempts to publish a tutorial with wrong data.

		super.signIn("company2", "company2");
		super.clickOnMenu("Company", "List Practica");

		super.checkListingExists();
		super.sortListing(0, "desc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("summary", summary);
		super.fillInputBoxIn("goals", goals);
		super.fillInputBoxIn("estimatedTotalTime", estimatedTotalTime);

		super.clickOnSubmit("Publish");

		super.checkErrorsExist();

		super.signOut();
	}

	@Test
	public void test300Hacking() {
		// HINT: this test tries to publish a tutorial with a role other than "Assistant".

		Collection<Practica> practicas;
		String param;

		practicas = this.repository.findManyPracticasByCompanyUsername("company2");
		for (final Practica practica : practicas)
			if (practica.getDraftMode() == true) {
				param = String.format("id=%d", practica.getId());

				super.checkLinkExists("Sign in");
				super.request("/assistant/tutorial/publish", param);
				super.checkPanicExists();

				super.signIn("administrator1", "administrator1");
				super.request("/assistant/tutorial/publish", param);
				super.checkPanicExists();
				super.signOut();

				super.signIn("administrator2", "administrator2");
				super.request("/assistant/tutorial/publish", param);
				super.checkPanicExists();
				super.signOut();

				super.signIn("auditor1", "auditor1");
				super.request("/assistant/tutorial/publish", param);
				super.checkPanicExists();
				super.signOut();
			}
	}
}
