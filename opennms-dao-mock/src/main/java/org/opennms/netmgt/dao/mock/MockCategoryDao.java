package org.opennms.netmgt.dao.mock;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.hibernate.criterion.Criterion;
import org.opennms.netmgt.dao.api.CategoryDao;
import org.opennms.netmgt.model.OnmsCategory;

public class MockCategoryDao extends AbstractMockDao<OnmsCategory, Integer> implements CategoryDao {
    private AtomicInteger m_id = new AtomicInteger(0);

    @Override
    protected void generateId(final OnmsCategory cat) {
        cat.setId(m_id.incrementAndGet());
    }

    @Override
    protected Integer getId(final OnmsCategory cat) {
        return cat.getId();
    }

    @Override
    public OnmsCategory findByName(final String name) {
        for (final OnmsCategory cat : getValues()) {
            if (name.equals(cat.getName())) {
                return cat;
            }
        }
        return null;
    }

    @Override
    public OnmsCategory findByName(final String name, final boolean useCached) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public List<String> getAllCategoryNames() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public List<Criterion> getCriterionForCategorySetsUnion(final String[]... categories) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public List<OnmsCategory> getCategoriesWithAuthorizedGroup(final String groupName) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

}
