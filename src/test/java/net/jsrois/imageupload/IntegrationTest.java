package net.jsrois.imageupload;

import net.jsrois.imageupload.repositories.Image;
import net.jsrois.imageupload.repositories.ImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTest {

    public static final byte[] fakeImageData = "Fake Image Data".getBytes();
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ImageRepository imageRepository;

    @BeforeEach
    void setUp() {
        imageRepository.deleteAll();
    }

    @Test
    void allowsToUploadAFile() throws Exception {

        MockMultipartFile mockMultipartFile = new MockMultipartFile("multipartFile",
                "image.png",
                MediaType.IMAGE_PNG_VALUE,
                fakeImageData);

        mockMvc.perform(multipart("/upload").file(mockMultipartFile))
                .andExpect(status().isOk());

        assertThat(imageRepository.findAll(), hasSize(1));
        assertThat(imageRepository.findAll().get(0), allOf(
                hasProperty("name", equalTo("image.png")),
                hasProperty("type", equalTo("image/png")),
                hasProperty("data", equalTo(fakeImageData))
        ));
    }

    @Test
    void returnsTheExistingFilesAsASummary() throws Exception {

        var images = List.of(
            new Image("image1.png", "image/png",fakeImageData),
            new Image("image2.png", "image/png",fakeImageData),
            new Image("image3.png", "image/png",fakeImageData)
        );

        imageRepository.saveAll(images);


        mockMvc.perform(get("/summary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(3)))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name", equalTo("image1.png")))
                .andExpect(jsonPath("$[0].sizeInMB", equalTo(15)))
                .andExpect(jsonPath("$[1].id").exists())
                .andExpect(jsonPath("$[1].name", equalTo("image2.png")))
                .andExpect(jsonPath("$[1].sizeInMB", equalTo(15)))
                .andExpect(jsonPath("$[2].id").exists())
                .andExpect(jsonPath("$[2].name", equalTo("image3.png")))
                .andExpect(jsonPath("$[2].sizeInMB", equalTo(15)));
    }

    @Test
    void returnsAnImage() throws Exception {
        Image image = imageRepository.save(
                new Image("image1.png", "image/png", fakeImageData)
        );

        mockMvc.perform(get("/images/" + image.getId()))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "inline; filename=\"image1.png\""))
                .andExpect(header().string("Content-Type", "image/png"))
                .andExpect(content().bytes(fakeImageData));
    }
}
