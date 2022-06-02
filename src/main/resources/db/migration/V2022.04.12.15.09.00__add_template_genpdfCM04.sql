INSERT INTO public."template" (template_code,template_name,template_description,template_subject,template_content,template_to,template_cc,template_bcc,template_type,template_status,created_date,created_by,last_modified_by,last_modified_date) VALUES
	 ('generatePdf','Test','Test','Test','<?xml version=''1.0'' encoding=''UTF-8''?>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta http-equiv=''Content-Type'' content=''text/html; charset=utf-8''/>
      <style>
         @font-face {
         font-family: fontvidu;
         src: url(''src/main/resources/ARIALUNI.ttf'');
         -fs-pdf-font-embed: embed;
         -fs-pdf-font-encoding: Identity-H;
         }
         @page {
         size: A4;
         }
         .apdungfont {
         font-family: fontvidu, Arial Unicode MS, Lucida Sans Unicode, Arial, verdana, arial, helvetica, sans-serif, sans-serif;
         }
         .ant-col {
         position: relative;
         max-width: 100%;
         min-height: 1px;
         }
         .ant-row {
         display: flex;
         flex-flow: row wrap;
         }
         .ant-col-12 {
         display: block;
         flex: 0 0 50%;
         max-width: 50%;
         }
         .ant-col-24 {
         display: block;
         flex: 0 0 100%;
         max-width: 100%;
         }
         .ant-col-1 {
         display: block;
         flex: 0 0 4.16666667%;
         max-width: 4.16666667%;
         }
         .ant-col-22 {
         display: block;
         flex: 0 0 91.66666667%;
         max-width: 91.66666667%;
         }
         .ant-col-4 {
         display: block;
         flex: 0 0 16.66666667%;
         max-width: 16.66666667%;
         }
         .ant-col-16 {
         display: block;
         flex: 0 0 66.66666667%;
         max-width: 66.66666667%;
         }
         .row:after {
         content: "";
         display: table;
         clear: both;
         }
         .column {
         float: left;
         width: 50%;
         padding-top: 10px;
         padding-bottom: 20px;
         height: 50px;
         text-align: center;
         }
         table {
         border-collapse: collapse;
         width: 100%;
         font-size: 19px;
         }
         td,th {
         border: 1px solid #dddddd;
         text-align: left;
         padding: 8px;
         }
      </style>
   </head>
   <body>
      <main class="ant-layout-content layout-main-content--3St4r apdungfont" id="KTMainLayout-content" style="height: 100%; width: 90%; word-wrap:break-word; margin-left: 5%;">
         <div style="background: white; border-radius: 5px;">
            <div class="ant-row" style="padding: 16px; row-gap: 0px;">
               <div class="ant-col ant-col-24">
                  <form class="ant-form ant-form-horizontal">
                     <div class="ant-row" style="row-gap: 0px;">
                        <div class="ant-col ant-col-24" style="text-align: -webkit-center;">
                           <span class="ant-typography KTText KTText-light-title KTText-size-2">
                           <b>CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM</b>
                           </span>
                           <p>Độc lập - Tự do - Hạnh phúc</p>
                           <span class="ant-typography KTText KTText-light-title KTText-size-2">
                           <b>HỢP ĐỒNG MUA HÀNG</b>
                           </span>
                           <p>Hợp đồng số: $contractNo</p>
                        </div>
                     </div>
                     <div class="ant-row" style="row-gap: 0px;">
                        <div class="ant-col ant-col-1"></div>
                        <div class="ant-col ant-col-22">
                           <div class="ant-row" style="row-gap: 0px;">
                              <div class="ant-col ant-col-24">
                                 <b>Căn cứ luật: </b>
                                 <p>$pursuantLaw</p>
                                 <p>Hôm nay: ngày 21 tháng 03 năm 2022 chúng tôi gồm có:</p>
                              </div>
                           </div>
                           <div class="ant-row" style="row-gap: 0px;">
                              <div class="ant-col ant-col-24">
                                 <b>Bên bán: $corporateNameSeller</b>
                                 <p>Địa chỉ bán: $corporateAddressSeller</p>
                                 <p>Đại diện bán: $representativeSeller</p>
                                 <p>Chức vụ: $positionSeller</p>
                              </div>
                           </div>
                           <div class="ant-row" style="row-gap: 0px;">
                              <div class="ant-col ant-col-24">
                                 <b>Bên mua: $corporateNameBuyer</b>
                                 <p>Địa chỉ mua: $corporateAddressBuyer</p>
                                 <p>Đại diện mua: $representativeBuyer</p>
                                 <p>Chức vụ: $positionBuyer</p>
                              </div>
                           </div>
                           <div class="ant-row" style="row-gap: 0px;">
                              <div class="ant-col ant-col-24">
                                 <b>Sau khi thảo luận, 2 bên thống nhất nội dung như sau:</b>
                              </div>
                           </div>
                           <div class="ant-row" style="row-gap: 0px;">
                              <div class="ant-col ant-col-24">
                                 <b>Điều 1: Mô tả hàng hóa </b>
                              </div>
                              <div class="ant-col ant-col-24">
                                 <p>Mô tả hàng hóa: $descriptionCommodity</p>
                              </div>
                              <div>
                                 <p>Danh mục hàng hóa: </p>
                              </div>
                              <div class="ant-col ant-col-16">
                                 <pre style="font-family: fontvidu, Arial Unicode MS, Lucida Sans Unicode, Arial, verdana, arial, helvetica, sans-serif;">
                        $listProduct
                      </pre>
                              </div>
                              <div class="ant-col ant-col-24">
                                 <p>VAT (%): $contractVat</p>
                              </div>
                              <div class="ant-col ant-col-24">
                                 <p>Dung sai giảm số tiền (%): $amountReductionTolerance</p>
                              </div>
                              <div class="ant-col ant-col-24">
                                 <p>Dung sai tăng số tiền (%): $toleranceIncreaseAmount</p>
                              </div>
                              <div class="ant-col ant-col-24">
                                 <p>Tổng giá trị hợp dồng: $contractValue</p>
                              </div>
                              <div class="ant-col ant-col-24">
                                 <p>Loại tiền: $currency</p>
                              </div>
                           </div>
                           <div class="ant-row" style="row-gap: 0px;">
                              <div class="ant-col ant-col-24">
                                 <b>Điều 2: Phương thức thanh toán </b>
                              </div>
                              <div class="ant-col ant-col-24">
                                 <ul>
                                    <li>Phương tiện giao hàng: $deliveryVehicle</li>
                                    <li>Thời hạn giao hàng: $deliveryTerm</li>
                                    <li>Hạn giao hàng: $deliveryDeadline</li>
                                    <li>Địa điểm giao hàng: $placeDelivery</li>
                                    <li>Địa điểm nhận hàng: $deliveryLocation</li>
                                 </ul>
                              </div>
                           </div>
                           <div class="ant-row" style="row-gap: 0px;">
                              <div class="ant-col ant-col-24">
                                 <b>Điều 3: Chất lượng hàng và bảo hành hàng hóa </b>
                              </div>
                              <div class="ant-col ant-col-24">
                                 <ul>
                                    <li>Chất lượng hàng hóa: $productQuality</li>
                                    <li>Quy định đổi hàng: $termsOfExchange</li>
                                    <li>Bảo hành hàng hóa: $goodsWarranty</li>
                                    <li>Bên Mua có trách nhiệm kiểm tra hàng trước khi nhận hàng. Sau khi 2 bên ký vào Biên Bản Giao Nhận Hàng Hóa, bên Bán không chịu trách nhiệ, về chất lượng hàng hóa.</li>
                                 </ul>
                              </div>
                           </div>
                           <div class="ant-row" style="row-gap: 0px;">
                              <div class="ant-col ant-col-24">
                                 <b>Điều 4: Phương thức thanh toán</b>
                              </div>
                              <div class="ant-col ant-col-24">
                                 <ul>
                                    <li>Phương thức thanh toán: Bên Mua sẽ thanh toán cho Bên Bán bằng: $paymentMethods</li>
                                    <li>Bên Mua sẽ mở $lcName $paymentTermLc với giá trị bằng $lcPayment giá trị hợp đồng</li>
                                    <li>Ngân hàng mở L/C phải là một trong các ngân hàng hàng đầu của Việt Nam và/hoặc một ngân hàng được Bên Bán chấp thuận. Nội dung của LC phải được Bên Bán chấp thuận.</li>
                                    <li>Ngân hàng thụ hưởng của Bên Bán là: $bankName </li>
                                    <li>Số tài khoản: $corporateAccountNumber</li>
                                    <li>Bộ chứng từ thanh toán: $listLicense</li>
                                    <li>Trong trường hợp thanh toán chậm, bên Mua phải chịu lãi suất phạt chậm trả là $latePaymentInterestRate %/năm trên số ngày chậm trả và số tiền chậm trả.</li>
                                 </ul>
                              </div>
                           </div>
                           <div class="ant-row" style="row-gap: 0px;">
                              <div class="ant-col ant-col-24">
                                 <b>Điều 5: Bảo hiểm hàng hóa</b>
                              </div>
                              <div class="ant-col ant-col-24">
                                 <ul>
                                    <li>Bảo hiểm hàng hóa: $cargoInsurance</li>
                                 </ul>
                              </div>
                           </div>
                           <div class="ant-row" style="row-gap: 0px;">
                              <div class="ant-col ant-col-24">
                                 <b>Điều 6: Quy định phạt và bồi thường hợp đồng</b>
                              </div>
                              <div class="ant-col ant-col-24">
                                 <ul>
                                    <li>Nghĩa vụ bên mua: $obligationsBuyer</li>
                                    <li>Nghĩa vụ bên bán: $obligationsSeller</li>
                                    <li>Quy định phạt và bồi thường hợp đồng: $regulationsPenaltiesAndContractCompensation</li>
                                 </ul>
                              </div>
                           </div>
                           <div class="ant-row" style="row-gap: 0px;">
                              <div class="ant-col ant-col-24">
                                 <b>Điều 7: Thủ tục tranh chấp và giải quyết hợp đồng</b>
                              </div>
                              <div class="ant-col ant-col-24">
                                 <p>$disputeSettlementProcedures</p>
                              </div>
                           </div>
                           <div class="ant-row" style="row-gap: 0px;">
                              <div class="ant-col ant-col-24">
                                 <b>Điều 8: Trường hợp bất khả kháng</b>
                              </div>
                              <div class="ant-col ant-col-24">
                                 <p>$caseOfForceMajeure</p>
                              </div>
                           </div>
                           <div class="ant-row" style="row-gap: 0px;">
                              <div class="ant-col ant-col-24">
                                 <b>Điều 9: Hiệu lực thi hành</b>
                              </div>
                              <div class="ant-col ant-col-24">
                                 <p>$validityContract</p>
                              </div>
                           </div>
                           <div class="ant-row" style="row-gap: 0px;">
                              <div class="ant-col ant-col-24">
                                 <b>Điều 10: Điều khoản chung</b>
                              </div>
                              <div class="ant-col ant-col-24">
                                 <p>$generalTerms</p>
                              </div>
                           </div>
                           <div>
                              $contractAddendum
                           </div>
                           <div style="height:50px"></div>
                           <div>
                              <div style="width: 50%; float: left; text-align: center;">
                                 <b>ĐẠI DIỆN BÊN MUA</b>
                              </div>
                              <div style="text-align: center; ">
                                 <b>ĐẠI DIỆN BÊN BÁN</b>
                              </div>
                              <div style="height: 150px;"></div>
                           </div>
                           <div class="ant-row" style="row-gap: 0px;">
                              <div class="ant-col ant-col-24">
                                 <div class="ant-row ant-form-item" style="row-gap: 0px;">
                                    <div class="ant-col ant-form-item-control">
                                       <div class="ant-form-item-control-input">
                                          <div class="ant-form-item-control-input-content">
                                             <label class="ant-checkbox-wrapper ant-checkbox-wrapper-checked">
                                             <span>&#10003;  Tôi đã đọc, hiểu, đồng ý với nội dung của văn bản điện tử và thống nhất sử dụng phương thức điện tử để giao dịch</span>
                                             </label>
                                          </div>
                                       </div>
                                    </div>
                                 </div>
                              </div>
                           </div>
                           <div class="ant-row" style="row-gap: 0px;">
                              <div class="ant-col ant-col-24">
                                 <div class="ant-row ant-form-item" style="row-gap: 0px;">
                                    <div class="ant-col ant-form-item-control">
                                       <div class="ant-form-item-control-input">
                                          <div class="ant-form-item-control-input-content">
                                             <label class="ant-checkbox-wrapper ant-checkbox-wrapper-checked">
                                             <span>&#10003;  Các phụ lục đi kèm là một phần không thể tách rời của Hợp đồng</span>
                                             </label>
                                          </div>
                                       </div>
                                    </div>
                                 </div>
                              </div>
                           </div>
                           <div class="ant-col ant-col-2"></div>
                        </div>
                        <div class="ant-col ant-col-1"></div>
                     </div>
                  </form>
               </div>
            </div>
         </div>
      </main>
   </body>
</html>',NULL,NULL,NULL,'email',1,'2022-03-20 15:16:35.457',NULL,NULL,'2022-03-20 15:16:35.457');