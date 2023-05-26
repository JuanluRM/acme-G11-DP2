
package acme.testing.company.session;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.Practica;
import acme.entities.Session;
import acme.testing.TestHarness;

public class CompanySessionShowTest extends TestHarness {
	// Internal state ---------------------------------------------------------

	@Autowired
	protected CompanySessionTestRepository repository;

	// Test data --------------------------------------------------------------


	@ParameterizedTest
	@CsvFileSource(resources = "/company/session/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int practicaRecordIndex, final int sessionRecordIndex, final String title, final String summary, final String startDate, final String endDate, final String link) {

		super.signIn("company1", "company1");

		super.clickOnMenu("Company", "List Practica");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(practicaRecordIndex);
		super.clickOnButton("Session");

		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(sessionRecordIndex);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("summary", summary);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("link", link);

		super.signOut();
	}

	@Test
	public void test200Negative() {
		// HINT: there aren't any negative tests for this feature because it's a listing
		// HINT+ that doesn't involve entering any data in any forms.
	}

	@Test
	public void test300Hacking() {
		// HINT: this test tries to list all of the tutorials using 
		// HINT+ inappropriate roles.
		final Collection<Practica> practicas;
		String param;
		Collection<Session> sessions;

		practicas = this.repository.findManyPracticasByCompanyUsername("company1");
		for (final Practica practica : practicas)
			if (!practica.getDraftMode() || practica.getDraftMode()) {
				sessions = this.repository.findManySessionsByPractica(practica);
				for (final Session session : sessions) {
					param = String.format("id=%d", session.getId());

					super.checkLinkExists("Sign in");
					super.request("/assistant/session-tutorial/list", param);
					super.checkPanicExists();

					super.signIn("administrator1", "administrator1");
					super.request("/assistant/session-tutorial/list", param);
					super.checkPanicExists();
					super.signOut();

					super.signIn("lecturer1", "lecturer1");
					super.request("/company/session/list", param);
					super.checkPanicExists();
					super.signOut();

				}

			}

	}
}
