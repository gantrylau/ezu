package com.gl.infra.hibernate;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import java.io.Serializable;

/**
 * hibernate自己的实现只有 PhysicalNamingStrategyStandardImpl，我需要的是驼峰转成下划线的格式！！！
 *
 * @author gantrylau
 * @since 2017/2/18
 */
public class PhysicalNamingStrategyJpaImpl implements PhysicalNamingStrategy, Serializable {
    @Override
    public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return convert(name);
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return convert(name);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return convert(name);
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return convert(name);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return convert(name);
    }

    private Identifier convert(Identifier identifier) {
        if (identifier == null || identifier.getText() == null || "".equals(identifier.getText())) {
            return identifier;
        }
        String regex = "([a-z])([A-Z])";
        String replacement = "$1_$2";
        String newName = identifier.getText().replaceAll(regex, replacement).toLowerCase();
        return Identifier.toIdentifier(newName);
    }
}
