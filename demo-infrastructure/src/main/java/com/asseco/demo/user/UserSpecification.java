package com.asseco.demo.user;

import com.asseco.demo.common.UserContactCode;
import com.asseco.demo.user.search.UserSearchCriteria;
import com.asseco.demo.usercontacts.UserContact_;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserSpecification implements Specification<User> {

    private final UserSearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        Optional.ofNullable(criteria)
                .map(UserSearchCriteria::getIds)
                .filter(CollectionUtils::isNotEmpty)
                .map(ids -> root.get(User_.id).in(ids))
                .ifPresent(predicates::add);

        Optional.ofNullable(criteria)
                .map(UserSearchCriteria::getFirstNames)
                .filter(CollectionUtils::isNotEmpty)
                .map(firstNames -> root.get(User_.firstName).in(firstNames))
                .ifPresent(predicates::add);

        Optional.ofNullable(criteria)
                .map(UserSearchCriteria::getLastNames)
                .filter(CollectionUtils::isNotEmpty)
                .map(lastNames -> root.get(User_.lastName).in(lastNames))
                .ifPresent(predicates::add);

        Optional.ofNullable(criteria)
                .map(UserSearchCriteria::getIdNumbers)
                .filter(CollectionUtils::isNotEmpty)
                .map(idNumbers -> root.get(User_.idNumber).in(idNumbers))
                .ifPresent(predicates::add);

        var contactsJoin = root.join(User_.userContacts, JoinType.LEFT);

        Optional.ofNullable(criteria)
                .map(UserSearchCriteria::getContactCodes)
                .filter(CollectionUtils::isNotEmpty)
                .map(codes -> codes.stream().map(UserContactCode::valueOf).collect(Collectors.toSet()))
                .map(contactCodes -> contactsJoin.get(UserContact_.code).in(contactCodes))
                .ifPresent(predicates::add);

        Optional.ofNullable(criteria)
                .map(UserSearchCriteria::getContactValues)
                .filter(CollectionUtils::isNotEmpty)
                .map(contactValues -> contactsJoin.get(UserContact_.value).in(contactValues))
                .ifPresent(predicates::add);

        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

}
