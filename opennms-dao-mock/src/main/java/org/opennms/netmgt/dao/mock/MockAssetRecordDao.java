package org.opennms.netmgt.dao.mock;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.opennms.netmgt.dao.api.AssetRecordDao;
import org.opennms.netmgt.model.OnmsAssetRecord;


public class MockAssetRecordDao extends AbstractMockDao<OnmsAssetRecord, Integer> implements AssetRecordDao {
    private AtomicInteger m_id = new AtomicInteger(0);

    @Override
    protected void generateId(final OnmsAssetRecord asset) {
        asset.setId(m_id.incrementAndGet());
    }

    @Override
    protected Integer getId(final OnmsAssetRecord asset) {
        return asset.getId();
    }

    @Override
    public OnmsAssetRecord findByNodeId(final Integer id) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Map<String, Integer> findImportedAssetNumbersToNodeIds(final String foreignSource) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public List<OnmsAssetRecord> getDistinctProperties() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

}
