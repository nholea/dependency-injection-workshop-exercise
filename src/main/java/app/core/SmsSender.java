package app.core;

public interface SmsSender {
    void send(String telephone, String content);
}
