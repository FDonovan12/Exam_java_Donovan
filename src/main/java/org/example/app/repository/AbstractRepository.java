package org.example.app.repository;


import org.example.app.entity.EntityInterface;
import org.example.app.service.DBConnect;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.app.service.Color.*;


public abstract class AbstractRepository<T extends EntityInterface> {
    protected String tableName;

    protected Map<String, List<T>> entityCache = new HashMap<>();
    protected static Map<String, ResultSet> entityCache2 = new HashMap<>();

    protected static Long queriesFromMap = 0L;
    protected static Long queriesFromRS = 0L;

    private String queryFields2(Map<String, Object> fields) {
        StringBuilder query = new StringBuilder(" WHERE ");
        for (Map.Entry<String, Object> mapEntry : fields.entrySet()) {
            query.append(mapEntry.getKey()).append(" ");
            if (mapEntry.getValue() instanceof String) {
                if (!((String) mapEntry.getValue()).toLowerCase().contains("between")) {
                    query.append("= ");
                    query.append("'").append(mapEntry.getValue()).append("'");
                } else {
                    query.append(mapEntry.getValue());
                }
            } else {
                query.append("= ");
                query.append(mapEntry.getValue());
            }
            query.append(" AND ");
        }
        return query.substring(0, query.length() - 5);
    }

    private String queryFields(Map<String, Object> fields) {
        StringBuilder query = new StringBuilder();
        for (Map.Entry<String, Object> mapEntry : fields.entrySet()) {
            query.append(mapEntry.getKey()).append(" ");
            boolean isString = mapEntry.getValue() instanceof String;
            boolean containBetween = mapEntry.getValue().toString().toLowerCase().contains("between");
            if (containBetween) {
                query.append(mapEntry.getValue());
            } else {
                query.append("= ");
                if (isString) {
                    query.append("'").append(mapEntry.getValue()).append("'");
                } else {
                    query.append(mapEntry.getValue());
                }
            }
            query.append(" AND ");
        }
        return query.substring(0, query.length() - 5);
    }

    private String queryOrderBy(Map<String, Boolean> orderBy) {
        StringBuilder query = new StringBuilder(" ORDER BY ");
        for (Map.Entry<String, Boolean> mapEntry : orderBy.entrySet()) {
            query.append(mapEntry.getKey());
            if (mapEntry.getValue()) {
                query.append(" ASC");
            } else {
                query.append(" DESC");
            }
            query.append(", ");
        }
        return query.substring(0, query.length() - 2);
    }

