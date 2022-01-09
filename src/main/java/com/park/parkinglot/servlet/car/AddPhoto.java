package com.park.parkinglot.servlet.car;

import com.park.parkinglot.common.CarDetails;
import com.park.parkinglot.ejb.CarBean;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet(name = "AddPhoto", urlPatterns = {"/Cars/AddPhoto"})
public class AddPhoto extends HttpServlet {

    @Inject 
    CarBean carBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer carId = Integer.parseInt(request.getParameter("id"));
        CarDetails car  = carBean.findById(carId);
        request.setAttribute("car", car);
        request.getRequestDispatcher("/WEB-INF/pages/addPhoto.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String carIdAsString = request.getParameter("car_id");
        Integer carId = Integer.parseInt(carIdAsString);
        
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        String fileType = filePart.getContentType();
        long fileSize = filePart.getSize();
        byte[] fileContent = new byte[(int) fileSize];
        filePart.getInputStream().read(fileContent);
        
        carBean.addPhotoToCar(carId, fileName, fileType, fileContent);
        response.sendRedirect(request.getContextPath() + "/Cars");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
