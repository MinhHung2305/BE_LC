package fis.com.vn.model.enumerate;

public enum ApplicationOpeningLcStatus {
    INITIALIZATION(1, "INITIALIZATION", "Khởi tạo"),
    WAITING_ACCOUNTANT_SIGNATURE(2, "WAITING ACCOUNTANT SIGNATURE", "Chờ ký số kế toán trưởng"),
    WAITING_BUSINESS_SIGNATURE(3, "WAITING BUSINESS SIGNATURE", "Chờ ký số doanh nghiệp"),
    SIGNED(4, "SIGNED", "Đã ký số"),
    REFUSE_TO_SIGN(5, "REFUSE TO SIGN", "Từ chối ký số"),
    REFUSE_PROCESSING(6, "REFUSE PROCESSING", " Từ chối xử lý"),
    PROCESSED(7, "PROCESSED", "Đã xử lý"),
    WAITING_DRAFT_CONFIRMING(8, "WAITING DRAFT CONFIRMING", "Chờ xác nhận Draft"),
    REFUSE_DRAFT(9, "REFUSE DRAFT", " Từ chối Draft"),
    WAITING_SPONSOR_CONFIRMING(10, "WAITING SPONSOR CONFIRMING", " Chờ xác nhận tài trợ"),
    REFUSE_SPONSOR(11, "REFUSE SPONSOR", "Từ chối tài trợ"),
    REFUSE_QUOTE(12, "REFUSE QUOTE", "Từ chối báo giá"),
    ACCEPT_QUOTATION(13, "ACCEPT QUOTATION", "Chấp nhận báo giá"),
    CANCEL(14, "CANCEL", "Hủy");

    private final int value;
    private final String name;
    private final String description;
    private ApplicationOpeningLcStatus(int value, String name, String description) {
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

    public static ApplicationOpeningLcStatus valueOf(int value) {
        ApplicationOpeningLcStatus[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ApplicationOpeningLcStatus responseCode = var1[var3];
            if (responseCode.getValue() == value) {
                return responseCode; // code int
            }
        }
        return null;
    }

}
