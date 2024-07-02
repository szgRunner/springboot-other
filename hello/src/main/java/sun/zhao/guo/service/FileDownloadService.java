package sun.zhao.guo.service;

import org.springframework.http.ResponseEntity;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package: sun.zhao.guo.service
 * @Author: SzgStart-sunzg
 * @Date: 2024/06/27/15:32
 * @Description:
 */
public interface FileDownloadService {


    ResponseEntity<byte[]> downloadDirTozip(String dir) throws IOException;

}
