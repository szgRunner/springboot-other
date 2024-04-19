package sun.zhao.guo.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
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
    List<TestInfo> queryTestInfoAll();

    //@Results 注解来映射查询结果集到实体类属性
    /*
    * 当我们需要通过查询到的一个字段值作为参数，去执行另外一个方法来查询关联的内容，而且两者是一对一关系时，可以使用 @One 注解来便捷的实现。
      selectById 方法是 BaseMapper 就提供的，所以我们不需要在 AreaMapper 中手动定义。
      @Result(column = "area_id", property = "areaId") 可以不写，也不会报错。但是会导致我们查询结果（User 实体）的 areaId 属性没有值（因为第二个 Result 将 area_id 值作为查询条件传入子查询）。
    */
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "user", column = "user_id",
                    one = @One(select = "sun.zhao.guo.mapper.UserMapper.queryUser",
                            fetchType = FetchType.EAGER))
    })
    @Select("select * from test_info where id = #{testId}")
    TestInfo queryTestInfo(@Param("testId") String testId);

}
