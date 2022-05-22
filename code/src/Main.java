import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;


public class Main {
    public static Queue<PCB> pcbQueue = new LinkedList<PCB>(); //就绪序列
    public static Clock clock = new Clock();                   //时钟
    public static Timer c1 = new Timer();                      //用于进程的启动与中断
    public static Timer c2 = new Timer();
    public static Timer c3 = new Timer();
    public static Timer c4 = new Timer();
    public static String over = new String();                  //记录进程完成后的信息
    public static int count = 0;                               //记录已经完成的进程数目
    public static Cpu cpu = new Cpu();                         //仿真CPU
    public static ArrayList<PCB> pcb = new ArrayList<>();      //临时数组存放进程
    public static void init(){
        count = 0;
        clock.num = 0;
        over = "";
        cpu.PC = 0;
        cpu.IR = 0;
        cpu.PSW = 0;
        pcb = new ArrayList<>();
    }
    public static void fun1() {
        /*----------------------------以下将输出内容转移到新建文件中---------------------------*/
        PrintStream ps;
        try {
            ps = new PrintStream("output1\\ProcessResults-初始-LZ.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.setOut(ps);  //把创建的打印输出流赋给系统。即系统下次向 ps输出
        FileReader fr = null;
        try {
            fr = new FileReader("input1\\jobs-input.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        /*----------------------------以下读取文件---------------------------*/
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        String[] arrs = null;
        int i = 0;
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            arrs = line.split(",");
            pcb.add(new PCB(Integer.parseInt(arrs[0]), Integer.parseInt(arrs[1]), Integer.parseInt(arrs[2])));
            i++;
        }
        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*----------------------------判断进程数目是否大于5---------------------------*/
        if(pcb.size() < 5){
            UI.textArea.append("进程输入小于5，请添加事件");
            pcb = new ArrayList<>();
            return;
        }
        System.out.println("进程调度事件：");
        UI.textArea.append("进程调度事件：");
        /*----------------------------运行算法---------------------------*/
        c1.schedule(new Process_scheduling_thread(), 0, 1000);
    }
    public static void fun2() {
        PrintStream ps;
        try {
            ps = new PrintStream("output2\\ProcessResults-初始-LZ.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.setOut(ps);//把创建的打印输出流赋给系统。即系统下次向 ps输出
        FileReader fr = null;
        try {
            fr = new FileReader("input2\\jobs-input.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        String[] arrs = null;
        int i = 0;
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            arrs = line.split(",");
            pcb.add(new PCB(Integer.parseInt(arrs[0]), Integer.parseInt(arrs[1]), Integer.parseInt(arrs[2])));
            i++;
        }
        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(pcb.size() < 5){
            UI.textArea.append("进程输入小于5，请添加事件");
            pcb = new ArrayList<>();
            return;
        }
        System.out.println("进程调度事件：");
        UI.textArea.append("进程调度事件：");
        c2.schedule(new Process_scheduling_thread(), 0, 1000);
    }
    public static void fun3() {
        PrintStream ps;
        try {
            ps = new PrintStream("output1\\ProcessResults-初始-DJ.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.setOut(ps);//把创建的打印输出流赋给系统。即系统下次向 ps输出
        FileReader fr = null;
        try {
            fr = new FileReader("input1\\jobs-input.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        String[] arrs = null;
        int i = 0;
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            arrs = line.split(",");
            pcb.add(new PCB(Integer.parseInt(arrs[0]), Integer.parseInt(arrs[1]), Integer.parseInt(arrs[2])));
            i++;
        }
        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(pcb.size() < 5){
            UI.textArea.append("进程输入小于5，请添加事件");
            pcb = new ArrayList<>();
            return;
        }
        System.out.println("进程调度事件：");
        UI.textArea.append("进程调度事件：");
        c3.schedule(new Job_request_thread(), 0, 1000);
    }
    public static void fun4() {
        PrintStream ps;
        try {
            ps = new PrintStream("output2\\ProcessResults-初始-DJ.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.setOut(ps);//把创建的打印输出流赋给系统。即系统下次向 ps输出
        FileReader fr = null;
        try {
            fr = new FileReader("input2\\jobs-input.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        String[] arrs = null;
        int i = 0;
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            arrs = line.split(",");
            pcb.add(new PCB(Integer.parseInt(arrs[0]), Integer.parseInt(arrs[1]), Integer.parseInt(arrs[2])));
            i++;
        }
        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(pcb.size() < 5){
            UI.textArea.append("进程输入小于5，请添加事件");
            pcb = new ArrayList<>();
            return;
        }
        System.out.println("进程调度事件：");
        UI.textArea.append("进程调度事件：");
        c4.schedule(new Job_request_thread(), 0, 1000);
    }

}
