package biblioteca.dominio;

public abstract class Usuario {
    private final String nome;
    private int quantidadeEmprestada = 0;

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidadeEmprestada() {
        return quantidadeEmprestada;
    }

    // Limite varia por tipo de usuario.
    public abstract int limiteItens();

    public boolean atingiuLimite() {
        return quantidadeEmprestada >= limiteItens();
    }

    // Controlados pela biblioteca.dominio.Biblioteca (mesmo pacote), como consequencia de emprestar()/devolver().
    void registrarEmprestimo() {
        quantidadeEmprestada++;
    }

    void registrarDevolucao() {
        quantidadeEmprestada--;
    }
}