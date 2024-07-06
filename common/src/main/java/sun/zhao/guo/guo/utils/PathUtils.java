package sun.zhao.guo.guo.utils;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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


    public static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }

    /**
     * 当前路径获取
     * @return
     */
    public static String getPath() {
        return Objects.requireNonNull(PathUtils.class.getResource("/")).getPath();
    }

    public static File createNewFile(String pathName) {
        File file = new File(getPath() + pathName);
        if (file.exists()) {
            file.delete();
        } else {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
        }
        return file;
    }

    public static File readFile(String pathName) {
        return new File(getPath() + pathName);
    }

    public static File readUserHomeFile(String pathName) {
        return new File(System.getProperty("user.home") + File.separator + pathName);
    }


    /**
     * build to test file path
     **/
    public static class TestPathBuild {
        private TestPathBuild() {
            subPath = new ArrayList<>();
        }

        private final List<String> subPath;

        public TestPathBuild sub(String dirOrFile) {
            subPath.add(dirOrFile);
            return this;
        }

        public String getPath() {
            if (subPath.size() == 0) {
                return PathUtils.class.getResource("/").getPath();
            }
            if (subPath.size() == 1) {
                return PathUtils.class.getResource("/").getPath() + subPath.get(0);
            }
            StringBuilder path = new StringBuilder(PathUtils.class.getResource("/").getPath());
            path.append(subPath.get(0));
            for (int i = 1; i < subPath.size(); i++) {
                path.append(File.separator).append(subPath.get(i));
            }
            return path.toString();
        }
    }

}
