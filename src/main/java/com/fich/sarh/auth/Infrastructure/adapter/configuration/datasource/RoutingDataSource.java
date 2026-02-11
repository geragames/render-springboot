package com.fich.sarh.auth.Infrastructure.adapter.configuration.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        DatabaseType dbtype = DatabaseContextHolder.getDatabaseType();
        return dbtype != null? dbtype: DatabaseType.PROD;
    }
}
