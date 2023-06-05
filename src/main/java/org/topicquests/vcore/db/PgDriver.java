package org.topicquests.vcore.db;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.SqlClient;
import org.topicquests.vcore.config.Configurator;

/**
 * A generic driver for PostgreSQL
 * NOTE: the config properties must be configured to specific settings
 * @author jackpark
 * @liecnse Apache2
 */
public class PgDriver {

    public static SqlClient createPgPool(final Configurator config, final Vertx vertx) {
        JsonObject props = config.getDataServerProperties();
        PgConnectOptions connectOptions = new PgConnectOptions()
                .setPort(props.getInteger("port"))
                .setHost(props.getString("host"))
                .setDatabase(props.getString("database"))
                .setUser(props.getString("user"))
                .setPassword(props.getString("password"));

        // Pool options
        PoolOptions poolOptions = new PoolOptions()
                .setMaxSize(props.getInteger("poolsize"));

        // Create the client
        SqlClient client = PgPool.client(vertx, connectOptions, poolOptions);
        return client;
    }
}
