package org.mitraz.MITRAz.api.user;

import lombok.AllArgsConstructor;
import org.mitraz.MITRAz.api.LocationData;
import org.mitraz.MITRAz.security.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("{email}")
    public Map<String,Object> getUserData( @PathVariable String email) {

        return userService.getUserData(email);
    }

    @GetMapping("{email}/book-service")
    public ArrayList<Double> bookService(@PathVariable String email){


        return userService.bookService(email);
    }

}
