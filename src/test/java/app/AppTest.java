package app;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    public void itWorks() {
        App app = new App();

        Assertions.assertThat(app).isNotNull();
    }
}