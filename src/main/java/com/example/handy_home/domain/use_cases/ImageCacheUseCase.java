package com.example.handy_home.domain.use_cases;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.UUID;

@Service
public class ImageCacheUseCase {

    public File downloadImageToCache(String imageUrl) {
        File cachedFile = null;

        try {
            // URL 객체로 변환
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            // 이미지 다운로드
            InputStream inputStream = connection.getInputStream();

            String cacheDirPath = "floor_plan_parser/cache_images";
            File cacheDir = new File(cacheDirPath);
            if (!cacheDir.exists()) {
                cacheDir.mkdir();
            }

            String fileName = STR."\{UUID.randomUUID()}.jpg";
            cachedFile = new File(cacheDir, fileName);

            try (OutputStream outputStream = new FileOutputStream(cachedFile)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            inputStream.close();
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return cachedFile;
    }
}