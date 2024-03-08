package generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class New {

    public static <K, V> Map<K, V> map() {
        return new HashMap<>();//泛型方法定义中使用类型参数
    }

    public static <T> List<T> list() {
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        Map<String, List<String>> sls = New.map();
        List<String> ls = New.list();
        List<Integer> li = New.list();
        ls.add("12");
        li.add(12);
    }
}