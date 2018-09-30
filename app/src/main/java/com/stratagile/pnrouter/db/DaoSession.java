package com.stratagile.pnrouter.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.stratagile.pnrouter.db.RouterEntity;
import com.stratagile.pnrouter.db.UserEntity;

import com.stratagile.pnrouter.db.RouterEntityDao;
import com.stratagile.pnrouter.db.UserEntityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig routerEntityDaoConfig;
    private final DaoConfig userEntityDaoConfig;

    private final RouterEntityDao routerEntityDao;
    private final UserEntityDao userEntityDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        routerEntityDaoConfig = daoConfigMap.get(RouterEntityDao.class).clone();
        routerEntityDaoConfig.initIdentityScope(type);

        userEntityDaoConfig = daoConfigMap.get(UserEntityDao.class).clone();
        userEntityDaoConfig.initIdentityScope(type);

        routerEntityDao = new RouterEntityDao(routerEntityDaoConfig, this);
        userEntityDao = new UserEntityDao(userEntityDaoConfig, this);

        registerDao(RouterEntity.class, routerEntityDao);
        registerDao(UserEntity.class, userEntityDao);
    }
    
    public void clear() {
        routerEntityDaoConfig.clearIdentityScope();
        userEntityDaoConfig.clearIdentityScope();
    }

    public RouterEntityDao getRouterEntityDao() {
        return routerEntityDao;
    }

    public UserEntityDao getUserEntityDao() {
        return userEntityDao;
    }

}