package sun.zhao.guo.treemap;

import java.util.Comparator;
import java.util.TreeMap;


/**
 * 相比于HashMap来说 TreeMap 主要多了对集合中的元素根据键排序的能力以及对集合内元素的搜索的能力。
 */
class Person {
    private Integer age;

    public Person(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }
}

public class TreeMapTest {

    public static void main(String[] args) {

//        TreeMap<Person, String> treeMap = new TreeMap<>(new Comparator<Person>() {
//            @Override
//            public int compare(Person p1, Person p2) {
//                return Integer.compare(p1.getAge() - p2.getAge(), 0);
//            }
//        });

        TreeMap<Person, String> treeMap = new TreeMap<>((p1, p2) -> Integer.compare(p1.getAge() - p2.getAge(), 0));

        treeMap.put(new Person(18), "Person1");
        treeMap.put(new Person(32), "Person2");
        treeMap.put(new Person(13), "Person3");
        treeMap.put(new Person(5), "Person4");

        treeMap.entrySet().stream().forEach(entry -> System.out.println(entry.getValue()));

    }

}
