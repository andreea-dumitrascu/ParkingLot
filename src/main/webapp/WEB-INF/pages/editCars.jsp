<%-- 
    Document   : addCars
    Created on : Nov 23, 2021, 10:02:34 AM
    Author     : Rori
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="EditCars">
    <h1>Add Cars Page</h1>
    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/EditCars">
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="licensePlate">License plate</label>
                <input type="text" class="form-control" id="licensePlate" name="license_plate" placeholder="" value="${car.licensePlate}" required>
                <div class="invalid-feedback"> License plate is required. </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="parkingSpot">Parking Spot</label>
                <input type="text" class="form-control" id="parkingSpot" name="parking_spot" placeholder="" value="${car.parkingSpot}" required>
                <div class="invalid-feedback"> Parking spot is required. </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="owner">Owner</label>
                <select class="custom-select d-block w-100" id="owner" name="owner_id" required>
                    <option value="">Choose...</option>
                    <c:forEach var="user" items="${users}" varStatus="status">
                        <option value="${user.id}" ${car.username eq user.username ? 'selected' : ''}>${user.username}</option>
                    </c:forEach>
                </select>
                <div class="invalid-feedback"> Please select an owner. </div>
            </div>
        </div>
        <input type="hidden" name="car_id" value="${car.id}" />
        <button class="btn btn-primary btn-lg btn-block" type="submit">Save</button>
    </form>
    <script>
        (function () {
            'use strict';

            window.addEventListener('load', function () {
                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.getElementsByClassName('needs-validation');

                // Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function (form) {
                    form.addEventListener('submit', function (event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                })
            }, false);
        }());
    </script>
</t:pageTemplate>