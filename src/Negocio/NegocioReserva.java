package Negocio;

import Datos.ReservaJpaController;
import Negocio.Entidades.Habitacion;
import Negocio.Entidades.Producto;
import Negocio.Entidades.Reserva;
import Negocio.Entidades.ReservaProducto;
import Negocio.Interfaces.IReserva;
import com.github.lgooddatepicker.components.TimePicker;
import com.mysql.jdbc.StringUtils;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JPanel;

public class NegocioReserva implements IReserva {

    private final ReservaJpaController reservaController = new ReservaJpaController();
    private static NegocioReserva negocioReserva;
    private final NegocioHabitacion negocioHabitacion = NegocioHabitacion.getNegocioHabitacion();
    private SimpleDateFormat sdf;

    public static NegocioReserva getNegocioReserva() {
        if (negocioReserva == null) {
            return negocioReserva = new NegocioReserva();
        }
        return negocioReserva;
    }

    @Override
    public void crearReserva(Reserva reserva) {
        if (reserva != null) {
            reservaController.create(reserva);
        }
    }

    @Override
    public List<Reserva> getReservas() {
        return reservaController.findReservaEntities();
    }

    @Override
    public String validarReserva(JDateChooser jdcFechaEntrada, JDateChooser jdcFechaSalida, TimePicker tpHoraEntrada, TimePicker tpHoraSalida) {

        String mensaje = "ok";

        if (jdcFechaEntrada.getDate() == null || jdcFechaSalida.getDate() == null || StringUtils.isEmptyOrWhitespaceOnly(tpHoraEntrada.getText())
                || StringUtils.isEmptyOrWhitespaceOnly(tpHoraSalida.getText())) {
            mensaje = "Debes completar todos los campos.";
        }
        if (mensaje.equals("ok")) {

            long dias = UtilidadGeneral.calcularDiasEntreFechas(jdcFechaEntrada.getDate(), jdcFechaSalida.getDate());

            String fechaEntrada = UtilidadGeneral.DateToString(jdcFechaEntrada.getDate());
            String fechaSalida = UtilidadGeneral.DateToString(jdcFechaSalida.getDate());

            // Si 13-09-2020 == 13-09-2020 es válido (dia = 1)
            if (dias <= 0 && !fechaEntrada.equals(fechaSalida)) {
                mensaje = "Verifica las fechas de alojamiento.";
            }
        }
        return mensaje;
    }

    @Override
    public float calcularPrecioTotalAlojamiento(Habitacion habitacion, Date fechaEntrada, Date fechaSalida) {

        long dias = UtilidadGeneral.calcularDiasEntreFechas(fechaEntrada, fechaSalida);

        if (dias == 0) {
            dias = 1;
        }
        return dias * habitacion.getPrecioDia();
    }

    @Override
    public int getCountReservasByEstado(String estado) {
        return reservaController.getCountReservasByState(estado);
    }

    @Override
    public void modificarReserva(Reserva reserva) throws Exception {
        reservaController.edit(reserva);
    }

    @Override
    public List<Reserva> ordenarReservas(String campo, boolean otraTabla) {
        if (otraTabla) {
            return reservaController.reservasOrderByInOtherTable(campo);
        }
        return reservaController.reservasOrderBy(campo);
    }

    @Override
    public List<Reserva> buscarReserva(String busqueda) {
        return reservaController.buscarReserva(busqueda);
    }

    @Override
    public Reserva encontrarReserva(Long id) {
        return reservaController.findReserva(id);
    }

    @Override
    public float calcularPrecioTotalXProducto(int cantProd, float precioProd) {
        return cantProd * precioProd;
    }

    // Si se modifica el precio de venta de un producto
    // Se recorren todas las reservas que contiene el producto
    // Y se actualiza el precio total de cada producto de cada reserva
    // Finalmente, se modifica cada reserva
    public void actualizarPrecioTotalProdReserva(Producto productoModificado) throws Exception {
        int indiceProd;
        float precioProductos, precioTotalProd;
        sdf = new SimpleDateFormat("dd-MM-yyyy");
        for (ReservaProducto reservaProd : productoModificado.getReservas()) {
            // Solo a las reservas que no están cobradas se le aplica el aumento
            if (!reservaProd.getReserva().getEstado().equals("Cobrado")) {
                precioTotalProd = calcularPrecioTotalXProducto(reservaProd.getCantProducto(), productoModificado.getPrecioVenta());
                try {
                    indiceProd = reservaProd.getReserva().getProductos().indexOf(reservaProd);
                    reservaProd.getReserva().getProductos().get(indiceProd).setPrecioTotal(precioTotalProd);

                    precioProductos = negocioReserva.calcularPrecioTotalProductos(reservaProd.getReserva().getProductos());
                    reservaProd.getReserva().setPrecioProductos(precioProductos);
                    reservaProd.getReserva().setPrecioTotal(reservaProd.getReserva().getPrecioAlojamiento() + precioProductos);

                    reservaController.edit(reservaProd.getReserva());
                } catch (Exception ex) {
                    System.out.println("Error al actualizar el precio total del prod. de la reserva. " + ex);
                }
            }
        }
    }

    @Override
    public float calcularPrecioTotalProductos(List<ReservaProducto> reservaProductos) {
        float precioTotal = 0;
        if (reservaProductos.size() > 0) {
            for (ReservaProducto reservaProd : reservaProductos) {
                precioTotal += reservaProd.getPrecioTotal();
            }
        }
        return precioTotal;
    }

    @Override
    public ReservaProducto verificarExistenciaProdReserva(List<ReservaProducto> reservaProd, Long idProdSeleccionado) {
        for (ReservaProducto resProd : reservaProd) {
            if (resProd.getId().getProductoId().equals(idProdSeleccionado)) {
                return resProd;
            }
        }
        return null;
    }

    @Override
    public void cobrarReserva(Reserva reserva) throws Exception {
        if (reserva != null) {
            reserva.setEstado("Cobrado");
            reserva.getHabitacion().setEstado("Disponible");
            negocioHabitacion.modificarHabitacion(reserva.getHabitacion());
            negocioReserva.modificarReserva(reserva);
        }
    }

    @Override
    public Reserva encontrarReservaHabitacion(Habitacion hab) {
        // Se devuelve la reserva que no está cobrada 
        for (Reserva reserva : getReservas()) {
            if (reserva.getHabitacion().getId().equals(hab.getId()) && !reserva.getEstado().equals("Cobrado")) {
                return reserva;
            }
        }
        return null;
    }

    @Override
    public void imprimirDetallesReserva(JPanel jpDetallesReserva) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Detalles de Reserva");

        job.setPrintable((Graphics graphics, PageFormat pageFormat, int pageIndex) -> {
            // Solo una página
            if (pageIndex > 0) {
                return Printable.NO_SUCH_PAGE;
            }
            Graphics2D g2 = (Graphics2D) graphics;
            g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            g2.scale(0.44, 0.90);

            jpDetallesReserva.paint(g2);
            return Printable.PAGE_EXISTS;
        });

        boolean ok = job.printDialog();
        if (ok) {
            try {
                jpDetallesReserva.setBackground(Color.WHITE);
                job.print();
                jpDetallesReserva.setBackground(new Color(164, 221, 234));
            } catch (PrinterException e) {
                System.err.println("Error al imprimir los detalles de la reserva. " + e.getMessage());
            }
        }
    }
}
