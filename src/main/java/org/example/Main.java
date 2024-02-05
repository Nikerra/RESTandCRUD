package org.example;


import org.example.model.Car;
import org.example.repository.CarDbRepositoryImpl;
import org.example.repository.CarRepository;


import org.example.dbconnection.PostgresqlDB;
import org.example.service.CarService;
import org.example.service.CarServiceImpl;
import org.h2.tools.Server;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;



public class Main {
    public static void main(String[] args) throws Exception {
//        Server server = Server.createTcpServer(args).start();
//        PostgresqlDB.initDb();
//        Collection<Car> cars = new HashSet<>();
//        for (int i = 0; i < 5; i++){
//            cars.add(new Car("id-" + i, "new Model-" + i));
//        }
//
//        try(PostgresqlDB postgresqlDB = new PostgresqlDB()) {
//            CarRepository carRepository = new CarDbRepositoryImpl(postgresqlDB.getConnection());
//            CarService carService = new CarServiceImpl(carRepository);
//
//            carService.addCar("1", "Lada");
//            carService.addCar("2", "BMW");
//            carService.addCar("3", "Volvo");
//            carService.addCar("4", "Ford");
//
//            // Test check start
//            String readAllCarsSql = "SELECT * FROM car";
//            Statement statement = PostgresqlDB.getConnection().createStatement();
//            ResultSet resultSet = statement.executeQuery(readAllCarsSql);
//
//            while (resultSet.next()) {
//                String id = resultSet.getString(1);
//                String model = resultSet.getString(2);
//                System.out.println("id=" + id + "; model=" + model);
//            }
//
//
//            System.out.println("findByModel=" + carService.findByModel("Volvo"));
//            carService.findAll()
//                    .forEach(System.out::println);
//            System.out.println("delete car from id=" + carService.deleteCar("2"));
//            carService.findAll()
//                    .forEach(System.out::println);
//
//            System.out.println("delete all=" + carService.deleteAll());
//            System.out.println("findAll=" + carService.findAll());
//            carService.createAll(cars);
//            carService.findAll()
//                    .forEach(System.out::println);
//            // Test end
//        }
//        server.stop();
    }
}