<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>

	<acme:hidden-data path="id"/>
	<jstl:if test="${_command != 'create'}">
		<acme:input-moment code="administrator.banner.label.instantiationMoment" path="instantiationMoment" readonly="true"/>
	</jstl:if>
	<acme:input-moment code="administrator.banner.label.instantiationMoment" path="instantiationMoment" readonly="true"/>
	<acme:input-moment code="administrator.banner.label.start" path="start"/>
	<acme:input-moment code="administrator.banner.label.end" path="end"/>	
	<acme:input-url code="administrator.banner.label.picture" path="picture"/>
	<acme:input-textbox code="administrator.banner.label.slogan" path="slogan"/>
	<acme:input-url code="administrator.banner.label.link" path="link"/>
	
	<acme:submit test="${_command == 'create'}" code="administrator.banner.button.create" action="/administrator/banner/create"/>		
	
	<jstl:if test="${_command != 'create'}">	
		<acme:submit code="administrator.banner.button.update" action="/administrator/banner/update"/>
		<acme:submit code="administrator.banner.button.delete" action="/administrator/banner/delete"/>	
	</jstl:if>
</acme:form>