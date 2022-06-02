CREATE TABLE public.finacing_limit_change_history (
                                                      id serial4 NOT NULL,
                                                      financing_limit_id bigint NOT NULL,
                                                      contract_number_limit varchar NULL,
                                                      date_range date NULL,
                                                      expiration_date date NULL,
                                                      total_limit bigint NULL,
                                                      request_a_refund boolean NULL,
                                                      times_change int4 NOT NULL DEFAULT 0,
                                                      CONSTRAINT finacing_limit_change_history_pk PRIMARY KEY (id),
                                                      CONSTRAINT finacing_limit_change_history_fk FOREIGN KEY (financing_limit_id) REFERENCES public.financing_limit(id) ON DELETE CASCADE ON UPDATE CASCADE
);
