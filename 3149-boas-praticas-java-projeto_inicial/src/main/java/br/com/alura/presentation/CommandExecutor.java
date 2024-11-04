package br.com.alura.presentation;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private Map<Integer, Command> commands = Map.ofEntries(
            Map.entry(1, new ListarAbrigoCommand()),
            Map.entry(2, new CadastrarAbrigoCommand()),
            Map.entry(3, new ListarPetsPorAbrigoCommand()),
            Map.entry(4, new ImportarPetsDoAbrigoCommand()),
            Map.entry(5, new ExitAppCommand())
    );

    public void executeCommand(Command command) throws Exception {
        command.execute();
    }

    public Command getCommand(int opcao) {
        return commands.get(opcao);
    }
}