import java.util.Locale;

public class Biblioteca {
    private final ItemBiblioteca[] acervo;
    private final Usuario[] emprestadoPara;
    private int totalItens = 0;

    public Biblioteca(int capacidade) {
        this.acervo = new ItemBiblioteca[capacidade];
        this.emprestadoPara = new Usuario[capacidade];
    }

    public boolean adicionarItem(ItemBiblioteca item) {
        if (totalItens >= acervo.length) {
            System.out.println("Acervo cheio: nao foi possivel adicionar " + item.getTitulo());
            return false;
        }
        acervo[totalItens] = item;
        totalItens++;
        return true;
    }

    private int indiceDe(ItemBiblioteca item) {
        for (int i = 0; i < totalItens; i++) {
            if (acervo[i] == item) {
                return i;
            }
        }
        return -1;
    }

    public boolean emprestar(Usuario usuario, ItemBiblioteca item) {
        int i = indiceDe(item);
        if (i < 0) {
            System.out.println("RECUSADO: item nao pertence ao acervo.");
            return false;
        }
        if (!item.isDisponivel()) {
            System.out.println("RECUSADO: \"" + item.getTitulo() + "\" ja esta emprestado.");
            return false;
        }
        if (usuario.atingiuLimite()) {
            System.out.println("RECUSADO: " + usuario.getNome() + " atingiu o limite de "
                    + usuario.limiteItens() + " itens.");
            return false;
        }
        item.marcarEmprestado();
        usuario.registrarEmprestimo();
        emprestadoPara[i] = usuario;
        System.out.println("OK: \"" + item.getTitulo() + "\" emprestado a " + usuario.getNome()
                + " (prazo: " + item.prazoDias() + " dias).");
        return true;
    }

    // Devolve o item e retorna o valor da multa (0 se estiver no prazo).
    public double devolver(Usuario usuario, ItemBiblioteca item, int diasComItem) {
        int i = indiceDe(item);
        if (i < 0 || emprestadoPara[i] != usuario) {
            System.out.println("RECUSADO: " + usuario.getNome() + " nao possui \"" + item.getTitulo() + "\".");
            return 0.0;
        }
        item.marcarDevolvido();
        usuario.registrarDevolucao();
        emprestadoPara[i] = null;
        double multa = item.calcularMulta(diasComItem);
        System.out.println(String.format(Locale.forLanguageTag("pt-BR"),
                "OK: \"%s\" devolvido por %s apos %d dias. Multa: R$ %.2f",
                item.getTitulo(), usuario.getNome(), diasComItem, multa));
        return multa;
    }

    // Um unico laco, sem if de tipo: o polimorfismo decide.
    public void listarAcervo() {
        System.out.println("=== ACERVO ===");
        for (int i = 0; i < totalItens; i++) {
            System.out.println(acervo[i].descricao());
        }
    }
}