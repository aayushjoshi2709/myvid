import { ApiClient } from "@/app/lib/api"
import { AddCommentBody, CommentBody } from "@/common/interfaces/Comment"
import { NextResponse } from "next/server"

export async function GET(
    req:Request,
    context: { params: Promise<{ id: string }> }
){
    const {id} = await context.params;
    const { searchParams } = new URL(req.url);
    const apiClient = new ApiClient<CommentBody>(process.env.HOST_URL as string)
    let data: CommentBody|null = null;
    const parentCommentId = searchParams.get("parentCommentId")
    if(parentCommentId != null){
        data = await apiClient.get(`/api/v1/video/${id}/comment?parentCommentId=${searchParams.get("parentCommentId")}`)
    }else{
        data = await apiClient.get(`/api/v1/video/${id}/comment`)
    }
    const response = NextResponse.json({ success: true, data: data });
    return response;
}


export async function POST(
    req:Request,
    context: { params: Promise<{ id: string }> }
){
    const {id} = await context.params;
    const apiClient = new ApiClient<CommentBody>(process.env.HOST_URL as string)
    const data:AddCommentBody = await req.json()
    await apiClient.post(`/api/v1/video/${id}/comment`, data)
    const response = NextResponse.json({ success: true, data: data });
    return response;
}