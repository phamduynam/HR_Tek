package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.model.ExceptionLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: namnv
 * Date: 17:46 17/02/2021
 */
public interface ExceptionLogService {
    void insert(ExceptionLog exceptionLog);

    List<ExceptionLog> getAll();
}
