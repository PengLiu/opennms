package org.opennms.netmgt.dao.mock;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.opennms.netmgt.dao.api.DataLinkInterfaceDao;
import org.opennms.netmgt.model.DataLinkInterface;
import org.opennms.netmgt.model.OnmsArpInterface.StatusType;

public class MockDataLinkInterfaceDao extends AbstractMockDao<DataLinkInterface, Integer> implements DataLinkInterfaceDao {
    private AtomicInteger m_id = new AtomicInteger(0);

    @Override
    protected void generateId(final DataLinkInterface dli) {
        dli.setId(m_id.incrementAndGet());
    }

    @Override
    protected Integer getId(final DataLinkInterface dli) {
        return dli.getId();
    }

    @Override
    public Collection<DataLinkInterface> findAll(final Integer offset, final Integer limit) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public DataLinkInterface findById(final Integer id) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Collection<DataLinkInterface> findByNodeId(final Integer nodeId) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Collection<DataLinkInterface> findByNodeParentId(final Integer nodeParentId) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void markDeletedIfNodeDeleted() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public DataLinkInterface findByNodeIdAndIfIndex(final Integer nodeId, final Integer ifindex) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void deactivateIfOlderThan(final Date now, final String source) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void deleteIfOlderThan(final Date now, final String source) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void setStatusForNode(final Integer nodeid, final StatusType action) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void setStatusForNodeAndIfIndex(final Integer nodeid, final Integer ifIndex, final StatusType action) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void setStatusForNode(final Integer nodeid, final String source, final StatusType action) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void setStatusForNodeAndIfIndex(final Integer nodeid, final Integer ifIndex, final String source, final StatusType action) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

}
