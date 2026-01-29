import { ApiClient } from "@/app/lib/api"
import { AddCommentBody, CommentBody } from "@/common/interfaces/Comment"
import { Routes } from "@/common/routes/routes";
import { NextResponse } from "next/server"
import util from "node:util";

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
        data = await apiClient.get(util.format(Routes.server.comment.GET_ALL_CHILD, id, parentCommentId))
    }else{
        console.log("here is the path", util.format(Routes.server.comment.GET_ALL, id))
        data = await apiClient.get(util.format(Routes.server.comment.GET_ALL, id))
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
    await apiClient.post(util.format(Routes.server.comment.ADD, id), data)
    const response = NextResponse.json({ success: true, data: data });
    return response;
}