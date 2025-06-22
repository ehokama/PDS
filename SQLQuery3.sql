CREATE DATABASE ConcesionariaPSD;
go
use ConcesionariaPSD;
go
	

select * from vehiculo;
select * from usuario;
select * from orden_de_compra;


insert into metodo_de_pago values ('Contado','en efectivo','Contado',0)
insert into metodo_de_pago values ('Transferencia','en el acto','Transferencia',0)
insert into metodo_de_pago values ('Tarjeta','en 1 pago','Tarjeta de Credito',10)

select * from metodo_de_pago;

select * from configuracion_adicional;




drop table vehiculo;
go
drop table  usuario;
go
drop table orden_de_compra;
go
drop table metodo_de_pago;
go
drop table estado_pedido;
go
drop table datos_de_facturacion;
go
drop table configuracion_adicional;
go
drop table area;
go


delete from orden_de_compra where vehiculo_patente='AG345LN';
go
select * from orden_de_compra;



USE master;
GO

ALTER DATABASE ConsecionariaPSD
SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
GO

DROP DATABASE ConsecionariaPSD;
GO