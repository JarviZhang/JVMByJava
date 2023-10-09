package classpath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ClassPath {
    //jre路径
    private String jreDir;
    //启动类路径
    private Entry bootClasspath;
    //扩展类路径
    private Entry extClasspath;
    //用户类路径
    private Entry userClasspath;

    //设置启动类路径、扩展类路径、用户类路径
    public ClassPath(String jreOption, String cpOption){
        jreDir = getJreDir(jreOption);
        bootClasspath = parseBootClasspath();
        extClasspath = parseExtClasspath();
        userClasspath = parseUserClasspath(cpOption);
    }

    //获取启动类路径,并使用WildcardEntry保存该目录
    //这里只包含/jre/lib下的jar包
    private Entry parseBootClasspath(){
        //jre/lib/*
        String jreLibPath = jreDir + File.separator + "lib" + File.separator + "*";
        return new WildcardEntry(jreLibPath);
    }
    //获取扩展类路径,并使用WildcardEntry保存该目录
    //这里只包含/jre/lib下的jar包
    private Entry parseExtClasspath(){
        //jre/lib/ext/*
        String jreExtPath = jreDir + File.separator + "lib" + File.separator + "ext" + File.separator + "*";
        return new WildcardEntry(jreExtPath);
    }

    //获取用户类路径,并使用Entry保存该目录
    //有cpOption则用户类路径是cpOption，没有则是当前路径
    private Entry parseUserClasspath(String cpOption){
        return Entry.createEntry(cpOption);
    }

    //按照jreOption,当前文件,JAVA_HOME地址的顺序找到jre,找不到就报错
    private String getJreDir(String jreOption){
        File jreFile;
        //如果传入jre路径，就在该路径中找
        if (jreOption != null && !"".equals(jreOption)){
            jreFile = new File(jreOption);
            if (jreFile.exists()){
                return jreOption;
            }
        }
        //如果jreOption选项为空，那么在当前路径找
        jreFile = new File("jre");
        if (jreFile.exists()){
            return jreFile.getAbsolutePath();
        }
        //当前路径没有，在JavaHome中找
        String java_home = System.getenv("JAVA_HOME");
        if (java_home != null){
            //File.separator 文件分隔符
            return java_home + File.separator + "jre";
        }
        throw new RuntimeException("Can not find jre folder!");
    }

    /**
     * ClassPath 对外的统一接口,实例化ClassPath时传入 userPath 路径和类名就可以读取字节码文件
     * 读取className 对应的字节码,注意顺序,我们的查找次序是:
     * bootClasspath => extClasspath => userClasspath;
     * @param className
     * @return
     */
    public byte[] readClass(String className) throws FileNotFoundException {
        //命令行加载java文件，只写文件名，不加class后缀
        if (className.endsWith(".class")) {
            throw new RuntimeException("can't find or can't load the class: " + className);
        }
        className = className.replace(".", "/");
        className = className + ".class";
        byte[] data;
        try {
            data = bootClasspath.readClass(className);
            if (data != null) {
                return data;
            }

            data = extClasspath.readClass(className);
            if (data != null) {
                return data;
            }

            data = userClasspath.readClass(className);
            if (data != null) {
                return data;
            }
        } catch (FileNotFoundException e){
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("can't find class!");
    }

    @Override
    public String toString(){
        return userClasspath.printClassName();
    }
}
