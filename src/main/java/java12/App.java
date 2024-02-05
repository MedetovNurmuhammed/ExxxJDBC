package java12;

import java12.model.User;
import java12.service.serviceImpl.UserService;
import java12.service.serviceImpl.UserServiceImpl;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        System.out.println("Hello World!");
        UserService userService = new UserServiceImpl();
       // userService.creatUsertable();
       // userService.save(new User("Nur", "Medetov", "Dev"));
      //  System.out.println(userService.findbyId(1L));
       // userService.deletebyId(1L);
       // userService.getUserRole("Dev");
        //System.out.println(userService.updateSrudentbyId(new User("hhh", "hh", "jj"), 2));


    }
}
