select * from articulo join producto on articulo_idarticulo = idarticulo;
create database Polimark;
delimiter //


-- a
create trigger beforeInsertEntrada before insert on entrada
for each row
begin
	if exists(select * from entrada where butaca_idbutaca = new.butaca_idbutaca and funcion_idfuncion = new.funcion_idfuncion)
		then
			SIGNAL SQLSTATE '45000' 
			SET MESSAGE_TEXT = 'Error: la butaca ya está ocupada para esa función';
		end if;
end //

-- b 

create trigger afterInsertProductoHasCompra after insert on producto_has_compra
for each row
begin
	update producto set stock = stock - new.cantidad where articulo_idarticulo = new.producto_articulo_idarticulo;
end //

-- c

select * from pelicula
