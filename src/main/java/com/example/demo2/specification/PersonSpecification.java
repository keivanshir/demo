package com.example.demo2.specification;

import com.example.demo2.entity.Address;
import com.example.demo2.entity.Person;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class PersonSpecification {

    public static Specification<Person> personsByAddressId(Long addressId){
        return (root, query, criteriaBuilder) -> {
            Join<Person, Address> personAddressJoin = root.join("addressList", JoinType.LEFT);
            return criteriaBuilder.equal(personAddressJoin.get("id"), addressId);
        };
    }
}
