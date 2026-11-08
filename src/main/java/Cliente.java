
    import java.util.ArrayList;
    import java.util.List;

    public class Cliente {

        private String numeroDocumento;
        private String nombreCompleto;
        private String telefono;
        private String correoElectronico;
        private String direccionResidencia;
        private List<Pedido> listaPedidos;

        public Cliente(String numeroDocumento, String nombreCompleto, String telefono,
                       String correoElectronico, String direccionResidencia) {
            this.numeroDocumento = numeroDocumento;
            this.nombreCompleto = nombreCompleto;
            this.telefono = telefono;
            this.correoElectronico = correoElectronico;
            this.direccionResidencia = direccionResidencia;
            this.listaPedidos = new ArrayList<>();
        }

        public String getNumeroDocumento() {
            return numeroDocumento;
        }

        public void setNumeroDocumento(String numeroDocumento) {
            this.numeroDocumento = numeroDocumento;
        }

        public String getNombreCompleto() {
            return nombreCompleto;
        }

        public void setNombreCompleto(String nombreCompleto) {
            this.nombreCompleto = nombreCompleto;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getCorreoElectronico() {
            return correoElectronico;
        }

        public void setCorreoElectronico(String correoElectronico) {
            this.correoElectronico = correoElectronico;
        }

        public String getDireccionResidencia() {
            return direccionResidencia;
        }

        public void setDireccionResidencia(String direccionResidencia) {
            this.direccionResidencia = direccionResidencia;
        }

        public List<Pedido> getListaPedidos() {
            return listaPedidos;
        }

        public void setListaPedidos(List<Pedido> listaPedidos) {
            this.listaPedidos = listaPedidos;
        }

        public boolean agregarPedidoAlHistorial(Pedido pedido) {
            boolean sePuedeAgregar = true;

        if (pedido == null) {
        sePuedeAgregar = false;

        }else{
            for (Pedido p : listaPedidos) {
                    if (p.getCodigo().equals(pedido.getCodigo())) {
                    sePuedeAgregar = false;
                    }
                }
            }

            if (sePuedeAgregar) {
                listaPedidos.add(pedido);
            }
            return sePuedeAgregar;
        }

        public List<Pedido> consultarHistorialCompras() {
            List<Pedido> historial = new ArrayList<>(listaPedidos);
            return historial;

        }
    }

