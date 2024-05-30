package com.example.facebook_be.service;

import com.example.facebook_be.dto.ImageForm;
import com.example.facebook_be.model.Image;
import com.example.facebook_be.repository.IImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ImageService implements IImageService{
    @Value("${file-upload}")
    private String fileUpload;

    @Autowired
    private IImageRepository iImageRepository;
    @Override
    public Iterable<Image> findAll() {
        return iImageRepository.findAll();
    }

    @Override
    public Optional<Image> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Image save(Image image) {
        return iImageRepository.save(image);
    }

    @Override
    public void remove(Long id) {

    }

    public List<Image> saveImage(ImageForm imageForm) throws IOException {


        List<Image> savedImages = new ArrayList<>();

        // Kiểm tra nếu mảng images rỗng bằng cách kiểm tra độ dài của nó
        if (imageForm.getImages().length == 0) {
            System.out.println("No images to process");
            return savedImages; // Trả về danh sách rỗng nếu không có ảnh
        }

        for (MultipartFile file : imageForm.getImages()) {
            System.out.println("Processing file: " + file.getOriginalFilename());

            String fileName = file.getOriginalFilename();
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));

            Image image = new Image();
            image.setName(fileName);
            image.setAccount(imageForm.getAccount());
            image.setPost(imageForm.getPost());

            savedImages.add(iImageRepository.save(image));
        }

        return savedImages;
    }







}
