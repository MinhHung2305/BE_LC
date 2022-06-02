CREATE TABLE public.financing_limit (
                                        id serial4 NOT NULL,
                                        created_by varchar(255) NULL,
                                        created_date timestamp NULL,
                                        last_modified_by varchar(255) NULL,
                                        last_modified_date timestamp NULL,
                                        bank_id bigint NULL,
                                        type_limit varchar(255) NULL,
                                        contract_number_limit varchar(255) NULL,
                                        date_range date NULL,
                                        expiration_date date NULL,
                                        money_type varchar(255) NULL DEFAULT 1,
                                        description_of_transactions varchar(255) NULL,
                                        request_a_refund boolean NULL,
                                        total_zoning_limit bigint NULL,
                                        total_commitment_limit bigint NULL,
                                        total_disbursement_amount bigint NULL,
                                        total_repayment_amount bigint NULL,
                                        availability_limit bigint NULL,
                                        sponsor_offer_code varchar(255) NULL,
                                        lc_number varchar(255) NULL,
                                        revised_sponsorship_request_code_lc varchar(255) NULL,
                                        modification_lc_code varchar(255) NULL,
                                        transaction_code_presented varchar(255) NULL,
                                        status_transaction_presented varchar(255) NULL,
                                        "period" varchar(255) NULL,
                                        financing_date date NULL,
                                        loan_maturity_date date NULL,
                                        zoning_limit bigint NULL,
                                        commitment_limit bigint NULL,
                                        disbursement_amount bigint NULL,
                                        repayment_amount bigint NULL,
                                        CONSTRAINT financing_limit_pk PRIMARY KEY (id),
                                        CONSTRAINT financing_limit_fk FOREIGN KEY (bank_id) REFERENCES public.bank_info(bank_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Column comments

COMMENT ON COLUMN public.financing_limit.id IS 'id(pk)';
COMMENT ON COLUMN public.financing_limit.bank_id IS 'phân loại kênh';
COMMENT ON COLUMN public.financing_limit.type_limit IS 'mã kênh';
COMMENT ON COLUMN public.financing_limit.contract_number_limit IS 'tên kênh';
COMMENT ON COLUMN public.financing_limit.date_range IS 'loại kênh';
COMMENT ON COLUMN public.financing_limit.expiration_date IS 'mô tả';
COMMENT ON COLUMN public.financing_limit.money_type IS 'trạng thái';
COMMENT ON COLUMN public.financing_limit.description_of_transactions IS 'Mieu ta cac giao dich thuoc han muc';
COMMENT ON COLUMN public.financing_limit.request_a_refund IS 'Can gui yeu cau hoan tra ';
COMMENT ON COLUMN public.financing_limit.total_zoning_limit IS 'tong han muc khoanh';
COMMENT ON COLUMN public.financing_limit.total_commitment_limit IS 'tong han muc cam ket';
COMMENT ON COLUMN public.financing_limit.total_disbursement_amount IS 'tong so tien giai ngan';
COMMENT ON COLUMN public.financing_limit.total_repayment_amount IS 'tong so tien tra no';
COMMENT ON COLUMN public.financing_limit.availability_limit IS 'han muc kha dung';
COMMENT ON COLUMN public.financing_limit.sponsor_offer_code IS 'ma de nghi tai tro';
COMMENT ON COLUMN public.financing_limit.lc_number IS 'so lc';
COMMENT ON COLUMN public.financing_limit.revised_sponsorship_request_code_lc IS 'ma de nghi tu chinh lc';
COMMENT ON COLUMN public.financing_limit.modification_lc_code IS 'ma tu chinh lc';
COMMENT ON COLUMN public.financing_limit.transaction_code_presented IS 'ma giao dich xuat trinh';
COMMENT ON COLUMN public.financing_limit.status_transaction_presented IS 'trang thai giao dich xuat trinh';
COMMENT ON COLUMN public.financing_limit."period" IS 'ky han';
COMMENT ON COLUMN public.financing_limit.financing_date IS 'ngay tai tro';
COMMENT ON COLUMN public.financing_limit.loan_maturity_date IS 'ngay den han cua khoan vay';
COMMENT ON COLUMN public.financing_limit.zoning_limit IS 'han muc khoanh';
COMMENT ON COLUMN public.financing_limit.commitment_limit IS 'han muc cam ket';
COMMENT ON COLUMN public.financing_limit.repayment_amount IS 'so tien tra no';
