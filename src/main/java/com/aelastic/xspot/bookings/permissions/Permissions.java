package com.aelastic.xspot.bookings.permissions;

import org.springframework.transaction.annotation.Transactional;

public class Permissions {

    Permission ownerPermissions;

    Permission othersPermission;

    Permission serverPermissions;





    public static enum Permission {

        READ,

        WRITE,

        READ_WRITE,

    }

}
