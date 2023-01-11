package com.uniyaz.common;

import org.hibernate.Criteria;

import java.io.Serializable;

public abstract class BaseCriteriaFilterDto implements Serializable {

    public abstract void addFilters(Criteria criteria);
}