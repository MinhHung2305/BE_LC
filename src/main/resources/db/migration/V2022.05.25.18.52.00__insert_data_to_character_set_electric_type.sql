----- Dien tao -----
----MT700 ----
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('27 ', 'Điện/Tổng số điện', true, 'chữ số/chữ số', '3', 'Cấu trúc: n1/n2 trong đó n1,
        n2 là ký tự chữ số nhận giá trị từ 1 đến 8, n1 <= n2 ', ':27: 1/2',1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('40A', 'Loại L/C', true, 'ký tự X', '24 ',
        'Chỉ được nhận 1 trong 2 giá trị sau: IRREVOCABLE hoặc IRREVOCABLE TRANSFERABLE.', NULL,1,1);
INSERT INTO public.character_set_electric_type(field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('20', 'Số L/C', true, 'ký tự X', '16', 'tối đa 16 ký tự X,
        không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//''',
        ':20: 12M9087/65',1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('23', 'Số tham chiếu sơ báo', false, 'ký tự X', '16', 'tối đa 16 ký tự X,
        không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//''',
        ':23: 12M9087/65',1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('31C', 'Ngày phát hành', true, 'chữ số', '6', 'bắt buộc có 6 ký tự số, định dạng YYMMDD',
        ':31C:211109',1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('40E', 'Các điều khoản áp dụng', true, 'ký tự V', '66', 'Cấu trúc: <điều khoản áp dụng></giải thích>. Điều khoản áp dụng và giải thích phân cách bởi dấu ''/''
+. Điều khoản áp dụng bắt buộc, tối đa 30 ký tự X, chỉ được nhận 1 trong các giá trị sau: EUCP LATEST VERSION, EUCPURR LATEST VERSION, OTHR, UCP LATEST VERSION, UCPURR LATEST VERSION
+. Phần giải thích không bắt buộc, tối đa 35 ký tự X (phần giải thích chỉ được xuất hiện sau code OTHR)', ':40E: UCP LATEST VERSION
:40E: OTHR/AGREEMENT NOV2021',1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('31D', 'Ngày và nơi hết hạn', true, 'ký tự V', '35', 'Cấu trúc <ngày><địa điểm>
+. Ngày bắt buộc, bắt buộc có 6 ký tự số, định dạng YYMMDD
+. Địa điểm bắt buộc, có tối đa 29 ký tự V', ':31D: 211109VIETNAM',1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('51a', 'Ngân hàng của bên đề nghị', false, 'chữ số, chữ cái viết hoa, ký tự X', '48', 'Có thể theo định dạng A hoặc định dạng D nhưng không xuất hiện đồng thời 2 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code

Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':51A: VPBKVNVX
:51A: /V/VP BANK
VPBKVNVX
:51D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('50', 'Bên đề nghị', true, 'ký tự V', '140', 'tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự V', ':50: CONG TY TNHH HOA LAN
TAN TRIEU, THANH TRI, HA NOI',1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('59', 'Bên thụ hưởng', true, 'ký tự V', '175', 'Cấu trúc:
<tài khoản>
<tên và địa chỉ>
+. Phần tài khoản không bắt buộc, định dạng /xxx. Trong đó, bắt đầu bằng ký tự ''/'', tiếp theo bởi tối đa 34 ký tự V
+. Phần tên và địa chỉ bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự V
+. Phần tài khoản xuống dòng đến phần tên và địa chỉ', ':59:/1209876567
CONG TY TNHH BIEN DONG
KEANGNAM LANDMARK 72
ME TRI, NAM TU LIEM, HANOI',1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('32B', 'Loại tiền tệ/Trị giá L/C', true, 'chữ cái viết hoa, số thập phân', '18', 'Cấu trúc: <Loại tiền><Số tiền>
+. Loại tiền: bắt buộc, check trong danh mục tiền tệ
+. Số tiền: bắt buộc, tối đa 15 ký tự số thập phân, phân cách thập phân bằng dấu '','', có tối đa 2 chữ số sau dấu '',''', ':32B: VND100000000
:32B: USD200000,50',1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('39A', 'Dung sai trên số tiền (%)', false, 'chữ số', '5', 'Cấu trúc: n1/n2 trong đó n1, n2 là ký tự số có tối đa 2 chữ số', ':39A: 0/10',1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('39C', 'Số tiền bổ sung', false, 'ký tự V', '140', 'tối đa 4 dòng,mỗi dòng tối đa 35 ký tự V', NULL,1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('41a', 'Chứng từ xuất trình tại', true, 'chữ số, chữ cái viết hoa, ký tự V','A: 25
D: 154','Có thể theo định dạng A hoặc định dạng D nhưng không xuất hiện đồng thời 2 định dạng.Định dạng A cấu trúc:<swift code>
<code>
+. Phần Swift code: bắt buộc, check trong DB
+. Phần code: bắt buộc, tối đa 14 ký tự X. code chỉ được nhận 1 trong các giá trị BY ACCEPTANCE/ BY DEF PAYMENT/ BY MIXED PYMT/ BY NEGOTIATION/ BY PAYMENT
+. Phần swift code xuống dòng đến phần code
Định dạng D cấu trúc:
<tên ngân hàng và địa chỉ>
<code>
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự V
+. Phần code: bắt buộc, tối đa 14 ký tự X. code chỉ được nhận 1 trong các giá trị BY ACCEPTANCE/ BY DEF PAYMENT/ BY MIXED PYMT/ BY NEGOTIATION/ BY PAYMENT
+. Phần tên ngân hàng và địa chỉ xuống dòng đến phần code', ':41A: VPBKVNVX
BY PAYMENT
:41D: ANY BANK
BY NEGOTIATION
:41D: NGAN HANG TMCP DAU TU VA PHAT TRIEN
VIET NAM
HANOI, VIETNAM
BY MIXED PYMT',1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('42C', 'Thời hạn của hối phiếu', false, 'ký tự V', '105', 'tối thiểu 1 dòng, tối đa 3 dòng, mỗi dòng tối đa 35 ký tự V. Nếu F42C có thông tin thì phải xuất hiện cả F42a.
Bắt buộc phải có cặp trường (F42C, F42A) hoặc trường 42M hoặc trường 42P. Không được xuất hiện kết hợp bất kỳ trường nào trong những trường trên', ':42C: AT SIGHT
Có 42C thì phải có 42A, không có 42M, 42P
Có 42P thì không có 42C, 42A, 42M
Có 42M thì không có 42C, 42A, 42P',1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('42a', 'Bên bị đòi tiền', false, 'chữ số, chữ cái viết hoa, ký tự V', 'A: 48
D: 176','Có thể theo định dạng A hoặc định dạng D nhưng không xuất hiện đồng thời 2 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code

Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự V)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự V
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':42A: VPBKVNVX
:42A: /V/VP BANK
VPBKVNVX
:42D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('42M', 'Phương thức thanh toán hỗn hợp', false, 'ký tự V', '140', 'tối đa 4 dòng,mỗi dòng tối đa 35 ký tự V', NULL,1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('42P', 'Chiết khấu/trả chậm', false, 'ký tự V', '140', 'tối đa 4 dòng,mỗi dòng tối đa 35 ký tự V', NULL,1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('43P', 'Giao hàng từng phần', false, 'ký tự X', '11', 'tối đa 11 ký tự X,chỉ được nhận 1 trong các giá trị sau: ALLOWED, NOT ALLOWED, CONDITIONAL', NULL,1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('43T', 'Chuyển tải', false, 'ký tự X', '11', 'tối đa 11 ký tự X,chỉ được nhận 1 trong các giá trị sau: ALLOWED, NOT ALLOWED, CONDITIONAL', NULL,1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('44A', 'Nơi giao hàng', false, 'ký tự V', '65', 'tối đa 65 ký tự V', NULL,1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('44E', 'Cảng xuất hàng/Sân bay khởi hành', false, 'ký tự V', '65', 'tối đa 65 ký tự V',NULL,1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('44F', 'Cảng dỡ hàng/Sân bay đến', false, 'ký tự V', '65', 'tối đa 65 ký tự V', NULL,1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('44B', 'Nơi nhận hàng', false, 'ký tự V', '65', 'tối đa 65 ký tự V', NULL,1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('44C', 'Ngày giao hàng muộn nhất', false, 'chữ số', '6', 'obligatory có 6 ký tự chữ số,format YYMMDD. obligatory phải có trường 44C hoặc trường 44D nhưng không được có cả 44C và 44D', NULL,1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('44D', 'Thời gian giao hàng', false, 'ký tự V', '390', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự V', NULL,1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('45A', 'description hàng hóa/dịch vụ', false, 'ký tự V', '650', 'tối đa 6 dòng,mỗi dòng tối đa 65 ký tự V', NULL,1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('46A', 'Chứng từ xuất trình', false, 'ký tự V', '650', 'tối đa 6 dòng,mỗi dòng tối đa 65 ký tự V', NULL,1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('47A', 'Điều kiện bổ sung', false, 'ký tự V', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự V', NULL,1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('49G', 'Điều khoản thanh toán đặc biệt cho bên thụ hưởng', false, 'ký tự V', '650',
        'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự V', NULL,1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('49H', 'Điều khoản thanh toán đặc biệt cho Ngân hàng', false, 'ký tự V', '650',
        'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự V', NULL,1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('71D', 'Phí', false, 'ký tự V', '210', 'tối thiểu 1 dòng, tối đa 6 dòng, mỗi dòng tối đa 35 ký tự V
Cấu trúc:
+. Dòng 1: <code><loại tiền><số tiền><giải thích>
Code là bắt buộc, định dạng /HHH/. Trong đó HHH tối đa 8 ký tự chữ cái viết hoa, có thể nhận 1 hoặc nhiều giá trị sau: AGENT, COMM. CORCOM, DISC, INSUR, POST, STAMP, TELECHAR, WAREHOUS. Code để đầu mỗi dòng và để trong cặp gạch chéo.
Loại tiền: không bắt buộc, check trong danh mục tiền tệ
Số tiền: không bắt buộc, tối đa 13 ký tự số thập phân. phân cách thập phân bằng dấu '','', có 2 chữ số sau dấu '',''
Giải thích: không bắt buộc
+. Dòng 2-6: cấu trúc như dòng 1 hoặc //zzz trong đó bắt đầu bằng 2 gạch chéo //, tiếp theo là tối đa 33 ký tự V', ':71D: /AGENT/
/DISC/USD100 LATE SHIPMENT
//LATE PRESENTATION',1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('48', 'Thời hạn xuất trình chứng từ (số ngày)', false, 'chữ số, ký tự V', '39', 'Cấu trúc: <số ngày></giải thích>
+. Số ngày: bắt buộc, tối đa 3 ký tự chữ số
+. Giải thích: không bắt buộc, tối đa 35 ký tự V
+. Số ngày phân cách phần giải thích bằng dấu ''/''', ':48: 21
:48: 21/SHIPMENT DATE',1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('49', 'Chỉ dẫn xác nhận', true, 'ký tự X', '7', 'obligatory 7 ký tự X,chỉ được nhận 1 trong các giá trị sau: CONFIRM, MAY ADD, WITHOUT', NULL,1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('58a', 'Yêu cầu ngân hàng xác nhận', false, 'chữ số, chữ cái viết hoa, ký tự V', 'A: 48
D: 176', 'Trường này bắt buộc khi F49 nhận các giá trị: CONFIRM hoặc MAY ADD.
Có thể theo định dạng A hoặc định dạng D nhưng không xuất hiện đồng thời 2 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code

Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự V)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự V
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ',':58A: VPBKVNVX
:58A: /V/VP BANK
VPBKVNVX
:58D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('53a', 'Ngân hàng hoàn trả', false, 'chữ số, chữ cái viết hoa, ký tự V', 'A: 48
D: 176','Có thể theo định dạng A hoặc định dạng D nhưng không xuất hiện đồng thời 2 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code

Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự V)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự V
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':53A: VPBKVNVX
:53A: /V/VP BANK
VPBKVNVX
:53D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('78', 'Chỉ dẫn cho ngân hàng thanh toán/chấp nhận/chiết khấu', false, 'ký tự V', '780',
        'tối đa 12 dòng, mỗi dòng 65 ký tự X', NULL,1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('57a', 'Ngân hàng thông báo cho người thụ hưởng', false, 'chữ số, chữ cái viết hoa, ký tự v','A: 48
B: 72
D: 176', 'Có thể theo định dạng A hoặc định dạng B hoặc định dạng D nhưng không xuất hiện đồng thời 2 hoặc 3 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code
Định dạng B cấu trúc:
<Định danh>
<vị trí>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự V)
+. Phần vị trí không bắt buộc, tối đa 35 ký tự V
+. Phần định danh xuống dòng đến phần vị trí
Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự V)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự V
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':57A: VPBKVNVX
:57A: /V/VP BANK
VPBKVNVX
:57B: /V/VP BANK
HANOI, VIETNAM
:57D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',1,1);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type")
VALUES ('72Z', 'Thông tin khác', false, 'ký tự V', '210', 'tối đa 6 dòng,mỗi dòng tối đa 35 ký tự V', NULL,1,1);

---MT701----
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '27', 'Điện/Tổng số điện', 'chữ số/chữ số', '3', 'Cấu trúc: n1/n2 trong đó n1, n2 là ký tự chữ số nhận giá trị từ 1 đến 8, n1 <=n2', ':27: 1/2',2,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '20', 'Số L/C', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//''', ':20: 12M9087/65',2,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '45A', 'Mô tả hàng hóa/dịch vụ', 'ký tự V', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự V', NULL,2,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '46A', 'Chứng từ xuất trình', 'ký tự V', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự V', NULL,2,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '47A', 'Điều kiện bổ sung', 'ký tự V', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự V', NULL,2,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '49G', 'Điều khoản thanh toán đặc biệt cho bên thụ hưởng', 'ký tự V', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự V', NULL,2,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '49H', 'Điều khoản thanh toán đặc biệt cho Ngân hàng', 'ký tự V', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự V', NULL,2,1);

--- MT707 ---
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '27', 'Điện/Tổng số điện', 'chữ số/chữ số', '3', 'Cấu trúc: n1/n2 trong đó n1, n2 là ký tự chữ số nhận giá trị từ 1 đến 8, n1 <=n2', ':27: 1/2',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '20', 'số tham chiếu bên gửi', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//'', phải giống số L/C trên F20 của điện MT700', ':20: 12M9087/65',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '21', 'số tham chiếu bên nhận', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//''', ':21: 12M9087/65',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '23', 'Số tham chiếu ngân hàng phát hành', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//''', ':23: 12M9087/65',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '52a', 'Ngân hàng phát hành', 'chữ số, chữ cái viết hoa, ký tự X', '48', 'Có thể theo định dạng A hoặc định dạng D nhưng không xuất hiện đồng thời 2 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng ''/''H''/''xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code', ':52A: VPBKVNVX
:52A: /V/VP BANK
VPBKVNVX',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '52a', 'Ngân hàng phát hành', 'chữ số, chữ cái viết hoa, ký tự V', '176', 'Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự V)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự V
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':52D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '50B', 'Bên phát hành', 'ký tự V', '140', 'tối đa 4 dòng, mỗi dòng tối đa 35 ký tự V', NULL,3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '31C', 'Ngày phát hành', 'chữ số', '6', 'bắt buộc có 6 ký tự số, định dạng YYMMDD', ':31C: 211109',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '26E', 'Số tu chỉnh', 'chữ số', '3', 'tối đa 3 chữ số', NULL,3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '30', 'Ngày tu chỉnh', 'chữ số', '6', 'bắt buộc có 6 ký tự số, định dạng YYMMDD', ':30: 211109',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '22A', 'Mục đích của tin điện', 'chữ cái viết hoa, chữ số', '4', 'chỉ được nhận 1 trong các giá trị sau: ANCF, ADVI, ISSU', NULL,3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '23S', 'Đề nghị hủy L/C', 'chữ cái viết hoa', '6', 'chỉ được nhận giá trị sau: CANCEL', NULL,3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '40A', 'Loại L/C', 'ký tự X', '24', 'Chỉ được nhận 1 trong 2 giá trị sau: IRREVOCABLE hoặc IRREVOCABLE TRANSFERABLE.', NULL,3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '40E', 'Các điều khoản áp dụng', 'ký tự V', '66', 'Cấu trúc: <điều khoản áp dụng></giải thích>. Điều khoản áp dụng và giải thích phân cách bởi dấu ''/''
+. Điều khoản áp dụng bắt buộc, tối đa 30 ký tự X, chỉ được nhận 1 trong các giá trị sau: EUCP LATEST VERSION, EUCPURR LATEST VERSION, OTHR, UCP LATEST VERSION, UCPURR LATEST VERSION
+. Phần giải thích không bắt buộc, tối đa 35 ký tự V (phần giải thích chỉ được xuất hiện sau code OTHR)', ':40E: UCP LATEST VERSION

:40E: OTHR/AGREEMENT NOV2021',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '31D', 'Ngày và nơi hết hạn', 'chữ số, ký tự V', '35', 'Cấu trúc <ngày><địa điểm>
+. Ngày bắt buộc, bắt buộc có 6 ký tự số, định dạng YYMMDD
+. Địa điểm bắt buộc, có tối đa 29 ký tự V', ':31D: 211109VIETNAM',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '50', 'Thay đổi chi tiết bên đề nghị', 'ký tự V', '140', 'tối đa 4 dòng, mỗi dòng tối đa 35 ký tự V', ':50: CONG TY TNHH HOA LAN
TAN TRIEU, THANH TRI, HA NOI',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '59', 'Bên thụ hưởng', 'ký tự V', '175', 'Cấu trúc:
<tài khoản>
<tên và địa chỉ>
+. Phần tài khoản không bắt buộc, định dạng /xxx. Trong đó, bắt đầu bằng ký tự ''/'', tiếp theo bởi tối đa 34 ký tự V
+. Phần tên và địa chỉ bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự V
+. Phần tài khoản xuống dòng đến phần tên và địa chỉ', ':59:/1209876567
CONG TY TNHH BIEN DONG
KEANGNAM LANDMARK 72
ME TRI, NAM TU LIEM, HANOI',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '32B', 'Tăng giá trị', 'chữ cái viết hoa, số thập phân', '18', 'Cấu trúc: <Loại tiền><Số tiền>
+. Loại tiền: bắt buộc, check trong danh mục tiền tệ
+. Số tiền: bắt buộc, tối đa 15 ký tự số thập phân, phân cách thập phân bằng dấu '','', có tối đa 2 chữ số sau dấu '',''', ':33B: VND100000000
:33B: USD200000,50',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '33B', 'Giảm giá trại', 'chữ cái viết hoa, số thập phân', '18', 'Cấu trúc: <Loại tiền><Số tiền>
+. Loại tiền: bắt buộc, check trong danh mục tiền tệ
+. Số tiền: bắt buộc, tối đa 15 ký tự số thập phân, phân cách thập phân bằng dấu '','', có tối đa 2 chữ số sau dấu '',''', ':33B: VND100000000
:33B: USD200000,50',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '39A', 'Dung sai (%)', 'chữ số', '5', 'Cấu trúc: n1/n2 trong đó n1, n2 là ký tự số có tối đa 2 chữ số', ':39A: 0/10',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '39C', 'Số tiền bổ sung', 'ký tự V', '140', 'tối đa 4 dòng, mỗi dòng tối đa 35 ký tự V', NULL,3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '41a', 'Chứng từ xuất trình tại', 'chữ số, chữ cái viết hoa, ký tự X', '25', 'Có thể theo định dạng A hoặc định dạng D nhưng không xuất hiện đồng thời 2 định dạng.
Định dạng A cấu trúc:
<swift code>
<code>
+. Phần Swift code: bắt buộc, check trong DB
+. Phần code: bắt buộc, tối đa 14 ký tự X. code chỉ được nhận 1 trong các giá trị BY ACCEPTANCE/ BY DEF PAYMENT/ BY MIXED PYMT/ BY NEGOTIATION/ BY PAYMENT
+. Phần swift code xuống dòng đến phần code', ':41A: VPBKVNVX
BY PAYMENT',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '41a', 'Chứng từ xuất trình tại', 'ký tự V', '154', 'Định dạng D cấu trúc:
<tên ngân hàng và địa chỉ>
<code>
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự V
+. Phần code: bắt buộc, tối đa 14 ký tự X. code chỉ được nhận 1 trong các giá trị BY ACCEPTANCE/ BY DEF PAYMENT/ BY MIXED PYMT/ BY NEGOTIATION/ BY PAYMENT
+. Phần tên ngân hàng và địa chỉ xuống dòng đến phần code', ':41D: ANY BANK
BY NEGOTIATION
:41D: NGAN HANG TMCP DAU TU VA PHAT TRIEN
VIET NAM
HANOI, VIETNAM
BY MIXED PYMT',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '42C', 'Thời hạn của hối phiếu', 'ký tự V', '105', 'tối thiểu 1 dòng, tối đa 3 dòng, mỗi dòng tối đa 35 ký tự V. Nếu F42C có thông tin thì phải xuất hiện cả F42a.
Bắt buộc phải có cặp trường (F42C, F42A) hoặc trường 42M hoặc trường 42P. Không được xuất hiện kết hợp bất kỳ trường nào trong những trường trên', ':42C: AT SIGHT
Có 42C thì phải có 42A, không có 42M, 42P
Có 42P thì không có 42C, 42A, 42M
Có 42M thì không có 42C, 42A, 42P',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '42a', 'Bên bị đòi tiền', 'chữ số, chữ cái viết hoa, ký tự X', '48', 'Có thể theo định dạng A hoặc định dạng D nhưng không xuất hiện đồng thời 2 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code', ':42A: VPBKVNVX
:42A: /V/VP BANK
VPBKVNVX',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '42a', 'Bên bị đòi tiền', 'chữ số, chữ cái viết hoa, ký tự V', '176', 'Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự V)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự V
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':42D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '42M', 'Phương thức thanh toán hỗn hợp', 'ký tự V', '140', 'tối đa 4 dòng, mỗi dòng tối đa 35 ký tự V', NULL,3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '42P', 'Chiết khấu/trả chậm', 'ký tự V', '140', 'tối đa 4 dòng, mỗi dòng tối đa 35 ký tự V', NULL,3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '43P', 'Giao hàng từng phần', 'ký tự X', '11', 'tối đa 11 ký tự X, chỉ được nhận 1 trong các giá trị sau: ALLOWED, NOT ALLOWED, CONDITIONAL', NULL,3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '43T', 'Chuyển tải', 'ký tự X', '11', 'tối đa 11 ký tự X, chỉ được nhận 1 trong các giá trị sau: ALLOWED, NOT ALLOWED, CONDITIONAL', NULL,3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '44A', 'Nơi giao hàng', 'ký tự V', '65', 'tối đa 65 ký tự V', NULL,3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '44E', 'Cảng xuất hàng/Sân bay khởi hành', 'ký tự V', '65', 'tối đa 65 ký tự V', NULL,3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '44F', 'Cảng dỡ hàng/Sân bay đến', 'ký tự V', '65', 'tối đa 65 ký tự V', NULL,3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '44B', 'Nơi nhận hàng', 'ký tự V', '65', 'tối đa 65 ký tự V', NULL,3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '44C', 'Ngày giao hàng muộn nhất', 'chữ số', '6', 'bắt buộc có 6 ký tự chữ số, định dạng YYMMDD. Bắt buộc phải có trường 44C hoặc trường 44D nhưng không được có cả 44C và 44D', NULL,3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '44D', 'Thời gian giao hàng', 'ký tự V', '390', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự V', NULL,3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '45B', 'Mô tả hàng hóa/dịch vụ', 'ký tự V', '390', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự V', NULL,3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '46B', 'Chứng từ xuất trình', 'ký tự V', '390', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự V', NULL,3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '47B', 'Điều kiện bổ sung', 'ký tự V', '390', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự V', NULL,3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '49M', 'Điều khoản thanh toán đặc biệt cho bên thụ hưởng', 'ký tự V', '390', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự V', NULL,3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '49N', 'Điều khoản thanh toán đặc biệt cho Ngân hàng', 'ký tự V', '390', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự V', NULL,3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '71D', 'Phí', 'ký tự V', '210', 'tối thiểu 1 dòng, tối đa 6 dòng, mỗi dòng tối đa 35 ký tự V
Cấu trúc:
+. Dòng 1: <code><loại tiền><số tiền><giải thích>
Code là bắt buộc, định dạng /HHH/. Trong đó HHH tối đa 8 ký tự chữ cái viết hoa, có thể nhận 1 hoặc nhiều giá trị sau: AGENT, COMM. CORCOM, DISC, INSUR, POST, STAMP, TELECHAR, WAREHOUS. Code để đầu mỗi dòng và để trong cặp gạch chéo.
Loại tiền: không bắt buộc, check trong danh mục tiền tệ
Số tiền: không bắt buộc, tối đa 13 ký tự số thập phân. phân cách thập phân bằng dấu '','', có 2 chữ số sau dấu '',''
Giải thích: không bắt buộc
+. Dòng 2-6: cấu trúc như dòng 1 hoặc //zzz trong đó bắt đầu bằng 2 gạch chéo //, tiếp theo là tối đa 33 ký tự V', ':71D: /AGENT/
/DISC/USD100 LATE SHIPMENT
//LATE PRESENTATION',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '71N', 'Phí tu chỉnh', 'ký tự V', '', 'Cấu trúc:
<code>
<giải thích>
+. Code bắt buộc, tối đa 4 ký tự chữ cái viết hoa, chỉ được nhận 1 trong các giá trị sau: APPL, BENE, OTHR.
+. Giải thích không bắt buộc, chỉ được sử dụng kèm code OTHR, tối đa 6 dòng mỗi dòng 35 ký tự V', ':71N: APPL
:71N: OTHR
OUR AMENDMENT CHARGE USD70 FOR APPLICANT ACCOUNT. OTHER FEE RELATED TO THIS AMENDMENT FOR BENEFICIARY ACCOUNT.',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '48', 'Thời hạn xuất trình chứng từ (số ngày)', 'chữ số, ký tự V', '39', 'bắt buộc 7 ký tự X, chỉ được nhận 1 trong các giá trị sau: CONFIRM, MAY ADD, WITHOUT', NULL,3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '49', 'Chỉ dẫn xác nhận', 'ký tự X', '7', 'bắt buộc 7 ký tự X, chỉ được nhận 1 trong các giá trị sau: CONFIRM, MAY ADD, WITHOUT', NULL,3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '58a', 'Yêu cầu ngân hàng xác nhận', 'chữ số, chữ cái viết hoa, ký tự X', '48', 'Trường này bắt buộc khi F49 nhận các giá trị: CONFIRM hoặc MAY ADD.
Có thể theo định dạng A hoặc định dạng D nhưng không xuất hiện đồng thời 2 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code', ':58A: VPBKVNVX
:58A: /V/VP BANK
VPBKVNVX',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '53a', 'Ngân hàng hoàn trả', 'chữ số, chữ cái viết hoa, ký tự X', '48', 'Có thể theo định dạng A hoặc định dạng D nhưng không xuất hiện đồng thời 2 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code', ':53A: VPBKVNVX
:53A: /V/VP BANK
VPBKVNVX',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '53a', 'Ngân hàng hoàn trả', 'chữ số, chữ cái viết hoa, ký tự V', '176', 'Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự V)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự V
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':53D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '78', 'Chỉ dẫn cho ngân hàng thanh toán/chấp nhận/chiết khấu', 'ký tự V', '780', 'tối đa 12 dòng, mỗi dòng 65 ký tự V', NULL,3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '57a', 'Ngân hàng thông báo cho người thụ hưởng', 'chữ số, chữ cái viết hoa, ký tự X', '48', 'Có thể theo định dạng A hoặc định dạng B hoặc định dạng D nhưng không xuất hiện đồng thời 2 hoặc 3 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code', ':57D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '57a', 'Ngân hàng thông báo cho người thụ hưởng', 'chữ số, chữ cái viết hoa, ký tự V', '72', 'Định dạng B cấu trúc:
<Định danh>
<vị trí>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
' ||
                                                                                                                                                                                                                                                                    '' ||
                                                                                                                                                                                                                                                                    '(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alp02habet, xxx: tối đa 34 ký tự thuộc bộ ký tự V)
+. Phần vị trí không bắt buộc, tối đa 35 ký tự V
+. Phần định danh xuống dòng đến phần vị trí', ':57B: /V/VP BANK
HANOI, VIETNAM',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '57a', 'Ngân hàng thông báo cho người thụ hưởng', 'chữ số, chữ cái viết hoa, ký tự V', '176', 'Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự V)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự V
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':57D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',3,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '72Z', 'Sender to Receiver Information', 'ký tự V', '210', 'tối đa 6 dòng, mỗi dòng tối đa 35 ký tự V', NULL,3,1);

