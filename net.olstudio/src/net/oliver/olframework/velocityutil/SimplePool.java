package net.oliver.olframework.velocityutil;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.    
 */

/**
 * Simple object pool. Based on ThreadPool and few other classes
 *
 * The pool will ignore overflow and return null if empty.
 *
 * @author Gal Shachor
 * @author Costin
 * @author <a href="mailto:geirm@optonline.net">Geir Magnusson Jr.</a>
 * @version $Id: SimplePool.java 463298 2006-10-12 16:10:32Z henning $
 */
// Oliver 于 2008-6-1 下午11:35:06 作出注释:
// 简单的对象池 基于ThreadPool等类
// 会忽略溢出,如果为空返回null
public final class SimplePool
{
    /*
     * 保存所有对象的容器
     */
    private Object pool[];

    /**
     * 最大数量
     */
    private int max;

    /**
     * 下一个槽口
     */
    private int current=-1;

    /**
     * @param max
     */
    public SimplePool(int max)
    {
        this.max = max;
        pool = new Object[max];
    }

    /**
     * 添加一个对象到池中,如果满了的话不做任何反应
     */
    public void put(Object o)
    {
        int idx=-1;

        synchronized(this)
        {
            /*
             *  if we aren't full
             */

            if (current < max - 1)
            {
                /*
                 *  then increment the
                 *  current index.
                 */
                idx = ++current;
            }

            if (idx >= 0)
            {
                pool[idx] = o;
            }
        }
    }

    /**
     * 从池中获取一个对象,若为空返回null
     */
    public Object get()
    {
        synchronized(this)
        {
            /*
             *  if we have any in the pool
             */
            if( current >= 0 )
            {
                /*
                 *  remove the current one
                 */

                Object o = pool[current];
                pool[current] = null;

                current--;

                return o;
            }
        }

        return null;
    }

    /**
     * 返回池的大小
     */
    public int getMax()
    {
        return max;
    }

    /**
     * 为了测试,可以返回整个池
     */
    Object[] getPool()
    {
        return pool;
    }
}
