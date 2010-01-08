package net.olstudio.test.categories.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ======����������CompletionService��ʹ��========
 * 
 * �ڸ��ڵ������У�getResult()������ʵ�ֹ����У�������FutureTask�����飬
 * �������û�������ǰ�̻߳�������
 * 
 * �������ϣ��������������ɺ�Ͱ������ӵ�result�У����������εȴ�ÿ��������ɣ�����ʹCompletionService��
 * 
 * ������submit()ִ�е�����ʹ����take()����ɵ����񣬲����������Щ�����˳�������ǵĽ�� ��
 * 
 * Ҳ���ǵ���CompletionService��take����ʱ���᷵�ذ����˳��Ż�����Ľ����
 * CompletionService�ڲ�ά����һ����������BlockingQueue�����û��������ɣ�take()����Ҳ��������
 * 
 * �޸ĸղŵ�����ʹ��CompletionService��
 * 
 * @author u
 */
public class ConcurrentCalculator2 {

	private ExecutorService exec;   
    private CompletionService<Long> completionService;   
  
  
    private int cpuCoreNumber;   
  
    // �ڲ���   
    class SumCalculator implements Callable<Long> {   
        private int[] numbers;   
        private int start;   
        private int end;   
  
        public SumCalculator(final int[] numbers, int start, int end) {   
            this.numbers = numbers;   
            this.start = start;   
            this.end = end;   
        }   
  
        public Long call() throws Exception {   
            Long sum = 0l;   
            for (int i = start; i < end; i++) {   
                sum += numbers[i];   
            }   
            return sum;   
        }   
    }     
  
    public ConcurrentCalculator2() {   
        cpuCoreNumber = Runtime.getRuntime().availableProcessors();   
        exec = Executors.newFixedThreadPool(cpuCoreNumber);   
        // ��ExecutorService����ExecutorCompletionService
        completionService = new ExecutorCompletionService<Long>(exec);   
  
  
    }   
  
    public Long sum(final int[] numbers) {   
        // ����CPU���ĸ���������񣬴���FutureTask���ύ��Executor   
        for (int i = 0; i < cpuCoreNumber; i++) {   
            int increment = numbers.length / cpuCoreNumber + 1;   
            int start = increment * i;   
            int end = increment * i + increment;   
            if (end > numbers.length)   
                end = numbers.length;   
            SumCalculator subCalc = new SumCalculator(numbers, start, end);  
            
            // ����Ͳ���ʹ��FutureTask��ExecutorService������ʹ��CompletionService�ύ����
            if (!exec.isShutdown()) {   
                completionService.submit(subCalc);   
            }   
        }   
        return getResult();   
    }   
  
    /**  
     * ����ÿ��ֻ���񣬻�ò��ֺͣ���ӷ���  
     *   
     * @return  
     */  
    public Long getResult() {   
        Long result = 0l;   
        for (int i = 0; i < cpuCoreNumber; i++) {               
            try {   
            	// CompletionService�ڲ�ά����һ����������BlockingQueue�����û��������ɣ�take()����Ҳ��������
                Long subSum = completionService.take().get();   
                result += subSum;              
            } catch (InterruptedException e) {   
                e.printStackTrace();   
            } catch (ExecutionException e) {   
                e.printStackTrace();   
            }   
        }   
        return result;   
    }   
  
    public void close() {   
        exec.shutdown();   
    }   

}
