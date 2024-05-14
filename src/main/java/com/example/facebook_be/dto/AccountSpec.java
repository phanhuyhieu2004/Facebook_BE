package com.example.facebook_be.dto;

import com.example.facebook_be.model.Account;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class AccountSpec implements Specification<Account> {
    private AccountRequest accountRequest;

    public AccountSpec(AccountRequest accountRequest) {
        this.accountRequest = accountRequest;
    }

    @Override
//    các điều kiện (predicates)
    //    đối tượng gốc của câu truy vấn, thường được sử dụng để truy cập các trường của đối tượng cơ sở dữ liệu
//xây dựng các điều kiện truy vấn.
    public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (accountRequest.getUsername() != null && !accountRequest.getUsername().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("username"), "%" + accountRequest.getUsername() + "%"));
        }
        if (accountRequest.getBirthday()!= null && !accountRequest.getBirthday().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("birthday"), "%" + accountRequest.getBirthday() + "%"));
        }
        predicates.add(criteriaBuilder.notEqual(root.get("role"), 0));
        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(new Predicate[0]));
        }

        return query.getRestriction();
    }

}
