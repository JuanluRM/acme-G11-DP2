/*
 * package acme.testing.auditor.audit;
 * 
 * import org.junit.jupiter.api.Test;
 * import org.junit.jupiter.params.ParameterizedTest;
 * import org.junit.jupiter.params.provider.CsvFileSource;
 * import org.springframework.beans.factory.annotation.Autowired;
 * 
 * import acme.entities.Audit;
 * import acme.testing.TestHarness;
 * 
 * public class AuditorAuditShowTest extends TestHarness {
 * 
 * @Autowired
 * protected AuditorAuditTestRepository repository;
 * 
 * 
 * @ParameterizedTest
 * 
 * @CsvFileSource(resources = "/auditor/audit/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
 * public void test100Positive(final int recordIndex, final String code, final String conclusion, final String strongPoints, final String weakPoints, final String draftMode, final String course) {
 * 
 * super.signIn("auditor2", "auditor2");
 * 
 * super.clickOnMenu("Auditor", "List my audits");
 * super.checkListingExists();
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
 * 
 * super.signOut();
 * }
 * 
 * @Test
 * public void test200Negative() {
 * // There are not any negative test cases for this feature.
 * }
 * 
 * @ParameterizedTest
 * 
 * @CsvFileSource(resources = "/auditor/audit/show-hacking.csv", encoding = "utf-8", numLinesToSkip = 1)
 * public void test300Hacking(final String code) {
 * 
 * final Audit audit = this.repository.findAuditByCode(code);
 * 
 * super.checkLinkExists("Sign in");
 * super.request("/auditor/audit/show", "id=" + audit.getId());
 * super.checkPanicExists();
 * 
 * super.checkLinkExists("Sign in");
 * super.signIn("lecturer1", "lecturer1");
 * super.request("/auditor/audit/show", "id=" + audit.getId());
 * super.checkPanicExists();
 * super.signOut();
 * 
 * super.checkLinkExists("Sign in");
 * super.signIn("administrator1", "administrator1");
 * super.request("/auditor/audit/show", "id=" + audit.getId());
 * super.checkPanicExists();
 * super.signOut();
 * 
 * super.checkLinkExists("Sign in");
 * super.signIn("administrator2", "administrator2");
 * super.request("/auditor/audit/show", "id=" + audit.getId());
 * super.checkPanicExists();
 * super.signOut();
 * 
 * }
 * }
 */
