
package control;

import hibernate.Inmueble;
import java.awt.image.BufferedImage;
import java.io.File;
import modelo.ModeloInmueble;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "Control", urlPatterns = {"/Control"})
@MultipartConfig
public class Control extends HttpServlet {
        boolean error=false;

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        String destino = "index.html";
        boolean forward = false;
        String target,op,action,view;
        
        //...
        target = request.getParameter("target");
        op = request.getParameter("op");
        action = request.getParameter("action");
        //view = request.getParameter("view");
        
        
        //abrimos y mostramos los datos en view
        if(target.equals("prueba") && op.equals("select") && action.equals("view") /*|| view == null*/){
            forward = true;
            destino = "WEB-INF/info/view.jsp";
            request.setAttribute("datos", ModeloInmueble.get());
            
        //borrar
        }else if(target.equals("prueba") && op.equals("delete") && action.equals("op")){
            forward = false;
            ModeloInmueble.delete(request.getParameter("id"));
            destino = "Control?target=prueba&op=select&action=view";
            
        //abrimos el insert
        }else if(target.equals("prueba") && op.equals("target") && action.equals("view")){
            forward = true;
            destino = "WEB-INF/info/insert.jsp";
            
        //insertamos
        }else if(target.equals("prueba") && op.equals("insert") && action.equals("op")){
            forward = false;
            
            Inmueble inm = getInmueble(request);
            error = false;
            
            Part filePart = request.getPart("archivo"); // <input type="file" name="file">
            String fileName = getFileName(filePart);
            InputStream fileContent = filePart.getInputStream();
            String d = getServletContext().getRealPath("/") + "img/";
            guardarImagen(fileContent, fileName, d);
            response.setContentType("application/json");
            
            inm.setImagenes(fileName);
            ModeloInmueble.insert(inm);
            destino = "Control?target=prueba&op=select&action=view";
           
        //abrimos update, y mostrmos los datos
        }else if(target.equals("prueba") && op.equals("update") && action.equals("view")){
            forward = true;
            request.setAttribute("inmueble", ModeloInmueble.getParametro(request.getParameter("id")));
            destino = "WEB-INF/info/update.jsp";
            
        //update
        }else if(target.equals("prueba") && op.equals("update") && action.equals("op")){
            forward = false;
                        Inmueble inm = getInmueble(request);
            error = false;
            
            Part filePart = request.getPart("archivo"); // <input type="file" name="file">
            String fileName = getFileName(filePart);
            InputStream fileContent = filePart.getInputStream();
            String d = getServletContext().getRealPath("/") + "img/";
            guardarImagen(fileContent, fileName, d);
            response.setContentType("application/json");
            
            inm.setImagenes(fileName);
            inm.setId(Integer.parseInt(request.getParameter("id")));
            ModeloInmueble.update(inm);
            destino = "Control?target=prueba&op=select&action=view";
            
        //mostrmos el detalle
        }else if(target.equals("prueba") && op.equals("ver") && action.equals("view")){
            forward = true;
            request.setAttribute("inmueble", ModeloInmueble.getParametro(request.getParameter("id")));
            destino = "WEB-INF/info/detalle.jsp";
            
        }

        
        //transferencia de control
        if(forward){
            request.getRequestDispatcher(destino).forward(request, response);
        }else{
            response.sendRedirect(destino);
        }
    }
    
    private void guardarImagen(InputStream fileContent, String fileName, String destino){
        try {
            //destino = "C:\Users\Mariangeles\Desktop\Practica5AAD\build\web\WEB-INF/img/aa.png";
            BufferedImage img = ImageIO.read(fileContent);
            if(fileName.endsWith("png"))
                ImageIO.write(img, "png", new File(destino + fileName));
            else if (fileName.endsWith("jpg"))
                ImageIO.write(img, "jpg", new File (destino + fileName));
        } catch (Exception ex) {
            error = true;
        }
    }

    private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
    
    private Inmueble getInmueble(HttpServletRequest request){
        Inmueble inm = new Inmueble();
        inm.setUsuario(request.getParameter("usuario"));
        inm.setLocalidad(request.getParameter("localidad"));
        inm.setCalle(request.getParameter("calle"));
        inm.setNumero(request.getParameter("numero"));
        inm.setTipo(request.getParameter("tipo"));
        inm.setPrecio(request.getParameter("precio"));
        return inm;
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
