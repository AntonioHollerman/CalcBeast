module api {
    requires java.sql;
    requires org.slf4j;
    requires google.adk;
    requires com.google.genai;

    exports agent;
    exports database;
}