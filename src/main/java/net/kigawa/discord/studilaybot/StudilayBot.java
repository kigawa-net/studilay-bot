package net.kigawa.discord.studilaybot;

import net.kigawa.app.Application;
import net.kigawa.app.Terminal;
import net.kigawa.file.FileUtil;
import net.kigawa.log.Formatter;
import net.kigawa.log.Logger;
import net.kigawa.log.TerminalHandler;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

public class StudilayBot implements Application {
    private static final String TOKEN = "OTMwNjY4MjA5OTYxNTg2Njg4.Yd5OUA.yTjA7yIg44nvTNSK8JWYh1A4V_U";
    private static final String OWNER_ID = "617576850654232608";
    private static final String COMMAND_PREFIX = "st";
    public static boolean J_LINE = true;
    public static StudilayBot studilayBot;

    public static void main(String[] args) throws IOException {
        new Logger(StudilayBot.class.getName(), null, Level.INFO, FileUtil.getRelativeFile("logs")).enable();

        for (String s : args) {
            if (s.equals("deny-jline")) J_LINE = false;
        }

        studilayBot = new StudilayBot();
        studilayBot.enable();
    }

    @Override
    public void onEnable() {
        studilayBot.addModule(Logger.getInstance());
        studilayBot.enableAddModule(new Terminal(J_LINE));

        for (Handler handler : Logger.getLogger("").getHandlers()) {
            if (handler instanceof ConsoleHandler) {
                Logger.getLogger("").removeHandler(handler);
            }
        }
        Logger.getLogger("").addHandler(new TerminalHandler(Terminal.terminal, new Formatter()));

        info("enable studilay bot");
        Terminal.terminal.addOnRead(s -> {
            if (s.equals("stop")) disable();
        });

    }

    @Override
    public void onDisable() {
        info("disable studilay bot");

    }
}
