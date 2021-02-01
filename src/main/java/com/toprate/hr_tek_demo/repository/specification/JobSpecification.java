package com.toprate.hr_tek_demo.repository.specification;

import com.toprate.hr_tek_demo.dto.SearchJobDto;
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
        return super.initWhere().and(findByLevel(level)).and(findByYearKeyWord(keyword)).and(findByYearExperience(yearExperience)).and(findBySkill(ids));
    }

    public Specification<JobRequirements> searchJob(SearchJobDto data) {
        if (data.getLevel().equals(StringUtils.EMPTY) && data.getYearExperience() == null && data.getLocation().equals(StringUtils.EMPTY) && data.getPartner().equals(StringUtils.EMPTY) && data.getJobWorkSkillList().size() == 0 && data.getJobPositionList().size() == 0 ) {
            return null;
        }
        return super.initWhere().and(findByYearExperience(data.getYearExperience())).and(findByLevel(data.getLevel())).and(findByLocation(data.getLocation())).and(findByPartner(data.getPartner())).and(findByPosition(data.getJobPositionList())).and(findBySkill(data.getJobWorkSkillList()));
    }

    private Specification<JobRequirements> findByYearKeyWord(String value) {
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

    private Specification<JobRequirements> findByLocation(String name) {
        if (name == null || StringUtils.EMPTY.equals(name)) {
            return null;
        }
        Filter<String> stringFilter = new Filter<>();
        stringFilter.setEquals(name);
        return super.buildJoinSpecification(stringFilter, JobRequirements_.location, Location_.address);
    }

    private Specification<JobRequirements> findByPartner(String name) {
        if (name == null || StringUtils.EMPTY.equals(name)) {
            return null;
        }
        Filter<String> stringFilter = new Filter<>();
        stringFilter.setEquals(name);
        return super.buildJoinSpecification(stringFilter, JobRequirements_.partner, Partner_.partnerName);
    }

    private Specification<JobRequirements> findBySkill(List<Integer> ids) {
        if (ids.size() == 0)
            return null;
        Filter<Integer> integerFilter = new Filter<>();
        integerFilter.setIn(ids);
        return super.buildJoinSpecification(integerFilter, JobRequirements_.jobWorkSkills, JobWorkSkill_.skill, Skill_.skillId);
    }

    private Specification<JobRequirements> findByPosition(List<Integer> ids) {
        if (ids.size() == 0)
            return null;
        Filter<Integer> integerFilter = new Filter<>();
        integerFilter.setIn(ids);
        return super.buildJoinSpecification(integerFilter, JobRequirements_.jobPositionList, JobPosition_.position, Position_.positionId);
    }
}
