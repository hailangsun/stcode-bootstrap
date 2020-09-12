package com.stcode.bootstrap.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DwXxExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DwXxExample() {
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

        public Criteria andDwidIsNull() {
            addCriterion("DWID is null");
            return (Criteria) this;
        }

        public Criteria andDwidIsNotNull() {
            addCriterion("DWID is not null");
            return (Criteria) this;
        }

        public Criteria andDwidEqualTo(String value) {
            addCriterion("DWID =", value, "dwid");
            return (Criteria) this;
        }

        public Criteria andDwidNotEqualTo(String value) {
            addCriterion("DWID <>", value, "dwid");
            return (Criteria) this;
        }

        public Criteria andDwidGreaterThan(String value) {
            addCriterion("DWID >", value, "dwid");
            return (Criteria) this;
        }

        public Criteria andDwidGreaterThanOrEqualTo(String value) {
            addCriterion("DWID >=", value, "dwid");
            return (Criteria) this;
        }

        public Criteria andDwidLessThan(String value) {
            addCriterion("DWID <", value, "dwid");
            return (Criteria) this;
        }

        public Criteria andDwidLessThanOrEqualTo(String value) {
            addCriterion("DWID <=", value, "dwid");
            return (Criteria) this;
        }

        public Criteria andDwidLike(String value) {
            addCriterion("DWID like", value, "dwid");
            return (Criteria) this;
        }

        public Criteria andDwidNotLike(String value) {
            addCriterion("DWID not like", value, "dwid");
            return (Criteria) this;
        }

        public Criteria andDwidIn(List<String> values) {
            addCriterion("DWID in", values, "dwid");
            return (Criteria) this;
        }

        public Criteria andDwidNotIn(List<String> values) {
            addCriterion("DWID not in", values, "dwid");
            return (Criteria) this;
        }

        public Criteria andDwidBetween(String value1, String value2) {
            addCriterion("DWID between", value1, value2, "dwid");
            return (Criteria) this;
        }

        public Criteria andDwidNotBetween(String value1, String value2) {
            addCriterion("DWID not between", value1, value2, "dwid");
            return (Criteria) this;
        }

        public Criteria andDwdmIsNull() {
            addCriterion("DWDM is null");
            return (Criteria) this;
        }

        public Criteria andDwdmIsNotNull() {
            addCriterion("DWDM is not null");
            return (Criteria) this;
        }

        public Criteria andDwdmEqualTo(String value) {
            addCriterion("DWDM =", value, "dwdm");
            return (Criteria) this;
        }

        public Criteria andDwdmNotEqualTo(String value) {
            addCriterion("DWDM <>", value, "dwdm");
            return (Criteria) this;
        }

        public Criteria andDwdmGreaterThan(String value) {
            addCriterion("DWDM >", value, "dwdm");
            return (Criteria) this;
        }

        public Criteria andDwdmGreaterThanOrEqualTo(String value) {
            addCriterion("DWDM >=", value, "dwdm");
            return (Criteria) this;
        }

        public Criteria andDwdmLessThan(String value) {
            addCriterion("DWDM <", value, "dwdm");
            return (Criteria) this;
        }

        public Criteria andDwdmLessThanOrEqualTo(String value) {
            addCriterion("DWDM <=", value, "dwdm");
            return (Criteria) this;
        }

        public Criteria andDwdmLike(String value) {
            addCriterion("DWDM like", value, "dwdm");
            return (Criteria) this;
        }

        public Criteria andDwdmNotLike(String value) {
            addCriterion("DWDM not like", value, "dwdm");
            return (Criteria) this;
        }

        public Criteria andDwdmIn(List<String> values) {
            addCriterion("DWDM in", values, "dwdm");
            return (Criteria) this;
        }

        public Criteria andDwdmNotIn(List<String> values) {
            addCriterion("DWDM not in", values, "dwdm");
            return (Criteria) this;
        }

        public Criteria andDwdmBetween(String value1, String value2) {
            addCriterion("DWDM between", value1, value2, "dwdm");
            return (Criteria) this;
        }

        public Criteria andDwdmNotBetween(String value1, String value2) {
            addCriterion("DWDM not between", value1, value2, "dwdm");
            return (Criteria) this;
        }

        public Criteria andDjzhIsNull() {
            addCriterion("DJZH is null");
            return (Criteria) this;
        }

        public Criteria andDjzhIsNotNull() {
            addCriterion("DJZH is not null");
            return (Criteria) this;
        }

        public Criteria andDjzhEqualTo(String value) {
            addCriterion("DJZH =", value, "djzh");
            return (Criteria) this;
        }

        public Criteria andDjzhNotEqualTo(String value) {
            addCriterion("DJZH <>", value, "djzh");
            return (Criteria) this;
        }

        public Criteria andDjzhGreaterThan(String value) {
            addCriterion("DJZH >", value, "djzh");
            return (Criteria) this;
        }

        public Criteria andDjzhGreaterThanOrEqualTo(String value) {
            addCriterion("DJZH >=", value, "djzh");
            return (Criteria) this;
        }

        public Criteria andDjzhLessThan(String value) {
            addCriterion("DJZH <", value, "djzh");
            return (Criteria) this;
        }

        public Criteria andDjzhLessThanOrEqualTo(String value) {
            addCriterion("DJZH <=", value, "djzh");
            return (Criteria) this;
        }

        public Criteria andDjzhLike(String value) {
            addCriterion("DJZH like", value, "djzh");
            return (Criteria) this;
        }

        public Criteria andDjzhNotLike(String value) {
            addCriterion("DJZH not like", value, "djzh");
            return (Criteria) this;
        }

        public Criteria andDjzhIn(List<String> values) {
            addCriterion("DJZH in", values, "djzh");
            return (Criteria) this;
        }

        public Criteria andDjzhNotIn(List<String> values) {
            addCriterion("DJZH not in", values, "djzh");
            return (Criteria) this;
        }

        public Criteria andDjzhBetween(String value1, String value2) {
            addCriterion("DJZH between", value1, value2, "djzh");
            return (Criteria) this;
        }

        public Criteria andDjzhNotBetween(String value1, String value2) {
            addCriterion("DJZH not between", value1, value2, "djzh");
            return (Criteria) this;
        }

        public Criteria andDwmcIsNull() {
            addCriterion("DWMC is null");
            return (Criteria) this;
        }

        public Criteria andDwmcIsNotNull() {
            addCriterion("DWMC is not null");
            return (Criteria) this;
        }

        public Criteria andDwmcEqualTo(String value) {
            addCriterion("DWMC =", value, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcNotEqualTo(String value) {
            addCriterion("DWMC <>", value, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcGreaterThan(String value) {
            addCriterion("DWMC >", value, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcGreaterThanOrEqualTo(String value) {
            addCriterion("DWMC >=", value, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcLessThan(String value) {
            addCriterion("DWMC <", value, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcLessThanOrEqualTo(String value) {
            addCriterion("DWMC <=", value, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcLike(String value) {
            addCriterion("DWMC like", value, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcNotLike(String value) {
            addCriterion("DWMC not like", value, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcIn(List<String> values) {
            addCriterion("DWMC in", values, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcNotIn(List<String> values) {
            addCriterion("DWMC not in", values, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcBetween(String value1, String value2) {
            addCriterion("DWMC between", value1, value2, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcNotBetween(String value1, String value2) {
            addCriterion("DWMC not between", value1, value2, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwjcIsNull() {
            addCriterion("DWJC is null");
            return (Criteria) this;
        }

        public Criteria andDwjcIsNotNull() {
            addCriterion("DWJC is not null");
            return (Criteria) this;
        }

        public Criteria andDwjcEqualTo(String value) {
            addCriterion("DWJC =", value, "dwjc");
            return (Criteria) this;
        }

        public Criteria andDwjcNotEqualTo(String value) {
            addCriterion("DWJC <>", value, "dwjc");
            return (Criteria) this;
        }

        public Criteria andDwjcGreaterThan(String value) {
            addCriterion("DWJC >", value, "dwjc");
            return (Criteria) this;
        }

        public Criteria andDwjcGreaterThanOrEqualTo(String value) {
            addCriterion("DWJC >=", value, "dwjc");
            return (Criteria) this;
        }

        public Criteria andDwjcLessThan(String value) {
            addCriterion("DWJC <", value, "dwjc");
            return (Criteria) this;
        }

        public Criteria andDwjcLessThanOrEqualTo(String value) {
            addCriterion("DWJC <=", value, "dwjc");
            return (Criteria) this;
        }

        public Criteria andDwjcLike(String value) {
            addCriterion("DWJC like", value, "dwjc");
            return (Criteria) this;
        }

        public Criteria andDwjcNotLike(String value) {
            addCriterion("DWJC not like", value, "dwjc");
            return (Criteria) this;
        }

        public Criteria andDwjcIn(List<String> values) {
            addCriterion("DWJC in", values, "dwjc");
            return (Criteria) this;
        }

        public Criteria andDwjcNotIn(List<String> values) {
            addCriterion("DWJC not in", values, "dwjc");
            return (Criteria) this;
        }

        public Criteria andDwjcBetween(String value1, String value2) {
            addCriterion("DWJC between", value1, value2, "dwjc");
            return (Criteria) this;
        }

        public Criteria andDwjcNotBetween(String value1, String value2) {
            addCriterion("DWJC not between", value1, value2, "dwjc");
            return (Criteria) this;
        }

        public Criteria andJfqxIsNull() {
            addCriterion("JFQX is null");
            return (Criteria) this;
        }

        public Criteria andJfqxIsNotNull() {
            addCriterion("JFQX is not null");
            return (Criteria) this;
        }

        public Criteria andJfqxEqualTo(String value) {
            addCriterion("JFQX =", value, "jfqx");
            return (Criteria) this;
        }

        public Criteria andJfqxNotEqualTo(String value) {
            addCriterion("JFQX <>", value, "jfqx");
            return (Criteria) this;
        }

        public Criteria andJfqxGreaterThan(String value) {
            addCriterion("JFQX >", value, "jfqx");
            return (Criteria) this;
        }

        public Criteria andJfqxGreaterThanOrEqualTo(String value) {
            addCriterion("JFQX >=", value, "jfqx");
            return (Criteria) this;
        }

        public Criteria andJfqxLessThan(String value) {
            addCriterion("JFQX <", value, "jfqx");
            return (Criteria) this;
        }

        public Criteria andJfqxLessThanOrEqualTo(String value) {
            addCriterion("JFQX <=", value, "jfqx");
            return (Criteria) this;
        }

        public Criteria andJfqxLike(String value) {
            addCriterion("JFQX like", value, "jfqx");
            return (Criteria) this;
        }

        public Criteria andJfqxNotLike(String value) {
            addCriterion("JFQX not like", value, "jfqx");
            return (Criteria) this;
        }

        public Criteria andJfqxIn(List<String> values) {
            addCriterion("JFQX in", values, "jfqx");
            return (Criteria) this;
        }

        public Criteria andJfqxNotIn(List<String> values) {
            addCriterion("JFQX not in", values, "jfqx");
            return (Criteria) this;
        }

        public Criteria andJfqxBetween(String value1, String value2) {
            addCriterion("JFQX between", value1, value2, "jfqx");
            return (Criteria) this;
        }

        public Criteria andJfqxNotBetween(String value1, String value2) {
            addCriterion("JFQX not between", value1, value2, "jfqx");
            return (Criteria) this;
        }

        public Criteria andZfqxylIsNull() {
            addCriterion("ZFQXYL is null");
            return (Criteria) this;
        }

        public Criteria andZfqxylIsNotNull() {
            addCriterion("ZFQXYL is not null");
            return (Criteria) this;
        }

        public Criteria andZfqxylEqualTo(String value) {
            addCriterion("ZFQXYL =", value, "zfqxyl");
            return (Criteria) this;
        }

        public Criteria andZfqxylNotEqualTo(String value) {
            addCriterion("ZFQXYL <>", value, "zfqxyl");
            return (Criteria) this;
        }

        public Criteria andZfqxylGreaterThan(String value) {
            addCriterion("ZFQXYL >", value, "zfqxyl");
            return (Criteria) this;
        }

        public Criteria andZfqxylGreaterThanOrEqualTo(String value) {
            addCriterion("ZFQXYL >=", value, "zfqxyl");
            return (Criteria) this;
        }

        public Criteria andZfqxylLessThan(String value) {
            addCriterion("ZFQXYL <", value, "zfqxyl");
            return (Criteria) this;
        }

        public Criteria andZfqxylLessThanOrEqualTo(String value) {
            addCriterion("ZFQXYL <=", value, "zfqxyl");
            return (Criteria) this;
        }

        public Criteria andZfqxylLike(String value) {
            addCriterion("ZFQXYL like", value, "zfqxyl");
            return (Criteria) this;
        }

        public Criteria andZfqxylNotLike(String value) {
            addCriterion("ZFQXYL not like", value, "zfqxyl");
            return (Criteria) this;
        }

        public Criteria andZfqxylIn(List<String> values) {
            addCriterion("ZFQXYL in", values, "zfqxyl");
            return (Criteria) this;
        }

        public Criteria andZfqxylNotIn(List<String> values) {
            addCriterion("ZFQXYL not in", values, "zfqxyl");
            return (Criteria) this;
        }

        public Criteria andZfqxylBetween(String value1, String value2) {
            addCriterion("ZFQXYL between", value1, value2, "zfqxyl");
            return (Criteria) this;
        }

        public Criteria andZfqxylNotBetween(String value1, String value2) {
            addCriterion("ZFQXYL not between", value1, value2, "zfqxyl");
            return (Criteria) this;
        }

        public Criteria andZfqxgsIsNull() {
            addCriterion("ZFQXGS is null");
            return (Criteria) this;
        }

        public Criteria andZfqxgsIsNotNull() {
            addCriterion("ZFQXGS is not null");
            return (Criteria) this;
        }

        public Criteria andZfqxgsEqualTo(String value) {
            addCriterion("ZFQXGS =", value, "zfqxgs");
            return (Criteria) this;
        }

        public Criteria andZfqxgsNotEqualTo(String value) {
            addCriterion("ZFQXGS <>", value, "zfqxgs");
            return (Criteria) this;
        }

        public Criteria andZfqxgsGreaterThan(String value) {
            addCriterion("ZFQXGS >", value, "zfqxgs");
            return (Criteria) this;
        }

        public Criteria andZfqxgsGreaterThanOrEqualTo(String value) {
            addCriterion("ZFQXGS >=", value, "zfqxgs");
            return (Criteria) this;
        }

        public Criteria andZfqxgsLessThan(String value) {
            addCriterion("ZFQXGS <", value, "zfqxgs");
            return (Criteria) this;
        }

        public Criteria andZfqxgsLessThanOrEqualTo(String value) {
            addCriterion("ZFQXGS <=", value, "zfqxgs");
            return (Criteria) this;
        }

        public Criteria andZfqxgsLike(String value) {
            addCriterion("ZFQXGS like", value, "zfqxgs");
            return (Criteria) this;
        }

        public Criteria andZfqxgsNotLike(String value) {
            addCriterion("ZFQXGS not like", value, "zfqxgs");
            return (Criteria) this;
        }

        public Criteria andZfqxgsIn(List<String> values) {
            addCriterion("ZFQXGS in", values, "zfqxgs");
            return (Criteria) this;
        }

        public Criteria andZfqxgsNotIn(List<String> values) {
            addCriterion("ZFQXGS not in", values, "zfqxgs");
            return (Criteria) this;
        }

        public Criteria andZfqxgsBetween(String value1, String value2) {
            addCriterion("ZFQXGS between", value1, value2, "zfqxgs");
            return (Criteria) this;
        }

        public Criteria andZfqxgsNotBetween(String value1, String value2) {
            addCriterion("ZFQXGS not between", value1, value2, "zfqxgs");
            return (Criteria) this;
        }

        public Criteria andZfqxsyIsNull() {
            addCriterion("ZFQXSY is null");
            return (Criteria) this;
        }

        public Criteria andZfqxsyIsNotNull() {
            addCriterion("ZFQXSY is not null");
            return (Criteria) this;
        }

        public Criteria andZfqxsyEqualTo(String value) {
            addCriterion("ZFQXSY =", value, "zfqxsy");
            return (Criteria) this;
        }

        public Criteria andZfqxsyNotEqualTo(String value) {
            addCriterion("ZFQXSY <>", value, "zfqxsy");
            return (Criteria) this;
        }

        public Criteria andZfqxsyGreaterThan(String value) {
            addCriterion("ZFQXSY >", value, "zfqxsy");
            return (Criteria) this;
        }

        public Criteria andZfqxsyGreaterThanOrEqualTo(String value) {
            addCriterion("ZFQXSY >=", value, "zfqxsy");
            return (Criteria) this;
        }

        public Criteria andZfqxsyLessThan(String value) {
            addCriterion("ZFQXSY <", value, "zfqxsy");
            return (Criteria) this;
        }

        public Criteria andZfqxsyLessThanOrEqualTo(String value) {
            addCriterion("ZFQXSY <=", value, "zfqxsy");
            return (Criteria) this;
        }

        public Criteria andZfqxsyLike(String value) {
            addCriterion("ZFQXSY like", value, "zfqxsy");
            return (Criteria) this;
        }

        public Criteria andZfqxsyNotLike(String value) {
            addCriterion("ZFQXSY not like", value, "zfqxsy");
            return (Criteria) this;
        }

        public Criteria andZfqxsyIn(List<String> values) {
            addCriterion("ZFQXSY in", values, "zfqxsy");
            return (Criteria) this;
        }

        public Criteria andZfqxsyNotIn(List<String> values) {
            addCriterion("ZFQXSY not in", values, "zfqxsy");
            return (Criteria) this;
        }

        public Criteria andZfqxsyBetween(String value1, String value2) {
            addCriterion("ZFQXSY between", value1, value2, "zfqxsy");
            return (Criteria) this;
        }

        public Criteria andZfqxsyNotBetween(String value1, String value2) {
            addCriterion("ZFQXSY not between", value1, value2, "zfqxsy");
            return (Criteria) this;
        }

        public Criteria andSsqxIsNull() {
            addCriterion("SSQX is null");
            return (Criteria) this;
        }

        public Criteria andSsqxIsNotNull() {
            addCriterion("SSQX is not null");
            return (Criteria) this;
        }

        public Criteria andSsqxEqualTo(String value) {
            addCriterion("SSQX =", value, "ssqx");
            return (Criteria) this;
        }

        public Criteria andSsqxNotEqualTo(String value) {
            addCriterion("SSQX <>", value, "ssqx");
            return (Criteria) this;
        }

        public Criteria andSsqxGreaterThan(String value) {
            addCriterion("SSQX >", value, "ssqx");
            return (Criteria) this;
        }

        public Criteria andSsqxGreaterThanOrEqualTo(String value) {
            addCriterion("SSQX >=", value, "ssqx");
            return (Criteria) this;
        }

        public Criteria andSsqxLessThan(String value) {
            addCriterion("SSQX <", value, "ssqx");
            return (Criteria) this;
        }

        public Criteria andSsqxLessThanOrEqualTo(String value) {
            addCriterion("SSQX <=", value, "ssqx");
            return (Criteria) this;
        }

        public Criteria andSsqxLike(String value) {
            addCriterion("SSQX like", value, "ssqx");
            return (Criteria) this;
        }

        public Criteria andSsqxNotLike(String value) {
            addCriterion("SSQX not like", value, "ssqx");
            return (Criteria) this;
        }

        public Criteria andSsqxIn(List<String> values) {
            addCriterion("SSQX in", values, "ssqx");
            return (Criteria) this;
        }

        public Criteria andSsqxNotIn(List<String> values) {
            addCriterion("SSQX not in", values, "ssqx");
            return (Criteria) this;
        }

        public Criteria andSsqxBetween(String value1, String value2) {
            addCriterion("SSQX between", value1, value2, "ssqx");
            return (Criteria) this;
        }

        public Criteria andSsqxNotBetween(String value1, String value2) {
            addCriterion("SSQX not between", value1, value2, "ssqx");
            return (Criteria) this;
        }

        public Criteria andJjlxIsNull() {
            addCriterion("JJLX is null");
            return (Criteria) this;
        }

        public Criteria andJjlxIsNotNull() {
            addCriterion("JJLX is not null");
            return (Criteria) this;
        }

        public Criteria andJjlxEqualTo(String value) {
            addCriterion("JJLX =", value, "jjlx");
            return (Criteria) this;
        }

        public Criteria andJjlxNotEqualTo(String value) {
            addCriterion("JJLX <>", value, "jjlx");
            return (Criteria) this;
        }

        public Criteria andJjlxGreaterThan(String value) {
            addCriterion("JJLX >", value, "jjlx");
            return (Criteria) this;
        }

        public Criteria andJjlxGreaterThanOrEqualTo(String value) {
            addCriterion("JJLX >=", value, "jjlx");
            return (Criteria) this;
        }

        public Criteria andJjlxLessThan(String value) {
            addCriterion("JJLX <", value, "jjlx");
            return (Criteria) this;
        }

        public Criteria andJjlxLessThanOrEqualTo(String value) {
            addCriterion("JJLX <=", value, "jjlx");
            return (Criteria) this;
        }

        public Criteria andJjlxLike(String value) {
            addCriterion("JJLX like", value, "jjlx");
            return (Criteria) this;
        }

        public Criteria andJjlxNotLike(String value) {
            addCriterion("JJLX not like", value, "jjlx");
            return (Criteria) this;
        }

        public Criteria andJjlxIn(List<String> values) {
            addCriterion("JJLX in", values, "jjlx");
            return (Criteria) this;
        }

        public Criteria andJjlxNotIn(List<String> values) {
            addCriterion("JJLX not in", values, "jjlx");
            return (Criteria) this;
        }

        public Criteria andJjlxBetween(String value1, String value2) {
            addCriterion("JJLX between", value1, value2, "jjlx");
            return (Criteria) this;
        }

        public Criteria andJjlxNotBetween(String value1, String value2) {
            addCriterion("JJLX not between", value1, value2, "jjlx");
            return (Criteria) this;
        }

        public Criteria andDwlxIsNull() {
            addCriterion("DWLX is null");
            return (Criteria) this;
        }

        public Criteria andDwlxIsNotNull() {
            addCriterion("DWLX is not null");
            return (Criteria) this;
        }

        public Criteria andDwlxEqualTo(String value) {
            addCriterion("DWLX =", value, "dwlx");
            return (Criteria) this;
        }

        public Criteria andDwlxNotEqualTo(String value) {
            addCriterion("DWLX <>", value, "dwlx");
            return (Criteria) this;
        }

        public Criteria andDwlxGreaterThan(String value) {
            addCriterion("DWLX >", value, "dwlx");
            return (Criteria) this;
        }

        public Criteria andDwlxGreaterThanOrEqualTo(String value) {
            addCriterion("DWLX >=", value, "dwlx");
            return (Criteria) this;
        }

        public Criteria andDwlxLessThan(String value) {
            addCriterion("DWLX <", value, "dwlx");
            return (Criteria) this;
        }

        public Criteria andDwlxLessThanOrEqualTo(String value) {
            addCriterion("DWLX <=", value, "dwlx");
            return (Criteria) this;
        }

        public Criteria andDwlxLike(String value) {
            addCriterion("DWLX like", value, "dwlx");
            return (Criteria) this;
        }

        public Criteria andDwlxNotLike(String value) {
            addCriterion("DWLX not like", value, "dwlx");
            return (Criteria) this;
        }

        public Criteria andDwlxIn(List<String> values) {
            addCriterion("DWLX in", values, "dwlx");
            return (Criteria) this;
        }

        public Criteria andDwlxNotIn(List<String> values) {
            addCriterion("DWLX not in", values, "dwlx");
            return (Criteria) this;
        }

        public Criteria andDwlxBetween(String value1, String value2) {
            addCriterion("DWLX between", value1, value2, "dwlx");
            return (Criteria) this;
        }

        public Criteria andDwlxNotBetween(String value1, String value2) {
            addCriterion("DWLX not between", value1, value2, "dwlx");
            return (Criteria) this;
        }

        public Criteria andLsgxIsNull() {
            addCriterion("LSGX is null");
            return (Criteria) this;
        }

        public Criteria andLsgxIsNotNull() {
            addCriterion("LSGX is not null");
            return (Criteria) this;
        }

        public Criteria andLsgxEqualTo(String value) {
            addCriterion("LSGX =", value, "lsgx");
            return (Criteria) this;
        }

        public Criteria andLsgxNotEqualTo(String value) {
            addCriterion("LSGX <>", value, "lsgx");
            return (Criteria) this;
        }

        public Criteria andLsgxGreaterThan(String value) {
            addCriterion("LSGX >", value, "lsgx");
            return (Criteria) this;
        }

        public Criteria andLsgxGreaterThanOrEqualTo(String value) {
            addCriterion("LSGX >=", value, "lsgx");
            return (Criteria) this;
        }

        public Criteria andLsgxLessThan(String value) {
            addCriterion("LSGX <", value, "lsgx");
            return (Criteria) this;
        }

        public Criteria andLsgxLessThanOrEqualTo(String value) {
            addCriterion("LSGX <=", value, "lsgx");
            return (Criteria) this;
        }

        public Criteria andLsgxLike(String value) {
            addCriterion("LSGX like", value, "lsgx");
            return (Criteria) this;
        }

        public Criteria andLsgxNotLike(String value) {
            addCriterion("LSGX not like", value, "lsgx");
            return (Criteria) this;
        }

        public Criteria andLsgxIn(List<String> values) {
            addCriterion("LSGX in", values, "lsgx");
            return (Criteria) this;
        }

        public Criteria andLsgxNotIn(List<String> values) {
            addCriterion("LSGX not in", values, "lsgx");
            return (Criteria) this;
        }

        public Criteria andLsgxBetween(String value1, String value2) {
            addCriterion("LSGX between", value1, value2, "lsgx");
            return (Criteria) this;
        }

        public Criteria andLsgxNotBetween(String value1, String value2) {
            addCriterion("LSGX not between", value1, value2, "lsgx");
            return (Criteria) this;
        }

        public Criteria andHyxtIsNull() {
            addCriterion("HYXT is null");
            return (Criteria) this;
        }

        public Criteria andHyxtIsNotNull() {
            addCriterion("HYXT is not null");
            return (Criteria) this;
        }

        public Criteria andHyxtEqualTo(String value) {
            addCriterion("HYXT =", value, "hyxt");
            return (Criteria) this;
        }

        public Criteria andHyxtNotEqualTo(String value) {
            addCriterion("HYXT <>", value, "hyxt");
            return (Criteria) this;
        }

        public Criteria andHyxtGreaterThan(String value) {
            addCriterion("HYXT >", value, "hyxt");
            return (Criteria) this;
        }

        public Criteria andHyxtGreaterThanOrEqualTo(String value) {
            addCriterion("HYXT >=", value, "hyxt");
            return (Criteria) this;
        }

        public Criteria andHyxtLessThan(String value) {
            addCriterion("HYXT <", value, "hyxt");
            return (Criteria) this;
        }

        public Criteria andHyxtLessThanOrEqualTo(String value) {
            addCriterion("HYXT <=", value, "hyxt");
            return (Criteria) this;
        }

        public Criteria andHyxtLike(String value) {
            addCriterion("HYXT like", value, "hyxt");
            return (Criteria) this;
        }

        public Criteria andHyxtNotLike(String value) {
            addCriterion("HYXT not like", value, "hyxt");
            return (Criteria) this;
        }

        public Criteria andHyxtIn(List<String> values) {
            addCriterion("HYXT in", values, "hyxt");
            return (Criteria) this;
        }

        public Criteria andHyxtNotIn(List<String> values) {
            addCriterion("HYXT not in", values, "hyxt");
            return (Criteria) this;
        }

        public Criteria andHyxtBetween(String value1, String value2) {
            addCriterion("HYXT between", value1, value2, "hyxt");
            return (Criteria) this;
        }

        public Criteria andHyxtNotBetween(String value1, String value2) {
            addCriterion("HYXT not between", value1, value2, "hyxt");
            return (Criteria) this;
        }

        public Criteria andHydmIsNull() {
            addCriterion("HYDM is null");
            return (Criteria) this;
        }

        public Criteria andHydmIsNotNull() {
            addCriterion("HYDM is not null");
            return (Criteria) this;
        }

        public Criteria andHydmEqualTo(String value) {
            addCriterion("HYDM =", value, "hydm");
            return (Criteria) this;
        }

        public Criteria andHydmNotEqualTo(String value) {
            addCriterion("HYDM <>", value, "hydm");
            return (Criteria) this;
        }

        public Criteria andHydmGreaterThan(String value) {
            addCriterion("HYDM >", value, "hydm");
            return (Criteria) this;
        }

        public Criteria andHydmGreaterThanOrEqualTo(String value) {
            addCriterion("HYDM >=", value, "hydm");
            return (Criteria) this;
        }

        public Criteria andHydmLessThan(String value) {
            addCriterion("HYDM <", value, "hydm");
            return (Criteria) this;
        }

        public Criteria andHydmLessThanOrEqualTo(String value) {
            addCriterion("HYDM <=", value, "hydm");
            return (Criteria) this;
        }

        public Criteria andHydmLike(String value) {
            addCriterion("HYDM like", value, "hydm");
            return (Criteria) this;
        }

        public Criteria andHydmNotLike(String value) {
            addCriterion("HYDM not like", value, "hydm");
            return (Criteria) this;
        }

        public Criteria andHydmIn(List<String> values) {
            addCriterion("HYDM in", values, "hydm");
            return (Criteria) this;
        }

        public Criteria andHydmNotIn(List<String> values) {
            addCriterion("HYDM not in", values, "hydm");
            return (Criteria) this;
        }

        public Criteria andHydmBetween(String value1, String value2) {
            addCriterion("HYDM between", value1, value2, "hydm");
            return (Criteria) this;
        }

        public Criteria andHydmNotBetween(String value1, String value2) {
            addCriterion("HYDM not between", value1, value2, "hydm");
            return (Criteria) this;
        }

        public Criteria andCtfsIsNull() {
            addCriterion("CTFS is null");
            return (Criteria) this;
        }

        public Criteria andCtfsIsNotNull() {
            addCriterion("CTFS is not null");
            return (Criteria) this;
        }

        public Criteria andCtfsEqualTo(String value) {
            addCriterion("CTFS =", value, "ctfs");
            return (Criteria) this;
        }

        public Criteria andCtfsNotEqualTo(String value) {
            addCriterion("CTFS <>", value, "ctfs");
            return (Criteria) this;
        }

        public Criteria andCtfsGreaterThan(String value) {
            addCriterion("CTFS >", value, "ctfs");
            return (Criteria) this;
        }

        public Criteria andCtfsGreaterThanOrEqualTo(String value) {
            addCriterion("CTFS >=", value, "ctfs");
            return (Criteria) this;
        }

        public Criteria andCtfsLessThan(String value) {
            addCriterion("CTFS <", value, "ctfs");
            return (Criteria) this;
        }

        public Criteria andCtfsLessThanOrEqualTo(String value) {
            addCriterion("CTFS <=", value, "ctfs");
            return (Criteria) this;
        }

        public Criteria andCtfsLike(String value) {
            addCriterion("CTFS like", value, "ctfs");
            return (Criteria) this;
        }

        public Criteria andCtfsNotLike(String value) {
            addCriterion("CTFS not like", value, "ctfs");
            return (Criteria) this;
        }

        public Criteria andCtfsIn(List<String> values) {
            addCriterion("CTFS in", values, "ctfs");
            return (Criteria) this;
        }

        public Criteria andCtfsNotIn(List<String> values) {
            addCriterion("CTFS not in", values, "ctfs");
            return (Criteria) this;
        }

        public Criteria andCtfsBetween(String value1, String value2) {
            addCriterion("CTFS between", value1, value2, "ctfs");
            return (Criteria) this;
        }

        public Criteria andCtfsNotBetween(String value1, String value2) {
            addCriterion("CTFS not between", value1, value2, "ctfs");
            return (Criteria) this;
        }

        public Criteria andJsfsIsNull() {
            addCriterion("JSFS is null");
            return (Criteria) this;
        }

        public Criteria andJsfsIsNotNull() {
            addCriterion("JSFS is not null");
            return (Criteria) this;
        }

        public Criteria andJsfsEqualTo(String value) {
            addCriterion("JSFS =", value, "jsfs");
            return (Criteria) this;
        }

        public Criteria andJsfsNotEqualTo(String value) {
            addCriterion("JSFS <>", value, "jsfs");
            return (Criteria) this;
        }

        public Criteria andJsfsGreaterThan(String value) {
            addCriterion("JSFS >", value, "jsfs");
            return (Criteria) this;
        }

        public Criteria andJsfsGreaterThanOrEqualTo(String value) {
            addCriterion("JSFS >=", value, "jsfs");
            return (Criteria) this;
        }

        public Criteria andJsfsLessThan(String value) {
            addCriterion("JSFS <", value, "jsfs");
            return (Criteria) this;
        }

        public Criteria andJsfsLessThanOrEqualTo(String value) {
            addCriterion("JSFS <=", value, "jsfs");
            return (Criteria) this;
        }

        public Criteria andJsfsLike(String value) {
            addCriterion("JSFS like", value, "jsfs");
            return (Criteria) this;
        }

        public Criteria andJsfsNotLike(String value) {
            addCriterion("JSFS not like", value, "jsfs");
            return (Criteria) this;
        }

        public Criteria andJsfsIn(List<String> values) {
            addCriterion("JSFS in", values, "jsfs");
            return (Criteria) this;
        }

        public Criteria andJsfsNotIn(List<String> values) {
            addCriterion("JSFS not in", values, "jsfs");
            return (Criteria) this;
        }

        public Criteria andJsfsBetween(String value1, String value2) {
            addCriterion("JSFS between", value1, value2, "jsfs");
            return (Criteria) this;
        }

        public Criteria andJsfsNotBetween(String value1, String value2) {
            addCriterion("JSFS not between", value1, value2, "jsfs");
            return (Criteria) this;
        }

        public Criteria andJszqIsNull() {
            addCriterion("JSZQ is null");
            return (Criteria) this;
        }

        public Criteria andJszqIsNotNull() {
            addCriterion("JSZQ is not null");
            return (Criteria) this;
        }

        public Criteria andJszqEqualTo(Short value) {
            addCriterion("JSZQ =", value, "jszq");
            return (Criteria) this;
        }

        public Criteria andJszqNotEqualTo(Short value) {
            addCriterion("JSZQ <>", value, "jszq");
            return (Criteria) this;
        }

        public Criteria andJszqGreaterThan(Short value) {
            addCriterion("JSZQ >", value, "jszq");
            return (Criteria) this;
        }

        public Criteria andJszqGreaterThanOrEqualTo(Short value) {
            addCriterion("JSZQ >=", value, "jszq");
            return (Criteria) this;
        }

        public Criteria andJszqLessThan(Short value) {
            addCriterion("JSZQ <", value, "jszq");
            return (Criteria) this;
        }

        public Criteria andJszqLessThanOrEqualTo(Short value) {
            addCriterion("JSZQ <=", value, "jszq");
            return (Criteria) this;
        }

        public Criteria andJszqIn(List<Short> values) {
            addCriterion("JSZQ in", values, "jszq");
            return (Criteria) this;
        }

        public Criteria andJszqNotIn(List<Short> values) {
            addCriterion("JSZQ not in", values, "jszq");
            return (Criteria) this;
        }

        public Criteria andJszqBetween(Short value1, Short value2) {
            addCriterion("JSZQ between", value1, value2, "jszq");
            return (Criteria) this;
        }

        public Criteria andJszqNotBetween(Short value1, Short value2) {
            addCriterion("JSZQ not between", value1, value2, "jszq");
            return (Criteria) this;
        }

        public Criteria andSbfsIsNull() {
            addCriterion("SBFS is null");
            return (Criteria) this;
        }

        public Criteria andSbfsIsNotNull() {
            addCriterion("SBFS is not null");
            return (Criteria) this;
        }

        public Criteria andSbfsEqualTo(String value) {
            addCriterion("SBFS =", value, "sbfs");
            return (Criteria) this;
        }

        public Criteria andSbfsNotEqualTo(String value) {
            addCriterion("SBFS <>", value, "sbfs");
            return (Criteria) this;
        }

        public Criteria andSbfsGreaterThan(String value) {
            addCriterion("SBFS >", value, "sbfs");
            return (Criteria) this;
        }

        public Criteria andSbfsGreaterThanOrEqualTo(String value) {
            addCriterion("SBFS >=", value, "sbfs");
            return (Criteria) this;
        }

        public Criteria andSbfsLessThan(String value) {
            addCriterion("SBFS <", value, "sbfs");
            return (Criteria) this;
        }

        public Criteria andSbfsLessThanOrEqualTo(String value) {
            addCriterion("SBFS <=", value, "sbfs");
            return (Criteria) this;
        }

        public Criteria andSbfsLike(String value) {
            addCriterion("SBFS like", value, "sbfs");
            return (Criteria) this;
        }

        public Criteria andSbfsNotLike(String value) {
            addCriterion("SBFS not like", value, "sbfs");
            return (Criteria) this;
        }

        public Criteria andSbfsIn(List<String> values) {
            addCriterion("SBFS in", values, "sbfs");
            return (Criteria) this;
        }

        public Criteria andSbfsNotIn(List<String> values) {
            addCriterion("SBFS not in", values, "sbfs");
            return (Criteria) this;
        }

        public Criteria andSbfsBetween(String value1, String value2) {
            addCriterion("SBFS between", value1, value2, "sbfs");
            return (Criteria) this;
        }

        public Criteria andSbfsNotBetween(String value1, String value2) {
            addCriterion("SBFS not between", value1, value2, "sbfs");
            return (Criteria) this;
        }

        public Criteria andEjidIsNull() {
            addCriterion("EJID is null");
            return (Criteria) this;
        }

        public Criteria andEjidIsNotNull() {
            addCriterion("EJID is not null");
            return (Criteria) this;
        }

        public Criteria andEjidEqualTo(String value) {
            addCriterion("EJID =", value, "ejid");
            return (Criteria) this;
        }

        public Criteria andEjidNotEqualTo(String value) {
            addCriterion("EJID <>", value, "ejid");
            return (Criteria) this;
        }

        public Criteria andEjidGreaterThan(String value) {
            addCriterion("EJID >", value, "ejid");
            return (Criteria) this;
        }

        public Criteria andEjidGreaterThanOrEqualTo(String value) {
            addCriterion("EJID >=", value, "ejid");
            return (Criteria) this;
        }

        public Criteria andEjidLessThan(String value) {
            addCriterion("EJID <", value, "ejid");
            return (Criteria) this;
        }

        public Criteria andEjidLessThanOrEqualTo(String value) {
            addCriterion("EJID <=", value, "ejid");
            return (Criteria) this;
        }

        public Criteria andEjidLike(String value) {
            addCriterion("EJID like", value, "ejid");
            return (Criteria) this;
        }

        public Criteria andEjidNotLike(String value) {
            addCriterion("EJID not like", value, "ejid");
            return (Criteria) this;
        }

        public Criteria andEjidIn(List<String> values) {
            addCriterion("EJID in", values, "ejid");
            return (Criteria) this;
        }

        public Criteria andEjidNotIn(List<String> values) {
            addCriterion("EJID not in", values, "ejid");
            return (Criteria) this;
        }

        public Criteria andEjidBetween(String value1, String value2) {
            addCriterion("EJID between", value1, value2, "ejid");
            return (Criteria) this;
        }

        public Criteria andEjidNotBetween(String value1, String value2) {
            addCriterion("EJID not between", value1, value2, "ejid");
            return (Criteria) this;
        }

        public Criteria andTjlx1IsNull() {
            addCriterion("TJLX1 is null");
            return (Criteria) this;
        }

        public Criteria andTjlx1IsNotNull() {
            addCriterion("TJLX1 is not null");
            return (Criteria) this;
        }

        public Criteria andTjlx1EqualTo(String value) {
            addCriterion("TJLX1 =", value, "tjlx1");
            return (Criteria) this;
        }

        public Criteria andTjlx1NotEqualTo(String value) {
            addCriterion("TJLX1 <>", value, "tjlx1");
            return (Criteria) this;
        }

        public Criteria andTjlx1GreaterThan(String value) {
            addCriterion("TJLX1 >", value, "tjlx1");
            return (Criteria) this;
        }

        public Criteria andTjlx1GreaterThanOrEqualTo(String value) {
            addCriterion("TJLX1 >=", value, "tjlx1");
            return (Criteria) this;
        }

        public Criteria andTjlx1LessThan(String value) {
            addCriterion("TJLX1 <", value, "tjlx1");
            return (Criteria) this;
        }

        public Criteria andTjlx1LessThanOrEqualTo(String value) {
            addCriterion("TJLX1 <=", value, "tjlx1");
            return (Criteria) this;
        }

        public Criteria andTjlx1Like(String value) {
            addCriterion("TJLX1 like", value, "tjlx1");
            return (Criteria) this;
        }

        public Criteria andTjlx1NotLike(String value) {
            addCriterion("TJLX1 not like", value, "tjlx1");
            return (Criteria) this;
        }

        public Criteria andTjlx1In(List<String> values) {
            addCriterion("TJLX1 in", values, "tjlx1");
            return (Criteria) this;
        }

        public Criteria andTjlx1NotIn(List<String> values) {
            addCriterion("TJLX1 not in", values, "tjlx1");
            return (Criteria) this;
        }

        public Criteria andTjlx1Between(String value1, String value2) {
            addCriterion("TJLX1 between", value1, value2, "tjlx1");
            return (Criteria) this;
        }

        public Criteria andTjlx1NotBetween(String value1, String value2) {
            addCriterion("TJLX1 not between", value1, value2, "tjlx1");
            return (Criteria) this;
        }

        public Criteria andTjlx2IsNull() {
            addCriterion("TJLX2 is null");
            return (Criteria) this;
        }

        public Criteria andTjlx2IsNotNull() {
            addCriterion("TJLX2 is not null");
            return (Criteria) this;
        }

        public Criteria andTjlx2EqualTo(String value) {
            addCriterion("TJLX2 =", value, "tjlx2");
            return (Criteria) this;
        }

        public Criteria andTjlx2NotEqualTo(String value) {
            addCriterion("TJLX2 <>", value, "tjlx2");
            return (Criteria) this;
        }

        public Criteria andTjlx2GreaterThan(String value) {
            addCriterion("TJLX2 >", value, "tjlx2");
            return (Criteria) this;
        }

        public Criteria andTjlx2GreaterThanOrEqualTo(String value) {
            addCriterion("TJLX2 >=", value, "tjlx2");
            return (Criteria) this;
        }

        public Criteria andTjlx2LessThan(String value) {
            addCriterion("TJLX2 <", value, "tjlx2");
            return (Criteria) this;
        }

        public Criteria andTjlx2LessThanOrEqualTo(String value) {
            addCriterion("TJLX2 <=", value, "tjlx2");
            return (Criteria) this;
        }

        public Criteria andTjlx2Like(String value) {
            addCriterion("TJLX2 like", value, "tjlx2");
            return (Criteria) this;
        }

        public Criteria andTjlx2NotLike(String value) {
            addCriterion("TJLX2 not like", value, "tjlx2");
            return (Criteria) this;
        }

        public Criteria andTjlx2In(List<String> values) {
            addCriterion("TJLX2 in", values, "tjlx2");
            return (Criteria) this;
        }

        public Criteria andTjlx2NotIn(List<String> values) {
            addCriterion("TJLX2 not in", values, "tjlx2");
            return (Criteria) this;
        }

        public Criteria andTjlx2Between(String value1, String value2) {
            addCriterion("TJLX2 between", value1, value2, "tjlx2");
            return (Criteria) this;
        }

        public Criteria andTjlx2NotBetween(String value1, String value2) {
            addCriterion("TJLX2 not between", value1, value2, "tjlx2");
            return (Criteria) this;
        }

        public Criteria andTjlx3IsNull() {
            addCriterion("TJLX3 is null");
            return (Criteria) this;
        }

        public Criteria andTjlx3IsNotNull() {
            addCriterion("TJLX3 is not null");
            return (Criteria) this;
        }

        public Criteria andTjlx3EqualTo(String value) {
            addCriterion("TJLX3 =", value, "tjlx3");
            return (Criteria) this;
        }

        public Criteria andTjlx3NotEqualTo(String value) {
            addCriterion("TJLX3 <>", value, "tjlx3");
            return (Criteria) this;
        }

        public Criteria andTjlx3GreaterThan(String value) {
            addCriterion("TJLX3 >", value, "tjlx3");
            return (Criteria) this;
        }

        public Criteria andTjlx3GreaterThanOrEqualTo(String value) {
            addCriterion("TJLX3 >=", value, "tjlx3");
            return (Criteria) this;
        }

        public Criteria andTjlx3LessThan(String value) {
            addCriterion("TJLX3 <", value, "tjlx3");
            return (Criteria) this;
        }

        public Criteria andTjlx3LessThanOrEqualTo(String value) {
            addCriterion("TJLX3 <=", value, "tjlx3");
            return (Criteria) this;
        }

        public Criteria andTjlx3Like(String value) {
            addCriterion("TJLX3 like", value, "tjlx3");
            return (Criteria) this;
        }

        public Criteria andTjlx3NotLike(String value) {
            addCriterion("TJLX3 not like", value, "tjlx3");
            return (Criteria) this;
        }

        public Criteria andTjlx3In(List<String> values) {
            addCriterion("TJLX3 in", values, "tjlx3");
            return (Criteria) this;
        }

        public Criteria andTjlx3NotIn(List<String> values) {
            addCriterion("TJLX3 not in", values, "tjlx3");
            return (Criteria) this;
        }

        public Criteria andTjlx3Between(String value1, String value2) {
            addCriterion("TJLX3 between", value1, value2, "tjlx3");
            return (Criteria) this;
        }

        public Criteria andTjlx3NotBetween(String value1, String value2) {
            addCriterion("TJLX3 not between", value1, value2, "tjlx3");
            return (Criteria) this;
        }

        public Criteria andTjlx4IsNull() {
            addCriterion("TJLX4 is null");
            return (Criteria) this;
        }

        public Criteria andTjlx4IsNotNull() {
            addCriterion("TJLX4 is not null");
            return (Criteria) this;
        }

        public Criteria andTjlx4EqualTo(String value) {
            addCriterion("TJLX4 =", value, "tjlx4");
            return (Criteria) this;
        }

        public Criteria andTjlx4NotEqualTo(String value) {
            addCriterion("TJLX4 <>", value, "tjlx4");
            return (Criteria) this;
        }

        public Criteria andTjlx4GreaterThan(String value) {
            addCriterion("TJLX4 >", value, "tjlx4");
            return (Criteria) this;
        }

        public Criteria andTjlx4GreaterThanOrEqualTo(String value) {
            addCriterion("TJLX4 >=", value, "tjlx4");
            return (Criteria) this;
        }

        public Criteria andTjlx4LessThan(String value) {
            addCriterion("TJLX4 <", value, "tjlx4");
            return (Criteria) this;
        }

        public Criteria andTjlx4LessThanOrEqualTo(String value) {
            addCriterion("TJLX4 <=", value, "tjlx4");
            return (Criteria) this;
        }

        public Criteria andTjlx4Like(String value) {
            addCriterion("TJLX4 like", value, "tjlx4");
            return (Criteria) this;
        }

        public Criteria andTjlx4NotLike(String value) {
            addCriterion("TJLX4 not like", value, "tjlx4");
            return (Criteria) this;
        }

        public Criteria andTjlx4In(List<String> values) {
            addCriterion("TJLX4 in", values, "tjlx4");
            return (Criteria) this;
        }

        public Criteria andTjlx4NotIn(List<String> values) {
            addCriterion("TJLX4 not in", values, "tjlx4");
            return (Criteria) this;
        }

        public Criteria andTjlx4Between(String value1, String value2) {
            addCriterion("TJLX4 between", value1, value2, "tjlx4");
            return (Criteria) this;
        }

        public Criteria andTjlx4NotBetween(String value1, String value2) {
            addCriterion("TJLX4 not between", value1, value2, "tjlx4");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("FLAG is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(String value) {
            addCriterion("FLAG =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(String value) {
            addCriterion("FLAG <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(String value) {
            addCriterion("FLAG >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(String value) {
            addCriterion("FLAG >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(String value) {
            addCriterion("FLAG <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(String value) {
            addCriterion("FLAG <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLike(String value) {
            addCriterion("FLAG like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotLike(String value) {
            addCriterion("FLAG not like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<String> values) {
            addCriterion("FLAG in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<String> values) {
            addCriterion("FLAG not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(String value1, String value2) {
            addCriterion("FLAG between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(String value1, String value2) {
            addCriterion("FLAG not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andJbrIsNull() {
            addCriterion("JBR is null");
            return (Criteria) this;
        }

        public Criteria andJbrIsNotNull() {
            addCriterion("JBR is not null");
            return (Criteria) this;
        }

        public Criteria andJbrEqualTo(String value) {
            addCriterion("JBR =", value, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrNotEqualTo(String value) {
            addCriterion("JBR <>", value, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrGreaterThan(String value) {
            addCriterion("JBR >", value, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrGreaterThanOrEqualTo(String value) {
            addCriterion("JBR >=", value, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrLessThan(String value) {
            addCriterion("JBR <", value, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrLessThanOrEqualTo(String value) {
            addCriterion("JBR <=", value, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrLike(String value) {
            addCriterion("JBR like", value, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrNotLike(String value) {
            addCriterion("JBR not like", value, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrIn(List<String> values) {
            addCriterion("JBR in", values, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrNotIn(List<String> values) {
            addCriterion("JBR not in", values, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrBetween(String value1, String value2) {
            addCriterion("JBR between", value1, value2, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrNotBetween(String value1, String value2) {
            addCriterion("JBR not between", value1, value2, "jbr");
            return (Criteria) this;
        }

        public Criteria andBlrqIsNull() {
            addCriterion("BLRQ is null");
            return (Criteria) this;
        }

        public Criteria andBlrqIsNotNull() {
            addCriterion("BLRQ is not null");
            return (Criteria) this;
        }

        public Criteria andBlrqEqualTo(Date value) {
            addCriterionForJDBCDate("BLRQ =", value, "blrq");
            return (Criteria) this;
        }

        public Criteria andBlrqNotEqualTo(Date value) {
            addCriterionForJDBCDate("BLRQ <>", value, "blrq");
            return (Criteria) this;
        }

        public Criteria andBlrqGreaterThan(Date value) {
            addCriterionForJDBCDate("BLRQ >", value, "blrq");
            return (Criteria) this;
        }

        public Criteria andBlrqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BLRQ >=", value, "blrq");
            return (Criteria) this;
        }

        public Criteria andBlrqLessThan(Date value) {
            addCriterionForJDBCDate("BLRQ <", value, "blrq");
            return (Criteria) this;
        }

        public Criteria andBlrqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BLRQ <=", value, "blrq");
            return (Criteria) this;
        }

        public Criteria andBlrqIn(List<Date> values) {
            addCriterionForJDBCDate("BLRQ in", values, "blrq");
            return (Criteria) this;
        }

        public Criteria andBlrqNotIn(List<Date> values) {
            addCriterionForJDBCDate("BLRQ not in", values, "blrq");
            return (Criteria) this;
        }

        public Criteria andBlrqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("BLRQ between", value1, value2, "blrq");
            return (Criteria) this;
        }

        public Criteria andBlrqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("BLRQ not between", value1, value2, "blrq");
            return (Criteria) this;
        }

        public Criteria andTcbzIsNull() {
            addCriterion("TCBZ is null");
            return (Criteria) this;
        }

        public Criteria andTcbzIsNotNull() {
            addCriterion("TCBZ is not null");
            return (Criteria) this;
        }

        public Criteria andTcbzEqualTo(String value) {
            addCriterion("TCBZ =", value, "tcbz");
            return (Criteria) this;
        }

        public Criteria andTcbzNotEqualTo(String value) {
            addCriterion("TCBZ <>", value, "tcbz");
            return (Criteria) this;
        }

        public Criteria andTcbzGreaterThan(String value) {
            addCriterion("TCBZ >", value, "tcbz");
            return (Criteria) this;
        }

        public Criteria andTcbzGreaterThanOrEqualTo(String value) {
            addCriterion("TCBZ >=", value, "tcbz");
            return (Criteria) this;
        }

        public Criteria andTcbzLessThan(String value) {
            addCriterion("TCBZ <", value, "tcbz");
            return (Criteria) this;
        }

        public Criteria andTcbzLessThanOrEqualTo(String value) {
            addCriterion("TCBZ <=", value, "tcbz");
            return (Criteria) this;
        }

        public Criteria andTcbzLike(String value) {
            addCriterion("TCBZ like", value, "tcbz");
            return (Criteria) this;
        }

        public Criteria andTcbzNotLike(String value) {
            addCriterion("TCBZ not like", value, "tcbz");
            return (Criteria) this;
        }

        public Criteria andTcbzIn(List<String> values) {
            addCriterion("TCBZ in", values, "tcbz");
            return (Criteria) this;
        }

        public Criteria andTcbzNotIn(List<String> values) {
            addCriterion("TCBZ not in", values, "tcbz");
            return (Criteria) this;
        }

        public Criteria andTcbzBetween(String value1, String value2) {
            addCriterion("TCBZ between", value1, value2, "tcbz");
            return (Criteria) this;
        }

        public Criteria andTcbzNotBetween(String value1, String value2) {
            addCriterion("TCBZ not between", value1, value2, "tcbz");
            return (Criteria) this;
        }

        public Criteria andQxbmIsNull() {
            addCriterion("QXBM is null");
            return (Criteria) this;
        }

        public Criteria andQxbmIsNotNull() {
            addCriterion("QXBM is not null");
            return (Criteria) this;
        }

        public Criteria andQxbmEqualTo(String value) {
            addCriterion("QXBM =", value, "qxbm");
            return (Criteria) this;
        }

        public Criteria andQxbmNotEqualTo(String value) {
            addCriterion("QXBM <>", value, "qxbm");
            return (Criteria) this;
        }

        public Criteria andQxbmGreaterThan(String value) {
            addCriterion("QXBM >", value, "qxbm");
            return (Criteria) this;
        }

        public Criteria andQxbmGreaterThanOrEqualTo(String value) {
            addCriterion("QXBM >=", value, "qxbm");
            return (Criteria) this;
        }

        public Criteria andQxbmLessThan(String value) {
            addCriterion("QXBM <", value, "qxbm");
            return (Criteria) this;
        }

        public Criteria andQxbmLessThanOrEqualTo(String value) {
            addCriterion("QXBM <=", value, "qxbm");
            return (Criteria) this;
        }

        public Criteria andQxbmLike(String value) {
            addCriterion("QXBM like", value, "qxbm");
            return (Criteria) this;
        }

        public Criteria andQxbmNotLike(String value) {
            addCriterion("QXBM not like", value, "qxbm");
            return (Criteria) this;
        }

        public Criteria andQxbmIn(List<String> values) {
            addCriterion("QXBM in", values, "qxbm");
            return (Criteria) this;
        }

        public Criteria andQxbmNotIn(List<String> values) {
            addCriterion("QXBM not in", values, "qxbm");
            return (Criteria) this;
        }

        public Criteria andQxbmBetween(String value1, String value2) {
            addCriterion("QXBM between", value1, value2, "qxbm");
            return (Criteria) this;
        }

        public Criteria andQxbmNotBetween(String value1, String value2) {
            addCriterion("QXBM not between", value1, value2, "qxbm");
            return (Criteria) this;
        }

        public Criteria andZfqxsxIsNull() {
            addCriterion("ZFQXSX is null");
            return (Criteria) this;
        }

        public Criteria andZfqxsxIsNotNull() {
            addCriterion("ZFQXSX is not null");
            return (Criteria) this;
        }

        public Criteria andZfqxsxEqualTo(String value) {
            addCriterion("ZFQXSX =", value, "zfqxsx");
            return (Criteria) this;
        }

        public Criteria andZfqxsxNotEqualTo(String value) {
            addCriterion("ZFQXSX <>", value, "zfqxsx");
            return (Criteria) this;
        }

        public Criteria andZfqxsxGreaterThan(String value) {
            addCriterion("ZFQXSX >", value, "zfqxsx");
            return (Criteria) this;
        }

        public Criteria andZfqxsxGreaterThanOrEqualTo(String value) {
            addCriterion("ZFQXSX >=", value, "zfqxsx");
            return (Criteria) this;
        }

        public Criteria andZfqxsxLessThan(String value) {
            addCriterion("ZFQXSX <", value, "zfqxsx");
            return (Criteria) this;
        }

        public Criteria andZfqxsxLessThanOrEqualTo(String value) {
            addCriterion("ZFQXSX <=", value, "zfqxsx");
            return (Criteria) this;
        }

        public Criteria andZfqxsxLike(String value) {
            addCriterion("ZFQXSX like", value, "zfqxsx");
            return (Criteria) this;
        }

        public Criteria andZfqxsxNotLike(String value) {
            addCriterion("ZFQXSX not like", value, "zfqxsx");
            return (Criteria) this;
        }

        public Criteria andZfqxsxIn(List<String> values) {
            addCriterion("ZFQXSX in", values, "zfqxsx");
            return (Criteria) this;
        }

        public Criteria andZfqxsxNotIn(List<String> values) {
            addCriterion("ZFQXSX not in", values, "zfqxsx");
            return (Criteria) this;
        }

        public Criteria andZfqxsxBetween(String value1, String value2) {
            addCriterion("ZFQXSX between", value1, value2, "zfqxsx");
            return (Criteria) this;
        }

        public Criteria andZfqxsxNotBetween(String value1, String value2) {
            addCriterion("ZFQXSX not between", value1, value2, "zfqxsx");
            return (Criteria) this;
        }

        public Criteria andSgqqIsNull() {
            addCriterion("SGQQ is null");
            return (Criteria) this;
        }

        public Criteria andSgqqIsNotNull() {
            addCriterion("SGQQ is not null");
            return (Criteria) this;
        }

        public Criteria andSgqqEqualTo(Date value) {
            addCriterionForJDBCDate("SGQQ =", value, "sgqq");
            return (Criteria) this;
        }

        public Criteria andSgqqNotEqualTo(Date value) {
            addCriterionForJDBCDate("SGQQ <>", value, "sgqq");
            return (Criteria) this;
        }

        public Criteria andSgqqGreaterThan(Date value) {
            addCriterionForJDBCDate("SGQQ >", value, "sgqq");
            return (Criteria) this;
        }

        public Criteria andSgqqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SGQQ >=", value, "sgqq");
            return (Criteria) this;
        }

        public Criteria andSgqqLessThan(Date value) {
            addCriterionForJDBCDate("SGQQ <", value, "sgqq");
            return (Criteria) this;
        }

        public Criteria andSgqqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SGQQ <=", value, "sgqq");
            return (Criteria) this;
        }

        public Criteria andSgqqIn(List<Date> values) {
            addCriterionForJDBCDate("SGQQ in", values, "sgqq");
            return (Criteria) this;
        }

        public Criteria andSgqqNotIn(List<Date> values) {
            addCriterionForJDBCDate("SGQQ not in", values, "sgqq");
            return (Criteria) this;
        }

        public Criteria andSgqqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SGQQ between", value1, value2, "sgqq");
            return (Criteria) this;
        }

        public Criteria andSgqqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SGQQ not between", value1, value2, "sgqq");
            return (Criteria) this;
        }

        public Criteria andSgqzIsNull() {
            addCriterion("SGQZ is null");
            return (Criteria) this;
        }

        public Criteria andSgqzIsNotNull() {
            addCriterion("SGQZ is not null");
            return (Criteria) this;
        }

        public Criteria andSgqzEqualTo(Date value) {
            addCriterionForJDBCDate("SGQZ =", value, "sgqz");
            return (Criteria) this;
        }

        public Criteria andSgqzNotEqualTo(Date value) {
            addCriterionForJDBCDate("SGQZ <>", value, "sgqz");
            return (Criteria) this;
        }

        public Criteria andSgqzGreaterThan(Date value) {
            addCriterionForJDBCDate("SGQZ >", value, "sgqz");
            return (Criteria) this;
        }

        public Criteria andSgqzGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SGQZ >=", value, "sgqz");
            return (Criteria) this;
        }

        public Criteria andSgqzLessThan(Date value) {
            addCriterionForJDBCDate("SGQZ <", value, "sgqz");
            return (Criteria) this;
        }

        public Criteria andSgqzLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SGQZ <=", value, "sgqz");
            return (Criteria) this;
        }

        public Criteria andSgqzIn(List<Date> values) {
            addCriterionForJDBCDate("SGQZ in", values, "sgqz");
            return (Criteria) this;
        }

        public Criteria andSgqzNotIn(List<Date> values) {
            addCriterionForJDBCDate("SGQZ not in", values, "sgqz");
            return (Criteria) this;
        }

        public Criteria andSgqzBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SGQZ between", value1, value2, "sgqz");
            return (Criteria) this;
        }

        public Criteria andSgqzNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SGQZ not between", value1, value2, "sgqz");
            return (Criteria) this;
        }

        public Criteria andWxqqIsNull() {
            addCriterion("WXQQ is null");
            return (Criteria) this;
        }

        public Criteria andWxqqIsNotNull() {
            addCriterion("WXQQ is not null");
            return (Criteria) this;
        }

        public Criteria andWxqqEqualTo(Date value) {
            addCriterionForJDBCDate("WXQQ =", value, "wxqq");
            return (Criteria) this;
        }

        public Criteria andWxqqNotEqualTo(Date value) {
            addCriterionForJDBCDate("WXQQ <>", value, "wxqq");
            return (Criteria) this;
        }

        public Criteria andWxqqGreaterThan(Date value) {
            addCriterionForJDBCDate("WXQQ >", value, "wxqq");
            return (Criteria) this;
        }

        public Criteria andWxqqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("WXQQ >=", value, "wxqq");
            return (Criteria) this;
        }

        public Criteria andWxqqLessThan(Date value) {
            addCriterionForJDBCDate("WXQQ <", value, "wxqq");
            return (Criteria) this;
        }

        public Criteria andWxqqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("WXQQ <=", value, "wxqq");
            return (Criteria) this;
        }

        public Criteria andWxqqIn(List<Date> values) {
            addCriterionForJDBCDate("WXQQ in", values, "wxqq");
            return (Criteria) this;
        }

        public Criteria andWxqqNotIn(List<Date> values) {
            addCriterionForJDBCDate("WXQQ not in", values, "wxqq");
            return (Criteria) this;
        }

        public Criteria andWxqqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("WXQQ between", value1, value2, "wxqq");
            return (Criteria) this;
        }

        public Criteria andWxqqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("WXQQ not between", value1, value2, "wxqq");
            return (Criteria) this;
        }

        public Criteria andWxqzIsNull() {
            addCriterion("WXQZ is null");
            return (Criteria) this;
        }

        public Criteria andWxqzIsNotNull() {
            addCriterion("WXQZ is not null");
            return (Criteria) this;
        }

        public Criteria andWxqzEqualTo(Date value) {
            addCriterionForJDBCDate("WXQZ =", value, "wxqz");
            return (Criteria) this;
        }

        public Criteria andWxqzNotEqualTo(Date value) {
            addCriterionForJDBCDate("WXQZ <>", value, "wxqz");
            return (Criteria) this;
        }

        public Criteria andWxqzGreaterThan(Date value) {
            addCriterionForJDBCDate("WXQZ >", value, "wxqz");
            return (Criteria) this;
        }

        public Criteria andWxqzGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("WXQZ >=", value, "wxqz");
            return (Criteria) this;
        }

        public Criteria andWxqzLessThan(Date value) {
            addCriterionForJDBCDate("WXQZ <", value, "wxqz");
            return (Criteria) this;
        }

        public Criteria andWxqzLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("WXQZ <=", value, "wxqz");
            return (Criteria) this;
        }

        public Criteria andWxqzIn(List<Date> values) {
            addCriterionForJDBCDate("WXQZ in", values, "wxqz");
            return (Criteria) this;
        }

        public Criteria andWxqzNotIn(List<Date> values) {
            addCriterionForJDBCDate("WXQZ not in", values, "wxqz");
            return (Criteria) this;
        }

        public Criteria andWxqzBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("WXQZ between", value1, value2, "wxqz");
            return (Criteria) this;
        }

        public Criteria andWxqzNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("WXQZ not between", value1, value2, "wxqz");
            return (Criteria) this;
        }

        public Criteria andJgqIsNull() {
            addCriterion("JGQ is null");
            return (Criteria) this;
        }

        public Criteria andJgqIsNotNull() {
            addCriterion("JGQ is not null");
            return (Criteria) this;
        }

        public Criteria andJgqEqualTo(Date value) {
            addCriterionForJDBCDate("JGQ =", value, "jgq");
            return (Criteria) this;
        }

        public Criteria andJgqNotEqualTo(Date value) {
            addCriterionForJDBCDate("JGQ <>", value, "jgq");
            return (Criteria) this;
        }

        public Criteria andJgqGreaterThan(Date value) {
            addCriterionForJDBCDate("JGQ >", value, "jgq");
            return (Criteria) this;
        }

        public Criteria andJgqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("JGQ >=", value, "jgq");
            return (Criteria) this;
        }

        public Criteria andJgqLessThan(Date value) {
            addCriterionForJDBCDate("JGQ <", value, "jgq");
            return (Criteria) this;
        }

        public Criteria andJgqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("JGQ <=", value, "jgq");
            return (Criteria) this;
        }

        public Criteria andJgqIn(List<Date> values) {
            addCriterionForJDBCDate("JGQ in", values, "jgq");
            return (Criteria) this;
        }

        public Criteria andJgqNotIn(List<Date> values) {
            addCriterionForJDBCDate("JGQ not in", values, "jgq");
            return (Criteria) this;
        }

        public Criteria andJgqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("JGQ between", value1, value2, "jgq");
            return (Criteria) this;
        }

        public Criteria andJgqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("JGQ not between", value1, value2, "jgq");
            return (Criteria) this;
        }

        public Criteria andYcqIsNull() {
            addCriterion("YCQ is null");
            return (Criteria) this;
        }

        public Criteria andYcqIsNotNull() {
            addCriterion("YCQ is not null");
            return (Criteria) this;
        }

        public Criteria andYcqEqualTo(Date value) {
            addCriterionForJDBCDate("YCQ =", value, "ycq");
            return (Criteria) this;
        }

        public Criteria andYcqNotEqualTo(Date value) {
            addCriterionForJDBCDate("YCQ <>", value, "ycq");
            return (Criteria) this;
        }

        public Criteria andYcqGreaterThan(Date value) {
            addCriterionForJDBCDate("YCQ >", value, "ycq");
            return (Criteria) this;
        }

        public Criteria andYcqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("YCQ >=", value, "ycq");
            return (Criteria) this;
        }

        public Criteria andYcqLessThan(Date value) {
            addCriterionForJDBCDate("YCQ <", value, "ycq");
            return (Criteria) this;
        }

        public Criteria andYcqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("YCQ <=", value, "ycq");
            return (Criteria) this;
        }

        public Criteria andYcqIn(List<Date> values) {
            addCriterionForJDBCDate("YCQ in", values, "ycq");
            return (Criteria) this;
        }

        public Criteria andYcqNotIn(List<Date> values) {
            addCriterionForJDBCDate("YCQ not in", values, "ycq");
            return (Criteria) this;
        }

        public Criteria andYcqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("YCQ between", value1, value2, "ycq");
            return (Criteria) this;
        }

        public Criteria andYcqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("YCQ not between", value1, value2, "ycq");
            return (Criteria) this;
        }

        public Criteria andJftjIsNull() {
            addCriterion("JFTJ is null");
            return (Criteria) this;
        }

        public Criteria andJftjIsNotNull() {
            addCriterion("JFTJ is not null");
            return (Criteria) this;
        }

        public Criteria andJftjEqualTo(String value) {
            addCriterion("JFTJ =", value, "jftj");
            return (Criteria) this;
        }

        public Criteria andJftjNotEqualTo(String value) {
            addCriterion("JFTJ <>", value, "jftj");
            return (Criteria) this;
        }

        public Criteria andJftjGreaterThan(String value) {
            addCriterion("JFTJ >", value, "jftj");
            return (Criteria) this;
        }

        public Criteria andJftjGreaterThanOrEqualTo(String value) {
            addCriterion("JFTJ >=", value, "jftj");
            return (Criteria) this;
        }

        public Criteria andJftjLessThan(String value) {
            addCriterion("JFTJ <", value, "jftj");
            return (Criteria) this;
        }

        public Criteria andJftjLessThanOrEqualTo(String value) {
            addCriterion("JFTJ <=", value, "jftj");
            return (Criteria) this;
        }

        public Criteria andJftjLike(String value) {
            addCriterion("JFTJ like", value, "jftj");
            return (Criteria) this;
        }

        public Criteria andJftjNotLike(String value) {
            addCriterion("JFTJ not like", value, "jftj");
            return (Criteria) this;
        }

        public Criteria andJftjIn(List<String> values) {
            addCriterion("JFTJ in", values, "jftj");
            return (Criteria) this;
        }

        public Criteria andJftjNotIn(List<String> values) {
            addCriterion("JFTJ not in", values, "jftj");
            return (Criteria) this;
        }

        public Criteria andJftjBetween(String value1, String value2) {
            addCriterion("JFTJ between", value1, value2, "jftj");
            return (Criteria) this;
        }

        public Criteria andJftjNotBetween(String value1, String value2) {
            addCriterion("JFTJ not between", value1, value2, "jftj");
            return (Criteria) this;
        }

        public Criteria andYhjcIsNull() {
            addCriterion("YHJC is null");
            return (Criteria) this;
        }

        public Criteria andYhjcIsNotNull() {
            addCriterion("YHJC is not null");
            return (Criteria) this;
        }

        public Criteria andYhjcEqualTo(String value) {
            addCriterion("YHJC =", value, "yhjc");
            return (Criteria) this;
        }

        public Criteria andYhjcNotEqualTo(String value) {
            addCriterion("YHJC <>", value, "yhjc");
            return (Criteria) this;
        }

        public Criteria andYhjcGreaterThan(String value) {
            addCriterion("YHJC >", value, "yhjc");
            return (Criteria) this;
        }

        public Criteria andYhjcGreaterThanOrEqualTo(String value) {
            addCriterion("YHJC >=", value, "yhjc");
            return (Criteria) this;
        }

        public Criteria andYhjcLessThan(String value) {
            addCriterion("YHJC <", value, "yhjc");
            return (Criteria) this;
        }

        public Criteria andYhjcLessThanOrEqualTo(String value) {
            addCriterion("YHJC <=", value, "yhjc");
            return (Criteria) this;
        }

        public Criteria andYhjcLike(String value) {
            addCriterion("YHJC like", value, "yhjc");
            return (Criteria) this;
        }

        public Criteria andYhjcNotLike(String value) {
            addCriterion("YHJC not like", value, "yhjc");
            return (Criteria) this;
        }

        public Criteria andYhjcIn(List<String> values) {
            addCriterion("YHJC in", values, "yhjc");
            return (Criteria) this;
        }

        public Criteria andYhjcNotIn(List<String> values) {
            addCriterion("YHJC not in", values, "yhjc");
            return (Criteria) this;
        }

        public Criteria andYhjcBetween(String value1, String value2) {
            addCriterion("YHJC between", value1, value2, "yhjc");
            return (Criteria) this;
        }

        public Criteria andYhjcNotBetween(String value1, String value2) {
            addCriterion("YHJC not between", value1, value2, "yhjc");
            return (Criteria) this;
        }

        public Criteria andKhqcIsNull() {
            addCriterion("KHQC is null");
            return (Criteria) this;
        }

        public Criteria andKhqcIsNotNull() {
            addCriterion("KHQC is not null");
            return (Criteria) this;
        }

        public Criteria andKhqcEqualTo(String value) {
            addCriterion("KHQC =", value, "khqc");
            return (Criteria) this;
        }

        public Criteria andKhqcNotEqualTo(String value) {
            addCriterion("KHQC <>", value, "khqc");
            return (Criteria) this;
        }

        public Criteria andKhqcGreaterThan(String value) {
            addCriterion("KHQC >", value, "khqc");
            return (Criteria) this;
        }

        public Criteria andKhqcGreaterThanOrEqualTo(String value) {
            addCriterion("KHQC >=", value, "khqc");
            return (Criteria) this;
        }

        public Criteria andKhqcLessThan(String value) {
            addCriterion("KHQC <", value, "khqc");
            return (Criteria) this;
        }

        public Criteria andKhqcLessThanOrEqualTo(String value) {
            addCriterion("KHQC <=", value, "khqc");
            return (Criteria) this;
        }

        public Criteria andKhqcLike(String value) {
            addCriterion("KHQC like", value, "khqc");
            return (Criteria) this;
        }

        public Criteria andKhqcNotLike(String value) {
            addCriterion("KHQC not like", value, "khqc");
            return (Criteria) this;
        }

        public Criteria andKhqcIn(List<String> values) {
            addCriterion("KHQC in", values, "khqc");
            return (Criteria) this;
        }

        public Criteria andKhqcNotIn(List<String> values) {
            addCriterion("KHQC not in", values, "khqc");
            return (Criteria) this;
        }

        public Criteria andKhqcBetween(String value1, String value2) {
            addCriterion("KHQC between", value1, value2, "khqc");
            return (Criteria) this;
        }

        public Criteria andKhqcNotBetween(String value1, String value2) {
            addCriterion("KHQC not between", value1, value2, "khqc");
            return (Criteria) this;
        }

        public Criteria andKhzhIsNull() {
            addCriterion("KHZH is null");
            return (Criteria) this;
        }

        public Criteria andKhzhIsNotNull() {
            addCriterion("KHZH is not null");
            return (Criteria) this;
        }

        public Criteria andKhzhEqualTo(String value) {
            addCriterion("KHZH =", value, "khzh");
            return (Criteria) this;
        }

        public Criteria andKhzhNotEqualTo(String value) {
            addCriterion("KHZH <>", value, "khzh");
            return (Criteria) this;
        }

        public Criteria andKhzhGreaterThan(String value) {
            addCriterion("KHZH >", value, "khzh");
            return (Criteria) this;
        }

        public Criteria andKhzhGreaterThanOrEqualTo(String value) {
            addCriterion("KHZH >=", value, "khzh");
            return (Criteria) this;
        }

        public Criteria andKhzhLessThan(String value) {
            addCriterion("KHZH <", value, "khzh");
            return (Criteria) this;
        }

        public Criteria andKhzhLessThanOrEqualTo(String value) {
            addCriterion("KHZH <=", value, "khzh");
            return (Criteria) this;
        }

        public Criteria andKhzhLike(String value) {
            addCriterion("KHZH like", value, "khzh");
            return (Criteria) this;
        }

        public Criteria andKhzhNotLike(String value) {
            addCriterion("KHZH not like", value, "khzh");
            return (Criteria) this;
        }

        public Criteria andKhzhIn(List<String> values) {
            addCriterion("KHZH in", values, "khzh");
            return (Criteria) this;
        }

        public Criteria andKhzhNotIn(List<String> values) {
            addCriterion("KHZH not in", values, "khzh");
            return (Criteria) this;
        }

        public Criteria andKhzhBetween(String value1, String value2) {
            addCriterion("KHZH between", value1, value2, "khzh");
            return (Criteria) this;
        }

        public Criteria andKhzhNotBetween(String value1, String value2) {
            addCriterion("KHZH not between", value1, value2, "khzh");
            return (Criteria) this;
        }

        public Criteria andYcqzIsNull() {
            addCriterion("YCQZ is null");
            return (Criteria) this;
        }

        public Criteria andYcqzIsNotNull() {
            addCriterion("YCQZ is not null");
            return (Criteria) this;
        }

        public Criteria andYcqzEqualTo(Date value) {
            addCriterionForJDBCDate("YCQZ =", value, "ycqz");
            return (Criteria) this;
        }

        public Criteria andYcqzNotEqualTo(Date value) {
            addCriterionForJDBCDate("YCQZ <>", value, "ycqz");
            return (Criteria) this;
        }

        public Criteria andYcqzGreaterThan(Date value) {
            addCriterionForJDBCDate("YCQZ >", value, "ycqz");
            return (Criteria) this;
        }

        public Criteria andYcqzGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("YCQZ >=", value, "ycqz");
            return (Criteria) this;
        }

        public Criteria andYcqzLessThan(Date value) {
            addCriterionForJDBCDate("YCQZ <", value, "ycqz");
            return (Criteria) this;
        }

        public Criteria andYcqzLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("YCQZ <=", value, "ycqz");
            return (Criteria) this;
        }

        public Criteria andYcqzIn(List<Date> values) {
            addCriterionForJDBCDate("YCQZ in", values, "ycqz");
            return (Criteria) this;
        }

        public Criteria andYcqzNotIn(List<Date> values) {
            addCriterionForJDBCDate("YCQZ not in", values, "ycqz");
            return (Criteria) this;
        }

        public Criteria andYcqzBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("YCQZ between", value1, value2, "ycqz");
            return (Criteria) this;
        }

        public Criteria andYcqzNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("YCQZ not between", value1, value2, "ycqz");
            return (Criteria) this;
        }

        public Criteria andKhhhIsNull() {
            addCriterion("KHHH is null");
            return (Criteria) this;
        }

        public Criteria andKhhhIsNotNull() {
            addCriterion("KHHH is not null");
            return (Criteria) this;
        }

        public Criteria andKhhhEqualTo(String value) {
            addCriterion("KHHH =", value, "khhh");
            return (Criteria) this;
        }

        public Criteria andKhhhNotEqualTo(String value) {
            addCriterion("KHHH <>", value, "khhh");
            return (Criteria) this;
        }

        public Criteria andKhhhGreaterThan(String value) {
            addCriterion("KHHH >", value, "khhh");
            return (Criteria) this;
        }

        public Criteria andKhhhGreaterThanOrEqualTo(String value) {
            addCriterion("KHHH >=", value, "khhh");
            return (Criteria) this;
        }

        public Criteria andKhhhLessThan(String value) {
            addCriterion("KHHH <", value, "khhh");
            return (Criteria) this;
        }

        public Criteria andKhhhLessThanOrEqualTo(String value) {
            addCriterion("KHHH <=", value, "khhh");
            return (Criteria) this;
        }

        public Criteria andKhhhLike(String value) {
            addCriterion("KHHH like", value, "khhh");
            return (Criteria) this;
        }

        public Criteria andKhhhNotLike(String value) {
            addCriterion("KHHH not like", value, "khhh");
            return (Criteria) this;
        }

        public Criteria andKhhhIn(List<String> values) {
            addCriterion("KHHH in", values, "khhh");
            return (Criteria) this;
        }

        public Criteria andKhhhNotIn(List<String> values) {
            addCriterion("KHHH not in", values, "khhh");
            return (Criteria) this;
        }

        public Criteria andKhhhBetween(String value1, String value2) {
            addCriterion("KHHH between", value1, value2, "khhh");
            return (Criteria) this;
        }

        public Criteria andKhhhNotBetween(String value1, String value2) {
            addCriterion("KHHH not between", value1, value2, "khhh");
            return (Criteria) this;
        }

        public Criteria andJgsybsIsNull() {
            addCriterion("JGSYBS is null");
            return (Criteria) this;
        }

        public Criteria andJgsybsIsNotNull() {
            addCriterion("JGSYBS is not null");
            return (Criteria) this;
        }

        public Criteria andJgsybsEqualTo(String value) {
            addCriterion("JGSYBS =", value, "jgsybs");
            return (Criteria) this;
        }

        public Criteria andJgsybsNotEqualTo(String value) {
            addCriterion("JGSYBS <>", value, "jgsybs");
            return (Criteria) this;
        }

        public Criteria andJgsybsGreaterThan(String value) {
            addCriterion("JGSYBS >", value, "jgsybs");
            return (Criteria) this;
        }

        public Criteria andJgsybsGreaterThanOrEqualTo(String value) {
            addCriterion("JGSYBS >=", value, "jgsybs");
            return (Criteria) this;
        }

        public Criteria andJgsybsLessThan(String value) {
            addCriterion("JGSYBS <", value, "jgsybs");
            return (Criteria) this;
        }

        public Criteria andJgsybsLessThanOrEqualTo(String value) {
            addCriterion("JGSYBS <=", value, "jgsybs");
            return (Criteria) this;
        }

        public Criteria andJgsybsLike(String value) {
            addCriterion("JGSYBS like", value, "jgsybs");
            return (Criteria) this;
        }

        public Criteria andJgsybsNotLike(String value) {
            addCriterion("JGSYBS not like", value, "jgsybs");
            return (Criteria) this;
        }

        public Criteria andJgsybsIn(List<String> values) {
            addCriterion("JGSYBS in", values, "jgsybs");
            return (Criteria) this;
        }

        public Criteria andJgsybsNotIn(List<String> values) {
            addCriterion("JGSYBS not in", values, "jgsybs");
            return (Criteria) this;
        }

        public Criteria andJgsybsBetween(String value1, String value2) {
            addCriterion("JGSYBS between", value1, value2, "jgsybs");
            return (Criteria) this;
        }

        public Criteria andJgsybsNotBetween(String value1, String value2) {
            addCriterion("JGSYBS not between", value1, value2, "jgsybs");
            return (Criteria) this;
        }

        public Criteria andJflyIsNull() {
            addCriterion("JFLY is null");
            return (Criteria) this;
        }

        public Criteria andJflyIsNotNull() {
            addCriterion("JFLY is not null");
            return (Criteria) this;
        }

        public Criteria andJflyEqualTo(String value) {
            addCriterion("JFLY =", value, "jfly");
            return (Criteria) this;
        }

        public Criteria andJflyNotEqualTo(String value) {
            addCriterion("JFLY <>", value, "jfly");
            return (Criteria) this;
        }

        public Criteria andJflyGreaterThan(String value) {
            addCriterion("JFLY >", value, "jfly");
            return (Criteria) this;
        }

        public Criteria andJflyGreaterThanOrEqualTo(String value) {
            addCriterion("JFLY >=", value, "jfly");
            return (Criteria) this;
        }

        public Criteria andJflyLessThan(String value) {
            addCriterion("JFLY <", value, "jfly");
            return (Criteria) this;
        }

        public Criteria andJflyLessThanOrEqualTo(String value) {
            addCriterion("JFLY <=", value, "jfly");
            return (Criteria) this;
        }

        public Criteria andJflyLike(String value) {
            addCriterion("JFLY like", value, "jfly");
            return (Criteria) this;
        }

        public Criteria andJflyNotLike(String value) {
            addCriterion("JFLY not like", value, "jfly");
            return (Criteria) this;
        }

        public Criteria andJflyIn(List<String> values) {
            addCriterion("JFLY in", values, "jfly");
            return (Criteria) this;
        }

        public Criteria andJflyNotIn(List<String> values) {
            addCriterion("JFLY not in", values, "jfly");
            return (Criteria) this;
        }

        public Criteria andJflyBetween(String value1, String value2) {
            addCriterion("JFLY between", value1, value2, "jfly");
            return (Criteria) this;
        }

        public Criteria andJflyNotBetween(String value1, String value2) {
            addCriterion("JFLY not between", value1, value2, "jfly");
            return (Criteria) this;
        }

        public Criteria andJgbzrsIsNull() {
            addCriterion("JGBZRS is null");
            return (Criteria) this;
        }

        public Criteria andJgbzrsIsNotNull() {
            addCriterion("JGBZRS is not null");
            return (Criteria) this;
        }

        public Criteria andJgbzrsEqualTo(Long value) {
            addCriterion("JGBZRS =", value, "jgbzrs");
            return (Criteria) this;
        }

        public Criteria andJgbzrsNotEqualTo(Long value) {
            addCriterion("JGBZRS <>", value, "jgbzrs");
            return (Criteria) this;
        }

        public Criteria andJgbzrsGreaterThan(Long value) {
            addCriterion("JGBZRS >", value, "jgbzrs");
            return (Criteria) this;
        }

        public Criteria andJgbzrsGreaterThanOrEqualTo(Long value) {
            addCriterion("JGBZRS >=", value, "jgbzrs");
            return (Criteria) this;
        }

        public Criteria andJgbzrsLessThan(Long value) {
            addCriterion("JGBZRS <", value, "jgbzrs");
            return (Criteria) this;
        }

        public Criteria andJgbzrsLessThanOrEqualTo(Long value) {
            addCriterion("JGBZRS <=", value, "jgbzrs");
            return (Criteria) this;
        }

        public Criteria andJgbzrsIn(List<Long> values) {
            addCriterion("JGBZRS in", values, "jgbzrs");
            return (Criteria) this;
        }

        public Criteria andJgbzrsNotIn(List<Long> values) {
            addCriterion("JGBZRS not in", values, "jgbzrs");
            return (Criteria) this;
        }

        public Criteria andJgbzrsBetween(Long value1, Long value2) {
            addCriterion("JGBZRS between", value1, value2, "jgbzrs");
            return (Criteria) this;
        }

        public Criteria andJgbzrsNotBetween(Long value1, Long value2) {
            addCriterion("JGBZRS not between", value1, value2, "jgbzrs");
            return (Criteria) this;
        }

        public Criteria andJfzhlxIsNull() {
            addCriterion("JFZHLX is null");
            return (Criteria) this;
        }

        public Criteria andJfzhlxIsNotNull() {
            addCriterion("JFZHLX is not null");
            return (Criteria) this;
        }

        public Criteria andJfzhlxEqualTo(String value) {
            addCriterion("JFZHLX =", value, "jfzhlx");
            return (Criteria) this;
        }

        public Criteria andJfzhlxNotEqualTo(String value) {
            addCriterion("JFZHLX <>", value, "jfzhlx");
            return (Criteria) this;
        }

        public Criteria andJfzhlxGreaterThan(String value) {
            addCriterion("JFZHLX >", value, "jfzhlx");
            return (Criteria) this;
        }

        public Criteria andJfzhlxGreaterThanOrEqualTo(String value) {
            addCriterion("JFZHLX >=", value, "jfzhlx");
            return (Criteria) this;
        }

        public Criteria andJfzhlxLessThan(String value) {
            addCriterion("JFZHLX <", value, "jfzhlx");
            return (Criteria) this;
        }

        public Criteria andJfzhlxLessThanOrEqualTo(String value) {
            addCriterion("JFZHLX <=", value, "jfzhlx");
            return (Criteria) this;
        }

        public Criteria andJfzhlxLike(String value) {
            addCriterion("JFZHLX like", value, "jfzhlx");
            return (Criteria) this;
        }

        public Criteria andJfzhlxNotLike(String value) {
            addCriterion("JFZHLX not like", value, "jfzhlx");
            return (Criteria) this;
        }

        public Criteria andJfzhlxIn(List<String> values) {
            addCriterion("JFZHLX in", values, "jfzhlx");
            return (Criteria) this;
        }

        public Criteria andJfzhlxNotIn(List<String> values) {
            addCriterion("JFZHLX not in", values, "jfzhlx");
            return (Criteria) this;
        }

        public Criteria andJfzhlxBetween(String value1, String value2) {
            addCriterion("JFZHLX between", value1, value2, "jfzhlx");
            return (Criteria) this;
        }

        public Criteria andJfzhlxNotBetween(String value1, String value2) {
            addCriterion("JFZHLX not between", value1, value2, "jfzhlx");
            return (Criteria) this;
        }

        public Criteria andJgsydwlbIsNull() {
            addCriterion("JGSYDWLB is null");
            return (Criteria) this;
        }

        public Criteria andJgsydwlbIsNotNull() {
            addCriterion("JGSYDWLB is not null");
            return (Criteria) this;
        }

        public Criteria andJgsydwlbEqualTo(String value) {
            addCriterion("JGSYDWLB =", value, "jgsydwlb");
            return (Criteria) this;
        }

        public Criteria andJgsydwlbNotEqualTo(String value) {
            addCriterion("JGSYDWLB <>", value, "jgsydwlb");
            return (Criteria) this;
        }

        public Criteria andJgsydwlbGreaterThan(String value) {
            addCriterion("JGSYDWLB >", value, "jgsydwlb");
            return (Criteria) this;
        }

        public Criteria andJgsydwlbGreaterThanOrEqualTo(String value) {
            addCriterion("JGSYDWLB >=", value, "jgsydwlb");
            return (Criteria) this;
        }

        public Criteria andJgsydwlbLessThan(String value) {
            addCriterion("JGSYDWLB <", value, "jgsydwlb");
            return (Criteria) this;
        }

        public Criteria andJgsydwlbLessThanOrEqualTo(String value) {
            addCriterion("JGSYDWLB <=", value, "jgsydwlb");
            return (Criteria) this;
        }

        public Criteria andJgsydwlbLike(String value) {
            addCriterion("JGSYDWLB like", value, "jgsydwlb");
            return (Criteria) this;
        }

        public Criteria andJgsydwlbNotLike(String value) {
            addCriterion("JGSYDWLB not like", value, "jgsydwlb");
            return (Criteria) this;
        }

        public Criteria andJgsydwlbIn(List<String> values) {
            addCriterion("JGSYDWLB in", values, "jgsydwlb");
            return (Criteria) this;
        }

        public Criteria andJgsydwlbNotIn(List<String> values) {
            addCriterion("JGSYDWLB not in", values, "jgsydwlb");
            return (Criteria) this;
        }

        public Criteria andJgsydwlbBetween(String value1, String value2) {
            addCriterion("JGSYDWLB between", value1, value2, "jgsydwlb");
            return (Criteria) this;
        }

        public Criteria andJgsydwlbNotBetween(String value1, String value2) {
            addCriterion("JGSYDWLB not between", value1, value2, "jgsydwlb");
            return (Criteria) this;
        }

        public Criteria andQfyhhhIsNull() {
            addCriterion("QFYHHH is null");
            return (Criteria) this;
        }

        public Criteria andQfyhhhIsNotNull() {
            addCriterion("QFYHHH is not null");
            return (Criteria) this;
        }

        public Criteria andQfyhhhEqualTo(String value) {
            addCriterion("QFYHHH =", value, "qfyhhh");
            return (Criteria) this;
        }

        public Criteria andQfyhhhNotEqualTo(String value) {
            addCriterion("QFYHHH <>", value, "qfyhhh");
            return (Criteria) this;
        }

        public Criteria andQfyhhhGreaterThan(String value) {
            addCriterion("QFYHHH >", value, "qfyhhh");
            return (Criteria) this;
        }

        public Criteria andQfyhhhGreaterThanOrEqualTo(String value) {
            addCriterion("QFYHHH >=", value, "qfyhhh");
            return (Criteria) this;
        }

        public Criteria andQfyhhhLessThan(String value) {
            addCriterion("QFYHHH <", value, "qfyhhh");
            return (Criteria) this;
        }

        public Criteria andQfyhhhLessThanOrEqualTo(String value) {
            addCriterion("QFYHHH <=", value, "qfyhhh");
            return (Criteria) this;
        }

        public Criteria andQfyhhhLike(String value) {
            addCriterion("QFYHHH like", value, "qfyhhh");
            return (Criteria) this;
        }

        public Criteria andQfyhhhNotLike(String value) {
            addCriterion("QFYHHH not like", value, "qfyhhh");
            return (Criteria) this;
        }

        public Criteria andQfyhhhIn(List<String> values) {
            addCriterion("QFYHHH in", values, "qfyhhh");
            return (Criteria) this;
        }

        public Criteria andQfyhhhNotIn(List<String> values) {
            addCriterion("QFYHHH not in", values, "qfyhhh");
            return (Criteria) this;
        }

        public Criteria andQfyhhhBetween(String value1, String value2) {
            addCriterion("QFYHHH between", value1, value2, "qfyhhh");
            return (Criteria) this;
        }

        public Criteria andQfyhhhNotBetween(String value1, String value2) {
            addCriterion("QFYHHH not between", value1, value2, "qfyhhh");
            return (Criteria) this;
        }

        public Criteria andSfshhIsNull() {
            addCriterion("SFSHH is null");
            return (Criteria) this;
        }

        public Criteria andSfshhIsNotNull() {
            addCriterion("SFSHH is not null");
            return (Criteria) this;
        }

        public Criteria andSfshhEqualTo(String value) {
            addCriterion("SFSHH =", value, "sfshh");
            return (Criteria) this;
        }

        public Criteria andSfshhNotEqualTo(String value) {
            addCriterion("SFSHH <>", value, "sfshh");
            return (Criteria) this;
        }

        public Criteria andSfshhGreaterThan(String value) {
            addCriterion("SFSHH >", value, "sfshh");
            return (Criteria) this;
        }

        public Criteria andSfshhGreaterThanOrEqualTo(String value) {
            addCriterion("SFSHH >=", value, "sfshh");
            return (Criteria) this;
        }

        public Criteria andSfshhLessThan(String value) {
            addCriterion("SFSHH <", value, "sfshh");
            return (Criteria) this;
        }

        public Criteria andSfshhLessThanOrEqualTo(String value) {
            addCriterion("SFSHH <=", value, "sfshh");
            return (Criteria) this;
        }

        public Criteria andSfshhLike(String value) {
            addCriterion("SFSHH like", value, "sfshh");
            return (Criteria) this;
        }

        public Criteria andSfshhNotLike(String value) {
            addCriterion("SFSHH not like", value, "sfshh");
            return (Criteria) this;
        }

        public Criteria andSfshhIn(List<String> values) {
            addCriterion("SFSHH in", values, "sfshh");
            return (Criteria) this;
        }

        public Criteria andSfshhNotIn(List<String> values) {
            addCriterion("SFSHH not in", values, "sfshh");
            return (Criteria) this;
        }

        public Criteria andSfshhBetween(String value1, String value2) {
            addCriterion("SFSHH between", value1, value2, "sfshh");
            return (Criteria) this;
        }

        public Criteria andSfshhNotBetween(String value1, String value2) {
            addCriterion("SFSHH not between", value1, value2, "sfshh");
            return (Criteria) this;
        }

        public Criteria andDwszjbrIsNull() {
            addCriterion("DWSZJBR is null");
            return (Criteria) this;
        }

        public Criteria andDwszjbrIsNotNull() {
            addCriterion("DWSZJBR is not null");
            return (Criteria) this;
        }

        public Criteria andDwszjbrEqualTo(String value) {
            addCriterion("DWSZJBR =", value, "dwszjbr");
            return (Criteria) this;
        }

        public Criteria andDwszjbrNotEqualTo(String value) {
            addCriterion("DWSZJBR <>", value, "dwszjbr");
            return (Criteria) this;
        }

        public Criteria andDwszjbrGreaterThan(String value) {
            addCriterion("DWSZJBR >", value, "dwszjbr");
            return (Criteria) this;
        }

        public Criteria andDwszjbrGreaterThanOrEqualTo(String value) {
            addCriterion("DWSZJBR >=", value, "dwszjbr");
            return (Criteria) this;
        }

        public Criteria andDwszjbrLessThan(String value) {
            addCriterion("DWSZJBR <", value, "dwszjbr");
            return (Criteria) this;
        }

        public Criteria andDwszjbrLessThanOrEqualTo(String value) {
            addCriterion("DWSZJBR <=", value, "dwszjbr");
            return (Criteria) this;
        }

        public Criteria andDwszjbrLike(String value) {
            addCriterion("DWSZJBR like", value, "dwszjbr");
            return (Criteria) this;
        }

        public Criteria andDwszjbrNotLike(String value) {
            addCriterion("DWSZJBR not like", value, "dwszjbr");
            return (Criteria) this;
        }

        public Criteria andDwszjbrIn(List<String> values) {
            addCriterion("DWSZJBR in", values, "dwszjbr");
            return (Criteria) this;
        }

        public Criteria andDwszjbrNotIn(List<String> values) {
            addCriterion("DWSZJBR not in", values, "dwszjbr");
            return (Criteria) this;
        }

        public Criteria andDwszjbrBetween(String value1, String value2) {
            addCriterion("DWSZJBR between", value1, value2, "dwszjbr");
            return (Criteria) this;
        }

        public Criteria andDwszjbrNotBetween(String value1, String value2) {
            addCriterion("DWSZJBR not between", value1, value2, "dwszjbr");
            return (Criteria) this;
        }

        public Criteria andJcrIsNull() {
            addCriterion("JCR is null");
            return (Criteria) this;
        }

        public Criteria andJcrIsNotNull() {
            addCriterion("JCR is not null");
            return (Criteria) this;
        }

        public Criteria andJcrEqualTo(String value) {
            addCriterion("JCR =", value, "jcr");
            return (Criteria) this;
        }

        public Criteria andJcrNotEqualTo(String value) {
            addCriterion("JCR <>", value, "jcr");
            return (Criteria) this;
        }

        public Criteria andJcrGreaterThan(String value) {
            addCriterion("JCR >", value, "jcr");
            return (Criteria) this;
        }

        public Criteria andJcrGreaterThanOrEqualTo(String value) {
            addCriterion("JCR >=", value, "jcr");
            return (Criteria) this;
        }

        public Criteria andJcrLessThan(String value) {
            addCriterion("JCR <", value, "jcr");
            return (Criteria) this;
        }

        public Criteria andJcrLessThanOrEqualTo(String value) {
            addCriterion("JCR <=", value, "jcr");
            return (Criteria) this;
        }

        public Criteria andJcrLike(String value) {
            addCriterion("JCR like", value, "jcr");
            return (Criteria) this;
        }

        public Criteria andJcrNotLike(String value) {
            addCriterion("JCR not like", value, "jcr");
            return (Criteria) this;
        }

        public Criteria andJcrIn(List<String> values) {
            addCriterion("JCR in", values, "jcr");
            return (Criteria) this;
        }

        public Criteria andJcrNotIn(List<String> values) {
            addCriterion("JCR not in", values, "jcr");
            return (Criteria) this;
        }

        public Criteria andJcrBetween(String value1, String value2) {
            addCriterion("JCR between", value1, value2, "jcr");
            return (Criteria) this;
        }

        public Criteria andJcrNotBetween(String value1, String value2) {
            addCriterion("JCR not between", value1, value2, "jcr");
            return (Criteria) this;
        }

        public Criteria andJcrqIsNull() {
            addCriterion("JCRQ is null");
            return (Criteria) this;
        }

        public Criteria andJcrqIsNotNull() {
            addCriterion("JCRQ is not null");
            return (Criteria) this;
        }

        public Criteria andJcrqEqualTo(Date value) {
            addCriterionForJDBCDate("JCRQ =", value, "jcrq");
            return (Criteria) this;
        }

        public Criteria andJcrqNotEqualTo(Date value) {
            addCriterionForJDBCDate("JCRQ <>", value, "jcrq");
            return (Criteria) this;
        }

        public Criteria andJcrqGreaterThan(Date value) {
            addCriterionForJDBCDate("JCRQ >", value, "jcrq");
            return (Criteria) this;
        }

        public Criteria andJcrqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("JCRQ >=", value, "jcrq");
            return (Criteria) this;
        }

        public Criteria andJcrqLessThan(Date value) {
            addCriterionForJDBCDate("JCRQ <", value, "jcrq");
            return (Criteria) this;
        }

        public Criteria andJcrqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("JCRQ <=", value, "jcrq");
            return (Criteria) this;
        }

        public Criteria andJcrqIn(List<Date> values) {
            addCriterionForJDBCDate("JCRQ in", values, "jcrq");
            return (Criteria) this;
        }

        public Criteria andJcrqNotIn(List<Date> values) {
            addCriterionForJDBCDate("JCRQ not in", values, "jcrq");
            return (Criteria) this;
        }

        public Criteria andJcrqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("JCRQ between", value1, value2, "jcrq");
            return (Criteria) this;
        }

        public Criteria andJcrqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("JCRQ not between", value1, value2, "jcrq");
            return (Criteria) this;
        }

        public Criteria andJcIsNull() {
            addCriterion("JC is null");
            return (Criteria) this;
        }

        public Criteria andJcIsNotNull() {
            addCriterion("JC is not null");
            return (Criteria) this;
        }

        public Criteria andJcEqualTo(String value) {
            addCriterion("JC =", value, "jc");
            return (Criteria) this;
        }

        public Criteria andJcNotEqualTo(String value) {
            addCriterion("JC <>", value, "jc");
            return (Criteria) this;
        }

        public Criteria andJcGreaterThan(String value) {
            addCriterion("JC >", value, "jc");
            return (Criteria) this;
        }

        public Criteria andJcGreaterThanOrEqualTo(String value) {
            addCriterion("JC >=", value, "jc");
            return (Criteria) this;
        }

        public Criteria andJcLessThan(String value) {
            addCriterion("JC <", value, "jc");
            return (Criteria) this;
        }

        public Criteria andJcLessThanOrEqualTo(String value) {
            addCriterion("JC <=", value, "jc");
            return (Criteria) this;
        }

        public Criteria andJcLike(String value) {
            addCriterion("JC like", value, "jc");
            return (Criteria) this;
        }

        public Criteria andJcNotLike(String value) {
            addCriterion("JC not like", value, "jc");
            return (Criteria) this;
        }

        public Criteria andJcIn(List<String> values) {
            addCriterion("JC in", values, "jc");
            return (Criteria) this;
        }

        public Criteria andJcNotIn(List<String> values) {
            addCriterion("JC not in", values, "jc");
            return (Criteria) this;
        }

        public Criteria andJcBetween(String value1, String value2) {
            addCriterion("JC between", value1, value2, "jc");
            return (Criteria) this;
        }

        public Criteria andJcNotBetween(String value1, String value2) {
            addCriterion("JC not between", value1, value2, "jc");
            return (Criteria) this;
        }

        public Criteria andCxjcIsNull() {
            addCriterion("CXJC is null");
            return (Criteria) this;
        }

        public Criteria andCxjcIsNotNull() {
            addCriterion("CXJC is not null");
            return (Criteria) this;
        }

        public Criteria andCxjcEqualTo(String value) {
            addCriterion("CXJC =", value, "cxjc");
            return (Criteria) this;
        }

        public Criteria andCxjcNotEqualTo(String value) {
            addCriterion("CXJC <>", value, "cxjc");
            return (Criteria) this;
        }

        public Criteria andCxjcGreaterThan(String value) {
            addCriterion("CXJC >", value, "cxjc");
            return (Criteria) this;
        }

        public Criteria andCxjcGreaterThanOrEqualTo(String value) {
            addCriterion("CXJC >=", value, "cxjc");
            return (Criteria) this;
        }

        public Criteria andCxjcLessThan(String value) {
            addCriterion("CXJC <", value, "cxjc");
            return (Criteria) this;
        }

        public Criteria andCxjcLessThanOrEqualTo(String value) {
            addCriterion("CXJC <=", value, "cxjc");
            return (Criteria) this;
        }

        public Criteria andCxjcLike(String value) {
            addCriterion("CXJC like", value, "cxjc");
            return (Criteria) this;
        }

        public Criteria andCxjcNotLike(String value) {
            addCriterion("CXJC not like", value, "cxjc");
            return (Criteria) this;
        }

        public Criteria andCxjcIn(List<String> values) {
            addCriterion("CXJC in", values, "cxjc");
            return (Criteria) this;
        }

        public Criteria andCxjcNotIn(List<String> values) {
            addCriterion("CXJC not in", values, "cxjc");
            return (Criteria) this;
        }

        public Criteria andCxjcBetween(String value1, String value2) {
            addCriterion("CXJC between", value1, value2, "cxjc");
            return (Criteria) this;
        }

        public Criteria andCxjcNotBetween(String value1, String value2) {
            addCriterion("CXJC not between", value1, value2, "cxjc");
            return (Criteria) this;
        }

        public Criteria andJcjgIsNull() {
            addCriterion("JCJG is null");
            return (Criteria) this;
        }

        public Criteria andJcjgIsNotNull() {
            addCriterion("JCJG is not null");
            return (Criteria) this;
        }

        public Criteria andJcjgEqualTo(String value) {
            addCriterion("JCJG =", value, "jcjg");
            return (Criteria) this;
        }

        public Criteria andJcjgNotEqualTo(String value) {
            addCriterion("JCJG <>", value, "jcjg");
            return (Criteria) this;
        }

        public Criteria andJcjgGreaterThan(String value) {
            addCriterion("JCJG >", value, "jcjg");
            return (Criteria) this;
        }

        public Criteria andJcjgGreaterThanOrEqualTo(String value) {
            addCriterion("JCJG >=", value, "jcjg");
            return (Criteria) this;
        }

        public Criteria andJcjgLessThan(String value) {
            addCriterion("JCJG <", value, "jcjg");
            return (Criteria) this;
        }

        public Criteria andJcjgLessThanOrEqualTo(String value) {
            addCriterion("JCJG <=", value, "jcjg");
            return (Criteria) this;
        }

        public Criteria andJcjgLike(String value) {
            addCriterion("JCJG like", value, "jcjg");
            return (Criteria) this;
        }

        public Criteria andJcjgNotLike(String value) {
            addCriterion("JCJG not like", value, "jcjg");
            return (Criteria) this;
        }

        public Criteria andJcjgIn(List<String> values) {
            addCriterion("JCJG in", values, "jcjg");
            return (Criteria) this;
        }

        public Criteria andJcjgNotIn(List<String> values) {
            addCriterion("JCJG not in", values, "jcjg");
            return (Criteria) this;
        }

        public Criteria andJcjgBetween(String value1, String value2) {
            addCriterion("JCJG between", value1, value2, "jcjg");
            return (Criteria) this;
        }

        public Criteria andJcjgNotBetween(String value1, String value2) {
            addCriterion("JCJG not between", value1, value2, "jcjg");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("MEMO is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("MEMO =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("MEMO <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("MEMO >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("MEMO >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("MEMO <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("MEMO <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("MEMO like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("MEMO not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("MEMO in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("MEMO not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("MEMO between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("MEMO not between", value1, value2, "memo");
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