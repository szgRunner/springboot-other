package sun.zhao.guo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.zhao.guo.constant.Config;
import sun.zhao.guo.service.FileDownloadService;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package: sun.zhao.guo.controller
 * @Author: SzgStart-sunzg
 * @Date: 2024/06/27/15:29
 * @Description:
 */
@Tag(name = "文件下载")
@RestController
@RequestMapping(Config.API_VERSION + "/download")
@CrossOrigin(origins = "http://localhost:9080")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileDownloadController {

    private final FileDownloadService fileDownloadService;

    @Operation(summary = "下载文件夹内所有文件并转为zip压缩包")
    @RequestMapping(value = "/dir/zip", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> downloadDirToZip(@RequestParam String dir){
        try {
            return fileDownloadService.downloadDirTozip(dir);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<FileSystemResource> downloadFile(@PathVariable String fileName) throws IOException {
        // 假设文件存储在服务器的某个目录下，例如："/path/to/files/"
        String filePath = "/path/to/files/" + fileName;

        // 检查文件是否存在
        File file = new File(filePath);
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        // 设置响应头，指示这是一个需要下载的文件
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");

        // 使用 FileSystemResource 将文件包装成可读资源
        FileSystemResource resource = new FileSystemResource(file);

        // 返回 ResponseEntity，包含文件资源和响应头信息
        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(resource);
    }

}
