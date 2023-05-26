<%--
- form.jsp
-
- Copyright (C) 2012-2023 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<h2>
	<acme:message code="auditor.auditorDashboard.form.title.statistics"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="auditor.auditorDashboard.form.label.total-number-of-audits"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfAudits}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="auditor.auditorDashboard.form.label.average-number-of-auditing-records"/>
		</th>
		<td>
			<acme:print value="${averageAuditingRecord}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="auditor.auditorDashboard.form.label.deviation-of-auditing-records"/>
		</th>
		<td>
			<acme:print value="${deviationAuditingRecord}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="auditor.auditorDashboard.form.label.maximum-number-of-auditing-records"/>
		</th>
		<td>
			<acme:print value="${maxAuditingRecord}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="auditor.auditorDashboard.form.label.minimum-number-of-auditing-records"/>
		</th>
		<td>
			<acme:print value="${minAuditingRecord}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="auditor.auditorDashboard.form.label.average-time-of-auditing-records"/>
		</th>
		<td>
			<acme:print value="${averageTimeAuditingRecords}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="auditor.auditorDashboard.form.label.time-deviation-of-auditing-records"/>
		</th>
		<td>
			<acme:print value="${deviationTimeAuditingRecords}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="auditor.auditorDashboard.form.label.maximum-time-of-auditing-records"/>
		</th>
		<td>
			<acme:print value="${maxTimeAuditingRecords}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="auditor.auditorDashboard.form.label.minimum-time-of-auditing-records"/>
		</th>
		<td>
			<acme:print value="${minTimeAuditingRecords}"/>
		</td>
	</tr>			
</table>

<acme:return/>