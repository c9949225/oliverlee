package net.olstudio.test.categories.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

// ���ཫ��������ĺ�ƽ����������CPU
public class ConcurrentCalculator {   
	  
    private ExecutorService exec;   
    private int cpuCoreNumber;   
    private List<Future<Long>> tasks = new ArrayList<Future<Long>>();   
  
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
  
    public ConcurrentCalculator() {   
        cpuCoreNumber = Runtime.getRuntime().availableProcessors();
        // ����CPU���������̳߳�
        exec = Executors.newFixedThreadPool(cpuCoreNumber);   
    }   
  
    public Long sum(final int[] numbers) {   
    	
        // ����CPU���ĸ���������񣬴���FutureTask���ύ��Executor ��
    	
    	// �������Ϊ�ö�Σ��εĸ�����CPU�ĺ�����ͬ��Ȼ��ͬ��CPU�˴���ͬ�Ķ�
        for (int i = 0; i < cpuCoreNumber; i++) {   
            
        	// ����ÿ�γ���
        	int increment = numbers.length / cpuCoreNumber + 1;   
        	// Ϊ��ǰ�˷����Ӧ�Ķ������൱���ƶ��α�
            int start = increment * i;   
            
            int end = increment * i + increment;   
            // ʣ�µĿ��ܲ���εĳ��ȣ������鳤��Ϊ��
            if (end > numbers.length)   
                end = numbers.length;   
            
            // �������ݣ���ʼλ�ã�����λ��
            SumCalculator subCalc = new SumCalculator(numbers, start, end);   
            // ����FutureTask�ı����� ʵ����Callable�ӿ�
            FutureTask<Long> task = new FutureTask<Long>(subCalc);   
            // ����FutureTask
            tasks.add(task);   
            
            if (!exec.isShutdown()) {   
                exec.submit(task);   
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
        // �������б���ķֶ�����
        for (Future<Long> task : tasks) {   
            try {   
                // �������δ���������   
                Long subSum = task.get();   
                // �������������ϲ�
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

