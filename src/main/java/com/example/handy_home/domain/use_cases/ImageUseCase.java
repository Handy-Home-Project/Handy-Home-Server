package com.example.handy_home.domain.use_cases;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

@Service
public class ImageUseCase {
    public byte[] resizeImageWithAspectRatio(MultipartFile file, int maxSize) {
        try {
            // 1. MultipartFile을 BufferedImage로 변환
            InputStream inputStream = file.getInputStream();
            BufferedImage originalImage = ImageIO.read(inputStream);

            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();

            // 2. 가장 큰 변을 기준으로 512 이하로 조정
            double scaleFactor = (double) maxSize / Math.max(originalWidth, originalHeight);
            int newWidth = (int) (originalWidth * scaleFactor);
            int newHeight = (int) (originalHeight * scaleFactor);

            // 3. 이미지 크기 조절
            Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(scaledImage, 0, 0, null);
            g.dispose();

            // 4. 이미지 바이트 배열로 변환 (JPEG)
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "jpg", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
