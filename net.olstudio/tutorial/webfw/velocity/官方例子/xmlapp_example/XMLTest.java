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

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;


/**
 * Example to show basic XML handling in a template.
 *
 * @author <a href="mailto:geirm@optonline.net">Geir Magnusson Jr.</a>
 * @version $Id: XMLTest.java 463298 2006-10-12 16:10:32Z henning $
 */
// Oliver 于 2008-5-31 上午12:26:17 作出注释:
// velocity和xml相关的内容展示
public class XMLTest
{
    public XMLTest( String templateFile)
    {
        Writer writer = null;

        try
        {
            /*
             *  and now call init
             */

            Velocity.init();


            /*
             * 创建Document对象
             */
            SAXBuilder builder;
            Document root = null;

            try
            {
                builder = new SAXBuilder("org.apache.xerces.parsers.SAXParser");
                root = builder.build("test.xml");
            }
            catch( Exception ee)
            {
                System.out.println("Exception building Document : " + ee);
                return;
            }

            // 创建一个velocity context
            VelocityContext context = new VelocityContext();
            // 将xml的根节点放入velocity context
            context.put("root", root);

            // 获取模板
            Template template = Velocity.getTemplate(templateFile);
            writer = new BufferedWriter(new OutputStreamWriter(System.out));
            // 用模板合并context和输出器
            template.merge( context , writer);
        }
        catch( Exception e )
        {
           System.out.println("Exception : " + e);
        }
        finally
        {
            if ( writer != null)
            {
                try
                {
                    writer.flush();
                    writer.close();
                }
                catch( Exception ee )
                {
                    System.out.println("Exception : " + ee );
                }
            }
        }
    }

    public static void main(String[] args)
    {
        XMLTest t;

        if( args.length < 1 )
        {
            System.out.println("Usage : java XMLTest <templatename>");
            return;
        }

        t = new XMLTest(args[0]);
    }
}

