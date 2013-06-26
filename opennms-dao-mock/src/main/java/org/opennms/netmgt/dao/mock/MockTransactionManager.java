package org.opennms.netmgt.dao.mock;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

public class MockTransactionManager implements PlatformTransactionManager {

    public MockTransactionManager() {
    }

    @Override
    public TransactionStatus getTransaction(final TransactionDefinition definition) throws TransactionException {
        return null;
    }

    @Override
    public void commit(final TransactionStatus status) throws TransactionException {
    }

    @Override
    public void rollback(final TransactionStatus status) throws TransactionException {
    }

}
