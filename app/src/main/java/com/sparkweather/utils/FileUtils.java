package com.sparkweather.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

/**
 * 日期：2019/3/11 11:37
 * 创建：李加蒙
 * 描述：
 */
public class FileUtils {
    public static File getFile(String filePath) {
        File file = null;
        if (!StringUtils.isE(filePath)) {
            file = new File(filePath);
        }
        return file;
    }

    public static File saveFile(String filePath, ResponseBody body) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        File file = null;
        try {
            if (StringUtils.isE(filePath)) {
                return null;
            }
            file = new File(filePath);
            if (file == null || !file.exists()) {
                file.createNewFile();
            }
            long fileSize = body.contentLength();
            long fileSizeDownload = 0;
            byte[] fileReader = new byte[4096];
            inputStream = body.byteStream();
            outputStream = new FileOutputStream(file);
            while (true) {
                int read = inputStream.read(fileReader);
                if (read == -1) break;
                outputStream.write(fileReader, 0, read);
                fileSizeDownload += read;
            }
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }
}
