package classpath;

import classpath.Entry;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/*
* 使用场景是指定了一个形如aa/bb/*的路径
* 这种路径表明我们的class文件在aa/bb/路径下的jar包中
* 所以我们只要遍历该路径下的所有以.jar结尾的文件
* 然后调用ZipJarEntry的实现方法,即可以获得字节码
* 这个类其实是CompositeEntry类的包装类
 */
public class WildcardEntry extends Entry {

    //以ZipJarEntry形式保存所有的.jar文件
    private CompositeEntry compositeEntry;

    public WildcardEntry(String jreLibPath){
        String baseDir = jreLibPath.substring(0, jreLibPath.length() - 1); //去掉最后一个字符*
        File dir = new File(baseDir);
        File[] files = dir.listFiles();
        compositeEntry = new CompositeEntry();
        compositeEntry.setCompositeEntries(new ArrayList<Entry>());
        for (File file : files){
            if (file.isFile() && file.getName().endsWith(".jar")) {
                compositeEntry.getCompositeEntries().add(new ZipJarEntry(baseDir, file.getName()));
            }
        }
    }
    @Override
    byte[] readClass(String className) throws IOException {
        return compositeEntry.readClass(className);
    }

    @Override
    String printClassName() {
        return null;
    }
}
