package com.umutavci;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class FileThread extends Thread {
    Map<String, Integer> map;
    String str;

    public FileThread(Map<String, Integer> map, String str) {
        this.map = map;
        this.str = str;
    }

    @Override
    public void run() {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                if (map.containsKey(str.substring(0, i))) {
                    map.put(str.substring(0, i), map.get(str.substring(0, i))+1);
                } else {
                    map.put(str.substring(0, i), 1);
                }
            }
        }
    }
}
