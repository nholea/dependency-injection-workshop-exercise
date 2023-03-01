package app.core;

import java.io.IOException;

public interface UserInterface {
  void display(String content);

  void printWhiteSpace();

  String read() throws IOException;

}
