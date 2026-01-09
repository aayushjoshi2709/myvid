import { ApiClient } from "@/app/lib/api"
import UserInfo from "@/common/interfaces/UserInfo"
import { NextRequest, NextResponse } from "next/server"

export async function PUT(req:NextRequest, context: {params: { id: string }}){
    const { id } = context.params; 
    const data: UserInfo = await req.json();
    const apiClient: ApiClient<UserInfo> = new ApiClient<UserInfo>(process.env.HOST_URL as string)
    const response: UserInfo = await apiClient.put(`/api/v1/user/${id}`, data)
    return NextResponse.json(response)
}