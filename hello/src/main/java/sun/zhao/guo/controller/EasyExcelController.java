package sun.zhao.guo.controller;

import com.alibaba.excel.EasyExcel;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.zhao.guo.constant.Config;
import sun.zhao.guo.model.Teacher;
import sun.zhao.guo.utils.EasyExcelUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package: sun.zhao.guo.controller
 * @Author: SzgStart-sunzg
 * @Date: 2024/07/03/23:12
 * @Description:
 */
@Tag(name = "easyExcel测试")
@RestController
@RequestMapping(Config.API_VERSION + "/easy-excel")
@CrossOrigin(origins = "http://localhost:9080")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EasyExcelController extends ApiController{


    /**
     *  教师信息表导出
     * @param response
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws MalformedURLException {
        // 准备数据
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher(1,"马云",new URL("https://money.gucheng.com/UploadFiles_6503/201901/2019012516201400.jpg"),1,"未任教","浙江杭州"));
        teachers.add(new Teacher(2,"王健林",new URL("https://img1.cache.netease.com/ent/2016/8/23/20160823221523324f5.jpg"),1,"未任教","上海浦东新区"));
        teachers.add(new Teacher(3,"雷军",new URL("https://i1.073img.com/140526/5808312_102252_1.jpg"),1,"未任教","北京通州区"));
        teachers.add(new Teacher(4,"马化腾",new URL("https://x0.ifengimg.com/res/2020/F43C0869EE9D07C77C0D82D13266BB1F94DBD148_size583_w1944_h1639.jpeg"),1,"未任教","中国深圳"));
        //　　　　　方法一
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
//         这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = null;
        try {
            fileName = URLEncoder.encode("教师信息表", "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        response.setHeader("Content-disposition", "attachment;filename*=" + fileName + ".xlsx");
        try {
            EasyExcel.write(response.getOutputStream(), Teacher.class).sheet("教师信息表").doWrite(teachers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *  教师信息表导出
     * @param response
     */
    @GetMapping("/export2")
    public void export2(HttpServletResponse response) throws MalformedURLException {

        // 准备数据
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher(1,"马云",new URL("https://money.gucheng.com/UploadFiles_6503/201901/2019012516201400.jpg"),1,"未任教","浙江杭州"));
        teachers.add(new Teacher(2,"王健林",new URL("https://img1.cache.netease.com/ent/2016/8/23/20160823221523324f5.jpg"),1,"未任教","上海浦东新区"));
        teachers.add(new Teacher(3,"雷军",new URL("https://i1.073img.com/140526/5808312_102252_1.jpg"),1,"未任教","北京通州区"));
        teachers.add(new Teacher(4,"马化腾",new URL("https://x0.ifengimg.com/res/2020/F43C0869EE9D07C77C0D82D13266BB1F94DBD148_size583_w1944_h1639.jpeg"),1,"未任教","中国深圳"));

        //　　　　　方法二
        EasyExcelUtil.exportExcel(response,"教师信息表", Teacher.class, teachers);
    }


}
