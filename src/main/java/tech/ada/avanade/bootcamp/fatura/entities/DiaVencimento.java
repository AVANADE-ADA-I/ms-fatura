package tech.ada.avanade.bootcamp.fatura.entities;

public enum DiaVencimento {
    CINCO(5),
    DEZ(10),
    QUINZE(15),
    VINTE(20),
    VINTE_CINCO(25);
    final private Integer dia;
    DiaVencimento(Integer dia) {
        this.dia = dia;
    }

    public Integer getDia() {return dia;}
}
