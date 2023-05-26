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
	<acme:input-textbox code="administrator.bulletin.list.label.instantiationMoment" path="instantiationMoment" readonly="false"/>
	<acme:input-textbox code="administrator.bulletin.list.label.title" path="title" readonly="false"/>
	<acme:input-textbox code="administrator.bulletin.list.label.message" path="message" readonly="false"/>
	<acme:input-checkbox code="administrator.bulletin.list.label.critical" path="critical" readonly="false"/>
	<acme:input-url code="administrator.bulletin.list.label.link" path="link" readonly="false"/>
	
</acme:form>
