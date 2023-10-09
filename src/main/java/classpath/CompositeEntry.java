package classpath;

import java.io.IOException;
import java.util.ArrayList;

/*
* 这里是包含多个路径的使用场景(如a1/b1/c1;a2/b2/c2),这里将字符串分割成不同的子串
* 分隔符在不同系统是不同的，这里只实现了windows
*
* */
public class CompositeEntry extends Entry{
    //DirEntry形式存储的所有类路径
    private ArrayList<Entry> compositeEntries;
    //a1/b1/c1;a2/b2/c2字符串形式存储的类路径
    private String pathList;

    public CompositeEntry(){
    }

    //保存所有的可能路径
    public CompositeEntry(String pathList, String pathListSeparator){
        this.pathList = pathList;
        String[] paths = pathList.split(pathListSeparator);
        for (int i = 0; i < paths.length; i++){
            compositeEntries.add(new DirEntry(paths[i]));
        }
    }

    //从所有可能路径中读取className,如果className文件不存在就输出异常继续执行,如果找到className就返回数据,如果都没有返回null
    @Override
    byte[] readClass(String className) throws IOException {
        byte[] data;
        for (int i = 0; i < compositeEntries.size(); i++){
            try {
                data = compositeEntries.get(i).readClass(className);
                if (data != null){
                    return data;
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    String printClassName() {
        return pathList;
    }

    public ArrayList<Entry> getCompositeEntries() {
        return compositeEntries;
    }

    public void setCompositeEntries(ArrayList<Entry> compositeEntries) {
        this.compositeEntries = compositeEntries;
    }
}
