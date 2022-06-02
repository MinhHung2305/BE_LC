update public."template" set template_content = '<!DOCTYPE html>
<html>
 <head>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8"> </meta>
   <style>
     @font-face {
       font-family: Times New Roman;
       src: url(src/main/resources/TimesNewRoman.ttf);
       -fs-pdf-font-embed: embed;
       -fs-pdf-font-encoding: Identity-H
     }

     .apdungfont {
       font-family: Times New Roman
     }

     table {
       border-collapse: collapse;
       width: 100%;
       font-size: 14px
     }

     td,
     th {
       border: 1px solid #ddd;
       text-align: left;
       padding: 8px
     }

     .column {
       float: left;
       width: 50%;
       padding-top: 10px;
       height: 50px;
       text-align: center
     }

     .row:after {
       content: "";
       display: table;
       clear: both
     }
   </style>
 </head>
 <body>
   <div class="apdungfont" style="margin-left:50px;margin-right:50px;word-wrap:break-word">
     <div class="row-text">
       <h3 style="text-align:center;font-size:14px">ĐỀ NGHỊ PHÁT HÀNH THƯ TÍN DỤNG (L/C) ONLINE</h3>
     </div>
     <div class="row-text">
       <p style="font-size:14px">Kính gửi: $bank_info</p>
     </div>
     <div class="row-text">
       <p style="font-size:14px">Với mọi trách nhiệm thuộc về mình, Chúng tôi đề nghị Quý Ngân hàng phát hành L/C không hủy ngang với các nội dung như sau:</p>
     </div>
     <div class="row-text">
       <h4 style="font-size:14px">I. THÔNG TIN KHÁCH HÀNG</h4>
     </div>
     <div class="row-text">
       <p style="font-size:14px">Tên khách hàng: $corporateBuy</p>
     </div>
     <div class="row-text">
       <p style="font-size:14px">Mã khách hàng: $corporateCode</p>
     </div>
     <div class="row-text">
       <h4 style="font-size:14px">II. THÔNG TIN ĐỀ NGHỊ PHÁT HÀNH L/C</h4>
     </div>
     <div class="row-text">
       <table>
         <tr>
           <th colspan="2">(1) Thông tin chung</th>
         </tr>
         <tr>
           <td>Mã hợp đồng</td>
           <td>$proposalCodeRelease</td>
         </tr>
         <tr>
           <td>Số hợp đồng</td>
           <td>$contract_number</td>
         </tr>
         <tr>
           <td>Bên đề nghị phát hành L/C</td>
           <td>$corporateBuy</td>
         </tr>
         <tr>
           <td>Địa chỉ và thông tin liên hệ bên đề nghị</td>
           <td>$corporate_buy_address</td>
         </tr>
         <tr>
           <td>Bên thụ hưởng</td>
           <td>$corporateSell</td>
         </tr>
         <tr>
           <td>Địa chỉ và thông tin liên hệ bên thụ hưởng</td>
           <td>$corporate_sell_address</td>
         </tr>
         <tr>
           <td>Ngân hàng thông báo</td>
           <td>$bankConfirm</td>
         </tr>
         <tr>
           <td>Địa chỉ ngân hàng thông báo</td>
           <td>$bank_confirm_address</td>
         </tr>
         <tr>
           <td>Loại L/C</td>
           <td>$lcType</td>
         </tr>
         <tr>
           <td>Ngân hàng chuyển nhượng</td>
           <td>$bankTranfer</td>
         </tr>
         <tr>
           <td>Chỉ dẫn xác nhận</td>
           <td>$confirmationInstruction</td>
         </tr>
         <tr>
           <td>Yêu cầu Ngân hàng xác nhận</td>
           <td>$confirmingBankRequest</td>
         </tr>
         <tr>
           <th colspan="2">(2) Điều khoản thanh toán</th>
         </tr>
         <tr>
           <td>Loại tiền và trị giá L/C</td>
           <td>$valueLc - $moneyType</td>
         </tr>
         <tr>
           <td>Dung sai giảm trên số tiền (%)</td>
           <td>$negativeTolerance</td>
         </tr>
         <tr>
           <td>Dung sai tăng trên số tiền (%)</td>
           <td>$positiveTolerance</td>
         </tr>
         <tr>
           <td>Chứng từ xuất trình tại</td>
           <td>$presentationAt</td>
         </tr>
         <tr>
           <td>Điều khoản thanh toán</td>
           <td>$termOfPayment</td>
         </tr>
         <tr>
           <td>Số tiền thanh toán</td>
           <td>$paymentAmount</td>
         </tr>
         <tr>
           <td>Ngày hết hạn</td>
           <td>$dueDate</td>
         </tr>
         <tr>
           <td>Nơi hết hạn</td>
           <td>$dueAddress</td>
         </tr>
         <tr>
           <td>Phí</td>
           <td>$fee</td>
         </tr>
         <tr>
           <th colspan="2">(3) Giao hàng</th>
         </tr>
         <tr>
           <td>Giao hàng từng phần</td>
           <td>$partialShipment</td>
         </tr>
         <tr>
           <td>Chuyển tải</td>
           <td>$transhipment</td>
         </tr>
         <tr>
           <td>Nơi giao hàng</td>
           <td>$placeOfReceipt</td>
         </tr>
         <tr>
           <td>Nơi nhận hàng</td>
           <td>$placeOfDestination</td>
         </tr>
         <tr>
           <td>Cảng xuất hàng/ Sân bay khởi hành</td>
           <td>$portOfDeparture</td>
         </tr>
         <tr>
           <td>Cảng dỡ hàng/ Sân bay đến</td>
           <td>$portOfDestination</td>
         </tr>
         <tr>
           <td>Ngày giao hàng muộn nhất</td>
           <td>$lastestDeliveryDate</td>
         </tr>
         <tr>
           <td>Thời gian giao hàng</td>
           <td>$deliveryTime</td>
         </tr>
         <tr>
           <th colspan="2">(4) Mô tả hàng hóa dịch vụ</th>
         </tr>
         <tr>
           <td colspan="2">Danh mục hàng hóa</td>
         </tr>$listProduct <tr>
           <td>Mô tả chi tiết hàng hóa</td>
           <td>$descriptionOfGoods</td>
         </tr>
         <tr>
           <td>Loại hàng hóa</td>
           <td>$product_type</td>
         </tr>
         <tr>
           <td>Tổng giá hàng hóa</td>
           <td>$total_value_product</td>
         </tr>
         <tr>
           <td>VAT</td>
           <td>$vat_product</td>
         </tr>
         <tr>
           <td>Tổng giá hàng hóa (bao gồm VAT)</td>
           <td>$total_value_after_vat</td>
         </tr>
         <tr>
           <th colspan="2">(5) Chứng từ xuất trình</th>
         </tr>$listLicense <tr>
           <th colspan="2">(6) Điều kiện bổ sung</th>
         </tr>
         <tr>
           <td>Thời gian xuất trình</td>
           <td>$periodForPresentation</td>
         </tr>
         <tr>
           <td>Đòi tiền bằng điện</td>
           <td>$ttReimbursement</td>
         </tr>
         <tr>
           <td>Điều kiện khác</td>
           <td>$otherCondition</td>
         </tr>
         <tr>
           <th colspan="2">(7) Chỉ dẫn thanh toán</th>
         </tr>
         <tr>
           <td>Ký quỹ</td>
           <td>$holdAccount</td>
         </tr>
         <tr>
           <td>Thu phí</td>
           <td>$thuPhi</td>
         </tr>
         <tr>
           <td>Thanh toán</td>
           <td>$paymentAccount</td>
         </tr>
         <tr>
           <th colspan="2">(8) Cam kết của khách hàng</th>
         </tr>
         <tr>
           <td colspan="2">$commitmentCustomer</td>
         </tr>
       </table>
     </div>
     <div class="row">
       <div class="column">
         <p style="padding-bottom:200px;font-weight:700;font-size:14px">KẾ TOÁN TRƯỞNG</p>
       </div>
       <div class="column">
         <p style="padding-bottom:200px;font-weight:700;font-size:14px">ĐẠI DIỆN DOANH NGHIỆP</p>
       </div>
     </div>
   </div>
 </body>
</html>'
where template_code = 'generateDNPHPdf';

