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

delimiter //
create procedure actualizarRango(in idCliente int, in puntos int)
begin
	if puntos > 500 then 
		update usuario set rangoIdRango = 2 where identificador = idCliente;
	end if;
end//
delimiter ;

select * from pelicula

--d

DELIMITER //

CREATE PROCEDURE PeliculaMasTaquilleraPorFecha(
    IN fecha_inicio DATE,
    IN fecha_fin DATE
)
BEGIN
    -- Variables para almacenar los resultados
    DECLARE pelicula_nombre varchar(45);
    DECLARE rango_horario_popular varchar(20);
    DECLARE max_asistencia INT;
    
    -- Encontrar la película más taquillera
    SELECT 
        p.nombre,
        SUM(a.precio) as total_recaudacion
    INTO pelicula_nombre
    FROM pelicula
    JOIN funcion ON pelicula.nombre = funcion.peliculaNombre
    JOIN entrada ON funcion.idFuncion = entrada.funcionIdFuncion
    JOIN articulo ON entrada.articuloIdArticulo = articulo.idArticulo
    WHERE DATE(funcion.horario) BETWEEN fecha_inicio AND fecha_fin
    GROUP BY pelicula.nombre
    ORDER BY total_recaudacion DESC LIMIT 1;
    
    -- Si no se encontraron datos
    IF pelicula_nombre IS NULL THEN
        SELECT 'No se encontraron ventas en el rango de fechas especificado' as resultado;
    ELSE
        -- Encontrar el rango horario con mayor asistencia para esa película
        SELECT 
            CASE 
                WHEN HOUR(funcion.horario) BETWEEN 6 AND 12 THEN 'Mañana (6:00-12:00)'
                WHEN HOUR(funcion.horario) BETWEEN 12 AND 18 THEN 'Tarde (12:00-18:00)'
                WHEN HOUR(funcion.horario) BETWEEN 18 AND 24 THEN 'Noche (18:00-24:00)'
                ELSE 'Madrugada (0:00-6:00)'
            END as rango_horario,
            COUNT(entrada.articuloIdArticulo) as total_asistentes
        INTO rango_horario_popular, max_asistencia
        FROM funcion
        JOIN entrada ON funcion.idFuncion = entrada.funcionIdFuncion
        WHERE funcion.peliculaNombre = pelicula_nombre
            AND DATE(funcion.horario) BETWEEN fecha_inicio AND fecha_fin
        GROUP BY 
            CASE 
                WHEN HOUR(funcion.horario) BETWEEN 6 AND 12 THEN 'Mañana (6:00-12:00)'
                WHEN HOUR(funcion.horario) BETWEEN 12 AND 18 THEN 'Tarde (12:00-18:00)'
                WHEN HOUR(funcion.horario) BETWEEN 18 AND 24 THEN 'Noche (18:00-24:00)'
                ELSE 'Madrugada (0:00-6:00)'
            END
        ORDER BY total_asistentes DESC
        LIMIT 1;
        
        -- Mostrar resultados
        SELECT 
            pelicula_nombre as 'Película Más Taquillera',
            rango_horario_popular as 'Rango Horario Más Popular',
            max_asistencia as 'Total de Asistentes',
            CONCAT(fecha_inicio, ' a ', fecha_fin) as 'Período Analizado';
    END IF;
    
END //

DELIMITER ;

--e

 create event if not exists notiPeli on schedule 
 every 1 day starts timestamp(current_date(), "08:00.00") do
 begin
	
 end//
 delimiter ;


DELIMITER //

CREATE EVENT RecordatoriosFuncionesDiarias
ON SCHEDULE EVERY 1 DAY
STARTS CONCAT(CURDATE() + INTERVAL 1 DAY, ' 08:00:00')
DO
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_usuario_id INT;
    DECLARE v_funcion_id INT;
    DECLARE v_pelicula_nombre VARCHAR(45);
    DECLARE v_horario DATETIME;
    DECLARE v_lugar_nombre VARCHAR(45);
    DECLARE v_notificacion_existe INT;
    
    -- Cursor para recorrer las compras con funciones del día
    DECLARE cur_compras CURSOR FOR 
    SELECT 
        compra.usuarioIdentificador,
        funcion.idFuncion,
        funcion.peliculaNombre,
        funcion.horario,
        lugar.nombre
    FROM compra
    JOIN entrada ON compra.idCompra = entrada.compraIdCompra
    JOIN funcion ON entrada.funcionIdFuncion = funcion.idFuncion
    JOIN sala ON funcion.salaIdSala = sala.idSala
    JOIN lugar ON sala.lugarIdLugar = lugar.idLugar
    WHERE DATE(funcion.horario) = CURDATE()
        AND compra.pagado = 1
    GROUP BY compra.usuarioIdentificador, funcion.idFuncion;
    
    -- Handler para cuando no hay más registros
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    -- Abrir cursor
    OPEN cur_compras;
    
    -- Iniciar ciclo
    read_loop: LOOP
        FETCH cur_compras INTO v_usuario_id, v_funcion_id, v_pelicula_nombre, v_horario, v_lugar_nombre;
        
        IF done THEN
            LEAVE read_loop;
        END IF;
        
        -- Verificar si ya existe una notificación para este usuario y función hoy
        SELECT COUNT(*) INTO v_notificacion_existe
        FROM notificaciones 
        WHERE usuarioIdentificador = v_usuario_id 
            AND funcionIdFuncion = v_funcion_id
            AND DATE(fechaCreacion) = CURDATE()
            AND mensaje LIKE '%Recordatorio:%';
        
        -- Si no existe notificación, insertar una nueva
        IF v_notificacion_existe = 0 THEN
            INSERT INTO notificaciones (
                usuarioIdentificador, 
                funcionIdFuncion, 
                mensaje, 
                fechaEnvio,
                leida
            ) VALUES (
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
        END IF;
        
    END LOOP;
    
    -- Cerrar cursor
    CLOSE cur_compras;
    
    -- Mostrar resultado (opcional)
    SELECT 'Proceso de recordatorios completado' as resultado;
    
END //

DELIMITER ;

