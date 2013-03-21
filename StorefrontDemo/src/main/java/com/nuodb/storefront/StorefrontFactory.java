package com.nuodb.storefront;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.nuodb.storefront.dal.IStorefrontDao;
import com.nuodb.storefront.dal.StorefrontDao;
import com.nuodb.storefront.service.DataGeneratorService;
import com.nuodb.storefront.service.IDataGeneratorService;
import com.nuodb.storefront.service.IStorefrontService;
import com.nuodb.storefront.service.StorefrontService;

/**
 * Factory for creating Storefront services and schema managers. To keep code in
 * this demo straightforward, this factory is used in lieu of dependency
 * injection, e.g. via the Spring framework.
 */
public class StorefrontFactory {
    private static SessionFactory sessionFactory;
    private static Configuration configuration;

    static {
        configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    private StorefrontFactory() {
    }

    public static SchemaExport createSchemaExport() {
        return new SchemaExport(configuration);
    }

    public static IStorefrontService createStorefrontService() {
        return new StorefrontService(createStorefrontDao());
    }

    public static IDataGeneratorService createDataGeneratorService() {
        return new DataGeneratorService(createStorefrontDao());
    }

    private static IStorefrontDao createStorefrontDao() {
        StorefrontDao dao = new StorefrontDao();
        dao.setSessionFactory(sessionFactory);
        return dao;
    }
}