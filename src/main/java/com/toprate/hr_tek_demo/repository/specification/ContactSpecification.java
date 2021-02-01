package com.toprate.hr_tek_demo.repository.specification;

import com.toprate.hr_tek_demo.dto.SearchDto;
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
 * Date: 11:30 29/01/2021
 */
@Component
public class ContactSpecification extends BaseQuerySpecification<Contact> {

    public Specification<Contact> searchFilter(SearchDto searchDto) {
        if (searchDto.getContactWorkSkillList().size() == 0 && searchDto.getContactPositionList().size() == 0 && searchDto.getLevel().equals(StringUtils.EMPTY) && searchDto.getKeyWord().equals(StringUtils.EMPTY) && searchDto.getYearExp() == 0f && searchDto.getIsBlackList().equals("ALL")) {
            return null;
        }
        return super.initWhere().and(findByName(searchDto.getKeyWord())).and(findByBlackList(searchDto.getIsBlackList())).and(findYearExperience(searchDto.getYearExp())).and(findByLevel(searchDto.getLevel())).and(findByPosition(searchDto.getContactPositionList())).and(findBySkill(searchDto.getContactWorkSkillList()));
    }

    private Specification<Contact> findYearExperience(Float value) {
        if (value == null || value == 0f)
            return null;
        return super.equalsSpecification(Contact_.YEAR_EXPERIENCE, value.toString());
    }

    private Specification<Contact> findByLevel(String value) {
        if (value == null || StringUtils.EMPTY.equals(value))
            return null;
        return super.equalsSpecification(Contact_.LEVELS, value);
    }

    private Specification<Contact> findByBlackList(String value) {
        if (value == null || StringUtils.EMPTY.equals(value) || "ALL".equals(value)) {
            return null;
        }
        boolean isBlackList = "OFF".equals(value);
        return super.equalsSpecification(Contact_.IS_BLACK_LIST, isBlackList);
    }

    private Specification<Contact> findByName(String name) {
        if (name == null || StringUtils.EMPTY.equals(name)) {
            return null;
        }
        return super.likeSpecification(Contact_.CANDIDATE_NAME, name);
    }

    private Specification<Contact> findByPosition(List<Integer> ids) {
        if (ids.size() == 0)
            return null;
        Filter<Integer> integerFilter = new Filter<>();
        integerFilter.setIn(ids);
        return super.buildJoinSpecification(integerFilter, Contact_.contactPositionList, ContactPosition_.position, Position_.positionId);
    }

    private Specification<Contact> findBySkill(List<Integer> ids) {
        if (ids.size() == 0)
            return null;
        Filter<Integer> integerFilter = new Filter<>();
        integerFilter.setIn(ids);
        return super.buildJoinSpecification(integerFilter, Contact_.contactWorkSkillList, ContactWorkSkill_.skill, Skill_.skillId);
    }
}
