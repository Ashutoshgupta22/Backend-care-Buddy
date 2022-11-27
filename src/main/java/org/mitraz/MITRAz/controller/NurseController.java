package org.mitraz.MITRAz.controller;

import org.mitraz.MITRAz.exception.NurseNotFoundException;
import org.mitraz.MITRAz.model.nurse.Nurse;
import org.mitraz.MITRAz.model.nurse.NurseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NurseController {

    @Autowired
    private NurseDao nurseDao ;

    @GetMapping("/nurse/get-all")
    public List<Nurse> getAllNurse() {
        return nurseDao.getAllNurse();
    }

    @PostMapping("/nurse/save")
    public Nurse postNurse(@RequestBody Nurse nurse) {
        return nurseDao.saveNurse(nurse);
    }

    @PutMapping("nurse/update/{id}")
    public Nurse updateNurse(@PathVariable("id") int id,@RequestBody Nurse nurse) {

        if(!nurseDao.findById(id))
            throw new NurseNotFoundException();

        nurse.setId(id);
        return nurseDao.saveNurse(nurse);
    }
}
