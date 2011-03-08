// 首先关于问题出现的原因的讨论:
// 在一些较简单的应用中，可以不用DTO而直接将PO传到view中，
// 对于仅仅提供read的view操作，很多需求需要PO的集合。
// 这种情况下，需要在curent thread保存一个open的session。

// 注:
// PO:就是Hibernate直接返回过来的对象,如果对这些对象进行操作,Hibernate要在Session中
// 对这些对象进行同步操作,因为这些对象不是游离对象
// 比如,如果Hibernate设置了延迟加载那么即使返回了一个PO对象但它的属性值还没有取出来,只有当
// 实际调用的时候才会去从数据库里取出来,所以当对象传到View层的话Session要保持Open状态
// Agree不存在这样的问题,可能是因为使用旧的Hibernate版本,默认不使用延迟加载
// DTO:主要用于远程调用等需要大量传输对象的地方。
// 比如我们一张表有100个字段，那么对应的PO就有100个属性。
// 但是我们界面上只要显示10个字段，客户端用WEB service来获取数据，没有必要把整个PO对象传递到客户端，
// 这时我们就可以用只有这10个属性的DTO(脱离Hibernate)来传递结果到客户端，这样也不会暴露服务端表结构.到达客户端以后，如果用这个对象来对应界面显示，那此时它的身份就转为VO

// 所以就有了open session in view 模式。但是在MVC中，这种引入session的逻辑到view中，确实不大好看。
// 因为Session是Model层的东西不应该渗入到View层

// 作为替代，应该可以在DAO中对所有的延迟装载的集合进行初始装载，然后回传到view，当然在这种情况下，会有较多副作用，但是不考虑这些副作用，他所带来的代码的简洁，层次的清晰，开发的独立等等好处就是很吸引人的拉！
// 不知道，你们在PO传递到View层的时候，是怎么处理这种延迟装载的问题。当然，对于绝对反对PO传递到View的人来说，这个帖子就不是他们来讨论的地方了。


// 不用这么麻烦，还是使用spring
// 用beanwrapper包装一下你的对象:
// BeanWrapper bean = new BeanWrapperImpl(object);
//
// 因为spring的BeanWrapper 可以对付嵌套属性，我们可以这么写：
// bean.getPrpperty("object.collectionName.collectionName");
// spring会利用反射去调用相应的getter.
//
// 但这并不解决真正的问题，只是使用上方便一点——只能在对象还在session中使用，而且没有解决我下面提到的问题。
//
// 我想问题还是出在OOP处理集合时的语意上。ORM的作用是要我们在开发时按OOP的方式思考。按照这个思路，假如一个父对象与一个子对象是个一对多的关系，那么父对象就应该持有其子对象的所有引用。所以Hiberate在处理父子关系时，在父对象一端要定义一个集合。这样带来的后果的是，加载父对象时，除了会生成一个"select .... from T_parent where id = ?"这样的sql，还会再生成"select .... from T_child where parent_id=?"这样的语句
//
// 这在语意上是没问题的，但我前面说的我要控制集合加载数据量上就出问题了：要是表很大的话，而且一个父对象有几个子对象，那么这要加载多少数据？特别是，如果我打算让这个父对象脱离session使用时，我会使用select&update，天，我只是希望更新几个属性，它加载那么多数据干什么？
// 当出现嵌套的集合时，可能出现的数据冗余加载，那就更多了
//
// 如果使用open session in view ，这些问题全部不存在，但如果不使用的话，还真是麻烦多多

// Oliver注:延迟加载的作用就在这里，Session打开的情况下，实际需要什么，再加载什么
public Object load(final Class entityClass, final Serializable id ,final boolean isLoadCollections) throws DataAccessException {
return execute(new HibernateCallback() {
public Object doInHibernate(Session session) throws HibernateException {
Object object= session.load(entityClass, id);
System.out.println("=================BeanUtil load Entity:"+id+" "+"Now initial the "+id+" Entity 's collections-------------------------");
if(isLoadCollections)
BeanUtil.loadCollectionsForLazy(object);


return object;
}
});
} 

//What does sessionFactory.getCurrentSession() do? First, you can call it
//as many times and anywhere you
//like, once you get hold of your SessionFactory (easy thanks to
//HibernateUtil). The getCurrentSession()
//method always returns the "current" unit of work. Remember that we
//switched the configuration option for this
//mechanism to "thread" in hibernate.cfg.xml? Hence, the scope of the
//current unit of work is the current Java
//thread that executes our application. However, this is not the full
//truth. A Session begins when it is first
//needed, when the first call to getCurrentSession() is made. It is then
//bound by Hibernate to the current
//thread. When the transaction ends, either committed or rolled back,
//Hibernate also unbinds the Session from
//the thread and closes it for you. If you call getCurrentSession() again,
//you get a new Session and can start a
//new unit of work. This thread-bound programming model is the most
//popular way of using Hibernate.


