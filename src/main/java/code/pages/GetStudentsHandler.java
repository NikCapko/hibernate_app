package code.pages;

import code.Main;
import code.Student;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.util.List;

public class GetStudentsHandler implements HttpHandler {
  @Override
  public void handle(HttpExchange t) {
    if (t.getRequestMethod().equalsIgnoreCase("GET")) {
      List<Student> list = Main.getStudentList();
      String resp = getJsonArray(list);
      Main.writeResponse(t, resp);
    } else {
      StringBuilder resp = new StringBuilder();
      Main.writeResponse(t, String.format("{\"data\":%s}", resp.toString()));
    }
  }

  public static <T> String getJsonArray(List<T> list) {
    StringBuilder result = new StringBuilder();
    for (Object o : list) {
      result.append(o.toString()).append(",");
    }
    return String.format("[%s]", result.toString().substring(0, result.length() - 1));
  }
}
