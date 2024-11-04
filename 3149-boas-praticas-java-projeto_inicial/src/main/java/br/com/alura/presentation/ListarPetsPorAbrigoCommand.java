package br.com.alura.presentation;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.PetService;

public class ListarPetsPorAbrigoCommand implements Command {
    private final PetService petService = new PetService(ClientHttpConfiguration.getInstance());
    @Override
    public void execute() throws Exception {
        petService.listarPetsPorAbrigo();
    }
}
