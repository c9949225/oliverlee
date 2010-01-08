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
// Oliver �� 2008-6-1 ����11:35:06 ����ע��:
// �򵥵Ķ���� ����ThreadPool����
// ��������,���Ϊ�շ���null
public final class SimplePool
{
    /*
     * �������ж��������
     */
    private Object pool[];

    /**
     * �������
     */
    private int max;

    /**
     * ��һ���ۿ�
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
     * ���һ�����󵽳���,������˵Ļ������κη�Ӧ
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
     * �ӳ��л�ȡһ������,��Ϊ�շ���null
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
     * ���سصĴ�С
     */
    public int getMax()
    {
        return max;
    }

    /**
     * Ϊ�˲���,���Է���������
     */
    Object[] getPool()
    {
        return pool;
    }
}
