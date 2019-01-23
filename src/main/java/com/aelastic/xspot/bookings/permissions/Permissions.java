package com.aelastic.xspot.bookings.permissions;

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
