package com.jadilumi.library.service;

import com.jadilumi.library.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UtilsService {


    public void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new CustomException("File is empty.", HttpStatus.BAD_REQUEST);
        }

        long MAX_FILE_SIZE = 10 * 1024 * 1024;
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new CustomException("File size exceeds the maximum limit.", HttpStatus.BAD_REQUEST);
        }

        String ALLOWED_CONTENT_TYPES = "image/png";
        if (!ALLOWED_CONTENT_TYPES.equals(file.getContentType())) {
            throw new CustomException("Invalid file type.", HttpStatus.BAD_REQUEST);
        }

    }
}