//The solution, in two-tiered systems, with the action execution, 
//data access through the Session, and the rendering of the view all 
//in the same virtual machine, 
//is to keep the Session open until the view has been rendered.

//If you implement your Session handling with Hibernates built-in support for
//automatic Session context management, see Sessions and transactions, 
//you have half of the code for this already. 
//Now you only need some kind of interceptor that runs after the view has been 
//rendered, and that will then commit the database transaction, 
//hence close the Session. 
//
//In other words, in most applications you need the following: 
//when an HTTP request has to be handled, a new Session and database 
//transaction will begin. Right before the response is send to the client, 
//and after all the work has been done, the transaction will be committed, and the Session will be closed.

// 前提automatic Session context management:

// 1, 写一个类似这样的过滤器
public class HibernateSessionRequestFilter implements Filter
{

    private static Log log = LogFactory
            .getLog(HibernateSessionRequestFilter.class);

    private SessionFactory sf;
    
    public void init(FilterConfig filterConfig) throws ServletException
    {
        log.debug("Initializing filter...");
        log.debug("Obtaining SessionFactory from static HibernateUtil singleton");
        sf = HibernateUtil.getSessionFactory();
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException
    {

        try
        {
            log.debug("Starting a database transaction");
            /*
             * hibernate.current_session_context_class的值得设定Hibernate自带有两个缩写"jta"和"thread"
             * sessionFactory.getCurrentSession()可以在任何地方调用任意次数
             * 如果改为了"thread",并将此Session绑定到当前线程,当事务提交或者回滚,
             * hibernate将session从当前线程中释放，并且关闭session。
             * 当再次调用getCurrentSession()时，将得到一个新的session，并重新开始这一系列工作。
             * 
             * Session session = HibernateUtil.getSessionFactory().getCurrentSession();
             * session.beginTransaction();
             * Event theEvent = new Event();
             * theEvent.setTitle(title);
             * theEvent.setDate(theDate);
             * session.save(theEvent);
             * session.getTransaction().commit();
             * 不需要close session了。
             */
            sf.getCurrentSession().beginTransaction();

            // Call the next filter (continue request processing)
            chain.doFilter(request, response);

            // Commit and cleanup
            log.debug("Committing the database transaction");
            sf.getCurrentSession().getTransaction().commit();

        } catch (StaleObjectStateException staleEx)
        {
            log.error("This interceptor does not implement optimistic concurrency control!");
            log.error("Your application will not work until you add compensation actions!");
            // Rollback, close everything, possibly compensate for any permanent
            // changes
            // during the conversation, and finally restart business
            // conversation. Maybe
            // give the user of the application a chance to merge some of his
            // work with
            // fresh data... what you do here depends on your applications
            // design.
            throw staleEx;
        } catch (Throwable ex)
        {
            // Rollback only
            ex.printStackTrace();
            try
            {
                if (sf.getCurrentSession().getTransaction().isActive())
                {
                    log.debug("Trying to rollback database transaction after exception");
                    sf.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx)
            {
                log.error("Could not rollback transaction after exception!",
                        rbEx);
            }

            // Let others handle it... maybe another interceptor for exceptions?
            throw new ServletException(ex);
        }
    }
    
    public void destroy()
    {
    }
}

// 如果使用了上面的过滤器并且加了自动Session上下文环境管理, 
// 2, 开发一个类似的DAO:

public class ItemDAO {

    Session currentSession;

    // 也可以将Session当作构造函数的参数 这样就可以在使用该Dao的地方负责传入
    public ItemDAO() { 
        currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public Item getItemById(Long itemId) {
        return (Item) currentSession.load(Item.class, itemId);
    }
}

// 3, 控制器比如Servlet
// 不用关注Session

public String execute(HttpRequest request) {

    Long itemId = request.getParameter(ITEM_ID);

    ItemDAO dao = new ItemDAO();

    request.setAttribute( RESULT, dao.getItemById(itemId) );
  
    return "success";
}

// 4, 为所有请求配置过滤器

<filter>
    <filter-name>HibernateFilter</filter-name>
    <filter-class>my.package.HibernateThreadFilter</filter-class>
</filter>

<filter-mapping>
    <filter-name>HibernateFilter</filter-name>
//    <!-- 这个过滤器会为每个请求开启一个Session 如果不需要的话可以调整url-pattern -->
    <url-pattern>/*</url-pattern>
</filter-mapping>


