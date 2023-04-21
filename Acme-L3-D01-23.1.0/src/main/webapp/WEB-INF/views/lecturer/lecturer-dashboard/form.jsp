<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.label.totalNumberOfTheoricalLectures"/>
		</th>
		<td>
			<acme:print value= "${totalNTheoricalLectures}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.label.totalNumberOfHandsOnLectures"/>
		</th>
		<td>
			<acme:print value= "${totalNHandsOnLectures}"/>
		</td>
	</tr>
</table>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.label.averageEstimatedLearningTimeOfLectures"/>
		</th>
		<td>
			<acme:print value= "${averageEstimatedLearningTimeByLectures}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.label.deviationEstimatedLearningTimeOfLectures"/>
		</th>
		<td>
			<acme:print value= "${deviationEstimatedLearningTimeByLectures}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.label.minimumEstimatedLearningTimeOfLectures"/>
		</th>
		<td>
			<acme:print value= "${minimumEstimatedLearningTimeByLectures}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.label.maximumEstimatedLearningTimeOfLectures"/>
		</th>
		<td>
			<acme:print value= "${maximumEstimatedLearningTimeByLectures}"/>
		</td>
	</tr>
</table>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.label.averageEstimatedLearningTimeOfCourses"/>
		</th>
		<td>
			<acme:print value= "${averageEstimatedLearningTimeByCourses}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.label.deviationEstimatedLearningTimeOfCourses"/>
		</th>
		<td>
			<acme:print value= "${deviationEstimatedLearningTimeByCourses}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.label.minimumEstimatedLearningTimeOfCourses"/>
		</th>
		<td>
			<acme:print value= "${minimumEstimatedLearningTimeByCourses}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.label.maximumEstimatedLearningTimeOfCourses"/>
		</th>
		<td>
			<acme:print value= "${maximumEstimatedLearningTimeByCourses}"/>
		</td>
	</tr>
</table>


