<%-- 
    Document   : manue
    Created on : Nov 2, 2021, 10:27:06 AM
    Author     : Rori
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="${pageContext.request.contextPath}">Parking lot</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav me-auto mb-2 mb-md-0">
          <li class="nav-item ${activePage eq 'Cars' ? 'active' : ''}">
            <a class="nav-link" href="${pageContext.request.contextPath}/Cars">Cars</a>
          </li>
          <li class="nav-item">
            <a class="nav-link also ${pageContext.request.requestURI eq '/ParkingLot/about.jsp' ? ' active' : ''}"
               href="${pageContext.request.contextPath}/about.jsp">About</a>
          </li>
          <li class="nav-item">
            <li class="nav-item ${activePage eq 'Users' ? 'active' : ''}">
            <a class="nav-link" href="${pageContext.request.contextPath}/Users">Users</a>
          </li>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled">Disabled</a>
          </li>
        </ul>
          <ul class=""navbar-nav d-flex">
              <li class=""nav-item">
                  <a class="nav-link" href="${pageContext.request.contextPath}/Login">Login</a>
              </li>
          </ul>
      </div>
    </div>
  </nav>
