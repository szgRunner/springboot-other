package sun.zhao.guo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import sun.zhao.guo.model.TestInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.mapper
 * @Author: SzgStart-sunzg
 * @Date: 2023/02/02/下午3:19
 * @Description:
 */

@Mapper
public interface TestInfoMapper extends MyBaseMapper<TestInfo> {

    List<TestInfo> queryTestInfoList(@Param("version") Integer version);

    @Select("select * from test_info")
    List<TestInfo> queryTestInfoList();

}
