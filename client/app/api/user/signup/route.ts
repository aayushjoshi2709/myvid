import { ApiClient } from "@/app/lib/api";
import UserInfo from "@/common/interfaces/UserInfo";
import { NextResponse } from "next/server";

export async function POST(req:Request){
    const body: Partial<UserInfo> = await req.json();
    const apiClient = new ApiClient<UserInfo>(process.env.HOST_URL as string);
    const data = apiClient.post("/api/v1/user",body)
    const response = NextResponse.json({ success: true, data: data });
    return response;
}