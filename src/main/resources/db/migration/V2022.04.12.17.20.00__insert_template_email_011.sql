INSERT INTO public."template" (template_code,template_name,template_description,template_subject,template_content,template_to,template_cc,template_bcc,template_type,template_status,created_date,created_by,last_modified_by,last_modified_date) VALUES
    ('Email_011','Thông báo đặt lại mật khẩu người dùng FPT quản trị','Thông báo đặt lại mật khẩu người dùng FPT quản trị','[FPT_L/C] - Thông báo đặt lại mật khẩu thành công','<!doctype html><html><head><meta name="viewport" content="width=device-width,initial-scale=1"><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><title>Simple Transactional Email</title><style>img{border:none;-ms-interpolation-mode:bicubic;max-width:100%}body{background-color:#f6f6f6;font-family:sans-serif;-webkit-font-smoothing:antialiased;font-size:14px;line-height:1.4;margin:0;padding:0;-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%}table{border-collapse:separate;mso-table-lspace:0;mso-table-rspace:0;width:100%}table td{font-family:sans-serif;font-size:14px;vertical-align:top}.body{background-color:#f6f6f6;width:100%}.container{display:block;margin:0 auto!important;max-width:580px;padding:10px;width:580px}.content{box-sizing:border-box;display:block;margin:0 auto;max-width:580px;padding:10px}.main{background:#fff;border-radius:3px;width:100%}.wrapper{box-sizing:border-box;padding:20px}.content-block{padding-bottom:10px;padding-top:10px}.footer{clear:both;margin-top:10px;text-align:center;width:100%}.footer a,.footer p,.footer span,.footer td{color:#999;font-size:12px;text-align:center}h1,h2,h3,h4{color:#000;font-family:sans-serif;font-weight:400;line-height:1.4;margin:0;margin-bottom:30px}h1{font-size:35px;font-weight:300;text-align:center;text-transform:capitalize}ol,p,ul{font-family:sans-serif;font-size:14px;font-weight:400;margin:0;margin-bottom:15px}ol li,p li,ul li{list-style-position:inside;margin-left:5px}a{color:#3498db;text-decoration:underline}.btn{box-sizing:border-box;width:100%}.btn>tbody>tr>td{padding-bottom:15px}.btn table{width:auto}.btn table td{background-color:#fff;border-radius:5px;text-align:center}.btn a{background-color:#fff;border:solid 1px #3498db;border-radius:5px;box-sizing:border-box;color:#3498db;cursor:pointer;display:inline-block;font-size:14px;font-weight:700;margin:0;padding:12px 25px;text-decoration:none;text-transform:capitalize}.btn-primary table td{background-color:#3498db}.btn-primary a{background-color:#3498db;border-color:#3498db;color:#fff}.last{margin-bottom:0}.first{margin-top:0}.align-center{text-align:center}.align-right{text-align:right}.align-left{text-align:left}.clear{clear:both}.mt0{margin-top:0}.mb0{margin-bottom:0}.preheader{color:transparent;display:none;height:0;max-height:0;max-width:0;opacity:0;overflow:hidden;mso-hide:all;visibility:hidden;width:0}.powered-by a{text-decoration:none}hr{border:0;border-bottom:1px solid #f6f6f6;margin:20px 0}@media only screen and (max-width:620px){table.body h1{font-size:28px!important;margin-bottom:10px!important}table.body a,table.body ol,table.body p,table.body span,table.body td,table.body ul{font-size:16px!important}table.body .article,table.body .wrapper{padding:10px!important}table.body .content{padding:0!important}table.body .container{padding:0!important;width:100%!important}table.body .main{border-left-width:0!important;border-radius:0!important;border-right-width:0!important}table.body .btn table{width:100%!important}table.body .btn a{width:100%!important}table.body .img-responsive{height:auto!important;max-width:100%!important;width:auto!important}}@media all{.ExternalClass{width:100%}.ExternalClass,.ExternalClass div,.ExternalClass font,.ExternalClass p,.ExternalClass span,.ExternalClass td{line-height:100%}.apple-link a{color:inherit!important;font-family:inherit!important;font-size:inherit!important;font-weight:inherit!important;line-height:inherit!important;text-decoration:none!important}#MessageViewBody a{color:inherit;text-decoration:none;font-size:inherit;font-family:inherit;font-weight:inherit;line-height:inherit}.btn-primary table td:hover{background-color:#34495e!important}.btn-primary a:hover{background-color:#34495e!important;border-color:#34495e!important}}</style></head><body class=""><span class="preheader">This is preheader text. Some clients will show this text as a preview.</span><table role="presentation" border="0" cellpadding="0" cellspacing="0" class="body"><tr><td>&nbsp;</td><td class="container"><div class="content"><table role="presentation" class="main"><tr><td class="wrapper"><table role="presentation" border="0" cellpadding="0" cellspacing="0"><tr><td><p>Kính gửi: $user_name<br>Công ty Hệ thống thông tin FPT xin thông báo mật khẩu mới của người dùng $user_code là: $password</p><p>Để đảm bảo quyền lợi và sự an toàn của Quý khách, đề nghị Quý khách thay đổi ngay mật khẩu truy cập.</p><p>Người dùng vui lòng không gửi thư vào địa chỉ này do đây là email tự động.</p><p>Trân trọng.</p></td></tr></table></td></tr></table><div class="footer"><table role="presentation" border="0" cellpadding="0" cellspacing="0"><tr><td class="content-block"><span class="apple-link">LC Platform Online</span></td></tr></table></div></div></td><td>&nbsp;</td></tr></table></body></html>',NULL,NULL,NULL,'email',1,'2022-02-08 12:04:12.647',NULL,NULL,'2022-02-08 12:04:12.647');

