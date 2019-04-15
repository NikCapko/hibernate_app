package code.pages;

import code.Main;
import code.Student;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

public class GetStudentHandler implements HttpHandler {
  @Override
  public void handle(HttpExchange httpExchange) {
      String s =
          new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()))
              .lines()
              .collect(Collectors.joining(""));
      Map<String, String> params = Main.queryToMap(s);
      Student student = Main.getStudent(params.get("id"));
      Main.writeResponse(httpExchange, student.toString());
  }
}
