package sun.zhao.guo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.calcite.linq4j.Linq4j;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.mapper
 * @Author: SzgStart-sunzg
 * @Date: 2024/04/16/22:42
 * @Description:
 */
public interface MyBaseMapper<T> extends BaseMapper<T> {

    default QueryWrapper<T> getQueryWrapper() {
        return Wrappers.query();
    }

    default LambdaQueryWrapper<T> getLambdaQueryWrapper() {
        return Wrappers.lambdaQuery();
    }


    default UpdateWrapper<T> getUpdateWrapper() {
        return Wrappers.update();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //QueryList

    default List<T> queryList(Long limit, Consumer<QueryWrapper<T>> action) {

        QueryWrapper<T> qw = getQueryWrapper();

        if (action != null) {
            action.accept(qw);
        }

        if (limit == null) {
            return this.selectList(qw);
        } else {
            return this.queryPage(limit, action).getRecords();
        }
    }

    default List<T> queryList(Consumer<QueryWrapper<T>> action) {

        return this.queryList(null, action);
    }

    default List<T> queryList() {

        return this.queryList(null, null);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //QueryPage
    default IPage<T> queryPage(Long offset, Long limit,
                               Consumer<QueryWrapper<T>> action) {

        QueryWrapper<T> qw = getQueryWrapper();

        if (action != null) {
            action.accept(qw);
        }


        Page<T> page = null;


        //如果offset为空,limit不为空,则为仅使用limit;
        if (offset == null && limit != null) {
            page = new Page<T>(1, limit);
            page.setOptimizeCountSql(false);
            page.setTotal(limit);

        }
        //如果offset,limit都为空,则不分页;
        else if (offset == null && limit == null) {
            page = new Page<T>(-1, -1);
        }

        //如果offset不为空,limit为空,跳过多少条记录;
        else if (offset != null && limit == null) {
            //TODO:这种设置暂时没有任何效果 因为mybatis plus分页插件没法单独的设置offset，这是个bug
//			page = new Page<T>(offset, -1);
            page = new Page<T>(-1, -1);
        } else {
            page = new Page<T>(offset, limit);
        }

        IPage<T> result = this.selectPage(page, qw);


        //修正totolsize和pageSize
        //TODO:这可能有性能问题，待测试。如果有性能问题，需要加个开关
//		var size=page.getRecords().size();
//		if(page.getTotal()!=size)
//		{
//			page.setTotal(size);
//			page.setSize(size);
//		}

        //修整offset单独存在的问题，和分页参数都没有的统计值
        if (page.getSize() == -1) {
            List<T> record = page.getRecords();

            if (limit == null && offset != null) {
                List<T> records1 = Linq4j.asEnumerable(record).skip(Math.toIntExact(offset)).toList();
                page.setRecords(records1);
                record = records1;
            }

            long size = record.size();
            page.setTotal(size);
            page.setSize(size);
        }

        return result;

    }

    default IPage<T> queryPage(Consumer<QueryWrapper<T>> action) {

        return this.queryPage(null, null, action);
    }

    /***/
    default IPage<T> queryPage(Long limit, Consumer<QueryWrapper<T>> action) {

        IPage<T> page = this.queryPage(null, limit, action);
        return page;
    }

    default IPage<T> queryPage() {

        IPage<T> page = this.queryPage(null, null, null);
        return page;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //QueryOne

    default T queryOne(Consumer<QueryWrapper<T>> action) {
        QueryWrapper<T> qw = getQueryWrapper();

        if (action != null) {
            action.accept(qw);
        }

        return this.selectOne(qw);

    }

    default T queryLambdaOne(Consumer<LambdaQueryWrapper<T>> action) {
        LambdaQueryWrapper<T> qw = getLambdaQueryWrapper();

        if (action != null) {
            action.accept(qw);
        }

        return this.selectOne(qw);

    }

    default boolean Exists(Consumer<QueryWrapper<T>> action) {
        QueryWrapper<T> qw = getQueryWrapper();

        if (action != null) {
            action.accept(qw);
        }

        T t = this.selectOne(qw);
        if (Objects.isNull(t)) {
            return false;
        } else {
            return true;
        }
    }


    default int delete(Consumer<QueryWrapper<T>> action) {
        QueryWrapper<T> qw = getQueryWrapper();

        if (action != null) {
            action.accept(qw);
        }

        return this.delete(qw);

    }

    default int deleteByKey(Serializable key) {
        return this.deleteById(key);

    }


    default int update(T t, Consumer<UpdateWrapper<T>> action) {
        UpdateWrapper<T> uw = getUpdateWrapper();

        if (null != action) {
            action.accept(uw);
        }

        return this.update(t, uw);

    }

}
