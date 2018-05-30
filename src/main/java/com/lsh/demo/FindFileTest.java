package com.lsh.demo;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-17
 */
public class FindFileTest {

    public static List<File> txtFiles = new LinkedList<>();

    public static void main(String[] args){
        File filesHome = new File("/home/lish/temp/testFindText");
        find(filesHome);
        txtFiles.forEach(oneText -> System.out.println(oneText.getPath()));
    }

    public static void find(File file){
        if(file.isDirectory()){
            List<File> childrens = Arrays.asList(file.listFiles());
            childrens.forEach(child -> find(child));
        }else{
            if(file.getName().endsWith(".txt")){
                txtFiles.add(file);
            }
        }
    }


}
