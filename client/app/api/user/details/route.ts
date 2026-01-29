import { ApiClient } from "@/app/lib/api";
import UserInfo from "@/common/interfaces/UserInfo";
import { Routes } from "@/common/routes/routes";
import {NextResponse } from "next/server";

export async function GET(){
    const apiClient = new ApiClient<UserInfo>(process.env.HOST_URL as string)
    const data: UserInfo = await apiClient.get(Routes.server.user.ME)
    return NextResponse.json(data)
}

