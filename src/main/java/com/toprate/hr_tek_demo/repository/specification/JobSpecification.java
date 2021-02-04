package com.toprate.hr_tek_demo.repository.specification;

import com.toprate.hr_tek_demo.model.*;
import com.toprate.hr_tek_demo.repository.specification.base.BaseQuerySpecification;
import com.toprate.hr_tek_demo.repository.specification.base.Filter;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: namnv
 * Date: 15:45 29/01/2021
 */
@Component
public class JobSpecification extends BaseQuerySpecification<JobRequirements> {

    public Specification<JobRequirements> searchFilter(String keyword, String level, Float yearExperience, List<Integer> ids) {
        if (keyword == null || StringUtils.EMPTY.equals(keyword) && ids.size() == 0 && yearExperience == null && level == null || StringUtils.EMPTY.equals(level)) {
            return null;
        }
        return super.initWhere().and(findByLevel(level)).and(findByYearTitle(keyword)).and(findByYearExperience(yearExperience)).and(findBySkill(ids));
    }

    private Specification<JobRequirements> findByYearTitle(String value) {
        if (value == null || StringUtils.EMPTY.equals(value)) {
            return null;
        }
        return super.likeSpecification(JobRequirements_.JOB_TITLE, value);
    }

    private Specification<JobRequirements> findByYearExperience(Float value) {
        if (value == null || value == 0f) {
            return null;
        }
        return super.equalsSpecification(JobRequirements_.YEAR_EXPERIENCE, value);
    }

    private Specification<JobRequirements> findByLevel(String name) {
        if (name == null || StringUtils.EMPTY.equals(name)) {
            return null;
        }
        return super.equalsSpecification(JobRequirements_.LEVEL, name);
    }

    private Specification<JobRequirements> findBySkill(List<Integer> ids) {
        if (ids.size() == 0)
            return null;
        Filter<Integer> integerFilter = new Filter<>();
        integerFilter.setIn(ids);
        return super.buildJoinSpecification(integerFilter, JobRequirements_.jobWorkSkills, JobWorkSkill_.skill, Skill_.skillId);
    }
}
