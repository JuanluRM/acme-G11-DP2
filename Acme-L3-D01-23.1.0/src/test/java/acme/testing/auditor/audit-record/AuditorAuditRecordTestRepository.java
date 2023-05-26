@Query("select a from Audit a where a.code = :code")
	Audit findAuditByCode(String code);

	@Query("select a from Audit a where a.auditor.userAccount.username = :username")
	List<Audit> findAllAuditsByAuditor(String username);

	@Query("select r from AuditingRecord r where r.audit.code = :code")
	List<AuditingRecord> findAllRecordsByAuditCode(String code);

	@Query("select r from AuditingRecord r where r.audit.code = :code and r.draftMode = true")
	List<AuditingRecord> findAllUpdatableRecordsByAuditCode(String code);