---MT708---
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '27', 'Điện/Tổng số điện', 'chữ số/chữ số', '3', 'Cấu trúc: n1/n2 trong đó n1, n2 là ký tự chữ số nhận giá trị từ 1 đến 8, n1 <=n2', ':27: 1/2',4,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '20', 'số tham chiếu bên gửi', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//'', phải giống số L/C trên F20 của điện MT700', ':20: 12M9087/65',4,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '21', 'số tham chiếu bên nhận', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//''', ':21: 12M9087/65',4,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '23', 'Số tham chiếu ngân hàng phát hành', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//''', ':23: 12M9087/65',4,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '26E', 'Số tu chỉnh', 'chữ số', '3', 'tối đa 3 chữ số', NULL,4,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '30', 'Ngày tu chỉnh', 'chữ số', '6', 'bắt buộc có 6 ký tự số, định dạng YYMMDD', ':30: 211109',4,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '45B', 'Mô tả hàng hóa/dịch vụ', 'ký tự V', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự V', NULL,4,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '46B', 'Chứng từ xuất trình', 'ký tự V', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự V', NULL,4,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '47B', 'Điều kiện bổ sung', 'ký tự V', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự V', NULL,4,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '49M', 'Điều khoản thanh toán đặc biệt cho bên thụ hưởng', 'ký tự V', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự V', NULL,4,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '49N', 'Điều khoản thanh toán đặc biệt cho Ngân hàng', 'ký tự V', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự V', NULL,4,1);

