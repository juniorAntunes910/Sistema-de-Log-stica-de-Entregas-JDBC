    package Entidades;

    public class EntregaClienteMotorista{
            private int idEntrega;
            private String nomeMotorista;
            private String nomeCliente;
            private String status;

            public EntregaClienteMotorista(int idEntrega, String nomeMotorista, String nomeCliente, String status){
                this.idEntrega = idEntrega;
                this.nomeMotorista = nomeMotorista;
                this.nomeCliente = nomeCliente;
                this.status = status;
        }

        public int getIdEntrega() {
            return idEntrega;
        }

        public void setIdEntrega(int idEntrega) {
            this.idEntrega = idEntrega;
        }

        public String getNomeMotorista() {
            return nomeMotorista;
        }

        public void setNomeMotorista(String nomeMotorista) {
            this.nomeMotorista = nomeMotorista;
        }

        public String getNomeCliente() {
            return nomeCliente;
        }

        public void setNomeCliente(String nomeCliente) {
            this.nomeCliente = nomeCliente;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "EntregaClienteMotorista{" +
                    "idEntrega=" + idEntrega +
                    ", nomeMotorista='" + nomeMotorista + '\'' +
                    ", nomeCliente='" + nomeCliente + '\'' +
                    ", status='" + status + '\'' +
                    '}';
        }
    }

