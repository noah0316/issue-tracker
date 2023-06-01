package issuetracker.issuetracker.infrastructure.aws;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final S3Client s3Client;

    @Value("${AWS_S3_BUCKET}")
    private String bucketName;


    public PutObjectResponse uploadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();

        // Upload file
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key("profile/" + fileName)
                .acl("public-read")
                .contentDisposition("inline")
                .contentType(contentType)
                .build();

        try {
            return s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
    }
}
