package net.jsrois.imageupload.controllers;

import net.jsrois.imageupload.repositories.Image;
import net.jsrois.imageupload.services.ImagePreview;
import net.jsrois.imageupload.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class ImageController {

    private final ImageService imageService;


    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam MultipartFile multipartFile) throws IOException {

        Image file = new Image(multipartFile.getOriginalFilename(), multipartFile.getContentType(), multipartFile.getBytes());

        imageService.upload(file);

        return ResponseEntity.ok("Successfully uploaded file " + file.getName());
    }

    @GetMapping("/summary")
    public List<ImagePreview> getSummary() {

        return imageService.getSummary();
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) {
        Image image = imageService.getImage(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + image.getName() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, image.getType())
                .body(image.getData());
    }


}
