package com.ai.spring.mcpserver.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class PostgresService {

    private static final Logger logger = LoggerFactory.getLogger(PostgresService.class);
    private final JdbcTemplate jdbcTemplate;

    private static final Pattern VALID_TABLE_NAME = Pattern.compile("^[a-zA-Z_][a-zA-Z0-9_]*$");

    /**
     * Lists all tables in the PostgreSQL public schema.
     */
    @Tool(description = "[PostgreSQL] List all tables in the database.")
    public List<String> listTables() {
        String sql = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'public'";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    /**
     * Gets the count of rows in a specific table.
     */
    @Tool(description = "[PostgreSQL] Get the count of rows in a specific table.")
    public int getRowCount(String tableName) {
        validateTableName(tableName);
        String sql = "SELECT COUNT(*) FROM " + tableName;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    /**
     * Gets all rows from a specific table.
     */
    @Tool(description = "[PostgreSQL] Get all rows from a specific table.")
    public List<Map<String, Object>> getAllRows(String tableName) {
        validateTableName(tableName);
        String sql = "SELECT * FROM " + tableName;
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * Execute a complex SQL query and return results.
     */
    @Tool(description = "[PostgreSQL] Execute a complex SQL query and return results.")
    public List<Map<String, Object>> executeComplexQuery(String sql) {
        logger.info("Executing complex SQL query: {}", sql);
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * Validates the table name to prevent SQL injection.
     */
    private void validateTableName(String tableName) {
        if (!VALID_TABLE_NAME.matcher(tableName).matches()) {
            throw new IllegalArgumentException("Invalid table name: " + tableName);
        }
    }
}
