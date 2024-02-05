package org.example.controller;

import org.example.dbconnection.PostgresqlDB;
import org.example.model.Car;
import org.example.repository.CarDbRepositoryImpl;
import org.example.repository.CarRepository;
import org.example.service.CarService;
import org.example.service.CarServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.Set;

@WebServlet("/")
public class CarController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI();

        if (path.endsWith("/api")) {
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        } else if (path.endsWith("/api/find")){
            PostgresqlDB postgresqlDB = new PostgresqlDB();
            try{
                Class.forName("org.postgresql.Driver");
                CarRepository carRepository = new CarDbRepositoryImpl(postgresqlDB.getConnection());
                CarService carService = new CarServiceImpl(carRepository);

                String car = req.getParameter("model");
                Set<Car> carSet = carService.findByModel(car);

                req.setAttribute("car", carSet);
                getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {


        String path = req.getRequestURI();

        String model = req.getParameter("model");

        System.out.println("path=" + path);

        System.out.println("model=" + model);

        PostgresqlDB postgresqlDB = new PostgresqlDB();

            try {
                Class.forName("org.postgresql.Driver");
                CarRepository carRepository = new CarDbRepositoryImpl(postgresqlDB.getConnection());
                CarService carService = new CarServiceImpl(carRepository);

                if (path.endsWith("/api")) {
                    carService.addCar(model);
                }else if (path.endsWith("/api/update")) {
                    Long id = Long.valueOf(req.getParameter("id"));
                    carService.editModel(id, model);
                }else if (path.endsWith("/api/delete")) {
                    carService.deleteCar(model);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

    }
}
