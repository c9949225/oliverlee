public class MaskingThread extends Thread {
   private boolean stop = false;
   private String prompt;
   private int  time;
     public MaskingThread(String prompt, int time){
         //传入提示符和刷新间隔时间
        this.prompt=prompt;
        this.time=time;
     }
     public void run() {
        while(!stop) {
         try {
              sleep(time);
         }catch (InterruptedException e) {
            e.printStackTrace();
         }
          if(!stop){
//执行刷新
// “\b”前面有两个空格
   System.out.print("\r" + prompt + "  \b\b");                }
             System.out.flush();
         }
   }
   //停止线程
   public void stopMasking() {
      this.stop = true;
   }
}