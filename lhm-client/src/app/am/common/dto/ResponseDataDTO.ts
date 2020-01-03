export class ResponseDataDTO<T>{
    statusCode: number;
    message: string;
    authToken: string;
    data: T;

    constructor(statusCode: number, message: string, authToken:string, data: T) {
        this.statusCode = statusCode;
        this.message = message;
        this.authToken = authToken;
        this.data = data;
    }
}