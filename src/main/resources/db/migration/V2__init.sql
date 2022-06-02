CREATE TABLE public."ca_info" (
	ca_info_id serial NOT NULL,
	ca_info_code varchar NOT NULL,
	ca_info_corporate_tax_code varchar NOT NULL,
	ca_info_corporate_name varchar NULL,
	ca_info_type varchar NULL,
	ca_info_number varchar NULL,
	ca_info_effect_date timestamp NOT NULL,
	ca_info_expiry_date timestamp NOT NULL,
	ca_info_public_key varchar NOT NULL,
	ca_info_cert_organization varchar NOT NULL,
	ca_info_organization varchar NOT NULL,
	ca_info_status int NOT NULL DEFAULT 0,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_by varchar NULL,
	last_modified_by varchar NULL,
	last_modified_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT ca_info_pk PRIMARY KEY (ca_info_id)
);
