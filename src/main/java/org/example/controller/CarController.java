package org.example.controller;


import org.example.model.Car;
import org.example.repository.CarDbRepositoryImpl;
import org.example.service.CarService;
import org.example.service.CarServiceImpl;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.Set;

@WebServlet("/")
public class CarController extends HttpServlet {
    CarDbRepositoryImpl carDbRepository = new CarDbRepositoryImpl();

    public CarController() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path = req.getRequestURI();

        if (path.endsWith("/api")) {
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        } else if (path.endsWith("/api/find")){
            try{
                CarService carService = new CarServiceImpl(carDbRepository.initConnection());
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

            try {
                JSONObject jo = (JSONObject) JSONConverter(req);
                String model = (String) jo.get("model");

                CarService carService = new CarServiceImpl(carDbRepository.initConnection());
                carService.addCar(model);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {

        try {
            JSONObject jo = (JSONObject) JSONConverter(req);
            String model = (String) jo.get("model");

            CarService carService = new CarServiceImpl(carDbRepository.initConnection());
            carService.deleteCar(model);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {

        try {
            JSONObject jo = (JSONObject) JSONConverter(req);
            String model = (String) jo.get("model");
            Long id = Long.valueOf((String) jo.get("id"));

            CarService carService = new CarServiceImpl(carDbRepository.initConnection());
            carService.editModel(id, model);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private Object JSONConverter(HttpServletRequest req) throws IOException, ParseException {
        BufferedReader reader = req.getReader();
        int intValueOfChar;
        StringBuilder jsonString = new StringBuilder();
        while ((intValueOfChar = reader.read()) != -1) {
            jsonString.append((char) intValueOfChar);
        }
        return new JSONParser().parse(String.valueOf(jsonString));
    }
}
