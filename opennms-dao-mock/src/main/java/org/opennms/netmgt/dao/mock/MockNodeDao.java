package org.opennms.netmgt.dao.mock;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.opennms.netmgt.dao.api.NodeDao;
import org.opennms.netmgt.model.OnmsCategory;
import org.opennms.netmgt.model.OnmsDistPoller;
import org.opennms.netmgt.model.OnmsIpInterface;
import org.opennms.netmgt.model.OnmsNode;
import org.opennms.netmgt.model.OnmsSnmpInterface;
import org.opennms.netmgt.model.SurveillanceStatus;

public class MockNodeDao extends AbstractMockDao<OnmsNode, Integer> implements NodeDao {
    private AtomicInteger m_id = new AtomicInteger(0);

    @Override
    protected void generateId(final OnmsNode node) {
        node.setId(m_id.incrementAndGet());
    }

    @Override
    protected Integer getId(final OnmsNode node) {
        return node.getId();
    }

    @Override
    public void delete(final OnmsNode node) {
        for (final OnmsIpInterface iface : node.getIpInterfaces()) {
            getIpInterfaceDao().delete(iface);
        }
        for (final OnmsSnmpInterface iface : node.getSnmpInterfaces()) {
            getSnmpInterfaceDao().delete(iface);
        }
        super.delete(node);
    }

    @Override
    public void save(final OnmsNode node) {
        if (node == null) return;
        getAssetRecordDao().saveOrUpdate(node.getAssetRecord());
        for (final OnmsCategory cat : node.getCategories()) {
            getCategoryDao().saveOrUpdate(cat);
        }
        getDistPollerDao().saveOrUpdate(node.getDistPoller());
        for (final OnmsIpInterface iface : node.getIpInterfaces()) {
            getIpInterfaceDao().saveOrUpdate(iface);
        }
        for (final OnmsSnmpInterface iface : node.getSnmpInterfaces()) {
            getSnmpInterfaceDao().saveOrUpdate(iface);
        }
        super.save(node);
    }

    @Override
    public OnmsNode get(final String lookupCriteria) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public String getLabelForId(final Integer id) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public List<OnmsNode> findByLabel(final String label) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public List<OnmsNode> findNodes(final OnmsDistPoller dp) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public OnmsNode getHierarchy(final Integer id) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Map<String, Integer> getForeignIdToNodeIdMap(final String foreignSource) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public List<OnmsNode> findAllByVarCharAssetColumn(final String columnName, final String columnValue) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public List<OnmsNode> findAllByVarCharAssetColumnCategoryList(final String columnName, final String columnValue, Collection<OnmsCategory> categories) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public List<OnmsNode> findByCategory(final OnmsCategory category) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public List<OnmsNode> findAllByCategoryList(final Collection<OnmsCategory> categories) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public List<OnmsNode> findAllByCategoryLists(final Collection<OnmsCategory> rowCatNames, final Collection<OnmsCategory> colCatNames) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public List<OnmsNode> findByForeignSource(final String foreignSource) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public OnmsNode findByForeignId(final String foreignSource, final String foreignId) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public int getNodeCountForForeignSource(final String groupName) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public List<OnmsNode> findAllProvisionedNodes() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public List<OnmsIpInterface> findObsoleteIpInterfaces(final Integer nodeId, final Date scanStamp) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void deleteObsoleteInterfaces(final Integer nodeId, final Date scanStamp) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void updateNodeScanStamp(final Integer nodeId, final Date scanStamp) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Collection<Integer> getNodeIds() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public List<OnmsNode> findByForeignSourceAndIpAddress(final String foreignSource, final String ipAddress) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public SurveillanceStatus findSurveillanceStatusByCategoryLists(final Collection<OnmsCategory> rowCategories, final Collection<OnmsCategory> columnCategories) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Integer getNextNodeId(final Integer nodeId) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Integer getPreviousNodeId(final Integer nodeId) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

}
