package Backend.riscv;

// ERROR RISC-V
public class ErrorRiscV extends Exception {

    private final int linea;

    public ErrorRiscV(String mensaje, int linea) {
        super(mensaje);
        this.linea = linea;
    }

    public int getLinea() {
        return linea;
    }

    @Override
    public String toString() {
        return (linea > 0 ? "LINEA " + linea + ": " : "") + getMessage();
    }
}
