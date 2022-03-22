package sun.zhao.guo;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo
 * @Author: SzgStart-sunzg
 * @Date: 2022/03/22/下午3:17
 * @Description:
 */
public class MainTest {

    public static void main(String[] args) {
        String s1 = "Javatpoint";
        String s2 = s1.intern();
        String s3 = new String("Javatpoint");
        String s4 = s3.intern();
        System.out.println(s1==s2); // True
        System.out.println(s1==s3); // False
        System.out.println(s1==s4); // True
        System.out.println(s2==s3); // False
        System.out.println(s2==s4); // True
        System.out.println(s3==s4); // False

        System.out.println(" ========================= ");

        Integer i1 = 40;
        Integer i2 = 40;
        Integer i3 = 0;
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        Integer i6 = new Integer(0);

        System.out.println(i1 == i2);// true
        System.out.println(i1 == i2 + i3);//true
        System.out.println(i1 == i4);// false
        System.out.println(i4 == i5);// false
        System.out.println(i4 == i5 + i6);// true   拆箱操作， 最终变为 40 == 40
        System.out.println(40 == i5 + i6);// true


    }

}
