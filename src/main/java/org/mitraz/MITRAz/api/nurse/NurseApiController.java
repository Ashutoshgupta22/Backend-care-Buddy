package org.mitraz.MITRAz.api.nurse;

import lombok.AllArgsConstructor;
import org.mitraz.MITRAz.api.LocationData;
import org.mitraz.MITRAz.model.nurse.NurseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("api/nurse")
@RestController
public class NurseApiController {

    NurseService nurseService;

    @PostMapping("save-location")
    boolean nurseSaveLocation(@RequestBody LocationData locationData) {

        return nurseService.nurseSaveLocation(locationData);
    }
}
