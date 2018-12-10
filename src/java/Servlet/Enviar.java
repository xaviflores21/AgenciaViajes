/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import grafospeso.Grafo;
import grafospeso.Grafo.Camino;
import grafospeso.GrafoSingleton;
import grafospeso.Nodo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author xavier
 */
@MultipartConfig
@WebServlet(name = "enviar", urlPatterns = {"/admin/adminController"})
public class Enviar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/plain");
            String accion = request.getParameter("accion");
            String resp = "";
            switch (accion) {
                case "listado":
                    resp = nodos();
                    break;
                case "rutaCorta":
                    resp = rutaCorta(request);
                    break;
                case "rutaEconomica":
                    resp = rutaEconomica(request);
                    break;
                case "tiempo":
                    resp = tiempo();
                    break;
                case "costo":
                    resp = precio();
                    break;
            }
            response.getWriter().write(resp);
        } catch (Exception e) {
            System.err.println(e + "hay en error en servlet");
        }
    }

    public String nodos() throws JSONException {
        Grafo<String, String, Integer> grafo = GrafoSingleton.instanciaCosto();
        List<String> vertices = grafo.getIdsVertices();
        JSONArray array = new JSONArray();
        for (String vertice : vertices) {
            JSONObject obj = new JSONObject();
            obj.put("key", vertice);
            array.put(obj);
        }
        return (array.toString());
    }

    public String rutaCorta(HttpServletRequest request) throws JSONException {
        String origen = request.getParameter("origen");
        String destino = request.getParameter("destino");
        ArrayList<Camino> caminos = GrafoSingleton.instanciaDistancia().obtenerCaminos(origen, destino);
        ArrayList<Nodo<String, String, Integer>> caminoMenor = GrafoSingleton.instanciaDistancia().buscaMenor(caminos);
        JSONArray array = new JSONArray();
        for (Nodo<String, String, Integer> nodo : caminoMenor) {
            JSONObject obj = new JSONObject();
            String $nodo = nodo.getId();
            obj.put("key", $nodo);
            array.put(obj);
        }
        return (array.toString());
    }

    public String rutaEconomica(HttpServletRequest request) throws JSONException {
        String origen = request.getParameter("origen");
        String destino = request.getParameter("destino");
        ArrayList<Camino> caminos = GrafoSingleton.instanciaCosto().obtenerCaminos(origen, destino);
        ArrayList<Nodo<String, String, Integer>> caminoMenor = GrafoSingleton.instanciaCosto().buscaMenor(caminos);
        JSONArray array = new JSONArray();
        for (Nodo<String, String, Integer> nodo : caminoMenor) {
            JSONObject obj = new JSONObject();
            String $nodo = nodo.getId();
            obj.put("key", $nodo);
            array.put(obj);
        }
        return (array.toString());
    }

    public String tiempo() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("key", GrafoSingleton.instanciaDistancia().getMenor());
        return obj.toString();
    }
    
    public String precio() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("key", GrafoSingleton.instanciaCosto().getMenor());
        return obj.toString();
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
