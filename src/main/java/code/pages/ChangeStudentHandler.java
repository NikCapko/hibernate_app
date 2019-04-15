package code.pages;

import code.Main;
import code.Student;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

public class ChangeStudentHandler implements HttpHandler {
  @Override
  public void handle(HttpExchange httpExchange) {
    if (httpExchange.getRequestMethod().equalsIgnoreCase("POST")) {
      String s =
          new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()))
              .lines()
              .collect(Collectors.joining(""));
      Map<String, String> params = Main.queryToMap(s);
      Student student =
          new Student(params.get("firstName"), params.get("lastName"), params.get("yearBirth"));
      student.setId(Integer.parseInt(params.get("id")));
      Main.updateStudent(student);
      Main.writeResponse(httpExchange, String.format("{\"result\":\"success\"}"));
    }
  }
}
