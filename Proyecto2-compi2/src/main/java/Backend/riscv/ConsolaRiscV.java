package Backend.riscv;

public interface ConsolaRiscV {

    void escribir(String texto);

    String leerLinea() throws InterruptedException;
}
