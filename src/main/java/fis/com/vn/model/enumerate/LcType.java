package fis.com.vn.model.enumerate;

public enum LcType {
    LC(1, "L/C thường", "L/C thường"),
    UPASLC(2, "UPAS L/C", " UPAS L/C");

    private final int value;
    private final String name;
    private final String description;
    private LcType(int value, String name, String description) {
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

    public static LcType valueOf(int value) {
        LcType[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            LcType responseCode = var1[var3];
            if (responseCode.getValue() == value) {
                return responseCode; // code int
            }
        }
        return null;
    }
}
