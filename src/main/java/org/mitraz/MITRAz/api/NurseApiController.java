package org.mitraz.MITRAz.api;

import lombok.AllArgsConstructor;
import org.mitraz.MITRAz.api.request.LocationData;
import org.mitraz.MITRAz.api.request.LoginRequest;
import org.mitraz.MITRAz.model.nurse.Nurse;
import org.mitraz.MITRAz.model.nurse.NurseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("api/nurse")
@RestController
public class NurseApiController {

    NurseService nurseService;

    @PostMapping("login")
    ResponseEntity<Nurse> loginNurse(@RequestBody LoginRequest loginRequest) {

        System.out.println("Received login request: "+loginRequest.toString());
        return nurseService.loginNurse(loginRequest);
    }

    @PostMapping("save-location")
    boolean nurseSaveLocation(@RequestBody LocationData locationData) {

        return nurseService.nurseSaveLocation(locationData);
    }


}
