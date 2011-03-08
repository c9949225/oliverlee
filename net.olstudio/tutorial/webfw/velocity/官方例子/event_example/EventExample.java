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

import java.io.StringWriter;
import java.util.Properties;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.VelocityContext;

import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.MethodInvocationException;

import org.apache.velocity.runtime.log.LogChute;
import org.apache.velocity.runtime.RuntimeServices;

import org.apache.velocity.app.event.EventCartridge;
import org.apache.velocity.app.event.ReferenceInsertionEventHandler;
import org.apache.velocity.app.event.MethodExceptionEventHandler;
import org.apache.velocity.app.event.NullSetEventHandler;

import org.apache.velocity.context.Context;

/**
 *   This class is a simple demonstration of how the event handling
 *   features of the Velocity Servlet Engine are used.  It uses a
 *   custom logger as well to check the log message stream
 *   when testing the NullSetEventHandler
 *
 * @author <a href="mailto:geirm@optonline.net">Geir Magnusson Jr.</a>
 * @version $Id: EventExample.java 463298 2006-10-12 16:10:32Z henning $
 */
// Oliver 于 2008-5-30 上午12:01:32 作出注释:
// 使用Velocity Servlet 引擎来演示事件处理机制

// ReferenceInsertionEventHandler: 当一个引用被输出的时候调用
// NullSetEventHandler: 当给一个变量赋null值得时候被调用
// MethodExceptionEventHandler: 当方法抛出异常的时候被调用
// LogChute: 日志接口 定义了前缀 ID 等常量
public class EventExample implements ReferenceInsertionEventHandler,
                                     NullSetEventHandler, MethodExceptionEventHandler,
                                     LogChute
{

    private boolean logOutput = false;
    private boolean exceptionSwitch = false;

    public static void main( String args[] )
    {
        EventExample ee = new EventExample();
    }

    public EventExample()
    {
        try
        {
            /*
             * 实现了LogSystem接口
             * 我们可以使用velocity日志功能
             */
            Velocity.setProperty(Velocity.RUNTIME_LOG_LOGSYSTEM, this );
            Velocity.init();
        }
        catch(Exception e)
        {
            System.out.println("Problem initializing Velocity : " + e );
            return;
        }

        /*
         * 创建一个context并加入一些数据
         */
        VelocityContext context = new VelocityContext();
        context.put("name", "Velocity");

        /*
         * 创建一个event cartridge，将所有的事件处理器都附加给它
         */
        EventCartridge ec = new EventCartridge();
        ec.addEventHandler(this); // 本类就是一个事件处理器
        ec.attachToContext( context ); // 将事件处理器容器附加给context

        try
        {
            /*
             *  开始使用单独的动态模板独立地测试每种事件处理器
             *  First, the reference insertion handler
             */

            System.out.println("");
            System.out.println("Velocity Event Handling Demo");
            System.out.println("============================");
            System.out.println("");
            
            // 创建一个字符串模板
            String s = "The word 'Velocity' should be bounded by emoticons :  $name.";

            StringWriter w = new StringWriter();
            Velocity.evaluate( context, w, "mystring", s );

            System.out.println("Reference Insertion Test : ");
            System.out.println("   " +  w.toString());
            System.out.println("");

            /*
             *  using the same handler, we can deal with
             *  null references as well
             */

            s = "There is no reference $floobie, $nullvalue or anything in the brackets : >$!silentnull<";

            w = new StringWriter();
            // Oliver 于 2008-5-30 下午11:10:32 作出注释:
			// mystring当出错的时候用作出错信息的标签
            Velocity.evaluate( context, w, "mystring", s );

            System.out.println("Reference Insertion Test with null references : ");
            System.out.println("   " + w.toString());
            System.out.println("");

            /*
             *  now lets test setting a null value - this test
             *  should result in *no* log output.
             *  Turn on the logger output.
             */

            logOutput = true;

            s = "#set($settest = $NotAReference)";
            w = new StringWriter();

            System.out.println("NullSetEventHandler test : " );
            System.out.print("      There should be nothing between >");
            Velocity.evaluate( context, w, "mystring", s );
            System.out.println("< the brackets.");
            System.out.println("");

            /*
             *  now lets test setting a null value - this test
             *  should result in log output.
             */

            s = "#set($logthis = $NotAReference)";
            w = new StringWriter();

            System.out.println("NullSetEventHandler test : " );
            System.out.print("     There should be a log message between >");
            Velocity.evaluate( context, w, "mystring", s );
            System.out.println("< the brackets.");
            System.out.println("");

            logOutput = false;

            /*
             *  finally, we test a method exception event - we do this
             *  by putting this class in the context, and calling
             *  a method that does nothing but throw an exception.
             *  we use a little switch to turn the event handling
             *  on and off
             *
             *  Note also how the reference insertion process
             *  happens as well
             */

            exceptionSwitch = true;

            context.put("this", this );

            s = " $this.throwException()";
            w = new StringWriter();

            System.out.println("MethodExceptionEventHandler test : " );
            System.out.print("    This exception will be controlled and converted into a string : ");
            Velocity.evaluate( context, w, "mystring", s );
            System.out.println("   " + w.toString());
            System.out.println("");

            /*
             *  now, we turn the switch off, and we can see that the
             *  exception will propgate all the way up here, and
             *  wil be caught by the catch() block below
             */

            exceptionSwitch = false;

            s = " $this.throwException()";
            w = new StringWriter();

            System.out.println("MethodExceptionEventHandler test : " );
            System.out.println("    This exception will NOT be controlled. "
                             + " The next thing you should see is the catch() output ");
            Velocity.evaluate( context, w, "mystring", s );
            System.out.println("If you see this, it didn't work!");

        }
        catch( ParseErrorException pee )
        {
            /*
             * thrown if something is wrong with the
             * syntax of our template string
             */
            System.out.println("ParseErrorException : " + pee );
        }
        catch( MethodInvocationException mee )
        {
            /*
             *  thrown if a method of a reference
             *  called by the template
             *  throws an exception. That won't happen here
             *  as we aren't calling any methods in this
             *  example, but we have to catch them anyway
             */
            System.out.println("   Catch Block : MethodInvocationException : " + mee );
        }
        catch( Exception e )
        {
            System.out.println("Exception : " + e );
        }
    }

    /**
     *  silly method to throw an exception to demonstrate
     *  the method invocation exception event handling
     */
    public void throwException()
        throws Exception
    {
        throw new Exception("Hello from throwException()");
    }

    /**
     *  Event handler for when a reference is inserted into the output stream.
     */
    public Object referenceInsert( String reference, Object value  )
    {
        /*
         *  if we have a value
         *  lets decorate the reference with emoticons
         */

        String s = null;

        if( value != null )
        {
            s = " ;) " + value.toString() + " :-)";
        }
        else
        {
            /*
             * we only want to deal with $floobie - anything
             *  else we let go
             */
            if ( reference.equals("floobie") )
            {
                s = "<no floobie value>";
            }
        }
        // Oliver 于 2008-5-30 下午11:16:47 作出注释:
		// 这样就会起到修饰要显示的值得作用
        return s;
    }

    /**
     *  Event handler for when the right hand side of
     *  a #set() directive is null, which results in
     *  a log message.  This method gives the application
     *  a chance to 'vote' on msg generation
     */
    // Oliver 于 2008-5-30 下午11:19:24 作出注释:
	// 当使用 #set() 给一个 变量赋 null的话 调用该方法
    // 这个方法来是应用决定是否生成该日志
    public boolean shouldLogOnNullSet( String lhs, String rhs )
    {
    	// lhs 为传入的被赋null值的变量
        if (lhs.equals("$settest"))
            return false;

        return true;
    }

    public Object methodException( Class claz, String method, Exception e )
         throws Exception
    {
        /*
         *  only do processing if the switch is on
         */

        if( exceptionSwitch && method.equals("throwException"))
        {
            return "Hello from the methodException() event handler method.";
        }

        throw e;
    }

	/**
	 *  Required init method for LogSystem
	 *  to get access to RuntimeServices
	 */
	 public void init( RuntimeServices rs )
	 {
	 	return;
	 }

    /**
     * This just prints the message and level to System.out.
     */
    public void log(int level, String message)
    {
        if (logOutput)
        {
            System.out.println("level : " + level + " msg : " + message);
        }
    }


    /**
     * This prints the level, message, and the Throwable's message to
     * System.out.
     */
    public void log(int level, String message, Throwable t)
    {
        if (logOutput)
        {
            System.out.println("level : " + level + " msg : " + message + " t : "
                    + t.getMessage());
        }
    }

    /**
     * This always returns true because logging levels can't be disabled in
     * this impl.
     */
    public boolean isLevelEnabled(int level)
    {
        return true;
    }
}
