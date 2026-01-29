import { ApiClient } from "@/app/lib/api"
import UserInfo from "@/common/interfaces/UserInfo"
import { Routes } from "@/common/routes/routes";
import { NextRequest, NextResponse } from "next/server"
import util from "node:util"

export async function PATCH(
    req:NextRequest, 
    context: { params: Promise<{ id: string }> }
    ){
    const { id } = await context.params; 
    const data: UserInfo = await req.json();
    const apiClient: ApiClient<UserInfo> = new ApiClient<UserInfo>(process.env.HOST_URL as string)
    const response: UserInfo = await apiClient.patch(util.format(Routes.server.user.UPDATE, id), data)
    return NextResponse.json(response)
}


export async function DELETE(
    req:NextRequest, 
    context: { params: Promise<{ id: string }> }
    ){
    const { id } = await context.params; 
    const apiClient: ApiClient<UserInfo> = new ApiClient<UserInfo>(process.env.HOST_URL as string)
    await apiClient.delete(util.format(Routes.server.user.DELETE, id))
}