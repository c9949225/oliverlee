
ʹ��JSTL��SQL��ǿ⣬�ñ�ǿ����ö���ȫ�档

�����Ӵ������ݿ���������ݣ���ȡ���е����ݲ������˵���
�����˵�����ͼ�еĿɼ��Ե��߼�������Servlet ��Struts Action��Servlet Filter��ʵ�֣�
����ֱ��ʹ�������еĴ��롣

���dynamicMenu.JSPҳ������ݣ����Կ�����ҳ���д������������ݵĴ��롣ÿ�μ���ҳ���ʱ�򣬶�����ɾ�����ٴ������������ݡ�

<sql:setDataSource var="db" url="JDBC:hsqldb:db/menu"

    driver="org.hsqldb.jdbcDriver" user="sa" password=""/>

 

<sql:transaction dataSource="${db}">

 

    <sql:update>

        DROP TABLE menu_item IF EXISTS

    </sql:update>

    <sql:update>

        CREATE TABLE menu_item (

           id BIGINT not null,

           parent_name VARCHAR(30),

           name VARCHAR(30),

           title VARCHAR(30),

           description VARCHAR(50),

           location VARCHAR(255),

           target VARCHAR(10),

           onclick VARCHAR(100),

           onmouseover VARCHAR(100),

           onmouseout VARCHAR(100),

           image VARCHAR(50),

           altImage VARCHAR(30),

           tooltip VARCHAR(100),

           roles VARCHAR(100),

           page VARCHAR(255),

           width VARCHAR(5),

           height VARCHAR(5),

           forward VARCHAR(50),

           action VARCHAR(50),

           primary key (id)

        )

    </sql:update>

 

    <sql:update var="updateCount">

        INSERT INTO menu_item

            (id, name, title)

        VALUES

            (1,'DatabaseMenu','Database Menu')

    </sql:update>

    <sql:update var="updateCount">

        INSERT INTO menu_item

            (id, parent_name, name, title, location)

        VALUES

            (2,'DatabaseMenu','Yahoo','Yahoo Mail','http://mail.yahoo.com')

    </sql:update>

    <sql:update var="updateCount">

        INSERT INTO menu_item

            (id, parent_name, name, title, location)

        VALUES

            (3,'DatabaseMenu','JavaBlogs','JavaBlogs','http://javablogs.com')

    </sql:update>

    <sql:update var="updateCount">

        INSERT INTO menu_item

            (id, name, title, location)

        VALUES

            (4,'StandaloneMenu','Standalone Menu','http://raibledesigns.com')

    </sql:update>

    <sql:query var="menus">

        SELECT * FROM menu_item order by id;

    </sql:query>

</sql:transaction>

���ڿ�ʼʹ����Щ���ݹ����˵��Ķ��塣�����ǹ����˵��Ĵ��롣

��һ���ܹ����õ�Ӧ���У�ʹ��Hibernate��iBATIS��JDBC�����ݿ��ж�ȡ���ݡ�
Ȼ��ʹ��ҵ�����Business Delegate��������˭���Կ����˵���
��ServletFilter��ServletContextListener��LoginServlet�е�����Ӧ��ҵ�����

	   MenuRepository repository = new MenuRepository();

        // Get the repository from the application scope - and copy the
        // DisplayerMappings from it.
        MenuRepository defaultRepository = 
        	(MenuRepository)application.getAttribute(MenuRepository.MENU_REPOSITORY_KEY);

        repository.setDisplayers(defaultRepository.getDisplayers());
        
        Result result = (Result) pageContext.getAttribute("menus");

        Map[] rows = result.getRows();

        for (int i=0; i < rows.length; i++) {

            MenuComponent mc = new MenuComponent();
            Map row = rows[i];
            String name = (String) row.get("name");
            mc.setName(name);
            String parent = (String) row.get("parent_name");
            System.out.println(name + ", parent is: " + parent);
            if (parent != null) {
                MenuComponent parentMenu = repository.getMenu(parent);

                if (parentMenu == null) {
                    System.out.println("parentMenu '" + parent + "' doesn't exist!");
                    // create a temporary parentMenu
                    parentMenu = new MenuComponent();
                    parentMenu.setName(parent);
                    repository.addMenu(parentMenu);
                }
                mc.setParent(parentMenu);
            }

            String title = (String) row.get("title");
            mc.setTitle(title);
            String location = (String) row.get("location");
            mc.setLocation(location);
            repository.addMenu(mc);
        }
        // ����������������Ϊ "repository"
        pageContext.setAttribute("repository", repository);
        
//        ���������Ѿ������˲˵��ṹ�⣬ʹ������Ĵ�����ʾ�˵���
      // repository="repository Ҫ�������repository��Ӧ
//    <menu:useMenuDisplayer name="ListMenu" repository="repository">
//
//        <menu:displayMenu name="DatabaseMenu"/>
//
//        <menu:displayMenu name="StandaloneMenu"/>
//
//    </menu:useMenuDisplayer>

//        Ҳ���ԴӲ˵��ṹ���ж�ȡ�˵������֣���ʹ��JSTL��<c:forEach>���ѭ�����ɲ˵���

//        <menu:useMenuDisplayer name="Velocity" config="/templates/xtree.html"
//
//            repository="repository">
//
//          <c:forEach var="menu" items="${repository.topMenus}">
//
//            <menu-el:displayMenu name="${menu.name}"/>
//
//          </c:forEach>
//
//        </menu:useMenuDisplayer>