package org.opennms.netmgt.dao.mock;

import java.util.concurrent.atomic.AtomicInteger;

import org.opennms.netmgt.dao.api.EventDao;
import org.opennms.netmgt.model.OnmsEvent;

public class MockEventDao extends AbstractMockDao<OnmsEvent, Integer> implements EventDao {
    private AtomicInteger m_id = new AtomicInteger(0);

    @Override
    protected void generateId(final OnmsEvent event) {
        event.setId(m_id.incrementAndGet());
    }

    @Override
    protected Integer getId(final OnmsEvent event) {
        final Integer id = event.getId();
        return id == null || id == 0? null : id;
    }

    @Override
    public int deletePreviousEventsForAlarm(final Integer id, final OnmsEvent e) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

}
