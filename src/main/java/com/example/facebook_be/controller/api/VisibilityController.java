package com.example.facebook_be.controller.api;

import com.example.facebook_be.model.Visibility;
import com.example.facebook_be.service.VisibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("apiVisibilityController")
@RequestMapping("api/visibilities")
@CrossOrigin(origins = "*")
public class VisibilityController {
    @Autowired
    private VisibilityService visibilityService;

    @GetMapping("")
    public ResponseEntity<Iterable<Visibility>> getAllVisibilities() {

        List<Visibility> visibilities= (List<Visibility>) visibilityService.findAll();

        if (visibilities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(visibilities, HttpStatus.OK);
    }


}
