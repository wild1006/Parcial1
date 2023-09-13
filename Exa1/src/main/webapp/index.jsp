<%@page import="com.emergentes.Productos"%>
<%@page import="java.util.ArrayList"%>
<%
    if(session.getAttribute("listapro")==null){
        ArrayList<Productos> lisaux = new ArrayList<Productos>();
        session.setAttribute("listapro", lisaux);
    }
    ArrayList<Productos> lista = (ArrayList<Productos>) session.getAttribute("listapro");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div style="text-align:center">
            <table border="2"> 
                <tr><br>
                <th>   <p>  PRIMER PARCIAL TEM - 742 <br>
                Nombre: Alvarez Osco Jhonny Wilder<br>
                C.I.: 4194159</p><br> </th>
            </tr>
        </table>
        </div>
        
        <h1>Gestion de Productos</h1><
        <a href ="MainServlet?op=nuevo"> Nuevo Producto</a>
        <br>
        <br>
        <table border ="1">
            <tr>
                <th>Id</th>
                <th>Descripcion</th>
                <th>Cantidad</th>
                <th>Precio</th>
                <th>Categoria</th>
                <th></th>
                <th></th>
            </tr>
            
            <tr>
            <%
                if(lista !=null){
                    for(Productos item :lista){
            %>            
            
                <td><%= item.getId()%></td>
                <td><%= item.getDescripcion()%></td>
                <td><%= item.getCantidad() %></td>
                <td><%= item.getPrecio() %></td>
                <td><%= item.getCategoria() %></td>
                <td>
                    <a href="MainServlet?op=editar&id=<%= item.getId() %>">Editar</a>
                </td>
                <td>
                    <a href="MainServlet?op=eliminar&id=<%= item.getId() %>"
                       onclick="return(confirm('Esta seguro de eliminar??')">Eliminar</a>
                </td>
            </tr>
           <%    
                    }
                }
            %> 
        </table>
    </body>
</html>
