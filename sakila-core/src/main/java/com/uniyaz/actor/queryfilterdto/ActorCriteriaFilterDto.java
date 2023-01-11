package com.uniyaz.actor.queryfilterdto;

import com.uniyaz.common.BaseCriteriaFilterDto;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class ActorCriteriaFilterDto extends BaseCriteriaFilterDto {

    private Long id;
    private String firstName;

    @Override
    public void addFilters(Criteria criteria) {
        if (id != null) criteria.add(Restrictions.eq("id", id));
        if (firstName != null) criteria.add(Restrictions.eq("firstName", firstName));
    }

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
}