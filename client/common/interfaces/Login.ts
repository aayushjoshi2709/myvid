export interface LoginRequestBody{
    username: string;
    password: string;
}
export interface LoginResponse{
    accessToken: string;
}