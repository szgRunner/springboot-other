package sun.zhao.guo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    private final static Logger logger = LogManager.getLogger(Test.class);

    public static void main(String[] args) {
        String test = "${java:hw}";
        logger.info("log4j =  {}", test);


        /**
         *  Arras.asList 是泛型方法，传入的参数必须是对象数组，而不能是基本数据类型
         *
         *  当传入一个原生的数据类型时，它接收到的参数就不是数组中的元素，而是数组对象本身。
         *
         *  此时 List 中的元素就是这个数组对象本身
         */
        int[] myArray = {1, 2, 3};
        List myList = Arrays.asList(myArray);
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

}
