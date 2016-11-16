package com.milos.neo4j.intrestinggeography;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class UserDataMultipart implements MultipartFile {

    private final byte[] imgContent;

    public byte[] getImgContent() {
        return imgContent;
    }

    public UserDataMultipart(byte[] imgContent) {
        this.imgContent = imgContent;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return imgContent;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getOriginalFilename() {
        return null;
    }

    @Override
    public long getSize() {
        if (imgContent == null) {
            return 0;
        } else {
            return imgContent.length;
        }
    }

    @Override
    public boolean isEmpty() {
        return imgContent != null && imgContent.length > 0;
    }

    @SuppressWarnings("resource")
    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        new FileOutputStream(dest).write(imgContent);
    }

}
