/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.park.parkinglot.ejb;

import com.park.parkinglot.common.CarDetails;
import com.park.parkinglot.common.PhotoDetails;
import com.park.parkinglot.entity.Car;
import com.park.parkinglot.entity.Photo;
import com.park.parkinglot.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Rori
 */
@Stateless
public class CarBean {

    private static final Logger LOG = Logger.getLogger(CarBean.class.getName());

    @PersistenceContext
    private EntityManager em;

    private List<CarDetails> copyCarsToDetails(List<Car> cars) {
        List<CarDetails> detailList = new ArrayList<>();
        for (Car car : cars) {
            CarDetails carDetails = new CarDetails(car.getId(),
                    car.getLicensePlate(),
                    car.getParkingSpot(),
                    car.getUser().getUsername());
            detailList.add(carDetails);
        }
        return detailList;
    }

    public void createCar(String licensePlate, String parkingSpot, Integer userId) {
        LOG.info("createCar");
        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        User user = em.find(User.class, userId);
        user.getCars().add(car);
        car.setUser(user);

        em.persist(car);
    }

    public CarDetails findById(Integer carId) {
        Car car = em.find(Car.class, carId);
        return new CarDetails(car.getId(), car.getLicensePlate(), car.getParkingSpot(), car.getUser().getUsername());
    }

    public List<CarDetails> getAllCars() {
        LOG.info("getAllCars");
        try {
            TypedQuery<Car> typedQuery = em.createQuery("SELECT c FROM Car c", Car.class);
            List<Car> cars = typedQuery.getResultList();
            return copyCarsToDetails(cars);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void updateCar(int carId, String licensePlate, String parkingSpot, int userId) {
        LOG.info("updateCar");
        Car car = em.find(Car.class, carId);
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        User oldUser = car.getUser();
        oldUser.getCars().remove(car);

        User user = em.find(User.class, userId);
        user.getCars().add(car);
        car.setUser(user);
    }

    public void deleteCarsByIds(List<Integer> ids) {
        LOG.info("deleteCarsByIds");
        for(Integer id: ids){
            Car car = em.find(Car.class, id);
            em.remove(car);
        }
    }
    
    public void addPhotoToCar(Integer carId, String filename, String fileType, byte[] fileContent) {
        LOG.info("addPhotoToCar");
        Photo photo = new Photo();
        photo.setFilename(filename);
        photo.setFileType(fileType);
        photo.setFileContent(fileContent);
        
        Car car = em.find(Car.class, carId);
        car.setPhoto(photo);
        
        photo.setCar(car);
        em.persist(photo);
    }
    
    public PhotoDetails findPhotoByCarId(Integer carId) {
        TypedQuery<Photo> typedQuery = em.createQuery("SELECT p FROM Photo p WHERE p.car.id = :id", Photo.class)
                .setParameter("id", carId);
        List<Photo> photos = typedQuery.getResultList();
        if(photos.isEmpty()) {
            return null;
        }
        Photo photo = photos.get(0);
        PhotoDetails ceva = new PhotoDetails(photo.getId(), photo.getFilename(), photo.getFileType(), photo.getFileContent());
        return ceva;
    }
}
