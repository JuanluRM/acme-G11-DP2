<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="any.audit.list.label.code" path="code" width="25%"/>
	<acme:list-column code="any.audit.list.label.conclusion" path="conclusion" width="25%"/>
	<acme:list-column code="any.audit.list.label.strongPoints" path="strongPoints" width="25%"/>
	<acme:list-column code="any.audit.list.label.weakPoints" path="weakPoints" width="25%"/>
	
</acme:list>