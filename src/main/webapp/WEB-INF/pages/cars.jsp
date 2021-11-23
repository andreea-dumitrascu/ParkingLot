<%-- 
    Document   : cars
    Created on : Nov 2, 2021, 11:00:44 AM
    Author     : Rori
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:pageTemplate pageTitle="Cars">
    <h1>Cars</h1>
    <form method="POST" action="${pageContext.request.contextPath}/Cars">
        <a role="button" class="btn btn-primary" href="${pageContext.request.contextPath}/AddCars">Add Cars</a>
        <button class="btn btn-danger" type="submit">Delete Cars</button>
        <c:forEach var="car" items="${cars}" varStatus="status">
            <div class="row">
                <div class="col-md">
                    <input type="checkbox" name="car_ids" value="${car.id}" />
                </div>
                <div class="col-md-3">
                    ${car.licensePlate}
                </div>
                <div class="col-md-3">
                    ${car.parkingSpot}
                </div>
                <div class="col-md-3">
                    ${car.username}
                </div>
                <div class="col-md-2">
                    <a role="button" class="btn btn-secondary" href="${pageContext.request.contextPath}/EditCars?id=${car.id}">Edit Car</a>
                </div>
            </div>
        </c:forEach>
    </form>
    <h5>Free parking spots: ${numberOfFreeParkingSpots}</h5>
</t:pageTemplate>
