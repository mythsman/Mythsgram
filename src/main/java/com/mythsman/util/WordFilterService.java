package com.mythsman.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by myths on 5/7/17.
 */
@Service
public class WordFilterService implements InitializingBean {
    private static Logger logger = Logger.getLogger(WordFilterService.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        String fileName = "static/txt/sensitive.txt";
        try {
            InputStream ins = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
            String line;
            while ((line = reader.readLine()) != null) {
                addWord(line.trim());
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private class Node {
        private Map<Character, Node> children = new HashMap<>();
        public Node get(Character c) {
            return children.get(c);
        }
        public void put(Character c, Node node) {
            children.put(c, node);
        }
        public boolean isEnd() {
            return children.isEmpty();
        }
    }
    private void addWord(String txt) {
        Node node = root;
        for (int i = 0; i < txt.length(); i++) {
            Character c = txt.charAt(i);
            Node next = node.get(c);
            if (next == null) {
                Node newNode = new Node();
                node.put(c, newNode);
                node = newNode;
            } else {
                node = next;
            }
        }
    }
    private boolean isSymbol(Character c){
        int ic=(int)c;
        return !Character.isDigit(c)&&!Character.isAlphabetic(c)&&(ic<0x2E80||ic>0x9FFF);
    }

    private Node root = new Node();
    public String filter(String txt) {
        StringBuffer sb = new StringBuffer();
        Node node = root;
        int begin = 0;
        int pos = 0;
        while (pos < txt.length()) {
            char c = txt.charAt(pos);
            if(isSymbol(c)){
                if(node==root){
                    sb.append(c);
                    begin++;
                }
                pos++;
                continue;
            }
            node = node.get(c);
            if (node == null) {
                sb.append(txt.charAt(begin));
                begin++;
                pos = begin;
                node = root;
            } else if (node.isEnd()) {
                for (int i = 0; i <= pos - begin; i++) {
                    sb.append('*');
                }
                begin = pos + 1;
                pos = begin;
                node = root;
            } else {
                pos++;
            }
        }
        sb.append(txt.substring(begin));
        return sb.toString();
    }

    public static void main(String[] args) {
        WordFilterService s = new WordFilterService();
        s.addWord("傻逼");
        System.out.print(s.filter("我是傻逼"));
    }
}
