package com.park.parkinglot.servlet.car;

import com.park.parkinglot.common.UserDetails;
import com.park.parkinglot.ejb.CarBean;
import com.park.parkinglot.ejb.UserBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@ServletSecurity(value = @HttpConstraint(rolesAllowed = { "AdminRole" }))
@WebServlet(name = "AddCars", urlPatterns = {"/AddCars"})
public class AddCars extends HttpServlet {
    
    @Inject
    UserBean userBean;
    
    @Inject
    CarBean carBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<UserDetails> users = userBean.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/pages/addCars.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String licensePlate = request.getParameter("license_plate");
        String parkingSpot = request.getParameter("parking_spot");
        int ownerId = Integer.parseInt(request.getParameter("owner_id"));
        carBean.createCar(licensePlate, parkingSpot, ownerId);
        response.sendRedirect(request.getContextPath() + "/Cars");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
