package org.appfuse.webapp.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

/**
 * A simple filter that allows the application to continue using the .html prefix for actions but also allows
 * static files to be served up with the same extension. Dojo to serve up its HTML template code. The filter works
 * on an include/exclude basis where all requests for active pages are redirected by the filter to the dispatch
 * servlet. All Dojo related .html requests are allowed to pass straight through to be processed by the servlet
 * container as per normal.
 * 
 * 
  <filter>
        <filter-name>staticFilter</filter-name>
        <filter-class>org.appfuse.webapp.filter.StaticFilter</filter-class>
        <init-param>
        	<!-- 如果不需要请求到Servlet的就在includes中配置,将不会经过servlet -->
            <param-name>includes</param-name>
            <param-value>/scripts/dojo/*,/dwr/*</param-value>
        </init-param>
        <init-param>
        	<!-- 指明经过哪个servlet -->
            <param-name>servletName</param-name>
            <param-value>tapestry</param-value>
        </init-param>
    </filter>
 */
public class StaticFilter extends OncePerRequestFilter {
	
    private final static String DEFAULT_INCLUDES = "*.html";
    private final static String DEFAULT_EXCLUDES = "";
    private static final String INCLUDES_PARAMETER = "includes";
    private static final String EXCLUDES_PARAMETER = "excludes";
    private static final String SERVLETNAME_PARAMETER = "servletName";
    private String[] excludes;
    private String[] includes;
    private String servletName = null;
    /**
     * Read the includes/excludes parameters and set the filter accordingly.
     */
    public void initFilterBean() {
        String includesParam = getFilterConfig().getInitParameter(INCLUDES_PARAMETER);
        if (StringUtils.isEmpty(includesParam)) {
        	// 如果参数为空,默认includes为*.html
            includes = parsePatterns(DEFAULT_INCLUDES);
        } else {
            includes = parsePatterns(includesParam);
        }

        String excludesParam = getFilterConfig().getInitParameter(EXCLUDES_PARAMETER);
        if (StringUtils.isEmpty(excludesParam)) {
            excludes = parsePatterns(DEFAULT_EXCLUDES);
        } else {
            excludes = parsePatterns(excludesParam);
        }
        // if servletName is specified, set it
        servletName = getFilterConfig().getInitParameter(SERVLETNAME_PARAMETER);
    }

    private String[] parsePatterns(String delimitedPatterns) {
        //make sure no patterns are repeated.
    	// 使用,将字符串分隔为集合
        Set patternSet = org.springframework.util.StringUtils.commaDelimitedListToSet(delimitedPatterns);
        String[] patterns = new String[patternSet.size()];
        int i = 0;
        for (Iterator iterator = patternSet.iterator(); iterator.hasNext(); i++) {
            //no trailing/leading white space.
            String pattern = (String) iterator.next();
            patterns[i] = pattern.trim();
        }
        // 返回数组
        return patterns;
    }

    /**
     * This method checks to see if the current path matches includes or excludes. 
     * 将查当前的请求在includes还是excludes中，如果在includes中，将送到静态资源中，并结束过滤器链接。
     * 如果当前请求在excludes中，将送到指定的servlet中,并将请求传向下一过滤器
     * If it matches includes and
     * not excludes, it forwards to the static resource and ends the filter chain. Otherwise, it forwards to the
     * next filter in the chain.
     * 
     * @param request the current request
     * @param response the current response
     * @param chain the filter chain
     * @throws ServletException when something goes wrong
     * @throws IOException when something goes terribly wrong
     */
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                 FilterChain chain) throws IOException, ServletException {

        UrlPathHelper urlPathHelper = new UrlPathHelper();
        String path = urlPathHelper.getPathWithinApplication(request);
        // path为当前请求路径
        boolean pathExcluded = PatternMatchUtils.simpleMatch(excludes, path);
        boolean pathIncluded = PatternMatchUtils.simpleMatch(includes, path);
        
        // 如果请求路径在include中并且不在exclude中,不会发送到Servlet
        if (pathIncluded && !pathExcluded) {
            if (logger.isDebugEnabled()) {
                logger.debug("Forwarding to static resource: " + path);
            }

            if (path.contains(".html")) {
                response.setContentType("text/html");
            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher(path);
            rd.include(request, response);
            return;
        }
        // 如果请求路径不在includ中或者在excluded中发送到Servlet
        if (servletName != null) {
            RequestDispatcher rd = getServletContext().getNamedDispatcher(servletName);
            rd.forward(request, response);
            return;
        }

        chain.doFilter(request, response);
    }
}
