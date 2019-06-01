package com.naruto.chapter01.predicate;

import com.naruto.chapter01.vo.EmployeeVO;

public class FilterEmployeeByAge implements MyPredicate<EmployeeVO> {

    @Override
    public boolean test(EmployeeVO employeeVO) {
        return employeeVO.getAge() > 35;
    }
}
