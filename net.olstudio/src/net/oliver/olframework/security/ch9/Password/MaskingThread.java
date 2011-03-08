public class MaskingThread extends Thread {
   private boolean stop = false;
   private String prompt;
   private int  time;
     public MaskingThread(String prompt, int time){
         //������ʾ����ˢ�¼��ʱ��
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
//ִ��ˢ��
// ��\b��ǰ���������ո�
   System.out.print("\r" + prompt + "  \b\b");                }
             System.out.flush();
         }
   }
   //ֹͣ�߳�
   public void stopMasking() {
      this.stop = true;
   }
}