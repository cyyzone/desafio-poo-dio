import br.com.dio.desafio.dominio.Bootcamp;
import br.com.dio.desafio.dominio.Curso;
import br.com.dio.desafio.dominio.Dev;
import br.com.dio.desafio.dominio.Mentoria;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // --- 1. CRIAÇÃO DO MATERIAL (Conteúdos) ---
        Curso curso1 = new Curso();
        curso1.setTitulo("curso java");
        curso1.setDescricao("descrição curso java");
        curso1.setCargaHoraria(8);

        Curso curso2 = new Curso();
        curso2.setTitulo("curso js");
        curso2.setDescricao("descrição curso js");
        curso2.setCargaHoraria(4);

        Mentoria mentoria = new Mentoria();
        mentoria.setTitulo("mentoria de java");
        mentoria.setDescricao("descrição mentoria java");
        mentoria.setData(LocalDate.now());

        // --- 2. CRIAÇÃO DO BOOTCAMP ---
        Bootcamp bootcamp = new Bootcamp();
        bootcamp.setNome("Bootcamp Java Developer");
        bootcamp.setDescricao("Descrição Bootcamp Java Developer");
        bootcamp.getConteudos().add(curso1);
        bootcamp.getConteudos().add(curso2);
        bootcamp.getConteudos().add(mentoria);

        // --- 3. INÍCIO DOS TESTES ---
        
        // Criamos o Dev "Miguel"
        Dev devMiguel = new Dev();
        devMiguel.setNome("Miguel");

        System.out.println("------- TESTE 1: Inscrição e Relatório Inicial -------");
        devMiguel.inscreverBootcamp(bootcamp);
        // Aqui deve listar todos os cursos como "Pendentes" e XP zero
        devMiguel.exibirRelatorio(); 

        System.out.println("\n------- TESTE 2: Tentar se inscrever duas vezes (Erro) -------");
        // Ao tentar se inscrever de novo, deve aparecer uma mensagem de ERRO (em vermelho no console)
        devMiguel.inscreverBootcamp(bootcamp);

        System.out.println("\n------- TESTE 3: Progresso e XP -------");
        // Vamos concluir o primeiro curso
        System.out.println(">> Progredindo no primeiro conteúdo...");
        devMiguel.progredir(); 
        
        // O relatório agora deve mostrar 1 Concluído, 2 Pendentes e o XP atualizado
        devMiguel.exibirRelatorio();

        System.out.println("\n------- TESTE 4: Cancelar Inscrição -------");
        // Vamos testar se o método de cancelar funciona
        devMiguel.cancelarInscricao(bootcamp);
        
        // Ao exibir o relatório final:
        // - O que foi concluído DEVE permanecer lá.
        // - O que estava pendente DEVE sumir.
        devMiguel.exibirRelatorio();
    }
}