/*
 * package acme.testing.auditor.auditRecord;
 * 
 * import org.junit.jupiter.params.ParameterizedTest;
 * import org.junit.jupiter.params.provider.CsvFileSource;
 * import org.springframework.beans.factory.annotation.Autowired;
 * 
 * import acme.testing.TestHarness;
 * import acme.testing.auditor.audit.AuditorAuditTestRepository;
 * 
 * public class AuditorAuditRecordShowTest extends TestHarness {
 * 
 * @Autowired
 * protected AuditorAuditTestRepository repository;
 * 
 * 
 * @ParameterizedTest
 * 
 * @CsvFileSource(resources = "/auditor/audit-record/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
 * public void test100Positive(final int masterIndex, final int recordIndex, final String subject, final String assessment, final String startAudition, final String endAudition, final String mark, final String link) {
 * 
 * super.signIn("auditor1", "auditor1");
 * 
 * super.clickOnMenu("Auditor", "List my audits");
 * super.checkListingExists();
 * super.sortListing(0, "asc");
 * super.clickOnListingRecord(masterIndex);
 * super.clickOnButton("Audit Record");
 * super.checkListingExists();
 * super.sortListing(0, "asc");
 * super.clickOnListingRecord(recordIndex);
 * super.checkFormExists();
 * 
 * super.checkInputBoxHasValue("subject", subject);
 * super.checkInputBoxHasValue("assessment", assessment);
 * super.checkInputBoxHasValue("startAudition", startAudition);
 * super.checkInputBoxHasValue("endAudition", endAudition);
 * super.checkInputBoxHasValue("link", link);
 * super.checkInputBoxHasValue("mark", mark);
 * 
 * super.signOut();
 * }
 */
/*
 * @Test
 * public void test200Negative() {
 * // There are not any negative test cases for this feature.
 * }
 */

/*
 * @ParameterizedTest
 * 
 * @CsvFileSource(resources = "/auditor/audit-record/show-hacking.csv", encoding = "utf-8", numLinesToSkip = 1)
 * public void test300Hacking(final String code) {
 * 
 * final Audit audit = this.repository.findAuditByCode(code);
 * 
 * super.checkLinkExists("Sign in");
 * super.request("/auditor/audit-record/show", "id=" + audit.getId());
 * super.checkPanicExists();
 * 
 * super.checkLinkExists("Sign in");
 * super.signIn("lecturer1", "lecturer1");
 * super.request("/auditor/audit-record/show", "id=" + audit.getId());
 * super.checkPanicExists();
 * super.signOut();
 * 
 * super.checkLinkExists("Sign in");
 * super.signIn("administrator1", "administrator1");
 * super.request("/auditor/audit-record/show", "id=" + audit.getId());
 * super.checkPanicExists();
 * super.signOut();
 * 
 * super.checkLinkExists("Sign in");
 * super.signIn("administrator2", "administrator2");
 * super.request("/auditor/audit-record/show", "id=" + audit.getId());
 * super.checkPanicExists();
 * super.signOut();
 */
