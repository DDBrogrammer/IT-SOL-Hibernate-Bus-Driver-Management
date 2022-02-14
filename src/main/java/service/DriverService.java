package service;

import entity.Driver;
import repository.DriverDao;

import java.util.ArrayList;
import java.util.List;

public class DriverService {
    DriverDao driverDao = new DriverDao();
    public ArrayList<Driver> getListDriver() {
        return (ArrayList<Driver>)driverDao.getAll();
    }

    public Driver findID(int id) {
        return driverDao.findById(id);
    }

    public boolean insert(Driver driver) {
        List<Driver> drivers = driverDao.getAll();
        drivers.sort((o1, o2) -> o1.getId() < o2.getId() ? 1 : -1);
        int id = drivers.get(0).getId() + 1;
        driver.setId(id);
        if (driver.getName() == null) {
            return false;
        }
        return driverDao.insert(driver);
    }

    public boolean removeStudent(int id) {
        return driverDao.removeDriver(id);
    }

}
