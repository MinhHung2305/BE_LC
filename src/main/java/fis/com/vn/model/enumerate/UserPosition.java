package fis.com.vn.model.enumerate;

public enum UserPosition {
    LEGAL_REPRESENTATIVE(0,"legal_representative","Người đại diện pháp luật"),
    ACCOUNTANT(1,"accountant","Kế toán trưởng");

    private final int value;
    private final String name;
    private final String description;

    private UserPosition(int value, String name, String description) {
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

    public static fis.com.vn.model.enumerate.UserPosition valueOf(int value) {
        fis.com.vn.model.enumerate.UserPosition[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            fis.com.vn.model.enumerate.UserPosition responseCode = var1[var3];
            if (responseCode.getValue() == value) {
                return responseCode; // code int
            }
        }
        return null;
    }

    public static fis.com.vn.model.enumerate.UserPosition nameOf(String name) {
        fis.com.vn.model.enumerate.UserPosition[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            fis.com.vn.model.enumerate.UserPosition responseCode = var1[var3];
            if (responseCode.getName().equals(name)) {
                return responseCode; // code int
            }
        }
        return null;
    }

}
