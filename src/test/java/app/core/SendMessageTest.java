package app.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SendMessageTest {

  @Mock
  SmsSender smsSender;

  @Mock
  DateProvider dateProvider;
  @InjectMocks
  SendMessage sendMessage;
  @Captor
  ArgumentCaptor<String> messageContent;

  @Test
  public void itPrependsAWinterEmojiInWinter() {
  String winterEmoji = "\u2744\uFE0F";
  when(dateProvider.getMonthNumber()).thenReturn(3);
  when(dateProvider.getDayOfMonthNumber()).thenReturn(1);
  Message message = generateMessage("is Winter!");

  sendMessage.execute(message);

  verify(smsSender).send(any(), messageContent.capture());
  assertThat(messageContent.getValue()).contains(winterEmoji);
  }

  @Test
  public void itPrependsASpringEmojiInSpring() {
  String springEmoji = "\uD83C\uDF37";
  when(dateProvider.getMonthNumber()).thenReturn(5);
  when(dateProvider.getDayOfMonthNumber()).thenReturn(30);
  Message message = generateMessage("is Spring!");

  sendMessage.execute(message);

  verify(smsSender).send(any(), messageContent.capture());
  assertThat(messageContent.getValue()).contains(springEmoji);
  }

  @Test
  public void itPrependsASummerEmojiInSummer() {
  String summerEmoji = "\uD83C\uDF1E";
  when(dateProvider.getMonthNumber()).thenReturn(9);
  when(dateProvider.getDayOfMonthNumber()).thenReturn(21);
  Message message = generateMessage("is Summer!");

  sendMessage.execute(message);

  verify(smsSender).send(any(), messageContent.capture());
  assertThat(messageContent.getValue()).isEqualTo(summerEmoji);
  }

  @Test
  public void itPrependsAnAutumnEmojiInAutumn() {
  String autumnEmoji = "\uD83C\uDF42";
  when(dateProvider.getMonthNumber()).thenReturn(11);
  when(dateProvider.getDayOfMonthNumber()).thenReturn(28);
  Message message = generateMessage("is Autumn!");

  sendMessage.execute(message);

  verify(smsSender).send(any(), messageContent.capture());
  assertThat(messageContent.getValue()).isEqualTo(autumnEmoji);
  }

  private Message generateMessage(String content) {
    Contact contact = new Contact("", "", "1111111", "");
    return new Message(content, contact);
  }
}
