package i_introduction._1_Java_To_Kotlin_Converter;

import util.JavaCode;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

public class JavaCode1 extends JavaCode {
    public String task1(Collection<Integer> collection) {
        return collection.stream()
            .map(i -> i.toString())
            .collect(Collectors.joining(", ", "{", "}"));
    }
}