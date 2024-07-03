package sun.zhao.guo;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void test(String[] args) {
        String test = "${java:hw}";


        /**
         *  Arras.asList 是泛型方法，传入的参数必须是对象数组，而不能是基本数据类型
         *
         *  当传入一个原生的数据类型时，它接收到的参数就不是数组中的元素，而是数组对象本身。
         *
         *  此时 List 中的元素就是这个数组对象本身
         */
        int[] myArray = {1, 2, 3};
        List myList = Collections.singletonList(myArray);
        System.out.println(myList.size());//1
        System.out.println(myList.get(0));//数组地址值
        System.out.println(myList.get(1));//报错：ArrayIndexOutOfBoundsException
        int[] array = (int[]) myList.get(0);
        System.out.println(array[0]);//1


        // 我们使用包装类型数组就可以解决这个问题。
        Integer[] myArrays = {1, 2, 3};
        List list = new ArrayList<>(Arrays.asList("a", "b", "c"));

        //java8
        List myList1 = Arrays.stream(myArrays).collect(Collectors.toList());
        //基本类型也可以实现转换（依赖boxed的装箱操作）
        int [] myArray2 = { 1, 2, 3 };
        List myList2 = Arrays.stream(myArray2).boxed().collect(Collectors.toList());

    }


    public static void main(String[] args) {
        String path = "/a/b/c/";
        String[] split = path.split("/");
        String s = split[split.length - 1];
        System.out.println("s = " + s);

        String substring = path.substring(0, path.lastIndexOf("/") - 1);
        System.out.println("substring = " + substring);
    }

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
