/*
 * package acme.testing.auditor.audit;
 * 
 * import java.util.Collection;
 * 
 * import org.junit.jupiter.api.Test;
 * import org.junit.jupiter.params.ParameterizedTest;
 * import org.junit.jupiter.params.provider.CsvFileSource;
 * import org.springframework.beans.factory.annotation.Autowired;
 * 
 * import acme.entities.Audit;
 * import acme.testing.TestHarness;
 * 
 * public class AuditorAuditPublishTest extends TestHarness {
 * 
 * @Autowired
 * protected AuditorAuditTestRepository repository;
 * 
 * // Test methods -----------------------------------------------------------
 * 
 * 
 * @ParameterizedTest
 * 
 * @CsvFileSource(resources = "/auditor/audit/publish-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
 * public void test100Positive(final int recordIndex, final String code, final String conclusion, final String strongPoints, final String weakPoints, final String draftMode, final String course) {
 * 
 * 
 * super.signIn("auditor2", "auditor2");
 * 
 * super.clickOnMenu("Auditor", "List my audits");
 * super.checkListingExists();
 * super.sortListing(0, "asc");
 * super.clickOnListingRecord(recordIndex);
 * super.checkFormExists();
 * 
 * super.fillInputBoxIn("code", code);
 * super.fillInputBoxIn("conclusion", conclusion);
 * super.fillInputBoxIn("course", course);
 * super.fillInputBoxIn("strongPoints", strongPoints);
 * super.fillInputBoxIn("weakPoints", weakPoints);
 * super.fillInputBoxIn("draftMode", draftMode);
 * super.fillInputBoxIn("course", course);
 * 
 * super.checkSubmitExists("Publish");
 * super.clickOnSubmit("Publish");
 * super.sortListing(0, "asc");
 * super.clickOnListingRecord(recordIndex);
 * super.checkFormExists();
 * 
 * super.checkInputBoxHasValue("code", code);
 * super.checkInputBoxHasValue("conclusion", conclusion);
 * super.checkInputBoxHasValue("strongPoints", strongPoints);
 * super.checkInputBoxHasValue("weakPoints", weakPoints);
 * super.checkInputBoxHasValue("draftMode", draftMode);
 * super.checkInputBoxHasValue("course", course);
 * super.checkNotSubmitExists("Publish");
 * 
 * super.signOut();
 * 
 * 
 * super.signIn("auditor1", "auditor1");
 * 
 * super.clickOnMenu("Auditor", "List my audits");
 * super.checkListingExists();
 * super.sortListing(0, "asc");
 * super.checkColumnHasValue(recordIndex, 0, code);
 * 
 * super.clickOnListingRecord(recordIndex);
 * super.checkFormExists();
 * super.clickOnSubmit("Publish");
 * super.checkNotErrorsExist();
 * 
 * super.signOut();
 * }
 * 
 * @Test
 * public void test300Hacking() {
 * Collection<Audit> audits;
 * String param;
 * 
 * audits = this.repository.findManyAuditByAuditorUsername("auditor1");
 * for (final Audit audit : audits) {
 * param = String.format("id=%d", audit.getId());
 * 
 * super.checkLinkExists("Sign in");
 * super.request("/auditor/audit/publish", param);
 * super.checkPanicExists();
 * 
 * super.signIn("administrator1", "administrator1");
 * super.request("/auditor/audit/publish", param);
 * super.checkPanicExists();
 * super.signOut();
 * 
 * super.signIn("administrator2", "administrator2");
 * super.request("/auditor/audit/publish", param);
 * super.checkPanicExists();
 * super.signOut();
 * 
 * super.signIn("lecturer2", "lecturer2");
 * super.request("/auditor/audit/publish", param);
 * super.checkPanicExists();
 * super.signOut();
 * 
 * }
 * }
 * }
 */
