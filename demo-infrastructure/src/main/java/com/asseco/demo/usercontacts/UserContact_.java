package com.asseco.demo.usercontacts;

import com.asseco.demo.common.UserContactCode;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UserContact.class)
public abstract class UserContact_ {
    public static SingularAttribute<UserContact, Long> id;
    public static SingularAttribute<UserContact, UserContactCode> code;
    public static SingularAttribute<UserContact, String> value;
}
