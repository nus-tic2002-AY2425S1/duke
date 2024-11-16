package duke.dancepop.exceptions;

import duke.dancepop.enums.ActualCommandEnum;
import duke.dancepop.enums.CommandEnum;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A builder for constructing error messages based on specific command contexts.
 */
public class ErrorMessageBuilder {

    private final List<String> messages = new ArrayList<>();
    private final CommandEnum command;

    /**
     * Constructs an ErrorMessageBuilder for a specific command.
     *
     * @param command The command enum used to determine the error message format.
     */
    public ErrorMessageBuilder(CommandEnum command) {
        this.command = command;
    }

    /**
     * Appends a missing description error message.
     *
     * @return This builder instance with the missing description message appended.
     */
    public ErrorMessageBuilder missingDescription() {
        String formattedMessage = MessageFormat.format(ExceptionConsts.DESCRIPTION_EMPTY_ERROR, command.getValue());
        messages.add(formattedMessage);
        return this;
    }

    /**
     * Appends a missing index error message.
     *
     * @return This builder instance with the missing index message appended.
     */
    public ErrorMessageBuilder missingIndex() {
        String formattedMessage = MessageFormat.format(ExceptionConsts.INDEX_EMPTY_ERROR, command.getValue());
        messages.add(formattedMessage);
        return this;
    }

    /**
     * Appends a missing "from" field error message.
     *
     * @return This builder instance with the missing "from" message appended.
     */
    public ErrorMessageBuilder missingFrom() {
        String formattedMessage = MessageFormat.format(ExceptionConsts.FROM_EMPTY_ERROR, command.getValue());
        messages.add(formattedMessage);
        return this;
    }

    /**
     * Appends a missing "to" field error message.
     *
     * @return This builder instance with the missing "to" message appended.
     */
    public ErrorMessageBuilder missingTo() {
        String formattedMessage = MessageFormat.format(ExceptionConsts.TO_EMPTY_ERROR, command.getValue());
        messages.add(formattedMessage);
        return this;
    }

    /**
     * Appends a missing "by" field error message.
     *
     * @return This builder instance with the missing "by" message appended.
     */
    public ErrorMessageBuilder missingBy() {
        String formattedMessage = MessageFormat.format(ExceptionConsts.BY_EMPTY_ERROR, command.getValue());
        messages.add(formattedMessage);
        return this;
    }

    /**
     * Appends an error message for additional, unnecessary arguments.
     *
     * @return This builder instance with the additional arguments message appended.
     */
    public ErrorMessageBuilder additionalArguments() {
        String formattedMessage = MessageFormat.format(ExceptionConsts.ADDITIONAL_INPUT_ERROR, ActualCommandEnum.getActualCommand(command));
        messages.add(formattedMessage);
        return this;
    }

    /**
     * Appends an error message for invalid integer parsing.
     *
     * @return This builder instance with the integer parse error message appended.
     */
    public ErrorMessageBuilder integerParse() {
        String formattedMessage = MessageFormat.format(ExceptionConsts.INTEGER_PARSE_ERROR, ActualCommandEnum.getActualCommand(command));
        messages.add(formattedMessage);
        return this;
    }

    /**
     * Appends an index out of bounds error message.
     *
     * @return This builder instance with the index out of bounds message appended.
     */
    public ErrorMessageBuilder indexOutOfBounds() {
        messages.add(ExceptionConsts.INDEX_INVALID_ERROR);
        return this;
    }

    /**
     * Appends an unknown command error message.
     *
     * @return This builder instance with the unknown command message appended.
     */
    public ErrorMessageBuilder unknownCommand() {
        messages.add(ExceptionConsts.UNKNOWN_COMMAND_ERROR);
        return this;
    }

    /**
     * Appends an error message for unknown or invalid arguments.
     *
     * @return This builder instance with the unknown arguments message appended.
     */
    public ErrorMessageBuilder unknownArguments() {
        String formattedMessage = MessageFormat.format(ExceptionConsts.UNKNOWN_ARGUMENTS_ERROR, ActualCommandEnum.getActualCommand(command));
        messages.add(formattedMessage);
        return this;
    }

    /**
     * Builds and returns the list of error messages accumulated so far.
     *
     * @return A list of error messages.
     */
    public List<String> build() {
        return messages;
    }
}
