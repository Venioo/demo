package com.asseco.demo.user;

import com.asseco.demo.usercontacts.UserContact;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public abstract class User_ {
    public static SingularAttribute<User, Long> id;
    public static SingularAttribute<User, String> firstName;
    public static SingularAttribute<User, String> lastName;
    public static SingularAttribute<User, Long> idNumber;

    public static SetAttribute<User, UserContact> userContacts;
}
