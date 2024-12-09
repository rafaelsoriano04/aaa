package View;

import Controller.ApiRest;
import Model.Estudiante;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "EstudianteServlet", urlPatterns = {"/EstudianteServlet"})
public class EstudianteServlet extends HttpServlet {

    private final ApiRest apiClient = new ApiRest();

    private Estudiante parseStudent(String jsonResponse) {
        try {
            JSONArray jsona = new JSONArray(jsonResponse);
            if (jsona.length() > 0) {
                JSONObject jsonStudent = jsona.getJSONObject(0);
                String cedula = jsonStudent.getString("cedula");
                String nombre = jsonStudent.getString("nombre");
                String apellido = jsonStudent.getString("apellido");
                String direccion = jsonStudent.getString("direccion");
                String telefono = jsonStudent.getString("telefono");
                return new Estudiante(cedula, nombre, apellido, direccion, telefono);
            } else {
                System.out.println("Not Found");

            }
        } catch (Exception e) {
            System.out.println("Error");
        }
        return null;
    }

    private List<Estudiante> parseListStudent(String jsonResponse) {
        List<Estudiante> estudiantes = new ArrayList<>();
        try {
            JSONArray listaEstudiantes = new JSONArray(jsonResponse);
            for (int i = 0; i < listaEstudiantes.length(); i++) {
                JSONObject jsonStudent = listaEstudiantes.getJSONObject(i);
                String cedula = jsonStudent.getString("cedula");
                String nombre = jsonStudent.getString("nombre");
                String apellido = jsonStudent.getString("apellido");
                String direccion = jsonStudent.getString("direccion");
                String telefono = jsonStudent.getString("telefono");
                estudiantes.add(new Estudiante(cedula, nombre, apellido, direccion, telefono));
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
        return estudiantes;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String filter = request.getParameter("filter");
        if ("edit".equals(action)) {
            String cedula = request.getParameter("cedula");
            String jsonResponse = apiClient.getStudentByCedula(cedula);
            Estudiante estudiante = this.parseStudent(jsonResponse);
            if (estudiante == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Estudiante no encontrado");
                return;
            }
            request.setAttribute("estudiante", estudiante);
            request.getRequestDispatcher("/WEB-INF/editarEstudiante.jsp").forward(request, response);

        } else if ("add".equals(action)) {
            request.getRequestDispatcher("/WEB-INF/agregarEstudiante.jsp").forward(request, response);
        } else if (filter != null) {
            String jsonResponse = apiClient.getStudentByCedula(filter);
            List<Estudiante> students = this.parseListStudent(jsonResponse);
            request.setAttribute("students", students);
            request.getRequestDispatcher("/WEB-INF/estudiantes.jsp").forward(request, response);
        } else {
            String jsonResponse = apiClient.getStudents();
            List<Estudiante> students = this.parseListStudent(jsonResponse);
            request.setAttribute("students", students);
            request.getRequestDispatcher("/WEB-INF/estudiantes.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("_method");
        if ("put".equals(method)) {
            doPut(request, response);
        }

        if ("delete".equals(method)) {
            doDelete(request, response);
        } else {
            String cedula = request.getParameter("cedula");
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String telefono = request.getParameter("telefono");
            String direccion = request.getParameter("direccion");
            String result = apiClient.saveStudent(cedula, nombre, apellido, direccion, telefono);
            response.sendRedirect("/WebApplication1/EstudianteServlet");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");

        String result = apiClient.updateStudent(cedula, nombre, apellido, direccion, telefono);
        response.getWriter().write(result);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cedula = request.getParameter("cedula");
        String result = apiClient.deleteStudent(cedula);
        response.sendRedirect("/WebApplication1/EstudianteServlet");
    }

}