--- MT730----
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '20', 'số tham chiếu bên gửi', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//'', phải giống số L/C trên F20 của điện MT700', ':20: 12M9087/65',5,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '21', 'số tham chiếu bên nhận', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//''', ':21: 12M9087/65',5,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '25', 'số tài khoản', 'Ký tự X', '35', 'tối đa 35 ký tự X', NULL,5,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '30', 'Ngày nhận được thư tín dụng', 'chữ số', '6', 'bắt buộc có 6 ký tự số, định dạng YYMMDD', ':31C:211109',5,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '32a', 'Số tiền phí', 'chữ cái viết hoa, số thập phân', '18', 'Có thể theo định dạng B hoặc định dạng D nhưng không xuất hiện đồng thời 2 định dạng.
Định dạng B cấu trúc:
Cấu trúc: <Loại tiền><Số tiền>
+. Loại tiền: bắt buộc, check trong danh mục tiền tệ
+. Số tiền: bắt buộc, tối đa 15 ký tự số thập phân, phân cách thập phân bằng dấu '','', có tối đa 2 chữ số sau dấu '',''', ':32B: VND1000000
:32B: USD2000,50',5,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '32a', 'Số tiền phí', 'chữ số, chữ cái viết hoa, số thập phân', '24', 'Định dạng D Cấu trúc: <Ngày><Loại tiền><Số tiền>
+. Ngày: bắt buộc, format YYMMDD
+. Loại tiền: bắt buộc, check trong danh mục tiền tệ
+. Số tiền: bắt buộc, tối đa 15 ký tự số thập phân, phân cách thập phân bằng dấu '','', có tối đa 2 chữ số sau dấu '',''', ':32D: 211231VND1000000
:32D: 211231USD2000,50',5,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '57a', 'Bên gửi có tài khoản tại', 'chữ số, chữ cái viết hoa, ký tự X', '48', 'Có thể theo định dạng A hoặc định dạng D nhưng không xuất hiện đồng thời 2 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code', ':57A: VPBKVNVX
:57A: /V/VP BANK
VPBKVNVX',5,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '57a', 'Bên gửi có tài khoản tại', 'chữ số, chữ cái viết hoa, ký tự V', '176', 'Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự V)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự V
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':57D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',5,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '71D', 'Quy định về phí', 'ký tự V', '210', 'tối thiểu 1 dòng, tối đa 6 dòng, mỗi dòng tối đa 35 ký tự V
Cấu trúc:
+. Dòng 1: <code><loại tiền><số tiền><giải thích>
Code là bắt buộc, định dạng /HHH/. Trong đó HHH tối đa 8 ký tự chữ cái viết hoa, có thể nhận 1 hoặc nhiều giá trị sau: AGENT, COMM. CORCOM, DISC, INSUR, POST, STAMP, TELECHAR, WAREHOUS. Code để đầu mỗi dòng và để trong cặp gạch chéo.
Loại tiền: không bắt buộc, check trong danh mục tiền tệ
Số tiền: không bắt buộc, tối đa 13 ký tự số thập phân. phân cách thập phân bằng dấu '','', có 2 chữ số sau dấu '',''
Giải thích: không bắt buộc
+. Dòng 2-6: cấu trúc như dòng 1 hoặc //zzz trong đó bắt đầu bằng 2 gạch chéo //, tiếp theo là tối đa 33 ký tự V', ':71D: /AGENT/
/DISC/USD100 LATE SHIPMENT
//LATE PRESENTATION',5,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '72Z', 'Trao đổi giữa bên gửi và bên nhận', 'ký tự V', '210', 'tối đa 6 dòng, mỗi dòng 35 ký tự
+. Dòng 1: Cấu trúc <code><giải thích>
Trong đó code theo định dạng /HHH/, HHH chỉ được nhận 1 trong các giá trị BENACC hoặc BENREJ
Phần giải thích không bắt buộc
+. Dòng 2-6: cấu trúc như dòng 1 hoặc //zzz trong đó zzz là tối đa 33 ký tự V', ':72Z: /BENACC/
:72Z: /BENREJ/
//BEN DO NOT ACCEPT THE //NEW EXPIRY DATE',5,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '79Z', 'Thông tin khác', 'ký tự V', '1750', 'Tối đa 35 dòng, mỗi dòng 50 ký tự V', NULL,5,1);

