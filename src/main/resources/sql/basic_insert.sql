use tax_data;
insert into role_type (role) value ('User');
insert into role_type (role) value ('Inspector');

insert into status (state) value ('Processing');
insert into status (state) value ('Approved');
insert into status (state) value ('Disapproved');

insert into period (period) value ('First period');
insert into period (period) value ('Second period');
insert into period (period) value ('Third period');
insert into period (period) value ('Fourth period');
insert into period (period) value ('Year');

insert into entrepreneur_type (type) value ('Physical Person');
insert into entrepreneur_type (type) value ('Juridical Person');

INSERT INTO tax_data.user (role_id, entrepreneur_type_id, name, lastname, email, password, tin, address) VALUES (2, 1, 'Admin', 'Admin', 'a@a.a', '123456', '00000000', 'Home');
INSERT INTO tax_data.user (role_id, entrepreneur_type_id, name, lastname, email, password, tin, address) VALUES (1, 1, 'User', 'User', 'u@u.u', '123456', '00000001', 'Home');
