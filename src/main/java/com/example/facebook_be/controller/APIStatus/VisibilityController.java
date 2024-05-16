package com.example.facebook_be.controller.APIStatus;

import com.example.facebook_be.model.Visibility;
import com.example.facebook_be.service.status.VisibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/Visibility")
public class VisibilityController {
    @Autowired
    private VisibilityService visibilityService;

    @GetMapping
    public Iterable<Visibility> getAllCategories() {
        return visibilityService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Visibility>> getCategoryById(@PathVariable long id) {
        Optional<Visibility> visibility = visibilityService.findById(id);
        return visibility.isPresent() ? ResponseEntity.ok().body(visibility) : ResponseEntity.notFound().build();
    }

}
