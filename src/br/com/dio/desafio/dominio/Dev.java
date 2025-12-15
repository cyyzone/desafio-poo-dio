package br.com.dio.desafio.dominio;

import java.util.*;

public class Dev {
    private String nome;
    private Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();
    private Set<Conteudo> conteudosConcluidos = new LinkedHashSet<>();

    public void inscreverBootcamp(Bootcamp bootcamp){
        // Evolução A: Verificação de duplicidade
        if(bootcamp.getDevsInscritos().contains(this)){
            System.err.println("O Dev " + this.nome + " já está inscrito neste Bootcamp!");
            return;
        }

        this.conteudosInscritos.addAll(bootcamp.getConteudos());
        bootcamp.getDevsInscritos().add(this);
    }

    // Evolução B: Cancelar matrícula
    public void cancelarInscricao(Bootcamp bootcamp) {
        if (!bootcamp.getDevsInscritos().contains(this)) {
            System.err.println("Você não está inscrito neste Bootcamp para cancelar!");
            return;
        }

        // Remove apenas os conteúdos que ainda não foram concluídos (estão na lista de inscritos)
        this.conteudosInscritos.removeAll(bootcamp.getConteudos());
        
        // Remove o dev da lista do bootcamp
        bootcamp.getDevsInscritos().remove(this);
        
        System.out.println("Inscrição cancelada com sucesso para: " + bootcamp.getNome());
    }

    // Evolução C: Relatório formatado
    public void exibirRelatorio() {
        System.out.println("======== RELATÓRIO DE PERFORMANCE ========");
        System.out.println("Dev: " + this.nome);
        System.out.println("XP Total: " + this.calcularTotalXp());
        
        System.out.println("--- Conteúdos Concluídos ---");
        if (this.conteudosConcluidos.isEmpty()) {
            System.out.println("[Nenhum conteúdo concluído]");
        } else {
            // Usando stream para imprimir bonito
            this.conteudosConcluidos.forEach(c -> System.out.println("- " + c.getTitulo()));
        }
        
        System.out.println("--- Conteúdos Pendentes ---");
         if (this.conteudosInscritos.isEmpty()) {
            System.out.println("[Nenhum conteúdo pendente]");
        } else {
            this.conteudosInscritos.forEach(c -> System.out.println("- " + c.getTitulo()));
        }
        System.out.println("==========================================");
    }

    public void progredir() {
        Optional<Conteudo> conteudo = this.conteudosInscritos.stream().findFirst();
        if(conteudo.isPresent()) {
            this.conteudosConcluidos.add(conteudo.get());
            this.conteudosInscritos.remove(conteudo.get());
        } else {
            System.err.println("Você não está matriculado em nenhum conteúdo!");
        }
    }

    public double calcularTotalXp() {
        Iterator<Conteudo> iterator = this.conteudosConcluidos.iterator();
        double soma = 0;
        while(iterator.hasNext()){
            double next = iterator.next().calcularXp();
            soma += next;
        }
        return soma;

        /*return this.conteudosConcluidos
                .stream()
                .mapToDouble(Conteudo::calcularXp)
                .sum();*/
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Conteudo> getConteudosInscritos() {
        return conteudosInscritos;
    }

    public void setConteudosInscritos(Set<Conteudo> conteudosInscritos) {
        this.conteudosInscritos = conteudosInscritos;
    }

    public Set<Conteudo> getConteudosConcluidos() {
        return conteudosConcluidos;
    }

    public void setConteudosConcluidos(Set<Conteudo> conteudosConcluidos) {
        this.conteudosConcluidos = conteudosConcluidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dev dev = (Dev) o;
        return Objects.equals(nome, dev.nome) && Objects.equals(conteudosInscritos, dev.conteudosInscritos) && Objects.equals(conteudosConcluidos, dev.conteudosConcluidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, conteudosInscritos, conteudosConcluidos);
    }
}
