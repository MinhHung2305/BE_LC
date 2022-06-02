package fis.com.vn.service.file.impl;

import fis.com.vn.config.MinioConfig;
import fis.com.vn.service.file.MinioService;
import fis.com.vn.util.MinioUtils;
import io.minio.*;
import io.minio.messages.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class MinioServiceImpl implements MinioService {

    private final MinioUtils minioUtil;
    private final MinioClient minioClient;
    private final MinioConfig minioConfig;

    public MinioServiceImpl(MinioUtils minioUtil, MinioClient minioClient, MinioConfig minioConfig) {
        this.minioUtil = minioUtil;
        this.minioClient = minioClient;
        this.minioConfig = minioConfig;
    }

    @Override
    public boolean bucketExists(String bucketName) {
        return minioUtil.bucketExists(bucketName);
    }


    @Override
    public void makeBucket(String bucketName) {
        minioUtil.makeBucket(bucketName);
    }

    @Override
    public List<String> listBucketName() {
        return minioUtil.listBucketNames();
    }

    @Override
    public List<Bucket> listBuckets() {
        return minioUtil.listBuckets();
    }

    @Override
    public boolean removeBucket(String bucketName) {
        return minioUtil.removeBucket(bucketName);
    }


    @Override
    public List<String> listObjectNames(String bucketName) {
        return minioUtil.listObjectNames(bucketName);
    }


    @Override
    public String putObject(MultipartFile file, String bucketName, String fileType) {
        try {
            bucketName = !"".equals(bucketName) ? bucketName : minioConfig.getBucketName();
            if (!this.bucketExists(bucketName)) {
                this.makeBucket(bucketName);
            }
            String fileName = file.getOriginalFilename().length() > 0 ? file.getOriginalFilename() : file.getName();

            String objectName = UUID.randomUUID().toString().replaceAll("-", "")
                    + fileName.substring(fileName.lastIndexOf("."));
            minioUtil.putObject(bucketName, file, objectName, fileType);
            return minioConfig.getEndpoint() + "/" + bucketName + "/" + objectName;
        } catch (Exception e) {
            e.printStackTrace();
            return " Upload failed ";
        }
    }

    @Override
    public InputStream downloadObject(String bucketName, String objectName) {
        return minioUtil.getObject(bucketName, objectName);
    }

    @Override
    public boolean removeObject(String bucketName, String objectName) {
        return minioUtil.removeObject(bucketName, objectName);
    }

    @Override
    public boolean removeListObject(String bucketName, List<String> objectNameList) {
        return minioUtil.removeObject(bucketName, objectNameList);
    }

    @Override
    public String getObjectUrl(String bucketName, String objectName) {
        return minioUtil.getObjectUrl(bucketName, objectName);
    }

    @Override
    public String getObjectUrlFile(String bucketName, String objectName, String fileType) {
        return minioUtil.getObjectUrlFile(bucketName, objectName, fileType);
    }

    @Override
    public String uploadFile(MultipartFile files) throws IOException {
        String fileType = FilenameUtils.getExtension(files.getOriginalFilename());
        String filePath = files.getOriginalFilename();
        byte[] file = files.getBytes();
        String fileName = files.getOriginalFilename();
        try {
            if (minioConfig.isUploadFile()) {
                if (filePath.startsWith("/")) {
                    filePath = filePath.substring(1);
                }
                createBucketIfNotExist(minioConfig.getBucketName(), false);
                minioClient.putObject(
                        PutObjectArgs.builder().bucket(minioConfig.getBucketName()).object(filePath).stream(new ByteArrayInputStream(file), file.length, -1).build());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return filePath;
    }

    private void createBucketIfNotExist(String bucketName, boolean objectLock) {
        try {
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!isExist) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).objectLock(objectLock).build());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getFile(String filePath) {
        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(minioConfig.getBucketName())
                        .object(filePath)
                        .build())) {
            if (stream == null) {
                try (InputStream streamAgain = minioClient.getObject(
                        GetObjectArgs.builder()
                                .bucket(minioConfig.getBucketName())
                                .object(File.separator + "crm-orig" + File.separator + filePath)
                                .build())) {
                    return IOUtils.toByteArray(streamAgain);
                }
            }
            return IOUtils.toByteArray(stream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public File getFileByPath(String filePath, String outputFileName) {
        File outputFile = new File(outputFileName);
        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(minioConfig.getBucketName())
                        .object(filePath)
                        .build())) {
            if (stream == null) {
                try (InputStream streamAgain = minioClient.getObject(
                        GetObjectArgs.builder()
                                .bucket(minioConfig.getBucketName())
                                .object(File.separator + "crm-orig" + File.separator + filePath)
                                .build())) {
                    try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                        fos.write(IOUtils.toByteArray(streamAgain));
                        }
                    return outputFile;
                }
            }
            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                fos.write(IOUtils.toByteArray(stream));
            }
            return outputFile;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}