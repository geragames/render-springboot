package com.fich.sarh.auth.Infrastructure.adapter.configuration.datasource;

public class DatabaseContextHolder {
    private static final ThreadLocal<DatabaseType> CONTEXT = new ThreadLocal<>();

    public static void setDatabaseType(DatabaseType databaseType){
        CONTEXT.set(databaseType);
    }

    public static DatabaseType getDatabaseType(){
        return CONTEXT.get();
    }

    public static void clear(){
        CONTEXT.remove();
    }
}
