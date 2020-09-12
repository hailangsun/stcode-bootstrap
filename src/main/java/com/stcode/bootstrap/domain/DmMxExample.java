package com.stcode.bootstrap.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DmMxExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DmMxExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andDmmxidIsNull() {
            addCriterion("DMMXID is null");
            return (Criteria) this;
        }

        public Criteria andDmmxidIsNotNull() {
            addCriterion("DMMXID is not null");
            return (Criteria) this;
        }

        public Criteria andDmmxidEqualTo(String value) {
            addCriterion("DMMXID =", value, "dmmxid");
            return (Criteria) this;
        }

        public Criteria andDmmxidNotEqualTo(String value) {
            addCriterion("DMMXID <>", value, "dmmxid");
            return (Criteria) this;
        }

        public Criteria andDmmxidGreaterThan(String value) {
            addCriterion("DMMXID >", value, "dmmxid");
            return (Criteria) this;
        }

        public Criteria andDmmxidGreaterThanOrEqualTo(String value) {
            addCriterion("DMMXID >=", value, "dmmxid");
            return (Criteria) this;
        }

        public Criteria andDmmxidLessThan(String value) {
            addCriterion("DMMXID <", value, "dmmxid");
            return (Criteria) this;
        }

        public Criteria andDmmxidLessThanOrEqualTo(String value) {
            addCriterion("DMMXID <=", value, "dmmxid");
            return (Criteria) this;
        }

        public Criteria andDmmxidLike(String value) {
            addCriterion("DMMXID like", value, "dmmxid");
            return (Criteria) this;
        }

        public Criteria andDmmxidNotLike(String value) {
            addCriterion("DMMXID not like", value, "dmmxid");
            return (Criteria) this;
        }

        public Criteria andDmmxidIn(List<String> values) {
            addCriterion("DMMXID in", values, "dmmxid");
            return (Criteria) this;
        }

        public Criteria andDmmxidNotIn(List<String> values) {
            addCriterion("DMMXID not in", values, "dmmxid");
            return (Criteria) this;
        }

        public Criteria andDmmxidBetween(String value1, String value2) {
            addCriterion("DMMXID between", value1, value2, "dmmxid");
            return (Criteria) this;
        }

        public Criteria andDmmxidNotBetween(String value1, String value2) {
            addCriterion("DMMXID not between", value1, value2, "dmmxid");
            return (Criteria) this;
        }

        public Criteria andDmlbidIsNull() {
            addCriterion("DMLBID is null");
            return (Criteria) this;
        }

        public Criteria andDmlbidIsNotNull() {
            addCriterion("DMLBID is not null");
            return (Criteria) this;
        }

        public Criteria andDmlbidEqualTo(String value) {
            addCriterion("DMLBID =", value, "dmlbid");
            return (Criteria) this;
        }

        public Criteria andDmlbidNotEqualTo(String value) {
            addCriterion("DMLBID <>", value, "dmlbid");
            return (Criteria) this;
        }

        public Criteria andDmlbidGreaterThan(String value) {
            addCriterion("DMLBID >", value, "dmlbid");
            return (Criteria) this;
        }

        public Criteria andDmlbidGreaterThanOrEqualTo(String value) {
            addCriterion("DMLBID >=", value, "dmlbid");
            return (Criteria) this;
        }

        public Criteria andDmlbidLessThan(String value) {
            addCriterion("DMLBID <", value, "dmlbid");
            return (Criteria) this;
        }

        public Criteria andDmlbidLessThanOrEqualTo(String value) {
            addCriterion("DMLBID <=", value, "dmlbid");
            return (Criteria) this;
        }

        public Criteria andDmlbidLike(String value) {
            addCriterion("DMLBID like", value, "dmlbid");
            return (Criteria) this;
        }

        public Criteria andDmlbidNotLike(String value) {
            addCriterion("DMLBID not like", value, "dmlbid");
            return (Criteria) this;
        }

        public Criteria andDmlbidIn(List<String> values) {
            addCriterion("DMLBID in", values, "dmlbid");
            return (Criteria) this;
        }

        public Criteria andDmlbidNotIn(List<String> values) {
            addCriterion("DMLBID not in", values, "dmlbid");
            return (Criteria) this;
        }

        public Criteria andDmlbidBetween(String value1, String value2) {
            addCriterion("DMLBID between", value1, value2, "dmlbid");
            return (Criteria) this;
        }

        public Criteria andDmlbidNotBetween(String value1, String value2) {
            addCriterion("DMLBID not between", value1, value2, "dmlbid");
            return (Criteria) this;
        }

        public Criteria andMxmcIsNull() {
            addCriterion("MXMC is null");
            return (Criteria) this;
        }

        public Criteria andMxmcIsNotNull() {
            addCriterion("MXMC is not null");
            return (Criteria) this;
        }

        public Criteria andMxmcEqualTo(String value) {
            addCriterion("MXMC =", value, "mxmc");
            return (Criteria) this;
        }

        public Criteria andMxmcNotEqualTo(String value) {
            addCriterion("MXMC <>", value, "mxmc");
            return (Criteria) this;
        }

        public Criteria andMxmcGreaterThan(String value) {
            addCriterion("MXMC >", value, "mxmc");
            return (Criteria) this;
        }

        public Criteria andMxmcGreaterThanOrEqualTo(String value) {
            addCriterion("MXMC >=", value, "mxmc");
            return (Criteria) this;
        }

        public Criteria andMxmcLessThan(String value) {
            addCriterion("MXMC <", value, "mxmc");
            return (Criteria) this;
        }

        public Criteria andMxmcLessThanOrEqualTo(String value) {
            addCriterion("MXMC <=", value, "mxmc");
            return (Criteria) this;
        }

        public Criteria andMxmcLike(String value) {
            addCriterion("MXMC like", value, "mxmc");
            return (Criteria) this;
        }

        public Criteria andMxmcNotLike(String value) {
            addCriterion("MXMC not like", value, "mxmc");
            return (Criteria) this;
        }

        public Criteria andMxmcIn(List<String> values) {
            addCriterion("MXMC in", values, "mxmc");
            return (Criteria) this;
        }

        public Criteria andMxmcNotIn(List<String> values) {
            addCriterion("MXMC not in", values, "mxmc");
            return (Criteria) this;
        }

        public Criteria andMxmcBetween(String value1, String value2) {
            addCriterion("MXMC between", value1, value2, "mxmc");
            return (Criteria) this;
        }

        public Criteria andMxmcNotBetween(String value1, String value2) {
            addCriterion("MXMC not between", value1, value2, "mxmc");
            return (Criteria) this;
        }

        public Criteria andMxdmIsNull() {
            addCriterion("MXDM is null");
            return (Criteria) this;
        }

        public Criteria andMxdmIsNotNull() {
            addCriterion("MXDM is not null");
            return (Criteria) this;
        }

        public Criteria andMxdmEqualTo(String value) {
            addCriterion("MXDM =", value, "mxdm");
            return (Criteria) this;
        }

        public Criteria andMxdmNotEqualTo(String value) {
            addCriterion("MXDM <>", value, "mxdm");
            return (Criteria) this;
        }

        public Criteria andMxdmGreaterThan(String value) {
            addCriterion("MXDM >", value, "mxdm");
            return (Criteria) this;
        }

        public Criteria andMxdmGreaterThanOrEqualTo(String value) {
            addCriterion("MXDM >=", value, "mxdm");
            return (Criteria) this;
        }

        public Criteria andMxdmLessThan(String value) {
            addCriterion("MXDM <", value, "mxdm");
            return (Criteria) this;
        }

        public Criteria andMxdmLessThanOrEqualTo(String value) {
            addCriterion("MXDM <=", value, "mxdm");
            return (Criteria) this;
        }

        public Criteria andMxdmLike(String value) {
            addCriterion("MXDM like", value, "mxdm");
            return (Criteria) this;
        }

        public Criteria andMxdmNotLike(String value) {
            addCriterion("MXDM not like", value, "mxdm");
            return (Criteria) this;
        }

        public Criteria andMxdmIn(List<String> values) {
            addCriterion("MXDM in", values, "mxdm");
            return (Criteria) this;
        }

        public Criteria andMxdmNotIn(List<String> values) {
            addCriterion("MXDM not in", values, "mxdm");
            return (Criteria) this;
        }

        public Criteria andMxdmBetween(String value1, String value2) {
            addCriterion("MXDM between", value1, value2, "mxdm");
            return (Criteria) this;
        }

        public Criteria andMxdmNotBetween(String value1, String value2) {
            addCriterion("MXDM not between", value1, value2, "mxdm");
            return (Criteria) this;
        }

        public Criteria andDmztIsNull() {
            addCriterion("DMZT is null");
            return (Criteria) this;
        }

        public Criteria andDmztIsNotNull() {
            addCriterion("DMZT is not null");
            return (Criteria) this;
        }

        public Criteria andDmztEqualTo(String value) {
            addCriterion("DMZT =", value, "dmzt");
            return (Criteria) this;
        }

        public Criteria andDmztNotEqualTo(String value) {
            addCriterion("DMZT <>", value, "dmzt");
            return (Criteria) this;
        }

        public Criteria andDmztGreaterThan(String value) {
            addCriterion("DMZT >", value, "dmzt");
            return (Criteria) this;
        }

        public Criteria andDmztGreaterThanOrEqualTo(String value) {
            addCriterion("DMZT >=", value, "dmzt");
            return (Criteria) this;
        }

        public Criteria andDmztLessThan(String value) {
            addCriterion("DMZT <", value, "dmzt");
            return (Criteria) this;
        }

        public Criteria andDmztLessThanOrEqualTo(String value) {
            addCriterion("DMZT <=", value, "dmzt");
            return (Criteria) this;
        }

        public Criteria andDmztLike(String value) {
            addCriterion("DMZT like", value, "dmzt");
            return (Criteria) this;
        }

        public Criteria andDmztNotLike(String value) {
            addCriterion("DMZT not like", value, "dmzt");
            return (Criteria) this;
        }

        public Criteria andDmztIn(List<String> values) {
            addCriterion("DMZT in", values, "dmzt");
            return (Criteria) this;
        }

        public Criteria andDmztNotIn(List<String> values) {
            addCriterion("DMZT not in", values, "dmzt");
            return (Criteria) this;
        }

        public Criteria andDmztBetween(String value1, String value2) {
            addCriterion("DMZT between", value1, value2, "dmzt");
            return (Criteria) this;
        }

        public Criteria andDmztNotBetween(String value1, String value2) {
            addCriterion("DMZT not between", value1, value2, "dmzt");
            return (Criteria) this;
        }

        public Criteria andLrryIsNull() {
            addCriterion("LRRY is null");
            return (Criteria) this;
        }

        public Criteria andLrryIsNotNull() {
            addCriterion("LRRY is not null");
            return (Criteria) this;
        }

        public Criteria andLrryEqualTo(String value) {
            addCriterion("LRRY =", value, "lrry");
            return (Criteria) this;
        }

        public Criteria andLrryNotEqualTo(String value) {
            addCriterion("LRRY <>", value, "lrry");
            return (Criteria) this;
        }

        public Criteria andLrryGreaterThan(String value) {
            addCriterion("LRRY >", value, "lrry");
            return (Criteria) this;
        }

        public Criteria andLrryGreaterThanOrEqualTo(String value) {
            addCriterion("LRRY >=", value, "lrry");
            return (Criteria) this;
        }

        public Criteria andLrryLessThan(String value) {
            addCriterion("LRRY <", value, "lrry");
            return (Criteria) this;
        }

        public Criteria andLrryLessThanOrEqualTo(String value) {
            addCriterion("LRRY <=", value, "lrry");
            return (Criteria) this;
        }

        public Criteria andLrryLike(String value) {
            addCriterion("LRRY like", value, "lrry");
            return (Criteria) this;
        }

        public Criteria andLrryNotLike(String value) {
            addCriterion("LRRY not like", value, "lrry");
            return (Criteria) this;
        }

        public Criteria andLrryIn(List<String> values) {
            addCriterion("LRRY in", values, "lrry");
            return (Criteria) this;
        }

        public Criteria andLrryNotIn(List<String> values) {
            addCriterion("LRRY not in", values, "lrry");
            return (Criteria) this;
        }

        public Criteria andLrryBetween(String value1, String value2) {
            addCriterion("LRRY between", value1, value2, "lrry");
            return (Criteria) this;
        }

        public Criteria andLrryNotBetween(String value1, String value2) {
            addCriterion("LRRY not between", value1, value2, "lrry");
            return (Criteria) this;
        }

        public Criteria andLrrqIsNull() {
            addCriterion("LRRQ is null");
            return (Criteria) this;
        }

        public Criteria andLrrqIsNotNull() {
            addCriterion("LRRQ is not null");
            return (Criteria) this;
        }

        public Criteria andLrrqEqualTo(Date value) {
            addCriterionForJDBCDate("LRRQ =", value, "lrrq");
            return (Criteria) this;
        }

        public Criteria andLrrqNotEqualTo(Date value) {
            addCriterionForJDBCDate("LRRQ <>", value, "lrrq");
            return (Criteria) this;
        }

        public Criteria andLrrqGreaterThan(Date value) {
            addCriterionForJDBCDate("LRRQ >", value, "lrrq");
            return (Criteria) this;
        }

        public Criteria andLrrqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LRRQ >=", value, "lrrq");
            return (Criteria) this;
        }

        public Criteria andLrrqLessThan(Date value) {
            addCriterionForJDBCDate("LRRQ <", value, "lrrq");
            return (Criteria) this;
        }

        public Criteria andLrrqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LRRQ <=", value, "lrrq");
            return (Criteria) this;
        }

        public Criteria andLrrqIn(List<Date> values) {
            addCriterionForJDBCDate("LRRQ in", values, "lrrq");
            return (Criteria) this;
        }

        public Criteria andLrrqNotIn(List<Date> values) {
            addCriterionForJDBCDate("LRRQ not in", values, "lrrq");
            return (Criteria) this;
        }

        public Criteria andLrrqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LRRQ between", value1, value2, "lrrq");
            return (Criteria) this;
        }

        public Criteria andLrrqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LRRQ not between", value1, value2, "lrrq");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}