-- MT734----
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '20', 'số tham chiếu bên gửi', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//'', phải giống số L/C trên F20 của điện MT700', ':20: 12M9087/65',6,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '21', 'số tham chiếu ngân hàng xuất trình', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//''', ':21: 12M9087/65',6,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '32A', 'Ngày và số tiền của giao dịch gốc', 'chữ số, chữ cái viết hoa, số thập phân', '24', 'Cấu trúc: <Ngày><Loại tiền><Số tiền>
+. Ngày: bắt buộc, format YYMMDD
+. Loại tiền: bắt buộc, check trong danh mục tiền tệ. Loại tiền tại F32A và F33a phải giống nhau.
+. Số tiền: bắt buộc, tối đa 15 ký tự số thập phân, phân cách thập phân bằng dấu '','', có tối đa 2 chữ số sau dấu '',''', ':32A: 211231USD2000,50',6,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '73A', 'Phí', 'ký tự V', '210', 'tối thiểu 1 dòng, tối đa 6 dòng, mỗi dòng tối đa 35 ký tự V
Cấu trúc:
+. Dòng 1: <code><loại tiền><số tiền><giải thích>
Code là bắt buộc, định dạng /HHH/. Trong đó HHH tối đa 8 ký tự chữ cái viết hoa. Code để đầu mỗi dòng và để trong cặp gạch chéo.
Loại tiền: không bắt buộc, check trong danh mục tiền tệ
Số tiền: không bắt buộc, tối đa 13 ký tự số thập phân. phân cách thập phân bằng dấu '','', có 2 chữ số sau dấu '',''
Giải thích: không bắt buộc
+. Dòng 2-6: cấu trúc như dòng 1 hoặc //zzz trong đó bắt đầu bằng 2 gạch chéo //, tiếp theo là tối đa 33 ký tự V', ':73A: /AGENT/
/DISC/USD100 LATE SHIPMENT
//LATE PRESENTATION',6,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '33a', 'Tổng số tiền đòi', 'chữ số, chữ cái viết hoa, số thập phân', '24', 'Khi có F73A thì F33a phải thể hiện. Loại tiền tại F32A và F33a phải giống nhau. Có thể theo định dạng A hoặc định dạng B nhưng không xuất hiện đồng thời 2 định dạng.
Định dạng A Cấu trúc: <Ngày><Loại tiền><Số tiền>
+. Ngày: bắt buộc, format YYMMDD
+. Loại tiền: bắt buộc, check trong danh mục tiền tệ
+. Số tiền: bắt buộc, tối đa 15 ký tự số thập phân, phân cách thập phân bằng dấu '','', có tối đa 2 chữ số sau dấu '',''', ':33A 211231VND1000000
:33A: 211231USD2000,50',6,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '33a', 'Tổng số tiền đòi', 'chữ cái viết hoa, số thập phân', '18', 'Định dạng B
Cấu trúc: <Loại tiền><Số tiền>
+. Loại tiền: bắt buộc, check trong danh mục tiền tệ
+. Số tiền: bắt buộc, tối đa 15 ký tự số thập phân, phân cách thập phân bằng dấu '','', có tối đa 2 chữ số sau dấu '',''', ':33B: VND100000000
:33B: USD200000,50',6,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '57a', 'Bên gửi có tài khoản tại', 'chữ số, chữ cái viết hoa, ký tự X', '48', 'Có thể theo định dạng A hoặc định dạng B hoặc định dạng D nhưng không xuất hiện đồng thời 2 hoặc 3 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code', ':57A: VPBKVNVX
:57A: /V/VP BANK
VPBKVNVX',6,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '57a', 'Bên gửi có tài khoản tại', 'chữ số, chữ cái viết hoa, ký tự V', '72', 'Định dạng B cấu trúc:
<Định danh>
<vị trí>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự V)
+. Phần vị trí không bắt buộc, tối đa 35 ký tự V
+. Phần định danh xuống dòng đến phần vị trí', ':57B: /V/VP BANK
HANOI, VIETNAM',6,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '57a', 'Bên gửi có tài khoản tại', 'chữ số, chữ cái viết hoa, ký tự V', '176', 'Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự V)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự V
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':57D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',6,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '72Z', 'Trao đổi giữa bên gửi và bên nhận', 'ký tự V', '210', 'tối đa 6 dòng, mỗi dòng 35 ký tự
+. Dòng 1: Cấu trúc <code><giải thích>
Trong đó code theo định dạng /HHH/, tối đa 8 ký tự chữ cái viết hoa hoặc chữ số bất kỳ
Phần giải thích không bắt buộc
+. Dòng 2-6: cấu trúc như dòng 1 hoặc //zzz trong đó zzz là tối đa 33 ký tự V', ':72Z: /BENACC/
:72Z: /BENREJ/
//BEN DO NOT ACCEPT THE //NEW EXPIRY DATE',6,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '77J', 'Bất hợp lệ', 'ký tự V', '3500', 'tối đa 70 dòng, mỗi dòng 50 ký tự V', NULL, 6,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '77B', 'Chỉ dẫn xử lý bộ chứng từ', 'ký tự V', '105', 'tối đa 3 dòng, mỗi dòng tối đa 35 ký tự V
+. Dòng 1: Cấu trúc <code><giải thích>
Trong đó code bắt buộc, chỉ được nhận 1 trong các giá trị sau: HOLD/NOTIFY/PREVINST/RETURN
+. Dòng 2-3: Cấu trúc //xxx trong đó bắt đầu bằng 2 gạch chéo //, tiếp theo là tối đa 33 ký tự V', ':77B:/NOTIFY/
:77B:/RETURN/
//PLEASE GIVE US YOUR BANK ADDRESS TO //RETURN THIS SET OF DOCS.',6,1);

-----MT756----
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '20', 'số tham chiếu bên gửi', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//'', phải giống số L/C trên F20 của điện MT700', ':20: 12M9087/65',7,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '21', 'số tham chiếu ngân hàng xuất trình', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//''', ':21: 12M9087/65',7,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '32B', 'Tổng số tiền đòi', 'chữ cái viết hoa, số thập phân', '18', 'Định dạng B
Cấu trúc: <Loại tiền><Số tiền>
+. Loại tiền: bắt buộc, check trong danh mục tiền tệ
+. Số tiền: bắt buộc, tối đa 15 ký tự số thập phân, phân cách thập phân bằng dấu '','', có tối đa 2 chữ số sau dấu '',''', ':32B: VND100000000
:32B: USD200000,50',7,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '33A', 'Số tiền thanh toán', 'chữ số, chữ cái viết hoa, số thập phân', '24', 'Cấu trúc: <Ngày><Loại tiền><Số tiền>
+. Ngày: bắt buộc, format YYMMDD
+. Loại tiền: bắt buộc, check trong danh mục tiền tệ
+. Số tiền: bắt buộc, tối đa 15 ký tự số thập phân, phân cách thập phân bằng dấu '','', có tối đa 2 chữ số sau dấu '',''', ':33A 211231VND1000000
:33A: 211231USD2000,50',7,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '53a', 'Ngân hàng của bên gửi', 'chữ số, chữ cái viết hoa, ký tự X', '48', 'Có thể theo định dạng A hoặc định dạng B hoặc định dạng D nhưng không xuất hiện đồng thời 2 hoặc 3 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code', ':53A: VPBKVNVX
:53A: /V/VP BANK
VPBKVNVX',7,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '53a', 'Ngân hàng của bên gửi', 'chữ số, chữ cái viết hoa, ký tự V', '72', 'Định dạng B cấu trúc:
<Định danh>
<vị trí>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự V)
+. Phần vị trí không bắt buộc, tối đa 35 ký tự V
+. Phần định danh xuống dòng đến phần vị trí', ':53B: /V/VP BANK
HANOI, VIETNAM',7,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '53a', 'Ngân hàng của bên gửi', 'chữ số, chữ cái viết hoa, ký tự V', '176', 'Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự V)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự V
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':53D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',7,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '54a', 'Ngân hàng của bên nhận', 'chữ số, chữ cái viết hoa, ký tự X', '48', 'Có thể theo định dạng A hoặc định dạng B hoặc định dạng D nhưng không xuất hiện đồng thời 2 hoặc 3 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code', ':54A: VPBKVNVX
:54A: /V/VP BANK
VPBKVNVX',7,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '54a', 'Ngân hàng của bên nhận', 'chữ số, chữ cái viết hoa, ký tự V', '72', 'Định dạng B cấu trúc:
<Định danh>
<vị trí>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự V)
+. Phần vị trí không bắt buộc, tối đa 35 ký tự V
+. Phần định danh xuống dòng đến phần vị trí', ':54B: /V/VP BANK
HANOI, VIETNAM',7,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '54a', 'Ngân hàng của bên nhận', 'chữ số, chữ cái viết hoa, ký tự V', '176', 'Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự V)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự V
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':54D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',7,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '72Z', 'Trao đổi giữa bên gửi và bên nhận', 'ký tự V', '210', 'tối đa 6 dòng, mỗi dòng 35 ký tự
+. Dòng 1: Cấu trúc <code><giải thích>
Trong đó code theo định dạng /HHH/, tối đa 8 ký tự chữ cái viết hoa hoặc chữ số bất kỳ
Phần giải thích không bắt buộc
+. Dòng 2-6: cấu trúc như dòng 1 hoặc //zzz trong đó zzz là tối đa 33 ký tự V', ':72Z: /BENACC/
:72Z: /BENREJ/
//BEN DO NOT ACCEPT THE //NEW EXPIRY DATE',7,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '79Z', 'Thông tin khác', 'ký tự V', '1750', 'Tối đa 35 dòng mỗi dòng 50 ký tự V', NULL,7,1);

--- MT799 ---
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '20', 'số tham chiếu bên gửi', 'ký tự X', '50', 'không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//''', NULL,8,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '21', 'số tham chiếu liên quan', 'ký tự X', '50', 'không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//''', NULL,8,1);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '79', 'Thông tin', 'ký tự Z', '1750', 'tối đa 35 dòng, mỗi dòng 50 ký tự Z', NULL,8,1);

