
package com.emergentes.controlador;

import com.emergentes.Productos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       String op = request.getParameter("op");
       Productos objeto = new Productos();
       int id, pos;
        HttpSession ses =request.getSession();
        ArrayList<Productos> lista = (ArrayList<Productos>)ses.getAttribute("listapro");
        switch(op){
            case "nuevo":
                //Enviar un objeto vacio a editar
                request.setAttribute("Objpro", objeto);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "editar":
                // Enviar un objeto a editar pero con contenido
                id= Integer.parseInt(request.getParameter("id"));
                //averiguar la posicion del elemnto en la lista
                pos = buscarPorIndice(request,id);
                //obtener el objeto
                objeto = lista.get(pos);
                request.setAttribute("Objpro", objeto);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "eliminar":
                // Eliminar un registro de la coleccion segun id
                id= Integer.parseInt(request.getParameter("id"));
                //averiguar la posicion del elemnto en la lista
                pos = buscarPorIndice(request,id);
                if(pos >=0)
                {
                    lista.remove(pos);
                }
                request.setAttribute("listapro", lista);
                response.sendRedirect("index.jsp");
                break;
            default:
        } 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    int cantidad =Integer.parseInt(request.getParameter("cantidad"));
    double precio=Double.parseDouble(request.getParameter("precio"));
        HttpSession ses = request.getSession();
        ArrayList<Productos> lista = (ArrayList<Productos>) ses.getAttribute("listapro");
        Productos objeto = new Productos();
        objeto.setId(id);
        objeto.setDescripcion(request.getParameter("descripcion"));
        objeto.setCantidad(cantidad);
        objeto.setPrecio(precio);
        objeto.setCategoria(request.getParameter("categoria"));
        
        if(id==0){
            // nuevo registro
            int idNuevo =obtenerId(request);
            objeto.setId(idNuevo);
            lista.add(objeto);
        }
        else{
            // edicion de registro
            int pos =buscarPorIndice(request,id);
            lista.set(pos, objeto);
        }    
        request.setAttribute("listapro", lista);
        response.sendRedirect("index.jsp");
    }

    public int buscarPorIndice(HttpServletRequest request,int id)
    {
        HttpSession ses = request.getSession();
        ArrayList<Productos> lista = (ArrayList<Productos>)ses.getAttribute("listapro");
        int pos = -1;
        if(lista !=null){
            for(Productos ele : lista){
                ++pos;
                if(ele.getId() == id)
                {
                    break;
                }
            }
        }
        return pos;
    }
    
    public int obtenerId(HttpServletRequest request){
        HttpSession ses = request.getSession();
        ArrayList<Productos> lista =(ArrayList<Productos>)ses.getAttribute("listapro");
        //Buscar el ultimo id
        int idn =0;
        for(Productos ele: lista)
        {
            idn = ele.getId();
        }
        return idn+1;
    }  
    
}
