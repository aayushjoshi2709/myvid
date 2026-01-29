export const Routes = {
    server:{
        storage:{
            PRESIGNED_URL: "/api/v1/storage/presignedUrl"
        },
        comment:{
            GET_ALL:"/api/v1/video/%s/comment",
            GET_ALL_CHILD:"/api/v1/video/%s/comment?parentCommentId=%s",
            ADD: "/api/v1/video/%s/comment"
        },
        video:{
            ADD: "/api/v1/video",
            GET: "/api/v1/video/%s"
        },
        user:{
            ME: "/api/v1/user/me",
            DELETE: "/api/v1/user/%s",
            ADD: "/api/v1/user",
            LOGIN:"/api/v1/user/login",
            GET_USER: "/api/v1/user/%s",
            UPDATE: "/api/v1/user/%s",
            LOGOUT: "/api/v1/logout"
        }
    }
}