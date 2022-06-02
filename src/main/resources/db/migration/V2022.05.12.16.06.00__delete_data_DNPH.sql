delete from application_opening_lc;
delete from product where application_opening_lc_id is not null;
delete from contract_license where application_opeing_lc_id is not null;