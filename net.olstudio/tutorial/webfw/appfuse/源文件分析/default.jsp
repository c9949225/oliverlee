<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <%@ include file="/common/meta.jsp" %>
        <title><decorator:title/> | <fmt:message key="webapp.name"/></title>

        <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/${appConfig["csstheme"]}/theme.css'/>" />
        <link rel="stylesheet" type="text/css" media="print" href="<c:url value='/styles/${appConfig["csstheme"]}/print.css'/>" />

        <script type="text/javascript" src="<c:url value='/scripts/prototype.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/scripts/scriptaculous.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/scripts/global.js'/>"></script>
        <decorator:head/>
    </head>
<!-- 使用被修饰页面的属性设置body的id class -->
<body<decorator:getProperty property="body.id" writeEntireProperty="true"/><decorator:getProperty property="body.class" writeEntireProperty="true"/>>

    <div id="page">
    
    	<!-- header -->
        <div id="header" class="clearfix">
            <jsp:include page="/common/header.jsp"/>
        </div>
	    <!-- content -->
        <div id="content" class="clearfix">
            <div id="main">
                <%@ include file="/common/messages.jsp" %>
                <!-- 使用被修饰页面的heading -->
                <h1><decorator:getProperty property="meta.heading"/></h1>
                <!-- 使用被修饰页面的body -->
                <decorator:body/>
            </div>
			<!-- 设置当前菜单类型 -->
            <c:set var="currentMenu" scope="request"><decorator:getProperty property="meta.menu"/></c:set>
            <!-- 如果当前菜单是管理菜单 -->
            <c:if test="${currentMenu == 'AdminMenu'}">
            <!-- 加入右侧的垂直管理菜单 -->
            <div id="sub">
                <menu:useMenuDisplayer name="Velocity" config="cssVerticalMenu.vm" permissions="rolesAdapter">
                    <menu:displayMenu name="AdminMenu"/>
                </menu:useMenuDisplayer>
            </div>
            </c:if>
		    <!-- 一般的浏览菜单 -->
            <div id="nav">
                <div class="wrapper">
                    <h2 class="accessibility">Navigation</h2>
                    <jsp:include page="/common/menu.jsp"/>
                </div>
                <hr/>
            </div><!-- end nav -->
        </div>

        <div id="footer" class="clearfix">
            <jsp:include page="/common/footer.jsp"/>
        </div>
    </div>
</body>
</html>
