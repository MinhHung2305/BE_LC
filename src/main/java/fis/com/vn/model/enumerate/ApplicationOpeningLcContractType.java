package fis.com.vn.model.enumerate;

public enum ApplicationOpeningLcContractType {
    SIGN_IN_FPT_LC_PLATFORM(1, "SIGN IN FPT LC PLATFORM", "Ký hợp đồng điện tử trên FPT L/C Platform"),
    DO_NOT_SIGN_IN_FPT_LC_PLATFORM(2, "DO NOT SIGN IN FPT LC PLATFORM", "Không ký hợp đồng điện tử trên FPT L/C Platform");

    private final int value;
    private final String name;
    private final String description;
    private ApplicationOpeningLcContractType(int value, String name, String description) {
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

    public static ApplicationOpeningLcContractType valueOf(int value) {
        ApplicationOpeningLcContractType[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ApplicationOpeningLcContractType responseCode = var1[var3];
            if (responseCode.getValue() == value) {
                return responseCode; // code int
            }
        }
        return null;
    }
}
