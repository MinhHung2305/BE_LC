package fis.com.vn.model.enumerate;

public enum RolesInfo {
    MAKER(2, "Maker", "Maker"),
    APPROVER(1, "Approver", "Approver"),
    SYSTEM(0, "System", "Quản trị hệ thống");

    private final int value;
    private final String name;
    private final String description;

    private RolesInfo(int value, String name, String description) {
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

    public static RolesInfo valueOf(int value) {
        RolesInfo[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            RolesInfo responseCode = var1[var3];
            if (responseCode.getValue() == value) {
                return responseCode;
            }
        }
        return null;
    }


}
