import { ApiClient } from "@/app/lib/api";
import UserInfo from "@/common/interfaces/UserInfo";
import { Routes } from "@/common/routes/routes";
import { NextResponse } from "next/server";

export async function POST(req:Request){
    const body: Partial<UserInfo> = await req.json();
    const apiClient = new ApiClient<UserInfo>(process.env.HOST_URL as string);
    const data = apiClient.post(Routes.server.user.ADD,body)
    const response = NextResponse.json({ success: true, data: data });
    return response;
}