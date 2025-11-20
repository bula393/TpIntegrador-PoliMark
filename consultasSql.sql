
delimiter //

create trigger beforeInsertEntrada 
before insert on entrada
for each row
begin
    if exists (
        select * from entrada 
        where butacaIdButaca = NEW.butacaIdButaca 
        and funcionIdFuncion = NEW.funcionIdFuncion
    ) then
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: la butaca ya está ocupada para esa función';
    end if;
end //

delimiter ;
delimiter //

-- b 

delimiter //

create trigger afterInsertProductoHasCompra 
after insert on producto_has_compra
for each row
begin
    update producto 
    set stock = stock - NEW.cantidad 
    where articuloIdArticulo = NEW.productoArticuloIdArticulo;
end //

delimiter ;

-- c

delimiter //
create procedure actualizarRango(in idCliente int, in puntos int)
begin
	if puntos > 500 and (select rangoIdRango from usuario where idCliente = identificador) = 1 then 
		update usuario set rangoIdRango = 2 where identificador = idCliente;
	end if;
end//
delimiter ;

select * from pelicula

--d

delimiter //

create procedure PeliculaMasTaquilleraPorFecha(
    in fecha_inicio date,
    in fecha_fin date
)
begin
    
    declare pelicula_nombre varchar(45);
    declare rango_horario_popular varchar(45);
    declare max_asistencia int;
    
    select pelicula.nombre,SUM(a.precio) as total_recaudacion
    into pelicula_nombre from pelicula
    join funcion on pelicula.nombre = funcion.peliculaNombre
    join entrada on funcion.idFuncion = entrada.funcionIdFuncion
    join articulo on entrada.articuloIdArticulo = articulo.idArticulo
    where DATE(funcion.horario) between fecha_inicio and fecha_fin
    group by pelicula.nombre
    order by total_recaudacion desc limit 1;
    
    if pelicula_nombre is null then
        select 'No se encontraron ventas en el rango de fechas especificado' as resultado;
    else
        select 
            case 
                when hour(funcion.horario) between 6 and 12 then 'Mañana (6:00-12:00)'
                when hour(funcion.horario) between 12 and 18 then 'Tarde (12:00-18:00)'
                when hour(funcion.horario) between 18 and 24 then 'Noche (18:00-24:00)'
                else 'Madrugada (0:00-6:00)'
            end as rango_horario,
            COUNT(entrada.articuloIdArticulo) as total_asistentes
        into rango_horario_popular, max_asistencia
        from funcion
        join entrada on funcion.idFuncion = entrada.funcionIdFuncion
        where funcion.peliculaNombre = pelicula_nombre
            and DATE(funcion.horario) between fecha_inicio and fecha_fin
        group by
            case 
                when hour(funcion.horario) between 6 and 12 then 'Mañana (6:00-12:00)'
                when hour(funcion.horario) between 12 and 18 then 'Tarde (12:00-18:00)'
                when hour(funcion.horario) between 18 and 24 then 'Noche (18:00-24:00)'
                else 'Madrugada (0:00-6:00)'
            end
        order by total_asistentes desc
        limit 1;
        
        -- Mostrar resultados
        select 
            pelicula_nombre as 'Película Más Taquillera',
            rango_horario_popular as 'Rango Horario Más Popular',
            max_asistencia as 'Total de Asistentes',
            CONCAT(fecha_inicio, ' a ', fecha_fin) as 'Período Analizado';
    end if;
    
end //

delimiter ;

delimiter //

create event RecordatoriosFuncionesDiarias
on schedule every 1 day
starts CONCAT(CURDATE() + interval 1 DAY, ' 08:00:00')
do
begin
    declare done int default FALSE;
    declare v_usuario_id int;
    declare v_funcion_id int;
    declare v_pelicula_nombre varchar(45);
    declare v_horario datetime;
    declare v_lugar_nombre varchar(45);
    declare v_notificacion_existe int;
    
    declare cur_compras cursor for 
    select 
        compra.usuarioIdentificador,
        funcion.idFuncion,
        funcion.peliculaNombre,
        funcion.horario,
        lugar.nombre
    from compra
    join entrada on compra.idCompra = entrada.compraIdCompra
    join funcion on entrada.funcionIdFuncion = funcion.idFuncion
    join sala on funcion.salaIdSala = sala.idSala
    join lugar on sala.lugarIdLugar = lugar.idLugar
    where date(funcion.horario) = CURDATE()
        and compra.pagado = 1
    group by compra.usuarioIdentificador, funcion.idFuncion;
    
    declare continue HandLER for not FOUND set done = TRUE;
    
    -- Abrir cursor
    open cur_compras;
    
    -- Iniciar ciclo
    read_loop: LOOP
        fetch cur_compras into v_usuario_id, v_funcion_id, v_pelicula_nombre, v_horario, v_lugar_nombre;
        
        if done then
            LEAVE read_loop;
        end if;
        
        -- Verificar si ya existe una notificación para este usuario y función hoy
        select COUNT(*) into v_notificacion_existe
        from notificaciones 
        where usuarioIdentificador = v_usuario_id 
            and funcionIdFuncion = v_funcion_id
            and DATE(fechaCreacion) = CURDATE()
            and mensaje LIKE '%Recordatorio:%';
        
        -- Si no existe notificación, insertar una nueva
        if v_notificacion_existe = 0 then
            insert into notificaciones (
                usuarioIdentificador, 
                funcionIdFuncion, 
                mensaje, 
                mensaje, 
                fechaEnvio,
                leida
            ) values (
                v_usuario_id,
                v_funcion_id,
                CONCAT(
                    'Recordatorio: Tienes una función de "', 
                    v_pelicula_nombre, 
                    '" hoy a las ', 
                    DATE_FORMAT(v_horario, '%H:%i'),
                    ' en ',
                    v_lugar_nombre,
                    '. ¡Disfruta la película!'
                ),
                CURDATE(),
                0
            );
        end if;
        
    end loop;    
    -- Cerrar cursor
    close cur_compras;
    
    -- Mostrar resultado (opcional)
    select 'Proceso de recordatorios completado' as resultado;
    
end //

delimiter ;

delimiter //




create event if not exists usuariosInactivos 
on schedule every 1 MONTH
starts now()
do
begin 
    update usuario 
    set rangoIdRango = 1 
    where idUsuario not in (
        select distinct c.idUsuario 
        from compra 
        where compra.fecha >= DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)
        and compra.fecha < CURRENT_DATE
    );
end//

delimiter ;

delimiter ;

