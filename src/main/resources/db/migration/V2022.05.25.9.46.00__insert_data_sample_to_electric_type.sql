---- electric_type -----
INSERT INTO public.electric_type (electric_type,electric_name,proposal,swift_version)
VALUES('MT700','Phát hành thư tín dụng','Chỉ ra các điều khoản, điều kiện của thư tín dụng', 'SWIFT release November 2021'),
      ('MT701','Phát hành thư tín dụng','Tiếp tục điện MT 700 cho các trường 45a, 46a, and 47a', 'SWIFT release November 2021'),
      ('MT707','Tu chỉnh thư tín dụng','Chỉ ra các điều khoản, điều kiện tu chỉnh của thư tín dụng', 'SWIFT release November 2021'),
      ('MT708','Tu chỉnh thư tín dụng','Tiếp tục điện MT707', 'SWIFT release November 2021'),
      ('MT730','Báo nhận được thư tín dụng','Thông báo đã nhận được thư tín dụng và có thể chỉ ra thư tín dụng đã được chuyển tiếp như chỉ dẫn, các phí (nếu có) hoặc thông báo chấp nhận/từ chối tu chỉnh thư tín dụng', 'SWIFT release November 2021'),
      ('MT734','Thông báo từ chối bộ chứng từ','Thông báo từ chối bộ chứng từ không hợp lệ', 'SWIFT release November 2021'),
      ('MT756','Thông báo thanh toán bộ chứng từ ','Thông báo khoản hoàn trả/thanh toán theo thư tín dụng (chấp nhận thanh toán)', 'SWIFT release November 2021'),
      ('MT799','Điện tự do','Cung cấp thông tin không được quy định trong các mẫu điện khác', 'SWIFT release November 2021');

--- character_set  ------
INSERT INTO public.character_set (character_set,applicable_characters,note)
VALUES('Chữ số','0 1 2 3 4 5 6 7 8 9', NULL),
      ('Chữ cái viết hoa','A B C D E F G H I J K L M N O P Q R S T U V W X Y Z', NULL),
      ('Số thập phân','0 1 2 3 4 5 6 7 8 9 và dấu , phân cách thập phân', NULL),
      ('Bộ ký tự X','a b c d e f g h i j k l m n o p q r s t u v w x y z A B C D E F G H I J K L M N O P Q R S T U V W X Y Z 0 1 2 3 4 5 6 7 8 9 / – ? : ( ) . , ‘ + CrLf Space', NULL),
      ('Bộ ký tự Y','A B C D E F G H I J K L M N O P Q R S T U V W X Y Z 0 1 2 3 4 5 6 7 8 9 . , – ( ) / = ‘ + : ? ! ” % & * < > ; Space', NULL),
      ('Bộ ký tự Z','a b c d e f g h i j k l m n o p q r s t u v w x y z A B C D E F G H I J K L M N O P Q R S T U V W X Y Z 0 1 2 3 4 5 6 7 8 9 . , – ( ) / = ‘ + : ? ! ” % & * < > ; { @ # _ Cr Lf Space', NULL),
      ('Bộ ký tự V','Cho phép nhập tiếng Việt có dấu(Chữ hoa, chữ thường), 0 1 2 3 4 5 6 7 8 9, . , – ( ) / = ‘ + : ? ! ” % & * < > ; { @ # _, Cr Lf Space', 'Chỉ áp dụng với điện do người dùng ngân hàng nhập trực tiếp trên module BM');
