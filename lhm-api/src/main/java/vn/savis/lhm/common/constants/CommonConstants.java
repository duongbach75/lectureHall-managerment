package vn.savis.lhm.common.constants;

/**
 * @author: SonNH - SAVIS
 * @created: 9/12/19
 * @Time: 15:23
 * @modified 9/12/19
 **/
public class CommonConstants {
    public static final int DELETE_STATUS = -1;

    /**
     * IP Address patterns.
     */
    public static final String IP_ADDRESS_PAT = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    public static final String NAME_PATTERN = "(( *)[a-zA-Z0-9_-ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴàáãạảăắằẳẵặâấầẩẫậèéẹẻẽêềếểễệđìíĩỉịòóõọỏôốồổỗộơớờởỡợùúũụủưứừửữựỳỵỷỹý]( *)+)*$";

    public static final String CODE_PATTERN = "^[a-zA-Z0-9_-]+$";

    public static final String INT_NUMBER_PATTERN = "^[1-9]*$";

    public static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";

    public static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";

    public static final String PHONE_NUMBER_PATTERN = "";

    public static final String ERR_TOKEN_EXPIRED = "TOKEN_EXPIRED";

    public final class STATUS {

        public static final String TRUE = "1";

        public static final String FALSE = "0";
    }

    public final class USER_ROLE {

        public static final String ROLE_ADMIN = "ROLE_ADMIN";

        public static final String ROLE_MEMBER = "ROLE_MEMBER";
    }

    public final class HEADER_FIELD {
        public static final String AUTHORIZATION = "Authorization";
        public static final String CONTENT_TYPE = "Content-Type";
    }

    public final class HEADER_VALUE {

    }

    /**
     * Default login user
     */
    public static final String DEFAULT_USER = "admin";

    /**
     * Action History Action
     */
    public final class ACTION_HISTORY_ACTION {

        public static final String CREATED = "Created";

        public static final String UPDATED = "Updated";

        public static final String DELETED = "Deleted";

        public static final String LOGIN = "Login";

        public static final String LOGOUT = "Logout";
    }

    /**
     * Action History Module
     */

    public final class ACTION_HISTORY_MODULE {

        public final class API {

            public static final String API = "API";

            public static final String DOCUMENT = "API Document";

            public static final String ENDPOINT = "API Endpoint";

            public static final String LC_EVENT = "API Lc Event";

            public static final String METHOD_PARAM = "API Method Parameter";

            public static final String URL_MAPPING = "API Url Mapping";

            public static final String VERSION = "API Version";
        }

        public final class APPLICATION {

            public static final String APPLICATION = "Application";

            public static final String TOKEN = "Application Token";
        }

        public final class CONDITION {

            public static final String CONDITION_GROUP = "Condition Group";

            public static final String BLOCK_CONDITION = "Block Condition";

            public static final String HEADER_FIELD_CONDITION = "Header Field Condition";

            public static final String IP_CONDITION = "IP Condition";

            public static final String JWT_CLAIM_CONDITION = "JWT Claim Condition";

            public static final String QUERY_PARAM_CONDITION = "Query Parameter Condition";
        }

        public final class CATEGORY {

            public static final String PROVINCE = "Province";

            public static final String DISTRICT = "District";

            public static final String WARD = "Ward";

            public static final String MINISTRY = "Ministry";

            public static final String DEPARTMENT = "Department";

            public static final String SUBSCRIBER = "Subscriber";

            public static final String SUBSCRIPTION = "Subscription";

            public static final String POLICY = "Policy";

            public static final String USER = "User";
        }

        public final class SYSTEM {

            public static final String LOGIN = "Login";

            public static final String LOGOUT = "Logout";
        }

        public final class ACTIVE_DIRECTORY {
            public static final String AD_OU = "AD Organizational Unit";

            public static final String AD_GROUP = "AD Group";

            public static final String AD_USER = "AD User";

            public static final String AD_MAP_GROUP_USER = "AD map User to Group";

            public static final String LDAP_GROUP = "LDAP Group";

            public static final String LDAP_USER = "LDAP User";

            public static final String LDAP_MAP_GROUP_USER = "LDAP map User to Group";

        }


    }
}
