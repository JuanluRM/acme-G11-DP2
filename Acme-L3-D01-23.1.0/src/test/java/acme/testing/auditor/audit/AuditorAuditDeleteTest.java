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
 * public class AuditorAuditDeleteTest extends TestHarness {
 * 
 * @Autowired
 * protected AuditorAuditTestRepository repository;
 * 
 * 
 * @ParameterizedTest
 * 
 * @CsvFileSource(resources = "/auditor/audit/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
 * public void test100Positive(final int recordIndex, final String code) {
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
 * super.clickOnSubmit("Delete");
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
 * super.request("/auditor/audit/delete", param);
 * super.checkPanicExists();
 * 
 * super.signIn("administrator1", "administrator1");
 * super.request("/auditor/audit/delete", param);
 * super.checkPanicExists();
 * super.signOut();
 * 
 * super.signIn("administrator2", "administrator2");
 * super.request("/auditor/audit/delete", param);
 * super.checkPanicExists();
 * super.signOut();
 * 
 * super.signIn("lecturer2", "lecturer2");
 * super.request("/auditor/audit/delete", param);
 * super.checkPanicExists();
 * super.signOut();
 * 
 * }
 * }
 * }
 */
