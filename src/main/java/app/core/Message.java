package app.core;

public record Message(String content, Contact destination) {

  String getTelephoneNumber(){
    return destination.telephone();
  }
}
