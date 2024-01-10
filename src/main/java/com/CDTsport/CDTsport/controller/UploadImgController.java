package com.CDTsport.CDTsport.controller;


import com.CDTsport.CDTsport.entity.ProductImage;
import com.CDTsport.CDTsport.service.ProductImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class UploadImgController {
    private final ProductImgService productImgService;

    @PostMapping()
    public ResponseEntity<List<ProductImage>> uploadImg(@RequestParam("image")List<MultipartFile> files) throws IOException {
        return  ResponseEntity.ok(productImgService.uploadImg(files));
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) throws IOException {
        byte[] imageData = productImgService.getImage(id);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
    }
}
