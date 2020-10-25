package Negocio.Interfaces;

import Negocio.Entidades.ReservaProductoId;

public interface IReservaProducto {

    void guardarReservaProducto(ReservaProductoId reservaProducto);

    void buscarProductosReserva(Long idReserva);
}
