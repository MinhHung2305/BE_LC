CREATE TABLE public.financing_limit_transaction (
                                                    id serial4 NOT NULL,
                                                    financing_limit_id bigint NOT NULL,
                                                    sponsor_offer_code varchar(255) NULL,
                                                    lc_number varchar(255) NULL,
                                                    revised_sponsorship_request_code_lc varchar(255) NULL,
                                                    modification_lc_code varchar(255) NULL,
                                                    transaction_code_presented varchar(255) NULL,
                                                    status_transaction_presented varchar(255) NULL,
                                                    "period" varchar(255) NULL,
                                                    financing_date date NULL,
                                                    loan_maturity_date date NULL,
                                                    zoning_limit int8 NULL DEFAULT 0,
                                                    commitment_limit int8 NULL DEFAULT 0,
                                                    disbursement_amount int8 NULL DEFAULT 0,
                                                    repayment_amount int8 NULL DEFAULT 0,
                                                    CONSTRAINT financing_limit_transaction_pk PRIMARY KEY (id),
                                                    CONSTRAINT financing_limit_transaction_fk FOREIGN KEY (financing_limit_id) REFERENCES public.financing_limit(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Column comments

COMMENT ON COLUMN public.financing_limit_transaction.sponsor_offer_code IS 'ma de nghi tai tro';
COMMENT ON COLUMN public.financing_limit_transaction.lc_number IS 'so lc';
COMMENT ON COLUMN public.financing_limit_transaction.revised_sponsorship_request_code_lc IS 'ma de nghi tu chinh lc';
COMMENT ON COLUMN public.financing_limit_transaction.modification_lc_code IS 'ma tu chinh lc';
COMMENT ON COLUMN public.financing_limit_transaction.transaction_code_presented IS 'ma giao dich xuat trinh';
COMMENT ON COLUMN public.financing_limit_transaction.status_transaction_presented IS 'trang thai giao dich xuat trinh';
COMMENT ON COLUMN public.financing_limit_transaction."period" IS 'ky han';
COMMENT ON COLUMN public.financing_limit_transaction.financing_date IS 'ngay tai tro';
COMMENT ON COLUMN public.financing_limit_transaction.loan_maturity_date IS 'ngay den han cua khoan vay';
COMMENT ON COLUMN public.financing_limit_transaction.zoning_limit IS 'han muc khoanh';
COMMENT ON COLUMN public.financing_limit_transaction.commitment_limit IS 'han muc cam ket';
COMMENT ON COLUMN public.financing_limit_transaction.repayment_amount IS 'so tien tra no';
COMMENT ON COLUMN public.financing_limit_transaction.disbursement_amount IS 'so tien giai ngan';
