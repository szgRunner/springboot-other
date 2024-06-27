package sun.zhao.guo.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.extra.compress.CompressUtil;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sun.zhao.guo.service.FileDownloadService;
import sun.zhao.guo.utils.PathUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package: sun.zhao.guo.service.impl
 * @Author: SzgStart-sunzg
 * @Date: 2024/06/27/15:33
 * @Description:
 */
@Service
public class FileDownLoadServiceImpl implements FileDownloadService {


    @Override
    public ResponseEntity<byte[]> downloadDirTozip(String dir) throws IOException {

        File file = FileUtil.file(dir);

        // 检查文件是否存在
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        String[] pathArr = dir.split("/");
        String zipName = pathArr[pathArr.length - 1] + ".zip";
        File zip = FileUtil.file(PathUtils.getParentPath(dir) + "/" + zipName);
        CompressUtil.createArchiver(CharsetUtil.defaultCharset(), ArchiveStreamFactory.ZIP, zip)
                .add(file)
                .finish()
                .close();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

        String fileName = new String(zipName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);

        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

        byte[] bytes = FileUtils.readFileToByteArray(zip);

        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(bytes);
    }
}
