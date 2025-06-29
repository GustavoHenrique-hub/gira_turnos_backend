package lbty.giraturnos.back.GiraTurnosAPI.infra.projection;

public interface VisitaSummary {
    Long   getId();
    String getUnidadeNome();
    String getTecnicoNome();
    String getDataHoraInicioVisita();
    String getDataHoraFimVisita();
    String getObjetivoDaVisita();
}
