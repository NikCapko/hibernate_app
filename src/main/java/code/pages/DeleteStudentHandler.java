package code.pages;

import code.Main;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

public class DeleteStudentHandler implements HttpHandler {

  @Override
  public void handle(HttpExchange httpExchange) {
    if (httpExchange.getRequestMethod().equalsIgnoreCase("POST")) {
      String s =
          new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()))
              .lines()
              .collect(Collectors.joining(""));
      Map<String, String> params = Main.queryToMap(s);
      Main.deleteStudent(params.get("id"));
      Main.writeResponse(httpExchange, String.format("{\"result\":\"success\"}"));
    }
  }
}
