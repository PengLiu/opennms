package org.opennms.netmgt.dao.mock;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.opennms.netmgt.dao.api.IpInterfaceDao;
import org.opennms.netmgt.model.OnmsIpInterface;
import org.opennms.netmgt.model.OnmsMonitoredService;
import org.opennms.netmgt.model.OnmsNode;

public class MockIpInterfaceDao extends AbstractMockDao<OnmsIpInterface, Integer> implements IpInterfaceDao {
    private AtomicInteger m_id = new AtomicInteger(0);

    @Override
    public void save(final OnmsIpInterface iface) {
        for (final OnmsMonitoredService svc : iface.getMonitoredServices()) {
            getMonitoredServiceDao().saveOrUpdate(svc);
        }
        super.save(iface);
    }

    @Override
    public void delete(final OnmsIpInterface iface) {
        super.delete(iface);
        for (final OnmsMonitoredService svc : iface.getMonitoredServices()) {
            getMonitoredServiceDao().delete(svc);
        }
    }

    @Override
    protected void generateId(final OnmsIpInterface iface) {
        iface.setId(m_id.incrementAndGet());
    }

    @Override
    protected Integer getId(final OnmsIpInterface iface) {
        return iface.getId();
    }

    @Override
    public OnmsIpInterface get(final OnmsNode node, final String ipAddress) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public OnmsIpInterface findByNodeIdAndIpAddress(final Integer nodeId, final String ipAddress) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public OnmsIpInterface findByForeignKeyAndIpAddress(final String foreignSource, final String foreignId, final String ipAddress) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public List<OnmsIpInterface> findByIpAddress(final String ipAddress) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public List<OnmsIpInterface> findByNodeId(final Integer nodeId) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public List<OnmsIpInterface> findByServiceType(final String svcName) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public List<OnmsIpInterface> findHierarchyByServiceType(final String svcName) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Map<InetAddress, Integer> getInterfacesForNodes() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public OnmsIpInterface findPrimaryInterfaceByNodeId(final Integer nodeId) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

}
