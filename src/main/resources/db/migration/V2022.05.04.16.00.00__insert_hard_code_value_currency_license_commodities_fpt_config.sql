-- currency loại tiền tệ;
CREATE TABLE IF NOT EXISTS public.currency (
	currency_id serial4 NOT NULL,
	currency_code varchar(255) NOT NULL,
	currency_name varchar(255) NOT NULL,
	currency_status int4 NULL DEFAULT 1,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_by varchar NULL,
	last_modified_by varchar NULL,
	last_modified_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT currency_id_pk PRIMARY KEY (currency_id)
);


-- loại tiền tệ
insert into public.currency (currency_code, currency_name) values ('VND','Việt Nam Đồng');
insert into public.currency (currency_code, currency_name) values ('USD','Đô la Mỹ');
insert into public.currency (currency_code, currency_name) values ('EUR','Đồng Euro');
insert into public.currency (currency_code, currency_name) values ('JPY','Yên Nhật');
insert into public.currency (currency_code, currency_name) values ('GBP','Bảng Anh');
insert into public.currency (currency_code, currency_name) values ('CHF','Phơ răng Thuỵ Sĩ');
insert into public.currency (currency_code, currency_name) values ('AUD','Đô la Úc');
insert into public.currency (currency_code, currency_name) values ('CAD','Đô la Canada');
insert into public.currency (currency_code, currency_name) values ('SEK','Curon Thuỵ Điển');
insert into public.currency (currency_code, currency_name) values ('NOK','Curon Nauy');
insert into public.currency (currency_code, currency_name) values ('DKK','Curon Đan Mạch');
insert into public.currency (currency_code, currency_name) values ('RUB','Rúp Nga');
insert into public.currency (currency_code, currency_name) values ('NZD','Đô la Newzealand');
insert into public.currency (currency_code, currency_name) values ('HKD','Đô la Hồng Công');
insert into public.currency (currency_code, currency_name) values ('SGD','Đô la Singapore');
insert into public.currency (currency_code, currency_name) values ('MYR','Ringít Malaysia');
insert into public.currency (currency_code, currency_name) values ('THB','Bath Thái');
insert into public.currency (currency_code, currency_name) values ('IDR','Rupiah Inđônêsia');
insert into public.currency (currency_code, currency_name) values ('KRW','Won Hàn Quốc');
insert into public.currency (currency_code, currency_name) values ('INR','Rupee Ấn độ');
insert into public.currency (currency_code, currency_name) values ('TWD','Đô la Đài Loan');
insert into public.currency (currency_code, currency_name) values ('CNY','Nhân dân tệ TQuốc');
insert into public.currency (currency_code, currency_name) values ('KHR','Riêl Cămpuchia');
insert into public.currency (currency_code, currency_name) values ('LAK','Kíp Lào');
insert into public.currency (currency_code, currency_name) values ('MOP','Pataca Macao');
insert into public.currency (currency_code, currency_name) values ('TRY','Thổ Nhĩ Kỳ');
insert into public.currency (currency_code, currency_name) values ('BRL','Real Brazin');
insert into public.currency (currency_code, currency_name) values ('PLN','Đồng Zloty Ba Lan');
insert into public.currency (currency_code, currency_name) values ('AED','Đồng UAE Dirham');


-- loai chứng từ
ALTER TABLE public.license add column IF NOT EXISTS license_code varchar(255) default 'OTHERS';

