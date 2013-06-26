package org.opennms.netmgt.dao.mock;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.opennms.netmgt.dao.api.LocationMonitorDao;
import org.opennms.netmgt.model.LocationMonitorIpInterface;
import org.opennms.netmgt.model.OnmsApplication;
import org.opennms.netmgt.model.OnmsLocationMonitor;
import org.opennms.netmgt.model.OnmsLocationSpecificStatus;
import org.opennms.netmgt.model.OnmsMonitoredService;
import org.opennms.netmgt.model.OnmsMonitoringLocationDefinition;

public class MockLocationMonitorDao extends AbstractMockDao<OnmsLocationMonitor, Integer> implements LocationMonitorDao {
    private AtomicInteger m_id = new AtomicInteger(0);

    @Override
    protected void generateId(final OnmsLocationMonitor mon) {
        mon.setId(m_id.incrementAndGet());
    }

    @Override
    protected Integer getId(final OnmsLocationMonitor loc) {
        return loc.getId();
    }

    @Override
    public Collection<OnmsLocationMonitor> findByLocationDefinition(final OnmsMonitoringLocationDefinition locationDefinition) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Collection<OnmsLocationMonitor> findByApplication(final OnmsApplication application) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public List<OnmsMonitoringLocationDefinition> findAllMonitoringLocationDefinitions() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public OnmsMonitoringLocationDefinition findMonitoringLocationDefinition(final String monitoringLocationDefinitionName) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void saveMonitoringLocationDefinition(final OnmsMonitoringLocationDefinition def) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void saveMonitoringLocationDefinitions(final Collection<OnmsMonitoringLocationDefinition> defs) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void saveStatusChange(final OnmsLocationSpecificStatus status) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public OnmsLocationSpecificStatus getMostRecentStatusChange(final OnmsLocationMonitor locationMonitor, final OnmsMonitoredService monSvc) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Collection<OnmsLocationSpecificStatus> getAllMostRecentStatusChanges() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Collection<OnmsLocationSpecificStatus> getAllStatusChangesAt(final Date timestamp) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Collection<OnmsLocationSpecificStatus> getStatusChangesBetween(final Date startDate, Date endDate) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Collection<OnmsLocationSpecificStatus> getStatusChangesForLocationBetween(final Date startDate, final Date endDate, final String locationDefinitionName) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Collection<OnmsLocationSpecificStatus> getStatusChangesForApplicationBetween(final Date startDate, final Date endDate, final String applicationName) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Collection<OnmsLocationSpecificStatus> getStatusChangesBetweenForApplications(final Date startDate, final Date endDate, final Collection<String> applicationNames) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Collection<OnmsLocationSpecificStatus> getMostRecentStatusChangesForLocation(final String locationName) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Collection<LocationMonitorIpInterface> findStatusChangesForNodeForUniqueMonitorAndInterface(final int nodeId) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void pauseAll() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void resumeAll() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

}
