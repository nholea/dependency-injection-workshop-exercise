package app.core;

public class SendMessage {
    private final SmsSender smsSender;

    private final DateProvider dateProvider;

    public SendMessage(SmsSender smsSender, DateProvider dateProvider) {
        this.smsSender = smsSender;
        this.dateProvider = dateProvider;
    }

    public void execute(Message message) {
        String content = getSeasonEmoji() + message.content();

        smsSender.send(message.getTelephoneNumber(), content);
    }

    private String getSeasonEmoji() {
        int month = dateProvider.getMonthNumber();
        int day = dateProvider.getDayOfMonthNumber();

        String winter = "\u2744\uFE0F";
        String spring = "\uD83C\uDF37";
        String summer = "\uD83C\uDF1E";
        String autumn = "\uD83C\uDF42";

        return switch (month) {
            case 1, 2 -> winter;
            case 3 -> day < 20 ? winter : spring;
            case 4, 5 -> spring;
            case 6 -> day < 21 ? spring : summer;
            case 7, 8 -> summer;
            case 9 -> day < 22 ? summer : autumn;
            case 10, 11 -> autumn;
            case 12 -> day < 21 ? autumn : winter;
            default -> "";
        };
    }
}
