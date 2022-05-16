# JGAPI

## What is it?

## How do I use it?

### Getting Started
```java
    private static JG_API jg_api;
    private static final String GUILDED_SERVER_ID = "";
    private static final String GUILDED_TOKEN = "";
    
    public static void main(String[] args) throws ClientBuildException {
        JG_API.ClientBuilder cb = new JG_API.ClientBuilder();
        cb.setParentServerId(GUILDED_SERVER_ID);
        cb.setToken(GUILDED_TOKEN);
        jg_api = cb.build();
        jg_api.login();
        jg_api.start();
    }
```

## Wiki

## Download Latest Release