package net.royalmind.library.lightquery;

import com.zaxxer.hikari.HikariConfig;

public class SimpleSourceBuilder {
    
    private String url = "", user = "", password = "";
    
    public SimpleSourceBuilder setUrl(final String url) {
        this.url = url;
        return this;
    }
    
    public SimpleSourceBuilder setUser(final String user) {
        this.user = user;
        return this;
    }
    
    public SimpleSourceBuilder setPassword(final String password) {
        this.password = password;
        return this;
    }
    
    public HikariConfig build() {
        if (this.url.isEmpty() || this.user.isEmpty()) return null;
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(this.url);
        hikariConfig.setUsername(this.user);
        hikariConfig.setPassword(this.password);
        hikariConfig.addDataSourceProperty("cachePrepStmts", true);
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", 250);
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        return hikariConfig;
    }
}
