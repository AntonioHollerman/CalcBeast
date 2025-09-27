module api {
    requires java.sql;
    requires org.slf4j;
    requires google.adk;
    requires com.google.genai;

    requires okhttp3;
    requires com.google.common;
    requires kotlin.stdlib;
    requires okio;

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jdk8;
    requires com.fasterxml.jackson.datatype.jsr310;

    exports agent;
    exports database;
}