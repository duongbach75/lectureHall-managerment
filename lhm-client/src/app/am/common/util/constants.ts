export class Constants {
    // Url API local site
    static BASE_URL = 'http://localhost:8080/api/v1/categories';
    // static BASE_URL = 'http://103.56.160.31:8080/api/savis/categories/api/v1/';
    // static BASE_URL = 'http://10.1.47.141:8080/api/savis/categories/api/v1/';

    static RUNTIME_URL = 'http://localhost:8080';
    // static RUNTIME_URL = 'http://103.56.160.31:8082';
    // static RUNTIME_URL = 'http://10.1.47.141:8082';

    static STATUS_LIST = [
        { id: 1, name: 'Active' },
        { id: 0, name: 'Inactive' }
    ];


    static NAME_PATTERN = "(( *)[a-zA-Z0-9_-ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴàáãạảăắằẳẵặâấầẩẫậèéẹẻẽêềếểễệđìíĩỉịòóõọỏôốồổỗộơớờởỡợùúũụủưứừửữựỳỵỷỹý]( *)+)*$";

    static CODE_PATTERN = "^[a-zA-Z0-9_-]+$";

    static IP_PATTERN = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$";

    static USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";

    static PASSWORD_PATTERN = "((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    static EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    static IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";

    static PHONE_NUMBER_PATTERN = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";

    static IS_AUTHENTIC = "isAuthentic";

    static CURRENT_USER = "currentUser";

    static ACCESS_TOKEN = "accessToken";

    static EXPIRE_TIME = "expireYime";

    static SESSION_STATE = "sessionState";

    static KEY_LANGUAGE = "keyLanguage";

    static RETURN_URL = "returnUrl";

    static PAGE_SIZE = 5;
}
