package net.olstudio.test.categories.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

// 本类将计算数组的和平均分配给多核CPU
public class ConcurrentCalculator {   
	  
    private ExecutorService exec;   
    private int cpuCoreNumber;   
    private List<Future<Long>> tasks = new ArrayList<Future<Long>>();   
  
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
  
    public ConcurrentCalculator() {   
        cpuCoreNumber = Runtime.getRuntime().availableProcessors();
        // 根据CPU核数创建线程池
        exec = Executors.newFixedThreadPool(cpuCoreNumber);   
    }   
  
    public Long sum(final int[] numbers) {   
    	
        // 根据CPU核心个数拆分任务，创建FutureTask并提交到Executor ：
    	
    	// 将数组分为好多段，段的个数与CPU的核数相同，然后不同的CPU核处理不同的段
        for (int i = 0; i < cpuCoreNumber; i++) {   
            
        	// 计算每段长度
        	int increment = numbers.length / cpuCoreNumber + 1;   
        	// 为当前核分配对应的段数，相当于移动游标
            int start = increment * i;   
            
            int end = increment * i + increment;   
            // 剩下的可能不足段的长度，以数组长度为主
            if (end > numbers.length)   
                end = numbers.length;   
            
            // 数组内容，开始位置，结束位置
            SumCalculator subCalc = new SumCalculator(numbers, start, end);   
            // 传入FutureTask的必须是 实现了Callable接口
            FutureTask<Long> task = new FutureTask<Long>(subCalc);   
            // 保存FutureTask
            tasks.add(task);   
            
            if (!exec.isShutdown()) {   
                exec.submit(task);   
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
        // 遍历所有保存的分段任务
        for (Future<Long> task : tasks) {   
            try {   
                // 如果计算未完成则阻塞   
                Long subSum = task.get();   
                // 将所有任务结果合并
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

