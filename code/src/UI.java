import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;

public class UI extends JFrame {

    private JPanel contentPane;//定义窗口框架
    private int num = 0;//记录对文件一还是文件二操作
    private int num1 = 3;//文件一新增作业从3开始
    private int num2 = 5;//文件二新增作业从5开始
    public static int index = 0;//记录第几个按钮
    public static JTextArea textArea = new JTextArea();//下面都是文本框，因为信息会呈现在文本框中，所以设为全局变量
    public static JTextArea txtrid = new JTextArea();
    public static JTextArea txtrid1 = new JTextArea();
    public static JTextArea txtrid2 = new JTextArea();
    public static JTextArea txtrid3 = new JTextArea();
    public static JTextArea textArea_2_1 = new JTextArea();
    public static JTextArea textArea_2_1_1 = new JTextArea();
    public static JTextArea textArea_2_1_2 = new JTextArea();
    public static JTextArea textArea_2_1_3 = new JTextArea();
    public static JTextArea textArea_2_1_4 = new JTextArea();
    public static JTextArea textArea_2_1_5 = new JTextArea();
    public static JTextArea textArea_2_1_6 = new JTextArea();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UI frame = new UI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * Create the frame.
     */
    public UI() {
        /*----------------------------以下定义界面窗口框架---------------------------*/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1707, 777);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        /*----------------------------以下定义标签---------------------------*/
        JLabel lblNewLabel = new JLabel("事件列表：");
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 32));
        lblNewLabel.setBounds(80, 10, 202, 55);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("就绪队列：");
        lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 32));
        lblNewLabel_1.setBounds(584, 10, 202, 55);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("就绪队列1(用于多级反馈队列)：");
        lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 32));
        lblNewLabel_2.setBounds(1134, 10, 502, 55);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("就绪队列2(用于多级反馈队列)：");
        lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 32));
        lblNewLabel_3.setBounds(1134, 240, 502, 55);
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("就绪队列3(用于多级反馈队列)：");
        lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 32));
        lblNewLabel_4.setBounds(1134, 470, 502, 55);
        contentPane.add(lblNewLabel_4);

        JLabel lblCpu = new JLabel("系统状态：");
        lblCpu.setFont(new Font("宋体", Font.PLAIN, 32));
        lblCpu.setBounds(584, 274, 202, 55);
        contentPane.add(lblCpu);

        JLabel lblCpu_2 = new JLabel("CPU：");
        lblCpu_2.setFont(new Font("宋体", Font.PLAIN, 32));
        lblCpu_2.setBounds(878, 274, 202, 55);
        contentPane.add(lblCpu_2);

        JLabel lblCpu_1 = new JLabel("当前时间：");
        lblCpu_1.setFont(new Font("宋体", Font.PLAIN, 22));
        lblCpu_1.setBounds(584, 322, 202, 55);
        contentPane.add(lblCpu_1);

        JLabel lblCpu_1_1 = new JLabel("并发进程数目：");
        lblCpu_1_1.setFont(new Font("宋体", Font.PLAIN, 22));
        lblCpu_1_1.setBounds(584, 384, 202, 55);
        contentPane.add(lblCpu_1_1);

        JLabel lblCpu_1_2 = new JLabel("PSW:");
        lblCpu_1_2.setFont(new Font("宋体", Font.PLAIN, 22));
        lblCpu_1_2.setBounds(878, 323, 202, 55);
        contentPane.add(lblCpu_1_2);

        JLabel lblCpu_1_3 = new JLabel("PC：");
        lblCpu_1_3.setFont(new Font("宋体", Font.PLAIN, 22));
        lblCpu_1_3.setBounds(878, 384, 202, 55);
        contentPane.add(lblCpu_1_3);

        JLabel lblCpu_1_4 = new JLabel("IR：");
        lblCpu_1_4.setFont(new Font("宋体", Font.PLAIN, 22));
        lblCpu_1_4.setBounds(878, 449, 202, 55);
        contentPane.add(lblCpu_1_4);

        JLabel lblCpu_1_4_1 = new JLabel("剩余时间片：");
        lblCpu_1_4_1.setFont(new Font("宋体", Font.PLAIN, 22));
        lblCpu_1_4_1.setBounds(878, 514, 202, 55);
        contentPane.add(lblCpu_1_4_1);

        JLabel lblCpu_1_1_1 = new JLabel("时间片轮转法：");
        lblCpu_1_1_1.setFont(new Font("宋体", Font.PLAIN, 22));
        lblCpu_1_1_1.setBounds(80, 510, 202, 55);
        contentPane.add(lblCpu_1_1_1);

        JLabel lblCpu_1_1_1_1 = new JLabel("多级反馈队列法：");
        lblCpu_1_1_1_1.setFont(new Font("宋体", Font.PLAIN, 22));
        lblCpu_1_1_1_1.setBounds(301, 510, 202, 55);
        contentPane.add(lblCpu_1_1_1_1);

        JLabel lblCpu_1_1_2 = new JLabel("当前使用的算法：");
        lblCpu_1_1_2.setFont(new Font("宋体", Font.PLAIN, 22));
        lblCpu_1_1_2.setBounds(584, 449, 202, 55);
        contentPane.add(lblCpu_1_1_2);

        JLabel lblCpu_1_1_1_2 = new JLabel("input1：");
        lblCpu_1_1_1_2.setFont(new Font("宋体", Font.PLAIN, 22));
        lblCpu_1_1_1_2.setBounds(10, 559, 97, 55);
        contentPane.add(lblCpu_1_1_1_2);

        JLabel lblCpu_1_1_1_2_1 = new JLabel("input2：");
        lblCpu_1_1_1_2_1.setFont(new Font("宋体", Font.PLAIN, 22));
        lblCpu_1_1_1_2_1.setBounds(10, 625, 97, 55);
        contentPane.add(lblCpu_1_1_1_2_1);

        /*----------------------------文本框---------------------------*/
        textArea.setFont(new Font("宋体", Font.PLAIN, 20));
        textArea.setEditable(false);//设置为不可编辑
        contentPane.add(textArea);
        textArea.setToolTipText("");
        textArea.setBounds(80, 70, 400, 430);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(80, 70, 400, 430);
        scrollPane.setViewportView(textArea);
        contentPane.add(scrollPane);

        txtrid.setBounds(584, 70, 496, 176);
        txtrid.setFont(new Font("宋体", Font.PLAIN, 20));
        txtrid.setEditable(false);//设置为不可编辑
        txtrid.setText("序号  进程ID  剩余指令数目  进入时间  优先级");
        contentPane.add(txtrid);
        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBounds(584, 70, 500, 200);
        scrollPane1.setViewportView(txtrid);
        contentPane.add(scrollPane1);

        txtrid1.setBounds(1134, 70, 496, 156);
        txtrid1.setFont(new Font("宋体", Font.PLAIN, 20));
        txtrid1.setEditable(false);//设置为不可编辑
        txtrid1.setText("序号  进程ID  剩余指令数目  进入时间  优先级");
        contentPane.add(txtrid1);
        JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setBounds(1134, 70, 500, 160);
        scrollPane2.setViewportView(txtrid1);
        contentPane.add(scrollPane2);

        txtrid2.setBounds(1134, 250, 496, 156);
        txtrid2.setFont(new Font("宋体", Font.PLAIN, 20));
        txtrid2.setEditable(false);//设置为不可编辑
        txtrid2.setText("序号  进程ID  剩余指令数目  进入时间  优先级");
        contentPane.add(txtrid2);
        JScrollPane scrollPane3 = new JScrollPane();
        scrollPane3.setBounds(1134, 300, 500, 160);
        scrollPane3.setViewportView(txtrid2);
        contentPane.add(scrollPane3);

        txtrid3.setBounds(1134, 530, 496, 156);
        txtrid3.setFont(new Font("宋体", Font.PLAIN, 20));
        txtrid3.setEditable(false);//设置为不可编辑
        txtrid3.setText("序号  进程ID  剩余指令数目  进入时间  优先级");
        contentPane.add(txtrid3);
        JScrollPane scrollPane4 = new JScrollPane();
        scrollPane4.setBounds(1134, 530, 500, 160);
        scrollPane4.setViewportView(txtrid3);
        contentPane.add(scrollPane4);

        textArea_2_1.setText("0");
        textArea_2_1.setFont(new Font("宋体", Font.PLAIN, 34));
        textArea_2_1.setEditable(false);
        textArea_2_1.setBounds(730, 387, 97, 41);
        contentPane.add(textArea_2_1);

        textArea_2_1_1.setText("");
        textArea_2_1_1.setFont(new Font("宋体", Font.PLAIN, 34));
        textArea_2_1_1.setEditable(false);
        textArea_2_1_1.setBounds(686, 322, 97, 41);
        contentPane.add(textArea_2_1_1);

        textArea_2_1_2.setText("0");
        textArea_2_1_2.setFont(new Font("宋体", Font.PLAIN, 34));
        textArea_2_1_2.setEditable(false);
        textArea_2_1_2.setBounds(925, 322, 87, 41);
        contentPane.add(textArea_2_1_2);

        textArea_2_1_3.setText("");
        textArea_2_1_3.setFont(new Font("宋体", Font.PLAIN, 34));
        textArea_2_1_3.setEditable(false);
        textArea_2_1_3.setBounds(915, 384, 97, 41);
        contentPane.add(textArea_2_1_3);

        textArea_2_1_4.setText("");
        textArea_2_1_4.setFont(new Font("宋体", Font.PLAIN, 34));
        textArea_2_1_4.setEditable(false);
        textArea_2_1_4.setBounds(915, 449, 97, 41);
        contentPane.add(textArea_2_1_4);

        textArea_2_1_5.setText("3");
        textArea_2_1_5.setFont(new Font("宋体", Font.PLAIN, 34));
        textArea_2_1_5.setEditable(false);
        textArea_2_1_5.setBounds(1002, 514, 97, 41);
        contentPane.add(textArea_2_1_5);

        textArea_2_1_6.setText("");
        textArea_2_1_6.setFont(new Font("宋体", Font.PLAIN, 30));
        textArea_2_1_6.setEditable(false);
        textArea_2_1_6.setBounds(584, 498, 236, 41);
        contentPane.add(textArea_2_1_6);

        /*----------------------------以下定义按钮---------------------------*/
        JButton btnNewButton = new JButton("运行");
        btnNewButton.setFont(new Font("宋体", Font.PLAIN, 16));
        btnNewButton.setBounds(103, 560, 131, 55);
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                index = 1;    //标志按钮
                num = 1;      //标志需要打开的文件
                Main.fun1();  //运行算法
                textArea_2_1_6.setText("时间片轮转法");
            }
        });
        contentPane.add(btnNewButton);

        JButton btnNewButton_2 = new JButton("运行");
        btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 16));
        btnNewButton_2.setBounds(328, 560, 131, 55);
        btnNewButton_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                index = 3;
                num = 1;
                Main.fun3();
                textArea_2_1_6.setText("多级反馈队列法");
            }
        });
        contentPane.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("运行");
        btnNewButton_3.setFont(new Font("宋体", Font.PLAIN, 16));
        btnNewButton_3.setBounds(103, 625, 131, 55);
        btnNewButton_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                index = 2;
                num = 2;
                Main.fun2();
                textArea_2_1_6.setText("时间片轮转法");
            }
        });
        contentPane.add(btnNewButton_3);

        JButton btnNewButton_4 = new JButton("运行");
        btnNewButton_4.setFont(new Font("宋体", Font.PLAIN, 16));
        btnNewButton_4.setBounds(328, 625, 131, 55);
        btnNewButton_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                index = 4;
                num = 2;
                Main.fun4();
                textArea_2_1_6.setText("多级反馈队列法");
            }
        });
        contentPane.add(btnNewButton_4);

        JButton btnNewButton_1 = new JButton("添加进程");
        btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 16));
        btnNewButton_1.setBounds(535, 602, 131, 55);
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                PCB p = null;
                if(num == 1){
                    num1++;
                    p = new PCB(num1,Main.clock.num,random.nextInt(15) + 10);  //随机生成10-25条指令的进程
                }
                if(num == 2){
                    num2++;
                    p = new PCB(num2,Main.clock.num,random.nextInt(15) + 10);
                }
                Main.pcb.add(p);
                /*----------------------------以下根据新增作业创建文件---------------------------*/
                String s = new String();
                for (int i = 1; i <= p.InstrucNum; i++) {
                    s += i + "," + "0\n";
                }
                if(num == 1){
                    File file = new File("input1\\" + p.ProID +".txt");
                    try (FileOutputStream fop = new FileOutputStream(file)) {

                        // if file doesn't exists, then create it
                        if (!file.exists()) {
                            file.createNewFile();
                        }

                        // get the content in bytes
                        byte[] contentInBytes = s.getBytes();

                        fop.write(contentInBytes);
                        fop.flush();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                if(num == 2){
                    File file = new File("input2\\" + p.ProID +".txt");
                    try (FileOutputStream fop = new FileOutputStream(file)) {

                        // if file doesn't exists, then create it
                        if (!file.exists()) {
                            file.createNewFile();
                        }

                        // get the content in bytes
                        byte[] contentInBytes = s.getBytes();

                        fop.write(contentInBytes);
                        fop.flush();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        contentPane.add(btnNewButton_1);

        /*JButton btnNewButton_5 = new JButton("提前结束");
        btnNewButton_5.setFont(new Font("宋体", Font.PLAIN, 16));
        btnNewButton_5.setBounds(735, 602, 131, 55);
        btnNewButton_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flag = 1;
            }
        });
        contentPane.add(btnNewButton_5);*/
    }
}