insert into public.license (license_code, license_name) values ('BC','Xác nhận đặt chỗ trên các phương tiện vận tải ');
insert into public.license (license_code, license_name) values ('SC','Hợp đồng thương mại');
insert into public.license (license_code, license_name) values ('CI','Hóa đơn thương mại');
insert into public.license (license_code, license_name) values ('PL','Phiếu đóng gói');
insert into public.license (license_code, license_name) values ('BL','Vận đơn đường biển');
insert into public.license (license_code, license_name) values ('SW','Giấy gửi hàng đường biển ');
insert into public.license (license_code, license_name) values ('CD','Tờ khai hải quan');
insert into public.license (license_code, license_name) values ('PI','Hóa đơn chiếu lệ');
insert into public.license (license_code, license_name) values ('IC','Bảo hiểm');
insert into public.license (license_code, license_name) values ('CO','Giấy chứng nhận xuất xứ ');
insert into public.license (license_code, license_name) values ('PC','Giấy chứng nhận kiểm dịch');
insert into public.license (license_code, license_name) values ('CQL','Giấy chứng nhận chất lượng');
insert into public.license (license_code, license_name) values ('CA','Chứng nhận kiểm định');
insert into public.license (license_code, license_name) values ('SC','Giấy chứng nhận vệ sinh');
insert into public.license (license_code, license_name) values ('FC','Chứng thư hun trùng');
insert into public.license (license_code, license_name) values ('MSDS','Phiếu khai báo hóa chất an toàn ');
insert into public.license (license_code, license_name) values ('PTPL','Chứng nhận hợp chuẩn hợp quy, phân tích phân loại');
insert into public.license (license_code, license_name) values ('CQN','Giấy chứng nhận số lượng');
insert into public.license (license_code, license_name) values ('BE','Hối phiếu');
insert into public.license (license_code, license_name) values ('AWB','Vận đơn hàng không');
insert into public.license (license_code, license_name) values ('VAT','Hóa đơn VAT');
insert into public.license (license_code, license_name) values ('RD','Phiếu giao hàng');
insert into public.license (license_code, license_name) values ('DN','Phiếu xuất kho');
insert into public.license (license_code, license_name) values ('AR','Biên bản nghiệm thu');
insert into public.license (license_code, license_name) values ('HOM','Biên bản bàn giao');
insert into public.license (license_code, license_name) values ('GL','Bảng kê hàng hóa');

-- loại LC
ALTER TABLE public.lc_classify add column IF NOT EXISTS lc_code varchar(255) NULL;
update lc_classify set lc_name = 'L/C thường', lc_code='NORMAL' where lc_type = 'lc';
update lc_classify set lc_name = 'UPAS L/C', lc_code='UPAS' where lc_type ='UPAS';


-- loại hàng hóa commodities
ALTER TABLE public.commodities add column IF NOT EXISTS commodities_code_level_0 varchar(255) NULL;
ALTER TABLE public.commodities add column IF NOT EXISTS commodities_code_level_1 varchar(255) NULL;
ALTER TABLE public.commodities add column IF NOT EXISTS created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE public.commodities add column IF NOT EXISTS created_by varchar NULL;
ALTER TABLE public.commodities add column IF NOT EXISTS last_modified_by varchar NULL;
ALTER TABLE public.commodities add column IF NOT EXISTS last_modified_date timestamp NULL DEFAULT CURRENT_TIMESTAMP;

insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('1','0','ĐỘNG VẬT SỐNG; CÁC SẢN PHẨM TỪ ĐỘNG VẬT');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('1','1','Động vật sống');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('1','1','Thịt và phụ phẩm dạng thịt ăn được sau giết mổ');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('1','1','Cá và động vật giáp xác, động vật thân mềm và động vật thuỷ sinh không xương sống khác');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('1','1','Sản phẩm bơ sữa; trứng chim và trứng gia cầm; mật ong tự nhiên; sản phẩm ăn được gốc động vật, chưa được chi tiết hoặc ghi ở nơi khác');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('1','1','Sản phẩm gốc động vật, chưa được chi tiết hoặc ghi ở các nơi khác');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('2','0','CÁC SẢN PHẨM THỰC VẬT');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('2','2','Cây sống và các loại cây trồng khác; củ, rễ và loại tương tự; cành hoa và cành lá trang trí');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('2','2','Rau và một số loại củ, thân củ và rễ ăn được');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('2','2','Quả và quả hạch (nut) ăn được; vỏ quả thuộc họ cam quýt hoặc các loại dưa');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('2','2','Cà phê, chè, chè Paragoay và các loại gia vị');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('2','0','Ngũ cốc');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('2','3','Các sản phẩm xay xát; malt; tinh bột; inulin; gluten lúa mì');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('2','3','Hạt dầu và quả có dầu; các loại ngũ cốc, hạt và quả khác; cây công nghiệp hoặc cây dược liệu; rơm, rạ và cỏ khô');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('2','3','Nhựa cánh kiến đỏ; gôm, nhựa cây, các chất nhựa và các chất chiết xuất từ thực vật khác');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('2','3','Vật liệu thực vật dùng để tết bện; các sản phẩm thực vật chưa được chi tiết hoặc ghi ở nơi khác');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('3','0','CHẤT BÉO VÀ DẦU CÓ NGUỒN GỐC TỪ ĐỘNG VẬT HOẶC THỰC VẬT VÀ CÁC SẢN PHẨM TÁCH TỪ CHÚNG; CHẤT BÉO ĂN ĐƯỢC ĐÃ CHẾ BIẾN; CÁC LOẠI SÁP ĐỘNG VẬT HOẶC THỰC VẬT');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('3','4','Chất béo và dầu có nguồn gốc từ động vật hoặc thực vật và các sản phẩm tách từ chúng; chất béo ăn được đã chế biến; các loại sáp động vật hoặc thực vật');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('4','0','THỰC PHẨM CHẾ BIẾN; ĐỒ UỐNG, RƯỢU MẠNH VÀ GIẤM; THUỐC LÁ VÀ CÁC LOẠI NGUYÊN LIỆU THAY THẾ THUỐC LÁ ĐÃ CHẾ BIẾN');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('4','5','Các chế phẩm từ thịt, cá hay động vật giáp xác, động vật thân mềm hoặc động vật thuỷ sinh không xương sống khác');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('4','5','Đường và các loại kẹo đường');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('4','5','Ca cao và các chế phẩm từ ca cao');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('4','5','Chế phẩm từ ngũ cốc, bột, tinh bột hoặc sữa; các loại bánh');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('4','5','Các chế phẩm từ rau, quả, quả hạch (nut) hoặc các phần khác của cây');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('4','5','Các chế phẩm ăn được khác');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('4','5','Đồ uống, rượu và giấm');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('4','5','Phế liệu và phế thải từ ngành công nghiệp thực phẩm; thức ăn gia súc đã chế biến');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('4','5','Thuốc lá và nguyên liệu thay thế lá thuốc lá đã chế biến');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('5','0','KHOÁNG SẢN');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('5','6','Muối; lưu huỳnh; đất và đá; thạch cao, vôi và xi măng');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('5','6','Quặng, xỉ và tro');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('5','6','Nhiên liệu khoáng, dầu khoáng và các sản phẩm chưng cất từ chúng; các chất chứa bi-tum; các loại sáp khoáng chất');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('6','7','SẢN PHẨM CỦA NGÀNH CÔNG NGHIỆP HOÁ CHẤT HOẶC CÁC NGÀNH CÔNG NGHIỆP LIÊN QUAN');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('6','7','Hoá chất vô cơ; các hợp chất vô cơ hay hữu cơ của kim loại quý, kim loại đất hiếm, các nguyên tố phóng xạ hoặc các chất đồng vị');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('6','7','Hoá chất hữu cơ');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('6','7','Dược phẩm');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('6','7','Phân bón');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('6','7','Các chất chiết xuất làm thuốc nhuộm hoặc thuộc da; ta nanh và các chất dẫn xuất của chúng; thuốc nhuộm, thuốc màu và các chất màu khác; sơn và véc ni; chất gắn và các loại ma tít khác; các loại mực');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('6','7','Tinh dầu và các chất tựa nhựa; nước hoa, mỹ phẩm hoặc các chế phẩm dùng cho vệ sinh');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('6','7','Xà phòng, các chất hữu cơ hoạt động bề mặt, các chế phẩm dùng để giặt, rửa, các chế phẩm bôi trơn, các loại sáp nhân tạo, sáp đã được chế biến, các chế phẩm dùng để đánh bóng hoặc tẩy sạch, nến và các sản phẩm tương tự, bột nhão dùng làm hình mẫu, sáp dùng trong nha khoa và các chế phẩm dùng trong nha khoa có thành phần cơ bản là thạch cao.');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('6','7','Các chất chứa albumin; các dạng tinh bột biến tính; keo hồ; enzyme');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('6','7','Chất nổ; các sản phẩm pháo; diêm; các hợp kim tự cháy; các chế phẩm dễ cháy khác');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('6','7','Vật liệu ảnh hoặc điện ảnh');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('6','7','Các sản phẩm hóa chất khác');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('7','0','PLASTIC VÀ CÁC SẢN PHẨM BẰNG PLASTIC; CAO SU VÀ CÁC SẢN PHẨM BẰNG CAO SU');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('7','8','Plastic và các sản phẩm bằng plastic');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('7','8','Cao su và các sản phẩm bằng cao su');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('8','0','DA SỐNG, DA THUỘC, DA LÔNG VÀ CÁC SẢN PHẨM TỪ DA; YÊN CƯƠNG VÀ BỘ ĐỒ YÊN CƯƠNG; HÀNG DU LỊCH, TÚI XÁCH TAY VÀ CÁC LOẠI ĐỒ CHỨA TƯƠNG TỰ; CÁC MẶT HÀNG TỪ RUỘT ĐỘNG VẬT (TRỪ RUỘT CON TẰM)');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('8','9','Da sống (trừ da lông) và da thuộc');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('8','9','Các sản phẩm bằng da thuộc; yên cương và bộ yên cương; các mặt hàng du lịch, túi xách và các loại đồ chứa tương tự; các sản phẩm làm từ ruột động vật (trừ ruột con tằm)');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('8','9','Da lông và da lông nhân tạo; các sản phẩm làm từ da lông và da lông nhân tạo');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('9','0','GỖ VÀ CÁC MẶT HÀNG BẰNG GỖ; THAN TỪ GỖ; LIE VÀ CÁC SẢN PHẨM BẰNG LIE; CÁC SẢN PHẨM TỪ RƠM, CỎ GIẤY HOẶC CÁC VẬT LIỆU TẾT BỆN KHÁC; CÁC SẢN PHẨM BẰNG LIỄU GAI VÀ SONG MÂY');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('9','10','Gỗ và các mặt hàng bằng gỗ; than từ gỗ');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('9','10','Lie và các sản phẩm bằng lie');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('9','10','Sản phẩm làm từ rơm, cỏ giấy hoặc từ các loại vật liệu tết bện khác; các sản phẩm bằng liễu gai và song mây');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('10','0','BỘT GIẤY TỪ GỖ HOẶC TỪ NGUYÊN LIỆU XƠ SỢI XENLULO KHÁC; GIẤY LOẠI HOẶC BÌA LOẠI THU HỒI (PHẾ LIỆU VÀ VỤN THỪA); GIẤY VÀ BÌA VÀ CÁC SẢN PHẨM CỦA CHÚNG');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('10','11','Bột giấy từ gỗ hoặc từ nguyên liệu xơ xenlulo khác; giấy loại hoặc bìa loại thu hồi (phế liệu và vụn thừa)');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('10','11','Giấy và bìa; các sản phẩm làm bằng bột giấy, bằng giấy hoặc bằng bìa');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('10','11','Sách, báo, tranh ảnh và các sản phẩm khác của công nghiệp in; các loại bản thảo viết bằng tay, đánh máy và sơ đồ');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('11','0','NGUYÊN LIỆU DỆT VÀ CÁC SẢN PHẨM DỆT');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('11','11','Tơ tằm');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('11','11','Lông cừu, lông động vật loại mịn hoặc loại thô; sợi từ lông đuôi hoặc bờm ngựa và vải dệt thoi từ các nguyên liệu trên');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('11','11','Bông');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('11','11','Xơ dệt gốc thực vật khác; sợi giấy và vải dệt thoi từ sợi giấy');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('11','11','Sợi filament nhân tạo; dải và các dạng tương tự từ nguyên liệu dệt nhân tạo');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('11','11','Xơ sợi staple nhân tạo');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('11','11','Mền xơ, phớt và các sản phẩm không dệt; các loại sợi đặc biệt; sợi xe, chão bện (cordage), thừng và cáp và các sản phẩm của chúng');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('11','11','Thảm và các loại hàng dệt trải sàn khác');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('11','11','Các loại vải dệt thoi đặc biệt; các loại vải dệt chần sợi vòng; hàng ren; thảm trang trí; hàng trang trí; hàng thêu');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('11','11','Các loại vải dệt đã được ngâm tẩm, tráng, phủ hoặc ép lớp; các mặt hàng dệt thích hợp dùng trong công nghiệp');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('11','11','Các loại hàng dệt kim hoặc móc');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('11','11','Quần áo và hàng may mặc phụ trợ, dệt kim hoặc móc');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('11','11','Quần áo và các hàng may mặc phụ trợ, không dệt kim hoặc móc');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('11','11','Các mặt hàng dệt đã hoàn thiện khác; bộ vải; quần áo dệt và các loại hàng dệt đã qua sử dụng khác; vải vụn');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('12','0','GIÀY, DÉP, MŨ VÀ CÁC VẬT ĐỘI ĐẦU KHÁC, Ô, DÙ, BA TOONG, GẬY TAY CẦM CÓ THỂ CHUYỂN THÀNH GHẾ, ROI GẬY ĐIỀU KHIỂN, ROI ĐIỀU KHIỂN SÚC VẬT THỒ KÉO VÀ CÁC BỘ PHẬN CỦA CÁC LOẠI HÀNG TRÊN; LÔNG VŨ CHẾ BIẾN VÀ CÁC SẢN PHẨM LÀM TỪ LÔNG VŨ CHẾ BIẾN; HOA NHÂN TẠO; CÁC SẢN PHẨM LÀM TỪ TÓC NGƯỜI');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('12','12','Giày, dép, ghệt và các sản phẩm tương tự; các bộ phận của các sản phẩm trên');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('12','12','Mũ và các vật đội đầu khác và các bộ phận của chúng');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('12','12','Ô, dù che, ba toong, gậy tay cầm có thể chuyển thành ghế, roi, gậy điều khiển, roi điều khiển súc vật thồ kéo và các bộ phận của các sản phẩm trên');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('12','12','Lông vũ và lông tơ chế biến, các sản phẩm bằng lông vũ hoặc lông tơ; hoa nhân tạo; các sản phẩm làm từ tóc người');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('13','0','SẢN PHẨM BẰNG ĐÁ, THẠCH CAO, XI MĂNG, AMIĂNG, MICA HOẶC CÁC VẬT LIỆU TƯƠNG TỰ; ĐỒ GỐM; THUỶ TINH VÀ CÁC SẢN PHẨM BẰNG THUỶ TINH');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('13','13','Sản phẩm làm bằng đá, thạch cao, xi măng, amiăng, mica hoặc các vật liệu tương tự');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('13','13','Đồ gốm, sứ');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('13','13','Thuỷ tinh và các sản phẩm bằng thuỷ tinh');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('14','0','NGỌC TRAI TỰ NHIÊN HOẶC NUÔI CẤY, ĐÁ QUÝ HOẶC ĐÁ BÁN QUÝ, KIM LOẠI QUÝ, KIM LOẠI ĐƯỢC DÁT PHỦ KIM LOẠI QUÝ, VÀ CÁC SẢN PHẨM CỦA CHÚNG; ĐỒ TRANG SỨC LÀM BẰNG CHẤT LIỆU KHÁC; TIỀN KIM LOẠI');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('14','14','Ngọc trai tự nhiên hoặc nuôi cấy, đá quý hoặc đá bán quý, kim loại quý, kim loại được dát phủ kim loại quý, và các sản phẩm của chúng; đồ trang sức làm bằng chất liệu khác; tiền kim loại');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('15','0','KIM LOẠI CƠ BẢN VÀ CÁC SẢN PHẨM BẰNG KIM LOẠI CƠ BẢN');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('15','15','Sắt và thép');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('15','15','Các sản phẩm bằng sắt hoặc thép');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('15','15','Đồng và các sản phẩm bằng đồng');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('15','15','Niken và các sản phẩm bằng niken');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('15','15','Nhôm và các sản phẩm bằng nhôm');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('15','15','(Dự phòng cho việc phân loại tiếp theo trong tương lai của hệ thống hài hòa)');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('15','15','Chì và các sản phẩm bằng chì');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('15','15','Kẽm và các sản phẩm bằng kẽm');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('15','15','Thiếc và các sản phẩm bằng thiếc');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('15','15','Kim loại cơ bản khác; gốm kim loại; các sản phẩm của chúng');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('15','15','Dụng cụ, đồ nghề, dao, kéo và bộ đồ ăn làm từ kim loại cơ bản; các bộ phận của chúng làm từ kim loại cơ bản');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('15','15','Hàng tạp hoá làm từ kim loại cơ bản');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('16','0','MÁY VÀ CÁC TRANG THIẾT BỊ CƠ KHÍ; THIẾT BỊ ĐIỆN; CÁC BỘ PHẬN CỦA CHÚNG; THIẾT BỊ GHI VÀ TÁI TẠO ÂM THANH, THIẾT BỊ GHI VÀ TÁI TẠO HÌNH ẢNH, ÂM THANH TRUYỀN HÌNH VÀ CÁC BỘ PHẬN VÀ PHỤ KIỆN CỦA CÁC THIẾT BỊ TRÊN');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('16','16','Lò phản ứng hạt nhân, nồi hơi, máy và thiết bị cơ khí; các bộ phận của chúng');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('16','16','Máy điện và thiết bị điện và các bộ phận của chúng; máy ghi và tái tạo âm thanh, máy ghi và tái tạo hình ảnh và âm thanh truyền hình, bộ phận và phụ kiện của các loại máy trên');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('17','0','XE CỘ, PHƯƠNG TIỆN BAY, TÀU THUYỀN VÀ CÁC THIẾT BỊ VẬN TẢI LIÊN HỢP');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('17','17','Đầu máy xe lửa hoặc xe điện, toa xe lửa và các bộ phận của chúng; vật cố định và ghép nối đường ray xe lửa hoặc xe điện và bộ phận của chúng; thiết bị tín hiệu giao thông bằng cơ khí (kể cả cơ điện) các loại');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('17','17','Xe trừ phương tiện chạy trên đường xe lửa hoặc xe điện, và các bộ phận và phụ kiện của chúng.');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('17','17','Phương tiện bay, tàu vũ trụ, và các bộ phận của chúng');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('17','17','Tàu thuỷ, thuyền và các kết cấu nổi');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('18','0','DỤNG CỤ, THIẾT BỊ VÀ MÁY QUANG HỌC, NHIẾP ẢNH, ĐIỆN ẢNH, ĐO LƯỜNG, KIỂM TRA, CHÍNH XÁC, Y TẾ HOẶC PHẪU THUẬT; ĐỒNG HỒ THỜI GIAN VÀ ĐỒNG HỒ CÁ NHÂN; NHẠC CỤ; CÁC BỘ PHẬN VÀ PHỤ KIỆN CỦA CHÚNG');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('18','18','Dụng cụ, thiết bị quang học, nhiếp ảnh, điện ảnh, đo lường, kiểm tra, chính xác, y tế hoặc phẫu thuật; các bộ phận và phụ kiện của chúng');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('18','18','Đồng hồ thời gian, đồng hồ cá nhân và các bộ phận của chúng');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('18','18','Nhạc cụ; các bộ phận và phụ kiện của chúng');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('19','0','VŨ KHÍ VÀ ĐẠN; CÁC BỘ PHẬN VÀ PHỤ KIỆN CỦA CHÚNG');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('19','19','Vũ khí và đạn; các bộ phận và phụ kiện của chúng');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('20','0','CÁC MẶT HÀNG KHÁC');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('20','20','Đồ nội thất; bộ đồ giường, đệm, khung đệm, nệm và các đồ dùng nhồi tương tự; đèn và bộ đèn, chưa được chi tiết hoặc ghi ở nơi khác; biển hiệu được chiếu sáng, biển đề tên được chiếu sáng và các loại tương tự; nhà lắp ghép');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('20','20','Đồ chơi, thiết bị trò chơi và dụng cụ thể thao; các bộ phận và phụ kiện của chúng');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('20','20','Các mặt hàng khác');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('21','0','CÁC TÁC PHẨM NGHỆ THUẬT, ĐỒ SƯU TẦM VÀ ĐỒ CỔ');
insert into public.commodities (commodities_code_level_0, commodities_code_level_1, commodities_name) values ('21','21','Các tác phẩm nghệ thuật, đồ sưu tầm và đồ cổ');


