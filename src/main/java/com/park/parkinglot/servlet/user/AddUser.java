package com.park.parkinglot.servlet.user;

import com.park.parkinglot.ejb.UserBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.park.parkinglot.util.PasswordUtil;
import javax.inject.Inject;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
/**
 *
 * @author Rori
 */
@ServletSecurity(value = @HttpConstraint(rolesAllowed = { "AdminRole" }))
@WebServlet(name = "AddUser", urlPatterns = {"/AddUser"})
public class AddUser extends HttpServlet {

    @Inject 
    UserBean userBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/addUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String position = request.getParameter("position");
        
        String passwordSha256 = PasswordUtil.convertToSha256(password);
        
        userBean.createUser(username, email, passwordSha256, position);
        
        response.sendRedirect(request.getContextPath() + "/Users");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
