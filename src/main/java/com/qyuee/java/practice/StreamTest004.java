package com.qyuee.java.practice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
public class StreamTest004 {
    public static void main(String[] args) {
        //StreamSortedTest();
        StreamTest2();
    }

    public static void StreamSortedTest() {
        List<String> list = Arrays.asList("Java", "Scala", "Groovy", "Go", "Python", "Swift");
        Stream<String> sorted = list.stream().sorted();
        log.info("sorted: {}", sorted.collect(Collectors.toList()));

        // 역순 정렬
        Stream<String> sortedReverse = list.stream().sorted(Comparator.reverseOrder());
        log.info("sortedReverse: {}", sortedReverse.collect(Collectors.toList()));

        Stream<String> sortedNaturalOrder = list.stream().sorted(Comparator.naturalOrder());
        log.info("sortedNaturalOrder:{}", sortedNaturalOrder.collect(Collectors.toList()));

        // 문자열 길이대로 sort
        Stream<String> sortedComparingInt = list.stream().sorted(Comparator.comparingInt(String::length));
        log.info("sortedComparingInt:{}", sortedComparingInt.collect(Collectors.toList()));

        // 문자열 길이 + 역순 정렬
        Stream<String> sortedLengthReverse = list.stream()
                .sorted(Comparator.comparingInt(String::length).reversed());
        log.info("sortedLengthReverse:{}", sortedLengthReverse.collect(Collectors.toList()));
    }

    public static void StreamTest2() {
        List<Member> list = Arrays.asList(
                new Member("dslee03", 30, "male"),
                new Member("jone", 20, "female"),
                new Member("jane", 25, "female")
        );

        // 객체내의 age순 정렬
        List<Member> stream = list.stream().sorted(Comparator.comparingInt(Member::getAge)).collect(Collectors.toList());
        log.info("stream:{}", stream);

        // 객체내의 특정 값으로 그룹핑
        Map<String, List<Member>> groupBySex = list.stream().collect(Collectors.groupingBy(Member::getSex));
        log.info("groupBySex:{}", groupBySex);
        List<Member> list1 = groupBySex.get("female");
        log.info("list1:{}", list1);

        // 파티셔닝
        Map<Boolean, List<Member>> partitioningByAge = list.stream().collect(Collectors.partitioningBy(member -> member.age > 25));
        log.info("partitioningByAge:{}", partitioningByAge);

        // 나이 평균 값 구하기
        //IntStream intStream = list.stream().flatMapToInt(member -> IntStream.of(member.getAge()));
        IntStream intStream = list.stream().flatMapToInt(member -> {
            return IntStream.of(member.getAge());
        });
        OptionalDouble average = intStream.average();
        log.info("average:{}", average);

        IntStream intStream1 = IntStream.of(1, 2, 3);
        log.info("intStream1:{}", intStream1);

        Stream<? extends Serializable> stream1 = Stream.of(1, 2, "123");
        //List<? extends Serializable> collect = stream1.collect(Collectors.toList());
        Map<Boolean, ? extends List<? extends Serializable>> collect = stream1.collect(Collectors.partitioningBy(value -> value.toString().length() > 2));
        log.info("collect:{}", collect);
    }

    @Getter @Setter @ToString
    public static class Member {
        private String name;
        private int age;
        private String sex;

        public Member(String name, int age, String sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }
    }
}