-- fpt_config
CREATE TABLE IF NOT EXISTS public.fpt_config (
	fpt_config_id serial4 NOT NULL,
	fpt_config_code varchar(255) NOT NULL,
	fpt_config_name varchar(255) NOT NULL,
	fpt_config_short_name varchar(255) NOT NULL,
	fpt_config_phone varchar(255) NOT NULL,
	fpt_config_fax varchar(255) NOT NULL,
	fpt_config_email varchar(255) NOT NULL,
	fpt_config_address varchar(255) NULL,
	fpt_config_description varchar(255) NULL,
	fpt_config_status int4 NULL DEFAULT 1,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_by varchar NULL,
	last_modified_by varchar NULL,
	last_modified_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT fpt_config_id_pk PRIMARY KEY (fpt_config_id)
);

CREATE TABLE IF NOT EXISTS public.fpt_config_account (
	fpt_config_account_id serial4 NOT NULL,
	fpt_config_id int4 NOT NULL,
	bank_id int4 NOT NULL,
	fpt_config_account_number varchar NULL,
	fpt_config_currency_code varchar NULL,
	fpt_config_accoun_default int4 NULL,
	fpt_config_accoun_status int4 NOT NULL DEFAULT 1,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_by varchar NULL,
	last_modified_by varchar NULL,
	last_modified_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT fpt_config_account_pk PRIMARY KEY (fpt_config_account_id),
	CONSTRAINT fpt_config_account_bank_fk FOREIGN KEY (bank_id) REFERENCES public.bank_info(bank_id),
	CONSTRAINT fpt_config_account_config_fk FOREIGN KEY (fpt_config_id) REFERENCES public.fpt_config(fpt_config_id)
);

insert into public.fpt_config (fpt_config_id, fpt_config_code, fpt_config_name, fpt_config_short_name, fpt_config_phone, fpt_config_fax, fpt_config_email, fpt_config_address, fpt_config_description)
	values (1, 'FPT', 'Công ty TNHH Hệ thống thông tin FPT', 'FIS', '84-4-35626000', '84-4-35624850', 'contact@fis.com.vn', 'Tầng 22 tòa nhà Keangnam Landmark72, E5 đường Phạm Hùng, Phường Mễ Trì, Quận Nam Từ Liêm, Thành phố Hà Nội, Việt Nam', 'Thông tin fpt');

insert into fpt_config_account (fpt_config_id, bank_id, fpt_config_account_number, fpt_config_currency_code, fpt_config_accoun_default) select 1, bank_id, '0011000506552', 'VND', 1  from bank_info bi where bank_code ='BFTV';
insert into fpt_config_account (fpt_config_id, bank_id, fpt_config_account_number, fpt_config_currency_code, fpt_config_accoun_default) select 1, bank_id, '00666868078', 'VND', 0  from bank_info bi where bank_code ='TPBV';