    public final List<T> findBy(Map<String, Object> fields, Integer limit, Map<String, Boolean> orderBy, String join) {
        List<T> resultQuery = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + tableName;
            if (join != null) {
                query += join;
                query += " AND ";
            }
            if (join == null && fields != null) {
                query += " WHERE ";
            }

            if (fields != null && !fields.isEmpty()) {
                query += queryFields(fields);
            }
            if (orderBy != null && !orderBy.isEmpty()) {
                query += queryOrderBy(orderBy);
            }
            if (limit != null) {
                query += " LIMIT " + limit;
            }
//            if (entityCache.get(query) != null) {
//                queriesFromMap++;
//                return entityCache.get(query);
//            }
            ResultSet resultSet = leDeuxiemeTrucQueKevinVeut(query);
            while (resultSet.next()) {
                resultQuery.add(getObject(resultSet));
            }
//            queriesFromRS++;
//            entityCache.put(query, resultQuery);
        } catch (
                Exception e) {
            System.out.println(ANSI_BACKGROUND_RED + "findBy : " + e + ANSI_RESET);
        }
        return resultQuery;
    }

    public final List<T> findBy() {
        return findBy(null, null, null, null);
    }

    public final List<T> findBy(String join) {
        return findBy(null, null, null, join);
    }

    public final List<T> findBy(Map<String, Object> fields, String join) {
        return findBy(fields, null, null, join);
    }

    public final List<T> findBy(Map<String, Object> fields) {
        return findBy(fields, null, null, null);
    }

    public final List<T> findBy(Map<String, Object> fields, Integer limit) {
        return findBy(fields, limit, null, null);
    }

    public final List<T> findBy(Map<String, Object> fields, Map<String, Boolean> orderBy) {
        return findBy(fields, null, orderBy, null);
    }

    public final List<T> findBy(Map<String, Object> fields, Integer limit, Map<String, Boolean> orderBy) {
        return findBy(fields, limit, orderBy, null);
    }

    public final List<T> findAll() {
        return findBy();
    }

    public final T findOneBy(Map<String, Object> fields, Map<String, Boolean> orderBy) {
        List<T> list = findBy(fields, orderBy);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public final T findOneBy(Map<String, Object> fields) {
        List<T> list = findBy(fields);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public final T findOneBy(Map<String, Object> fields, String join) {
        List<T> list = findBy(fields, join);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

//    private boolean checkedCache()

    public T save(T object) {
        try {
            String query = "";
            if (object.getId() == null) {
                query = insert(object);
                ResultSet res = leDeuxiemeTrucQueKevinVeut(query);
                ResultSet res2 = leDeuxiemeTrucQueKevinVeut("SELECT last_insert_id() FROM " + tableName);
                res2.next();
                String idString = res2.getString(1);
                Long id = Long.parseLong(idString);
                object.setId(id);
            } else {
                query = update(object);
                ResultSet res = leDeuxiemeTrucQueKevinVeut(query);
            }
            return object;
        } catch (Exception e) {
            System.out.println(ANSI_BACKGROUND_RED + "update : " + e + ANSI_RESET);
        }
        return null;
    }

    public void delete(T object) {
        try {
            String query = "DELETE FROM " + tableName + " WHERE id = " + object.getId();
            ResultSet res = leDeuxiemeTrucQueKevinVeut(query);
        } catch (Exception e) {
            System.out.println(ANSI_BACKGROUND_RED + "delete : " + e + ANSI_RESET);
        }
    }

    protected abstract T getObject(ResultSet resultSet);

    protected abstract String insert(T object);
    protected abstract String update(T object);

    protected ResultSet leDeuxiemeTrucQueKevinVeut(String query) {
        try {
            Statement stmt = DBConnect.getInstance().createStatement();
            System.out.println(ANSI_BACKGROUND_GREEN + " query : " + query + ANSI_RESET);
            ResultSet resultSet = stmt.executeQuery(query);
            List<T> resultQuery = new ArrayList<>();
            return resultSet;
        } catch (Exception e) {
            System.out.println(ANSI_BACKGROUND_RED + "leDeuxiemeTrucQueKevinVeut" + e + ANSI_RESET);
        }
        return null;
    }

    public List<T> createQuery(String query) {
            try {
                ResultSet resultSet = leDeuxiemeTrucQueKevinVeut(query);
                List<T> resultQuery = new ArrayList<>();
                while (resultSet.next()) {
                    resultQuery.add(getObject(resultSet));
                }
                return resultQuery;
            } catch (Exception e) {
                System.out.println(ANSI_BACKGROUND_RED + "createQuery " + e + ANSI_RESET);
            }
            return null;
        }


    public void test(Map<String, Boolean> order) {
        Map<String, Object> mapFind = new HashMap<>();
        mapFind.put("id", "BETWEEN 1 and 5");
        System.out.println();
        findBy(mapFind, 3, order).forEach(System.out::println);
        Map<String, Object> mapFind2 = new HashMap<>();
        mapFind2.put("id", 2L);
        System.out.println();
        T object = findOneBy(mapFind2);
        System.out.println(object);
        object.setId(null);
        System.out.println();
        save(object);
        System.out.println();
        Map<String, Object> mapFind3 = new HashMap<>();
        mapFind3.put("id", object.getId());
        System.out.println(findOneBy(mapFind3));
        delete(object);
        findAll().forEach(System.out::println);
        findAll().forEach(System.out::println);
        System.out.println("queriesFromMap : " + queriesFromMap);
        System.out.println("queriesFromRS : " + queriesFromRS);
    }
}
