export interface LoginRequestBody{
    username: string;
    password: string;
}
export interface LoginResponse{
    jwtToken: string;
    refreshToken: string;
}