INSERT INTO public."template" (template_code,template_name,template_description,template_subject,template_content,template_to,template_cc,template_bcc,template_type,template_status,created_date,created_by,last_modified_by,last_modified_date) VALUES
    ('Email_051','Thông báo đề nghị phát hành LC','Thông báo đề nghị phát hành LC','[FPT_L/C] - Đề nghị phát hành LC','<!doctype html>
<html>

<head>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Simple Transactional Email</title>
    <style>
        img {
            border: none;
            -ms-interpolation-mode: bicubic;
            max-width: 100%
        }

        body {
            background-color: #f6f6f6;
            font-family: sans-serif;
            -webkit-font-smoothing: antialiased;
            font-size: 14px;
            line-height: 1.4;
            margin: 0;
            padding: 0;
            -ms-text-size-adjust: 100%;
            -webkit-text-size-adjust: 100%
        }

        table {
            border-collapse: separate;
            mso-table-lspace: 0;
            mso-table-rspace: 0;
            width: 100%
        }

        table td {
            font-family: sans-serif;
            font-size: 14px;
            vertical-align: top
        }

        .body {
            background-color: #f6f6f6;
            width: 100%
        }

        .container {
            display: block;
            margin: 0 auto !important;
            max-width: 580px;
            padding: 10px;
            width: 580px
        }

        .content {
            box-sizing: border-box;
            display: block;
            margin: 0 auto;
            max-width: 580px;
            padding: 10px
        }

        .main {
            background: #fff;
            border-radius: 3px;
            width: 100%
        }

        .wrapper {
            box-sizing: border-box;
            padding: 20px
        }

        .content-block {
            padding-bottom: 10px;
            padding-top: 10px
        }

        .footer {
            clear: both;
            margin-top: 10px;
            text-align: center;
            width: 100%
        }

        .footer a,
        .footer p,
        .footer span,
        .footer td {
            color: #999;
            font-size: 12px;
            text-align: center
        }

        h1,
        h2,
        h3,
        h4 {
            color: #000;
            font-family: sans-serif;
            font-weight: 400;
            line-height: 1.4;
            margin: 0;
            margin-bottom: 30px
        }

        h1 {
            font-size: 35px;
            font-weight: 300;
            text-align: center;
            text-transform: capitalize
        }

        ol,
        p,
        ul {
            font-family: sans-serif;
            font-size: 14px;
            font-weight: 400;
            margin: 0;
            margin-bottom: 15px
        }

        ol li,
        p li,
        ul li {
            list-style-position: inside;
            margin-left: 5px
        }

        a {
            color: #3498db;
            text-decoration: underline
        }

        .btn {
            box-sizing: border-box;
            width: 100%
        }

        .btn>tbody>tr>td {
            padding-bottom: 15px
        }

        .btn table {
            width: auto
        }

        .btn table td {
            background-color: #fff;
            border-radius: 5px;
            text-align: center
        }

        .btn a {
            background-color: #fff;
            border: solid 1px #3498db;
            border-radius: 5px;
            box-sizing: border-box;
            color: #3498db;
            cursor: pointer;
            display: inline-block;
            font-size: 14px;
            font-weight: 700;
            margin: 0;
            padding: 12px 25px;
            text-decoration: none;
            text-transform: capitalize
        }

        .btn-primary table td {
            background-color: #3498db
        }

        .btn-primary a {
            background-color: #3498db;
            border-color: #3498db;
            color: #fff
        }

        .last {
            margin-bottom: 0
        }

        .first {
            margin-top: 0
        }

        .align-center {
            text-align: center
        }

        .align-right {
            text-align: right
        }

        .align-left {
            text-align: left
        }

        .clear {
            clear: both
        }

        .mt0 {
            margin-top: 0
        }

        .mb0 {
            margin-bottom: 0
        }

        .preheader {
            color: transparent;
            display: none;
            height: 0;
            max-height: 0;
            max-width: 0;
            opacity: 0;
            overflow: hidden;
            mso-hide: all;
            visibility: hidden;
            width: 0
        }

        .powered-by a {
            text-decoration: none
        }

        hr {
            border: 0;
            border-bottom: 1px solid #f6f6f6;
            margin: 20px 0
        }

        @media only screen and (max-width:620px) {
            table.body h1 {
                font-size: 28px !important;
                margin-bottom: 10px !important
            }

            table.body a,
            table.body ol,
            table.body p,
            table.body span,
            table.body td,
            table.body ul {
                font-size: 16px !important
            }

            table.body .article,
            table.body .wrapper {
                padding: 10px !important
            }

            table.body .content {
                padding: 0 !important
            }

            table.body .container {
                padding: 0 !important;
                width: 100% !important
            }

            table.body .main {
                border-left-width: 0 !important;
                border-radius: 0 !important;
                border-right-width: 0 !important
            }

            table.body .btn table {
                width: 100% !important
            }

            table.body .btn a {
                width: 100% !important
            }

            table.body .img-responsive {
                height: auto !important;
                max-width: 100% !important;
                width: auto !important
            }
        }

        @media all {
            .ExternalClass {
                width: 100%
            }

            .ExternalClass,
            .ExternalClass div,
            .ExternalClass font,
            .ExternalClass p,
            .ExternalClass span,
            .ExternalClass td {
                line-height: 100%
            }

            .apple-link a {
                color: inherit !important;
                font-family: inherit !important;
                font-size: inherit !important;
                font-weight: inherit !important;
                line-height: inherit !important;
                text-decoration: none !important
            }

            #MessageViewBody a {
                color: inherit;
                text-decoration: none;
                font-size: inherit;
                font-family: inherit;
                font-weight: inherit;
                line-height: inherit
            }

            .btn-primary table td:hover {
                background-color: #34495e !important
            }

            .btn-primary a:hover {
                background-color: #34495e !important;
                border-color: #34495e !important
            }
        }
    </style>
</head>

<body class=""><span class="preheader">This is preheader text. Some clients will show this text as a preview.</span>
    <table role="presentation" border="0" cellpadding="0" cellspacing="0" class="body">
        <tr>
            <td>&nbsp;</td>
            <td class="container">
                <div class="content">
                    <table role="presentation" class="main">
                        <tr>
                            <td class="wrapper">
                                <table role="presentation" border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td>
                                            <p>Kính gửi: $bankName<br></p>
                                            <p>$corporateBuy Đề nghị Quý Ngân hàng phát hành thư tín dụng (LC) theo các điều kiện và điều khoản như form "Đề nghị phát hành LC" đính kèm.</p>
                                            <p>Mã đề nghị phát hành LC: $proposalCodeRelease</p>
                                            <p>Trân trọng.</p>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                    <div class="footer">
                        <table role="presentation" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td class="content-block"><span class="apple-link">LC Platform Online</span></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </td>
            <td>&nbsp;</td>
        </tr>
    </table>
</body>

</html>',NULL,NULL,NULL,'email',1,'2022-02-08 12:04:12.647',NULL,NULL,'2022-02-08 12:04:12.647');