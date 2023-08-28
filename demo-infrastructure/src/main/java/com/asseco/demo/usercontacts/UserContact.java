package com.asseco.demo.usercontacts;

import com.asseco.demo.common.UserContactCode;
import com.asseco.demo.user.User;
import com.asseco.demo.usercontact.UserContactData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserContact implements UserContactData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserContactCode code;

    private String value;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

}
