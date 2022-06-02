package fis.com.vn.model.enumerate;

public enum UserInitiationChannel {
    FPT(1, "FPT", "FPT"),
    CORPORATE(2, "Corporate", "Doanh nghiệp");

    private final int value;
    private final String name;
    private final String description;

    private UserInitiationChannel(int value, String name, String description) {
        this.value = value;
        this.name = name;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static UserInitiationChannel valueOf(int value) {
        UserInitiationChannel[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            UserInitiationChannel responseCode = var1[var3];
            if (responseCode.getValue() == value) {
                return responseCode;
            }
        }
        return null;
    }


}
