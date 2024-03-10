package com.bsj;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StaticUtils {
    private StaticUtils(){}
    
    //返回指定区间的Integer List
    public static List<Integer> range(int start,int end){
        return IntStream.range(start,end).boxed().collect(Collectors.toList());
    }
    
    //返回Echo字符串
    public static String getName(){
        return "Echo";
    }
}
