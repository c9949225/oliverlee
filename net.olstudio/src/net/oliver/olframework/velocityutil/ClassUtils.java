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

import java.io.InputStream;



/**
 * Simple utility functions for manipulating classes and resources
 * from the classloader.
 *
 *  @author <a href="mailto:wglass@apache.org">Will Glass-Husain</a>
 *  @version $Id: ClassUtils.java 463298 2006-10-12 16:10:32Z henning $
 */
// Oliver 于 2008-6-1 下午11:39:01 作出注释:
// 从ClassLoader中操作类和资源的工具类
// 统一都会先从线程的ClassLoader中查找,然后再从System的ClassLoader中查找
public class ClassUtils {

    /**
     * Utility class; cannot be instantiated.
     */
    private ClassUtils()
    {
    }

    /**
     * Return the specified class.  Checks the ThreadContext classloader first,
     * then uses the System classloader.  Should replace all calls to
     * <code>Class.forName( claz )</code> (which only calls the System class
     * loader) when the class might be in a different classloader (e.g. in a
     * webapp).
     *
     * @param clazz the name of the class to instantiate
     * @return the requested Class object
     * @throws ClassNotFoundException
     */
    // Oliver 于 2008-6-1 下午11:39:41 作出注释:
	// 返回指定的Class对象 
    // 首先从ThreadContext的ClassLoader查找,然后使用System的ClassLoader
    // 使用本方法来替换使用Class.forName( claz )方法,因为该方法只会从System的ClassLoader中加载
    // 而有些时候class可能会在不同的,比如应用的ClassLoader中
    public static Class getClass(String clazz) throws ClassNotFoundException
    {
        /**
         * Use the Thread context classloader if possible
         */
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader != null)
        {
            try
            {
                return Class.forName(clazz, true, loader);
            }
            catch (ClassNotFoundException E)
            {
                /**
                 * If not found with ThreadContext loader, fall thru to
                 * try System classloader below (works around bug in ant).
                 */
            }
        }
        /**
         * Thread context classloader isn't working out, so use system loader.
         */
        return Class.forName(clazz);
    }

    /**
     * Return a new instance of the given class.  Checks the ThreadContext
     * classloader first, then uses the System classloader.  Should replace all
     * calls to <code>Class.forName( claz ).newInstance()</code> (which only
     * calls the System class loader) when the class might be in a different
     * classloader (e.g. in a webapp).
     *
     * @param clazz the name of the class to instantiate
     * @return an instance of the specified class
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    // Oliver 于 2008-6-1 下午11:42:07 作出注释:
	// 返回指定类的实例
    public static Object getNewInstance(String clazz)
        throws ClassNotFoundException,IllegalAccessException,InstantiationException
    {
        return getClass(clazz).newInstance();
    }

    /**
     * Finds a resource with the given name.  Checks the Thread Context
     * classloader, then uses the System classloader.  Should replace all
     * calls to <code>Class.getResourceAsString</code> when the resource
     * might come from a different classloader.  (e.g. a webapp).
     * @param claz Class to use when getting the System classloader (used if no Thread
     * Context classloader available or fails to get resource).
     * @param name name of the resource
     * @return InputStream for the resource.
     */
    // 返回指定名字的资源
    public static InputStream getResourceAsStream(Class claz, String name)
    {
        InputStream result = null;

        /**
         * remove leading slash so path will work with classes in a JAR file
         */
        while (name.startsWith("/"))
        {
            name = name.substring(1);
        }

        ClassLoader classLoader = Thread.currentThread()
                                    .getContextClassLoader();

        if (classLoader == null)
        {
            classLoader = claz.getClassLoader();
            result = classLoader.getResourceAsStream( name );
        }
        else
        {
            result= classLoader.getResourceAsStream( name );

            /**
            * for compatibility with texen / ant tasks, fall back to
            * old method when resource is not found.
            */

            if (result == null)
            {
                classLoader = claz.getClassLoader();
                if (classLoader != null)
                    result = classLoader.getResourceAsStream( name );
            }
        }

        return result;

    }


}
