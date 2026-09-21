export const Routes = {
    server:{
        storage:{
            PRESIGNED_URL: "/api/video/v1/storage/presignedUrl"
        },
        comment:{
            GET_ALL:"/api/comment/v1/%s/comment",
            GET_ALL_CHILD:"/api/comment/v1/%s/comment?parentCommentId=%s",
            ADD: "/api/comment/v1/%s/comment"
        },
        video:{
            ADD: "/api/video/v1/video",
            GET: "/api/video/v1/video/%s"
        },
        user:{
            ME: "/api/auth/v1/user/me",
            DELETE: "/api/auth/v1/user/%s",
            ADD: "/api/auth/v1/user",
            LOGIN:"/api/auth/v1/user/login",
            GET_USER: "/api/auth/v1/user/%s",
            UPDATE: "/api/auth/v1/user/%s",
            LOGOUT: "/api/auth/v1/logout"
        }
    }
}