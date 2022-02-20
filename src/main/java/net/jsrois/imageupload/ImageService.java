package net.jsrois.imageupload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }


    public void upload(Image file) {
        imageRepository.save(file);
    }

    public List<ImagePreview> getSummary() {
        return imageRepository.findAll().stream()
                .map(image -> new ImagePreview(image.getName(), image.getId(), image.getData().length))
                .collect(Collectors.toList());
    }

    public Image getImage(String id) {
        return imageRepository.findById(id).get();
    }
}
