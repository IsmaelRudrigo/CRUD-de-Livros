import crud.CRUD;
import entities.Livro;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static CRUD crud = new CRUD();
    public static void main(String[] args) {

        menu();

        int opcao;
        boolean sair = false;

        try{
            while(!sair){

                System.out.println("Escolha uma opção - 5 para ver opções");
                opcao = sc.nextInt();
                sc.nextLine();

                switch(opcao){
                    case 0:
                        sair = true;
                        break;

                    case 1:
                        cadastrar();
                        break;

                    case 2:
                        deletar();
                        break;

                    case 3:
                        alteraLivro();
                        break;

                    case 4:
                        crud.listarLivros();
                        break;

                    case 5:
                        menu();
                        break;
                }
            }
        } catch(InputMismatchException e){
            System.out.println("Formato inválido! Digite apenas números!");
        }

    }

    public static void cadastrar(){

        System.out.println("Título do livro: ");
        String titulo = sc.nextLine();

        System.out.println("Autor do livro: ");
        String autor = sc.nextLine();

        System.out.println("Código ISBN: ");
        String isbn = sc.nextLine();

        Livro livro = new Livro(titulo, autor, isbn);
        crud.cadastraLivro(livro);

    }

    public static void deletar(){
        System.out.println("Digite o código ISBN do livro a ser deletado: ");
        String isbn = sc.nextLine();

        crud.apagaLivro(isbn);
    }

    public static void alteraLivro(){
        System.out.println("Digite o código ISBN do livro: ");
        String isbn = sc.nextLine();

        System.out.println("Digite o novo título: ");
        String titulo = sc.nextLine();

        crud.alteraTituloLivro(isbn, titulo);
    }

    public static void menu(){
        System.out.println("Pressione \n" +
                "0 - para parar\n" +
                "1 - para cadastrar livro\n" +
                "2 - para deletar um livro\n" +
                "3 - para alterar o título de um livro\n" +
                "4 - para listar livros cadastrados\n" +
                "5 - para ver opções");
    }

}