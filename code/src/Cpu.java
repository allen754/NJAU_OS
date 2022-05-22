public class Cpu {
    public int PC,IR,PSW,Timepiece,Timepiece1,Timepiece2,Timepiece3,num;
    Cpu(){
        this.PC = 0;                //下一条进程ID
        this.IR = 0;                //当前进程ID
        this.PSW = 0;               //状态，本次实验中只有用户态
        this.num = 0;               //用于判断cpu处理第几级反馈队列
        this.Timepiece = 3;         //时间片大小
        this.Timepiece1 = 3;        //这3个时间片用于多级反馈队列
        this.Timepiece2 = 4;
        this.Timepiece3 = 5;
    }
}
