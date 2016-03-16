<%@ page 	language="java" 
			contentType="text/html; charset=UTF-8"
    		pageEncoding='UTF-8'
    		errorPage="/error.jsp"
    		%>
    		
    		
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<title>Your NoteBook</title>

	<style type="text/css"> body { margin:0; padding:0; } </style>

	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.9.0/build/fonts/fonts-min.css" />
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.9.0/build/datatable/assets/skins/sam/datatable.css" />
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.9.0/build/paginator/assets/skins/sam/paginator.css" />
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.9.0/build/menu/assets/skins/sam/menu.css" />

	<script type="text/javascript" src="http://yui.yahooapis.com/2.9.0/build/yahoo-dom-event/yahoo-dom-event.js"></script>
	<script type="text/javascript" src="http://yui.yahooapis.com/2.9.0/build/dragdrop/dragdrop-min.js"></script>
	<script type="text/javascript" src="http://yui.yahooapis.com/2.9.0/build/element/element-min.js"></script>
	<script type="text/javascript" src="http://yui.yahooapis.com/2.9.0/build/datasource/datasource-min.js"></script>
	<script type="text/javascript" src="http://yui.yahooapis.com/2.9.0/build/event-delegate/event-delegate-min.js"></script>
	<script type="text/javascript" src="http://yui.yahooapis.com/2.9.0/build/datatable/datatable-min.js"></script>
	<script type="text/javascript" src="http://yui.yahooapis.com/2.9.0/build/animation/animation-min.js"></script>
	<script type="text/javascript" src="http://yui.yahooapis.com/2.9.0/build/paginator/paginator-min.js"></script>
	<script type="text/javascript" src="http://yui.yahooapis.com/2.9.0/build/container/container_core-min.js"></script>
	<script type="text/javascript" src="http://yui.yahooapis.com/2.9.0/build/menu/menu-min.js"></script>

	<!--begin custom header content -->
	<style type="text/css">
		/* custom styles */
		.yui-skin-sam .yui-dt-body { cursor:pointer; }
		#single { margin-top:2em; }
		.yui-skin-sam .yui-dt td.align-center { text-align:center; }
		.yui-skin-sam .yui-dt td.align-right { text-align:right; }
		div.yuimenu .bd { zoom: normal; }
	</style>
	<!--end custom header content -->

</head>

<body class="yui-skin-sam">

	<%@ include file="/header.jsp" %>
	
    <c:if test="${message != null}">
		<div align="center">
			<h3><font color="blue"> ${message} </font></h3><br/>
		</div>    
    </c:if>

	<c:choose>
	   	<c:when test="${users.isEmpty()}">
			<div align="center">
				<c:choose>
				    <c:when test="${mode == 'find'}"> <h2> По вашему запросу ничего не найдено. </h2> </c:when>
				    <c:otherwise> <h2> Таблица пользователей пуста. </h2> </c:otherwise>
				</c:choose>
			</div>    
		</c:when>
		<c:otherwise>
			<c:choose>
			   	<c:when test="${mode == 'find'}">
					<c:set var="tableCaption">Результаты поиска для ${findMode}</c:set>
				</c:when>
				<c:otherwise>
					<c:set var="tableCaption">Таблица пользователей</c:set>
				</c:otherwise>
			</c:choose>
			<div align="center">
				<h2> <c:out value="${tableCaption}"/> </h2>
			</div>    
			<div id="idUsersTable" align="center">
			</div>
		</c:otherwise>
	</c:choose>

	<div align="center">
	    <c:if test="${mode == 'edit' && !users.isEmpty()}">
			<h3><font color="blue">Выберите пользователя для редактирования.</font></h3>
			<h5>Редактирование доступно через контекстное меню.</h5> 
		</c:if>
	</div>
	
<script type="text/javascript">
YAHOO.example.Data = { 
	    usersSet: [ 
	    		  	/* <c:forEach items="${users}" var="user"> */ 
	    		  	{ id: +"${user.id}", surname: "${user.surname}", name: "${user.name}", age: +"${user.age}", /*<c:choose> <c:when test="${user.gender == 'MAN'}">*/ gender: "Мужской", /*</c:when> <c:when test="${user.gender == 'WOMEN'}">*/ gender: "Женский", /*</c:when> </c:choose>*/ phone: "${user.phone}" }, 
	    		  	/* </c:forEach> */ 
	    		  	] 
	}
</script>

<script type="text/javascript">
YAHOO.util.Event.addListener(window, "load", function() {
    YAHOO.example.Basic = function() {
        var myColumnDefs = [
            {key:"id", label:"Номер", sortable:true, resizeable:true, width:80, className: 'align-center'},
            {key:"surname", label:"Фамилия", sortable:true, resizeable:true, width:200},
            {key:"name", label:"Имя", sortable:true, resizeable:true, width:200},
            {key:"age", label:"Возраст", sortable:true, resizeable:true, width:80, className: 'align-center'},
            {key:"gender", label:"Пол", sortable:true, resizeable:true, width:100, className: 'align-center'},
            {key:"phone", label:"Телефон", sortable:true, resizeable:true, width:150, className: 'align-center'},
        ];

        var myDataSource = new YAHOO.util.DataSource(YAHOO.example.Data.usersSet);
        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
        myDataSource.responseSchema = {
            fields: ["id", "surname", "name", "age", "gender", "phone"]
        };

 		var myConfigs = { 
			sortedBy:{key:"id", dir:"asc"},
			draggableColumns:true,
            paginator: new YAHOO.widget.Paginator({
                rowsPerPage: 5,
                template: YAHOO.widget.Paginator.TEMPLATE_ROWS_PER_PAGE,
                rowsPerPageOptions: [5,10,25,50,100],
                pageLinks: 5
            }),
		} 
			
		
        var myDataTable = new YAHOO.widget.DataTable("idUsersTable", myColumnDefs, myDataSource, myConfigs);

        myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
        myDataTable.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);
        myDataTable.subscribe("rowClickEvent", myDataTable.onEventSelectRow);
        
        // Programmatically select the first row
        //myDataTable.selectRow(myDataTable.getTrEl(0));

        var onContextMenuClick = function(p_sType, p_aArgs, p_myDataTable) {
            var task = p_aArgs[1];
            if(task) {
                // Extract which TR element triggered the context menu
                var elRow = this.contextEventTarget;
                elRow = p_myDataTable.getTrEl(elRow);

                if(elRow) {
                    switch(task.index) {
                        case 0:     // Delete row upon confirmation
                            var oRecord = p_myDataTable.getRecord(elRow);
                           	post('/NoteBook/upd_users', {id: oRecord.getData("id"), update:true });
                    }
                }
            }
        };

        var myContextMenu = new YAHOO.widget.ContextMenu("mycontextmenu", {trigger:myDataTable.getTbodyEl()});
        myContextMenu.addItem("Редактировать");
        // Render the ContextMenu instance to the parent container of the DataTable
        myContextMenu.render("idUsersTable");
        myContextMenu.clickEvent.subscribe(onContextMenuClick, myDataTable);
		
        return {
            oDS: myDataSource,
            oDT: myDataTable
        };
    }();
});
</script>


<script type="text/javascript">
function post(path, params, method) {
    method = method || "post";

    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);

    for(var key in params) {
        if(params.hasOwnProperty(key)) {
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", key);
            hiddenField.setAttribute("value", params[key]);

            form.appendChild(hiddenField);
         }
    }

    document.body.appendChild(form);
    form.submit();
}
</script>

</body>

</html>
