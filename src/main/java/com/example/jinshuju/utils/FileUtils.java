package com.example.jinshuju.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 上传文件工具类
 */
public class FileUtils {

    /**
     * 保存上传的文件到指定路径
     *
     * @param file
     * @param filePath
     * @return
     */
    public static String uploadMultipartFile(MultipartFile file, String filePath) {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            String newFileName = UUIDUtils.getUUID() + "." + fileType;

            //文件夹路径
            File file1 = new File(filePath);
            if (!file1.exists()) {
                file1.mkdirs();
            }

            String newFilePath = filePath + File.separator + newFileName;
            File newFile = new File(newFilePath);

            //检查上级文件夹
            if (!newFile.getParentFile().exists()) {
                newFile.getParentFile().mkdirs();
            }
            try {
                file.transferTo(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newFileName;
        }
        return null;
    }

    /**
     * MultipartFile 转 File
     *
     * @param file
     * @throws Exception
     */
    public static File multipartFileToFile(MultipartFile file) throws Exception {
        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    /**
     * InputStream 转 File
     *
     * @param ins
     * @param file
     */
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
