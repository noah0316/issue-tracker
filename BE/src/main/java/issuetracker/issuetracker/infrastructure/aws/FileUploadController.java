package issuetracker.issuetracker.infrastructure.aws;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileUploadController {

    private final FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        String url = "https://issue-tracker-team04.s3.ap-northeast-2.amazonaws.com/profile/" +file.getOriginalFilename();

        fileUploadService.uploadFile(file);
        return url;
    }
}

