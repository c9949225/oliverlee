// ���ȹ���������ֵ�ԭ�������:
// ��һЩ�ϼ򵥵�Ӧ���У����Բ���DTO��ֱ�ӽ�PO����view�У�
// ���ڽ����ṩread��view�������ܶ�������ҪPO�ļ��ϡ�
// ��������£���Ҫ��curent thread����һ��open��session��

// ע:
// PO:����Hibernateֱ�ӷ��ع����Ķ���,�������Щ������в���,HibernateҪ��Session��
// ����Щ�������ͬ������,��Ϊ��Щ�������������
// ����,���Hibernate�������ӳټ�����ô��ʹ������һ��PO������������ֵ��û��ȡ����,ֻ�е�
// ʵ�ʵ��õ�ʱ��Ż�ȥ�����ݿ���ȡ����,���Ե����󴫵�View��Ļ�SessionҪ����Open״̬
// Agree����������������,��������Ϊʹ�þɵ�Hibernate�汾,Ĭ�ϲ�ʹ���ӳټ���
// DTO:��Ҫ����Զ�̵��õ���Ҫ�����������ĵط���
// ��������һ�ű���100���ֶΣ���ô��Ӧ��PO����100�����ԡ�
// �������ǽ�����ֻҪ��ʾ10���ֶΣ��ͻ�����WEB service����ȡ���ݣ�û�б�Ҫ������PO���󴫵ݵ��ͻ��ˣ�
// ��ʱ���ǾͿ�����ֻ����10�����Ե�DTO(����Hibernate)�����ݽ�����ͻ��ˣ�����Ҳ���ᱩ¶����˱�ṹ.����ͻ����Ժ�����������������Ӧ������ʾ���Ǵ�ʱ������ݾ�תΪVO

// ���Ծ�����open session in view ģʽ��������MVC�У���������session���߼���view�У�ȷʵ����ÿ���
// ��ΪSession��Model��Ķ�����Ӧ�����뵽View��

// ��Ϊ�����Ӧ�ÿ�����DAO�ж����е��ӳ�װ�صļ��Ͻ��г�ʼװ�أ�Ȼ��ش���view����Ȼ����������£����н϶ั���ã����ǲ�������Щ�����ã����������Ĵ���ļ�࣬��ε������������Ķ����ȵȺô����Ǻ������˵�����
// ��֪����������PO���ݵ�View���ʱ������ô���������ӳ�װ�ص����⡣��Ȼ�����ھ��Է���PO���ݵ�View������˵��������ӾͲ������������۵ĵط��ˡ�


// ������ô�鷳������ʹ��spring
// ��beanwrapper��װһ����Ķ���:
// BeanWrapper bean = new BeanWrapperImpl(object);
//
// ��Ϊspring��BeanWrapper ���ԶԸ�Ƕ�����ԣ����ǿ�����ôд��
// bean.getPrpperty("object.collectionName.collectionName");
// spring�����÷���ȥ������Ӧ��getter.
//
// ���Ⲣ��������������⣬ֻ��ʹ���Ϸ���һ�㡪��ֻ���ڶ�����session��ʹ�ã�����û�н���������ᵽ�����⡣
//
// �������⻹�ǳ���OOP������ʱ�������ϡ�ORM��������Ҫ�����ڿ���ʱ��OOP�ķ�ʽ˼�����������˼·������һ����������һ���Ӷ����Ǹ�һ�Զ�Ĺ�ϵ����ô�������Ӧ�ó������Ӷ�����������á�����Hiberate�ڴ����ӹ�ϵʱ���ڸ�����һ��Ҫ����һ�����ϡ����������ĺ�����ǣ����ظ�����ʱ�����˻�����һ��"select .... from T_parent where id = ?"������sql������������"select .... from T_child where parent_id=?"���������
//
// ������������û����ģ�����ǰ��˵����Ҫ���Ƽ��ϼ����������Ͼͳ������ˣ�Ҫ�Ǳ�ܴ�Ļ�������һ���������м����Ӷ�����ô��Ҫ���ض������ݣ��ر��ǣ�����Ҵ������������������sessionʹ��ʱ���һ�ʹ��select&update���죬��ֻ��ϣ�����¼������ԣ���������ô�����ݸ�ʲô��
// ������Ƕ�׵ļ���ʱ�����ܳ��ֵ�����������أ��Ǿ͸�����
//
// ���ʹ��open session in view ����Щ����ȫ�������ڣ��������ʹ�õĻ����������鷳���

// Oliverע:�ӳټ��ص����þ������Session�򿪵�����£�ʵ����Ҫʲô���ټ���ʲô
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

// ǰ��automatic Session context management:

// 1, дһ�����������Ĺ�����
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
             * hibernate.current_session_context_class��ֵ���趨Hibernate�Դ���������д"jta"��"thread"
             * sessionFactory.getCurrentSession()�������κεط������������
             * �����Ϊ��"thread",������Session�󶨵���ǰ�߳�,�������ύ���߻ع�,
             * hibernate��session�ӵ�ǰ�߳����ͷţ����ҹر�session��
             * ���ٴε���getCurrentSession()ʱ�����õ�һ���µ�session�������¿�ʼ��һϵ�й�����
             * 
             * Session session = HibernateUtil.getSessionFactory().getCurrentSession();
             * session.beginTransaction();
             * Event theEvent = new Event();
             * theEvent.setTitle(title);
             * theEvent.setDate(theDate);
             * session.save(theEvent);
             * session.getTransaction().commit();
             * ����Ҫclose session�ˡ�
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

// ���ʹ��������Ĺ��������Ҽ����Զ�Session�����Ļ�������, 
// 2, ����һ�����Ƶ�DAO:

public class ItemDAO {

    Session currentSession;

    // Ҳ���Խ�Session�������캯���Ĳ��� �����Ϳ�����ʹ�ø�Dao�ĵط�������
    public ItemDAO() { 
        currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public Item getItemById(Long itemId) {
        return (Item) currentSession.load(Item.class, itemId);
    }
}

// 3, ����������Servlet
// ���ù�עSession

public String execute(HttpRequest request) {

    Long itemId = request.getParameter(ITEM_ID);

    ItemDAO dao = new ItemDAO();

    request.setAttribute( RESULT, dao.getItemById(itemId) );
  
    return "success";
}

// 4, Ϊ�����������ù�����

<filter>
    <filter-name>HibernateFilter</filter-name>
    <filter-class>my.package.HibernateThreadFilter</filter-class>
</filter>

<filter-mapping>
    <filter-name>HibernateFilter</filter-name>
//    <!-- �����������Ϊÿ��������һ��Session �������Ҫ�Ļ����Ե���url-pattern -->
    <url-pattern>/*</url-pattern>
</filter-mapping>


