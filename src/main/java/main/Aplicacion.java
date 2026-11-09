package main;

import java.util.List;
import java.util.Scanner;

import model.core.Cliente;
import model.core.DetallePedido;
import model.core.Pedido;
import model.core.Restaurante;
import model.enums.EstadoPedido;
import model.enums.ModalidadEntrega;
import model.enums.TipoPreparacion;
import model.enums.TipoTemperatura;
import model.producto.Bebida;
import model.producto.PlatoFuerte;
import model.producto.Postre;
import model.producto.Producto;
import java.time.LocalDateTime;
import model.domicilio.EmpresaDomicilio;

public class Aplicacion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Restaurante restaurante = new Restaurante("Sabor & Tradición", "900.123.456-7", "Armenia, Quindío");
        int opcion;
        do {
            System.out.println("\n---- Menú SABOR & TRADICIÓN ----");
            System.out.println("1. Registrar producto en el menú");
            System.out.println("2. Registrar cliente");
            System.out.println("3. Calcular precio de venta de productos");
            System.out.println("4. Verificar condiciones de calidad de productos");
            System.out.println("5. Consultar productos disponibles y rechazados");
            System.out.println("------------------------------------");
            System.out.println("6. Crear pedido");
            System.out.println("7. Gestionar servicio de domicilio");
            System.out.println("8. Confirmar pedido");
            System.out.println("9. Calcular valor total de un pedido");
            System.out.println("------------------------------------");
            System.out.println("10. Consultar historial de pedidos de un cliente");
            System.out.println("11. Actualizar estado de un pedido");
            System.out.println("12. Generar reporte general de ventas");
            System.out.println("13. Salir");
            System.out.print("\nIngrese una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println("--- 1. REGISTRAR PRODUCTO EN EL MENÚ ---");
                    System.out.println("Seleccione la categoría del producto (ingrese el número de la opción):");
                    System.out.println("1. Plato Fuerte");
                    System.out.println("2. Bebida");
                    System.out.println("3. Postre");
                    System.out.print("Ingrese el número de la opción: ");
                    int tipoProducto = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Cree y digite un nuevo código único para el producto (ej: PF01, BE01, PO01): ");
                    String codigo = scanner.nextLine();

                    if (restaurante.buscarProductoPorCodigo(codigo) != null) {
                        System.out.println("Error: Ya existe un producto registrado con el código " + codigo);
                        break;
                    }

                    System.out.print("Ingrese el nombre del producto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese la descripción: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Ingrese el precio base: ");
                    double precioBase = scanner.nextDouble();
                    System.out.print("Ingrese el tiempo de preparación (minutos): ");
                    int tiempoPreparacion = scanner.nextInt();
                    scanner.nextLine();

                    if (tipoProducto == 1) {
                        System.out.print("Ingrese el peso en gramos: ");
                        double pesoGramos = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.println("Seleccione el tipo de preparación (ingrese el número):");
                        System.out.println("1. ASADO | 2. FRITO | 3. HORNEADO | 4. A_LA_PLANCHA");
                        System.out.print("Ingrese el número de la opción: ");
                        int opcionPrep = scanner.nextInt();
                        scanner.nextLine();

                        TipoPreparacion tipoPrep = TipoPreparacion.A_LA_PLANCHA;
                        if (opcionPrep == 1) tipoPrep = TipoPreparacion.ASADO;
                        else if (opcionPrep == 2) tipoPrep = TipoPreparacion.FRITO;
                        else if (opcionPrep == 3) tipoPrep = TipoPreparacion.HORNEADO;

                        System.out.print("¿Aprobó el control de cocción? (ingrese 'true' o 'false'): ");
                        boolean aproboCoccion = scanner.nextBoolean();
                        scanner.nextLine();

                        PlatoFuerte nuevoPlato = new PlatoFuerte(codigo, nombre, descripcion, precioBase, tiempoPreparacion, pesoGramos, tipoPrep, aproboCoccion);
                        restaurante.registrarProducto(nuevoPlato);
                        System.out.println("--> Plato Fuerte registrado exitosamente.");

                    } else if (tipoProducto == 2) {
                        System.out.print("Ingrese el volumen en mililitros: ");
                        double volumenMl = scanner.nextDouble();
                        System.out.print("Ingrese la temperatura de servicio (°C): ");
                        double tempServicio = scanner.nextDouble();
                        System.out.println("Seleccione tipo de temperatura (ingrese el número):");
                        System.out.println("1. FRIA | 2. CALIENTE");
                        System.out.print("Ingrese el número de la opción: ");
                        int opcionTemp = scanner.nextInt();
                        scanner.nextLine();

                        TipoTemperatura tipoTemp = (opcionTemp == 1) ? TipoTemperatura.FRIA : TipoTemperatura.CALIENTE;

                        System.out.print("¿Requiere preparación especial? (ingrese 'true' o 'false'): ");
                        boolean prepEspecial = scanner.nextBoolean();
                        scanner.nextLine();

                        Bebida nuevaBebida = new Bebida(codigo, nombre, descripcion, precioBase, tiempoPreparacion, volumenMl, tempServicio, tipoTemp, prepEspecial);
                        restaurante.registrarProducto(nuevaBebida);
                        System.out.println("--> Bebida registrada exitosamente.");

                    } else if (tipoProducto == 3) {
                        System.out.print("Ingrese el peso en gramos: ");
                        double pesoPostre = scanner.nextDouble();
                        System.out.print("Ingrese la temperatura de almacenamiento (°C): ");
                        double tempAlmacenamiento = scanner.nextDouble();
                        System.out.print("¿Requiere refrigeración? (ingrese 'true' o 'false'): ");
                        boolean requiereRefrigeracion = scanner.nextBoolean();
                        scanner.nextLine();

                        Postre nuevoPostre = new Postre(codigo, nombre, descripcion, precioBase, tiempoPreparacion, pesoPostre, tempAlmacenamiento, requiereRefrigeracion);
                        restaurante.registrarProducto(nuevoPostre);
                        System.out.println("--> Postre registrado exitosamente.");
                    } else {
                        System.out.println("Opción de categoría no válida.");
                    }
                    break;
                case 2:
                    System.out.println("--- 2. REGISTRAR CLIENTE ---");
                    System.out.print("Digite el número de documento de identidad del nuevo cliente a registrar: ");
                    String documento = scanner.nextLine();

                    if (restaurante.buscarClientePorDocumento(documento) != null) {
                        System.out.println("Error: Ya existe un cliente registrado con el documento de identidad " + documento);
                        break;
                    }

                    System.out.print("Ingrese el nombre completo del cliente: ");
                    String nombreCliente = scanner.nextLine();
                    System.out.print("Ingrese el número de teléfono: ");
                    String telefono = scanner.nextLine();
                    System.out.print("Ingrese el correo electrónico: ");
                    String correo = scanner.nextLine();
                    System.out.print("Ingrese la dirección de residencia: ");
                    String direccion = scanner.nextLine();

                    Cliente nuevoCliente = new Cliente(documento, nombreCliente, telefono, correo, direccion);
                    boolean clienteRegistrado = restaurante.registrarCliente(nuevoCliente);

                    if (clienteRegistrado) {
                        System.out.println("--> Cliente registrado exitosamente en el sistema.");
                    } else {
                        System.out.println("--> Error al registrar el cliente.");
                    }
                    break;
                case 3:
                    System.out.println("--- 3. CALCULAR PRECIO DE VENTA DE PRODUCTOS ---");
                    System.out.print("Ingrese el código del producto a consultar: ");
                    String codigoPrecio = scanner.nextLine();

                    Producto productoEncontrado = restaurante.buscarProductoPorCodigo(codigoPrecio);

                    if (productoEncontrado == null) {
                        System.out.println("Error: No se encontró ningún producto registrado con el código " + codigoPrecio);
                    } else {
                        System.out.println("Producto: " + productoEncontrado.getNombre());
                        System.out.println("Precio base: $" + productoEncontrado.getPrecioBase());
                        System.out.println("Precio final de venta (con incrementos y recargos): $" + productoEncontrado.calcularPrecioVenta());
                    }
                    break;
                case 4:
                    System.out.println("--- 4. VERIFICAR CONDICIONES DE CALIDAD ---");
                    System.out.print("Ingrese el código del producto a evaluar: ");
                    String codigoCalidad = scanner.nextLine();

                    Producto productoCalidad = restaurante.buscarProductoPorCodigo(codigoCalidad);

                    if (productoCalidad == null) {
                        System.out.println("Error: No se encontró ningún producto registrado con el código " + codigoCalidad);
                    } else {
                        boolean cumple = productoCalidad.cumpleCondicionesCalidad();
                        System.out.println("Producto: " + productoCalidad.getNombre());
                        if (cumple) {
                            System.out.println("--> ESTADO: APROBADO. El producto cumple con todos los estándares de calidad.");
                        } else {
                            System.out.println("--> ESTADO: RECHAZADO. El producto NO cumple con los estándares de calidad del restaurante.");
                        }
                    }
                    break;
                case 5:
                    System.out.println("--- 5. CONSULTAR PRODUCTOS DISPONIBLES Y RECHAZADOS ---");
                    System.out.println("\n=== PRODUCTOS DISPONIBLES (Aprobados por Calidad) ===");
                    int contadorDisponibles = 0;
                    for (Producto productoItem : restaurante.getListaProductos()) {
                        if (productoItem.cumpleCondicionesCalidad()) {
                            contadorDisponibles++;
                            System.out.println("Código: " + productoItem.getCodigo() +
                                    " | Nombre: " + productoItem.getNombre() +
                                    " | Categoría: " + productoItem.getClass().getSimpleName() +
                                    " | Precio Venta: $" + productoItem.calcularPrecioVenta());
                        }
                    }
                    if (contadorDisponibles == 0) {
                        System.out.println("No hay productos disponibles que cumplan los estándares de calidad.");
                    }
                    System.out.println("\n=== PRODUCTOS RECHAZADOS (No cumplen Calidad) ===");
                    int contadorRechazados = 0;
                    for (Producto productoItem : restaurante.getListaProductos()) {
                        if (!productoItem.cumpleCondicionesCalidad()) {
                            contadorRechazados++;
                            System.out.println("Código: " + productoItem.getCodigo() +
                                    " | Nombre: " + productoItem.getNombre() +
                                    " | Categoría: " + productoItem.getClass().getSimpleName() +
                                    " | Precio Base: $" + productoItem.getPrecioBase());
                        }
                    }
                    if (contadorRechazados == 0) {
                        System.out.println("No hay productos rechazados en el registro.");
                    }
                    break;
                case 6:
                    System.out.println("--- 6. CREAR PEDIDO ---");
                    System.out.print("Digite el número de documento del cliente para realizar el pedido: ");
                    String documentoCliente = scanner.nextLine();
                    Cliente clientePedido = restaurante.buscarClientePorDocumento(documentoCliente);
                    if (clientePedido == null) {
                        System.out.println("Error: No existe ningún cliente registrado con el documento " + documentoCliente + ". Debe registrarlo primero en la opción 2.");
                        break;
                    }
                    System.out.print("Cree y digite un nuevo código único para el pedido (ej: PED-01): ");
                    String codigoPedido = scanner.nextLine();
                    boolean existePedido = false;
                    for (Pedido p : restaurante.getListaPedidos()) {
                        if (p.getCodigo().equalsIgnoreCase(codigoPedido)) {
                            existePedido = true;
                            break;
                        }
                    }
                    if (existePedido) {
                        System.out.println("Error: Ya existe un pedido registrado con el código " + codigoPedido);
                        break;
                    }
                    System.out.println("Seleccione la modalidad de entrega (ingrese el número de la opción):");
                    System.out.println("1. RESTAURANTE (Consumo local o recoger en local)");
                    System.out.println("2. DOMICILIO");
                    System.out.print("Ingrese el número de la opción: ");
                    int opcionModalidad = scanner.nextInt();
                    scanner.nextLine();
                    ModalidadEntrega modalidad = (opcionModalidad == 1) ? ModalidadEntrega.RESTAURANTE : ModalidadEntrega.DOMICILIO;
                    double distanciaKm = 0.0;
                    EmpresaDomicilio empresa = null;
                    if (modalidad == ModalidadEntrega.DOMICILIO) {
                        System.out.print("Ingrese la distancia de entrega en kilómetros (km): ");
                        distanciaKm = scanner.nextDouble();
                        scanner.nextLine();
                        empresa = restaurante.gestionarServicioDomicilio(distanciaKm);
                        if (empresa == null) {
                            System.out.println("Advertencia: Ninguna empresa de domicilio cubre una distancia de " + distanciaKm + " km.");
                        } else {
                            System.out.println("--> Empresa asignada automáticamente: " + empresa.getClass().getSimpleName());
                        }
                    }
                    Pedido nuevoPedido = new Pedido(codigoPedido, LocalDateTime.now(), clientePedido, modalidad, distanciaKm, empresa);
                    System.out.println("\n--- AGREGAR PRODUCTOS AL PEDIDO ---");
                    boolean agregarMasProductos = true;
                    while (agregarMasProductos) {
                        System.out.print("Ingrese el código del producto a agregar (o escriba 'FIN' para terminar): ");
                        String codigoProductoAgregar = scanner.nextLine();
                        if (codigoProductoAgregar.equalsIgnoreCase("FIN")) {
                            agregarMasProductos = false;
                        } else {
                            Producto productoSeleccionado = restaurante.buscarProductoPorCodigo(codigoProductoAgregar);
                            if (productoSeleccionado == null) {
                                System.out.println("Error: El producto con código " + codigoProductoAgregar + " no existe.");
                            } else {
                                System.out.print("Ingrese la cantidad deseada para '" + productoSeleccionado.getNombre() + "': ");
                                int cantidadSolicitada = scanner.nextInt();
                                scanner.nextLine();
                                boolean agregado = nuevoPedido.agregarDetalle(productoSeleccionado, cantidadSolicitada);
                                if (agregado) {
                                    System.out.println("--> " + productoSeleccionado.getNombre() + " (x" + cantidadSolicitada + ") agregado al pedido.");
                                } else {
                                    System.out.println("Error: No se pudo agregar el producto. Verifique que la cantidad sea mayor a 0.");
                                }
                            }
                        }
                    }
                    if (nuevoPedido.getListaDetallePedido().isEmpty()) {
                        System.out.println("Atención: No se agregaron productos al pedido. El pedido no fue guardado.");
                    } else {
                        restaurante.getListaPedidos().add(nuevoPedido);
                        System.out.println("--> Pedido " + codigoPedido + " registrado exitosamente con estado PENDIENTE.");
                    }
                    break;
                case 7:
                    System.out.println("--- 7. GESTIONAR SERVICIO DE DOMICILIO ---");
                    System.out.print("Ingrese la distancia de entrega en kilómetros (km): ");
                    double distanciaDomicilio = scanner.nextDouble();
                    scanner.nextLine();
                    EmpresaDomicilio empresaAsignada = restaurante.gestionarServicioDomicilio(distanciaDomicilio);
                    if (empresaAsignada != null) {
                        double costoEnvio = empresaAsignada.calcularCostoEnvio(distanciaDomicilio);
                        System.out.println("--> SERVICIO DISPONIBLE");
                        System.out.println("Empresa asignada: " + empresaAsignada.getClass().getSimpleName());
                        System.out.println("Distancia evaluada: " + distanciaDomicilio + " km");
                        System.out.println("Costo estimado de envío: $" + costoEnvio);
                    } else {
                        System.out.println("--> SERVICIO NO DISPONIBLE");
                        System.out.println("Error: Ninguna empresa de domicilio (RápidoExpress o EntregaSegura) cubre la distancia de " + distanciaDomicilio + " km.");
                    }
                    break;
                case 8:
                    System.out.println("--- 8. CONFIRMAR PEDIDO ---");
                    System.out.print("Ingrese el código del pedido a confirmar (ej: PED-01): ");
                    String codigoConfirmar = scanner.nextLine();
                    Pedido pedidoAConfirmar = null;
                    for (Pedido p : restaurante.getListaPedidos()) {
                        if (p.getCodigo().equalsIgnoreCase(codigoConfirmar)) {
                            pedidoAConfirmar = p;
                            break;
                        }
                    }
                    if (pedidoAConfirmar == null) {
                        System.out.println("Error: No se encontró ningún pedido registrado con el código " + codigoConfirmar);
                        break;
                    }
                    if (pedidoAConfirmar.getEstado() != EstadoPedido.PENDIENTE) {
                        System.out.println("Atención: El pedido " + codigoConfirmar + " ya fue procesado anteriormente (Estado actual: " + pedidoAConfirmar.getEstado() + ").");
                        break;
                    }
                    boolean confirmado = restaurante.confirmarPedido(pedidoAConfirmar);
                    if (confirmado) {
                        System.out.println("--> ¡PEDIDO CONFIRMADO EXITOSAMENTE!");
                        System.out.println("Cliente: " + pedidoAConfirmar.getCliente().getNombreCompleto());
                        System.out.println("Modalidad: " + pedidoAConfirmar.getModalidadEntrega());
                        if (pedidoAConfirmar.getModalidadEntrega() == ModalidadEntrega.DOMICILIO && pedidoAConfirmar.getEmpresaDomicilio() != null) {
                            System.out.println("Empresa de Domicilio: " + pedidoAConfirmar.getEmpresaDomicilio().getClass().getSimpleName());
                            System.out.println("Costo de Envío: $" + pedidoAConfirmar.getCostoDomicilio());
                        }
                        System.out.println("Valor Total a Pagar: $" + pedidoAConfirmar.getValorTotal());
                    } else {
                        System.out.println("--> ERROR AL CONFIRMAR EL PEDIDO");
                        System.out.println("El pedido no superó la validación. Verifique que:");
                        System.out.println("- El cliente esté registrado.");
                        System.out.println("- Todos los productos pertenezcan al menú y cumplan con los estándares de calidad.");
                        System.out.println("- Las cantidades ingresadas sean mayores a 0.");
                        System.out.println("- Si es un domicilio, la distancia no supere la cobertura de las empresas.");
                    }
                    break;
                case 9:
                    System.out.println("--- 9. CALCULAR VALOR TOTAL DE UN PEDIDO ---");
                    System.out.print("Ingrese el código del pedido a consultar: ");
                    String codigoTotal = scanner.nextLine();
                    Pedido pedidoTotal = restaurante.buscarPedidoPorCodigo(codigoTotal);
                    if (pedidoTotal == null) {
                        System.out.println("Error: No se encontró ningún pedido registrado con el código " + codigoTotal);
                    } else {
                        double totalCalculado = pedidoTotal.calcularValorTotal();
                        System.out.println("--> INFORMACIÓN DEL PEDIDO: " + pedidoTotal.getCodigo());
                        System.out.println("Cliente: " + pedidoTotal.getCliente().getNombreCompleto());
                        System.out.println("Estado actual: " + pedidoTotal.getEstado());
                        System.out.println("Modalidad de entrega: " + pedidoTotal.getModalidadEntrega());
                        System.out.println("Subtotal de productos: $" + pedidoTotal.calcularValorProductos());

                        if (pedidoTotal.getModalidadEntrega() == ModalidadEntrega.DOMICILIO) {
                            String empresaNombre = (pedidoTotal.getEmpresaDomicilio() != null)
                                    ? pedidoTotal.getEmpresaDomicilio().getClass().getSimpleName()
                                    : "No asignada";
                            System.out.println("Empresa de domicilio: " + empresaNombre);
                            System.out.println("Distancia de entrega: " + pedidoTotal.getDistanciaKm() + " km");
                            System.out.println("Costo de envío: $" + pedidoTotal.getCostoDomicilio());
                        }
                        System.out.println("--> VALOR TOTAL CALCULADO: $" + totalCalculado);
                    }
                    break;
                case 10:
                    System.out.println("--- 10. CONSULTAR HISTORIAL DE PEDIDOS DE UN CLIENTE ---");
                    System.out.print("Digite el número de documento del cliente a consultar: ");
                    String documentoHistorial = scanner.nextLine();
                    Cliente clienteHistorial = restaurante.buscarClientePorDocumento(documentoHistorial);
                    if (clienteHistorial == null) {
                        System.out.println("Error: No existe ningún cliente registrado con el documento " + documentoHistorial);
                    } else {
                        List<Pedido> historial = restaurante.consultarHistorialCliente(documentoHistorial);
                        if (historial.isEmpty()) {
                            System.out.println("El cliente " + clienteHistorial.getNombreCompleto() + " no registra pedidos en su historial.");
                        } else {
                            System.out.println("--> HISTORIAL DE PEDIDOS DE: " + clienteHistorial.getNombreCompleto());
                            for (Pedido p : historial) {
                                System.out.println("\n  Código del pedido: " + p.getCodigo() +
                                        " | Estado: " + p.getEstado() +
                                        " | Modalidad: " + p.getModalidadEntrega());
                                System.out.println("  Productos adquiridos:");
                                for (DetallePedido detalle : p.getListaDetallePedido()) {
                                    System.out.println("    - " + detalle.getProducto().getNombre() +
                                            " | Cantidad: " + detalle.getCantidad() +
                                            " | Subtotal: $" + detalle.calcularSubtotal());
                                }
                                if (p.getModalidadEntrega() == ModalidadEntrega.DOMICILIO) {
                                    System.out.println("  Costo de domicilio: $" + p.getCostoDomicilio());
                                }
                                System.out.println("  Valor total del pedido: $" + p.getValorTotal());
                            }
                        }
                    }
                    break;
                case 11:
                    System.out.println("--- 11. ACTUALIZAR ESTADO DE UN PEDIDO ---");
                    System.out.print("Ingrese el código del pedido a actualizar (ej: PED-01): ");
                    String codigoEstado = scanner.nextLine();
                    Pedido pedidoEstado = restaurante.buscarPedidoPorCodigo(codigoEstado);
                    if (pedidoEstado == null) {
                        System.out.println("Error: No se encontró ningún pedido registrado con el código " + codigoEstado);
                    } else {
                        System.out.println("--> INFORMACIÓN DEL PEDIDO: " + pedidoEstado.getCodigo());
                        System.out.println("Cliente: " + pedidoEstado.getCliente().getNombreCompleto());
                        System.out.println("Estado actual: " + pedidoEstado.getEstado());
                        System.out.println("\nSeleccione el nuevo estado deseado (ingrese el número de la opción):");
                        System.out.println("1. CONFIRMADO");
                        System.out.println("2. ENTREGADO");
                        System.out.println("3. CANCELADO");
                        System.out.print("Ingrese el número de la opción: ");
                        int opcionEstado = scanner.nextInt();
                        scanner.nextLine();
                        EstadoPedido nuevoEstado = null;
                        if (opcionEstado == 1) {
                            nuevoEstado = EstadoPedido.CONFIRMADO;
                        } else if (opcionEstado == 2) {
                            nuevoEstado = EstadoPedido.ENTREGADO;
                        } else if (opcionEstado == 3) {
                            nuevoEstado = EstadoPedido.CANCELADO;
                        }
                        if (nuevoEstado == null) {
                            System.out.println("Opción de estado no válida.");
                        } else {
                            boolean actualizado = pedidoEstado.actualizarEstado(nuevoEstado);
                            if (actualizado) {
                                System.out.println("--> ¡ESTADO ACTUALIZADO EXITOSAMENTE!");
                                System.out.println("El pedido " + pedidoEstado.getCodigo() + " cambió su estado a: " + nuevoEstado);
                            } else {
                                System.out.println("--> ERROR AL ACTUALIZAR EL ESTADO");
                                System.out.println("No es posible cambiar de estado " + pedidoEstado.getEstado() + " a " + nuevoEstado + ".");
                                System.out.println("Recuerde las reglas de transición:");
                                System.out.println(" - De PENDIENTE sólo se puede pasar a CONFIRMADO o CANCELADO.");
                                System.out.println(" - De CONFIRMADO sólo se puede pasar a ENTREGADO o CANCELADO.");
                                System.out.println(" - Un pedido en estado ENTREGADO o CANCELADO ya es final y no se puede modificar.");
                            }
                        }
                    }
                    break;
                case 12:
                    System.out.println("--- 12. GENERAR REPORTE GENERAL DE VENTAS ---");
                    double ingresosPropios = restaurante.calcularIngresosPropiosRestaurante();
                    double ingresosDomicilio = restaurante.calcularIngresosTotalesDomicilio();
                    double ingresosTotales = ingresosPropios + ingresosDomicilio;
                    Producto productoMasVendido = restaurante.obtenerProductoMasVendido();
                    String categoriaMayorIngreso = restaurante.obtenerCategoriaMayorIngreso();
                    System.out.println("--> REPORTE GENERAL DE VENTAS Y ESTADÍSTICAS");
                    System.out.println("Ingresos propios del restaurante (Ventas de menú): $" + ingresosPropios);
                    System.out.println("Ingresos totales por domicilios (Recargo de envío): $" + ingresosDomicilio);
                    System.out.println("Ingreso total recaudado (Menú + Domicilios): $" + ingresosTotales);
                    System.out.println("------------------------------------------------");
                    if (productoMasVendido != null) {
                        System.out.println("Producto más vendido: " + productoMasVendido.getNombre() +
                                " (Código: " + productoMasVendido.getCodigo() + ")");
                    } else {
                        System.out.println("Producto más vendido: Aún no hay ventas registradas en pedidos confirmados o entregados.");
                    }
                    System.out.println("Categoría que genera mayores ingresos: " + categoriaMayorIngreso);
                    break;
                case 13:
                    System.out.println("--- SALIENDO DEL SISTEMA... ---");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 13);
        scanner.close();
    }
}