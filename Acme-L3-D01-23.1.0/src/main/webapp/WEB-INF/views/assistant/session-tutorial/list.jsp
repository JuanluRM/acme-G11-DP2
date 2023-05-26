<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>
<%@ page import = "java.io.*,java.util.Locale" %>
<%
   Locale locale = request.getLocale();
   String language = locale.getISO3Language();
%>

<acme:list>
  <acme:list-column code="assistant.session-tutorial.list.label.title" path="title" width="50%"/>
  <acme:list-column code="assistant.session-tutorial.list.label.type" path="tipoSesion" width="30%"/>
    <acme:list-column code="assistant.tutorial.list.label.isPublished" path="isPublished" width="20%"/>
</acme:list>

<acme:button test="${showCreate}" code="assistant.session-tutorial.list.button.create" action="/assistant/session-tutorial/create?masterId=${masterId}"/>