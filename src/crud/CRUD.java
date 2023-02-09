package crud;

import db.DB;
import entities.Livro;

import java.sql.*;

public class CRUD {

    public void cadastraLivro(Livro livro){

        PreparedStatement st = null;
        int l = encontraLivro(livro.getIsbn());

        if(l<0){

            try{
                Connection conn = DB.getConnection();
                st = conn.prepareStatement(
                        "INSERT INTO livros (Titulo, Autor, ISBN) " +
                                "VALUES (?, ?, ?)");

                st.setString(1, livro.getTitulo());
                st.setString(2, livro.getAutor());
                st.setString(3, livro.getIsbn());

                st.executeUpdate();
            } catch(SQLException e){
                System.out.println(e.getMessage());
            }

        }else{
            System.out.println("Livro já existe na base de dados");
        }

    }

    public void apagaLivro(String isbn){

        PreparedStatement st = null;
        int l = encontraLivro(isbn);

        if(l >0){
            try{
                Connection conn = DB.getConnection();
                st = conn.prepareStatement(
                        "DELETE FROM livros "
                                +"WHERE ISBN=?");

                st.setString(1, isbn);
                st.executeUpdate();

                System.out.println("Livro com codigo ISBN: " + isbn + " removido com sucesso");

            } catch(SQLException e){
                System.out.println(e.getMessage());
            }

        }else{
            System.out.println("Não foi possível encontrar o livro com código ISBN: " + isbn);
        }
    }

    public void alteraTituloLivro(String isbn, String titulo){

        int l = encontraLivro(isbn);
        if(l>0){
            try{
                Connection conn = DB.getConnection();
                PreparedStatement st = conn.prepareStatement(
                        "UPDATE livros "
                                +"SET Titulo=? "
                                +"WHERE ISBN=?");

                st.setString(1, titulo);
                st.setString(2, isbn);
                st.executeUpdate();

                System.out.println("Título do livro com codigo ISBN: " + isbn + " alterado com sucesso");

            } catch(SQLException e){
                System.out.println(e.getMessage());
            }

        }else{
            System.out.println("Não foi possível encontrar livro com código ISBN: " + isbn);
        }
    }

    public void listarLivros(){

        int cont = 0;
        Statement st = null;
        try{
            Connection conn = DB.getConnection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM livros");

            while(rs.next()){
                System.out.println();
                System.out.println("Título: " + rs.getString(1));
                System.out.println("Autor: " + rs.getString(2));
                System.out.println("ISBN: " + rs.getString(3));
                cont = 1;
            }
            if(cont == 0){
                System.out.println("Não há nenhum livro cadastrado na base de dados");
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public int encontraLivro(String isbn){

        Connection conn = null;
        Statement st = null;

        try{
            conn = DB.getConnection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM livros WHERE ISBN=" + isbn);
            if(rs.next()){
                return 1;
            }else{
                return -1;
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return -1;
    }
}
