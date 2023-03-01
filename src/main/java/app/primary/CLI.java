package app.primary;

import app.core.UserInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLI implements UserInterface {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void display(String content) {
        System.out.println(content);
    }

    public void printWhiteSpace() {
        display("\n");
    }

    public String read() throws IOException {
        return reader.readLine();
    }
}
