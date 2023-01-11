package com.uniyaz.actor.queryfilterdto;

import com.uniyaz.common.BaseCriteriaFilterDto;
import com.uniyaz.common.query.SakilaQueryable;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class ActorQueryFilterDto implements SakilaQueryable {

    private Long id;
    private String firstName;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String buildQuery() {
        String hql =
                "Select     actor " +
                "From       Actor actor " +
                "Where      1=1 ";

        if (id != null) hql += " and actor.id = " + id;
        if (firstName != null) hql += " and actor.firstName = '" + firstName + "'";

        return hql;
    }
}