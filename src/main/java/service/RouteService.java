package service;

import entity.Route;
import repository.RouteDao;

import java.util.ArrayList;
import java.util.List;

public class RouteService {

    RouteDao routeDao = new RouteDao();
    public ArrayList<Route> getListRoute() {
        return (ArrayList<Route>) routeDao.getAll();
    }

    public Route findID(int id) {
        return routeDao.findById(id);
    }

    public boolean insert(Route route) {
        List<Route> drivers = routeDao.getAll();
        drivers.sort((o1, o2) -> o1.getId() < o2.getId() ? 1 : -1);
        int id = drivers.get(0).getId() + 1;
        route.setId(id);
        return routeDao.insert(route);
    }



}
