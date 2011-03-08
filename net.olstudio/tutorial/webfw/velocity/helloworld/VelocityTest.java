import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.servlet.VelocityServlet;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

public class VelocityTest extends VelocityServlet
{
    protected Properties loadConfiguration(ServletConfig config)
    {
        Properties properties = new Properties();
        properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, config.getServletContext().getRealPath("/"));

        return properties;
    }

    public Template handleRequest(HttpServletRequest request,
            HttpServletResponse response, Context context)
    {

        context.put("hello", "Hello Velocity World!");
        String rules[] = { "Hello", "Velocity", "World!" };
        context.put("rules", rules);
        Template tpl = null;
        try
        {
            tpl = getTemplate("hello.vm");
        } catch (ResourceNotFoundException e)
        {
            e.printStackTrace();
        } catch (ParseErrorException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return tpl;
    }
}