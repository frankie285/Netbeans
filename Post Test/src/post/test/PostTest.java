package post.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class PostTest {
    
public static void main(String[] args) throws Exception {

    InputStream is = new FileInputStream("C:\\Users\\Frank\\Desktop\\teachers.txt"); 
    BufferedReader buf = new BufferedReader(new InputStreamReader(is)); 
    String line = buf.readLine(); 
    StringBuilder sb = new StringBuilder(); 
    while(line != null){ 
        sb.append(line).append("\n"); 
        line = buf.readLine(); 
    } 
    String content = sb.toString(); 
    content = '{' + " " + "\"teachers\"" + ':' + content + '}';

    JSONObject obj = new JSONObject(content);
        List<String> list = new ArrayList<>();
        JSONArray array = obj.getJSONArray("teachers");
        for(int i = 0 ; i < array.length() ; i++){
        list.add(array.getJSONObject(i).getString("id"));
        list.stream().forEach((r) -> {
            System.out.println(r);
        });
        }
    }
}
