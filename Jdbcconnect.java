package jdbc;

import java.sql.*;

public class Jdbcconnect {

    // Adjust these to your MySQL installation
    private static final String HOST = "localhost";
    private static final int PORT = 3306;
    private static final String DB = "testdb";

    // Normal valid credentials (TC1 / TC2)
    private static final String VALID_USER = "root";
    private static final String VALID_PASS = "deepthi2245";

    // Intentionally invalid credentials for TC3
    private static final String INVALID_USER = "bad_user";
    private static final String INVALID_PASS = "bad_pass";

    public static void main(String[] args) {
        System.out.println("Janise Deepthi YP -- 2117240070124");

        // TC1: Connect to DB successfully
        testConnection(VALID_USER, VALID_PASS);

        // TC2: Execute SELECT query
        testSelectQuery(VALID_USER, VALID_PASS, "SELECT id, name, position FROM employees");

        // TC3: Invalid credentials
        testConnection(INVALID_USER, INVALID_PASS);

        // TC4: Empty query -> expected output: No results
        testSelectQuery(VALID_USER, VALID_PASS, ""); // handled gracefully

        // TC5: Malformed SQL -> Expect SQLSyntaxErrorException or general SQLException
        testSelectQuery(VALID_USER, VALID_PASS, "SELEC * FROM employees"); // deliberate typo
    }

    private static String jdbcUrl() {
        return String.format("jdbc:mysql://%s:%d/%s?serverTimezone=UTC&useSSL=false", HOST, PORT, DB);
    }

    private static void testConnection(String user, String pass) {
        System.out.println("---- Test Connection (user=" + user + ") ----");
        try (Connection conn = DriverManager.getConnection(jdbcUrl(), user, pass)) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("TC1: Connected");
            } else {
                System.out.println("TC1: Could not establish connection (unknown reason).");
            }
        } catch (SQLException e) {
            // Distinguish invalid credentials from other errors
            String sqlState = e.getSQLState(); // may help diagnose
            System.out.println("TC3: Access denied (or connection failed): " + e.getMessage());
        }
        System.out.println();
    }

    private static void testSelectQuery(String user, String pass, String sql) {
        System.out.println("---- Test Query: \"" + (sql.isBlank() ? "<EMPTY>" : sql) + "\" ----");
        // handle "empty query" explicitly per TC4
        if (sql == null || sql.isBlank()) {
            System.out.println("TC4: No results (empty query).");
            System.out.println();
            return;
        }

        try (Connection conn = DriverManager.getConnection(jdbcUrl(), user, pass);
             Statement stmt = conn.createStatement()) {

            // Execute the query — using executeQuery for SELECT-like queries
            try (ResultSet rs = stmt.executeQuery(sql)) {
                boolean any = false;
                ResultSetMetaData md = rs.getMetaData();
                int cols = md.getColumnCount();
                while (rs.next()) {
                    any = true;
                    // Print each row as column=value pairs
                    StringBuilder row = new StringBuilder();
                    for (int i = 1; i <= cols; i++) {
                        row.append(md.getColumnLabel(i)).append("=").append(rs.getString(i));
                        if (i < cols) row.append(", ");
                    }
                    System.out.println(row.toString());
                }
                if (!any) {
                    System.out.println("Query executed successfully but no rows returned.");
                } else {
                    System.out.println("TC2: Records displayed above.");
                }
            }

        } catch (SQLSyntaxErrorException syntaxEx) {
            System.out.println("TC5: Malformed SQL -> SQLSyntaxErrorException: " + syntaxEx.getMessage());
        } catch (SQLException e) {
            // Generic SQL exception handling (e.g., authentication, network)
            System.out.println("SQLException occurred: SQLState=" + e.getSQLState() +
                               ", ErrorCode=" + e.getErrorCode() + ", Message=" + e.getMessage());
            // If you want to identify invalid credentials here:
            if (e.getMessage().toLowerCase().contains("access denied") ||
                e.getMessage().toLowerCase().contains("authentication")) {
                System.out.println("Likely authentication/authorization failure (invalid credentials).");
            }
        }
        System.out.println();
    }
}
