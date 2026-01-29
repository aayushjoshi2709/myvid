import UserInfo from "./UserInfo";

export interface CommentBody{
    id: string;
    author: Partial<UserInfo>;
    message: string;
    createdAt: string;
    videoId: string;
    parentCommentId: string | null;
    replyCount: number;
}

export interface AddCommentBody {
    message: string;
    parentCommentId?: string | null;
}