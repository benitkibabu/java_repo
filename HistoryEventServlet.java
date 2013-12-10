/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 *
 * @author scr2
 */
@WebServlet(name="HistoryEventServlet", urlPatterns={"/HistoryEventServlet"})
public class HistoryEventServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // File Info
            String path = getServletContext().getRealPath("/WEB-INF/");
            String XMLFileName = path + "/history.xml";
            String XSLFileName = path + "/historyevent.xsl";

            // Get Query String Param
            String eventName = request.getParameter("name");
            
            // Create DOM object
            File docFile = new File(XMLFileName);

            Document doc = null;
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                doc = db.parse(docFile);
            }catch (Exception e) {
                System.err.println("Error Document: "+e.getMessage());
            }

            // Identify Sources

            DOMSource XMLSource = new DOMSource(doc);
            StreamSource XSLSource = new StreamSource(XSLFileName);

            // Identify Result
            StreamResult homeOutput = new StreamResult(out);

            // Create TransformerFactory
            TransformerFactory xFactory = TransformerFactory.newInstance();

            // Create Transformer
            Transformer optimusPrime = xFactory.newTransformer(XSLSource);

            // Get Navigation Info
            NodeList eventNodeList = doc.getElementsByTagName("event");
            int num_events = eventNodeList.getLength();
            String prev_name;
            String next_name;

            for (int i=0;i<num_events; i++){

                Node eventNode = eventNodeList.item(i);

                NodeList eventNodeListChildren = eventNode.getChildNodes();
                Node eventNameNode = eventNodeListChildren.item(1);
                Node nameTextNode = eventNameNode.getChildNodes().item(0);
                String eventNameValue = nameTextNode.getNodeValue();
                
                 if (eventName.equals(eventNameValue)) {

                    if (i!=0) {
                        prev_name=eventNodeList.item(i-1).getChildNodes().item(1).getChildNodes().item(0).getNodeValue();
                    } else {
                        prev_name=eventNodeList.item(num_events-1).getChildNodes().item(1).getChildNodes().item(0).getNodeValue();
                    }

                    if (i!=(num_events-1)) {
                        next_name=eventNodeList.item(i+1).getChildNodes().item(1).getChildNodes().item(0).getNodeValue();
                    } else {
                        next_name=eventNodeList.item(0).getChildNodes().item(1).getChildNodes().item(0).getNodeValue();
                    }

                    // Apply transform
                    optimusPrime.setParameter("vEventName", eventName);
                    optimusPrime.setParameter("vNextName", next_name);
                    optimusPrime.setParameter("vPrevName", prev_name);
                    optimusPrime.transform(XMLSource, homeOutput);

                 }
            }

        } catch (TransformerConfigurationException ex) {

            System.out.println("Encountered TransformerConfiguration Error: " + ex.getMessage());

        } catch (TransformerException ex) {

            System.out.println("Encountered Transformer Error: " + ex.getMessage());

        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
