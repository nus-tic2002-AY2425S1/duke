package duke.dancepop.exceptions;

import duke.dancepop.enums.ActualCommandEnum;
import duke.dancepop.enums.CommandEnum;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class ErrorMessageBuilder {

    private final List<String> messages = new ArrayList<>();
    private final CommandEnum command;

    public ErrorMessageBuilder(CommandEnum command) {
        this.command = command;
    }

    public ErrorMessageBuilder missingDescription() {
        String formattedMessage = MessageFormat.format(ExceptionConsts.DESCRIPTION_EMPTY_ERROR, command.getValue());
        messages.add(formattedMessage);
        return this;
    }

    public ErrorMessageBuilder missingIndex() {
        String formattedMessage = MessageFormat.format(ExceptionConsts.INDEX_EMPTY_ERROR, command.getValue());
        messages.add(formattedMessage);
        return this;
    }

    public ErrorMessageBuilder missingFrom() {
        String formattedMessage = MessageFormat.format(ExceptionConsts.FROM_EMPTY_ERROR, command.getValue());
        messages.add(formattedMessage);
        return this;
    }

    public ErrorMessageBuilder missingTo() {
        String formattedMessage = MessageFormat.format(ExceptionConsts.TO_EMPTY_ERROR, command.getValue());
        messages.add(formattedMessage);
        return this;
    }

    public ErrorMessageBuilder missingBy() {
        String formattedMessage = MessageFormat.format(ExceptionConsts.BY_EMPTY_ERROR, command.getValue());
        messages.add(formattedMessage);
        return this;
    }

    public ErrorMessageBuilder additionalArguments() {
        String formattedMessage = MessageFormat.format(ExceptionConsts.ADDITIONAL_INPUT_ERROR, ActualCommandEnum.getActualCommand(command));
        messages.add(formattedMessage);
        return this;
    }

    public ErrorMessageBuilder integerParse() {
        String formattedMessage = MessageFormat.format(ExceptionConsts.INTEGER_PARSE_ERROR, ActualCommandEnum.getActualCommand(command));
        messages.add(formattedMessage);
        return this;
    }

    public ErrorMessageBuilder indexOutOfBounds() {
        messages.add(ExceptionConsts.INDEX_INVALID_ERROR);
        return this;
    }

    public ErrorMessageBuilder unknownCommand() {
        messages.add(ExceptionConsts.UNKNOWN_COMMAND_ERROR);
        return this;
    }

    public ErrorMessageBuilder unknownArguments() {
        String formattedMessage = MessageFormat.format(ExceptionConsts.UNKNOWN_ARGUMENTS_ERROR, ActualCommandEnum.getActualCommand(command));
        messages.add(formattedMessage);
        return this;
    }

    public List<String> build() {
        return messages;
    }
}
