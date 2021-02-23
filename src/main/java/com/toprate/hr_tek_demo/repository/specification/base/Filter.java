package com.toprate.hr_tek_demo.repository.specification.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: namnv
 * Date: 10:30 29/01/2021
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Filter<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private T equals;
    private T like;
    private T notEquals;
    private List<T> in;
    private List<T> notIn;
}
