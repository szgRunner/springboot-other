package sun.zhao.guo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.zhao.guo.constant.Config;
import sun.zhao.guo.service.FileDownloadService;

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

}
