package net.olstudio.test.categories.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ======并发计算中CompletionService的使用========
 * 
 * 在刚在的例子中，getResult()方法的实现过程中，迭代了FutureTask的数组，
 * 如果任务还没有完成则当前线程会阻塞，
 * 
 * 如果我们希望任意字任务完成后就把其结果加到result中，而不用依次等待每个任务完成，可以使CompletionService。
 * 
 * 生产者submit()执行的任务。使用者take()已完成的任务，并按照完成这些任务的顺序处理它们的结果 。
 * 
 * 也就是调用CompletionService的take方法时，会返回按完成顺序放回任务的结果，
 * CompletionService内部维护了一个阻塞队列BlockingQueue，如果没有任务完成，take()方法也会阻塞。
 * 
 * 修改刚才的例子使用CompletionService：
 * 
 * @author u
 */
public class ConcurrentCalculator2 {

	private ExecutorService exec;   
    private CompletionService<Long> completionService;   
  
  
    private int cpuCoreNumber;   
  
    // 内部类   
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
        // 将ExecutorService传入ExecutorCompletionService
        completionService = new ExecutorCompletionService<Long>(exec);   
  
  
    }   
  
    public Long sum(final int[] numbers) {   
        // 根据CPU核心个数拆分任务，创建FutureTask并提交到Executor   
        for (int i = 0; i < cpuCoreNumber; i++) {   
            int increment = numbers.length / cpuCoreNumber + 1;   
            int start = increment * i;   
            int end = increment * i + increment;   
            if (end > numbers.length)   
                end = numbers.length;   
            SumCalculator subCalc = new SumCalculator(numbers, start, end);  
            
            // 这里就不再使用FutureTask和ExecutorService，而是使用CompletionService提交任务
            if (!exec.isShutdown()) {   
                completionService.submit(subCalc);   
            }   
        }   
        return getResult();   
    }   
  
    /**  
     * 迭代每个只任务，获得部分和，相加返回  
     *   
     * @return  
     */  
    public Long getResult() {   
        Long result = 0l;   
        for (int i = 0; i < cpuCoreNumber; i++) {               
            try {   
            	// CompletionService内部维护了一个阻塞队列BlockingQueue，如果没有任务完成，take()方法也会阻塞。
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
