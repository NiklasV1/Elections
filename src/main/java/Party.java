public enum Party {
    REPUBLICAN, DEMOCRAT, GREEN, CONSTITUTION, LIBERTARIAN;

    public int getColorCode() {
        return switch (this) {
            case REPUBLICAN -> 0xff0000;
            case DEMOCRAT -> 0x0000ff;
            case GREEN -> 0x008000;
            case CONSTITUTION -> 0xffffff;
            case LIBERTARIAN -> 0xffd700;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case REPUBLICAN -> "Republican Party";
            case DEMOCRAT -> "Democratic Party";
            case GREEN -> "Green Party";
            case CONSTITUTION -> "Constitution Party";
            case LIBERTARIAN -> "Libertarian Party";
        };
    }
}
