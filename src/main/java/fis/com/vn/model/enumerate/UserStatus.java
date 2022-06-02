package fis.com.vn.model.enumerate;

public enum UserStatus {
    ACTIVE(1, "ACTIVE", "Kích hoạt"),
    INACTIVE(0, "INACTIVE", "Vô hiệu hóa"),
    LOCK(2, "LOCK", "Khóa"),
    UNLOCK(3, "UNLOCK", "Mở khóa"),
    DISCONNECT(4, "DISCONNECT", "Ngắt kết nối");
 
    private final int value;
    private final String name;
    private final String description;
    private UserStatus(int value, String name, String description) {
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

    public static UserStatus valueOf(int value) {
        UserStatus[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            UserStatus responseCode = var1[var3];
            if (responseCode.getValue() == value) {
                return responseCode; // code int
            }
        }
        return null;
    }
}
