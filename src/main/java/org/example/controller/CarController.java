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

        String model = req.getParameter("model");
        PostgresqlDB postgresqlDB = new PostgresqlDB();

            try {
                Class.forName("org.postgresql.Driver");
                CarRepository carRepository = new CarDbRepositoryImpl(postgresqlDB.getConnection());
                CarService carService = new CarServiceImpl(carRepository);

                carService.addCar(model);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {

        String model = req.getParameter("model");
        PostgresqlDB postgresqlDB = new PostgresqlDB();

        try {
            Class.forName("org.postgresql.Driver");
            CarRepository carRepository = new CarDbRepositoryImpl(postgresqlDB.getConnection());
            CarService carService = new CarServiceImpl(carRepository);

            carService.deleteCar(model);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {

        String model = req.getParameter("model");
        Long id = Long.valueOf(req.getParameter("id"));
        PostgresqlDB postgresqlDB = new PostgresqlDB();

        try {
            Class.forName("org.postgresql.Driver");
            CarRepository carRepository = new CarDbRepositoryImpl(postgresqlDB.getConnection());
            CarService carService = new CarServiceImpl(carRepository);

            carService.editModel(id, model);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
