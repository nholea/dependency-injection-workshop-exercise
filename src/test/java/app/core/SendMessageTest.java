package app.core;

import java.time.LocalDate;
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
    @InjectMocks
    SendMessage sendMessage;
    @Captor
    ArgumentCaptor<String> messageContent;

    @Test
    public void itPrependsAWinterEmojiInWinter() {
    when(LocalDate.now().getMonthValue()).thenReturn(6);
    when(LocalDate.now().getDayOfMonth()).thenReturn(15);
    Contact contact = new Contact("","","1111111","");
    Message message = new Message("is Spring!", contact);

    sendMessage.execute(message);

    verify(smsSender).send(any(), messageContent.capture());
    String expectedMessage = "\uD83C\uDF37is Spring!";
    assertThat(messageContent.getValue()).isEqualTo(expectedMessage);
    }

    @Test
    public void itPrependsASpringEmojiInSpring() {
        // TODO()
    }

    @Test
    public void itPrependsASummerEmojiInSummer() {
        // TODO()
    }

    @Test
    public void itPrependsAnAutumnEmojiInAutumn() {
        // TODO()
    }
}
