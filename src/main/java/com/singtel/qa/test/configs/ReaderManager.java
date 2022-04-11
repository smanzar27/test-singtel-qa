package com.singtel.qa.test.configs;

public class ReaderManager {

    private static final ReaderManager readerManager = new ReaderManager();
    protected static GUIConfig guiConfigReader;
    protected static APIConfig apiConfigReader;

    private ReaderManager() { }

    public static ReaderManager getInstance( ) {
        return readerManager;
    }
    public GUIConfig getGUIConfigReader() { return (guiConfigReader == null) ? new GUIConfig() : guiConfigReader; }
    public APIConfig getApiConfigReader() { return (apiConfigReader == null) ? new APIConfig() : apiConfigReader; }
}
