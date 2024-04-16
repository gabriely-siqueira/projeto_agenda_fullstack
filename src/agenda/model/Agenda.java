package agenda.model;

import agenda.model.DAO.ContatoDAO;
import agenda.view.Tela;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Agenda {

    public void iniciarAgenda() {
        Tela tela = new Tela();

        // Estrura do menu
        String menu = ":: Agenda de contatos ::\n\n" +
                "[1] Cadastrar novo contato.\n" +
                "[2] Buscar contato.\n" +
                "[3] Alterar dados de um contato.\n" +
                "[4] Excluir um contato.\n" +
                "[0] Sair.\n";

        // mantém o menu ativo
        boolean isAtivo = true;

        // Loop principal do sistema
        while (isAtivo) {
            String opcao = tela.informar(menu, "Informe uma opção do menu", -1);

            switch (opcao) {
                case "1":
                    // cadastrar
                    cadastrarContato(tela);
                    break;
                case "2":
                    // buscar
                    try {
                        buscarContato(tela);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    //editarContato(tela);
                    break;
                case "4":
                    //excluirContato(tela);
                    break;
                default:
                    int sair = tela.confirmar("Deseja sair?", "Encerrar", 3);
                    if (sair == 0) {
                        isAtivo = false;
                        tela.mostrar("Encerrando o sistema...", "Encerrando", 1);
                    }
                    break;
            }

        }
    }

    private void cadastrarContato(Tela tela) {
        // Solicita informações do novo contato ao usuário
        String contatoNome = tela.informar("Informe o nome do contato", "Nome", 1);
        String contatoEmail = tela.informar("Informe o email do contato", "Email", 1);
        String contatoFone = tela.informar("Informe o telefone do contato", "Telefone", 1);

        // Verifica se alguma informação está em branco
        if (contatoNome.isEmpty() || contatoEmail.isEmpty() || contatoFone.isEmpty()) {
            tela.mostrar("Por favor, preencha todas as informações para cadastrar o contato.", "Digite novamente", 0);
            return;
        }

        // Cria um novo objeto Contato e o adiciona à lista de contatos
        Contato contato = new Contato(contatoNome, contatoEmail, contatoFone);

        //Envia o Contato (preenchido) para o DB
        ContatoDAO contatoDAO = new ContatoDAO();
        contatoDAO.cadastrarContatoDAO(contato);
        // Mostra a mensagem de sucesso na tela
        tela.mostrar("Contato cadastrado com sucesso!", "Cadastro", 1);
    }

    private void buscarContato(Tela tela) throws SQLException {
        ContatoDAO contatoDAO = new ContatoDAO();
        ResultSet resultado = contatoDAO.buscarContatoDAO(0); //busca todos os registros
        if (!resultado.next()) {//se não tiver registro
            tela.mostrar("Nenhum contato registrado", "Contatos", 0);
        } else {
            String contatos = "";
            do {
                contatos += "ID: " + resultado.getInt("id")
                        + "\nNome: " + resultado.getString("nome")
                        + "\nEmail: " + resultado.getString("email")
                        + "\nFone: " + resultado.getString("fone")
                        + "\n\n";
            } while (resultado.next());
            // mostra resultado
            tela.mostrar(contatos, "Contatos", 1);
        }
    }
}
/* 
    private void editarContato(Tela tela) {
        buscarContato(tela);

        // Pede para o usuário digitar o ID
        String idDoContatoStr = tela.informar("Informe o ID do contato a ser alterado", "Alterado", 1);
        if (idDoContatoStr.isEmpty()) {
            tela.mostrar("Por favor, digite o ID do contato.", "Digite novamente", 0);
            return;
        }
        int idDoContato = Integer.parseInt(idDoContatoStr);
        try {
            for (int i = 0; i < Lista.getInstance().size(); i++) {
                if (idDoContato == (i + 1)) {
                    String nome = tela.informar("Informe o nome do contato", "Nome", 1);
                    String email = tela.informar("Informe o email do contato", "Email", 1);
                    String fone = tela.informar("Informe o telefone do contato", "Telefone", 1);

                    // Verifica se alguma informação está em branco
                    if (nome.isEmpty() || email.isEmpty() || fone.isEmpty()) {
                        tela.mostrar("Por favor, preencha todas as informações para alterar o contato.",
                                "Digite novamente", 0);
                        return;
                    }

                    // Atualiza as informações do contato
                    Lista.getInstance().get(i).setNome(nome);
                    Lista.getInstance().get(i).setEmail(email);
                    Lista.getInstance().get(i).setFone(fone);

                    // Exibe mensagem de sucesso apenas após atualizar todas as informações
                    tela.mostrar("Contato alterado com sucesso!", "Alteração do contato", 1);
                    return; // Sai do método após atualizar o contato
                }
            }
            // Se chegou aqui, o ID do contato não foi encontrado

            tela.mostrar("ID de contato inválido", "ERRO", 0);
        } catch (Exception e) {
            tela.mostrar("Informe um ID válido", "ERRO", 0);
        }
    }
*/

/* 
    private void excluirContato(Tela tela) {
        buscarContato(tela);
    
        // Pede para o usuário digitar o ID
        String idDoContatoStr = tela.informar("Informe o ID do contato a ser excluído", "Excluir", 1);
        
        // Verifica se o ID está vazio ou não é um número
        if (idDoContatoStr.isEmpty()) {
            tela.mostrar("ID de contato inválido. Por favor, digite um número inteiro válido.", "ERRO", 0);
            return;
        }
        
        int idDoContato = Integer.parseInt(idDoContatoStr);
    
        try {
            if (Lista.getInstance().size() > 0) {
                for (int i = 0; i < Lista.getInstance().size(); i++) {
                    if (idDoContato == (i + 1)) {
                        Lista.getInstance().remove(i);
                        tela.mostrar("Contato removido com sucesso!", "Exclusão de contato", 1);
                        return;
                    }
                }
            }
            // Se chegou aqui, o ID do contato não foi encontrado
            tela.mostrar("ID de contato inválido", "ERRO", 0);
        } catch (IndexOutOfBoundsException e) {
            tela.mostrar("ID de contato inválido", "ERRO", 0);
        }
    }
    */
