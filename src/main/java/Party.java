public enum Party {
    REPUBLICAN, DEMOCRAT, GREEN, CONSTITUTION, LIBERTARIAN;

    public int getColorCode() {
        switch (this) {
            case REPUBLICAN -> {
                return 0xff0000;
            }
            case DEMOCRAT -> {
                return 0x0000ff;
            }
            case GREEN -> {
                return 0x008000;
            }
            case CONSTITUTION -> {
                return 0xffffff;
            }
            case LIBERTARIAN -> {
                return 0xffd700;
            }
            default -> {
                return 0x000000;
            }
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case REPUBLICAN -> {
                return "Republican Party";
            }
            case DEMOCRAT -> {
                return "Democratic Party";
            }
            case GREEN -> {
                return "Green Party";
            }
            case CONSTITUTION -> {
                return "Constitution Party";
            }
            case LIBERTARIAN -> {
                return "Libertarian Party";
            }
            default -> {
                return "Unknown Party";
            }
        }
    }
}
