package com.qyuee.java.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class StreamTest002 {
    public static void main(String[] args) {
        Stream<String> stream1 = Stream.of("Java", "Scala", "Groovy");
        Stream<String> stream2 = Stream.of("Python", "Go", "Swift");
        Stream<String> stream3 = Stream.concat(stream1, stream2);

        // list로 변환하여 출력
        //System.out.println(stream3.collect(Collectors.toList()));

        // set으로 변환하여 출력
        // System.out.println(stream3.collect(Collectors.toSet()));

        // peek를 사용하여 출력
        // 최종처리 메소드(ex. allMatch, nonMatch 등)가 있어야 한다.
        // peek는 stream을 리턴한다.
        stream3.peek(s -> log.info("s:{}", s)).allMatch(s -> s.length()>0);
    }
}
