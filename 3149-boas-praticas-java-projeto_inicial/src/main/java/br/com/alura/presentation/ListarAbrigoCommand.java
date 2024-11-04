package br.com.alura.presentation;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.AbrigoService;

public class ListarAbrigoCommand implements Command {
    private final AbrigoService abrigoService = new AbrigoService(ClientHttpConfiguration.getInstance());

    @Override
    public void execute() throws Exception {
        abrigoService.listarAbrigo();
    }
}
