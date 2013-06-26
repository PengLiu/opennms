package org.opennms.netmgt.dao.mock;

import org.junit.Before;
import org.junit.Test;
import org.opennms.core.criteria.restrictions.EqRestriction;
import org.opennms.core.criteria.restrictions.GeRestriction;
import org.opennms.core.criteria.restrictions.GtRestriction;
import org.opennms.core.criteria.restrictions.LeRestriction;
import org.opennms.core.criteria.restrictions.LtRestriction;
import org.opennms.core.criteria.restrictions.NotNullRestriction;
import org.opennms.core.criteria.restrictions.NullRestriction;

public class BeanWrapperRestrictionVisitorTest {
    public static class TestBean {
        public Object getNullValue() {
            return null;
        }
        public String getString() {
            return "I am a string.";
        }
        public Boolean getTrueValue() {
            return Boolean.valueOf(true);
        }
        public Boolean getFalseValue() {
            return Boolean.valueOf(false);
        }
        public Double getOne() {
            return 1.0d;
        }
        public Float getTwo() {
            return 2.0f;
        }
        public Integer getThree() {
            return 3;
        }
        public Long getFour() {
            return 4L;
        }
    }

    BeanWrapperRestrictionVisitor<TestBean> m_visitor = null;

    @Before
    public void setUp() {
        final TestBean bean = new TestBean();
        m_visitor = new BeanWrapperRestrictionVisitor<TestBean>(bean);
    }

    @Test
    public void testCaseInsensitive() {
        m_visitor.visitNotNull(new NotNullRestriction("truevalue"));
    }
    @Test
    public void testNullRestriction() {
        m_visitor.visitNull(new NullRestriction("nullValue"));
    }

    @Test(expected=BeanWrapperRestrictionFailure.class)
    public void testNotNullFromNullRestriction() {
        m_visitor.visitNull(new NullRestriction("string"));
    }

    @Test
    public void testNotNullRestriction() {
        m_visitor.visitNotNull(new NotNullRestriction("string"));
    }

    @Test(expected=BeanWrapperRestrictionFailure.class)
    public void testNullFromNotNullRestriction() {
        m_visitor.visitNotNull(new NotNullRestriction("nullValue"));
    }
    
    @Test
    public void testEqualsRestriction() {
        m_visitor.visitEq(new EqRestriction("string", "I am a string."));
        m_visitor.visitEq(new EqRestriction("trueValue", Boolean.valueOf(true)));
        m_visitor.visitEq(new EqRestriction("falseValue", Boolean.valueOf(false)));
    }

    @Test(expected=BeanWrapperRestrictionFailure.class)
    public void testFailedEqualsRestriction() {
        m_visitor.visitEq(new EqRestriction("string", false));
    }

    @Test
    public void testGreaterThan() {
        m_visitor.visitGt(new GtRestriction("one", 0.0f));
        m_visitor.visitGt(new GtRestriction("one", 0.99999d));
        m_visitor.visitGt(new GtRestriction("four", 3.9999f));
    }

    @Test(expected=BeanWrapperRestrictionFailure.class)
    public void testFailedGreaterThanWithEqualsComparison() {
        m_visitor.visitGt(new GtRestriction("four", 4));
    }

    @Test(expected=BeanWrapperRestrictionFailure.class)
    public void testFailedGreaterThanWithLessComparison() {
        m_visitor.visitGt(new GtRestriction("four", 17d));
    }
    
    @Test
    public void testGreaterEqual() {
        m_visitor.visitGe(new GeRestriction("one", 0.0f));
        m_visitor.visitGe(new GeRestriction("one", 0.99999d));
        m_visitor.visitGe(new GeRestriction("four", 3.9999f));
    }

    @Test
    public void testGreaterEqualWithEqualsComparison() {
        m_visitor.visitGe(new GeRestriction("four", 4.0f));
        m_visitor.visitGe(new GeRestriction("four", 4));
    }

    @Test(expected=BeanWrapperRestrictionFailure.class)
    public void testFailedGreaterEqualWithLessComparison() {
        m_visitor.visitGe(new GeRestriction("four", 17d));
    }

    @Test
    public void testLessThan() {
        m_visitor.visitLt(new LtRestriction("one", 2.0f));
        m_visitor.visitLt(new LtRestriction("one", 2.99999d));
        m_visitor.visitLt(new LtRestriction("four", 5.9999f));
    }

    @Test(expected=BeanWrapperRestrictionFailure.class)
    public void testFailedLessThanWithEqualsComparison() {
        m_visitor.visitLt(new LtRestriction("four", 4));
    }

    @Test(expected=BeanWrapperRestrictionFailure.class)
    public void testFailedLessThanWithGreaterComparison() {
        m_visitor.visitLt(new LtRestriction("four", 1d));
    }
    
    @Test
    public void testLessEqual() {
        m_visitor.visitLe(new LeRestriction("one", 2.0f));
        m_visitor.visitLe(new LeRestriction("one", 2.99999d));
        m_visitor.visitLe(new LeRestriction("four", 5.9999f));
    }

    @Test
    public void testLessEqualWithEqualsComparison() {
        m_visitor.visitLe(new LeRestriction("four", 4.0f));
        m_visitor.visitLe(new LeRestriction("four", 4));
    }

    @Test(expected=BeanWrapperRestrictionFailure.class)
    public void testFailedLessEqualWithGreaterComparison() {
        m_visitor.visitLe(new LeRestriction("four", 1d));
    }
    
}
