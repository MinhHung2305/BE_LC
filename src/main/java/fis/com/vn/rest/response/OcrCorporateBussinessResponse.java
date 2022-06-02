package fis.com.vn.rest.response;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OcrCorporateBussinessResponse implements Serializable {

    private String maSoDoanhNghiep;
    private String loaiHinhDoanhNghiep;
    private String ngayDangKy;
    private String lanDangKy;
    private String ngayThayDoi;
    private String tenDoanhNghiep;
    private String tenDoanhNghiepEn;
    private String tenDoanhNghiepVietTat;
    private String diaChi;
    private String dienThoai;
    private String email;
    private String fax;
    private String website;
    private String vonDieuLe;
    private String menhGiaCoPhan;
    private String tongSoCoPhan;
    private nguoiDaiDienPhapLuat[] nguoiDaiDienPhapLuat;
    private String noiCapDkkd;
    private chiTietDiaChi chiTietDiaChi;
    private thanhVienGopVon thanhVienGopVon;
    private thongTinBang thongTinBang;

    @Data
    @ToString
    public static class thanhVienGopVon {
        private String[] Tables;
    }

    @Data
    @ToString
    public static class thongTinBang {
        private String[] Tables;
    }

    @Data
    @ToString
    public static class nguoiDaiDienPhapLuat {
        private String hoVaTen;
        private String gioiTinh;
        private String chucDanh;
        private String ngaySinh;
        private String quocTich;
        private String loaiGiayTo;
        private String soGiayTo;
        private String ngayCap;
        private String noiCap;
        private chiTietNoiDkHktt chiTietNoiDkHktt;
        private String noiDkHktt;
        private chiTietDiaChiTt chiTietDiaChiTt;
        private String diaChiTt;
        private String danToc;

    }

    @Data
    @ToString
    public static class chiTietNoiDkHktt{
        private String country;
        private String province;
        private String street;
        private String provinceCode;
        private String district;
        private String ward;
    }

    @Data
    @ToString
    public static class chiTietDiaChiTt{
        private String country;
        private String province;
        private String street;
        private String provinceCode;
        private String district;
        private String ward;
    }

    @Data
    @ToString
    public static class chiTietDiaChi{
        private String country;
        private String province;
        private String street;
        private String provinceCode;
        private String district;
        private String ward;
    }
}