import { NextResponse } from "next/server";
import { ApiClient } from "@/app/lib/api";
import { LoginRequestBody, LoginResponse } from "@/common/interfaces/Login";


export async function POST(req:Request){
    const body: LoginRequestBody = await req.json();
    const apiClient = new ApiClient<LoginResponse>(process.env.HOST_URL as string);
    const data:LoginResponse = await apiClient.post("/api/v1/user/login", body);
    const response = NextResponse.json({ success: true });
    response.cookies.set("accessToken", data.accessToken, {
        httpOnly: true,
        secure: true,
        sameSite: "strict",
        path: "/",
        maxAge: 60 * 60,
    });
    return response;
}