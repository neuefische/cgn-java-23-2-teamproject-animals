package de.neuefische.backend;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final Cloudinary cloudinary;

    public String getImageURL(MultipartFile multipartFile) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("folder", "images");
            Map uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(), params);
            String imageURL = uploadResult.get("url").toString();
            return imageURL;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
