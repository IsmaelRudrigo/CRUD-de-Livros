package entities;

public class Livro {

    private final String titulo;
    private final String autor;
    private final String isbn;

    public Livro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }

        if((obj == null) || (this.getClass() != obj.getClass())){
            return false;
        }

        String objIsbn = ((Livro) obj).getIsbn();
        return this.isbn.equals(((Livro) obj).getIsbn());
    }

    @Override
    public int hashCode() {
        return this.isbn.hashCode() + 35;
    }
}
