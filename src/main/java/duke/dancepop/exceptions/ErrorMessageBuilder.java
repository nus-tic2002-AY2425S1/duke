package duke.dancepop.exceptions;

import duke.dancepop.enums.ActualCommandEnum;
import duke.dancepop.enums.CommandEnum;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class ErrorMessageBuilder {

    private final List<String> messages = new ArrayList<>();
    private final CommandEnum command;

    /**
     * @param command enum used to determine error message format
     */
    public ErrorMessageBuilder(CommandEnum command) {
        this.command = command;
    }

    /**
     * @return builder with missing description message appended
     */
    public ErrorMessageBuilder missingDescription() {
        String formattedMessage = MessageFormat.format(ExceptionConsts.DESCRIPTION_EMPTY_ERROR, command.getValue());
        messages.add(formattedMessage);
        return this;
    }

    /**
     * @return builder with missing index message appended
     */
    public ErrorMessageBuilder missingIndex() {
        String formattedMessage = MessageFormat.format(ExceptionConsts.INDEX_EMPTY_ERROR, command.getValue());
        messages.add(formattedMessage);
        return this;
    }

    /**
     * @return builder with missing from message appended
     */
    public ErrorMessageBuilder missingFrom() {
        String formattedMessage = MessageFormat.format(ExceptionConsts.FROM_EMPTY_ERROR, command.getValue());
        messages.add(formattedMessage);
        return this;
    }

    /**
     * @return builder with missing to message appended
     */
    public ErrorMessageBuilder missingTo() {
        String formattedMessage = MessageFormat.format(ExceptionConsts.TO_EMPTY_ERROR, command.getValue());
        messages.add(formattedMessage);
        return this;
    }

    /**
     * @return builder with missing by message appended
     */
    public ErrorMessageBuilder missingBy() {
        String formattedMessage = MessageFormat.format(ExceptionConsts.BY_EMPTY_ERROR, command.getValue());
        messages.add(formattedMessage);
        return this;
    }

    /**
     * @return builder with additional arguments message appended
     */
    public ErrorMessageBuilder additionalArguments() {
        String formattedMessage = MessageFormat.format(ExceptionConsts.ADDITIONAL_INPUT_ERROR, ActualCommandEnum.getActualCommand(command));
        messages.add(formattedMessage);
        return this;
    }

    /**
     * @return builder with integer parse message appended
     */
    public ErrorMessageBuilder integerParse() {
        String formattedMessage = MessageFormat.format(ExceptionConsts.INTEGER_PARSE_ERROR, ActualCommandEnum.getActualCommand(command));
        messages.add(formattedMessage);
        return this;
    }

    /**
     * @return builder with index out of bounds message appended
     */
    public ErrorMessageBuilder indexOutOfBounds() {
        messages.add(ExceptionConsts.INDEX_INVALID_ERROR);
        return this;
    }

    /**
     * @return builder with unknown command message appended
     */
    public ErrorMessageBuilder unknownCommand() {
        messages.add(ExceptionConsts.UNKNOWN_COMMAND_ERROR);
        return this;
    }

    /**
     * @return builder with unknown arguments message appended
     */
    public ErrorMessageBuilder unknownArguments() {
        String formattedMessage = MessageFormat.format(ExceptionConsts.UNKNOWN_ARGUMENTS_ERROR, ActualCommandEnum.getActualCommand(command));
        messages.add(formattedMessage);
        return this;
    }

    /**
     * @return all messages built previously if any
     */
    public List<String> build() {
        return messages;
    }
}
