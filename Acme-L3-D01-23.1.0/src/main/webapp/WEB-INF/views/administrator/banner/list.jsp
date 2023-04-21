<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>



<acme:list>
	<acme:list-column code="administrator.banner.label.slogan" path="slogan" width="20%"/>
	<acme:list-column code="administrator.banner.label.start" path="start" width="60%"/>
	<acme:list-column code="administrator.banner.label.end" path="end" width="20%"/>
</acme:list>


<br/><br/>
<acme:button code="administrator.banner.button.create" action="/administrator/banner/create"/>		

<br/><br/>