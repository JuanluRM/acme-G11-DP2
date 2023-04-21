<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="assistant.dashboard.form.label.theorical"/>
		</th>
		<td>
			<acme:print value= "${totalNumberOfTutorialsWithTheoricalCourses}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="assistant.dashboard.form.label.handsOn"/>
		</th>
		<td>
			<acme:print value= "${totalNumberOfTutorialsWithHandsOnlCourses}"/>
		</td>
	</tr>
</table>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="assistant.dashboard.form.label.average-tutorials-time"/>
		</th>
		<td>
			<acme:print value= "${averageLearningTimeByTutorials}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="assistant.dashboard.form.label.deviation-tutorials-time"/>
		</th>
		<td>
			<acme:print value= "${deviationLearningTimeByTutorials}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="assistant.dashboard.form.label.minimum-tutorials-time"/>
		</th>
		<td>
			<acme:print value= "${minimumLearningTimeByTutorials}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="assistant.dashboard.form.label.maximum-tutorials-time"/>
		</th>
		<td>
			<acme:print value= "${maximumLearningTimeByTutorials}"/>
		</td>
	</tr>
</table>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="assistant.dashboard.form.label.average-sessionTutorial-time"/>
		</th>
		<td>
			<acme:print value= "${averageLearningTimeBySessions}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="assistant.dashboard.form.label.deviation-sessionTutorial-time"/>
		</th>
		<td>
			<acme:print value= "${deviationLearningTimeBySessions}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="assistant.dashboard.form.label.minimum-sessionTutorial-time"/>
		</th>
		<td>
			<acme:print value= "${minimumLearningTimeBySessions}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="assistant.dashboard.form.label.maximum--sessionTutorial-time"/>
		</th>
		<td>
			<acme:print value= "${maximumLearningTimeBySessions}"/>
		</td>
	</tr>
</table>

