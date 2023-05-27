public class JvmApplication {
    public void getInsert(String[] args){
        Cmd cmd = new Cmd(args);
        if(!cmd.isRightFmt()){
            cmd.printUsage();
        } else {
            if (cmd.isVersionFlag()){
                System.out.println("java version \"1.8.0_20\"\n"
                        + "Java(TM) SE Runtime Environment (build 1.8.0_20-b26)\n"
                        + "Java HotSpot(TM) 64-Bit Server VM (build 25.20-b23, mixed mode)");
            } else if (cmd.isHelpFlag() || cmd.getArgs() == null){
                cmd.printUsage();
            } else {
                System.out.println("start JVM");
            }
        }
    }
}
