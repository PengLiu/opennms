package org.opennms.netmgt.dao.mock;

import org.springframework.dao.DataRetrievalFailureException;

public class BeanWrapperRestrictionFailure extends DataRetrievalFailureException {
    private static final long serialVersionUID = -502014384653230594L;

    public BeanWrapperRestrictionFailure(final String msg) {
        super(msg);
    }

    public BeanWrapperRestrictionFailure(final String msg, final Throwable cause) {
        super(msg, cause);
    }

}
