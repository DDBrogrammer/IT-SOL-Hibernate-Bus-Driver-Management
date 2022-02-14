package controller;

import entity.Driver;
import entity.Roster;
import entity.Route;
import main.MainRun;
import service.DriverService;
import service.RosterService;
import service.RouteService;
import util.validate.ValidateRoster;

import java.util.*;

public class RosterController {
    private RosterService rosterService =new RosterService();
    private DriverService driverService =new DriverService();
    private ValidateRoster validateRoster=new ValidateRoster();
    private RouteService routeService =new RouteService();
   public void getInputEntity(){
       MainRun.driverController.printListData();
       System.out.println("Chọn lái xe từ danh sách đã cho");
       Driver driver= new Driver();
       do{int driverId=MainRun.helper.getInt("Nhập Id lái xe từ danh sách:");
           if(
                   driverService.findID(driverId)==null
           ){
               System.out.println("Không tìm thấy lái xe vừa chọn");
           }else{
               driver= driverService.findID(driverId);
               System.out.println("Đã chọn lái xe: "+ driver.toString());
               break;
           }
       }while(true);
      MainRun.routeController.printListData();
       int numberOfRoute;
       do{
           numberOfRoute=MainRun.helper.getInt("Nhập số lượng tuyến đường của lái xe");
           if(numberOfRoute> routeService.getListRoute().size()){
               System.out.println("Vượt quá số lượng tuyếm đường hiện có ("+ routeService.getListRoute().size()+")");
           }else{
               break;
           }
       }while(true);
       Map<Route,Integer> routeList=new HashMap<>();
       int routeId,totalRoute;
       for(int i=0;i<numberOfRoute;i++){
           do{
               routeId=MainRun.helper.getInt("Nhập id tuyến đường trong danh sách");
               if(!validateRoster.validateChosenSubject(routeList,routeId) &&
                       routeService.findID(routeId).getId()!=0){
                   System.out.println("Đã chọn tuyến đường: " + routeService.findID(routeId));
                   break;
               }else{
                   System.out.println("Tuyến đường vừa chọn đã được lưu cho người lái hoặc không có trong danh sách");
               }
           }while(true);
           do{
               totalRoute=MainRun.helper.getInt("Nhập tổng số lượt:");
               if(totalRoute<=15 ){
                    routeList.put(routeService.findID(routeId),totalRoute);
                   break;
               }else{
                   System.out.println("Số lượt phải nhỏ hơn 16");
               }
           }while(true);
           Roster roster=new Roster(driver, routeService.findID(routeId),totalRoute);
           rosterService.insert(roster);
       }

   }
    public void printListData(){
        List<Driver> rosterArrayList= rosterService.getListDriver();
        printInputListData(rosterArrayList);

    }

    public void printInputListData(List<Driver> drivers){
       if(!drivers.isEmpty()){
        for(Driver driver:drivers){
            System.out.println("                            ");
            System.out.println("Người lái: "+ driver.toString());
            for(Roster roster:rosterService.getListRosterByDriverID(driver.getId()) ){
                System.out.println("Tuyến đường: " + roster.getRoute().getId() +
                        ", Tổng số lượt: " + roster.getTotalRoute());
            }
            System.out.println("                            ");
        }
    }}

    public void printListSortByDriverName(){
        List<Driver> driverArrayList= rosterService.getListDriver();
        driverArrayList.sort(new DriverNameComparator());
        printInputListData(driverArrayList);
    }

    public void printListSortByTotalRoute(){
        List<Driver> driverArrayList= rosterService.getListDriver();
        driverArrayList.sort(new NumberOfRouteComparator());
        printInputListData(driverArrayList);
    }

    public void printListTotalRange(){
        List<Driver> drivers=rosterService.getListDriver();
        for(Driver driver:drivers){
            System.out.println("                            ");
            System.out.println("Người lái: "+ driver.toString());
            System.out.println("Tổng độ dài quãng đường: "+ getRange(rosterService.getListRosterByDriverID(driver.getId()))+ " (Km)" );

        }

    }

    public double getRange(ArrayList<Roster> rosterList ){
       double total=0;
        for (Roster ros:rosterList){
            total=total+ ros.getTotalRoute()*ros.getRoute().getDistance();
        }
        return total;
    }

    public boolean sortRosterManage(){
        int chose_4;
        boolean run=false;
        do {
            chose_4 =MainRun.helper.getInt("Nhập lựa chọn:\n"
                    + "[1] sắp xếp danh sách theo tên lái xe.\n"
                    + "[2] sắp xếp danh sách theo số lượng tuyến trong ngày.\n"
                    + "[3] Quay lại.\n"
            );
            if(chose_4>=1 && chose_4<=3){
                break;
            }
            System.out.println("Bạn phải nhập số nguyên từ 1 đến 3");
        } while(true);
        if(chose_4==1){
            printListSortByDriverName();
            run=true;
        }else if(chose_4==2){
            printListSortByTotalRoute();
            run=true;
        }
        return run;
    }

    public boolean rosterManage() {
        int chose_3;
        boolean run=false;
        do {
            chose_3 = MainRun.helper.getInt("Nhập lựa chọn:\n"
                    + "[1] Thêm bản ghi phân công.\n"
                    + "[2] Xem danh sách phân công.\n"
                    + "[3] Quay lại.\n"
            );
            if(chose_3>=1 && chose_3<=3){
                break;
            }
            System.out.println("Bạn phải nhập số nguyên từ 1 đến 3");
        } while(true);
        if(chose_3==1){
            getInputEntity();
            run=true;
        }else if(chose_3==2){
            printListData();
            run=true;
        }
        return run;
    }
}

class DriverNameComparator implements Comparator<Driver> {
    // override the compare() method
    public int compare(Driver r1, Driver r2)
    {
        if (r1.getName().equals(r2.getName()))
            return 0;
        else if (r2.getName().compareTo(r1.getName())>0)
            return 1;
        else
            return -1;
    }
}

class NumberOfRouteComparator implements Comparator<Driver> {
  private  RosterService rosterService=new RosterService();
    // override the compare() method
    public int compare(Driver r1, Driver r2)
    {
        return Integer.compare(rosterService.getListRosterByDriverID(r2.getId()).size(),
                rosterService.getListRosterByDriverID(r1.getId()).size());
    }
}
