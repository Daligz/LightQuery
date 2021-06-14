package net.royalmind.library.lightquery;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariPool {

    private final HikariDataSource hikariDataSource;

    public HikariPool(final HikariConfig hikariConfig) {
        this.hikariDataSource = new HikariDataSource(hikariConfig);
    }

    public <T> T execute(final ConnectionCallback<T> callback) {
        try (final Connection conn = this.hikariDataSource.getConnection()) {
            return callback.doInConnection(conn);
        } catch (final SQLException ex) {
            throw new IllegalStateException("Error during execution.", ex);
        }
    }

    public void close() {
        if (this.hikariDataSource.isClosed()) return;
        this.hikariDataSource.close();
    }

    public interface ConnectionCallback<T> {
        public T doInConnection(final Connection connection);
    }
}
