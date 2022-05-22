import java.util.Random;

public class PCB {
    public int ProID,Priority,InTimes,EndTimes,PSW,RunTimes,TurnTimes,InstrucNum,PC,IR,RqNum,RqTimes,t;
    PCB(int proID,int inTimes,int instrucNum){        //进程初始化
        this.ProID = proID;                           //进程ID
        this.InTimes = inTimes;                       //进入时间
        this.InstrucNum = instrucNum;                 //指令数目
        Random random = new Random();
        this.Priority = random.nextInt(4) + 1; //生成1-5随机数
        this.EndTimes = 0;                            //结束时间
        this.PSW = 0;                                 //进程状态 1代表进入就绪队列 2代表进入阻塞队列 3代表进程完成
        this.RunTimes = 0;                            //运行时间
        this.TurnTimes = 0;                           //周转时间
        this.PC = 1;                                  //下一条运行的指令
        this.IR = 0;                                  //当前运行的指令
        this.RqNum = 0;                               //位置编号
        this.RqTimes = 0;                             //进入就绪队列时间
        this.t = 0;                                   //记录当前运行了几条指令
    }


}
