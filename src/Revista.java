public class Revista extends ItemBiblioteca {
    private final int edicao;

    public Revista(String codigo, String titulo, int edicao) {
        super(codigo, titulo);
        this.edicao = edicao;
    }

    public int getEdicao() {
        return edicao;
    }

    @Override
    public int prazoDias() {
        return 7;
    }

    @Override
    public double multaPorDia() {
        return 1.00;
    }
}