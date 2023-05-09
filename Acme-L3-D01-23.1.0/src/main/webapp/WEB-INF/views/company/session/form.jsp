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

<acme:form>
    <acme:input-textbox code="company.session.form.label.title" path="title"/>
    <acme:input-textbox code="company.session.form.label.summary" path="summary"/>
    <acme:input-moment code="company.session.form.label.startDate" path="startDate"/>
    <acme:input-moment code="company.session.form.label.endDate" path="endDate"/>
    <acme:input-url code="company.session.form.label.link" path="link"/>

    <jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete') && draftMode == true}">
			<acme:submit code="company.session.form.button.update" action="/company/session/update"/>
			<acme:submit code="company.session.form.button.delete" action="/company/session/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create' && draftMode == true}">
			<acme:submit code="company.session.form.button.create" action="/company/session/create?practicaId=${practicaId}"/>
		</jstl:when>
		<jstl:when test="${_command == 'create' && draftMode == false}">
			<acme:input-checkbox code="company.session.form.button.confirmation" path="confirmation"/>
			<acme:submit code="company.session.form.button.createAddendum" action="/company/session/create?practicaId=${practicaId}"/>
		</jstl:when>
					
	</jstl:choose>
</acme:form>