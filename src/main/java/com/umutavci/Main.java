package com.umutavci;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        List<FileThread> threadList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt")))
        {
            String l;
            while((l = br.readLine()) != null){
                FileThread th = new FileThread(map, l);
                threadList.add(th);
                th.start();
            }
        }
        catch (IOException e){
            throw e;
        }
        for(FileThread thread : threadList){
            try {
                thread.join();
            }
            catch(InterruptedException e){
                throw e;
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt")))
        {
            for(String str : map.keySet()){
                bw.write(str + " " + map.get(str) + "\n");
            }
        }
    }
}