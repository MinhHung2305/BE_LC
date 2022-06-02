-- public.application_opening_lc definition

-- Drop table

-- DROP TABLE public.application_opening_lc;

CREATE TABLE public.application_opening_lc (
                                               id serial4 NOT NULL,
                                               bank_id int4 NULL,
                                               proposal_code_release varchar(255) NULL,
                                               contract_type int4 NULL,
                                               contract_code varchar(50) NULL,
                                               contract_number varchar(20) NULL,
                                               contract_file varchar(255) NULL,
                                               proposal_date date NULL,
                                               lc_type varchar(255) NULL,
                                               corporate_buy_id int4 NULL,
                                               corporate_sell_id int4 NULL,
                                               bank_confirm_id int4 NULL,
                                               bank_tranfer_id int4 NULL,
                                               confirmation_instruction int4 NULL,
                                               confirming_bank_request varchar(255) NULL,
                                               money_type varchar(255) NULL,
                                               value_lc int4 NULL,
                                               negative_tolerance float4 NULL,
                                               positive_tolerance float4 NULL,
                                               term_of_payment int4 NULL,
                                               note_term_of_payment varchar(255) NULL,
                                               payment_amount varchar(255) NULL,
                                               due_date date NULL,
                                               due_address varchar(255) NULL,
                                               fee varchar(255) NULL,
                                               partial_shipment bool NULL,
                                               transhipment bool NULL,
                                               place_of_receipt varchar(255) NULL,
                                               place_of_destination varchar(255) NULL,
                                               port_of_departure varchar(300) NULL,
                                               lastest_delivery_date date NULL,
                                               delivery_time date NULL,
                                               delivery_term varchar(300) NULL,
                                               description_of_goods varchar(500) NULL,
                                               period_for_presentation date NULL,
                                               other_condition varchar NULL,
                                               tt_reimbursement bool NULL,
                                               hold_account int4 NULL,
                                               fee_account int4 NULL,
                                               payment_account int4 NULL,
                                               commitment_customer varchar NULL,
                                               port_of_destination varchar(300) NULL,
                                               created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                               created_by varchar NULL,
                                               last_modified_by varchar NULL,
                                               last_modified_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                               presentation_at varchar NULL,
                                               CONSTRAINT application_opening_lc_pk PRIMARY KEY (id)
);


-- public.application_opening_lc foreign keys

ALTER TABLE public.application_opening_lc ADD CONSTRAINT application_opening_lc_fk FOREIGN KEY (bank_id) REFERENCES public.bank_info(bank_id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE public.application_opening_lc ADD CONSTRAINT application_opening_lc_fk_1 FOREIGN KEY (corporate_buy_id) REFERENCES public.corporate(corporate_id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE public.application_opening_lc ADD CONSTRAINT application_opening_lc_fk_2 FOREIGN KEY (corporate_sell_id) REFERENCES public.corporate(corporate_id) ON DELETE CASCADE ON UPDATE CASCADE;