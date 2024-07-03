package sun.zhao.guo.guo.utils;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package: sun.zhao.guo.utils
 * @Author: SzgStart-sunzg
 * @Date: 2024/06/27/16:37
 * @Description:
 */
public class PathUtils {

    /**
     * 获取给定路径的父路径
     * @param path 输入的路径字符串
     * @return 父路径字符串，如果已经是根路径则返回null或原路径
     */
    public static String getParentPath(String path) {
        if (path == null || path.isEmpty()) {
            return null;
        }

        // 移除尾部的路径分隔符
        path = path.endsWith("/") ? path.substring(0, path.length() - 1) : path;

        // 使用File类的getParent方法获取父路径
        File file = new File(path);
        String parentPath = file.getParent();

        // 如果父路径为空，说明已经是根路径
        return parentPath != null ? parentPath : "/";
    }

}
