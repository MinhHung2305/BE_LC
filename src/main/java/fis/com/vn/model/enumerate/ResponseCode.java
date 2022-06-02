package fis.com.vn.model.enumerate;

import org.springframework.lang.Nullable;

public enum ResponseCode {

    BAD_REQUEST(9000000, ResponseCode.Series.CLIENT_ERROR),
    CAN_NOT_DELETE(900000001,ResponseCode.Series.CLIENT_ERROR),
    ACCOUNT_IS_NOT_CORRECT(900000002,ResponseCode.Series.CLIENT_ERROR),
    CAPCHA_IS_NOT_CORRECT(900000003,ResponseCode.Series.CLIENT_ERROR),
    ACCOUNT_IS_LOCKED(900000004,ResponseCode.Series.CLIENT_ERROR),
    ACCOUNT_EMAIL_IS_NOT_CORRECT(900000005,ResponseCode.Series.CLIENT_ERROR),
    NEWPASSWORD_IS_SAME_CURRENTPASSWORD(900000006,ResponseCode.Series.CLIENT_ERROR),
    NEWPASSWORD_IS_NOT_CORRECT(900000007,ResponseCode.Series.CLIENT_ERROR),
    ERR_CHANGEPASS(900000008,ResponseCode.Series.CLIENT_ERROR),
    PASSWORD_INVALID(900000009,ResponseCode.Series.CLIENT_ERROR),

    // 1010001
    PARM_CANT_EDIT(100010001,ResponseCode.Series.CLIENT_ERROR),
    EXISTING_YEAR(100010002,ResponseCode.Series.CLIENT_ERROR),
    DATE_IS_NOT_CORRECT(100010003,ResponseCode.Series.CLIENT_ERROR),
    RECORD_CAN_NOT_IMPACT(100010004,ResponseCode.Series.CLIENT_ERROR),
    USER_STATUS_INVALID(100010005,ResponseCode.Series.CLIENT_ERROR),
    STATUS_ERROR(100010006,ResponseCode.Series.CLIENT_ERROR),
    DAILY_JOB_MANUAL_NOT_ALLOWED(100010007,ResponseCode.Series.CLIENT_ERROR),
    IMPACT_PAST_JOB_NOT_ALLOWED(100010008,ResponseCode.Series.CLIENT_ERROR),
    BANK_RATING_RULE_NOT_EXISTED(100010009,ResponseCode.Series.CLIENT_ERROR),
    SETTING_FROM_TO_NOT_ALLOWED(100010010,ResponseCode.Series.CLIENT_ERROR),
    CAN_NOT_DUPLICATE(100020001,ResponseCode.Series.CLIENT_ERROR),
    CAN_NOT_BLANK(100020002,ResponseCode.Series.CLIENT_ERROR),
    RECORD_CAN_NOT_DELETE(100020003,ResponseCode.Series.CLIENT_ERROR),
    FEE_RULE_BASED_ON_BANK_RATING_NOT_EXISTED(100030001,ResponseCode.Series.CLIENT_ERROR),
    FULL_PROGRESSIVE_FEE_BASED_ON_LC_VALUE_NOT_EXISTED(100030002,ResponseCode.Series.CLIENT_ERROR),
    EFFECTIVE_DATE_OVERLAP_EXPIRATION_DATE(100030003,ResponseCode.Series.CLIENT_ERROR),
    TRANSACTION_FEE_RULE_NOT_EXISTED(100030004,ResponseCode.Series.CLIENT_ERROR),
    PACKAGE_FEE_RULE_NOT_EXISTED(100030005,ResponseCode.Series.CLIENT_ERROR),
    ERR_INVALID(100010011,ResponseCode.Series.CLIENT_ERROR),
    CREATE_SUCCESS(900100000, Series.SUCCESSFUL),
    UPDATE_SUCCESS(900100001, Series.SUCCESSFUL),
    DELETE_SUCCESS(900100002, Series.SUCCESSFUL),
    CANCLE_SUCCESS(900100003, Series.SUCCESSFUL),
    SIGN_DIGITAL_ACCOUTANT_SUCCESS(300070000, Series.SUCCESSFUL),
    SIGN_DIGITAL_LEGAL_REPRESENTATIVE_SUCCESS(300070001, Series.SUCCESSFUL),
    REFUSE_SIGN_DIGITAL_SUCCESS(300070002, Series.SUCCESSFUL),

    // 2010001
    MANIPULATE_ADMIN_BANK_USER_NOT_ALLOWED(200010001,ResponseCode.Series.CLIENT_ERROR),

    // TODO should change
    USER_DOES_NOT_EXIST(4040007, ResponseCode.Series.CLIENT_ERROR),
    CANNOT_EMPTY( 10001, ResponseCode.Series.CLIENT_ERROR),
    FEE_RULE_CODE_DUPLICATE(4000008, ResponseCode.Series.CLIENT_ERROR),
    GROUP_IN_USED( 4000009, ResponseCode.Series.CLIENT_ERROR),
    CANNOT_UPDATE_LC(100011, ResponseCode.Series.CLIENT_ERROR),
    CANNOT_UPDATE_LC_FOR_LC_UPAC(100012, ResponseCode.Series.CLIENT_ERROR),
    CONFLICT(4090000, ResponseCode.Series.CLIENT_ERROR),
    EMAIL_EXISTED(4090006, ResponseCode.Series.CLIENT_ERROR),
    INVALID_TOKEN(4010002, ResponseCode.Series.CLIENT_ERROR),
    USER_NAME_EXISTED(4090002, ResponseCode.Series.CLIENT_ERROR),
    NOT_FOUND(4040000, ResponseCode.Series.CLIENT_ERROR);


    private final int value;
    private final ResponseCode.Series series;

    public static ResponseCode valueOf(int value) {
        ResponseCode[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ResponseCode responseCode = var1[var3];
            if (responseCode.getValue() == value) {
                return responseCode;
            }
        }

        return null;
    }

    public static boolean equal(Integer code, ResponseCode responseCode) {
        if (code != null && responseCode != null) {
            ResponseCode responseCode1 = valueOf(code);
            return responseCode1 == responseCode;
        } else {
            return true;
        }
    }

    private ResponseCode(int value, ResponseCode.Series series) {
        this.value = value;
        this.series = series;
    }

    public int getValue() {
        return this.value;
    }

    public ResponseCode.Series getSeries() {
        return this.series;
    }

    public static enum Series {
        SUCCESSFUL(0),
        INFORMATIONAL(1),
        REDIRECTION(2),
        CLIENT_ERROR(3),
        SERVER_ERROR(4),
        CORE_BANK_ERROR(5),
        SMART_BANK_ERROR(6);

        private final int value;

        private Series(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }

        @Nullable
        public static ResponseCode.Series resolve(int statusCode) {
            int seriesCode = statusCode / 100;
            ResponseCode.Series[] var2 = values();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                ResponseCode.Series series = var2[var4];
                if (series.value == seriesCode) {
                    return series;
                }
            }

            return null;
        }
    }
}