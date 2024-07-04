package sun.zhao.guo.converter;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package: sun.zhao.guo.converter
 * @Author: SzgStart-sunzg
 * @Date: 2024/07/03/23:00
 * @Description:
 */

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.alibaba.excel.util.IoUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Description 图片处理
 * @Author songwp
 * @Date 2023/3/30 15:04
 **/
@Slf4j
public class SxjgUrlImageConverter implements Converter<URL> {
    public static int urlConnectTimeout = 2000;
    public static int urlReadTimeout = 6000;

    @Override
    public Class<?> supportJavaTypeKey() {
        return URL.class;
    }

    @Override
    public WriteCellData<?> convertToExcelData(URL value, ExcelContentProperty contentProperty,
                                               GlobalConfiguration globalConfiguration) throws IOException {
        InputStream inputStream = null;
        try {
            if (value == null){
                return new WriteCellData<>("图片链接为空");
            }
            URLConnection urlConnection = value.openConnection();
            urlConnection.setConnectTimeout(urlConnectTimeout);
            urlConnection.setReadTimeout(urlReadTimeout);
            inputStream = urlConnection.getInputStream();
            byte[] bytes = IoUtils.toByteArray(inputStream);
            return new WriteCellData<>(bytes);
        }catch (Exception e){
            log.info("图片获取异常",e);
            return new WriteCellData<>("图片获取异常");
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
