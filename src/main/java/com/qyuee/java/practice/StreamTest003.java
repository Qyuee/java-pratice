package com.qyuee.java.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
public class StreamTest003 {
    public static void main(String[] args) {
        //flatMap();
        flatMapToInt();
    }

    public static void flatMap() {
        List<List<String>> list = Arrays.asList(
                Arrays.asList("a", "b", "c"),
                Arrays.asList("b")
        );

        // flatMap을 사용X
        List<String> result = new ArrayList<>();
        list.forEach(stringList -> {
            stringList.forEach(string -> {
                result.add(string);
            });
        });
        log.info("result:{}", result);

        // flatMap 사용 O
        List<String> flatList = list.stream()
                .flatMap(subList -> {
                    if (subList.size() > 2) {
                        return Stream.of();
                    }
                    return subList.stream();
                })
                .collect(Collectors.toList());
        log.info("flatList:{}", flatList);

        List<String> flatList2 = list.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        log.info("flatList2:{}", flatList2);
    }

    /**
     * flatMapToInt: Int형 단일 원소 스트림으로 변환
     */
    public static void flatMapToInt() {
        List<String> list = Arrays.asList("10, 20, 30", "40, 50, 60");
        IntStream intStream = list.stream()
                .flatMapToInt(data -> {
                    String[] strArr = data.split(",");
                    int[] intArr = new int[strArr.length];
                    for (int i=0; i<strArr.length; i++) {
                        intArr[i] = Integer.parseInt(strArr[i].trim());
                    }
                    return Arrays.stream(intArr);
                });

        //OptionalInt max = intStream.max();
        //log.info("max:{}", max.getAsInt());

        // boxed: int -> Integer로 변환 (기본형을 참조형으로)
        List<Integer> intList = intStream.boxed().collect(Collectors.toList());
        log.info("intList:{}", intList);

    }
}
