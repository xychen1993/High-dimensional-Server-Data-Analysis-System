<%@ page import="mondrian.olap.*"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.sql.SQLException"  %>
<%@ page import=" java.sql.Connection"  %>
<%@ page import="java.sql.DriverManager"  %>
<%@ page import="org.olap4j.CellSet" %>
<%@ page import="org.olap4j.Cell" %>
<%@ page import="org.olap4j.OlapConnection"  %>
<%@ page import="org.olap4j.OlapException"  %>
<%@ page import="org.olap4j.OlapStatement"  %>
<%@ page import="org.olap4j.OlapWrapper"  %>
<%@ page import="org.olap4j.metadata.Member"  %>
<%@ page import="org.olap4j.Position"  %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<jsp:useBean id="MondrianResult" scope="session" class="com.renren.monitor.model.MondrianResult"/>
<%
	Class.forName("mondrian.olap4j.MondrianOlap4jDriver");
 	//Connection connection = DriverManager.getConnection("Provider=mondrian;Jdbc=jdbc:mysql://localhost:3306/visualization?user=root;password=''; Catalog=C:/Users/WML/Workspaces/MyEclipse 10/monitorCharts/src/main/webapp/WEB-INF/dataCube.xml; JdbcDrivers=com.mysql.jdbc.Driver;",null);     
	Connection connection= DriverManager.getConnection("jdbc:mondrian:" + 
			        		"Jdbc=jdbc:mysql://localhost:3306/visualization?user=root;password='';" +
			        		"Catalog=C:/Users/WML/Workspaces/MyEclipse 10/monitor/src/main/webapp/WEB-INF/dataCube.xml;");
		
	OlapConnection olapConnection = connection.unwrap(OlapConnection.class);

	String queryStr="select {[TIME].[year].[2015].[7].[10].[18].[30]} ON COLUMNS, {[Measures].[count]} ON ROWS from dataCube ";
	//String queryStr=" SELECT {[time].[],[time].[1998]} ON COLUMNS, {([product].[drink],[customer].[gender].[F]),( [product].[food],[customer].[gender].[M])} ON ROWS FROM [Sales] WHERE ([Measures].[ StoreSales ]) from dataCube ";
	//String queryStr=" select {[Measures].[avg],[Measures].[count]} ON COLUMNS, {[TIME].[month].Members} ON ROWS from dataCube ";

	//Query query =connection.parseQuery(queryStr);
	//Result result = connection.execute(query);
	//PrintWriter pw = new PrintWriter(System.out);
	//result.print(pw);
	//pw.flush();

CellSet cs = null;
		try {
			OlapStatement statement =olapConnection.createStatement();
			cs = statement.executeOlapQuery(queryStr);
		} catch (OlapException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	System.out.println("cs.getAxes().size():"+cs.getAxes().size());
			if(cs.getAxes().size()>1){
			for (Position row : cs.getAxes().get(1)) {
			    
			    for (Position column : cs.getAxes().get(0)) {
			       
			        for (Member member : row.getMembers()) {
			            
			            System.out.println("rows:"+member.getUniqueName());
			        }
			        for (Member member : column.getMembers()) {
			           
			            
			            String aa2= member.getUniqueName();
			            System.out.println("columns:"+aa2);
			            
			            String[] aa= aa2.split("\\.|\\[|\\]");
			            		           
			            for(int i=2;i<aa.length;i++){
			          		if(!aa[i].equals(""))
			          		 System.out.print(aa[i]+"-");
			            }
			            
			        }
			       // System.out.println(column+","+row);
			        Cell cell = cs.getCell(column, row);
			        System.out.println("values:"+cell.getValue());
			        System.out.println();
			    }
			    }
		}else{
			for(Position column:cs.getAxes().get(0))
			{
				for(Member member:column.getMembers()){
					System.out.println("columns:"+member.getUniqueName());
				}
				Cell cell=cs.getCell(column);
				System.out.print("values:"+cell.getValue());
				System.out.println();
			}
		}



	System.out.println("successful!");

%>

 