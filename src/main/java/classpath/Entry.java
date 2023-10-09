package classpath;

import java.io.File;
import java.io.IOException;
/*
* 用于实现java -cp classpath ClassName arg1 arg2 ...和java -classpath classpath ClassName arg1 arg2 ...
* 类路径分为以下三部分:
* 1. 启动类路径(bootstrap classpath):默认对应jre\lib目录，Java标准库(大部分在rt.jar里)位于该路径
* 2. 扩展类路径(extension classpath):默认对应jre\lib\ext目录，使用Java扩展机制的类位于这个路径
* 3. 用户类路径(user classpath):自己实现的类，以及第三方类库则位于用户类路径
* 对于类路径的格式，有以下几种：
* 1. 直接指定类所在的文件,后面跟类名:java -cp aaa/bbb/ccc ddd arg1 arg2
* 2. 指定类所在的jar文件的路径,后面跟类名(类在jar包中):java -cp aaa/bbb/ccc.jar ddd arg1 arg2
* 3. 指定一个模糊路径,后面跟类名:java -cp aaa/bbb/* ddd arg1 arg2
* 4. 指定若干个路径,后面跟类名(指定的类存在指定的某一条路径中,如果都存在那么以第一条为准):java -cp aaa1/bbb/ccc;aaa2/bbb/ccc;aaa3/bbb/ccc; ddd arg1 arg2
* */
public abstract class Entry {
    //路径分割符，在windows使用，使用；分隔开，在Linux下使用:分隔开
    public static final String pathListSeparator = System.getProperty("os.name").contains("Windows") ? ";" : ":";

    /**
    * 负责寻找和记载class文件
    * @param className class文件的相对路径,路径之间用斜线/分割,文件名有后缀.class
    * */
    abstract byte[] readClass(String className) throws IOException;

    /**
     * @return 返回className的字符串表示形式;
     * */
    abstract String printClassName();

    /**
     * 工厂方法,根据传入的path的形式不同
     * @param path 命令行得到的路径字符串
     * @return 创建具体的Entry
     * */
    static Entry createEntry(String path){
        if (path != null && !path.equals("")){
            if (path.contains(pathListSeparator)){//指定若干个路径,后面跟类名(指定的类存在指定的某一条路径中,如果都存在那么以第一条为准)
                return new CompositeEntry(path, pathListSeparator);
            } else if (path.contains("*")) {//指定一个模糊路径,后面跟类名
                return new WildcardEntry("");
            } else if (path.contains(".jar") || path.contains(".JAR") || path.contains(".zip") || path.contains(".ZIP")){//指定类所在的jar文件的路径,后面跟类名
                return new ZipJarEntry(path);
            }
            //直接指定路径,后面跟类名
            return new DirEntry(path);
        } else {
            //如果命令中没有显式的指定-cp选项, 那么默认要找的class就在当前路径下
            File file = new File("");
            try {
                path = file.getCanonicalPath();
                return new DirEntry(path);
            } catch (IOException e){
                e.printStackTrace();
            }
            throw  new RuntimeException("illegal classpath format, or you should pint out the classpath ex;licitly");
        }
    }
}
