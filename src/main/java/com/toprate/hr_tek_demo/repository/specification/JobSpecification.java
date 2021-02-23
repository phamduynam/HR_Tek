package com.toprate.hr_tek_demo.repository.specification;

import com.toprate.hr_tek_demo.dto.SearchJobDto;
import com.toprate.hr_tek_demo.model.*;
import com.toprate.hr_tek_demo.model.JobRequirements;
import com.toprate.hr_tek_demo.model.JobRequirements_;
import com.toprate.hr_tek_demo.model.JobWorkSkill_;
import com.toprate.hr_tek_demo.model.Skill_;
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
            return super.initWhere();
        }
        return super.initWhere().and(findByLevel(level)).and(findByJobTitle(keyword)).and(findByYearExperience(yearExperience)).and(findBySkill(ids));
    }

    // tim kiem theo toan bo cac tieu chi
    public Specification<JobRequirements> searchJob(SearchJobDto data) {
        if (data.getLevel().equals(StringUtils.EMPTY) && data.getYearExperience() == null && data.getLocation().equals(StringUtils.EMPTY) && data.getPartner().equals(StringUtils.EMPTY) && data.getJobWorkSkillList().size() == 0 && data.getJobPositionList().size() == 0 && data.getKeyword().equals(StringUtils.EMPTY)) {
            return super.initWhere();
        }
        return findByYearExperience(data.getYearExperience())
                .and(findByLevel(data.getLevel()))
                .and(findByLocation(data.getLocation()))
                .and(findByPartner(data.getPartner()))
                .and(findByPosition(data.getJobPositionList()))
                .and(findBySkill(data.getJobWorkSkillList()))
                .and(findByJobTitle(data.getKeyword()).or(findLikeByLevel(data.getKeyword())).or(findLikeByLocation(data.getKeyword())).or(findLikeByPartner(data.getKeyword())));
    }

    // tim kiem theo tieu de
    private Specification<JobRequirements> findByJobTitle(String value) {
        if (value == null || StringUtils.EMPTY.equals(value)) {
            return super.initWhere();
        }
        return super.likeSpecification(JobRequirements_.JOB_TITLE, value);
    }

    // tim kiem theo so nam KN
    private Specification<JobRequirements> findByYearExperience(Float value) {
        if (value == null) {
            return super.initWhere();
        }
        return super.equalsSpecification(JobRequirements_.YEAR_EXPERIENCE, value);
    }

    // tim kiem theo level
    private Specification<JobRequirements> findByLevel(String level) {
        if (level == null || StringUtils.EMPTY.equals(level)) {
            return super.initWhere();
        }
        return super.equalsSpecification(JobRequirements_.LEVEL, level);
    }

    // tim kiem theo level su dung like
    private Specification<JobRequirements> findLikeByLevel(String level) {
        if (level == null || StringUtils.EMPTY.equals(level)) {
            return super.initWhere();
        }
        return super.likeSpecification(JobRequirements_.LEVEL, level);
    }

    // tim kiem theo vi tri
    private Specification<JobRequirements> findByLocation(String address) {
        if (address == null || StringUtils.EMPTY.equals(address)) {
            return super.initWhere();
        }
        Filter<String> stringFilter = new Filter<>();
        stringFilter.setEquals(address);
        return super.buildJoinSpecification(stringFilter, JobRequirements_.location, Location_.address);
    }

    // tim kiem theo vi tri su dung like
    private Specification<JobRequirements> findLikeByLocation(String address) {
        if (address == null || StringUtils.EMPTY.equals(address)) {
            return super.initWhere();
        }
        Filter<String> stringFilter = new Filter<>();
        stringFilter.setLike(address);
        return super.buildJoinSpecification(stringFilter, JobRequirements_.location, Location_.address);
    }

    // tim kiem theo doi tac
    private Specification<JobRequirements> findByPartner(String partner) {
        if (partner == null || StringUtils.EMPTY.equals(partner)) {
            return super.initWhere();
        }
        Filter<String> stringFilter = new Filter<>();
        stringFilter.setEquals(partner);
        return super.buildJoinSpecification(stringFilter, JobRequirements_.partner, Partner_.partnerName);
    }

    // tim kiem theo doi tac su dung like
    private Specification<JobRequirements> findLikeByPartner(String partner) {
        if (partner == null || StringUtils.EMPTY.equals(partner)) {
            return super.initWhere();
        }
        Filter<String> stringFilter = new Filter<>();
        stringFilter.setLike(partner);
        return super.buildJoinSpecification(stringFilter, JobRequirements_.partner, Partner_.partnerName);
    }

    // tim kiem theo ky nang
    private Specification<JobRequirements> findBySkill(List<Integer> skills) {
        if (skills.size() == 0) {
            return super.initWhere();
        }
        Filter<Integer> integerFilter = new Filter<>();
        integerFilter.setIn(skills);
        return super.buildJoinSpecification(integerFilter, JobRequirements_.jobWorkSkills, JobWorkSkill_.skill, Skill_.skillId);
    }

    // tim kiem theo vi tri tuyen dung
    private Specification<JobRequirements> findByPosition(List<Integer> positions) {
        if (positions.size() == 0) {
            return super.initWhere();
        }
        Filter<Integer> integerFilter = new Filter<>();
        integerFilter.setIn(positions);
        return super.buildJoinSpecification(integerFilter, JobRequirements_.jobPositionList, JobPosition_.position, Position_.positionId);
    }
}
