package org.opennms.netmgt.dao.mock;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;

import org.opennms.core.criteria.restrictions.AllRestriction;
import org.opennms.core.criteria.restrictions.AnyRestriction;
import org.opennms.core.criteria.restrictions.AttributeRestriction;
import org.opennms.core.criteria.restrictions.BaseRestrictionVisitor;
import org.opennms.core.criteria.restrictions.BetweenRestriction;
import org.opennms.core.criteria.restrictions.EqRestriction;
import org.opennms.core.criteria.restrictions.GeRestriction;
import org.opennms.core.criteria.restrictions.GtRestriction;
import org.opennms.core.criteria.restrictions.IlikeRestriction;
import org.opennms.core.criteria.restrictions.InRestriction;
import org.opennms.core.criteria.restrictions.IplikeRestriction;
import org.opennms.core.criteria.restrictions.LeRestriction;
import org.opennms.core.criteria.restrictions.LikeRestriction;
import org.opennms.core.criteria.restrictions.LtRestriction;
import org.opennms.core.criteria.restrictions.NeRestriction;
import org.opennms.core.criteria.restrictions.NotNullRestriction;
import org.opennms.core.criteria.restrictions.NotRestriction;
import org.opennms.core.criteria.restrictions.NullRestriction;
import org.opennms.core.criteria.restrictions.Restriction;
import org.opennms.core.criteria.restrictions.SqlRestriction;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

final class BeanWrapperRestrictionVisitor<T> extends BaseRestrictionVisitor {
    private T m_entity;
    private BeanWrapper m_beanWrapper;

    public BeanWrapperRestrictionVisitor(final T entity) {
        m_entity = entity;
        m_beanWrapper = new BeanWrapperImpl(m_entity);
    }

    protected void fail(final Restriction restriction) {
        throw new BeanWrapperRestrictionFailure(m_entity  + " failed restriction: " + restriction);
    }

    protected Object getProperty(final String attribute) {
        for (final PropertyDescriptor pd : m_beanWrapper.getPropertyDescriptors()) {
            if (pd.getName().equalsIgnoreCase(attribute)) {
                return m_beanWrapper.getPropertyValue(pd.getName());
            }
        }
        return null;
    }
    
    protected Object getProperty(final AttributeRestriction restriction) {
        return getProperty(restriction.getAttribute());
    }

    @Override public void visitNull(final NullRestriction restriction) {
        if (getProperty(restriction) != null) fail(restriction);
    }
    @Override public void visitNullComplete(final NullRestriction restriction) {}
    @Override public void visitNotNull(final NotNullRestriction restriction) {
        if (getProperty(restriction) == null) fail(restriction);
    }
    @Override public void visitNotNullComplete(final NotNullRestriction restriction) {}
    @Override public void visitEq(final EqRestriction restriction) {
        final Object o = getProperty(restriction);
        if (!restriction.getValue().equals(o)) fail(restriction);
    }
    @Override public void visitEqComplete(final EqRestriction restriction) {}
    @Override public void visitNe(final NeRestriction restriction) {
        final Object o = getProperty(restriction);
        if (restriction.getValue().equals(o)) fail(restriction);
    }
    @Override public void visitNeComplete(final NeRestriction restriction) {}
    @Override public void visitGt(final GtRestriction restriction) {
        final Object o = getProperty(restriction);
        if (o instanceof java.lang.Number && restriction.getValue() instanceof java.lang.Number) {
            final BigDecimal left = new BigDecimal(((Number)o).doubleValue());
            final BigDecimal right = new BigDecimal(((Number)restriction.getValue()).doubleValue());
            if (left.compareTo(right) == 1) return;
        }
        fail(restriction);
    }
    @Override public void visitGtComplete(final GtRestriction restriction) {}
    @Override public void visitGe(final GeRestriction restriction) {
        final Object o = getProperty(restriction);
        if (o instanceof java.lang.Number && restriction.getValue() instanceof java.lang.Number) {
            final BigDecimal left = new BigDecimal(((Number)o).doubleValue());
            final BigDecimal right = new BigDecimal(((Number)restriction.getValue()).doubleValue());
            if (left.compareTo(right) >= 0) return;
        }
        fail(restriction);
    }
    @Override public void visitGeComplete(final GeRestriction restriction) {}
    @Override public void visitLt(final LtRestriction restriction) {
        final Object o = getProperty(restriction);
        if (o instanceof java.lang.Number && restriction.getValue() instanceof java.lang.Number) {
            final BigDecimal left = new BigDecimal(((Number)o).doubleValue());
            final BigDecimal right = new BigDecimal(((Number)restriction.getValue()).doubleValue());
            if (left.compareTo(right) == -1) return;
        }
        fail(restriction);
    }
    @Override public void visitLtComplete(final LtRestriction restriction) {}
    @Override public void visitLe(final LeRestriction restriction) {
        final Object o = getProperty(restriction);
        if (o instanceof java.lang.Number && restriction.getValue() instanceof java.lang.Number) {
            final BigDecimal left = new BigDecimal(((Number)o).doubleValue());
            final BigDecimal right = new BigDecimal(((Number)restriction.getValue()).doubleValue());
            if (left.compareTo(right) <= 0) return;
        }
        fail(restriction);
    }
    @Override public void visitLeComplete(final LeRestriction restriction) {}
    @Override public void visitAll(final AllRestriction restriction) {
        for (final Restriction r : restriction.getRestrictions()) {
            r.visit(this);
        }
    }
    @Override public void visitAllComplete(final AllRestriction restriction) {}
    @Override public void visitAny(final AnyRestriction restriction) {
        boolean matched = false;
        for (final Restriction r : restriction.getRestrictions()) {
            try {
                r.visit(this);
                matched = true;
                break;
            } catch (final Exception e) {
            }
        }
        if (!matched) {
            fail(restriction);
        }
    }
    @Override public void visitAnyComplete(final AnyRestriction restriction) {}
    @Override public void visitLike(final LikeRestriction restriction) {
        throw new UnsupportedOperationException("Not Yet Implemented!");
    }
    @Override public void visitLikeComplete(final LikeRestriction restriction) {}
    @Override public void visitIlike(final IlikeRestriction restriction) {
        throw new UnsupportedOperationException("Not Yet Implemented!");
    }
    @Override public void visitIlikeComplete(final IlikeRestriction restriction) {}
    @Override public void visitIn(final InRestriction restriction) {
        throw new UnsupportedOperationException("Not Yet Implemented!");
    }
    @Override public void visitInComplete(final InRestriction restriction) {}
    @Override public void visitNot(final NotRestriction restriction) {
        throw new UnsupportedOperationException("Not Yet Implemented!");
    }
    @Override public void visitNotComplete(final NotRestriction restriction) {}
    @Override public void visitBetween(final BetweenRestriction restriction) {
        throw new UnsupportedOperationException("Not Yet Implemented!");
    }
    @Override public void visitBetweenComplete(final BetweenRestriction restriction) {}
    @Override public void visitSql(final SqlRestriction restriction) {
        throw new UnsupportedOperationException("Not Yet Implemented!");
    }
    @Override public void visitSqlComplete(final SqlRestriction restriction) {}
    @Override public void visitIplike(final IplikeRestriction restriction) {
        throw new UnsupportedOperationException("Not Yet Implemented!");
    }
    @Override public void visitIplikeComplete(final IplikeRestriction restriction) {}
}