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
    when(dateProvider.getMonthNumber()).thenReturn(3);
    when(dateProvider.getDayOfMonthNumber()).thenReturn(1);
        Message message = generateMessage("is Winter!");

        sendMessage.execute(message);

    verify(smsSender).send(any(), messageContent.capture());
    assertThat(messageContent.getValue()).contains("\u2744\uFE0F");
    }

    @Test
    public void itPrependsASpringEmojiInSpring() {
    when(dateProvider.getMonthNumber()).thenReturn(5);
    when(dateProvider.getDayOfMonthNumber()).thenReturn(30);
    Message message = generateMessage("is Spring!");

    sendMessage.execute(message);

    verify(smsSender).send(any(), messageContent.capture());
    assertThat(messageContent.getValue()).contains("\uD83C\uDF37");
    }

    @Test
    public void itPrependsASummerEmojiInSummer() {
    when(dateProvider.getMonthNumber()).thenReturn(9);
    when(dateProvider.getDayOfMonthNumber()).thenReturn(21);
    Message message = generateMessage("is Summer!");

    sendMessage.execute(message);

    verify(smsSender).send(any(), messageContent.capture());
    assertThat(messageContent.getValue()).isEqualTo("\uD83C\uDF1E");
    }

    @Test
    public void itPrependsAnAutumnEmojiInAutumn() {
    when(dateProvider.getMonthNumber()).thenReturn(11);
    when(dateProvider.getDayOfMonthNumber()).thenReturn(28);
    Message message = generateMessage("is Autumn!");

    sendMessage.execute(message);

    verify(smsSender).send(any(), messageContent.capture());
    assertThat(messageContent.getValue()).isEqualTo("\uD83C\uDF42");
    }

    private Message generateMessage(String content) {
        Contact contact = new Contact("","","1111111","");
        return new Message(content, contact);
    }
}
