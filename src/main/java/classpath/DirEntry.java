package classpath;

import classpath.Entry;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;

/*
* 这里针对的是类路径 类名形式的最基本情况
* */
public class DirEntry extends Entry {
    //类所在文件夹的绝对路径
    String absDir;

    //传入的字符串表示目录形式的类路径
    public DirEntry(String path){
        File dir = new File(path);
        if (dir.exists()){
            //获取文件的绝对路径
            absDir = dir.getAbsolutePath();
        }
    }

    //如果没有找到文件会抛出IOException让上层处理
    @Override
    byte[] readClass(String className) throws FileNotFoundException, IOException {
        try {
            File file = new File(absDir, className);
            byte[] temp = new byte[1024];
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
            int size = 0;
            while ((size = in.read(temp)) != -1){
                out.write(temp, 0 ,size);
            }
            if (in != null){
                in.close();
            }
            if (out != null){
                out.close();
            }
            return out.toByteArray();
        } catch (FileNotFoundException e){
            throw e;
        } catch (IOException e){
            throw e;
        }

    }

    @Override
    String printClassName() {
        return absDir;
    }
}
