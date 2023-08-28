package com.asseco.demo.user;

import com.asseco.demo.usercontact.UserContactData;
import com.asseco.demo.usercontacts.UserContact;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NamedEntityGraphs({
        @NamedEntityGraph(name = "userWithContacts", attributeNodes = {
                @NamedAttributeNode("userContacts")
        })
})
public class User implements UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private long idNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<UserContact> userContacts = new HashSet<>();

    @Override
    public Set<UserContactData> getUserContactsData() {
        return new HashSet<>(getUserContacts());
    }
}