-------------------------Dien nhan ----------------------------------
-- MT700 --
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('27', 'Điện/Tổng số điện', true, 'chữ số/chữ số', '3', 'Cấu trúc: n1/n2 trong đó n1, n2 là ký tự chữ số nhận giá trị từ 1 đến 8, n1 <=n2', ':27: 1/2',1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('40A', 'Loại L/C', true, 'ký tự X', '24', 'Chỉ được nhận 1 trong 2 giá trị sau: IRREVOCABLE hoặc IRREVOCABLE TRANSFERABLE. ', NULL,1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('20', 'Số L/C', true, 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//''', ':20: 12M9087/65',1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('23', 'Số tham chiếu sơ báo', false, 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//''', ':23: 12M9087/65',1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('31C', 'Ngày phát hành', true, 'chữ số', '6', 'bắt buộc có 6 ký tự số, định dạng YYMMDD', ':31C:211109',1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('40E', 'Các điều khoản áp dụng', true, 'ký tự X', '66', 'Cấu trúc: <điều khoản áp dụng></giải thích>. Điều khoản áp dụng và giải thích phân cách bởi dấu ''/''
+. Điều khoản áp dụng bắt buộc, tối đa 30 ký tự X, chỉ được nhận 1 trong các giá trị sau: EUCP LATEST VERSION, EUCPURR LATEST VERSION, OTHR, UCP LATEST VERSION, UCPURR LATEST VERSION
+. Phần giải thích không bắt buộc, tối đa 35 ký tự X (phần giải thích chỉ được xuất hiện sau code OTHR)', ':40E: UCP LATEST VERSION

:40E: OTHR/AGREEMENT NOV2021',1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('31D', 'Ngày và nơi hết hạn', true, 'chữ số, ký tự X', '35', 'Cấu trúc <ngày><địa điểm>
+. Ngày bắt buộc, bắt buộc có 6 ký tự số, định dạng YYMMDD
+. Địa điểm bắt buộc, có tối đa 29 ký tự X', ':31D: 211109VIETNAM',1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('51a', 'Ngân hàng của bên đề nghị', false, 'chữ số, chữ cái viết hoa, ký tự X', '48', 'Có thể theo định dạng A hoặc định dạng D nhưng không xuất hiện đồng thời 2 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code

Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':51A: VPBKVNVX
:51A: /V/VP BANK
VPBKVNVX
:51D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('50', 'Bên đề nghị', true, 'ký tự X', '140', 'tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X', ':50: CONG TY TNHH HOA LAN
TAN TRIEU, THANH TRI, HA NOI',1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('59', 'Bên thụ hưởng', true, 'ký tự X', '175', 'Cấu trúc:
<tài khoản>
<tên và địa chỉ>
+. Phần tài khoản không bắt buộc, định dạng /xxx. Trong đó, bắt đầu bằng ký tự ''/'', tiếp theo bởi tối đa 34 ký tự X
+. Phần tên và địa chỉ bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X
+. Phần tài khoản xuống dòng đến phần tên và địa chỉ', ':59:/1209876567
CONG TY TNHH BIEN DONG
KEANGNAM LANDMARK 72
ME TRI, NAM TU LIEM, HANOI',1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('32B', 'Loại tiền tệ/Trị giá L/C', true, 'chữ cái viết hoa, số thập phân', '18', 'Cấu trúc: <Loại tiền><Số tiền>
+. Loại tiền: bắt buộc, check trong danh mục tiền tệ
+. Số tiền: bắt buộc, tối đa 15 ký tự số thập phân, phân cách thập phân bằng dấu '','', có tối đa 2 chữ số sau dấu '',''', ':32B: VND100000000
:32B: USD200000,50',1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('39A', 'Dung sai trên số tiền (%)', false, 'chữ số', '5', 'Cấu trúc: n1/n2 trong đó n1, n2 là ký tự số có tối đa 2 chữ số', ':39A: 0/10',1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('39C', 'Số tiền bổ sung', false, 'ký tự X', '140', 'tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X', NULL,1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('41a', 'Chứng từ xuất trình tại', true, 'chữ số, chữ cái viết hoa, ký tự X', 'A: 25
D: 154','Có thể theo định dạng A hoặc định dạng D nhưng không xuất hiện đồng thời 2 định dạng.
Định dạng A cấu trúc:
<swift code>
<code>
+. Phần Swift code: bắt buộc, check trong DB
+. Phần code: bắt buộc, tối đa 14 ký tự X. code chỉ được nhận 1 trong các giá trị BY ACCEPTANCE/ BY DEF PAYMENT/ BY MIXED PYMT/ BY NEGOTIATION/ BY PAYMENT
+. Phần swift code xuống dòng đến phần code
Định dạng D cấu trúc:
<tên ngân hàng và địa chỉ>
<code>
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X
+. Phần code: bắt buộc, tối đa 14 ký tự X. code chỉ được nhận 1 trong các giá trị BY ACCEPTANCE/ BY DEF PAYMENT/ BY MIXED PYMT/ BY NEGOTIATION/ BY PAYMENT
+. Phần tên ngân hàng và địa chỉ xuống dòng đến phần code', ':41A: VPBKVNVX
BY PAYMENT
:41D: ANY BANK
BY NEGOTIATION
:41D: NGAN HANG TMCP DAU TU VA PHAT TRIEN
VIET NAM
HANOI, VIETNAM
BY MIXED PYMT',1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('42C', 'Thời hạn của hối phiếu', false, 'ký tự X', '105', 'tối thiểu 1 dòng, tối đa 3 dòng, mỗi dòng tối đa 35 ký tự X. Nếu F42C có thông tin thì phải xuất hiện cả F42a.
Bắt buộc phải có cặp trường (F42C, F42A) hoặc trường 42M hoặc trường 42P. Không được xuất hiện kết hợp bất kỳ trường nào trong những trường trên', ':42C: AT SIGHT
Có 42C thì phải có 42A, không có 42M, 42P
Có 42P thì không có 42C, 42A, 42M
Có 42M thì không có 42C, 42A, 42P',1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('42a', 'Bên bị đòi tiền', false, 'chữ số, chữ cái viết hoa, ký tự X','A: 48
D: 176','Có thể theo định dạng A hoặc định dạng D nhưng không xuất hiện đồng thời 2 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code

Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':42A: VPBKVNVX
:42A: /V/VP BANK
VPBKVNVX
:42D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('42M', 'Phương thức thanh toán hỗn hợp', false, 'ký tự X', '140', 'tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X', NULL,1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('42P', 'Chiết khấu/trả chậm', false, 'ký tự X', '140', 'tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X', NULL,1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('43P', 'Giao hàng từng phần', false, 'ký tự X', '11', 'tối đa 11 ký tự X, chỉ được nhận 1 trong các giá trị sau: ALLOWED, NOT ALLOWED, CONDITIONAL', NULL,1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('43T', 'Chuyển tải', false, 'ký tự X', '11', 'tối đa 11 ký tự X, chỉ được nhận 1 trong các giá trị sau: ALLOWED, NOT ALLOWED, CONDITIONAL', NULL,1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('44A', 'Nơi giao hàng', false, 'ký tự X', '65', 'tối đa 65 ký tự X', NULL,1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('44E', 'Cảng xuất hàng/Sân bay khởi hành', false, 'ký tự X', '65', 'tối đa 65 ký tự X', NULL,1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('44F', 'Cảng dỡ hàng/Sân bay đến', false, 'ký tự X', '65', 'tối đa 65 ký tự X', NULL,1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('44B', 'Nơi nhận hàng', false, 'ký tự X', '65', 'tối đa 65 ký tự X', NULL,1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('44C', 'Ngày giao hàng muộn nhất', false, 'chữ số', '6', 'bắt buộc có 6 ký tự chữ số, định dạng YYMMDD. Bắt buộc phải có trường 44C hoặc trường 44D nhưng không được có cả 44C và 44D', NULL,1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('44D', 'Thời gian giao hàng', false, 'ký tự X', '390', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự X', NULL,1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('45A', 'Mô tả hàng hóa/dịch vụ', false, 'ký tự Z', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự X', NULL,1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('46A', 'Chứng từ xuất trình', false, 'ký tự Z', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự X',  NULL,1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('47A', 'Điều kiện bổ sung', false, 'ký tự Z', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự X',  NULL,1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('49G', 'Điều khoản thanh toán đặc biệt cho bên thụ hưởng', false, 'ký tự Z', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự X',  NULL,1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('49H', 'Điều khoản thanh toán đặc biệt cho Ngân hàng', false, 'ký tự Z', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự X',  NULL,1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('71D', 'Phí', false, 'ký tự Z', '210', 'tối thiểu 1 dòng, tối đa 6 dòng, mỗi dòng tối đa 35 ký tự Z
Cấu trúc:
+. Dòng 1: <code><loại tiền><số tiền><giải thích>
Code là bắt buộc, định dạng /HHH/. Trong đó HHH tối đa 8 ký tự chữ cái viết hoa, có thể nhận 1 hoặc nhiều giá trị sau: AGENT, COMM. CORCOM, DISC, INSUR, POST, STAMP, TELECHAR, WAREHOUS. Code để đầu mỗi dòng và để trong cặp gạch chéo.
Loại tiền: không bắt buộc, check trong danh mục tiền tệ
Số tiền: không bắt buộc, tối đa 13 ký tự số thập phân. phân cách thập phân bằng dấu '','', có 2 chữ số sau dấu '',''
Giải thích: không bắt buộc
+. Dòng 2-6: cấu trúc như dòng 1 hoặc //zzz trong đó bắt đầu bằng 2 gạch chéo //, tiếp theo là tối đa 33 ký tự Z', ':71D: /AGENT/
/DISC/USD100 LATE SHIPMENT
//LATE PRESENTATION', 1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('48', 'Thời hạn xuất trình chứng từ (số ngày)', false, 'chữ số, ký tự X', '39', 'Cấu trúc: <số ngày></giải thích>
+. Số ngày: bắt buộc, tối đa 3 ký tự chữ số
+. Giải thích: không bắt buộc, tối đa 35 ký tự X
+. Số ngày phân cách phần giải thích bằng dấu ''/''', ':48: 21
:48: 21/SHIPMENT DATE',1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('49', 'Chỉ dẫn xác nhận', true, 'ký tự X', '7', 'bắt buộc 7 ký tự X, chỉ được nhận 1 trong các giá trị sau: CONFIRM, MAY ADD, WITHOUT',  NULL,1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('58a', 'Yêu cầu ngân hàng xác nhận', false, 'chữ số, chữ cái viết hoa, ký tự X', 'A: 48
D: 176', 'Trường này bắt buộc khi F49 nhận các giá trị: CONFIRM hoặc MAY ADD.
Có thể theo định dạng A hoặc định dạng D nhưng không xuất hiện đồng thời 2 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code
Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':58A: VPBKVNVX
:58A: /V/VP BANK
VPBKVNVX
:58D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('53a', 'Ngân hàng hoàn trả', false, 'chữ số, chữ cái viết hoa, ký tự X', 'A: 48
D: 176','Có thể theo định dạng A hoặc định dạng D nhưng không xuất hiện đồng thời 2 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code

Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':53A: VPBKVNVX
:53A: /V/VP BANK
VPBKVNVX
:53D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('78', 'Chỉ dẫn cho ngân hàng thanh toán/chấp nhận/chiết khấu', false, 'ký tự X', '780', 'tối đa 12 dòng, mỗi dòng 65 ký tự X', NULL,1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('57a', 'Ngân hàng thông báo cho người thụ hưởng', false, 'chữ số, chữ cái viết hoa, ký tự X', 'A: 48
B: 72
D: 176','Có thể theo định dạng A hoặc định dạng B hoặc định dạng D nhưng không xuất hiện đồng thời 2 hoặc 3 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code

Định dạng B cấu trúc:
<Định danh>
<vị trí>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần vị trí không bắt buộc, tối đa 35 ký tự X
+. Phần định danh xuống dòng đến phần vị trí

Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':57A: VPBKVNVX
:57A: /V/VP BANK
VPBKVNVX
:57B: /V/VP BANK
HANOI, VIETNAM
:57D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',1,2);
INSERT INTO public.character_set_electric_type (field_number, field_name, obligatory, format, max_length, description, sample, electric_type_id,"type") VALUES ('72Z', 'Thông tin khác', false, 'ký tự Z', '210', 'tối đa 6 dòng, mỗi dòng tối đa 35 ký tự Z', NULL,1,2);

--- MT701 ---
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '27', 'Điện/Tổng số điện', 'chữ số/chữ số', '3', 'Cấu trúc: n1/n2 trong đó n1, n2 là ký tự chữ số nhận giá trị từ 1 đến 8, n1 <=n2', ':27: 1/2',2,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '20', 'Số L/C', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//''', ':20: 12M9087/65',2,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '45A', 'Mô tả hàng hóa/dịch vụ', 'ký tự Z', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự X', NULL,2,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '46A', 'Chứng từ xuất trình', 'ký tự Z', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự X', NULL,2,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '47A', 'Điều kiện bổ sung', 'ký tự Z', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự X', NULL,2,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '49G', 'Điều khoản thanh toán đặc biệt cho bên thụ hưởng', 'ký tự Z', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự X', NULL,2,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '49H', 'Điều khoản thanh toán đặc biệt cho Ngân hàng', 'ký tự Z', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự X',NULL,2,2);

--- MT707-----
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '27', 'Điện/Tổng số điện', 'chữ số/chữ số', '3', 'Cấu trúc: n1/n2 trong đó n1, n2 là ký tự chữ số nhận giá trị từ 1 đến 8, n1 <=n2', ':27: 1/2',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '20', 'số tham chiếu bên gửi', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//'', phải giống số L/C trên F20 của điện MT700', ':20: 12M9087/65',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '21', 'số tham chiếu bên nhận', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//''', ':21: 12M9087/65',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '23', 'Số tham chiếu ngân hàng phát hành', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//''', ':23: 12M9087/65',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '52a', 'Ngân hàng phát hành', 'chữ số, chữ cái viết hoa, ký tự X', '48', 'Có thể theo định dạng A hoặc định dạng D nhưng không xuất hiện đồng thời 2 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code', ':52A: VPBKVNVX
:52A: /V/VP BANK
VPBKVNVX',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '52a', 'Ngân hàng phát hành', 'chữ số, chữ cái viết hoa, ký tự X', '176', 'Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':52D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '50B', 'Bên phát hành', 'ký tự X', '140', 'tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X', NULL,3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '31C', 'Ngày phát hành', 'chữ số', '6', 'bắt buộc có 6 ký tự số, định dạng YYMMDD', ':31C: 211109',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '26E', 'Số tu chỉnh', 'chữ số', '3', 'tối đa 3 chữ số', NULL,3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '30', 'Ngày tu chỉnh', 'chữ số', '6', 'bắt buộc có 6 ký tự số, định dạng YYMMDD', ':30: 211109',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '22A', 'Mục đích của tin điện', 'chữ cái viết hoa, chữ số', '4', 'chỉ được nhận 1 trong các giá trị sau: ANCF, ADVI, ISSU', NULL,3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '23S', 'Đề nghị hủy L/C', 'chữ cái viết hoa', '6', 'chỉ được nhận giá trị sau: CANCEL', NULL,3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '40A', 'Loại L/C', 'ký tự X', '24', 'Chỉ được nhận 1 trong 2 giá trị sau: IRREVOCABLE hoặc IRREVOCABLE TRANSFERABLE. ', NULL,3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '40E', 'Các điều khoản áp dụng', 'ký tự X', '66', 'Cấu trúc: <điều khoản áp dụng></giải thích>. Điều khoản áp dụng và giải thích phân cách bởi dấu ''/''
+. Điều khoản áp dụng bắt buộc, tối đa 30 ký tự X, chỉ được nhận 1 trong các giá trị sau: EUCP LATEST VERSION, EUCPURR LATEST VERSION, OTHR, UCP LATEST VERSION, UCPURR LATEST VERSION
+. Phần giải thích không bắt buộc, tối đa 35 ký tự X (phần giải thích chỉ được xuất hiện sau code OTHR)', ':40E: UCP LATEST VERSION

:40E: OTHR/AGREEMENT NOV2021',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '31D', 'Ngày và nơi hết hạn', 'chữ số, ký tự X', '35', 'Cấu trúc <ngày><địa điểm>
+. Ngày bắt buộc, bắt buộc có 6 ký tự số, định dạng YYMMDD
+. Địa điểm bắt buộc, có tối đa 29 ký tự X', ':31D: 211109VIETNAM',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '50', 'Thay đổi chi tiết bên đề nghị', 'ký tự X', '140', 'tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X', ':50: CONG TY TNHH HOA LAN
TAN TRIEU, THANH TRI, HA NOI',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '59', 'Bên thụ hưởng', 'ký tự X', '175', 'Cấu trúc:
<tài khoản>
<tên và địa chỉ>
+. Phần tài khoản không bắt buộc, định dạng /xxx. Trong đó, bắt đầu bằng ký tự ''/'', tiếp theo bởi tối đa 34 ký tự X
+. Phần tên và địa chỉ bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X
+. Phần tài khoản xuống dòng đến phần tên và địa chỉ', ':59:/1209876567
CONG TY TNHH BIEN DONG
KEANGNAM LANDMARK 72
ME TRI, NAM TU LIEM, HANOI',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '32B', 'Tăng giá trị', 'chữ cái viết hoa, số thập phân', '18', 'Cấu trúc: <Loại tiền><Số tiền>
+. Loại tiền: bắt buộc, check trong danh mục tiền tệ
+. Số tiền: bắt buộc, tối đa 15 ký tự số thập phân, phân cách thập phân bằng dấu '','', có tối đa 2 chữ số sau dấu '',''', ':32B: VND100000000
:32B: USD200000,50',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '33B', 'Giảm giá trại', 'chữ cái viết hoa, số thập phân', '18', 'Cấu trúc: <Loại tiền><Số tiền>
+. Loại tiền: bắt buộc, check trong danh mục tiền tệ
+. Số tiền: bắt buộc, tối đa 15 ký tự số thập phân, phân cách thập phân bằng dấu '','', có tối đa 2 chữ số sau dấu '',''', ':33B: VND100000000
:33B: USD200000,50',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '39A', 'Dung sai (%)', 'chữ số', '5', 'Cấu trúc: n1/n2 trong đó n1, n2 là ký tự số có tối đa 2 chữ số', ':39A: 0/10',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '39C', 'Số tiền bổ sung', 'ký tự X', '140', 'tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X', NULL,3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '41a', 'Chứng từ xuất trình tại', 'chữ số, chữ cái viết hoa, ký tự X', '25','Có thể theo định dạng A hoặc định dạng D nhưng không xuất hiện đồng thời 2 định dạng.
Định dạng A cấu trúc:
<swift code>
<code>
+. Phần Swift code: bắt buộc, check trong DB
+. Phần code: bắt buộc, tối đa 14 ký tự X. code chỉ được nhận 1 trong các giá trị BY ACCEPTANCE/ BY DEF PAYMENT/ BY MIXED PYMT/ BY NEGOTIATION/ BY PAYMENT
+. Phần swift code xuống dòng đến phần code', ':41A: VPBKVNVX
BY PAYMENT',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '41a', 'Chứng từ xuất trình tại', 'ký tự X', '154', 'Định dạng D cấu trúc:
<tên ngân hàng và địa chỉ>
<code>
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X
+. Phần code: bắt buộc, tối đa 14 ký tự X. code chỉ được nhận 1 trong các giá trị BY ACCEPTANCE/ BY DEF PAYMENT/ BY MIXED PYMT/ BY NEGOTIATION/ BY PAYMENT
+. Phần tên ngân hàng và địa chỉ xuống dòng đến phần code', ':41D: ANY BANK
BY NEGOTIATION
:41D: NGAN HANG TMCP DAU TU VA PHAT TRIEN
VIET NAM
HANOI, VIETNAM
BY MIXED PYMT',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '42C', 'Thời hạn của hối phiếu', 'ký tự X', '105', 'tối thiểu 1 dòng, tối đa 3 dòng, mỗi dòng tối đa 35 ký tự X. Nếu F42C có thông tin thì phải xuất hiện cả F42a.
Bắt buộc phải có cặp trường (F42C, F42A) hoặc trường 42M hoặc trường 42P. Không được xuất hiện kết hợp bất kỳ trường nào trong những trường trên', ':42C: AT SIGHT
Có 42C thì phải có 42A, không có 42M, 42P
Có 42P thì không có 42C, 42A, 42M
Có 42M thì không có 42C, 42A, 42P',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '42a', 'Bên bị đòi tiền', 'chữ số, chữ cái viết hoa, ký tự X', '48', 'Có thể theo định dạng A hoặc định dạng D nhưng không xuất hiện đồng thời 2 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code', ':42A: VPBKVNVX
:42A: /V/VP BANK
VPBKVNVX',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '42a', 'Bên bị đòi tiền', 'chữ số, chữ cái viết hoa, ký tự X', '176', 'Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':42D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '42M', 'Phương thức thanh toán hỗn hợp', 'ký tự X', '140', 'tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X', NULL,3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '42P', 'Chiết khấu/trả chậm', 'ký tự X', '140', 'tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X', NULL,3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '43P', 'Giao hàng từng phần', 'ký tự X', '11', 'tối đa 11 ký tự X, chỉ được nhận 1 trong các giá trị sau: ALLOWED, NOT ALLOWED, CONDITIONAL', NULL,3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '43T', 'Chuyển tải', 'ký tự X', '11', 'tối đa 11 ký tự X, chỉ được nhận 1 trong các giá trị sau: ALLOWED, NOT ALLOWED, CONDITIONAL', NULL,3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '44A', 'Nơi giao hàng', 'ký tự X', '65', 'tối đa 65 ký tự X', NULL,3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '44E', 'Cảng xuất hàng/Sân bay khởi hành', 'ký tự X', '65', 'tối đa 65 ký tự X', NULL,3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '44F', 'Cảng dỡ hàng/Sân bay đến', 'ký tự X', '65', 'tối đa 65 ký tự X', NULL,3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '44B', 'Nơi nhận hàng', 'ký tự X', '65', 'tối đa 65 ký tự X', NULL,3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '44C', 'Ngày giao hàng muộn nhất', 'chữ số', '6', 'bắt buộc có 6 ký tự chữ số, định dạng YYMMDD. Bắt buộc phải có trường 44C hoặc trường 44D nhưng không được có cả 44C và 44D', NULL,3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '44D', 'Thời gian giao hàng', 'ký tự X', '390', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự X', NULL,3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '45B', 'Mô tả hàng hóa/dịch vụ', 'ký tự Z', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự X', NULL,3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '46B', 'Chứng từ xuất trình', 'ký tự Z', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự X', NULL,3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '47B', 'Điều kiện bổ sung', 'ký tự Z', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự X', NULL,3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '49M', 'Điều khoản thanh toán đặc biệt cho bên thụ hưởng', 'ký tự Z', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự X', NULL,3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '49N', 'Điều khoản thanh toán đặc biệt cho Ngân hàng', 'ký tự Z', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự X', NULL,3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '71D', 'Phí', 'ký tự Z', '210', 'tối thiểu 1 dòng, tối đa 6 dòng, mỗi dòng tối đa 35 ký tự Z
Cấu trúc:
+. Dòng 1: <code><loại tiền><số tiền><giải thích>
Code là bắt buộc, định dạng /HHH/. Trong đó HHH tối đa 8 ký tự chữ cái viết hoa, có thể nhận 1 hoặc nhiều giá trị sau: AGENT, COMM. CORCOM, DISC, INSUR, POST, STAMP, TELECHAR, WAREHOUS. Code để đầu mỗi dòng và để trong cặp gạch chéo.
Loại tiền: không bắt buộc, check trong danh mục tiền tệ
Số tiền: không bắt buộc, tối đa 13 ký tự số thập phân. phân cách thập phân bằng dấu '','', có 2 chữ số sau dấu '',''
Giải thích: không bắt buộc
+. Dòng 2-6: cấu trúc như dòng 1 hoặc //zzz trong đó bắt đầu bằng 2 gạch chéo //, tiếp theo là tối đa 33 ký tự Z', ':71D: /AGENT/
/DISC/USD100 LATE SHIPMENT
//LATE PRESENTATION',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '71N', 'Phí tu chỉnh', 'ký tự Z', '214', 'Cấu trúc:
<code>
<giải thích>
+. Code bắt buộc, tối đa 4 ký tự chữ cái viết hoa, chỉ được nhận 1 trong các giá trị sau: APPL, BENE, OTHR.
+. Giải thích không bắt buộc, chỉ được sử dụng kèm code OTHR, tối đa 6 dòng mỗi dòng 35 ký tự Z', ':71N: APPL
:71N: OTHR
OUR AMENDMENT CHARGE USD70 FOR APPLICANT ACCOUNT. OTHER FEE RELATED TO THIS AMENDMENT FOR BENEFICIARY ACCOUNT.',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '48', 'Thời hạn xuất trình chứng từ (số ngày)', 'chữ số, ký tự X', '39', 'Cấu trúc: <số ngày></giải thích>
+. Số ngày: bắt buộc, tối đa 3 ký tự chữ số
+. Giải thích: không bắt buộc, tối đa 35 ký tự X
+. Số ngày phân cách phần giải thích bằng dấu ''/''', ':48: 21
:48: 21/SHIPMENT DATE',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '49', 'Chỉ dẫn xác nhận', 'ký tự X', '7', 'bắt buộc 7 ký tự X, chỉ được nhận 1 trong các giá trị sau: CONFIRM, MAY ADD, WITHOUT', NULL,3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '58a', 'Yêu cầu ngân hàng xác nhận', 'chữ số, chữ cái viết hoa, ký tự X', '48', 'Trường này bắt buộc khi F49 nhận các giá trị: CONFIRM hoặc MAY ADD.
Có thể theo định dạng A hoặc định dạng D nhưng không xuất hiện đồng thời 2 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code', ':58A: VPBKVNVX
:58A: /V/VP BANK
VPBKVNVX',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '58a', 'Yêu cầu ngân hàng xác nhận', 'chữ số, chữ cái viết hoa, ký tự X', '176', 'Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':58D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '53a', 'Ngân hàng hoàn trả', 'chữ số, chữ cái viết hoa, ký tự X', '48','Có thể theo định dạng A hoặc định dạng D nhưng không xuất hiện đồng thời 2 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code', ':53A: VPBKVNVX
:53A: /V/VP BANK
VPBKVNVX',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '53a', 'Ngân hàng hoàn trả', 'chữ số, chữ cái viết hoa, ký tự X', '176', 'Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':53D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '78', 'Chỉ dẫn cho ngân hàng thanh toán/chấp nhận/chiết khấu', 'ký tự X', '780', 'tối đa 12 dòng, mỗi dòng 65 ký tự X', NULL,3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '57a', 'Ngân hàng thông báo cho người thụ hưởng', 'chữ số, chữ cái viết hoa, ký tự X', '48', 'Có thể theo định dạng A hoặc định dạng B hoặc định dạng D nhưng không xuất hiện đồng thời 2 hoặc 3 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code', ':57A: VPBKVNVX
:57A: /V/VP BANK
VPBKVNVX',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '57a', 'Ngân hàng thông báo cho người thụ hưởng', '', '72', 'Định dạng B cấu trúc:
<Định danh>
<vị trí>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần vị trí không bắt buộc, tối đa 35 ký tự X
+. Phần định danh xuống dòng đến phần vị tr', ':57B: /V/VP BANK
HANOI, VIETNAM',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '57a', 'Ngân hàng thông báo cho người thụ hưởng', 'chữ số, chữ cái viết hoa, ký tự X', '176', 'Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':57D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',3,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '72Z', 'Sender to Receiver Information', 'ký tự Z', '210', 'tối đa 6 dòng, mỗi dòng tối đa 35 ký tự Z',NULL,3,2);

--- MT708----
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '27', 'Điện/Tổng số điện', 'chữ số/chữ số', '3', 'Cấu trúc: n1/n2 trong đó n1, n2 là ký tự chữ số nhận giá trị từ 1 đến 8, n1 <=n2', ':27: 1/2',4,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '20', 'số tham chiếu bên gửi', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//'', phải giống số L/C trên F20 của điện MT700', ':20: 12M9087/65',4,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '21', 'số tham chiếu bên nhận', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//''', ':21: 12M9087/65',4,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '23', 'Số tham chiếu ngân hàng phát hành', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//''', ':23: 12M9087/65',4,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '26E', 'Số tu chỉnh', 'chữ số', '3', 'tối đa 3 chữ số', NULL,4,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '30', 'Ngày tu chỉnh', 'chữ số', '6', 'bắt buộc có 6 ký tự số, định dạng YYMMDD', ':30: 211109',4,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '45B', 'Mô tả hàng hóa/dịch vụ', 'ký tự Z', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự X', NULL,4,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '46B', 'Chứng từ xuất trình', 'ký tự Z', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự X', NULL,4,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '47B', 'Điều kiện bổ sung', 'ký tự Z', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự X', NULL,4,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '49M', 'Điều khoản thanh toán đặc biệt cho bên thụ hưởng', 'ký tự Z', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự X', NULL,4,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '49N', 'Điều khoản thanh toán đặc biệt cho Ngân hàng', 'ký tự Z', '650', 'tối đa 6 dòng, mỗi dòng tối đa 65 ký tự X',NULL,4,2);

----MT730----
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '20', 'số tham chiếu bên gửi', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//'', phải giống số L/C trên F20 của điện MT700', ':20: 12M9087/65',5,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '21', 'số tham chiếu bên nhận', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//''', ':21: 12M9087/65',5,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '25', 'số tài khoản', 'Ký tự X', '35', 'tối đa 35 ký tự X', NULL,5,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '30', 'Ngày nhận được thư tín dụng', 'chữ số', '6', 'bắt buộc có 6 ký tự số, định dạng YYMMDD', ':31C:211109',5,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '32a', 'Số tiền phí', 'chữ cái viết hoa, số thập phân', '18', 'Có thể theo định dạng B hoặc định dạng D nhưng không xuất hiện đồng thời 2 định dạng.
Định dạng B cấu trúc:
Cấu trúc: <Loại tiền><Số tiền>
+. Loại tiền: bắt buộc, check trong danh mục tiền tệ
+. Số tiền: bắt buộc, tối đa 15 ký tự số thập phân, phân cách thập phân bằng dấu '','', có tối đa 2 chữ số sau dấu '',''', ':32B: VND1000000
:32B: USD2000,50',5,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '32a', 'Số tiền phí', 'chữ số, chữ cái viết hoa, số thập phân', '24', 'Định dạng D Cấu trúc: <Ngày><Loại tiền><Số tiền>
+. Ngày: bắt buộc, format YYMMDD
+. Loại tiền: bắt buộc, check trong danh mục tiền tệ
+. Số tiền: bắt buộc, tối đa 15 ký tự số thập phân, phân cách thập phân bằng dấu '','', có tối đa 2 chữ số sau dấu '',''', ':32D: 211231VND1000000
:32D: 211231USD2000,50',5,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '57a', 'Bên gửi có tài khoản tại', 'chữ số, chữ cái viết hoa, ký tự X', '48', 'Có thể theo định dạng A hoặc định dạng D nhưng không xuất hiện đồng thời 2 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code', ':57A: VPBKVNVX
:57A: /V/VP BANK
VPBKVNVX',5,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '57a', 'Bên gửi có tài khoản tại', 'chữ số, chữ cái viết hoa, ký tự X', '176', 'Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':57D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',5,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '71D', 'Quy định về phí', 'ký tự Z', '210', 'tối thiểu 1 dòng, tối đa 6 dòng, mỗi dòng tối đa 35 ký tự Z
Cấu trúc:
+. Dòng 1: <code><loại tiền><số tiền><giải thích>
Code là bắt buộc, định dạng /HHH/. Trong đó HHH tối đa 8 ký tự chữ cái viết hoa, có thể nhận 1 hoặc nhiều giá trị sau: AGENT, COMM. CORCOM, DISC, INSUR, POST, STAMP, TELECHAR, WAREHOUS. Code để đầu mỗi dòng và để trong cặp gạch chéo.
Loại tiền: không bắt buộc, check trong danh mục tiền tệ
Số tiền: không bắt buộc, tối đa 13 ký tự số thập phân. phân cách thập phân bằng dấu '','', có 2 chữ số sau dấu '',''
Giải thích: không bắt buộc
+. Dòng 2-6: cấu trúc như dòng 1 hoặc //zzz trong đó bắt đầu bằng 2 gạch chéo //, tiếp theo là tối đa 33 ký tự Z', ':71D: /AGENT/
/DISC/USD100 LATE SHIPMENT
//LATE PRESENTATION',5,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '72Z', 'Trao đổi giữa bên gửi và bên nhận', 'ký tự Z', '210', 'tối đa 6 dòng, mỗi dòng 35 ký tự
+. Dòng 1: Cấu trúc <code><giải thích>
Trong đó code theo định dạng /HHH/, HHH chỉ được nhận 1 trong các giá trị BENACC hoặc BENREJ
Phần giải thích không bắt buộc
+. Dòng 2-6: cấu trúc như dòng 1 hoặc //zzz trong đó zzz là tối đa 33 ký tự Z', ':72Z: /BENACC/
:72Z: /BENREJ/
//BEN DO NOT ACCEPT THE //NEW EXPIRY DATE',5,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '79Z', 'Thông tin khác', 'ký tự Z', '1750', 'Tối đa 35 dòng, mỗi dòng 50 ký tự Z', NULL,5,2);

---- MT734 --------
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '20', 'số tham chiếu bên gửi', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//'', phải giống số L/C trên F20 của điện MT700', ':20: 12M9087/65',6,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '21', 'số tham chiếu ngân hàng xuất trình', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//''', ':21: 12M9087/65',6,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '32A', 'Ngày và số tiền của giao dịch gốc', 'chữ số, chữ cái viết hoa, số thập phân', '24', 'Cấu trúc: <Ngày><Loại tiền><Số tiền>
+. Ngày: bắt buộc, format YYMMDD
+. Loại tiền: bắt buộc, check trong danh mục tiền tệ. Loại tiền tại F32A và F33a phải giống nhau.
+. Số tiền: bắt buộc, tối đa 15 ký tự số thập phân, phân cách thập phân bằng dấu '','', có tối đa 2 chữ số sau dấu '',''', ':32A: 211231USD2000,50',6,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '73A', 'Phí', 'ký tự Z', '210', 'tối thiểu 1 dòng, tối đa 6 dòng, mỗi dòng tối đa 35 ký tự Z
Cấu trúc:
+. Dòng 1: <code><loại tiền><số tiền><giải thích>
Code là bắt buộc, định dạng /HHH/. Trong đó HHH tối đa 8 ký tự chữ cái viết hoa. Code để đầu mỗi dòng và để trong cặp gạch chéo.
Loại tiền: không bắt buộc, check trong danh mục tiền tệ
Số tiền: không bắt buộc, tối đa 13 ký tự số thập phân. phân cách thập phân bằng dấu '','', có 2 chữ số sau dấu '',''
Giải thích: không bắt buộc
+. Dòng 2-6: cấu trúc như dòng 1 hoặc //zzz trong đó bắt đầu bằng 2 gạch chéo //, tiếp theo là tối đa 33 ký tự Z', ':73A: /AGENT/
/DISC/USD100 LATE SHIPMENT
//LATE PRESENTATION',6,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '33a', 'Tổng số tiền đòi', 'chữ số, chữ cái viết hoa, số thập phân', '24', 'Khi có F73A thì F33a phải thể hiện. Loại tiền tại F32A và F33a phải giống nhau. Có thể theo định dạng A hoặc định dạng B nhưng không xuất hiện đồng thời 2 định dạng.
Định dạng A Cấu trúc: <Ngày><Loại tiền><Số tiền>
+. Ngày: bắt buộc, format YYMMDD
+. Loại tiền: bắt buộc, check trong danh mục tiền tệ
+. Số tiền: bắt buộc, tối đa 15 ký tự số thập phân, phân cách thập phân bằng dấu '','', có tối đa 2 chữ số sau dấu '',''', ':33A 211231VND1000000
:33A: 211231USD2000,50',6,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '33a', 'Tổng số tiền đòi', 'chữ cái viết hoa, số thập phân', '18', 'Định dạng B
Cấu trúc: <Loại tiền><Số tiền>
+. Loại tiền: bắt buộc, check trong danh mục tiền tệ
+. Số tiền: bắt buộc, tối đa 15 ký tự số thập phân, phân cách thập phân bằng dấu '','', có tối đa 2 chữ số sau dấu '',''', ':33B: VND100000000
:33B: USD200000,50',6,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '57a', 'Bên gửi có tài khoản tại', 'chữ số, chữ cái viết hoa, ký tự X', '48', 'Có thể theo định dạng A hoặc định dạng B hoặc định dạng D nhưng không xuất hiện đồng thời 2 hoặc 3 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code', ':57A: VPBKVNVX
:57A: /V/VP BANK
VPBKVNVX',6,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '57a', 'Bên gửi có tài khoản tại', 'ký tự X', '72', 'Định dạng B cấu trúc:
<Định danh>
<vị trí>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần vị trí không bắt buộc, tối đa 35 ký tự X
+. Phần định danh xuống dòng đến phần vị trí', ':57B: /V/VP BANK
HANOI, VIETNAM',6,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '57a', 'Bên gửi có tài khoản tại', 'chữ số, chữ cái viết hoa, ký tự X', '176', 'Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':57D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',6,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '72Z', 'Trao đổi giữa bên gửi và bên nhận', 'ký tự Z', '210', 'tối đa 6 dòng, mỗi dòng 35 ký tự
+. Dòng 1: Cấu trúc <code><giải thích>
Trong đó code theo định dạng /HHH/, tối đa 8 ký tự chữ cái viết hoa hoặc chữ số bất kỳ
Phần giải thích không bắt buộc
+. Dòng 2-6: cấu trúc như dòng 1 hoặc //zzz trong đó zzz là tối đa 33 ký tự Z', ':72Z: /BENACC/
:72Z: /BENREJ/
//BEN DO NOT ACCEPT THE //NEW EXPIRY DATE',6,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '77J', 'Bất hợp lệ', 'ký tự Z', '3500', 'tối đa 70 dòng, mỗi dòng 50 ký tự Z', NULL,6,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '77B', 'Chỉ dẫn xử lý bộ chứng từ', 'ký tự X', '105', 'tối đa 3 dòng, mỗi dòng tối đa 35 ký tự X
+. Dòng 1: Cấu trúc <code><giải thích>
Trong đó code bắt buộc, chỉ được nhận 1 trong các giá trị sau: HOLD/NOTIFY/PREVINST/RETURN
+. Dòng 2-3: Cấu trúc //xxx trong đó bắt đầu bằng 2 gạch chéo //, tiếp theo là tối đa 33 ký tự X', ':77B:/NOTIFY/
:77B:/RETURN/
//PLEASE GIVE US YOUR BANK ADDRESS TO //RETURN THIS SET OF DOCS.',6,2);

--MT756---
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '20', 'số tham chiếu bên gửi', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//'', phải giống số L/C trên F20 của điện MT700', ':20: 12M9087/65',7,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '21', 'số tham chiếu ngân hàng xuất trình', 'ký tự X', '16', 'tối đa 16 ký tự X, không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//''', ':21: 12M9087/65',7,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '32B', 'Tổng số tiền đòi', 'chữ cái viết hoa, số thập phân', '18', 'Định dạng B
Cấu trúc: <Loại tiền><Số tiền>
+. Loại tiền: bắt buộc, check trong danh mục tiền tệ
+. Số tiền: bắt buộc, tối đa 15 ký tự số thập phân, phân cách thập phân bằng dấu '','', có tối đa 2 chữ số sau dấu '',''', ':32B: VND100000000
:32B: USD200000,50',7,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '33A', 'Số tiền thanh toán', 'chữ số, chữ cái viết hoa, số thập phân', '24', 'Cấu trúc: <Ngày><Loại tiền><Số tiền>
+. Ngày: bắt buộc, format YYMMDD
+. Loại tiền: bắt buộc, check trong danh mục tiền tệ
+. Số tiền: bắt buộc, tối đa 15 ký tự số thập phân, phân cách thập phân bằng dấu '','', có tối đa 2 chữ số sau dấu '',''', ':33A 211231VND1000000
:33A: 211231USD2000,50',7,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '53a', 'Ngân hàng của bên gửi', 'chữ số, chữ cái viết hoa, ký tự X', '48', 'Có thể theo định dạng A hoặc định dạng B hoặc định dạng D nhưng không xuất hiện đồng thời 2 hoặc 3 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code', ':53A: VPBKVNVX
:53A: /V/VP BANK
VPBKVNVX',7,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '53a', 'Ngân hàng của bên gửi', 'chữ số, chữ cái viết hoa, ký tự X', '72', 'Định dạng B cấu trúc:
<Định danh>
<vị trí>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần vị trí không bắt buộc, tối đa 35 ký tự X
+. Phần định danh xuống dòng đến phần vị trí', ':53B: /V/VP BANK
HANOI, VIETNAM',7,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '53a', 'Ngân hàng của bên gửi', 'chữ số, chữ cái viết hoa, ký tự X', '176', 'Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':53D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',7,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '54a', 'Ngân hàng của bên nhận', 'chữ số, chữ cái viết hoa, ký tự X', '48', 'Có thể theo định dạng A hoặc định dạng B hoặc định dạng D nhưng không xuất hiện đồng thời 2 hoặc 3 định dạng.
Định dạng A cấu trúc:
<định danh>
<swift code>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần Swift code bắt buộc, check trong DB
+. Phần định danh xuống dòng đến phần Swift code', ':54A: VPBKVNVX
:54A: /V/VP BANK
VPBKVNVX',7,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '54a', 'Ngân hàng của bên nhận', 'chữ số, chữ cái viết hoa, ký tự X', '72', 'Định dạng B cấu trúc:
<Định danh>
<vị trí>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần vị trí không bắt buộc, tối đa 35 ký tự X
+. Phần định danh xuống dòng đến phần vị trí', ':54B: /V/VP BANK
HANOI, VIETNAM',7,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '54a', 'Ngân hàng của bên nhận', 'chữ số, chữ cái viết hoa, ký tự X', '176', 'Định dạng D cấu trúc:
<định danh>
<tên ngân hàng và địa chỉ>
+. Phần định danh không bắt buộc, tối đa 37 ký tự theo định dạng /H/xxx
(H là 1 ký tự chữ cái viết hoa bất kỳ trong bảng chữ cái alphabet, xxx: tối đa 34 ký tự thuộc bộ ký tự X)
+. Phần tên ngân hàng và địa chỉ: bắt buộc, tối thiểu 1 dòng, tối đa 4 dòng, mỗi dòng tối đa 35 ký tự X
+. Phần định danh xuống dòng đến phần tên ngân hàng và địa chỉ', ':54D: /V/VP BANK
NGAN HANG TMCP VIET NAM THINH VUONG
HANOI, VIETNAM',7,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '72Z', 'Trao đổi giữa bên gửi và bên nhận', 'ký tự Z', '210', 'tối đa 6 dòng, mỗi dòng 35 ký tự
+. Dòng 1: Cấu trúc <code><giải thích>
Trong đó code theo định dạng /HHH/, tối đa 8 ký tự chữ cái viết hoa hoặc chữ số bất kỳ
Phần giải thích không bắt buộc
+. Dòng 2-6: cấu trúc như dòng 1 hoặc //zzz trong đó zzz là tối đa 33 ký tự Z', ':72Z: /BENACC/
:72Z: /BENREJ/
//BEN DO NOT ACCEPT THE //NEW EXPIRY DATE',7,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '79Z', 'Thông tin khác', 'ký tự Z', '1750', 'Tối đa 35 dòng mỗi dòng 50 ký tự Z',NULL,7,2);

---MT799----
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '20', 'số tham chiếu bên gửi', 'ký tự X', '16', 'không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//''', NULL,8,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (false, '21', 'số tham chiếu liên quan', 'ký tự X', '16', 'không được bắt đầu hoặc kết thúc bằng ký tự gạch chéo ''/'', không được có 2 ký tự gạch chéo liên tiếp ''//''', NULL,8,2);
INSERT INTO public.character_set_electric_type (obligatory, field_number, field_name, format, max_length, description, sample, electric_type_id,"type") VALUES (true, '79', 'Thông tin', 'ký tự X', '1750', 'tối đa 35 dòng, mỗi dòng 50 ký tự X', NULL, 8, 2);
