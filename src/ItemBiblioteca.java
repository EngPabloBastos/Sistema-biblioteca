import java.util.Locale;

public abstract class ItemBiblioteca {
    private final String codigo;
    private final String titulo;
    private boolean disponivel = true;

    public ItemBiblioteca(String codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }


    public abstract int prazoDias();

    public abstract double multaPorDia();


    void marcarEmprestado() {
        this.disponivel = false;
    }

    void marcarDevolvido() {
        this.disponivel = true;
    }

    // Multa para uma devolucao feita apos 'diasComItem' dias de posse.
    public double calcularMulta(int diasComItem) {
        int atraso = diasComItem - prazoDias();
        return atraso > 0 ? atraso * multaPorDia() : 0.0;
    }


    public String descricao() {
        return String.format(Locale.forLanguageTag("pt-BR"),
                "[%s] %s - %s | prazo: %d dias | multa: R$ %.2f/dia | %s",
                codigo, getClass().getSimpleName(), titulo, prazoDias(), multaPorDia(),
                disponivel ? "disponivel" : "emprestado");
    }
}