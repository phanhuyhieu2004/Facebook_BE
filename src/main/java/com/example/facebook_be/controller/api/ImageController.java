package com.example.facebook_be.controller.api;

import com.example.facebook_be.dto.ImageForm;
import com.example.facebook_be.dto.PostForm;
import com.example.facebook_be.model.Image;
import com.example.facebook_be.model.Post;
import com.example.facebook_be.repository.IImageRepository;
import com.example.facebook_be.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController("apiImageController")
@RequestMapping("api/images")
@CrossOrigin(origins = "*")
public class ImageController {
    @Autowired
    private IImageRepository iImageRepository;
    @Autowired
    private ImageService imageService;
    @GetMapping("")
    public ResponseEntity<Iterable<Image>> getAllImages(Long post_id) {

        List<Image> images= (List<Image>) iImageRepository.findByPostId(post_id);

        if (images.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(images, HttpStatus.OK);
    }


    @PostMapping

    public ResponseEntity<?> saveImage(@ModelAttribute ImageForm postForm) throws IOException {
        return new ResponseEntity<>(imageService.saveImage(postForm), HttpStatus.CREATED);
    }



}