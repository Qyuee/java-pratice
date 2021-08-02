package com.qyuee.java.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class StreamTest001 {
    public static void main(String[] args) {
        String[] arr = new String[]{"a", "b", "c"};
        Stream<String> stream = Arrays.stream(arr);
        Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3);

        Stream<String> stream1 = streamOfArrayPart.map(String::toUpperCase);
        stream1.forEach(s -> {
            System.out.println("stream1 = " + s);
        });

        // stream has already been operated upon or closed
        // stream을 이미 소비했기에 새롭게 생성해서 사용해야 한다.
        //Stream<String> stream2 = streamOfArrayPart.map(s -> s.toUpperCase());
        Stream<String> stream2 = Arrays.stream(arr).map(String::toUpperCase);
        stream2.forEach(s -> {
            System.out.println("stream2 = " + s);
        });


        // flatmap: 스트림의 형태가 배열과 같을 때, 모든 원소를 단일 원소 스트림으로 변환한다.
        String[][] namesArray = new String[][]{
                {"kim", "taeng"}, {"mad", "play"},
                {"kim", "mad"}, {"taeng", "play"}};

        Stream<String> stream3 = Arrays.stream(namesArray).flatMap(Arrays::stream);
        stream3.forEach(s -> {
            System.out.println("stream3 = " + s);
        });

        // 2차원 배열을 List로 변환
        // flatmap을 사용하여 모든 원소를 단일 원소로 변환함
        List<String> test = Arrays.stream(namesArray)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
        test.forEach(s -> {
            System.out.println("s = " + s);
        });
        System.out.println("test:"+test);
        // test:[kim, taeng, mad, play, kim, mad, taeng, play]

        // Set으로 변환
        Set<String> setResult = Arrays.stream(namesArray)
                .flatMap(Arrays::stream)
                .collect(Collectors.toSet());
        System.out.println("setResult:"+setResult);
    }
}
