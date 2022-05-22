import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Job_request_thread extends TimerTask{
    public static Queue<PCB> pcbQueue1 = new LinkedList<PCB>(); //这三个队列用于多级反馈队列算法
    public static Queue<PCB> pcbQueue2 = new LinkedList<PCB>();
    public static Queue<PCB> pcbQueue3 = new LinkedList<PCB>();
    public static int[] a = new int[1005]; //记录PC信息
    @Override
    public void run() {
        /*----------------------------判断进程数目是否大于10---------------------------*/
        if(Main.pcb.size() > 10){
            UI.textArea.setText("进程超过10个停止运行");
            if(UI.index == 3){
                Main.init();
                Main.c3.cancel();
            }
            if(UI.index == 4){
                Main.init();
                Main.c4.cancel();
            }
        }
        showpcbqueue(); //显示队列
        UI.textArea_2_1_1.setText(String.valueOf(Main.clock.num)); //显示当前时间
        UI.textArea_2_1.setText(String.valueOf(Main.pcb.size()));  //显示当前进程数目
        Scanner scan = new Scanner(System.in);
        PCB p = null;
        while(true){
            if(Main.clock.num != 0 && Main.clock.num % 10 == 0){
                fun8();         //新增作业
                fun9();         //创建进程
                fun10();        //进入就绪队列
                showpcbqueue(); //显示队列信息
            }
            if(pcbQueue1.isEmpty() && pcbQueue2.isEmpty() && pcbQueue3.isEmpty()){
                fun7();         //显示CPU空闲
                UI.textArea_2_1_3.setText("");
                UI.textArea_2_1_4.setText("");
                if(Main.count == Main.pcb.size()){ //如果已完成进程数目等于新建作业数目则退出
                    fun11();    //统计完成的进程的状态信息
                    fun14();    //退出
                }
                break;
            }else {  //三个if判断，队列优先级高的先运行
                if(!pcbQueue1.isEmpty()){
                    p = pcbQueue1.peek();
                    Main.cpu.num = 1;
                }else if(!pcbQueue2.isEmpty()){
                    p = pcbQueue2.peek();
                    Main.cpu.num = 2;
                }else{
                    p = pcbQueue3.peek();
                    Main.cpu.num = 3;
                }
            }
            if(Main.cpu.num == 1){   //运行第一级就绪队列
                if(Main.cpu.Timepiece1 > 0){                                         //CPU时间片大于0即可运行
                    UI.textArea_2_1_3.setText(String.valueOf(p.ProID));
                    a[Main.clock.num] = p.ProID;                                     //记录PC信息
                    UI.textArea_2_1_4.setText(String.valueOf(a[Main.clock.num-1]));
                    fun1(p); //运行指令
                    Main.cpu.Timepiece1--;
                    UI.textArea_2_1_5.setText(String.valueOf(Main.cpu.Timepiece1));
                    if (p.IR == p.InstrucNum) { //如果进程的指令被执行完结束进程
                        fun6(p); //结束进程
                        Main.pcbQueue.poll(); //移出就绪队列
                        Main.cpu.Timepiece1 = 3; //恢复时间片
                        UI.textArea_2_1_5.setText(String.valueOf(Main.cpu.Timepiece1));
                    }
                    break;
                }
                pcbQueue1.poll(); //移出就绪队列
                Main.cpu.Timepiece1 = 3; //恢复时间片
                UI.textArea_2_1_5.setText(String.valueOf(Main.cpu.Timepiece1));
                if (p.t % 3 == 0 && p.IR != p.InstrucNum) { //根据该队列的CPU时间片中断，如果进程尚未完成，进入就绪队列
                    fun5(p,2);   //重新进入就绪队列
                    pcbQueue2.add(p);
                    p.t = 0;
                }
            }
            if(Main.cpu.num == 2){  //运行第二级就绪队列
                if(Main.cpu.Timepiece2 > 0){
                    UI.textArea_2_1_3.setText(String.valueOf(p.ProID));
                    a[Main.clock.num] = p.ProID;
                    UI.textArea_2_1_4.setText(String.valueOf(a[Main.clock.num-1]));
                    fun1(p);
                    Main.cpu.Timepiece2--;
                    UI.textArea_2_1_5.setText(String.valueOf(Main.cpu.Timepiece2));
                    if (p.IR == p.InstrucNum) {
                        fun6(p);
                        pcbQueue2.poll();
                        Main.cpu.Timepiece2 = 4;
                        UI.textArea_2_1_5.setText(String.valueOf(Main.cpu.Timepiece2));
                    }
                    break;
                }
                pcbQueue2.poll();
                Main.cpu.Timepiece2 = 4;
                UI.textArea_2_1_5.setText(String.valueOf(Main.cpu.Timepiece2));
                if (p.t % 4 == 0 && p.IR != p.InstrucNum) {
                    fun5(p,3);
                    pcbQueue3.add(p);
                    p.t = 0;
                }
            }
            if(Main.cpu.num == 3){  //运行第三级就绪队列
                if(Main.cpu.Timepiece3 > 0){
                    UI.textArea_2_1_3.setText(String.valueOf(p.ProID));
                    a[Main.clock.num] = p.ProID;
                    UI.textArea_2_1_4.setText(String.valueOf(a[Main.clock.num-1]));
                    fun1(p);
                    Main.cpu.Timepiece3--;
                    UI.textArea_2_1_5.setText(String.valueOf(Main.cpu.Timepiece3));
                    if (p.IR == p.InstrucNum) {
                        fun6(p);
                        pcbQueue3.poll();
                        Main.cpu.Timepiece3 = 5;
                        UI.textArea_2_1_5.setText(String.valueOf(Main.cpu.Timepiece3));
                    }
                    break;
                }
                pcbQueue3.poll();
                Main.cpu.Timepiece3 = 5;
                UI.textArea_2_1_5.setText(String.valueOf(Main.cpu.Timepiece3));
                if (p.t % 5 == 0 && p.IR != p.InstrucNum) {
                    fun5(p,3);
                    pcbQueue3.add(p);
                    p.t = 0;
                }
            }
        }
        Main.clock.num++;   //线程每隔一秒进行一次
    }
    private static void showpcbqueue() {
        int count1 = 0;   //就绪队列序号
        String qu = "序号  进程ID  剩余指令数目  进入时间  优先级";
        for (PCB p : pcbQueue1) {
            qu += ("\n" + (++count1) + "       " + p.ProID +"          " + (p.InstrucNum - p.IR)  + "           " + p.InTimes + "        " + p.Priority);
        }
        UI.txtrid1.setText(qu);
        count1 = 0;
        qu = "序号  进程ID  剩余指令数目  进入时间  优先级";
        for (PCB p : pcbQueue2) {
            qu += ("\n" + (++count1) + "       " + p.ProID +"          " + (p.InstrucNum - p.IR)  + "           " + p.InTimes + "        " + p.Priority);
        }
        UI.txtrid2.setText(qu);
        count1 = 0;   //就绪队列序号
        qu = "序号  进程ID  剩余指令数目  进入时间  优先级";
        for (PCB p : pcbQueue3) {
            qu += ("\n" + (++count1) + "       " + p.ProID +"          " + (p.InstrucNum - p.IR)  + "           " + p.InTimes + "        " + p.Priority);
        }
        UI.txtrid3.setText(qu);
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
    public static void fun4(PCB p,int num){
        p.PSW = 1;
        p.RqTimes = Main.clock.num;
        System.out.println(Main.clock.num + "[进入第" + num + "级就绪队列：" + p.ProID + "，" + (p.InstrucNum - p.IR) + "]");
        UI.textArea.append("\n" + Main.clock.num + "[进入第" + num + "级就绪队列：" + p.ProID + "，" + (p.InstrucNum - p.IR) + "]");
    }
    public static void fun5(PCB p,int num){
        System.out.println(Main.clock.num + "[重新进入第" + num + "级就绪队列："+ p.ProID + "，" + (p.InstrucNum - p.IR) + "]");
        UI.textArea.append("\n" + (Main.clock.num-1) + "[重新进入第" + num + "级就绪队列："+ p.ProID + "，" + (p.InstrucNum - p.IR) + "]");
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
                fun4(p,1);
                pcbQueue1.offer(p);
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
        File of = new File("output1\\ProcessResults-初始-DJ.txt");
        Path path = Paths.get("output1\\ProcessResults-"+ (Main.clock.num-1) + "-DJ.txt");
        Files.copy(of.toPath(), path);
    }
    public static void fun13() throws IOException {
        File of = new File("output2\\ProcessResults-初始-DJ.txt");
        Path path = Paths.get("output2\\ProcessResults-"+ (Main.clock.num-1) + "-DJ.txt");
        Files.copy(of.toPath(), path);
    }
    public static void fun14(){
        a = new int[1005];
        if(UI.index == 3){
            try {
                fun12();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Main.init();
            Main.c3.cancel();
        }
        if(UI.index == 4){
            try {
                fun13();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Main.init();
            Main.c4.cancel();
        }
    }
}
