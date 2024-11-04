package br.com.alura.presentation;

public class ExitAppCommand implements Command {
    @Override
    public void execute() throws Exception {
        System.exit(0);
    }
}
