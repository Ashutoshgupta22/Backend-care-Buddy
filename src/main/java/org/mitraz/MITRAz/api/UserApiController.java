package org.mitraz.MITRAz.api;

import lombok.AllArgsConstructor;
import org.mitraz.MITRAz.api.request.BookServiceRequest;
import org.mitraz.MITRAz.api.request.LocationData;
import org.mitraz.MITRAz.model.nurse.Nurse;
import org.mitraz.MITRAz.model.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("api/user")
public class UserApiController {

    UserService userService;

    @PostMapping("save-location")
    public boolean saveUserLocation(@RequestBody LocationData locationData) {

        return userService.saveLocation(locationData);
    }

    @GetMapping("get-user/{email}")
    public Map<String,Object> getUserData( @PathVariable String email) {

        return userService.getUserData(email);
    }

    @PostMapping("book-service")
    public Nurse bookService(@RequestBody BookServiceRequest request){

        System.out.println("book service api call received for "+request.getEmail());
        return userService.bookService(request.getEmail());
    }

}
