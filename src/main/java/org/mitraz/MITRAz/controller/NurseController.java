package org.mitraz.MITRAz.controller;

import org.mitraz.MITRAz.exception.NurseNotFoundException;
import org.mitraz.MITRAz.model.nurse.Nurse;
import org.mitraz.MITRAz.model.nurse.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NurseController {

    @Autowired
    private NurseService nurseService;


}
