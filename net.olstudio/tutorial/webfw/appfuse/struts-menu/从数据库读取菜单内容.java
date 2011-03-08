
使用JSTL的SQL标记库，该标记库易用而且全面。

本例子创建数据库表、插入数据，读取表中的数据并构建菜单。
决定菜单在视图中的可见性的逻辑可以在Servlet 、Struts Action或Servlet Filter中实现，
可以直接使用例子中的代码。

浏览dynamicMenu.JSP页面的内容，可以看到该页面中创建表并插入数据的代码。每次加载页面的时候，都会先删除表、再创建表，插入数据。

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

现在开始使用这些数据构建菜单的定义。下面是构建菜单的代码。

在一个架构良好的应用中，使用Hibernate、iBATIS或JDBC从数据库中读取数据。
然后，使用业务代表（Business Delegate）来根据谁可以看到菜单，
从ServletFilter、ServletContextListener或LoginServlet中调用相应的业务代表。

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
        // 这里设置了属性名为 "repository"
        pageContext.setAttribute("repository", repository);
        
//        现在我们已经构建了菜单结构库，使用下面的代码显示菜单：
      // repository="repository 要和上面的repository对应
//    <menu:useMenuDisplayer name="ListMenu" repository="repository">
//
//        <menu:displayMenu name="DatabaseMenu"/>
//
//        <menu:displayMenu name="StandaloneMenu"/>
//
//    </menu:useMenuDisplayer>

//        也可以从菜单结构库中读取菜单的名字，并使用JSTL的<c:forEach>标记循环生成菜单：

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