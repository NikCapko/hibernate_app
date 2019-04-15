package code;

import code.pages.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

  private static StudentService studentService = new code.StudentService();

  public static void main(String[] args) {

    // StudentService studentService = new StudentService();
    // Student student = new code.Student("Alex", "Smit", "1994");
    // studentService.saveStudent(student);

    try {
      HttpServer server = HttpServer.create(new InetSocketAddress(8083), 5);
      server.createContext("/convert", new ConvertHandler());
      server.createContext("/add_student", new AddStudentHandler());
      server.createContext("/get_students_list", new GetStudentsListHandler());
      server.createContext("/delete_student", new DeleteStudentHandler());
      server.createContext("/get_student", new GetStudentHandler());
      server.createContext("/change_student", new ChangeStudentHandler());
      server.setExecutor(null);
      server.start();
      System.out.println("Server start...");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void writeResponse(HttpExchange t, String str) {
    byte[] resp = str.getBytes();
    try {
      t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
      t.sendResponseHeaders(200, resp.length);
      OutputStream out = t.getResponseBody();
      out.write(resp);
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Map<String, String> queryToMap(String query) {
    Map<String, String> params = new HashMap<>();
    if (query != null && query.contains("=")) {
      String[] paramsArray = query.split("&");
      for (String par : paramsArray) {
        String[] p = par.split("=");
        if (p.length == 2) {
          params.put(p[0], p[1]);
        }
      }
    }
    return params;
  }

  public static void addStudent(Student student) {
    studentService.saveStudent(student);
  }

  public static void updateStudent(Student student) {
    studentService.updateStudent(student);
  }

  public static void deleteStudent(String id) {
    Student student = studentService.findStudent(Integer.parseInt(id));
    studentService.deleteStudent(student);
  }

  public static Student getStudent(String id) {
    return studentService.findStudent(Integer.parseInt(id));
  }

  public static List<Student> getStudentList() {
    return studentService.findAllStudent();
  }
}
