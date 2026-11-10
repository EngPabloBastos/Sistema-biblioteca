public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(10);

        // Cadastro do acervo
        Livro l1 = new Livro("L001", "Dom Casmurro", "Machado de Assis");
        Livro l2 = new Livro("L002", "Clean Code", "Robert C. Martin");
        Revista r1 = new Revista("R001", "Superinteressante", 450);
        Revista r2 = new Revista("R002", "Veja", 2900);
        biblioteca.adicionarItem(l1);
        biblioteca.adicionarItem(l2);
        biblioteca.adicionarItem(r1);
        biblioteca.adicionarItem(r2);

        // Cadastro de usuarios
        Aluno aluno = new Aluno("Ana");
        Professor professor = new Professor("Carlos");

        biblioteca.listarAcervo();

        System.out.println("\n=== EMPRESTIMOS ===");
        // Aluno ate o limite de 3 (bem-sucedidos)
        biblioteca.emprestar(aluno, l1);
        biblioteca.emprestar(aluno, l2);
        biblioteca.emprestar(aluno, r1);
        // Quarto item: recusado por limite atingido
        biblioteca.emprestar(aluno, r2);
        // Professor tem limite maior: consegue o mesmo item recusado ao aluno
        biblioteca.emprestar(professor, r2);

        System.out.println("\n=== DEVOLUCOES ===");
        biblioteca.devolver(aluno, l1, 20); // livro: 6 dias de atraso -> R$ 3,00
        biblioteca.devolver(aluno, r1, 5);  // revista no prazo -> sem multa

        System.out.println();
        biblioteca.listarAcervo();
    }
}