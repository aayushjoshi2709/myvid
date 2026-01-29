import { NextResponse } from "next/server";
import { ApiClient } from "@/app/lib/api";
import { LoginRequestBody, LoginResponse } from "@/common/interfaces/Login";
import { Routes } from "@/common/routes/routes";


export async function POST(req:Request){
    const body: LoginRequestBody = await req.json();
    const apiClient = new ApiClient<LoginResponse>(process.env.HOST_URL as string);
    const data:LoginResponse = await apiClient.post(Routes.server.user.LOGIN, body);
    const response = NextResponse.json({ success: true });
    response.cookies.set("accessToken", data.accessToken, {
        httpOnly: true,
        secure: process.env.NODE_ENV === "production",
        sameSite: "lax",
        path: "/",
        maxAge: 60 * 60,
    });
    return response;
}