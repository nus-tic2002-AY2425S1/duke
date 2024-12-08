package jibberJabber.commands;

public enum Keywords {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    COMPLETE("complete"),
    FIND("find"),;

    private final String keyword;

    Keywords(String keyword) {
        this.keyword = keyword;
    }
    public String getKeyword() {
        return keyword;
    }
}
