package util.validate;

import entity.Route;
import repository.RosterDao;

import java.util.Map;

public class ValidateRoster {
private RosterDao rosterDao=new RosterDao();

    public boolean validateChosenSubject(Map<Route,Integer>  routeList, int routeId){
        boolean checkRouteSubject=false;
        for (Map.Entry<Route,Integer> entry : routeList.entrySet())
           if(entry.getKey().getId()==routeId){
               checkRouteSubject= true;
           }
        return checkRouteSubject;
    }


}
