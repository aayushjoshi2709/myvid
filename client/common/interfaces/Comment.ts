import UserInfo from "./UserInfo";

export interface CommentBody{
    id: string;
    author: Partial<UserInfo>;
    message: string;
    createdAt: string;
}