import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Process_scheduling_thread extends TimerTask{

    @Override
    public void run() {
        /*----------------------------判断进程数目是否大于10---------------------------*/
        if(Main.pcb.size() > 10){
            UI.textArea.setText("进程超过10个停止运行");
            if(UI.index == 1){
                Main.init();
                Main.c1.cancel();
            }
            if(UI.index == 2){
                Main.init();
                Main.c2.cancel();
            }
        }
        showpcbqueue();  //显示队列信息
        UI.textArea_2_1_1.setText(String.valueOf(Main.clock.num)); //显示当前时间
        UI.textArea_2_1.setText(String.valueOf(Main.pcb.size()));  //显示进程数目
        if(!Main.pcbQueue.isEmpty()){   //显示PC与IR
            UI.textArea_2_1_4.setText(String.valueOf(Main.pcbQueue.peek().ProID));
        }else{
            UI.textArea_2_1_3.setText("");
        }
        Scanner scan = new Scanner(System.in);
        PCB p = null;
        while(true){
            if(Main.clock.num != 0 && Main.clock.num % 10 == 0){
                fun8();         //新增作业
                fun9();         //创建进程
                fun10();        //进入就绪队列
                showpcbqueue(); //显示队列信息
            }
            if(Main.pcbQueue.isEmpty()){
                fun7();         //显示CPU空闲
                UI.textArea_2_1_4.setText("");
                UI.textArea_2_1_3.setText("");
                if(Main.count == Main.pcb.size()){ //如果已完成进程数目等于新建作业数目则退出
                    fun11();    //统计完成的进程的状态信息
                    fun14();    //退出
                }
                break;
            }else {
                p = Main.pcbQueue.peek();  //临时变量
                UI.textArea_2_1_3.setText(String.valueOf(p.ProID));
            }
            if(Main.cpu.Timepiece > 0){ //如果CPU时间片大于0运行进程

                fun1(p);     //运行进程
                Main.cpu.Timepiece--;
                UI.textArea_2_1_5.setText(String.valueOf(Main.cpu.Timepiece));
                if (p.IR == p.InstrucNum) {  //如果进程的指令被执行完结束进程
                    fun6(p);  //结束进程
                    Main.pcbQueue.poll();   //移出就绪队列
                    Main.cpu.Timepiece = 3; //恢复时间片
                    UI.textArea_2_1_5.setText(String.valueOf(Main.cpu.Timepiece));
                }
                break;
            }
            Main.pcbQueue.poll();     //移出就绪队列
            Main.cpu.Timepiece = 3;   //恢复时间片
            UI.textArea_2_1_5.setText(String.valueOf(Main.cpu.Timepiece));
            if (p.t % 3 == 0 && p.IR != p.InstrucNum) {  //每3个时间片中断，如果进程尚未完成，进入就绪队列
                fun5(p);             //重新进入就绪队列
                Main.pcbQueue.add(p);
            }
        }
        Main.clock.num++;  //线程每隔一秒进行一次
    }
    private static void showpcbqueue() {
        int count1 = 0;   //就绪队列序号
        String qu = "序号  进程ID  剩余指令数目  进入时间  优先级";
        for (PCB p : Main.pcbQueue) {
            qu += ("\n" + (++count1) + "       " + p.ProID +"          " + (p.InstrucNum - p.IR)  + "           " + p.InTimes + "        " + p.Priority);
        }
        UI.txtrid.setText(qu);
    }
    public static void fun1(PCB p)  {
        if (p.IR == p.InstrucNum)return;
        p.IR++;
        p.PC++;
        p.t++;
        System.out.println(Main.clock.num + "[运行进程：" + p.ProID + "，" + p.IR + "，计算]");
        UI.textArea.append("\n" + Main.clock.num + "[运行进程：" + p.ProID + "，" + p.IR + "，计算]");
    }
    public static void fun2(PCB p)  {
        System.out.println(Main.clock.num + "[新增作业：" + p.ProID + "，" + p.InTimes + "，" + p.InstrucNum + "]");
        UI.textArea.append("\n" + Main.clock.num + "[新增作业：" + p.ProID + "，" + p.InTimes + "，" + p.InstrucNum + "]");
    }
    public static void fun3(PCB p) {
        System.out.println(Main.clock.num + "[创建进程：" + p.ProID + "]");
        UI.textArea.append("\n" + Main.clock.num + "[创建进程：" + p.ProID + "]");
    }
    public static void fun4(PCB p){
        p.PSW = 1;
        p.RqTimes = Main.clock.num;
        System.out.println(Main.clock.num + "[进入就绪队列：" + p.ProID + "，" + (p.InstrucNum - p.IR) + "]");
        UI.textArea.append("\n" + Main.clock.num + "[进入就绪队列：" + p.ProID + "，" + (p.InstrucNum - p.IR) + "]");
    }
    public static void fun5(PCB p){
        System.out.println((Main.clock.num-1) + "[重新进入就绪队列：" + p.ProID + "，" + (p.InstrucNum - p.IR) + "]");
        UI.textArea.append("\n" + (Main.clock.num-1) + "[重新进入就绪队列：" + p.ProID + "，" + (p.InstrucNum - p.IR) + "]");
    }
    public static void fun6(PCB p) {
        p.PSW = 3;
        p.EndTimes = Main.clock.num;
        p.RunTimes = p.EndTimes - p.InTimes;
        Main.count++;
        Main.over += Main.clock.num + "[" + Main.count + "：" + p.InTimes + "+" + p.RqTimes + "+" + p.RunTimes + "]" + "\n";
        System.out.println(Main.clock.num + "[终止进程：" + p.ProID + "]");
        UI.textArea.append("\n" + Main.clock.num + "[终止进程：" + p.ProID + "]");

    }
    public static void fun7(){
        System.out.println(Main.clock.num + "[CPU空闲]");
        UI.textArea.append("\n" + Main.clock.num + "[CPU空闲]");
    }
    public static void fun8() {
        for (PCB p : Main.pcb) {
            if(Main.clock.num >= p.InTimes && p.PSW == 0) {
                fun2(p);
            }
        }
    }
    public static void fun9()  {
        for (PCB p : Main.pcb) {
            if(Main.clock.num >= p.InTimes && p.PSW == 0) {
                fun3(p);
            }
        }
    }
    public static void fun10() {
        for (PCB p : Main.pcb) {
            if(Main.clock.num >= p.InTimes && p.PSW == 0) {
                fun4(p);
                Main.pcbQueue.offer(p);
            }
        }
    }
    public static void fun11(){
        System.out.println("状态统计信息：");
        UI.textArea.append("\n\n" + "状态统计信息：");
        UI.textArea.append("\n" + Main.over);
        System.out.println(Main.over);
    }
    public static void fun12() throws IOException {
        File of = new File("output1\\ProcessResults-初始-LZ.txt");
        Path path = Paths.get("output1\\ProcessResults-"+ (Main.clock.num-1) + "-LZ.txt");
        Files.copy(of.toPath(), path);
    }
    public static void fun13() throws IOException {
        File of = new File("output2\\ProcessResults-初始-LZ.txt");
        Path path = Paths.get("output2\\ProcessResults-"+ (Main.clock.num-1) + "-LZ.txt");
        Files.copy(of.toPath(), path);
    }
    public static void fun14(){
        if(UI.index == 1){
            try {
                fun12();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Main.init();
            Main.c1.cancel();
        }
        if(UI.index == 2){
            try {
                fun13();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Main.init();
            Main.c2.cancel();
        }
    }
}
