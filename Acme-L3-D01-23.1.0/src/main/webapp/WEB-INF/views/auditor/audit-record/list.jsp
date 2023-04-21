<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="auditor.auditRecord.list.label.subject" path="subject" width="80%"/>	
	<acme:list-column code="auditor.auditRecord.list.label.assessment" path="assessment" width="20%"/>
</acme:list>

<acme:button test="${showCreate}" code="auditor.auditRecord.list.button.create" action="/auditor/audit-record/create?id=${id}"/>