package com.koitt.www.util;

import java.io.File;
import java.io.IOException;

import com.oreilly.servlet.multipart.*;

public class MyFileRenamePolicy implements FileRenamePolicy {
    public File rename(File f) {
    	
        String name = f.getName();
        String body = null;
        String ext = null;

        int dot = name.lastIndexOf(".");
        if (dot != -1) {
             body = name.substring(0, dot);
             ext = name.substring(dot);  // 확장자 잘라내기 - includes "."
        } else {
             body = name;
             ext = "";
        }
        
        f = new File(f.getParent(), name);
        if (createNewFile(f)) {
             return f;
        }

        int cnt = 0;
        while (!createNewFile(f) && cnt < 9999) {
             cnt++;
             String newName = body + "_" + cnt + ext;
             f = new File(f.getParent(), newName);
        }

        return f;
   }

   private boolean createNewFile(File f) {
        try {
             return f.createNewFile();
        } catch (IOException ignored) {
             return false;
        }
   }
}
