/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.park.parkinglot.servlet.car;

import com.park.parkinglot.common.CarDetails;
import com.park.parkinglot.ejb.CarBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rori
 */
@DeclareRoles({"AdminRole", "ClientRole"})
@ServletSecurity(
        value = @HttpConstraint(
                rolesAllowed = {"AdminRole"}
        )
//        ,
//        httpMethodConstraints = {
//            @HttpMethodConstraint(value = "POST", rolesAllowed = {"AdminRole"})
//        }
)
@WebServlet(name = "Cars", urlPatterns = {"/Cars"})
public class Cars extends HttpServlet {

    @Inject
    private CarBean carBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("activePage", "Cars");
        request.setAttribute("numberOfFreeParkingSpots", 10);

        List<CarDetails> cars = carBean.getAllCars();
        request.setAttribute("cars", cars);
        request.getRequestDispatcher("/WEB-INF/pages/cars.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] carIdsAsString = request.getParameterValues("car_ids");
        if (carIdsAsString != null) {
            List<Integer> carIds = new ArrayList<>();
            for (String carIdAsString : carIdsAsString) {
                carIds.add(Integer.parseInt(carIdAsString));
            }
            carBean.deleteCarsByIds(carIds);
        }
    }

    @Override
    public String getServletInfo() {
        return "Cars v1.0";
    }
    
}
