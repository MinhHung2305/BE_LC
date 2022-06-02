package fis.com.vn.rest.response;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OcrIdentifyResponse implements Serializable{
    private String soCmt;
    private String hoVaTen;
    private String namSinh;
    private String queQuan;
    private String noiTru;
    private String dacDiemNhanDang;
    private String noiCap;
    private String ngayCap;
    private String loaiCmt;
    private String loaiCmtMatTruoc;
    private String quocTich;
    private String ngayHetHan;
    private String gioiTinh;
    private chiTietNoiTru chiTietNoiTru;
    private kiemTraMatTruoc kiemTraMatTruoc;
    private kiemTraMatSau kiemTraMatSau;
    private String soCmtScore;
    private String hoVaTenScore;
    private String namSinhScore;
    private String queQuanScore;
    private String noiTruScore;
    private String danTocScore;
    private String tonGiaoScore;
    private String ngayCapScore;
    private String noiCapScore;
    private String maTinhQueQuan;
    private String maTinhDiaChi;
    private String maTinhNoiCap;
    private Integer status;
    private Integer codeMesage;
    private String message;

    public OcrIdentifyResponse(Integer status, Integer codeMesage, String message)
    {
        this.status = status;
        this.codeMesage = codeMesage;
        this.message = message;
    }


    @Data
    @ToString
    private class chiTietNoiTru {
        private String province;
        private String district;
        private String ward;
        private String street;
    }

    @Data
    @ToString
    private class kiemTraMatTruoc {
        private String denTrang;
        private String catGoc;
        private String dauNoi;
        private String kiemTraAnh;
        private String ngayHetHan;
        private String quyLuatSo;
    }

    @Data
    @ToString
    private class kiemTraMatSau {
        private String dauXacNhan;

    }

    @Data
    @ToString
    private class error {
        private String dauXacNhan;

    }
}
