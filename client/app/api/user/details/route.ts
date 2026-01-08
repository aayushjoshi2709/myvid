import { ApiClient } from "@/app/lib/api";
import UserInfo from "@/common/interfaces/UserInfo";
import { NextResponse } from "next/server";

export async function GET(){
    const apiClient = new ApiClient<UserInfo>(process.env.HOST_URL as string)
    const data: UserInfo = await apiClient.get("/api/v1/user/me")
    return NextResponse.json(data)
}