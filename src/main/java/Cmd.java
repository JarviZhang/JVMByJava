/*
* 实现Cmd功能,接收用户输入指令
* 实现指令:
* 1. java -version
* 2. java -?
* 3. java -help
* 4. java -cp classpath ClassName arg1 arg2 ...
* 5. java -classpath classpath ClassName arg1 arg2 ...
* 6. java -xjre jrepath className arg1 arg2 ...
* */
public class Cmd {
    private boolean isRightFmt = true; //是否是正确格式
    private boolean helpFlag; //是否是help(查看帮助)
    private boolean versionFlag; //是否是查看版本
    private String cpOption = ""; //classPath的路径
    private String className; //要编译的class文件
    private String[] args; //要执行的class文件需要的参数
    private String XjreOption; // jre路径

    public Cmd(String[] strs){
        parseCmd(strs);
    }


    /*
* 对用户在cmd中的输入进行解析,并保存在cmd实例中
* */
    private void parseCmd(String[] args){
        int index = 1;
        if(args.length < 2){
            isRightFmt = false;
            return;
        }
        //检查第一个输入
        if(!args[0].equals("java")){
            isRightFmt = false;
        }else {
            if (args[1].equals("-help") || args[1].equals("-?")){
                helpFlag = true;
            } else if (args[1].equals("-version")){
                versionFlag = true;
            } else if (args[1].equals("-cp") || args[1].equals("classpath")){
                if (args.length < 4){
                    isRightFmt = false;
                }
                index = 4;
                this.cpOption = args[2];
            } else if (args[1].equals("-xjre")){
                if (args.length < 4){
                    isRightFmt = false;
                }
                index = 4;
                XjreOption = args[2];
            }
        }
        className  = args[index - 1];
        this.args = new String[args.length - index];
        for(int i = index; i < args.length; i++){
            this.args[i - index] = args[i];
        }
    }

    public void printUsage(){
        System.out.println("Usage: java [-option] class [args...]\n");
    }

    public boolean isRightFmt() {
        return isRightFmt;
    }

    public boolean isVersionFlag() {
        return versionFlag;
    }

    public boolean isHelpFlag() {
        return helpFlag;
    }

    public String[] getArgs() {
        return args;
    }
}
