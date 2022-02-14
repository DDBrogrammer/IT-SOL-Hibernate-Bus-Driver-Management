package service;

import entity.Driver;
import entity.Roster;
import repository.RosterDao;

import java.util.ArrayList;
import java.util.List;

public class RosterService {
    RosterDao rosterDao = new RosterDao();
    public ArrayList<Roster> getListRoster() {
        for(Roster r:rosterDao.getAll()){
            System.out.println(r.toString());
        }
        for(Driver d: rosterDao.getAllDriver()){
            System.out.println(d.toString());
        }
        return (ArrayList<Roster>) rosterDao.getAll();
    }

    public Roster findID(int id) {
        return rosterDao.findById(id);
    }

    public boolean insert(Roster roster) {
        List<Roster> rosters = rosterDao.getAll();

        return rosterDao.insert(roster);
    }

    public ArrayList<Roster> getListRosterByDriverID(int id ){
        return (ArrayList<Roster>) rosterDao.getRostersByDriverId(id);
    }
    public List<Driver> getListDriver(){
        return rosterDao.getAllDriver();
    }

    public boolean removeRoster(int id) {
        return rosterDao.removeRoster(id);
    }

}