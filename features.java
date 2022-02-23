package com.assement.featureswtiches;

import javax.persistence.*;

@Entity
public class features {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        public int ID;
        @Column(name = "EMAIL")
        public String email;
        @Column(name = "FEATURES")
        public String featureName;
        @Column(name = "ACCESS")
        public boolean enable;


}
