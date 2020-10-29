package trackitnus.ui;

import javafx.beans.property.StringProperty;

public class HelpCard {
    private String command;
    private String action;

    public HelpCard(String action, String command) {
        this.action = action;
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public String getAction() {
        return action;
    }
}
