import { ApiClient } from "@/app/lib/api";
import { Routes } from "@/common/routes/routes";
import { NextResponse } from "next/server";

export async function POST(){
    const apiClient = new ApiClient<null>(process.env.HOST_URL as string);
    await apiClient.post(Routes.server.user.LOGOUT, null);
    const res = NextResponse.json({success: true})
        res.cookies.set("accessToken", "", {
        maxAge: 0,
        path: "/",
    });

  return res;

}