package Backend.riscv;

public final class InstruccionRiscV {

    public enum Operacion {
        // RV32I
        LUI, AUIPC, JAL, JALR,
        BEQ, BNE, BLT, BGE, BLTU, BGEU,
        LB, LH, LW, LBU, LHU, SB, SH, SW,
        ADDI, SLTI, SLTIU, XORI, ORI, ANDI, SLLI, SRLI, SRAI,
        ADD, SUB, SLL, SLT, SLTU, XOR, SRL, SRA, OR, AND,
        ECALL, EBREAK,
        // RV32M
        MUL, MULH, MULHSU, MULHU, DIV, DIVU, REM, REMU,
        // RV32D
        FLD, FSD,
        FADD_D, FSUB_D, FMUL_D, FDIV_D, FSQRT_D, FMIN_D, FMAX_D,
        FSGNJ_D, FSGNJN_D, FSGNJX_D,
        FEQ_D, FLT_D, FLE_D,
        FCVT_W_D, FCVT_WU_D, FCVT_D_W, FCVT_D_WU,
        // li 
        LI
    }

    public static final int RNE = 0, RTZ = 1, RDN = 2, RUP = 3, RMM = 4, DYN = 7;

    final Operacion operacion;
    final int rd;
    final int rs1;
    final int rs2;
    final int inmediato;
    final int redondeo;
    final int linea;
    final String fuente;

    InstruccionRiscV(Operacion operacion, int rd, int rs1, int rs2, int inmediato, int redondeo, int linea, String fuente) {
        this.operacion = operacion;
        this.rd = rd;
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.inmediato = inmediato;
        this.redondeo = redondeo;
        this.linea = linea;
        this.fuente = fuente;
    }

    public Operacion getOperacion() {
        return operacion;
    }

    public int getLinea() {
        return linea;
    }

    public String getFuente() {
        return fuente;
    }

    @Override
    public String toString() {
        return "LINEA " + linea + ": " + fuente;
    }
}
