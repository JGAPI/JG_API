# JG_API
![JG_API Logo](https://i.gyazo.com/a2e8fe48ff262584be665e1bc5d2bfc3.png)
## What is it?
`JG_API` is a Java API wrapper made for use with the Guilded REST API. JG_API has built in features such as an automatic queue system to try to avoid rate limiting from the Guilded API. Much of JG_API's system is inspired by the ideas of Java Discord API known as JDA.
## How do I use it?
### Getting Started
```java
    private static JG_API jg_api;
    private static final String GUILDED_SERVER_ID = "";
    private static final String GUILDED_TOKEN = "";
    
    public static void main(String[] args) throws ClientBuildException {
        JG_API.ClientBuilder cb = new JG_API.ClientBuilder();
        cb.setToken(GUILDED_TOKEN);
        jg_api = cb.build();
        jg_api.login();
        jg_api.start();
    }
```
## Wiki

## Download Latest Release