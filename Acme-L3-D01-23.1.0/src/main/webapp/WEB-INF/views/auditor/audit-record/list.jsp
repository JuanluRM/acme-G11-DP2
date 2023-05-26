<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>


<acme:list>
	<acme:list-column code="auditor.auditrecord.list.label.correction" path="correction" width="20%"/>
	<acme:list-column code="auditor.auditrecord.list.label.subject" path="subject" width="50%"/>	
	<acme:list-column code="auditor.auditrecord.list.label.assessment" path="assessment" width="30%"/>
</acme:list>

<jstl:choose>
	<jstl:when test="${showCreate==true}">
		<acme:button code="auditor.auditrecord.list.button.create" action="/auditor/audit-record/create?id=${id}"/>
	</jstl:when>
<%-- 	<jstl:otherwise> --%>
<%-- 		<acme:button code="auditor.auditrecord.list.button.correction" action="/auditor/audit-record/correct?id=${id}"/> --%>
<%-- 	</jstl:otherwise> --%>
</jstl:choose>