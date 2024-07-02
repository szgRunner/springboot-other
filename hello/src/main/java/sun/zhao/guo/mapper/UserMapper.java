package sun.zhao.guo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import sun.zhao.guo.model.User;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.mapper
 * @Author: SzgStart-sunzg
 * @Date: 2024/04/20/01:20
 * @Description:
 */
@Mapper
public interface UserMapper extends MyBaseMapper<User>{

    int saveUser(User user);

    @Select("select * from user_info where id = #{userId}")
    User queryUser(String userId);

}
