package fis.com.vn.service.file;

import io.minio.messages.Bucket;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface MinioService {

    boolean bucketExists(String bucketName);

    /** *  establish  bucket * * @param bucketName */
    void makeBucket(String bucketName);

    /** *  List all bucket names  * @return */
    List<String> listBucketName();

    /** *  List all buckets   Information  * * @return */
    List<Bucket> listBuckets();

    /** *  Delete bucket according to bucket name  * @param bucketName */
    boolean removeBucket(String bucketName);

    /** *  List all object names in the bucket  * @param bucketName * @return */
    List<String> listObjectNames(String bucketName);

    /** *  Upload files  * * @param multipartFile * @param bucketName */
    String putObject(MultipartFile multipartFile, String bucketName, String fileType);

    /** *  File stream download  * @param bucketName * @param objectName * @return */
    InputStream downloadObject(String bucketName, String objectName);

    /** *  Delete file  * @param bucketName * @param objectName */
    boolean removeObject(String bucketName, String objectName);

    /** *  Delete files in bulk  * @param bucketName * @param objectNameList * @return */
    boolean removeListObject(String bucketName, List<String> objectNameList);

    /** *  Get file path  * @param bucketName * @param objectName * @return */
    String getObjectUrl(String bucketName,String objectName);

    /** *  Get file path  * @param files * @return */
    String uploadFile(MultipartFile files) throws IOException;

    /** *  Get file path  * @param bucketName * @param objectName * @return */
    String getObjectUrlFile(String bucketName,String objectName, String fileType);

    /** *  Get file   * @param filePath * @return */
    File getFileByPath(String filePath, String outputFileName);
}
